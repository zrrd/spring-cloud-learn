package cn.learn.springboot;

import org.mybatis.spring.annotation.MapperScan;
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
@MapperScan("cn.learn.springboot.mapper")
public class SeataConsumApplication {

  public static void main(String[] args) {
    SpringApplication.run(SeataConsumApplication.class, args);
  }
}
