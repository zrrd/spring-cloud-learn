package cn.learn.igame.config;

import cn.learn.igame.base.BaseException;
import java.net.BindException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理.
 *
 * @author 邵益炯
 * @date 2018/8/14
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

  /**
   * 异常处理器.
   *
   * @param e 自定义的异常全部通过这个处理器处理
   * @return 自定义的异常信息, json格式
   */
  @ResponseBody
  @ExceptionHandler(Exception.class)
  public Map<String, Object> handleException(Exception e) {
    Map<String, Object> map = new HashMap<>(2);
    if (e instanceof BaseException) {
      //自定义的业务逻辑异常
      BaseException baseException = (BaseException) e;
      map.put("code", baseException.getCode());
      map.put("message", baseException.getMessage());
      log.info("业务逻辑异常,{}", e.getMessage());
    } else if (e instanceof BindException) {
      //参数校验异常
      BindException bindException = (BindException) e;
      map.put("code", 2333);
      map.put("message", bindException.getMessage());
      log.info("业务逻辑异常,{}", e.getMessage());
    } else {
      //系统内部异常
      log.error("系统异常", e);
      map.put("code", 500);
      map.put("message", "系统异常");
    }
    return map;

  }
}
