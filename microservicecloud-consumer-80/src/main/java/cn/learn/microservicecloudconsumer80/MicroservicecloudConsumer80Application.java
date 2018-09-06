package cn.learn.microservicecloudconsumer80;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MicroservicecloudConsumer80Application {

  public static void main(String[] args) {
    SpringApplication.run(MicroservicecloudConsumer80Application.class, args);
  }
}
