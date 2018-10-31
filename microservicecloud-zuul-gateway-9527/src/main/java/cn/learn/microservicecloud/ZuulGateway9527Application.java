package cn.learn.microservicecloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringCloudApplication
@EnableZuulProxy
public class ZuulGateway9527Application {

  public static void main(String[] args) {
    SpringApplication.run(ZuulGateway9527Application.class, args);
  }

  @Bean
  public AccessFilter accessFilter() {
    return new AccessFilter();
  }
}
