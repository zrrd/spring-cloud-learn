package cn.learn.springboot.springboot.annotation;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @author caoquanlong
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

  /**
   * 全局异常处理
   */
  @ExceptionHandler
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public String exceptionHandler(Throwable throwable) {
    log.info(throwable.getClass().getName());
    if (BlockException.isBlockException(throwable)) {
      return "服务器忙";
    }
    return "网络异常";
  }
}

