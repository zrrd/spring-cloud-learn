package cn.learn.microservicecloudconsumergame8002.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate.
 *
 * @author shaoyijiong
 * @date 2018/7/26
 */
@Configuration
public class RestTemplateConfig {

  private final RestTemplateBuilder restTemplateBuilder;

  @Autowired
  public RestTemplateConfig(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplateBuilder = restTemplateBuilder;
  }

  @Bean
  public RestTemplate restTemplate() {
    return restTemplateBuilder.build();
  }

}
