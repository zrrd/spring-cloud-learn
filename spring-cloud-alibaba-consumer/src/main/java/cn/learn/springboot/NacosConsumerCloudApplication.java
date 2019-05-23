package cn.learn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 *
 * @author shaoyijiong
 * @date 2019/5/23
 */
@EnableConfigurationProperties
@EnableDiscoveryClient
@SpringBootApplication
public class NacosConsumerCloudApplication {

  public static void main(String[] args) {
    SpringApplication.run(NacosConsumerCloudApplication.class, args);
  }
}
