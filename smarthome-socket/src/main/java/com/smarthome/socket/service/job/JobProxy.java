package com.smarthome.socket.service.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
/**
 * 
 * 类名称：JobProxy 
 * 类描述：job代理类 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-11-29 上午10:34:12
 */
public class JobProxy implements Job
{
	//job对象
	private BaseJob job = null;
	/**
	 * 
	 * 方法的描述:job执行
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-29 上午10:34:53
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 * @param context
	 * @throws JobExecutionException
	 */
	public void execute(JobExecutionContext context)
		throws JobExecutionException
	{
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		this.job = (BaseJob) jobDataMap.get("job");
		this.job.execute();
	}
	
}
