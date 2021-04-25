package cn.learn.springcloud;

import com.alibaba.boot.hsf.annotation.HSFProvider;

/**
 * @author shaoyijiong
 * @date 2021/4/25
 */
@HSFProvider(serviceInterface = HelloService2.class, serviceVersion = "1.0.0")
public class HelloServiceImpl2 implements HelloService2 {

    @Override
    public String echo(String string) {
        return string;
    }
}