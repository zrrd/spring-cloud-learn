package cn.learn.springboot.filter;

import io.netty.buffer.ByteBufAllocator;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.MimeType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 缓存请求数据
 *
 * @author shaoyijiong
 * @date 2019/5/31
 */
@Component
@Slf4j
public class GatewayContextFilter implements GlobalFilter, Ordered {

  /**
   * default HttpMessageReader
   */
  private static final List<HttpMessageReader<?>> MESSAGE_READERS = HandlerStrategies.withDefaults()
      .messageReaders();

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

    //获取请求
    ServerHttpRequest request = exchange.getRequest();
    String path = request.getPath().pathWithinApplication().value();
    GatewayContext gatewayContext = new GatewayContext();
    gatewayContext.getRequestData().addAll(request.getQueryParams());
    gatewayContext.setPath(path);
    //上下文添加属性
    exchange.getAttributes().put(GatewayContext.CACHE_GATEWAY_CONTEXT, gatewayContext);
    HttpHeaders headers = request.getHeaders();
    MediaType contentType = headers.getContentType();
    long contentLength = headers.getContentLength();
    if (contentLength > 0) {
      if (MediaType.APPLICATION_JSON.isCompatibleWith(contentType) || MediaType.APPLICATION_JSON_UTF8
          .isCompatibleWith(contentType)) {
        //获取json请求
        return readBody(exchange, chain, gatewayContext);
      }
      if (MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(contentType)) {
        //获取表单请求
        return readFormData(exchange, chain, gatewayContext);
      }
    }
    log.debug("[GatewayContext]ContentType:{},Gateway context is set with {}", contentType,
        gatewayContext);
    return chain.filter(exchange);

  }


  @Override
  public int getOrder() {
    return Integer.MIN_VALUE;
  }

  /**
   * 读取表单信息
   */
  private Mono<Void> readFormData(ServerWebExchange exchange, GatewayFilterChain chain,
      GatewayContext gatewayContext) {
    HttpHeaders headers = exchange.getRequest().getHeaders();
    return exchange.getFormData()
        .doOnNext(multiValueMap -> {
          gatewayContext.setFormData(multiValueMap);
          log.debug("[GatewayContext]Read FormData:{}", multiValueMap);
        })
        .then(Mono.defer(() -> {
          Charset charset = Optional.ofNullable(headers.getContentType()).map(MimeType::getCharset)
              .orElse(StandardCharsets.UTF_8);
          String charsetName = charset.name();
          MultiValueMap<String, String> formData = gatewayContext.getFormData();
          //如果为空直接返回
          if (null == formData || formData.isEmpty()) {
            return chain.filter(exchange);
          }
          StringBuilder formDataBodyBuilder = new StringBuilder();
          String entryKey;
          List<String> entryValue;
          try {
            //包装表单信息
            for (Map.Entry<String, List<String>> entry : formData.entrySet()) {
              entryKey = entry.getKey();
              entryValue = entry.getValue();
              if (entryValue.size() > 1) {
                for (String value : entryValue) {
                  formDataBodyBuilder.append(entryKey).append("=")
                      .append(URLEncoder.encode(value, charsetName)).append("&");
                }
              } else {
                formDataBodyBuilder.append(entryKey).append("=")
                    .append(URLEncoder.encode(entryValue.get(0), charsetName)).append("&");
              }
            }
          } catch (UnsupportedEncodingException e) {
            log.error("base64 error", e);
          }

          //substring with the last char '&'
          String formDataBodyString = "";
          if (formDataBodyBuilder.length() > 0) {
            formDataBodyString = formDataBodyBuilder.substring(0, formDataBodyBuilder.length() - 1);
          }

          //get data bytes
          byte[] bodyBytes = formDataBodyString.getBytes(charset);
          int contentLength = bodyBytes.length;
          ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(
              exchange.getRequest()) {
            /**
             * change content-length
             */
            @Override
            public HttpHeaders getHeaders() {
              HttpHeaders httpHeaders = new HttpHeaders();
              httpHeaders.putAll(super.getHeaders());
              if (contentLength > 0) {
                httpHeaders.setContentLength(contentLength);
              } else {
                httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
              }
              return httpHeaders;
            }

            /**
             * 从流中获取数据
             */
            @Override
            public Flux<DataBuffer> getBody() {
              return DataBufferUtils.read(new ByteArrayResource(bodyBytes),
                  new NettyDataBufferFactory(ByteBufAllocator.DEFAULT), contentLength);
            }
          };
          ServerWebExchange mutateExchange = exchange.mutate().request(decorator).build();
          log.debug("[GatewayContext]Rewrite Form Data :{}", formDataBodyString);
          return chain.filter(mutateExchange);
        }));
  }

  /**
   * 获取json请求
   */
  private Mono<Void> readBody(ServerWebExchange exchange, GatewayFilterChain chain,
      GatewayContext gatewayContext) {

    //join the body
    return DataBufferUtils.join(exchange.getRequest().getBody())
        .flatMap(dataBuffer -> {
          //read the body Flux<Databuffer>
          DataBufferUtils.retain(dataBuffer);
          Flux<DataBuffer> cachedFlux = Flux
              .defer(() -> Flux.just(dataBuffer.slice(0, dataBuffer.readableByteCount())));
          //repackage ServerHttpRequest
          ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public Flux<DataBuffer> getBody() {
              return cachedFlux;
            }
          };
          //mutate exchage with new ServerHttpRequest
          ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();

          //read body string with default messageReaders
          return ServerRequest.create(mutatedExchange, MESSAGE_READERS)
              .bodyToMono(String.class)
              .doOnNext(objectValue -> {
                gatewayContext.setCacheBody(objectValue);
                log.debug("[GatewayContext]Read JsonBody:{}", objectValue);
              }).then(chain.filter(mutatedExchange));
        });
  }

}

