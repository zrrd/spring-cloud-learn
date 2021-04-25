package cn.learn.springcloud.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * jdbc.
 *
 * @author shaoyijiong
 * @date 2018/7/22
 */
@Controller
@Setter
public class HelloController {


  @Autowired
  private DataSource dataSource;
  @Autowired
  private JdbcTemplate jdbcTemplate;


  @ResponseBody
  @GetMapping("/hello")
  public void map() throws SQLException {
    System.out.println("hello");
    System.out.println(dataSource);
    String sql = "SELECT o.* FROM t_order o WHERE o.id=? AND o.order_no=?";
    try (
        Connection conn = dataSource.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
      preparedStatement.setInt(1, 4);
      preparedStatement.setInt(2, 4);
      try (ResultSet rs = preparedStatement.executeQuery()) {
        System.out.println(rs);
        while(rs.next()) {
          System.out.println(rs.getInt(1));
          System.out.println(rs.getInt(2));
        }
      }
    }

  }
}
