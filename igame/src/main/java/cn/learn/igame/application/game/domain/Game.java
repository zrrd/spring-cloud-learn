package cn.learn.igame.application.game.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * 游戏实体类.
 *
 * @author shaoyijiong
 * @date 2018/6/30
 */
@Getter
@Setter
public class Game extends Model<Game> {

  private Integer id;

  private String name;

  private Double price;

  private Date publishDate;

  private Double score;

  private String image;

  @Override
  protected Serializable pkVal() {
    return id;
  }

  public Game() {
  }

  public Game(Integer id) {
    this.id = id;
  }
}