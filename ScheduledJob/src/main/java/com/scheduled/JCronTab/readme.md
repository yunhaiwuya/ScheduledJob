任务调度
是指基于给定时间点，给定时间间隔或者给定执行次数自动执行任务。本文由浅入深介绍四种任务调度的 Java 实现：
JCronTab
Crontab 是一个非常方便的用于 unix/linux 系统的任务调度命令。JCronTab 则是一款完全按照 crontab 语法编写的 java 任务调度工具。
crontab 的语法
主要由六个字段组成（括弧中标识了每个字段的取值范围）：
Minutes （0-59）
Hours   （0-23） 
Day-of-Month （1-31）
Month    （1-12/JAN-DEC） 
Day-of-Week  （0-6/SUN-SAT）
Command
与 Quartz 相比，省略了 Seconds 与 Year，多了一个 command 字段，即为将要被调度的命令。JCronTab 中也包含符号“*”与“/”, 其含义与 Quartz 相同。

JCronTab 与 Web 应用服务器的结合非常简单，只需要在 Web 应用程序的 web.xml 中添加如下行：
在 web.xml 中配置 JCronTab 的属性
<servlet> 
  <servlet-name>LoadOnStartupServlet</servlet-name> 
  <servlet-class>org.jcrontab.web.loadCrontabServlet</servlet-class> 
  <init-param> 
<param-name>PROPERTIES_FILE</param-name> 
<param-value>D:/Scheduler/src/jcrontab.properties</param-value> 
  </init-param> 
  <load-on-startup>1</load-on-startup> 
</servlet> 
<!-- Mapping of the StartUp Servlet -->
<servlet-mapping> 
  <servlet-name>LoadOnStartupServlet</servlet-name> 
<url-pattern>/Startup</url-pattern> 
</servlet-mapping>

需要注意两点：第一，必须指定 servlet-class 为 org.jcrontab.web.loadCrontabServlet，因为它是整个任务调度的入口；第二，必须指定一个参数为 PROPERTIES_FILE，才能被 loadCrontabServlet 识别。
接下来，需要撰写 D:/Scheduler/src/jcrontab.properties 的内容，其内容根据需求的不同而改变。

当采用普通文件持久化时，jcrontab.properties 的内容主要包括：

org.jcrontab.data.file = D:/Scheduler/src/crontab 
org.jcrontab.data.datasource = org.jcrontab.data.FileSource
其中数据来源 org.jcrontab.data.datasource 被描述为普通文件，即 org.jcrontab.data.FileSource。具体的文件即 org.jcrontab.data.file 指明为 D:/Scheduler/src/crontab。

Crontab 描述了任务的调度安排：
*/2 * * * * com.ibm.scheduler.JCronTask1 
* * * * * com.ibm.scheduler.JCronTask2#run Hello World
其中包含了两条任务的调度，分别是每两分钟执行一次 JCronTask1 的 main 方法，每一分钟执行一次 JCronTask2 的 run 方法。