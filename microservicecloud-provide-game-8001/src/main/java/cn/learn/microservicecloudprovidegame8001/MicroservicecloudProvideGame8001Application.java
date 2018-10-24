package cn.learn.microservicecloudprovidegame8001;

import cn.learn.igame.IgameApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MicroservicecloudProvideGame8001Application {

  public static void main(String[] args) {
    Class[] classes = new Class[2];
    classes[0] = MicroservicecloudProvideGame8001Application.class;
    classes[1] = IgameApplication.class;
    SpringApplication.run(classes, args);
  }
}