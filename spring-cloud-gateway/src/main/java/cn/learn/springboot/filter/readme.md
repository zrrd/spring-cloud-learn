Spring Cloud Gateway 的 Filter 的生命周期不像 Zuul 的那么丰富，它只有两个：“pre” 和 “post”。

PRE： 这种过滤器在请求被路由之前调用。我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等。
POST：这种过滤器在路由到微服务以后执行。这种过滤器可用来为响应添加标准的 HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等。
Spring Cloud Gateway 的 Filter 分为两种：GatewayFilter 与 GlobalFilter。GlobalFilter 会应用到所有的路由上，而 GatewayFilter 将应用到单个路由或者一个分组的路由上。

Spring Cloud Gateway 内置了 9 种 GlobalFilter，比如 Netty Routing Filter、LoadBalancerClient Filter、Websocket Routing Filter 等，根据名字即可猜测出这些 Filter 的作者，具体大家可以参考官网内容：Global Filters

利用 GatewayFilter 可以修改请求的 Http 的请求或者响应，或者根据请求或者响应做一些特殊的限制。 更多时候我们会利用 GatewayFilter 做一些具体的路由配置，下面我们做一些简单的介绍。