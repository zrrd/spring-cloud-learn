package cn.learn.springboot.springboot.annotation;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author shaoyijiong
 * @date 2019/5/24
 */
public final class ExceptionUtil {

  /**
   * 拦截 错误处理
   */
  public static void handleException(BlockException ex) {
    // Handler method that handles BlockException when blocked.
    // The method parameter list should match original method, with the last additional
    // parameter with type BlockException. The return type should be same as the original method.
    // The block handler method should be located in the same class with original method by default.
    // If you want to use method in other classes, you can set the blockHandlerClass
    // with corresponding Class (Note the method in other classes must be static).
    System.out.println("Oops: " + ex.getClass().getCanonicalName());
  }
}