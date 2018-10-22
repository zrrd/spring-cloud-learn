package cn.learn.igame.service;

import java.util.List;

/**
 * 测试下feign的远程调用.
 *
 * @author 邵益炯
 * @date 2018/10/22
 */
public interface FeignTest {

  /**
   * 就是用来测试feign的远程调用的.
   */
  List gameList();
}
