package cn.learn.igame;

import cn.learn.igame.base.BaseResponse;
import cn.learn.igame.base.BaseResponse.ResponseBuilder;
import cn.learn.igame.domain.Game;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 * @author 邵益炯
 * @date 2018/9/12
 */
@RestController
public class TestController {

  @GetMapping("/games")
  public BaseResponse<List<Game>> list() {
    Game game = new Game();
    List<Game> games = game.selectAll();
    return new ResponseBuilder<>(games).build();
  }
}
