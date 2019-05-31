package cn.learn.springboot.springboot.annotation;

import cn.learn.springboot.springboot.annotation.handler.CustomRequestOriginParser;
import cn.learn.springboot.springboot.annotation.handler.CustomUrlBlockHandler;
import cn.learn.springboot.springboot.annotation.handler.CustomUrlCleaner;
import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowItem;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shaoyijiong
 * @date 2019/5/24
 */
@SuppressWarnings("all")
@Configuration
public class AopConfiguration {

  @Bean
  public SentinelResourceAspect sentinelResourceAspect() {
    return new SentinelResourceAspect();
  }


  @PostConstruct
  private void init() {
    //initFlowRules();
    //initDegradeRule();
    //initFlowRulesURl();
    //initWebCallbackManager();
    initParamFlowRules();
  }


  /**
   * 根据方法进行限流 限流规则
   */
  private void initFlowRules() {
    List<FlowRule> rules = new ArrayList<>();
    FlowRule rule = new FlowRule();
    rule.setResource("test");
    rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
    // Set limit QPS to 20.
    rule.setCount(1);
    rules.add(rule);
    FlowRuleManager.loadRules(rules);
  }

  /**
   * 根据方法进行限流 降级规则
   */
  private void initDegradeRule() {
    List<DegradeRule> rules = new ArrayList<>();
    DegradeRule rule = new DegradeRule();
    rule.setResource("timeout");
    // set threshold RT, 10 ms 响应时间10 ms为阈值
    rule.setCount(50);
    rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
    //多少s进行恢复
    rule.setTimeWindow(10);
    rules.add(rule);
    DegradeRuleManager.loadRules(rules);
  }


  /**
   * 根据路径进行限流
   */
  private void initFlowRulesURl() {
    List<FlowRule> rules = new ArrayList<>();
    FlowRule rule = new FlowRule();
    rule.setResource("/test");
    rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
    // Set limit QPS to 20.
    rule.setCount(1);
    //比如对ip生效
    //rule.setLimitApp("192.168.9.218");
    rules.add(rule);
    FlowRuleManager.loadRules(rules);
  }

  private static void initParamFlowRules() {
    ParamFlowRule rule = new ParamFlowRule("param")
        .setParamIdx(0)
        .setCount(1);
    // 针对 int 类型的参数 PARAM_B，单独设置限流 QPS 阈值为 100，而不是全局的阈值 1.
    ParamFlowItem item = new ParamFlowItem().setObject(String.valueOf(2))
        .setClassType(int.class.getName()).setClassType(int.class.getName())
        .setCount(10);
    rule.setParamFlowItemList(Collections.singletonList(item));

    ParamFlowRuleManager.loadRules(Collections.singletonList(rule));
  }

  private void initWebCallbackManager() {
    WebCallbackManager.setUrlBlockHandler(new CustomUrlBlockHandler());
    //WebCallbackManager.setRequestOriginParser(new CustomRequestOriginParser());
    WebCallbackManager.setUrlCleaner(new CustomUrlCleaner());
  }

  /**
   * <pre>
   * Sentinel starter 默认为所有的 HTTP 服务提供了限流埋点，如果只想对 HTTP 服务进行限流，那么只需要引入依赖，无需修改代码。
   * 不需要下面的这个
   * 对特定的方法进行限流或者降级 @SentinelResource 注解来完成限流的埋点
   * 可以实现接口 将/a/1 映射到 /a/* UrlCleaner
   * </pre>
   */
  //@Bean
  public FilterRegistrationBean sentinelFilterRegistration() {
    //自定义http 接口限流
    FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
    registration.setFilter(new CommonFilter());
    registration.addUrlPatterns("/*");
    registration.setName("sentinelFilter");
    registration.setOrder(1);

    return registration;
  }

}