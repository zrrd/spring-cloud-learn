package cn.learn.microservicecloudprovidegame8001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * .
 * @author shaoyijiong
 */
//服务注册 将本微服务注册到Eureka server中去
@EnableEurekaClient
//服务发现
@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicecloudProvideGame8001Application {

  public static void main(String[] args) {
    SpringApplication.run(MicroservicecloudProvideGame8001Application.class, args);
  }
}
