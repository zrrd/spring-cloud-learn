package cn.learn.consumeruser.controller;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * .
 *
 * @author shaoyijiong
 * @date 2018/8/4
 */
@RestController
public class UserController {

  /**
   * 票务服务的注册中心的名字
   */
  private static final String TICKET_URL = "http://PROVIDE-TICKET/ticket";

  @Resource
  private RestTemplate restTemplate;

  /**
   * .
   */
  @GetMapping("/buy")
  public String buy() {
    String name = restTemplate.getForObject(TICKET_URL,String.class);
    System.out.println("购买了" + name);
    return "购买了" + name;
  }
}
