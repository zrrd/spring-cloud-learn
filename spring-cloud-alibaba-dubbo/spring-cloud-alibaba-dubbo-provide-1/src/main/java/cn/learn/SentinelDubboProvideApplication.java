package cn.learn;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shaoyijiong
 * @date 2021/7/20
 */
@SpringBootApplication
public class SentinelDubboProvideApplication {

  public static void main(String[] args) {
    SpringApplication.run(SentinelDubboProvideApplication.class, args);
    // remoteAddress 代表 Nacos 服务端的地址
    // groupId 和 dataId 对应 Nacos 中相应配置
    ReadableDataSource<String, List<FlowRule>> flowRuleDataSource =
        new NacosDataSource<>("121.36.136.81:8848", "sentinel", "provide-sentinel",
            source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
            }));
    FlowRuleManager.register2Property(flowRuleDataSource.getProperty());

  }

}

