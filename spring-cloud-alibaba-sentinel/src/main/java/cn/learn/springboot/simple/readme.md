
运行下面命令，则会返回现有生效的规则：
其中，type=flow 以 JSON 格式返回现有的限流规则；degrade 则返回现有生效的降级规则列表；system 则返回系统保护规则。
```
curl http://localhost:8719/getRules?type=<XXXX>
```

同时也可以通过下面命令来修改已有规则： 其中，type 可以输入 flow、degrade 等方式来制定更改的规则种类，data 则是对应的 JSON 格式的规则。
```
curl http://localhost:8719/setRules?type=<XXXX>&data=<DATA>
```

上面的规则配置，都是存在内存中的。即如果应用重启，这个规则就会失效。因此我们提供了开放的接口，您可以通过实现 DataSource 接口的方式，来自定义规则的存储数据源。通常我们的建议有：

整合动态配置系统，如 ZooKeeper、Nacos 等，动态地实时刷新配置规则
结合 RDBMS、NoSQL、VCS 等来实现该规则
配合 Sentinel Dashboard 使用
https://github.com/alibaba/Sentinel/wiki/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%99%E6%89%A9%E5%B1%95


日志 
https://github.com/alibaba/Sentinel/wiki/%E6%97%A5%E5%BF%97

```
其它 API
业务异常统计 Tracer
业务异常记录类 Tracer 用于记录业务异常。相关方法：

public static void trace(Throwable e)：记录业务异常（非 BlockException 异常）
public static void trace(Throwable e, int count)：记录业务异常，异常数目为传入的 count。
如果用户通过 SphU 或 SphO 手动定义资源，则 Sentinel 不能感知上层业务的异常，需要手动调用 Tracer.trace(ex) 来记录业务异常，否则对应的异常不会统计到 Sentinel 异常计数中。

从 1.3.1 版本开始，注解方式定义资源支持自动统计业务异常，无需手动调用 Tracer.trace(ex) 来记录业务异常。Sentinel 1.3.1 以前的版本需要手动记录。

上下文工具类 ContextUtil
相关静态方法：

标识进入调用链入口（上下文）：

以下静态方法用于标识调用链路入口，用于区分不同的调用链路：

public static Context enter(String contextName)
public static Context enter(String contextName, String origin)
其中 contextName 代表调用链路入口名称（上下文名称），origin 代表调用来源名称。默认调用来源为空。返回值类型为 Context，即生成的调用链路上下文对象。

注意：ContextUtil.enter(xxx) 方法仅在调用链路入口处生效，即仅在当前线程的初次调用生效，后面再调用不会覆盖当前线程的调用链路，直到 exit。Context 存于 ThreadLocal 中，因此切换线程时可能会丢掉，如果需要跨线程使用可以结合 runOnContext 方法使用。

流控规则中若选择“流控方式”为“链路”方式，则入口资源名即为上面的 contextName。

退出调用链（清空上下文）：

public static void exit()：该方法用于退出调用链，清理当前线程的上下文。
获取当前线程的调用链上下文：

public static Context getContext()：获取当前线程的调用链路上下文对象。
在某个调用链上下文中执行代码：

public static void runOnContext(Context context, Runnable f)：常用于异步调用链路中 context 的变换。
指标统计配置
Sentinel 底层采用高性能的滑动窗口数据结构来统计实时的秒级指标数据，并支持对滑动窗口进行配置。主要有以下两个配置：

windowIntervalMs：滑动窗口的总的时间长度，默认为 1000 ms
sampleCount：滑动窗口划分的格子数目，默认为 2；格子越多则精度越高，但是内存占用也会越多
sliding-window-leap-array

我们可以通过 SampleCountProperty 来动态地变更滑动窗口的格子数目，通过 IntervalProperty 来动态地变更滑动窗口的总时间长度。注意这两个配置都是全局生效的，会影响所有资源的所有指标统计。
```