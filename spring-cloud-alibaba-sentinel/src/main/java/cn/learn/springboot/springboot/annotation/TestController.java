package cn.learn.springboot.springboot.annotation;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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


  @GetMapping("/param")
  public String paramLimit(int uid) {
    Entry entry = null;
    String retVal;
    try {
      // 只对参数uid进行限流，参数ip不进行限制
      entry = SphU.entry("param", EntryType.IN, 1, uid);
      retVal = "passed";
    } catch (BlockException e) {
      retVal = "blocked";
    } finally {
      if (entry != null) {
        entry.exit();
      }
    }
    return retVal;
  }
}
