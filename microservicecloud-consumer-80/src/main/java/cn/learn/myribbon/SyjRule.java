package cn.learn.myribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import java.util.List;
import java.util.Random;

/**
 * 自定义算法.
 *
 * @author 邵益炯
 * @date 2018/9/13
 */
public class SyjRule extends AbstractLoadBalancerRule {

  /**
   * 轮询 每5次访问一个服务器. total = 0 当total = 5 我们指针才能往下走 index = 0 当前对外服务器地址
   */

  private int total = 0;
  private int index = 0;

  public Server choose(ILoadBalancer lb, Object key) {
    if (lb == null) {
      return null;
    }
    Server server = null;

    while (server == null) {
      if (Thread.interrupted()) {
        return null;
      }
      List<Server> upList = lb.getReachableServers();
      List<Server> allList = lb.getAllServers();

      int serverCount = allList.size();
      if (serverCount == 0) {
        /*
         * No servers. End regardless of pass, because subsequent passes
         * only get more restrictive.
         */
        return null;
      }

      //自己定义的代码
      if (total < 5) {
        total++;
      } else {
        total = 0;
        index = (index + 1) % upList.size();
      }
      server = upList.get(index);

      if (server == null) {
        /*
         * The only time this should happen is if the server list were
         * somehow trimmed. This is a transient condition. Retry after
         * yielding.
         */
        Thread.yield();
        continue;
      }

      if (server.isAlive()) {
        return (server);
      }

      // Shouldn't actually happen.. but must be transient or a bug.
      server = null;
      Thread.yield();
    }

    return server;

  }

  @Override
  public Server choose(Object key) {
    return choose(getLoadBalancer(), key);
  }

  @Override
  public void initWithNiwsConfig(IClientConfig clientConfig) {

  }
}
