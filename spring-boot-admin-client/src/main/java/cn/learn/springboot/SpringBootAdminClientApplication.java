package cn.learn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringBootAdminClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootAdminClientApplication.class, args);
  }
}
