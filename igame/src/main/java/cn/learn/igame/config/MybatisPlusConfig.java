package cn.learn.igame.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybaitsPlus配置.
 *
 * @author shaoyijiong
 * @since 2018/6/30
 */
@Configuration
@MapperScan(basePackages = {"cn.learn.igame.application.game.domain.repository",
    "cn.learn.igame.query.mapper"})
public class MybatisPlusConfig {

}
