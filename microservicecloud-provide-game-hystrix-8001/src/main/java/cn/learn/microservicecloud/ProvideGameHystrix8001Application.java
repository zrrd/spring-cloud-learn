package cn.learn.microservicecloud;

import cn.learn.igame.IgameApplication;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;

/**
 * <p>SpringCloudApplication 注解相当于 SpringBootApplication</p>.
 * <p>EnableDiscoveryClient EnableCircuitBreaker 三个注解</p>
 *
 * @author shaoyijiong
 */
@SpringCloudApplication
public class ProvideGameHystrix8001Application {

  /**
   * SpringBoot 2.0 需要这样配置才能开启服务监控   并且该项目需要EnableCircuitBreaker
   */
  @Bean
  public ServletRegistrationBean getServlet() {
    HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
    ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<>(
        streamServlet);
    registrationBean.setLoadOnStartup(1);
    registrationBean.addUrlMappings("/hystrix.stream");
    registrationBean.setName("HystrixMetricsStreamServlet");
    return registrationBean;
  }

  public static void main(String[] args) {
    Class[] classes = new Class[2];
    classes[0] = ProvideGameHystrix8001Application.class;
    classes[1] = IgameApplication.class;
    SpringApplication.run(classes, args);
  }
}
