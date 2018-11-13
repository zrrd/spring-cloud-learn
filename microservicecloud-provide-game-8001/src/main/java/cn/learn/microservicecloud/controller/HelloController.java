package cn.learn.microservicecloud.controller;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ThreadUtils;
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
@Slf4j
public class HelloController {

  /**
   * 为了服务端除法服务降级  将时间延迟5s返回.
   */
  @ResponseBody
  @RequestMapping("/hello")
  public String hello() throws InterruptedException {
    System.out.println("8001");
    long a = System.currentTimeMillis();
    TimeUnit.SECONDS.sleep(new Random().nextInt(7));
    long b = System.currentTimeMillis();
    log.info("等待时间" + (a - b));
    return "hello world";
  }

  @RequestMapping("/success")
  public String success() {
    return "success";
  }
}
