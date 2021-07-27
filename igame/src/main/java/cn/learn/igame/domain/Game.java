package cn.learn.igame.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 游戏实体类.
 *
 * @author shaoyijiong
 * @date 2018/6/30
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("game")
public class Game extends Model<Game> {

  private Integer id;

  private String name;

  private Double price;

  private Date publishDate;

  private Double score;

  private String image;

  public Game(Integer id) {
    this.id = id;
  }
}