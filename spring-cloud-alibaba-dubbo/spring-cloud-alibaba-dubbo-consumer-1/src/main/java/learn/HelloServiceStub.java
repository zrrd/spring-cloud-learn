package learn;

import cn.learn.HelloService;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * 类似实现了代理的功能
 *
 * @author shaoyijiong
 * @date 2021/7/29
 */
public class HelloServiceStub implements HelloService {

  private final HelloService helloService;

  /**
   * 构造函数传入真正的远程代理对象
   */
  public HelloServiceStub(HelloService helloService) {
    this.helloService = helloService;
  }

  /**
   * 除了降级 还能做其他很多功能 比如rpc结果缓存(等)
   * <br> 下面功能实现了针对限流的降级策略
   */
  @Override
  public String hello(String name) {
    try {
      return helloService.hello(name);
    } catch (Exception e) {
      if (BlockException.isBlockException(e)) {
        return "您真是一个小天才";
      }
      throw e;
    }
  }

  @Override
  public String lazy(Integer second) {
    return helloService.lazy(second);
  }
}
