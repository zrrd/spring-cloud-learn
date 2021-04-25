package cn.learn.springcloud;

import com.alibaba.boot.hsf.annotation.HSFProvider;

/**
 * @author shaoyijiong
 * @date 2021/4/25
 */
@HSFProvider(serviceInterface = HelloService.class, serviceVersion = "1.0.0")
public class HelloServiceImpl implements HelloService {

    @Override
    public String echo(String string) {
        return string;
    }
}