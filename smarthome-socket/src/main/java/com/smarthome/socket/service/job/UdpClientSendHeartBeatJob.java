package com.smarthome.socket.service.job;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.smarthome.socket.service.netty.UdpClient;
import com.smarthome.socket.service.netty.service.NettyService;
/**
 * 
 * 类名称：PushJob 类描述：推送工作
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-9 下午3:28:54
 */
public class UdpClientSendHeartBeatJob extends BaseJob {
	Logger log = LoggerFactory.getLogger(UdpClientSendHeartBeatJob.class.getName());
	
	private static Map<String, String> service_status_map ;
    private String ip;
    private NettyService nettyService;
	public UdpClientSendHeartBeatJob(String ip,Map<String, String> service_status_map,NettyService nettyService) {
		super();
		this.ip=ip;
		this.service_status_map=service_status_map;
		this.nettyService=nettyService;
	}

	public synchronized void  execute()

	{
		new UdpClient(ip,service_status_map,nettyService);
	}

	

	
	
}
