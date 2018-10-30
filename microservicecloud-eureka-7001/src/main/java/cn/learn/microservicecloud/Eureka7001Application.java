package cn.learn.microservicecloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * EurekaServer 接收其他微服务的注册.
 * @author syj
 */
@EnableEurekaServer
@SpringBootApplication
public class Eureka7001Application {

  public static void main(String[] args) {
    SpringApplication.run(Eureka7001Application.class, args);
  }
}
