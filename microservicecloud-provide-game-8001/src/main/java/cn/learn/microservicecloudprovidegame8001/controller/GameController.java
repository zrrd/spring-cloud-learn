package cn.learn.microservicecloudprovidegame8001.controller;

import cn.learn.igame.application.game.domain.Game;
import cn.learn.igame.base.BaseResponse;
import cn.learn.igame.base.BaseResponse.ResponseBuilder;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * provide层的controller.
 *
 * @author shaoyijiong
 * @date 2018/7/18
 */
@RestController
public class GameController {

  /**
   * 查询所有游戏信息,返回游戏列表页面.
   *
   * @return 游戏列表
   */
  @GetMapping("/games")
  public BaseResponse<List<Game>> list() {
    Game game = new Game();
    List<Game> games = game.selectAll();
    return new ResponseBuilder<>(games).build();
  }

  /**
   * 添加游戏,返回游戏列表页面.
   *
   * @param game 新添加的游戏
   * @return 返回到游戏列表页面
   */
  @PostMapping("/game")
  public BaseResponse add(Game game) {
    game.insert();
    return new ResponseBuilder<>(game).build();

  }

  /**
   * 查出当前员工,在页面回显.
   *
   * @return 修改员工页面
   */
  @GetMapping("/game/{id}")
  public BaseResponse toEditPage(@PathVariable Integer id) {
    Game game = new Game(id);
    game = game.selectById();
    return new ResponseBuilder<>(game).build();

  }

  /**
   * 修改游戏.
   *
   * @param game 游戏参数
   * @return 修改结果
   */
  @PutMapping("/game")
  public BaseResponse updateGame(Game game) {
    game.updateById();
    return new ResponseBuilder<>(game).build();

  }

  /**
   * 删除游戏.
   *
   * @param id 游戏id
   * @return 游戏
   */
  @DeleteMapping("/game/{id}")
  public BaseResponse deleteGame(@PathVariable Integer id) {
    Game game = new Game(id);
    game.deleteById();
    return new ResponseBuilder<>(game).build();

  }
}
