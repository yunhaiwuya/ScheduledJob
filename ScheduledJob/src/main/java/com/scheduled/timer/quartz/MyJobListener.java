package com.scheduled.timer.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cjm 
 * @desc 	Quartz 还提供了 listener 的功能
 * @createDate 2019年7月22日下午6:58:21
 *
 */
public class MyJobListener implements JobListener{

	private static Logger logger = LoggerFactory.getLogger(MyJobListener.class);
	//用于获取该JobListener的名称
	@Override
	public String getName() {
		String name = getClass().getSimpleName();
        logger.info(" listener name is:"+name);
		return name;
	}

	//Scheduler在JobDetail将要被执行时调用这个方法。
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		String jobName = context.getJobDetail().getKey().getName();
        logger.info(jobName + " is going to be executed");
	}

	//Scheduler在JobDetail即将被执行，但又被TriggerListerner否决时会调用该方法
	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		 String jobName = context.getJobDetail().getKey().getName();
	     logger.info(jobName + " was vetoed and not executed");
	}

	//Scheduler在JobDetail被执行之后调用这个方法
	@Override
	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {
		String jobName = context.getJobDetail().getKey().getName();
        logger.info(jobName + " was executed");
		/*if(jobException!=null){
			try {
				//停止Scheduler
				context.getScheduler().shutdown();
				System.out.println("Error occurs when executing jobs, shut down the scheduler");
				// 给管理员发送邮件…
			} catch (SchedulerException e) {
				e.printStackTrace();
			}*/
	  }
}

