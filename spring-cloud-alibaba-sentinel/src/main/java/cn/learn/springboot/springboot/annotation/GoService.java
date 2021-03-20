package cn.learn.springboot.springboot.annotation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>game接口调用. 通过FeignClient来判断掉用哪个服务的接口 声明式调用</p>
 * <p>>value 表示调用的服务   fallback表示异常产生后的熔断处理  path用来表示父路径</p>
 *
 * @author 邵益炯
 * @date 2018/10/31
 */
@FeignClient(value = "service-provider", fallback = GoFeignClientFallbackImpl.class)
public interface GoService {

  /**
   * @param hello 游戏id
   * @return 返回
   */
  @GetMapping("/go")
  String go(@RequestParam(value = "hello") String hello);


}
