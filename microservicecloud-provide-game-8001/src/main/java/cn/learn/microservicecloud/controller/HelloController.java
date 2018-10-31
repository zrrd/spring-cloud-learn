package cn.learn.microservicecloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Hello Controller.
 *
 * @author shaoyijiong
 * @date 2018/7/6
 */
@Controller
public class HelloController {

  /**
   * 为了服务端除法服务降级  将时间延迟5s返回.
   */
  @ResponseBody
  @RequestMapping("/hello")
  public String hello() throws InterruptedException {
    System.out.println("8001");
    Thread.sleep(10000L);
    return "hello world";
  }

  @RequestMapping("/success")
  public String success() {
    return "success";
  }
}
