package cn.learn.microservicecloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试配置中心是否成功.
 *
 * @author 邵益炯
 * @date 2018/10/31
 */
@RestController
public class TestController {

  @Value("${spring.application.name}")
  private String appName;


  @Value("${server.port}")
  private String port;

  @RequestMapping("/config")
  public String config() {
    return appName + ";" + port;
  }

}
