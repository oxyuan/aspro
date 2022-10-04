## aspro

A simple project.

---

设计理念： 完全复用。

复用代码包括：

1. 配置分离，相同的配置在不同服务中也只需要设置一次；
2. 业务逻辑解耦，将不同类型的统一业务抽离出来；

### 一、aspro-web

web 服务入口

- aspro-web-gateway

> 网关服务

- aspro-web-admin

> 后台接口

- aspro-web-api

> 对外接口


### 二、aspro-starter

基础通用组件的封装

- aspro-starter-db

> 数据库操作的封装

- aspro-starter-nacos

> 配置\注册 中心的封装

- aspro-starter-web

> web 服务的封装

- aspro-starter-redis

> redis 相关服务的封装

- aspro-starter-sharding

> 支持分库分表，支持集成nacos后动态扩展表结构. [sharding-jdbc v5.2.0]()。

### 三、aspro-service

业务逻辑

- aspro-service-consumer

> 消息队列的消费处理

- aspro-service-engine

> 基础业务逻辑

- aspro-service-stream

> 流数据处理
 
- aspro-service-govern

> 分库分表接口调用服务