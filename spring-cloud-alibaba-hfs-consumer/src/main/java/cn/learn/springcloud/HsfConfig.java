package cn.learn.springcloud;

import com.alibaba.boot.hsf.annotation.HSFConsumer;
import org.springframework.context.annotation.Configuration;

/**
 * @author shaoyijiong
 * @date 2021/4/25
 */
@Configuration
public class HsfConfig {

    @HSFConsumer(clientTimeout = 3000, serviceVersion = "1.0.0")
    private HelloService helloService;

}