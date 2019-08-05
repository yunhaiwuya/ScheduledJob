package com.scheduled.timer.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author cjm 
 * @desc Quartz实例
 * 0 0-5 14 * * ?	每天下午的 2点到2点05分每分触发
 * 
 * @createDate 2019年7月22日下午5:25:13
 *
 */
public class QuartzDemo {
	
	private static Logger logger = LoggerFactory.getLogger(QuartzDemo.class);
	public static void main(String[] args) throws Exception {
		QuartzDemo quartz = new QuartzDemo();
		logger.info("init scheduler componets");
		// 创建调度器
        Scheduler scheduler = quartz.createScheduler();
        // 创建两个任务及对应的触发器
        JobDetail jobDetail1 = quartz.createJobDetail("HelloWord1_Job", "HelloWorld1_Group");
        Trigger trigger1 = quartz.createTrigger("HelloWord1_Job", "HelloWorld1_Group");
        
        JobDetail jobDetail2 = quartz.createJobDetail("HelloWord2_Job", "HelloWorld2_Group");
        Trigger trigger2 = quartz.createTrigger("HelloWord2_Job", "HelloWorld2_Group");
        
        // 构建调度任务
        scheduler.scheduleJob(jobDetail1, trigger1);
        scheduler.scheduleJob(jobDetail2, trigger2);
        
        // 创建并注册一个全局的Job Listener
        scheduler.getListenerManager().addJobListener(new MyJobListener(), EverythingMatcher.allJobs());
        // 创建并注册一个全局的Trigger Listener
        scheduler.getListenerManager().addTriggerListener(new MyTriggerListener("MyTrigger"), EverythingMatcher.allTriggers());
        
        // 创建并注册一个指定任务的Job Listener
       /* scheduler.getListenerManager().addJobListener(new MyJobListener(), KeyMatcher.keyEquals(JobKey.jobKey("HelloWorld1_Job", "HelloWorld1_Group")));
        //将同一任务组的任务注册到监听器中
        scheduler.getListenerManager().addJobListener(new MyJobListener(), GroupMatcher.jobGroupEquals("HelloWorld2_Group"));
        //将两个任务组的任务注册到监听器中
        scheduler.getListenerManager().addJobListener(new MyJobListener(), OrMatcher.or(GroupMatcher.jobGroupEquals("HelloWorld1_Group"), GroupMatcher.jobGroupEquals("HelloWorld2_Group")));
        */
        
        // 创建SchedulerListener
        scheduler.getListenerManager().addSchedulerListener(new MyScheduledListener());
        
        // 移除对应的SchedulerListener
//        scheduler.getListenerManager().removeSchedulerListener(new SimpleSchedulerListener());
        
        logger.info("execute scheduler");
        // 开启调度器
        scheduler.start();
        Thread.sleep(20000);
        scheduler.shutdown();
        logger.info("shut down scheduler");
    }
	
	protected Scheduler createScheduler() throws SchedulerException{
        return StdSchedulerFactory.getDefaultScheduler(); 
    }
    //任务详情
    protected JobDetail createJobDetail(String jobName, String jobGroup){
        return JobBuilder.newJob(MyJob.class)
                .withIdentity(jobName, jobGroup)
                .build();
    }
    // 触发器简单定时任务 每2秒执行一次，直到永远
    protected Trigger createTrigger(String triggerName, String triggerGroup){
        return  TriggerBuilder.newTrigger()
                .withIdentity(triggerName, triggerGroup)
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever()
                        ).build();
    }
    //cron 表达式 0 15 10 * * ? *	每天10点15分触发
    protected Trigger createTrigger(String triggerName, String triggerGroup,String cron){
        return  TriggerBuilder.newTrigger()
                .withIdentity(triggerName, triggerGroup)
                .withSchedule(
                		CronScheduleBuilder.cronSchedule(cron)
                        ).build();
    }
	
}
