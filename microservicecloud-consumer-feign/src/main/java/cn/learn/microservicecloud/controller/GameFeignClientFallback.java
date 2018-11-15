package cn.learn.microservicecloud.controller;

import cn.learn.igame.base.BaseResponse;
import cn.learn.igame.base.BaseResponse.ResponseBuilder;
import org.springframework.stereotype.Component;

/**
 * 为Feign接口实现熔断.
 *
 * @author 邵益炯
 * @date 2018/11/15
 */
@Component
public class GameFeignClientFallback implements GameService {

  @Override
  public BaseResponse getGameById(Integer id) {
    return new ResponseBuilder().codeMessage(500, "查询游戏出错").build();
  }
}
