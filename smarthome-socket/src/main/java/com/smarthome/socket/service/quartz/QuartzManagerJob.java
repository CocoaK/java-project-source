package com.smarthome.socket.service.quartz;

import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.smarthome.socket.service.job.BaseJob;


/**
 * 
 * 类名称：QuartzManagerJob 
 * 类描述： 定时任务管理器
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-10-30 上午10:06:52
 */
public class QuartzManagerJob
{
	
	private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
	
	private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
	
	private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";
	
	/**
	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * 
	 * @param jobName 任务名
	 * @param jobClass 任务
	 * @param time 时间设置，参考quartz说明文档
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public static void addJob(String jobName, String jobClass, String time)
	{
		try
		{
			Scheduler sched = gSchedulerFactory.getScheduler();
			JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, Class
					.forName(jobClass));// 任务名，任务组，任务执行类
			// 触发器
			CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);// 触发器名,触发器组
			trigger.setCronExpression(time);// 触发器时间设定
			sched.scheduleJob(jobDetail, trigger);
			// 启动
			if (!sched.isShutdown())
			{
				sched.start();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static void addJob(String jobName, BaseJob baseJob, String time)
	{
		try
		{
			Scheduler sched = gSchedulerFactory.getScheduler();
			JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, Class
					.forName("com.smarthome.socket.service.job.JobProxy"));// 任务名，任务组，任务执行类
			jobDetail.getJobDataMap().put("job",baseJob);

			// 触发器
			CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);// 触发器名,触发器组
			trigger.setCronExpression(time);// 触发器时间设定
			sched.scheduleJob(jobDetail, trigger);
			// 启动
			if (!sched.isShutdown())
			{
				sched.start();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 添加一个定时任务
	 * 
	 * @param jobName 任务名
	 * @param jobGroupName 任务组名
	 * @param triggerName 触发器名
	 * @param triggerGroupName 触发器组名
	 * @param jobClass 任务
	 * @param time 时间设置，参考quartz说明文档
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public static void addJob(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName, String jobClass,
			String time)
	{
		try
		{
			Scheduler sched = gSchedulerFactory.getScheduler();
			JobDetail jobDetail = new JobDetail(jobName, jobGroupName, Class
					.forName(jobClass));// 任务名，任务组，任务执行类
			// 触发器
			CronTrigger trigger = new CronTrigger(triggerName, triggerGroupName);// 触发器名,触发器组
			trigger.setCronExpression(time);// 触发器时间设定
			sched.scheduleJob(jobDetail, trigger);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 * @param time
	 */
	public static void modifyJobTime(String jobName, String time)
	{
		try
		{
			Scheduler sched = gSchedulerFactory.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(jobName,
					TRIGGER_GROUP_NAME);
			if (trigger == null)
			{
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time))
			{
				JobDetail jobDetail = sched.getJobDetail(jobName,
						JOB_GROUP_NAME);
				Class objJobClass = jobDetail.getJobClass();
				String jobClass = objJobClass.getName();
				removeJob(jobName);
				
				addJob(jobName, jobClass, time);
				System.out.println("update job:"+jobName+",time:"+time+" successfully!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 
	 * 方法的描述: 修改任务定时时间，并重启
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-12-11 下午5:14:26
	 * @param jobName
	 * @param time
	 */
	public static void modifyJobTimeAndRestart(String jobName, String time)
	{
		try
		{
			Scheduler sched = gSchedulerFactory.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(jobName,
					TRIGGER_GROUP_NAME);
			if (trigger == null)
			{
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time))
			{
				CronTrigger ct = (CronTrigger) trigger;
				// 修改时间
				ct.setCronExpression(time);
				// 重启触发器
				sched.rescheduleJob(jobName, TRIGGER_GROUP_NAME, ct);
				System.out.println("update job:"+jobName+",time:"+time+" successfully!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * 修改一个任务的触发时间
	 * 
	 * @param triggerName
	 * @param triggerGroupName
	 * @param time
	 */
	public static void modifyJobTime(String triggerName,
			String triggerGroupName, String time)
	{
		try
		{
			Scheduler sched = gSchedulerFactory.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerName,
					triggerGroupName);
			if (trigger == null)
			{
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time))
			{
				CronTrigger ct = (CronTrigger) trigger;
				// 修改时间
				ct.setCronExpression(time);
				// 重启触发器
				sched.resumeTrigger(triggerName, triggerGroupName);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 */
	public static void removeJob(String jobName)
	{
		try
		{
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.pauseTrigger(jobName, TRIGGER_GROUP_NAME);// 停止触发器
			sched.unscheduleJob(jobName, TRIGGER_GROUP_NAME);// 移除触发器
			sched.deleteJob(jobName, JOB_GROUP_NAME);// 删除任务
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 移除一个任务
	 * 
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 */
	public static void removeJob(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName)
	{
		try
		{
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.pauseTrigger(triggerName, triggerGroupName);// 停止触发器
			sched.unscheduleJob(triggerName, triggerGroupName);// 移除触发器
			sched.deleteJob(jobName, jobGroupName);// 删除任务
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 启动所有定时任务
	 */
	public static void startJobs()
	{
		try
		{
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 关闭所有定时任务
	 */
	public static void shutdownJobs()
	{
		try
		{
			Scheduler sched = gSchedulerFactory.getScheduler();
			if (!sched.isShutdown())
			{
				sched.shutdown();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
