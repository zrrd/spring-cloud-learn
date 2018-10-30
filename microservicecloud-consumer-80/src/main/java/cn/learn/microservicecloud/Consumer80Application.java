package cn.learn.microservicecloud;

import cn.learn.myribbon.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;


/**
 * .
 * 自定义的负载均衡算法
 * @author syj
 */
@RibbonClient(configuration = MySelfRule.class,name = "syj_ribbon")
@EnableEurekaClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Consumer80Application {

  public static void main(String[] args) {
    SpringApplication.run(Consumer80Application.class, args);
  }
}
