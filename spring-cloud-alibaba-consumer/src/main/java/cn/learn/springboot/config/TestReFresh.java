package cn.learn.springboot.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 直接获取配置的方式
 *
 * @author shaoyijiong
 * @date 2019/5/23
 */
@RestController
public class TestReFresh {

  private final TestReFreshProperties testReFreshProperties;
  private final TestReFreshBean testReFreshBean;

  public TestReFresh(TestReFreshProperties testReFreshProperties,
      TestReFreshBean testReFreshBean) {
    this.testReFreshProperties = testReFreshProperties;
    this.testReFreshBean = testReFreshBean;
  }

  @GetMapping("test/refresh")
  public String refresh() {
    return testReFreshProperties.getName();
  }

  @GetMapping("test/refresh/bean")
  public String refreshBean() {
    return testReFreshBean.getName();
  }
}
