package cn.learn.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaoyijiong
 * @date 2019/5/23
 */
@RestController
public class TestController {

  @GetMapping("go")
  public String go() {
    return "go";
  }
}
