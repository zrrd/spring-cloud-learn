package cn.learn.springcloud;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author shaoyijiong
 * @date 2021/4/25
 */
public class HealthCheckController {
    @RequestMapping("/health")
    public String healthCheck() {
        return "success";
    }
}
