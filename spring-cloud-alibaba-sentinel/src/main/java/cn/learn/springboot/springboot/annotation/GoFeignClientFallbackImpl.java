package cn.learn.springboot.springboot.annotation;

import org.springframework.stereotype.Component;

/**
 * 为Feign接口实现熔断.
 *
 * @author 邵益炯
 * @date 2018/11/15
 */
@Component
public class GoFeignClientFallbackImpl implements GoService {

  @Override
  public String go(String id) {
    return "查询出错";
  }
}
