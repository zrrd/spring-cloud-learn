package cn.learn.springboot.springboot.annotation.handler;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;

/**
 * 路径清理 /user/1 -> /user/*
 *
 * @author shaoyijiong
 * @date 2019/5/31
 */
public class CustomUrlCleaner implements UrlCleaner {

  @Override
  public String clean(String originUrl) {
    return originUrl;
  }
}
