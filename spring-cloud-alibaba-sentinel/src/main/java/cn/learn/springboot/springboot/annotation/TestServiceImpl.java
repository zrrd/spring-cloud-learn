package cn.learn.springboot.springboot.annotation;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

/**
 * @author shaoyijiong
 * @date 2019/5/24
 */
@SuppressWarnings("all")
@Service
public class TestServiceImpl implements TestService {

  /**
   * <pre>
   *  @SentinelResource 定义资源  等价于 Entry entry = SphU.entry("HelloWorld")
   *
   *
   * value：资源名称，必需项（不能为空）
   *
   * entryType：entry 类型，可选项（默认为 EntryType.OUT）
   *
   * !!降级的时候调用
   * blockHandler / blockHandlerClass: blockHandler 对应处理 BlockException 的函数名称，可选项。
   * blockHandler 函数访问范围需要是 public，返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，
   * 类型为 BlockException。blockHandler 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 blockHandlerClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
   *
   * !!其他异常的时候调用
   * fallback：fallback 函数名称，可选项，用于在抛出异常的时候提供 fallback 处理逻辑。fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理。fallback 函数签名和位置要求：
   * 返回值类型必须与原函数返回值类型一致；
   * 方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
   * fallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
   *
   * defaultFallback（since 1.6.0）：默认的 fallback 函数名称，可选项，通常用于通用的 fallback 逻辑（即可以用于很多服务或方法）。默认 fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理。若同时配置了 fallback 和 defaultFallback，则只有 fallback 会生效。defaultFallback 函数签名要求：
   * 返回值类型必须与原函数返回值类型一致；
   * 方法参数列表需要为空，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
   * defaultFallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
   *
   * exceptionsToIgnore（since 1.6.0）：用于指定哪些异常被排除掉，不会计入异常统计中，也不会进入 fallback 逻辑中，而是会原样抛出。
   * </pre>
   */
  @Override
  @SentinelResource(value = "test", blockHandler = "handleException", blockHandlerClass = {
      ExceptionUtil.class})
  public void test() {
    System.out.println("Test");
  }

  @Override
  @SentinelResource(value = "hello", fallback = "helloFallback")
  public String hello(long s) {
    if (s < 0) {
      throw new IllegalArgumentException("invalid arg");
    }
    return String.format("Hello at %d", s);
  }

  @Override
  @SentinelResource(value = "helloAnother", defaultFallback = "defaultFallback",
      exceptionsToIgnore = {IllegalStateException.class})
  public String helloAnother(String name) {
    if (name == null || "bad".equals(name)) {
      throw new IllegalArgumentException("oops");
    }
    if ("foo".equals(name)) {
      throw new IllegalStateException("oops");
    }
    return "Hello, " + name;
  }

  /**
   * 抛出异常  已这个返回
   */
  public String helloFallback(long s, Throwable ex) {
    // Do some log here.
    ex.printStackTrace();
    return "Oops, error occurred at " + s;
  }

  /**
   * 针对所有类型的异常
   */
  public String defaultFallback() {
    System.out.println("Go to default fallback");
    return "default_fallback";
  }

  /**
   * 这里 entryType 得是入站流量
   */
  @Override
  @SentinelResource(value = "timeout")
  public String timeoutGo() {
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return String.valueOf(System.currentTimeMillis());
  }
}
