package cn.learn.microservicecloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 测试服务降级.
 *
 * @author 邵益炯
 * @date 2018/10/31
 */
@RestController
public class HystrixTestController {

  private final RestTemplate restTemplate;

  @Autowired
  public HystrixTestController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @GetMapping("hello")
  @HystrixCommand(fallbackMethod = "fallback")
  public String hello() {
    //启动 8001 与 8002  其中8001 端口的服务 存在问题
    return restTemplate.getForObject("http://PROVIDE-GAME/hello", String.class);
  }

  public String fallback() {
    return "fallback";
  }

}
