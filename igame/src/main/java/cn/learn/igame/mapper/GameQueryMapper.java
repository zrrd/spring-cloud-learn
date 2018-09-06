package cn.learn.igame.mapper;

import cn.learn.igame.domain.Game;
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