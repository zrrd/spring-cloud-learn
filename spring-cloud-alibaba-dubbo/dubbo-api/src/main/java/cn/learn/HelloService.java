package cn.learn;

/**
 * @author shaoyijiong
 * @date 2021/7/20
 */
public interface HelloService {

  String hello(String name);

  /**
   * 等待多少秒
   *
   * @param second 秒
   * @return any string
   */
  String lazy(Integer second);
}
