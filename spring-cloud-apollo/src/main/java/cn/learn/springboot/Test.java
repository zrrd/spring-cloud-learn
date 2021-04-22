package cn.learn.springboot;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author shaoyijiong
 * @date 2021/4/22
 */
@Component
public class Test {

  @Value("${my.name}")
  private String name;

  @Value("${hello:ok}")
  private String hello;

  @Value("${}")

  @PostConstruct
  public void hello() {
    System.out.println(hello);
    System.out.println(name);
  }

}
