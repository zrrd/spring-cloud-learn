package cn.learn.igame.query.mapper;

import cn.learn.igame.application.game.domain.Game;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 * .
 * @author win
 */
public interface GameQueryMapper extends BaseMapper<Game> {

  /**
   * 所有.
   *
   * @return 游戏
   */
  List<Game> selectAll();

}