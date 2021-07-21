package cn.learn.dubbo;

import cn.learn.HelloService;
import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author shaoyijiong
 * @date 2021/7/20
 */
@Service(version = "1.0.0")
public class HelloServiceImpl implements HelloService {
    
    @Override
    public String hello(String name) {
        return "hello " + name;
    }

}
