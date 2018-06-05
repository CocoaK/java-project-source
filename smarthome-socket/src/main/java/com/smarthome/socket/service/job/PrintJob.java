package com.smarthome.socket.service.job;

import java.util.Map;
import java.util.Set;
import org.jboss.netty.channel.Channel;
/**
 * 
 * 类名称：PrintJob 
 * 类描述： 打印job类
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-11-29 上午10:35:36
 */
public class PrintJob extends BaseJob
{
	//管道map
	private Map<String, Channel> channel;
	
	//构造方法
	public PrintJob(Map<String, Channel> channel)
	{
		super();
		this.channel = channel;
	}


	/**
	 * 
	 * 方法的描述:job执行
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-29 上午10:37:00
	 * @see com.smarthome.socket.service.job.BaseJob#execute()
	 */
	public void execute()
		
	{
		if (channel != null && !channel.isEmpty())
		{
			
			// System.out.println("启动命令推送线程...");
			Set<String> keySet = channel.keySet();
			if (keySet != null && !keySet.isEmpty())
			{
				print("当前保存连接的终端数:"+keySet.size());
				for (String mac : keySet)
				{
					if (mac != null)
					{
						print("终端["+mac+"]保持连接状态");
						try
						{
							Thread.sleep(60000);
						}
						catch (InterruptedException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
			}
		}
	}
	
}
