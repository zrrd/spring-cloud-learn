package cn.learn.springboot.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayParamFlowItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;

/**
 * @author shaoyijiong
 * @date 2019/5/23
 */
@Configuration
public class MyGatewayConfiguration {

  private final List<ViewResolver> viewResolvers;
  private final ServerCodecConfigurer serverCodecConfigurer;

  public MyGatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider,
      ServerCodecConfigurer serverCodecConfigurer) {
    this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
    this.serverCodecConfigurer = serverCodecConfigurer;
  }

  @Primary
  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
    // Register the block exception handler for Spring Cloud Gateway.
    GatewayCallbackManager.setBlockHandler((serverWebExchange, throwable) -> ServerResponse.status(
        HttpStatus.OK)
        .body(BodyInserters.fromObject(ImmutableMap.of("code", 601, "message", "服务器忙"))));
    return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
  }

  @Primary
  @Bean
  @Order(-1)
  public GlobalFilter sentinelGatewayFilter() {
    return new SentinelGatewayFilter();
  }

  @PostConstruct
  public void doInit() {
    //自定义api限流规则
    initCustomizedApis();
    //网关限流的规则
    initGatewayRules();
  }

  private void initCustomizedApis() {
    Set<ApiDefinition> definitions = new HashSet<>();
    ApiDefinition api1 = new ApiDefinition("some_customized_api")
        .setPredicateItems(new HashSet<ApiPredicateItem>() {{
          add(new ApiPathPredicateItem().setPattern("/ahas"));
          add(new ApiPathPredicateItem().setPattern("/product/**")
              .setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_PREFIX));
        }});
    ApiDefinition api2 = new ApiDefinition("another_customized_api")
        .setPredicateItems(new HashSet<ApiPredicateItem>() {{
          add(new ApiPathPredicateItem().setPattern("/**")
              .setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_PREFIX));
        }});
    definitions.add(api1);
    definitions.add(api2);
    GatewayApiDefinitionManager.loadApiDefinitions(definitions);
  }

  private void initGatewayRules() {
    Set<GatewayFlowRule> rules = new HashSet<>();
    //resource：资源名称，可以是网关中的 route 名称或者用户自定义的 API 分组名称
    rules.add(new GatewayFlowRule("lb://service-consumer")
        //限流阈值
        .setCount(10)
        //统计时间窗口，单位是秒，默认是 1 秒（目前仅对参数限流生效）。
        .setIntervalSec(1)
    );
    rules.add(new GatewayFlowRule("aliyun_route")
        .setCount(2)
        .setIntervalSec(2)
        //应对突发请求时额外允许的请求数目（目前仅对参数限流生效）。
        .setBurst(2)
        .setParamItem(new GatewayParamFlowItem()
            //针对ip限流
            .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_CLIENT_IP)
        )
    );
    rules.add(new GatewayFlowRule("httpbin_route")
        .setCount(10)
        .setIntervalSec(1)
        //流量整形的控制效果，同限流规则的 controlBehavior 字段，目前支持快速失败和匀速排队两种模式，默认是快速失败。
        .setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER)
        //匀速排队模式下的最长排队时间，单位是毫秒，仅在匀速排队模式下生效。
        .setMaxQueueingTimeoutMs(600)
        //参数限流配置。若不提供，则代表不针对参数进行限流，该网关规则将会被转换成普通流控规则；否则会转换成热点规则。其中的字段：
        //parseStrategy：从请求中提取参数的策略，目前支持提取来源 IP（PARAM_PARSE_STRATEGY_CLIENT_IP）、Host（PARAM_PARSE_STRATEGY_HOST）、任意 Header（PARAM_PARSE_STRATEGY_HEADER）和任意 URL 参数（PARAM_PARSE_STRATEGY_URL_PARAM）四种模式。
        //fieldName：若提取策略选择 Header 模式或 URL 参数模式，则需要指定对应的 header 名称或 URL 参数名称。
        //pattern 和 matchStrategy：为后续参数匹配特性预留，目前未实现。
        .setParamItem(new GatewayParamFlowItem()
            //根据请求头限流
            .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_HEADER)
            //请求头name
            .setFieldName("X-Sentinel-Flag")
        )
    );
    rules.add(new GatewayFlowRule("httpbin_route")
        .setCount(1)
        .setIntervalSec(1)
        .setParamItem(new GatewayParamFlowItem()
            //根据请求参数限流
            .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
            //参数name
            .setFieldName("pa")
        )
    );

    //对应上面的api组
    rules.add(new GatewayFlowRule("some_customized_api")
        .setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME)
        .setCount(5)
        .setIntervalSec(1)
        .setParamItem(new GatewayParamFlowItem()
            .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
            .setFieldName("pn")
        )
    );

    rules.add(new GatewayFlowRule("another_customized_api").setCount(1)
        .setIntervalSec(1));
    GatewayRuleManager.loadRules(rules);
  }
}
