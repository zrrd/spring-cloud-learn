package learn;

import cn.learn.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaoyijiong
 * @date 2021/7/20
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelDubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelDubboConsumerApplication.class, args);
    }

    @RestController
    static class TestController {

        @DubboReference
        HelloService helloService;

        @GetMapping("/test")
        public String test() {
            return helloService.hello("didispace.com");
        }
    }

}
