package cn.learn.springboot.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 测试
 *
 * @author shaoyijiong
 * @date 2019/5/31
 */
@Component
@Slf4j
public class TestFilter implements GlobalFilter, Ordered {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    GatewayContext gatewayContext = exchange.getAttribute(GatewayContext.CACHE_GATEWAY_CONTEXT);
    log.info("参数[{}]", gatewayContext);
    return chain.filter(exchange);
  }

  @Override
  public int getOrder() {
    return 5;
  }
}
