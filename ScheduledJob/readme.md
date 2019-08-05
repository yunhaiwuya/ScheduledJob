任务调度
是指基于给定时间点，给定时间间隔或者给定执行次数自动执行任务。本文由浅入深介绍四种任务调度的 Java 实现：
Timer
ScheduledExecutor
开源工具包Quartz
开源工具包JCronTab

本文介绍了四种常用的对任务进行调度的 Java 实现方法，即 Timer，ScheduledExecutor, Quartz 以及 JCronTab。文本对每种方法都进行了实例解释，并对其优缺点进行比较。对于简单的基于起始时间点与时间间隔的任务调度，使用 Timer 就足够了；如果需要同时调度多个任务，基于线程池的 ScheduledTimer 是更为合适的选择；当任务调度的策略复杂到难以凭借起始时间点与时间间隔来描述时，Quartz 与 JCronTab 则体现出它们的优势。熟悉 Unix/Linux 的开发人员更倾向于 JCronTab，且 JCronTab 更适合与 Web 应用服务器相结合。Quartz 的 Trigger 与 Job 松耦合设计使其更适用于 Job 与 Trigger 的多对多应用场景。
