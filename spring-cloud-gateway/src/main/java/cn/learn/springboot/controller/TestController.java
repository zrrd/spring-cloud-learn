package cn.learn.springboot.controller;

import com.alibaba.csp.sentinel.adapter.reactor.SentinelReactorTransformer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 测试
 *
 * @author shaoyijiong
 * @date 2019/5/25
 */
@RestController
public class TestController {

  @GetMapping("hello")
  public Mono<String> hello() {
    return Mono.just("hello world")
        .transform(new SentinelReactorTransformer<>("otherResourceName"));
  }
}
