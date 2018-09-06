package cn.learn.microservicecloudprovidegame8003.config;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

/**
 * Actuator自定义的info信息.
 *
 * @author 邵益炯
 * @date 2018/9/3
 */
@Component
public class ExampleInfoContributor implements InfoContributor {

  @Override
  public void contribute(Builder builder) {
    builder.withDetail("项目名", "服务提供").build();
  }
}
