package cn.learn.microservicecloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
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
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
    scanBasePackages = "cn.learn.igame")
public class ProvideGame8002Application {

  public static void main(String[] args) {
    SpringApplication.run(ProvideGame8002Application.class, args);
  }
}
