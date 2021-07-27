package cn.learn.igame.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;


/**
 * steam实体类.
 *
 * @author shaoyijiong
 * @date 2018-6-30
 */
@Getter
@Setter
@TableName("steam")
public class Steam extends Model<Steam> {

  private Integer id;

  private Integer gameId;

  private Integer userId;

  private Date createTime;
}