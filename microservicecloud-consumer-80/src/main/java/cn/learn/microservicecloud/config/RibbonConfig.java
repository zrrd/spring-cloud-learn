package cn.learn.microservicecloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Ribbon配置类.
 *
 * @author 邵益炯
 * @date 2018/9/13
 */
@Configuration
public class RibbonConfig {

  /**
   * 修改默认负载均衡策略.
   */
  @Bean
  public IRule myRule() {
    //随机选择算法 代替默认的轮询
    // RoundRobinRule 轮询算法
    // AvailabilityFilteringRule 过滤掉故障和超过并发数量的 剩下的轮询
    // WeightedResponseTimeRule 根据响应时间计算权重
    // ResponseTimeWeightedRule 使用平均/百分位响应时间为每个服务器分配动态“权重”的规则，然后以“加权循环”方式使用。
    // RetryRule 先按照RoundRobinRule 获取服务 失败了再重试几次
    // ZoneAvoidanceRule 复合判断server所在区域的性能和server的可用性选择server
    // BestAvailableRule 并发最小的请求
    // 随机 RandomRule
    return new RoundRobinRule();
  }

}
