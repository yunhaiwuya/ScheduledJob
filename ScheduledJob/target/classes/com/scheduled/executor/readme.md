任务调度
是指基于给定时间点，给定时间间隔或者给定执行次数自动执行任务。本文由浅入深介绍四种任务调度的 Java 实现：
ScheduledExecutor
鉴于 Timer 的上述缺陷，Java 5 推出了基于线程池设计的 ScheduledExecutor。其设计思想是，每一个被调度的任务都会由线程池中一个线程去执行，因此任务是并发执行的，相互之间不会受到干扰。需要注意的是，只有当任务的执行时间到来时，ScheduedExecutor 才会真正启动一个线程，其余时间 ScheduledExecutor 都是在轮询任务的状态。

 ScheduledExecutorService 中两种最常用的调度方法 ScheduleAtFixedRate 和 ScheduleWithFixedDelay。ScheduleAtFixedRate 每次执行时间为上一次任务开始起向后推一个时间间隔，即每次执行时间为 :initialDelay, initialDelay+period, initialDelay+2*period, …；ScheduleWithFixedDelay 每次执行时间为上一次任务结束起向后推一个时间间隔，即每次执行时间为：initialDelay, initialDelay+executeTime+delay, initialDelay+2*executeTime+2*delay。由此可见，ScheduleAtFixedRate 是基于固定时间间隔进行任务调度，ScheduleWithFixedDelay 取决于每次任务执行的时间长短，是基于不固定时间间隔进行任务调度。
 
 Timer 和 ScheduledExecutor 都仅能提供基于开始时间与重复间隔的任务调度，不能胜任更加复杂的调度需求。比如，设置每星期二的 16:38:10 执行任务。该功能使用 Timer 和 ScheduledExecutor 都不能直接实现，但我们可以借助 Calendar 间接实现该功能。
 用 ScheduledExecutor 和 Calendar 实现复杂任务调度
 其核心在于根据当前时间推算出最近一个星期二 16:38:10 的绝对时间，然后计算与当前时间的时间差，作为调用 ScheduledExceutor 函数的参数。计算最近时间要用到 java.util.calendar 的功能。
 
 Calendar 有以下几种唯一标识一个日期的组合方式：

YEAR + MONTH + DAY_OF_MONTH 
YEAR + MONTH + WEEK_OF_MONTH + DAY_OF_WEEK 
YEAR + MONTH + DAY_OF_WEEK_IN_MONTH + DAY_OF_WEEK 
YEAR + DAY_OF_YEAR 
YEAR + DAY_OF_WEEK + WEEK_OF_YEAR

上述组合分别加上 HOUR_OF_DAY + MINUTE + SECOND 即为一个完整的时间标识。本例采用了最后一种组合方式。输入为 DAY_OF_WEEK, HOUR_OF_DAY, MINUTE, SECOND 以及当前日期 , 输出为一个满足 DAY_OF_WEEK, HOUR_OF_DAY, MINUTE, SECOND 并且距离当前日期最近的未来日期。计算的原则是从输入的 DAY_OF_WEEK 开始比较，如果小于当前日期的 DAY_OF_WEEK，则需要向 WEEK_OF_YEAR 进一， 即将当前日期中的 WEEK_OF_YEAR 加一并覆盖旧值；如果等于当前的 DAY_OF_WEEK, 则继续比较 HOUR_OF_DAY；如果大于当前的 DAY_OF_WEEK，则直接调用 java.util.calenda 的 calendar.set(field, value) 函数将当前日期的 DAY_OF_WEEK, HOUR_OF_DAY, MINUTE, SECOND 赋值为输入值，依次类推，直到比较至 SECOND。读者可以根据输入需求选择不同的组合方式来计算最近执行时间。

可以看出，用上述方法实现该任务调度比较麻烦，这就需要一个更加完善的任务调度框架来解决这些复杂的调度问题。幸运的是，开源工具包 Quartz 与 JCronTab 提供了这方面强大的支持。
