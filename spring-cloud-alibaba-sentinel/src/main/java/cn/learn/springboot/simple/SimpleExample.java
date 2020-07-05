package cn.learn.springboot.simple;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 最简单的例子 没有任何框架支持
 *
 * @author shaoyijiong
 * @date 2019/5/24
 */
@SuppressWarnings("all")
public class SimpleExample {

  public static void main(String[] args) {
    initFlowRules();
    //定义资源
    while (true) {
      //entry 实现了 AutoCloseable 能自动关闭资源
      try (Entry entry = SphU.entry("HelloWorld")) {
        /*您的业务逻辑 - 开始*/
        System.out.println("hello world");
        /*您的业务逻辑 - 结束*/
      } catch (BlockException e1) {
        /*流控逻辑处理 - 开始*/
        System.out.println("block!");
        /*流控逻辑处理 - 结束*/
      }
    }
  }

  /**
   * <pre>
   * 流量控制规则  用于控制流量
   * 定义规则 定义了资源 HelloWorld 每秒最多只能通过 20 个请求。
   * Field            说明                                                                 默认值
   * resource         资源名，资源名是限流规则的作用对象
   * count            限流阈值
   * grade            限流阈值类型，QPS 或线程数模式                                         QPS 模式
   * limitApp         流控针对的调用来源 (例如同一个service 被两个controller调用)              default，代表不区分调用来源
   * strategy         判断的根据是资源自身，还是根据其它关联资源 (refResource)，还是根据链路入口  根据资源本身
   * controlBehavior 流控效果（直接拒绝 / 排队等待 / 慢启动模式）                              直接拒绝
   * https://github.com/alibaba/Sentinel/wiki/%E6%B5%81%E9%87%8F%E6%8E%A7%E5%88%B6 具体控制
   * </pre>
   */
  private static void initFlowRules() {
    List<FlowRule> rules = new ArrayList<>();
    FlowRule rule = new FlowRule();
    rule.setResource("HelloWorld");
    rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
    // Set limit QPS to 20.
    rule.setCount(20);
    rules.add(rule);
    FlowRuleManager.loadRules(rules);
  }


  /**
   * Field      说明                                      默认值
   * resource   资源名，即限流规则的作用对象
   * count      阈值
   * grade      降级模式，根据 RT 降级还是根据异常比例降级     RT 根据平均响应时间降级
   * timeWindow 降级的时间，单位为 s
   * 熔断降级的具体规则https://github.com/alibaba/Sentinel/wiki/%E7%86%94%E6%96%AD%E9%99%8D%E7%BA%A7
   */
  private static void initDegradeRule() {
    List<DegradeRule> rules = new ArrayList<>();
    DegradeRule rule = new DegradeRule();
    rule.setResource("aa");
    // set threshold RT, 10 ms 响应时间10 ms为阈值
    rule.setCount(10);
    rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
    //多少s进行恢复
    rule.setTimeWindow(10);
    rules.add(rule);
    DegradeRuleManager.loadRules(rules);
  }


  /**
   * <pre>
   * Field              说明                    默认值
   * highestSystemLoad  最大的 load1，参考值     -1 (不生效)
   * avgRt              所有入口流量的平均响应时间 -1 (不生效)
   * maxThread          入口流量的最大并发数      -1 (不生效)
   * qps                所有入口资源的 QPS       -1 (不生效)
   * </pre>
   */
  private void initSystemRule() {
    List<SystemRule> rules = new ArrayList<>();
    SystemRule rule = new SystemRule();
    rule.setHighestSystemLoad(10);
    rules.add(rule);
    SystemRuleManager.loadRules(rules);
  }


  /**
   * <pre>
   * 黑白名单规则（AuthorityRule）非常简单，主要有以下配置项：
   *
   * resource：资源名，即限流规则的作用对象
   * limitApp：对应的黑名单/白名单，不同 origin 用 , 分隔，如 appA,appB
   * strategy：限制模式，AUTHORITY_WHITE 为白名单模式，AUTHORITY_BLACK 为黑名单模式，默认为白名单模式
   * </pre>
   */
  private void initAuthorityRule() {
    AuthorityRule rule = new AuthorityRule();
    rule.setResource("test");
    rule.setStrategy(RuleConstant.AUTHORITY_WHITE);
    rule.setLimitApp("appA,appB");
    AuthorityRuleManager.loadRules(Collections.singletonList(rule));
  }
}
