package cn.learn.springboot.springboot.annotation;

/**
 * @author shaoyijiong
 * @date 2019/5/24
 */
public interface TestService {

  void test();

  String hello(long s);

  String helloAnother(String name);

  String timeoutGo();
}
