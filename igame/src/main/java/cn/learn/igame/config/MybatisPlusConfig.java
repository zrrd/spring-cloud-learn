package cn.learn.igame.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * mybaitsPlus配置.
 *
 * @author shaoyijiong
 * @since 2018/6/30
 */
@Configuration
@MapperScan(basePackages = {"cn.learn.igame.domain.repository", "cn.learn.igame.mapper"})
public class MybatisPlusConfig {

  /**
   * 数据库连接池配置.
   */
  @Bean
  public DataSource dataSources() {
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setJdbcUrl(
        "jdbc:mysql://47.99.73.15:3306/games?useUnicode=true&characterEncoding=utf-8&useSSL=false");
    dataSource.setUsername("root");
    dataSource.setPassword("123456");
    return dataSource;
  }

  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
    sqlSessionFactory.setDataSource(dataSource);
    //xml目录
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:/mapper/*/*Mapper.xml"));
    MybatisConfiguration configuration = new MybatisConfiguration();
    configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
    configuration.setJdbcTypeForNull(JdbcType.NULL);
    //驼峰下划线映射
    configuration.setMapUnderscoreToCamelCase(true);
    sqlSessionFactory.setConfiguration(configuration);
    return sqlSessionFactory.getObject();
  }


}
