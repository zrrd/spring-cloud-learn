package cn.learn.springboot.springboot.annotation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaoyijiong
 * @date 2019/5/24
 */
@RestController
public class TestController {

  private final TestService service;

  public TestController(TestService service) {
    this.service = service;
  }

  @GetMapping("/foo")
  public String apiFoo(@RequestParam(required = false) Long t) throws Exception {
    if (t == null) {
      t = System.currentTimeMillis();
    }
    service.test();
    return service.hello(t);
  }

  @GetMapping("/baz/{name}")
  public String apiBaz(@PathVariable("name") String name) {
    return service.helloAnother(name);
  }


  @GetMapping("timeout")
  public String timeout() {
    return service.timeoutGo();
  }

  @GetMapping("/a")
  public String a() {
    return "a";
  }
}
