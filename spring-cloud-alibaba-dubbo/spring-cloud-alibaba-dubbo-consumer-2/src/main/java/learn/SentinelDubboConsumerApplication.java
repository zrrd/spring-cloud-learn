package learn;

import cn.learn.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaoyijiong
 * @date 2021/7/20
 */
@Slf4j
@SpringBootApplication
public class SentinelDubboConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SentinelDubboConsumerApplication.class, args);
  }

  @RestController
  static class TestController {

    @DubboReference(version = "1.0.0", timeout = 20000)
    HelloService helloService;

    @GetMapping("/hello")
    public String test() {
      log.info(Thread.currentThread().getName());
      return helloService.hello("hello");
    }

    @GetMapping("/lazy")
    public String lazy() {
      log.info(Thread.currentThread().getName());
      return helloService.lazy(10);
    }

  }

}
