package cn.learn.springboot.simple;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowItem;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import java.util.Collections;

/**
 * @author shaoyijiong
 * @date 2019/5/24
 */
public class ParamFlowQpsDemo {

  private static final int PARAM_A = 1;
  private static final int PARAM_B = 2;
  private static final int PARAM_C = 3;
  private static final int PARAM_D = 4;

  /**
   * Here we prepare different parameters to validate flow control by parameters.
   */
  private static final Integer[] PARAMS = new Integer[] {PARAM_A, PARAM_B, PARAM_C, PARAM_D};

  private static final String RESOURCE_KEY = "resA";

  public static void main(String[] args) throws Exception {
    initParamFlowRules();

    final int threadCount = 20;
    ParamFlowQpsRunner<Integer> runner = new ParamFlowQpsRunner<>(PARAMS, RESOURCE_KEY, threadCount, 120);
    runner.tick();

    Thread.sleep(1000);
    runner.simulateTraffic();
  }

  /**
   * 属性                   说明              默认值
   * resource               资源名，必填
   * count                  限流阈值，必填
   * grade                  限流模式          QPS 模式
   * paramIdx               热点参数的索引，必填，对应 SphU.entry(xxx, args) 中的参数索引位置
   * paramFlowItemList      参数例外项，可以针对指定的参数值单独设置限流阈值，不受前面 count 阈值的限制。仅支持基本类型
   */
  private static void initParamFlowRules() {
    // QPS mode, threshold is 5 for every frequent "hot spot" parameter in index 0 (the first arg).
    ParamFlowRule rule = new ParamFlowRule(RESOURCE_KEY)
        .setParamIdx(0)
        .setGrade(RuleConstant.FLOW_GRADE_QPS)
        //.setDurationInSec(3)
        //.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER)
        //.setMaxQueueingTimeMs(600)
        .setCount(5);

    // We can set threshold count for specific parameter value individually.
    // Here we add an exception item. That means: QPS threshold of entries with parameter `PARAM_B` (type: int)
    // in index 0 will be 10, rather than the global threshold (5).
    ParamFlowItem item = new ParamFlowItem().setObject(String.valueOf(PARAM_B))
        .setClassType(int.class.getName())
        .setCount(10);
    rule.setParamFlowItemList(Collections.singletonList(item));
    ParamFlowRuleManager.loadRules(Collections.singletonList(rule));
  }
}
