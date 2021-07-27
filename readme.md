SpringCloud
---

1. igame 用来当做通用属性包(其他包导入这个包的话需要exclude = DataSourceAutoConfiguration.class)
   来排除mysql的自动配置
2. provide 服务提供者
3. consumer 服务消费者
4. eureka 服务注册的服务端 7001 7002 7003 共同实现集群功能
5. ribbon 通过ribbon 80 客户端的负载均衡 @LoadBalanced
6. hystrix 带服务熔断机制的服务提供者 用于放在链式调用中的雪崩 (客户端)
7. hystrix dashboard 可视化服务监控
8. zuul 服务网关 用于过滤与路由
9. feign 通过feign进一步封装微服务直接的调用关系

### spring cloud alibab

1. dubbo dubbo + nacos + sentinel
2. provide nacos注册中心
3. consumer nacos注册中心
4. seata 分布式事务
5. sentinel 限流熔断组件

### apollo

配置中心

### sharding-jdbc

分库分表

修改host

# learn-springcloud

> 127.0.0.1 eureka7001.com
> 127.0.0.1 eureka7002.com
> 127.0.0.1 eureka7003.com
> 127.0.0.1 myzuul.com
> 127.0.0.1 client-config.com