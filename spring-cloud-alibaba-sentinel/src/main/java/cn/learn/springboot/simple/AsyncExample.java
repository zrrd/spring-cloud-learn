package cn.learn.springboot.simple;

import com.alibaba.csp.sentinel.AsyncEntry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * 异步调用的使用
 *
 * @author shaoyijiong
 * @date 2019/5/24
 */
@SuppressWarnings("all")
public class AsyncExample {

  public static void main(String[] args) {
    try {
      AsyncEntry entry = SphU.asyncEntry("HelloWorld");

      // 异步调用.
      new Thread(() -> {
        try {
          // 在此处处理异步调用的结果.
        } finally {
          // 在回调结束后 exit.
          entry.exit();
        }
      }).start();
    } catch (BlockException ex) {
      // Request blocked.
      // Handle the exception (e.g. retry or fallback).
    }
  }
}
