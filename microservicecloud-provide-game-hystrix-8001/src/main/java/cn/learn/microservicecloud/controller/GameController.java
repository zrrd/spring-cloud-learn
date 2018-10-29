package cn.learn.microservicecloud.controller;

import cn.learn.igame.base.BaseException;
import cn.learn.igame.base.BaseResponse;
import cn.learn.igame.base.BaseResponse.ResponseBuilder;
import cn.learn.igame.domain.Game;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
public class GameController {


  /**
   * 查询所有游戏信息,返回游戏列表页面.
   *
   * @return 游戏列表
   */
  @GetMapping("/games")
  public BaseResponse<List<Game>> list() {
    log.info("8001被访问了");
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
   * 查出当前员工,在页面回显. 异常出现后
   *
   * <p>使用 hystrixGet 方法返回调用   aop类似异常通知  排除自定义异常
   * 但是每写一个方法都要完成对应的fallback方法代码量膨胀
   *
   * @return 修改员工页面
   */
  @HystrixCommand(fallbackMethod = "hystrixGet", ignoreExceptions = BaseException.class)
  @GetMapping("/game/{id}")
  public BaseResponse toEditPage(@PathVariable Integer id) {
    Game game = new Game(id);
    game = game.selectById();
    if (game == null) {
      //故意手动抛出异常
      throw new RuntimeException("没有对应的id信息");
    }

    return new ResponseBuilder<>(game).build();
  }

  @SuppressWarnings("unused")
  public BaseResponse hystrixGet(@PathVariable Integer id) {
    return new ResponseBuilder().codeMessage(500, "该id" + id + "不存在").build();
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
