package learn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理.
 *
 * @author shaoyijiong
 * @date 2018/8/14
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 异常处理器.
     *
     * @param e 自定义的异常全部通过这个处理器处理
     * @return 自定义的异常信息, json格式
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        log.error("", e);
        return e.getMessage();
    }
}
