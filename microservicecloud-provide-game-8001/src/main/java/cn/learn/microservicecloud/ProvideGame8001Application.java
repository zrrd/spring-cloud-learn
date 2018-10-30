package cn.learn.microservicecloud;

import cn.learn.igame.IgameApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 服务注册 将本微服务注册到Eureka server中去. 服务发现
 *
 * @author shaoyijiong
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ProvideGame8001Application {

  /**
   * 将igame也放到容器中去.
   */
  public static void main(String[] args) {
    Class[] classes = new Class[2];
    classes[0] = ProvideGame8001Application.class;
    classes[1] = IgameApplication.class;
    SpringApplication.run(classes, args);
  }
}
