package cn.learn.springboot.springboot.annotation.handler;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * url限流处理
 *
 * @author shaoyijiong
 * @date 2019/5/28
 */
@Slf4j
public class CustomUrlBlockHandler implements UrlBlockHandler {

  @Override
  public void blocked(HttpServletRequest request,
      HttpServletResponse response, BlockException e) throws IOException {
    Context context = ContextUtil.getContext();
    log.error("限流处理,上下文[{}]", context, e);
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    PrintWriter writer = response.getWriter();
    writer.write(
        JSON.toJSONString(ImmutableMap.of("code", 500, "message", "服务器忙")));
    writer.flush();
  }
}
