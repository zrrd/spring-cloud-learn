package cn.learn.microservicecloud;

import cn.learn.igame.IgameApplication;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * .
 * @author shaoyijiong
 */
//服务注册 将本微服务注册到Eureka server中去

@EnableEurekaClient
//服务发现
@EnableDiscoveryClient
@SpringBootApplication
//开启熔断器
@EnableCircuitBreaker
public class MicroservicecloudProvideGameHystrix8001Application {

  /**
   * SpringBoot 2.0 需要这样配置才能开启服务监控
   */
  @Bean
  public ServletRegistrationBean getServlet() {
    HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
    ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<>(streamServlet);
    registrationBean.setLoadOnStartup(1);
    registrationBean.addUrlMappings("/hystrix.stream");
    registrationBean.setName("HystrixMetricsStreamServlet");
    return registrationBean;
  }

  public static void main(String[] args) {
    Class[] classes = new Class[2];
    classes[0] = MicroservicecloudProvideGameHystrix8001Application.class;
    classes[1] = IgameApplication.class;
    SpringApplication.run(classes, args);
  }
}
