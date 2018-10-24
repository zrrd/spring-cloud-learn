package cn.learn.microservicecloudconsumer80;

import cn.learn.myribbon.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
/**
 * 自定义的负载均衡算法
 */
@RibbonClient(configuration = MySelfRule.class,name = "syj_ribbon")
public class MicroservicecloudConsumer80Application {

  public static void main(String[] args) {
    SpringApplication.run(MicroservicecloudConsumer80Application.class, args);
  }
}
