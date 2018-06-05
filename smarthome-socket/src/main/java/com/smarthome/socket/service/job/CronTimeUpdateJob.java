package com.smarthome.socket.service.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smarthome.socket.common.constant.PubConstant;
import com.smarthome.socket.common.util.CacheUtil;
import com.smarthome.socket.service.netty.service.NettyService;
import com.smarthome.socket.service.quartz.QuartzManagerJob;

/**
 * 
 * 类名称：CronTimeUpdateJob 类描述：Cron表达式时间变更
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-12-11 下午3:32:00
 */
public class CronTimeUpdateJob extends BaseJob {
	Logger log = LoggerFactory.getLogger(CronTimeUpdateJob.class.getName());
	private NettyService nettyService;
	private String push_job_name_instancy;
	private String push_job_name_secondary;
	private String push_job_name;
	private String heartbeat_time_job_name;

	public CronTimeUpdateJob(NettyService nettyService, String push_job_name_instancy, String push_job_name_secondary,
			String push_job_name, String heartbeat_time_job_name) {
		super();
		this.nettyService = nettyService;
		this.push_job_name_instancy = push_job_name_instancy;
		this.push_job_name_secondary = push_job_name_secondary;
		this.push_job_name = push_job_name;
		this.heartbeat_time_job_name = heartbeat_time_job_name;
	}

	/**
	 * 
	 * 方法的描述:job执行
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-29 上午10:45:56
	 * @see com.smarthome.socket.service.job.BaseJob#execute()
	 */
	public synchronized void execute() {

		print("execute cron time update......");
		//判断service服务器是否已经启来
		boolean service_is_ok = CacheUtil.getInstance().serviceIsOK(PubConstant.TELNET_SERVICE_STATUS);
		if (service_is_ok) {//service服务器启来了
			//查到所有在参数表中设置的job定时表达式设置
			String cornTimeString = nettyService.queryCronTimeUpdate();
			if (cornTimeString != null) {
				//print("execute cron time update:"+cornTimeString);
				//拆分为数组
				String[] arry = cornTimeString.split(",");
				if (arry != null && arry.length == 4) {
					
					    //紧要推送数据类型cron表达式设置更新
//						this.modifyJobTime(arry[0], PubConstant.CRON_TIME_INSTANCY, push_job_name_instancy);
						//次要推送数据类型cron表达式设置更新
//						this.modifyJobTime(arry[1], PubConstant.CRON_TIME_SECONDARY, push_job_name_secondary);
						//普通推送数据类型cron表达式设置更新
//						this.modifyJobTime(arry[2], PubConstant.CRON_TIME_NORMAL, push_job_name);
						//心跳cron表达式设置更新
						this.modifyJobTime(arry[3], PubConstant.CRON_TIME_HEARTBEAT, heartbeat_time_job_name);
					
				}
			}
		}

	}

	/**
	 * 
	 * 方法的描述: 修改任务定时时间
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-11 下午4:33:54
	 * @param kvCronStr
	 * @param cronTimeName
	 * @param jobName
	 */
	private void modifyJobTime(String kvCronStr, String cronTimeName, String jobName) {
		if (kvCronStr != null) {
			if (kvCronStr.contains(cronTimeName)) {
				String[] kvStr = kvCronStr.split(":");
				if (kvStr != null && kvStr.length > 0) {
					//获得设置的cron表达式
					String cornTime = kvStr[1];
					if (cornTime != null && !"".equals(cornTime)) {
						//获得已经设置的cron表达式
						Object value = CacheUtil.getInstance().getValue(cronTimeName);
						if (value != null) 
						{
							//已经设置的cron表达式
							String existCornTime = (String) value;
							if (existCornTime != null && !existCornTime.equals(cornTime)) {
								//更新内存中的cron表达式
								CacheUtil.getInstance().addCache(cronTimeName, cornTime);
								print("update "+ jobName+" cron time to be:"+cornTime);
								//重启定时任务
								QuartzManagerJob.modifyJobTimeAndRestart(jobName, cornTime);
							}
						}else
						{
							////更新内存中的cron表达式
							CacheUtil.getInstance().addCache(cronTimeName, cornTime);
							print("update "+ jobName+" cron time to be:"+cornTime);
							//重启定时任务
							QuartzManagerJob.modifyJobTimeAndRestart(jobName, cornTime);
						}

					}
				}

			}
		}
	}

}
