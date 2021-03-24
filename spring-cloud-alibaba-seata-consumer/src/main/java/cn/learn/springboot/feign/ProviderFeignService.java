package cn.learn.springboot.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yu.hb on 2019-10-30
 */
@FeignClient(value = "sca-provider")
public interface ProviderFeignService {
    @GetMapping("/feign/echo1")
    String feignEcho1(@RequestParam("name") String name);

    @GetMapping("/feign/echo2")
    String feignEcho2(@RequestParam("name") String name);
}
