package learn;

import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import java.util.concurrent.CompletableFuture;
import org.apache.dubbo.rpc.AppResponse;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.springframework.context.annotation.Configuration;

/**
 * @author shaoyijiong
 * @date 2021/7/29
 */
//@Configuration
public class SentinelConfig {

  // 全局降级策略
  static {
    // Register fallback handler for consumer.
    // If you only want to handle degrading, you need to
    // check the type of BlockException.
    // 当超过限制的时候会返回一个string "您的请求太快了"
    DubboAdapterGlobalConfig.setConsumerFallback(
        (a, b, ex) -> new AsyncRpcResult(CompletableFuture.completedFuture(new AppResponse("您的请求太快了")), b));
  }
}
