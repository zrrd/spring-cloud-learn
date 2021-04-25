package cn.learn.springcloud.controller;

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
    String sql = "select * from order where id = 3 and order_no = 3";
    String sql1 = "SELECT * FROM order";
    jdbcTemplate.getDataSource();
    dataSource.getConnection().createStatement().execute(sql1);

  }
}
