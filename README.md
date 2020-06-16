#### spring cloud 微服务框架体系学习搭建

**使用的技术**
* 核心框架：springboot 2.0.6.RELEASE
* 微服务框架：spring-cloud Finchley.SR1
* orm框架：mybatis 1.3.2、tkmybatis2.0.2
* 分布式事务：LCN框架（强一致性）、bytetcc、可靠消息服务保证最终一致性
* 缓存框架：redis
* 调用监控：hystrix-dashboard
* 调用链跟踪：zipkin
* 配置中心：gitlab、config、bus、apollo、nacos
* 消息总线：rabbitmq
* 服务降级：hystrix、sentinel
* 数据库：mysql
* 数据库分片：sharding-jdbc
* 服务注册：eureka、zookeeper
* 服务请求：openfeign
* 服务网关：gateway

*项目为使用maven构建的多模块项目*


**框架基础项目结构**
（端口配置详见配置文件）

* cloud
    * config-center // 配置中心
    * config-server // 配置服务端 
    * server-common // 服务常用组件
        * service-client //注册服务依赖包
        * service-common //常用依赖集成包
        * service-db     
            * db-bytetcc //使用bytetcc依赖包
            * db-common  //普通单机或者读写分离依赖包
            * db-lcn     //使用lcn依赖包
    * server-instances // 服务实例 
        * apollo-test   //读取apollo配置文件实例
        * bytetcc-server //使用bytetcc-server实例
        * hello-server   //hello world 实例中心
            * ...
            * hello-server-provider-alibaba //使用阿里系的服务实例
            * hello-server-provider-sharding //数据分片实例
        * hystrix-dashboard //熔断仪表盘（同类型的sentinel使用官方jar包）
        * message-server   //可靠消息服务
        * service-gateway  //网关实例
        * sms-server       //短信服务实例
        * tx-manager       //lcn独立的事务管理器
        * user-server      //用户认证服务（使用OAuth2+JWT）
        * zipkin-stream-server //链路追踪
    * server-registry //服务注册中心 （阿里的nacos无须自己搭建）
        * service-registry-server eureka注册中心
        * service-registry-zookeeper zookeeper 注册中心
    
**服务项目结构**
（端口配置详见配置文件）
* hello-server    服务端
    * hello-server-api    系统服务api模块，定义服务端与客户端api标准
    * hello-server-entity    系统服务实体模块，定义服务所需model、dto、服务状态类
    * hello-server-provider  系统服务实现模块，针对api的实现
    * hello-consumer  服务客户端（消费端）

**项目使用**

 *项目搭建依赖的基本配置*

* 在没有多台机器情况下是通过修改本机host文件实现微服务注册高可用
    ```
    127.0.0.1 peer1
    127.0.0.1 peer2
    ```
* 配置中心需要自己搭建gitlab服务器   url需要根据自己仓库地址配置
    ```
    spring.cloud.config.server.git.uri=http://localhost/luqinglin/cloud.git
    spring.cloud.config.server.git.search-paths=config-center
    spring.cloud.config.server.git.username=luqinglin
    spring.cloud.config.server.git.password=luqinglin
    ```
* 消息总线和消息驱动都采用rabbitmq，消息总线刷新配置需要利用git仓库webhook 来触发
    ```
   spring.rabbitmq.host=localhost
   spring.rabbitmq.port=5672
   spring.rabbitmq.virtual-host=testmq
   spring.rabbitmq.username=test
   spring.rabbitmq.password=test
    ```
* 分布式事务管理器和sentinel都是使用的是redis持久化
    ```
    #redis主机地址
    spring.redis.host=127.0.0.1
    #redis主机端口
    spring.redis.port=6379
* 服务降级选择hystrix或者是senntinel使用下面配置进行切换
    ```
    #降级框架开关
    feign.hystrix.enabled=false
    ####################################
    feign.sentinel.enabled=true
    spring.cloud.sentinel.transport.dashboard=localhost:8080
    spring.cloud.sentinel.transport.port=8719
    ####################################
    ```
* zipkin-stream-server调用链跟踪服务数据持久化使用的是mysql数据库，建表语句参考zipkin-stream-server项目下create.sql
    ```
    #链路追踪
    spring.sleuth.sampler.probability=1
    spring.zipkin.baseUrl=http://127.0.0.1:9110
    spring.zipkin.encoder=json_v1
    spring.zipkin.sender.type=web
    ```
* 分布式事务测试表，需要创建两个数据库,分别配置到hello-server-provider、hello-consumer，建表语句参考LCN-test.sql
  数据库配置则参考config-center配置中心
  
  
 *项目执行示例*
 （伪代码）
 
1. 启动peer1服务注册中心（本服务注册到peer2服务上）
```
jar -jar me.sta.ServiceRegistryServerApplication --spring.profiles.active=peer1
```
2. 启动peer2服务注册中心（本服务注册到peer1服务上）
```
jar -jar me.sta.ServiceRegistryServerApplication --spring.profiles.active=peer2
```
3. 启动配置中心服务端
```
jar -jar me.sta.ConfigServerApplication 
```
4. 启动分布式事务管理器
```
jar -jar com.codingapi.tm.TxManagerApplication
```
5. 启动链跟踪服务
```
jar -jar me.sta.ZipkinStreamServerApplication
```
6. 启动调用监控服务
```
jar -jar me.sta.HystrixDashboardApplication
```
以上启动完后，整个微服务框架环境搭建完毕


 *业务实例代码*
1. 启动服务提供者
 ```
 jar -jar me.sta.HelloServerApplication --server.port=10086 --spring.profiles.active=dev,ms 
 jar -jar me.sta.HelloServerApplication --server.port=10087 --spring.profiles.active=dev,ms 
 ``` 
2. 启动服务消费者
 ```
 jar -jar me.sta.HelloConsumerApplication
 ``` 



