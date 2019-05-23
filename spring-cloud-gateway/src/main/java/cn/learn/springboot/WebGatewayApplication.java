package cn.learn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * 网关启动类
 *
 * @author shaoyijiong
 * @date 2019/5/23
 */
@EnableDiscoveryClient
@SpringBootApplication
public class WebGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebGatewayApplication.class, args);
  }

  /**
   * <pre>
   *  通过代码实现强制转发
   * http://localhost:8080/about 时会自动转发到地址：http://www.ityouknow.com/about
   * </pre>
   */
  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("path_route", r -> r.path("/about")
            .uri("http://ityouknow.com")).build();
  }

}
