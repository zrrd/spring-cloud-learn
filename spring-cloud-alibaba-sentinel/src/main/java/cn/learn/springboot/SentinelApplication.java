package cn.learn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 *
 * @author shaoyijiong
 * @date 2019/5/24
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SentinelApplication {

  public static void main(String[] args) {
    SpringApplication.run(SentinelApplication.class, args);
  }
}
