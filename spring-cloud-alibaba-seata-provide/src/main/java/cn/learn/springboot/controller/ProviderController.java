package cn.learn.springboot.controller;

import cn.learn.springboot.domain.TbUser;
import cn.learn.springboot.mapper.TbUserMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yu.hb on 2019-10-30
 */
@RestController
public class ProviderController {

  private final TbUserMapper tbUserMapper;

  public ProviderController(TbUserMapper tbUserMapper) {
    this.tbUserMapper = tbUserMapper;
  }


  //@GlobalTransactional
  @GetMapping("/feign/echo1")
  public String feignEcho1(String name) {
    TbUser tbUser = new TbUser();
    tbUser.setAge(10);
    tbUser.setName("provider1");
    tbUserMapper.insert(tbUser);
    return "feignEcho() hi " + name;
  }

  //@GlobalTransactional
  @GetMapping("/feign/echo2")
  public String feignEcho2(String name) {
    if ("cc".equals(name)) {
      throw new RuntimeException();
    }
    TbUser tbUser = new TbUser();
    tbUser.setAge(10);
    tbUser.setName("provider2");
    tbUserMapper.insert(tbUser);
    return "feignEcho() hi " + name;
  }
}
