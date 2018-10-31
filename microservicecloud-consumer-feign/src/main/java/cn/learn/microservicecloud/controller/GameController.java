package cn.learn.microservicecloud.controller;

import cn.learn.igame.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消费者Controller通过Feign调用其他微服务.
 *
 * @author 邵益炯
 * @date 2018/8/29
 */
@RestController
public class GameController {

  private final GameService gameService;

  @Autowired
  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping("/game/{id}")
  public BaseResponse get(@PathVariable Integer id) {
    return gameService.getGameById(id);
  }
}