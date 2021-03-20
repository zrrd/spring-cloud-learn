package cn.learn.springboot.springboot.annotation;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaoyijiong
 * @date 2021/3/20
 */
@RestController
public class TestController {

  @Autowired
  private GoService goService;

  @RequestMapping("test-feign")
  public String testFeign(String hello) {
    return goService.go(hello);
  }

  @SentinelResource(value = "o1")
  @RequestMapping("test-rs")
  public String rs1() {
    return "hello world";
  }
}
