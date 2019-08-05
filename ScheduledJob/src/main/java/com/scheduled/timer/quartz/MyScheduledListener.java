package com.scheduled.timer.quartz;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cjm 
 * @desc  SchedulerListener
 * SchedulerListener会在Scheduler的生命周期中关键事件发生时被调用。
 * 与Scheduler有关的事件包括：增加一个job/trigger，删除一个job/trigger，scheduler发生严重错误，关闭scheduler
 * @createDate 2019年7月22日下午8:07:33
 *
 */
public class MyScheduledListener implements SchedulerListener{

	 private static Logger logger = LoggerFactory.getLogger(MyScheduledListener.class);
	    
	    @Override
	    public void jobScheduled(Trigger trigger) {
	        String jobName = trigger.getJobKey().getName();
	        logger.info(jobName + " has been scheduled");
	    }

	    @Override
	    public void jobUnscheduled(TriggerKey triggerKey) {
	        logger.info(triggerKey + " is being unscheduled");
	    }

	    @Override
	    public void triggerFinalized(Trigger trigger) {
	        logger.info("Trigger is finished for " + trigger.getJobKey().getName());
	    }

	    @Override
	    public void triggerPaused(TriggerKey triggerKey) {
	        logger.info(triggerKey + " is being paused");
	    }

	    @Override
	    public void triggersPaused(String triggerGroup) {
	        logger.info("trigger group "+triggerGroup + " is being paused");
	    }

	    @Override
	    public void triggerResumed(TriggerKey triggerKey) {
	        logger.info(triggerKey + " is being resumed");
	    }

	    @Override
	    public void triggersResumed(String triggerGroup) {
	        logger.info("trigger group "+triggerGroup + " is being resumed");
	    }

	    @Override
	    public void jobAdded(JobDetail jobDetail) {
	        logger.info(jobDetail.getKey()+" is added");
	    }

	    @Override
	    public void jobDeleted(JobKey jobKey) {
	        logger.info(jobKey+" is deleted");
	    }

	    @Override
	    public void jobPaused(JobKey jobKey) {
	        logger.info(jobKey+" is paused");
	    }

	    @Override
	    public void jobsPaused(String jobGroup) {
	        logger.info("job group "+jobGroup+" is paused");
	    }

	    @Override
	    public void jobResumed(JobKey jobKey) {
	        logger.info(jobKey+" is resumed");
	    }

	    @Override
	    public void jobsResumed(String jobGroup) {
	        logger.info("job group "+jobGroup+" is resumed");
	    }

	    @Override
	    public void schedulerError(String msg, SchedulerException cause) {
	        logger.error(msg, cause.getUnderlyingException());
	    }

	    @Override
	    public void schedulerInStandbyMode() {
	        logger.info("scheduler is in standby mode");
	    }

	    @Override
	    public void schedulerStarted() {
	        logger.info("scheduler has been started");
	    }

	    @Override
	    public void schedulerStarting() {
	        logger.info("scheduler is being started");
	    }

	    @Override
	    public void schedulerShutdown() {
	        logger.info("scheduler has been shutdown");
	    }

	    @Override
	    public void schedulerShuttingdown() {
	        logger.info("scheduler is being shutdown");
	    }

	    @Override
	    public void schedulingDataCleared() {
	        logger.info("scheduler has cleared all data");
	    }
}
