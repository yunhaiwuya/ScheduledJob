package com.scheduled.timer.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cjm 
 * @desc 业务job
 * @createDate 2019年7月22日下午6:20:49
 *
 */
public class MyJob implements Job{

	private static Logger logger = LoggerFactory.getLogger(MyJob.class);
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		logger.info("hello world");
		System.out.println("执行"+new Date()+","+
				context.getJobDetail().getKey().getName());
		
	}
}
