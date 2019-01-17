## 简介

sync-manager,主要用于解决一些接收MQ消息处理问题
需要集成在spring环境中,目前执行定义仅支持参数为JSONObject方法


## Getting started

-  初始化数据库
    
    在数据库中执行 resource 目录下 init.sql,初始化所需表格

-  install 发布到本地MAVEN仓库
    
    clone项目代码,本地install
        
-   添加依赖
    -   Maven:
        ```xml
        <dependency>
            <groupId>pers.yurwisher</groupId>
            <artifactId>syncmanager</artifactId>
            <version>0.0.1</version>
        </dependency>
        ```
-   继承以下抽象类,使用自定义的ORM实现抽象方法,注入到spring上下文

    - AbstractMessageRecordService : 待处理消息
    
    - AbstractOperationDefinitionService : 操作定义
    
    - AbstractOperationLogService : 处理日志持久
    
    - 实现接口MQService,用于MQ发送消息
    
-   初始化执行器
    
    ```java
      @Bean
      public SyncManagerMessageProcessor syncManagerMessageProcessor(MessageRecordService messageRecordService,
                                                                     OperationDefinitionService operationDefinitionService,
                                                                     MqService mqService){
          SyncManagerMessageProcessorBuilder builder = new SyncManagerMessageProcessorBuilder();
          builder.setProcessThreadNumber(6);
          builder.setProcessThreadNamePrefix("message-process");
          builder.setRetryNumber(3);
          builder.setQueueSize(256);
          builder.setMessageRecordService(messageRecordService);
          builder.setOperationDefinitionService(operationDefinitionService);
          builder.setMqService(mqService);
          builder.setProcessExceptionHandle((messageExecute,e)->{
              //自定义的异常回调方法
          });
          return builder.build();
      }
    ```

-  执行器调用

    ```java
           /**
            * 监听到消息后执行
            * @param message 监听到的消息
            */
           void afterListeningDo(final String message);
       
       
           /**
            * 手动再次执行
            * @param messageRecord 待处理消息
            */
           void trigger(T messageRecord);
    ```
- 执行定义

     ```java
        package pers.test  ;
        
        @Component
        public class A{
          void m(JSONObject json){
              //方法M
          }
        }  
      
     ``` 
     - 在 OperationDefinition 定义一条记录
        - method_name : m 
        - service_name : pers.test.A 
         
- 消息发送
    - json字符串,字段如下
         - operation_id : OperationDefinition 中定义的记录ID
         - payload : 具体的参数(json)
         - message_no : 唯一消息号
         - sender_name : 消息发送者名称
         - operator : 操作人
         - doc_no : 关联单据号
         - chain_no : 调用链号
         - send_time :发送时间
         