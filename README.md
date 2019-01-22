#### spring cloud 微服务框架体系学习搭建

**技术选型**
* 核心框架：springboot 2.0.6.RELEASE
* 微服务框架：spring-cloud Finchley.SR1
* orm框架：mybatis 1.3.2
* 分布式事务：LCN框架（强一致性）
* 缓存框架：redis
* 调用监控：hystrix-dashboard
* 调用链跟踪：zipkin
* 配置中心：gitlab
* 消息总线：rabbitmq



*项目为使用maven构建的多模块项目*


**框架基础项目结构**
* zipkin-stream-server    调用链跟踪服务端
* hystrix-dashboard    熔断仪表盘
* service-registry-server    服务注册中心
* tx-manager    分布式事务管理器(LCN)
* config-server    配置中心服务端
* service-common    工具包
* service-db    数据源基础配置包
* config-center 配置中心（独立于maven项目）

**服务项目结构**

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
* 配置中心需要自己搭建gitlab服务器
    ```
    spring.cloud.config.server.git.uri=http://localhost/luqinglin/cloud.git
    spring.cloud.config.server.git.search-paths=config-center
    spring.cloud.config.server.git.username=luqinglin
    spring.cloud.config.server.git.password=luqinglin
    ```
* 消息总线和消息驱动都采用rabbitmq
    ```
   spring.rabbitmq.host=localhost
   spring.rabbitmq.port=5672
   spring.rabbitmq.virtual-host=testmq
   spring.rabbitmq.username=test
   spring.rabbitmq.password=test
    ```
* 分布式事务管理器使用的是redis持久化
    ```
    #redis主机地址
    spring.redis.host=127.0.0.1
    #redis主机端口
    spring.redis.port=6379
    ```
* zipkin-stream-server调用链跟踪服务数据持久化使用的是mysql数据库，建表语句参考zipkin-stream-server项目下create.sql

* 分布式事务测试表，需要创建两个数据库,分别配置到hello-server-provider、hello-consumer，建表语句参考LCN-test.sql
  数据库配置则参考config-center配置中心
  
  
 *项目启动顺序*
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
 jar -jar me.sta.HelloServerApplication --server.port=10086 --spring.profiles.active=dev --spring.cloud.config.profile=dev
 jar -jar me.sta.HelloServerApplication --server.port=10087 --spring.profiles.active=dev --spring.cloud.config.profile=dev
 ``` 
2. 启动服务消费者
 ```
 jar -jar me.sta.HelloConsumerApplication
 ``` 



