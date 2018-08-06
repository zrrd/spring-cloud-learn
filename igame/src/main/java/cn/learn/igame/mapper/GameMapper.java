package cn.learn.igame.mapper;

import cn.learn.igame.model.Game;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;

/**
 * .
 * @author win
 */
public interface GameMapper extends BaseMapper<Game> {

  /**
   * 所有.
   *
   * @return 游戏
   */
  List<Game> selectAll();

}