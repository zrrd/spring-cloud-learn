package cn.learn.myribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Ribbon算法 , 不能放在启动类同一个目录下.
 *
 * @author 邵益炯
 * @date 2018/9/13
 */
@Configuration
public class MySelfRule {
  @Bean
  public IRule myRule() {
    return new SyjRule();
  }
}
