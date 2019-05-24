package cn.learn.springboot.simple;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import java.util.Collections;

/**
 * 黑白名单配置 https://github.com/alibaba/Sentinel/wiki/%E9%BB%91%E7%99%BD%E5%90%8D%E5%8D%95%E6%8E%A7%E5%88%B6
 *
 * @author shaoyijiong
 * @date 2019/5/24
 */
public class AuthorityDemo {

  private static final String RESOURCE_NAME = "testABC";

  public static void main(String[] args) {
    System.out.println("========Testing for black list========");
    initBlackRules();
    testFor(RESOURCE_NAME, "appA");
    testFor(RESOURCE_NAME, "appB");
    testFor(RESOURCE_NAME, "appC");
    testFor(RESOURCE_NAME, "appE");

    System.out.println("========Testing for white list========");
    initWhiteRules();
    testFor(RESOURCE_NAME, "appA");
    testFor(RESOURCE_NAME, "appB");
    testFor(RESOURCE_NAME, "appC");
    testFor(RESOURCE_NAME, "appE");
  }

  private static void testFor(/*@NonNull*/ String resource, /*@NonNull*/ String origin) {
    ContextUtil.enter(resource, origin);
    Entry entry = null;
    try {
      entry = SphU.entry(resource);
      System.out.println(String.format("Passed for resource %s, origin is %s", resource, origin));
    } catch (BlockException ex) {
      System.err.println(String.format("Blocked for resource %s, origin is %s", resource, origin));
    } finally {
      if (entry != null) {
        entry.exit();
      }
      ContextUtil.exit();
    }
  }

  private static void initWhiteRules() {
    AuthorityRule rule = new AuthorityRule();
    rule.setResource(RESOURCE_NAME);
    rule.setStrategy(RuleConstant.AUTHORITY_WHITE);
    rule.setLimitApp("appA,appE");
    AuthorityRuleManager.loadRules(Collections.singletonList(rule));
  }

  private static void initBlackRules() {
    AuthorityRule rule = new AuthorityRule();
    rule.setResource(RESOURCE_NAME);
    rule.setStrategy(RuleConstant.AUTHORITY_BLACK);
    rule.setLimitApp("appA,appB");
    AuthorityRuleManager.loadRules(Collections.singletonList(rule));
  }
}
