* eureka 满足了CAP原则的 A可用性 P分区容错性 一个服务器挂了eureka不能马上发现 只能保障最终一致性
* Zookeeper 满足了CAP原则的 C一致性 P分区容错性 在一个Zookeeper结点挂了以后需要选举 导致服务不可用