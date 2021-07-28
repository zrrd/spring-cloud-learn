import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

/**
 * @author shaoyijiong
 * @date 2021/7/28
 */
public class RequestTest {

  RestTemplate restTemplate = new RestTemplate();

  /**
   * 请求限流
   */
  @Test
  public void providerLimit() throws InterruptedException {
    int size = 10;
    ExecutorService executor = Executors.newFixedThreadPool(size);
    CountDownLatch countDownLatch = new CountDownLatch(size);
    for (int i = 0; i < size; i++) {
      executor.submit(() -> {
        try {
          countDownLatch.await();
          String result = restTemplate.getForObject("http://127.0.0.1:8091/hello", String.class);
          System.out.println("hello result:" + result);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
      countDownLatch.countDown();
    }
    TimeUnit.SECONDS.sleep(10);
  }

  /**
   * 来源限流
   */
  @Test
  public void originLimit() throws InterruptedException {
    int size = 20;
    ExecutorService executor = Executors.newFixedThreadPool(size);
    CountDownLatch countDownLatch = new CountDownLatch(size);
    for (int i = 0; i < 10; i++) {
      executor.submit(() -> {
        try {
          countDownLatch.await();
          // origin name : dubbo-consumer
          String result = restTemplate.getForObject("http://127.0.0.1:8091/hello", String.class);
          System.out.println("app1 result:" + result);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
      countDownLatch.countDown();
    }
    for (int i = 0; i < 10; i++) {
      executor.submit(() -> {
        try {
          countDownLatch.await();
          // origin name : dubbo-consumer-2
          String result = restTemplate.getForObject("http://127.0.0.1:8092/hello", String.class);
          System.out.println("app2 result:" + result);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
      countDownLatch.countDown();
    }
    TimeUnit.SECONDS.sleep(10);
  }

  /**
   * 服务消费方并发线程数控制
   */
  @Test
  public void consumerThreadTest() throws InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(200);
    CountDownLatch countDownLatch = new CountDownLatch(200);
    // 100 个线程请求低rt接口
    for (int i = 0; i < 100; i++) {
      executor.submit(() -> {
        try {
          countDownLatch.await();
          TimeUnit.SECONDS.sleep(1);
          String result = restTemplate.getForObject("http://127.0.0.1:8091/hello", String.class);
          System.out.println("hello result:" + result);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
      countDownLatch.countDown();
    }
    // 100 个线程请求高rt接口
    // 高rt接口会导致tomcat的请求线程被该接口占用完毕
    for (int i = 0; i < 100; i++) {
      executor.submit(() -> {
        try {
          countDownLatch.await();
          String result = restTemplate.getForObject("http://127.0.0.1:8091/lazy", String.class);
          System.out.println("lazy result:" + result);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
      countDownLatch.countDown();
    }
    TimeUnit.SECONDS.sleep(100);
  }
}