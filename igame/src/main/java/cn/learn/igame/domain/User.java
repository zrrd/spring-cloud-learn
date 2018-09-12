package cn.learn.igame.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户实体类.
 *
 * @author shaoyijiong
 * @date 2018-6-30
 */
@Getter
@Setter
@TableName("user")
public class User extends Model<User> {

  private Integer id;

  private String name;

  private Integer gameNum;

  private Double money;

  private String prefer;

  @Override
  protected Serializable pkVal() {
    return id;
  }
}