package cn.learn.springboot.controller;

import cn.learn.springboot.domain.TbUser;
import cn.learn.springboot.feign.ProviderFeignService;
import cn.learn.springboot.mapper.TbUserMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yu.hb on 2019-10-30
 */
@RestController
public class ConsumerController {

  private final TbUserMapper tbUserMapper;
  private final ProviderFeignService feignService;

  public ConsumerController(TbUserMapper tbUserMapper, ProviderFeignService feignService) {
    this.tbUserMapper = tbUserMapper;
    this.feignService = feignService;
  }


  @Transactional
  //@GlobalTransactional
  @GetMapping("/feign/echo")
  public String feignEcho(String name) {

    feignService.feignEcho1(name);

    TbUser tbUser = new TbUser();
    tbUser.setAge(10);
    tbUser.setName("cousumer");
    tbUserMapper.insert(tbUser);

    feignService.feignEcho2(name);
    return "feignEcho() hi " + name;
  }
}
