package learn;

import cn.learn.HelloService;

/**
 * 本地伪装
 *
 * @author shaoyijiong
 * @date 2021/7/29
 */
public class HelloServiceMock implements HelloService {

  @Override
  public String hello(String name) {
    return "假装返回了";
  }

  @Override
  public String lazy(Integer second) {
    return "假装返回了";
  }
}
