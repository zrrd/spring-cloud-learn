package cn.learn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 启动类
 *
 * @author shaoyijiong
 * @date 2019/5/23
 */
@EnableConfigurationProperties
@EnableDiscoveryClient
@SpringBootApplication
public class NacosConsumerCloudApplication {


  /**
   * 添加LoadBalanced 注解与Ribbon  集成
   */
  @LoadBalanced
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }


  public static void main(String[] args) {
    SpringApplication.run(NacosConsumerCloudApplication.class, args);
  }


  @RestController
  public class TestController {

    private final RestTemplate restTemplate;

    public TestController(RestTemplate restTemplate) {
      this.restTemplate = restTemplate;
    }

    @GetMapping("discovery")
    public String discovery() {
      //service-provider 服务名
      return restTemplate.getForObject("http://service-provider/go", String.class);
    }
  }
}
