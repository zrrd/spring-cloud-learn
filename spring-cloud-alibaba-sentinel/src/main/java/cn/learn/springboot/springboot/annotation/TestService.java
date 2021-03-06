package cn.learn.springboot.springboot.annotation;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.nacos.common.utils.ExceptionUtil;

/**
 * @author shaoyijiong
 * @date 2021/3/20
 */
@SuppressWarnings("all")
public class TestService {

  // 原函数
  @SentinelResource(value = "hello", blockHandler = "exceptionHandler", fallback = "helloFallback")
  public String hello(long s) {
    return String.format("Hello at %d", s);
  }

  // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
  public String helloFallback(long s) {
    return String.format("Halooooo %d", s);
  }

  // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
  public String exceptionHandler(long s, BlockException ex) {
    // Do some log here.
    ex.printStackTrace();
    return "Oops, error occurred at " + s;
  }

  // 这里单独演示 blockHandlerClass 的配置.
  // 对应的 `handleException` 函数需要位于 `ExceptionUtil` 类中，并且必须为 public static 函数.
  @SentinelResource(value = "test", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
  public void test() {
    System.out.println("Test");
  }
}
