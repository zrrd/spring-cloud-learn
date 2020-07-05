package cn.learn.springboot.springboot.annotation.handler;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 * @author shaoyijiong
 * @date 2019/5/28
 */
public class CustomRequestOriginParser implements RequestOriginParser {

  /**
   * 获取用户的ip
   */
  @Override
  public String parseOrigin(HttpServletRequest request) {
    return request.getRemoteAddr();
  }
}
