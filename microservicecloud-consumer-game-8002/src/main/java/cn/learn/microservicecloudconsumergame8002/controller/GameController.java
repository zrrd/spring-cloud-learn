package cn.learn.microservicecloudconsumergame8002.controller;

import cn.learn.igame.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 消费者Controller通过rest调用其他微服务.
 *
 * @author 邵益炯
 * @date 2018/8/29
 */
@RestController
public class GameController {

  private final RestTemplate restTemplate;

  @Autowired
  public GameController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  /**
   * 查询所有游戏信息,返回游戏列表.
   *
   * @return 游戏列表
   */
  @GetMapping("/games")
  public BaseResponse list() {
    return restTemplate.getForObject("http://localhost:8081/games", BaseResponse.class);
  }
}
