package com.smarthome.socket.service.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smarthome.socket.common.constant.PubConstant;
import com.smarthome.socket.common.util.CacheUtil;
import com.smarthome.socket.common.util.TelnetUtil;
import com.smarthome.socket.service.quartz.QuartzManagerJob;


/**
 * 
 * 类名称：TelnetJob 
 * 类描述： Telnet任务类
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-10-31 下午4:14:55
 */
public class TelnetJob extends BaseJob {
	Logger log = LoggerFactory.getLogger(TelnetJob.class.getName());
	
	//ip地址
	private String ip;
	//端口
	private String port;
	public TelnetJob(String ip,String port) {
		super();		
		this.ip=ip;
		this.port=port;
	}
    /**
     * 
     * 方法的描述:job执行
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-11-29 上午10:45:56
     * @see com.smarthome.socket.service.job.BaseJob#execute()
     */
	public synchronized void  execute()
	{
		if(ip!=null&&port!=null)
		{
			System.out.println("telnet do...........");
			int hostPort=Integer.parseInt(port);
			boolean is_telnet_ok=TelnetUtil.telnetHoust(ip,hostPort);
			if(is_telnet_ok)
			{
				System.out.println("telnet ok");
				CacheUtil.getInstance().addCache(PubConstant.TELNET_SERVICE_STATUS, PubConstant.TELNET_SERVICE_STATUS_SUCCESS);
				QuartzManagerJob.removeJob("telnet_job");
			}else
			{
				CacheUtil.getInstance().addCache(PubConstant.TELNET_SERVICE_STATUS, PubConstant.TELNET_SERVICE_STATUS_FAIL);
			}
		}
	}

	

	
}
