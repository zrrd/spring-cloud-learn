package cn.learn.microservicecloudprovidegame8001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * .
 * @author shaoyijiong
 */
@EnableEurekaClient
@SpringBootApplication
public class MicroservicecloudProvideGame8001Application {

  public static void main(String[] args) {
    SpringApplication.run(MicroservicecloudProvideGame8001Application.class, args);
  }
}
