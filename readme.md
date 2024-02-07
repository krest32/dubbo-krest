# dubbo测试使用说明
## 服务启动配置
服务启动追加配置, 只需要添加相应参数即可绕开 java17 的限制
--add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/sun.reflect.generics.reflectiveObjects=ALL-UNNAMED --add-opens java.base/java.math=ALL-UNNAMED

## 已测试功能如下
dubbo可以提供的功能-已经尝试的功能
1. 服务的注册和发现
2. 服务的分组和同一接口的多版本
3. 集群的负载君均衡
4. 泛化调用的功能
5. 自定义 filter
6. 事件监听机制, 类似异步调用的效果
7. 调用日志记录,可以指定输出的文件路径
8. 本地调用：开发阶段不想暴露借口，protocol 使用 injvm 配置
9. 延迟暴漏， service delay 字段, 可以设置dubbo 从 spring启动后等待几秒的暴露时间

## 待测试功能：
1. 链路追踪 zipkin或skywalking
2. 分布式事务 seata
3. 限流 sentinel


思考：
dubbo 服务虽然能够有很强的扩展，但是使用起来会有一定的门槛，如果想自定义适合公司的一些功能，虽然稍微较强的自研能力
