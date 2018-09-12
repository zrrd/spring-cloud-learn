package cn.learn.microservicecloudconsumer80.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate配置类.
 *
 * @author 邵益炯
 * @date 2018/9/6
 */
@Configuration
public class RestTemplateConfig {

  private final RestTemplateBuilder restTemplateBuilder;

  @Autowired
  public RestTemplateConfig(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplateBuilder = restTemplateBuilder;
  }

  /**
   * 通过LoadBalanced来进行负载均衡. 默认是通过轮询的方式
   */
  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return restTemplateBuilder.build();
  }
}
