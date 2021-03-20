package cn.learn.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaoyijiong
 * @date 2019/5/23
 */
@Slf4j
@RestController
public class TestController {

  @GetMapping("go")
  public String go(String hello) throws InterruptedException {
    if (hello.equals("go")) {
      throw new IllegalArgumentException();
    }
    if (hello.equals("slow")) {
      Thread.sleep(1000000);
    }
    log.info("请求{}", hello);
    return "go" + hello;
  }

  @GetMapping("go1")
  public String go1(String hello) throws InterruptedException {
    if (hello.equals("go")) {
      throw new IllegalArgumentException();
    }
    if (hello.equals("slow")) {
      Thread.sleep(1000000);
    }
    log.info("请求{}", hello);
    return "go" + hello;
  }
}
