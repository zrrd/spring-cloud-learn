package cn.learn.consumeruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 开启发现服务功能.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerUserApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConsumerUserApplication.class, args);
  }

  /**
   * 开启负载均衡.
   */
  @LoadBalanced
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
