package cn.learn.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author shaoyijiong
 * @date 2019/5/23
 */
@Data
@Component
@ConfigurationProperties(prefix = "test.refresh")
public class TestReFreshProperties {

  private String name;
}
