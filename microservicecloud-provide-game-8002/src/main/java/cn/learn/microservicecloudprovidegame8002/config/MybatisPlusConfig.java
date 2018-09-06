package cn.learn.microservicecloudprovidegame8002.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybaitsPlus配置.
 *
 * @author shaoyijiong
 * @since 2018/6/30
 */
@Configuration
@MapperScan(basePackages = {"cn.learn.microservicecloudprovidegame8001.domain.repository",
    "cn.learn.igame.query.mapper"})
public class MybatisPlusConfig {

}
