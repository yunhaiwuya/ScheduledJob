package com.scheduled.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author cjm 
 * @desc ScheduledExecutor实例(简单)
 * @createDate 2019年7月22日下午5:25:13
 *
 */
public class ScheduledExecutorDemo implements Runnable{

	private String jobName = ""; 
	public ScheduledExecutorDemo(String jobName) {
		super();
		this.jobName = jobName;
	}
	@Override
	public void run() {
		System.out.println("excute:"+jobName);
	}
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		long initialDelay1 = 1;
        long period1 = 1;
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1
        service.scheduleAtFixedRate(
                new ScheduledExecutorDemo("job1"), initialDelay1,
                period1, TimeUnit.SECONDS);
 
        long initialDelay2 = 2;
        long delay2 = 2;
        // 从现在开始2秒钟之后，每隔2秒钟执行一次job2
        service.scheduleWithFixedDelay(
                new ScheduledExecutorDemo("job2"), initialDelay2,
                delay2, TimeUnit.SECONDS);
	}
}
