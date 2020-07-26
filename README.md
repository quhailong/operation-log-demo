# operation-log-demo
---
## 2020年07月26日首次提交
## 描述
操作日志Demo
## 采用技术
SpringBoot2.3.2   
druid  
mybatis   
aspectj 
## 功能
1、controller层使用aspectj进行日志记录，并使用slf4j中的MDC进行方法中自定义日志记录内容   
2、方法抛出异常能够记录异常名称和异常内容  
## 项目运行
直接运行OperationApplication即可，默认8080端口   
## 注意
>每个项目中的resources文件夹需要IDEA识别出来，否则不能读取配置文件  
![Alt text](https://github.com/quhailong/operation-log-demo/blob/master/1.png)
## 结果截图
![Alt text](https://github.com/quhailong/operation-log-demo/blob/master/2.png)  
![Alt text](https://github.com/quhailong/operation-log-demo/blob/master/3.png) 
## tips
如果有什么问题请联系  
QQ：961584293  
WX: ququhailong  
邮箱:qhl961584293@163.com  
如果觉得还行，就请点个赞把