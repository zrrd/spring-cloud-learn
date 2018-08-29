package cn.learn.microservicecloudprovidegame8001.domain;

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
@Accessors(chain = true)
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

  public Game(Integer id) {
    this.id = id;
  }
}