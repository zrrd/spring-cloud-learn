package cn.learn.microservicecloudprovidegame8001;

import cn.learn.igame.domain.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author 邵益炯
 * @date 2018/9/12
 */
@RunWith(JUnit4.class)
public class aa {

  @Test
  public void test1() throws ClassNotFoundException {
    Class<Game> gameClass = (Class<Game>) Class.forName("cn.learn.igame.domain.Game");

    System.out.println( gameClass.getDeclaredMethods()[0]);

  }

}
