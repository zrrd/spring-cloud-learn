package cn.learn.springboot.config;

import lombok.Data;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *  通过注入 在TestReFreshBean 注解RefreshScope   TestReFreshProperties 不注解RefreshScope
 *  TestReFreshProperties  会刷新配置 TestReFreshBean 也会刷新配置
 * </pre>
 *
 * @author shaoyijiong
 * @date 2019/5/23
 */
@RefreshScope
@Data
@Component
public class TestReFreshBean {

  private String name;

  public TestReFreshBean(TestReFreshProperties testReFreshProperties) {
    this.name = testReFreshProperties.getName();

  }
}
