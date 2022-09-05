package cn.learn.dubbo;

import cn.learn.HelloService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Random;
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
    String s = "hello " + name + RandomUtils.nextInt(1, 10);
    System.out.println(s);
    return s;
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
