package cn.learn.dubbo;

import cn.learn.HelloService;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.concurrent.TimeUnit;
import org.apache.dubbo.rpc.RpcException;

/**
 * @author shaoyijiong
 * @date 2021/7/20
 */
@DubboService(version = "1.0.0")
public class HelloServiceImpl implements HelloService {

  @Override
  public String hello(String name) {
    return "hello " + name;
  }

  @Override
  public String lazy(Integer second) {

    try {
      TimeUnit.SECONDS.sleep(second);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "end";
  }
}
