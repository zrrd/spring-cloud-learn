package cn.learn;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author shaoyijiong
 * @date 2021/7/20
 */
@EnableDubbo(scanBasePackages = "cn.learn.dubbo")
@PropertySource(value = "classpath:/dubbo-config.properties")
@SpringBootApplication
public class SentinelDubboProvideApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelDubboProvideApplication.class, args);
    }

}
