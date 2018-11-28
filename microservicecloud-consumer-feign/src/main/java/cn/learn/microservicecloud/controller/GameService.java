package cn.learn.microservicecloud.controller;

import cn.learn.igame.base.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>game接口调用. 通过FeignClient来判断掉用哪个服务的接口 声明式调用</p>
 * <p>>value 表示调用的服务   fallback表示异常产生后的熔断处理  path用来表示父路径</p>
 *
 * @author 邵益炯
 * @date 2018/10/31
 */
@FeignClient(value = "provide-game", fallback = GameFeignClientFallback.class)
public interface GameService {

  /**
   * 远程调用的代理接口. 相当于对restTemplate进行了封装
   * <p>
   * 注意  这里的PathVariable 必须加上value参数 否则会报错
   * </p>
   *
   * @param id 游戏id
   * @return 返回
   */
  @GetMapping("/game/{id}")
  BaseResponse getGameById(@PathVariable(value = "id") Integer id);

}
