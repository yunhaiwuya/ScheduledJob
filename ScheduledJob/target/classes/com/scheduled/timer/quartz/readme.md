任务调度
是指基于给定时间点，给定时间间隔或者给定执行次数自动执行任务。本文由浅入深介绍四种任务调度的 Java 实现：
Quartz
定时任务大哥：Quartz
虽然ScheduledExecutorService对Timer进行了线程池的改进，但是依然无法满足复杂的定时任务调度场景。因此OpenSymphony提供了强大的开源任务调度框架：Quartz。Quartz是纯Java实现，而且作为Spring的默认调度框架，由于Quartz的强大的调度功能、灵活的使用方式、还具有分布式集群能力，可以说Quartz出马，可以搞定一切定时任务调度！

1、从代码上来看，有XxxBuilder、XxxFactory，说明Quartz用到了Builder、Factory模式，还有非常易懂的链式编程风格。
2、Quartz有3个核心概念：调度器（Scheduler）、任务（Job&JobDetail）、触发器（Trigger）。（一个任务可以被多个触发器触发，一个触发器只能触发一个任务）
3、注意当Scheduler调度Job时，实际上会通过反射newInstance一个新的Job实例（待调度完毕后销毁掉），同时会把JobExecutionContext传递给Job的execute方法，Job实例通过JobExecutionContext访问到Quartz运行时的环境以及Job本身的明细数据。
4、JobDataMap可以装载任何可以序列化的数据，存取很方便。需要注意的是JobDetail和Trigger都可以各自关联上JobDataMap。JobDataMap除了可以通过上述代码获取外，还可以在YourJob实现类中，添加相应setter方法获取。
5、Trigger用来告诉Quartz调度程序什么时候执行，常用的触发器有2种：SimpleTrigger（类似于Timer）、CronTrigger（类似于Linux的Crontab）。
6、实际上，Quartz在进行调度器初始化的时候，会加载quartz.properties文件进行一些属性的设置，比如Quartz后台线程池的属性（threadCount）、作业存储设置等。它会先从工程中找，如果找不到那么就是用quartz.jar中的默认的quartz.properties文件。
7、Quartz存在监听器的概念，比如任务执行前后、任务的添加等，可以方便实现任务的监控。

除了上述基本的调度功能，Quartz 还提供了 listener 的功能。主要包含三种 listener：JobListener，TriggerListener 以及 SchedulerListener。当系统发生故障，相关人员需要被通知时，Listener 便能发挥它的作用。最常见的情况是，当任务被执行时，系统发生故障，Listener 监听到错误，立即发送邮件给管理员。

Quartz 的另一显著优点在于持久化，即将任务调度的相关数据保存下来。这样，当系统重启后，任务被调度的状态依然存在于系统中，不会丢失。默认情况下，Quartz 采用的是 org.quartz.simpl.RAMJobStore，在这种情况下，数据仅能保存在内存中，系统重启后会全部丢失。若想持久化数据，需要采用 org.quartz.simpl.JDBCJobStoreTX。

实现持久化的第一步，是要创建 Quartz 持久化所需要的表格。在 Quartz 的发布包 docs/dbTables 中可以找到相应的表格创建脚本。Quartz 支持目前大部分流行的数据库。