package cn.learn.igame.service.impl;

import cn.learn.igame.domain.Game;
import cn.learn.igame.service.FeignTest;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试下feign的远程调用.
 *
 * @author 邵益炯
 * @date 2018/10/22
 */
public class FeignTestImpl implements FeignTest {

  @Autowired
  DataSource dataSource;

  @Override
  public List gameList() {
    Game game = new Game();
    return game.selectAll();
  }
}
