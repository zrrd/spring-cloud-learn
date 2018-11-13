package cn.learn.microservicecloud.controller;

import cn.learn.igame.base.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 消费者Controller通过rest调用其他微服务.
 *
 * @author 邵益炯
 * @date 2018/8/29
 */
@RestController
public class GameController {

  ///通过ip访问
  //@Value("${micro.url-prefix}")
  //private String prefix;

  /**
   * 通过eureka服务名调用.
   * <p>
   * 为什么能通过这样调用呢  SpringCloudRibbon有一个拦截器
   * 进行实际调用的时候，自动的去选取服务实例，并将实际要请求的IP地址和端口替换这里的服务名，
   * 从而完成服务接口的调用
   * </p>
   */
  //private static final String REST_URL_PREFIX = "http://localhost:8001";
  private static final String REST_URL_PREFIX = "http://provide-game";

  private final RestTemplate restTemplate;

  @Autowired
  public GameController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  /**
   * 查询所有游戏信息,返回游戏列表.
   *
   * @return 游戏列表
   */
  @GetMapping("/games")
  public BaseResponse list() {
    return restTemplate.getForObject(REST_URL_PREFIX + "/games", BaseResponse.class);
  }

  @GetMapping("/game/{id}")
  public BaseResponse get(@PathVariable int id) {
    return restTemplate.getForObject(REST_URL_PREFIX + "/game/{id}", BaseResponse.class, id);
  }


}