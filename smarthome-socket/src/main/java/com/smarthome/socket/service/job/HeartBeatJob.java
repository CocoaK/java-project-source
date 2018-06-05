package com.smarthome.socket.service.job;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smarthome.socket.common.util.CacheUtil;
import com.smarthome.socket.common.util.DateTimeUtil;
import com.smarthome.socket.common.util.SessionUtil;
import com.smarthome.socket.service.netty.service.NettyService;
/**
 * 
 * 类名称：HeartBeatJob 
 * 类描述：心跳job类，用于定时判断上次心跳时间与当前时间的差，超过某个时间间隔，判断终端已经离线 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-11-29 上午10:26:49
 */
public class HeartBeatJob extends BaseJob {
	Logger log = LoggerFactory.getLogger(HeartBeatJob.class.getName());
	//
	private NettyService nettyService;
	//心跳map
	private Map<String, Date> mac_time_heart_map;
	//管道id map
	private Map<Integer, String> channelId_mac_map;
	//mac地址与设备编号对应关系map
	private Map<String, String> mac_deviceNoMac_map;
	//设备编号,mac与管道map
	private Map<String, Channel> deviceNoMac_channel_map;
	//设备编号,mac与已发数据终端关系map
	private Map<String, Long> deviceNoMac_sentDataClient_map;
	
    //构造方法
	public HeartBeatJob(Map<String, Date> mac_time_heart_map, Map<Integer, String> channelId_mac_map,Map<String, String> mac_deviceNoMac_map,Map<String, Channel> deviceNoMac_channel_map,Map<String, Long> deviceNoMac_sentDataClient_map,NettyService nettyService) {
		super();
		this.deviceNoMac_channel_map = deviceNoMac_channel_map;
		this.nettyService = nettyService;
		this.mac_time_heart_map = mac_time_heart_map;
		this.channelId_mac_map=channelId_mac_map;
        this.mac_deviceNoMac_map=mac_deviceNoMac_map;
        this.deviceNoMac_channel_map=deviceNoMac_channel_map;
        this.deviceNoMac_sentDataClient_map=deviceNoMac_sentDataClient_map;
	}
	
	public static void main(String [] arg)
	{
		CacheUtil.getInstance().addChannelId_mac_map(1, "1");
		CacheUtil.getInstance().addChannelId_mac_map(2, "2");
		CacheUtil.getInstance().addChannelId_mac_map(3, "3");
		System.out.println("a---"+CacheUtil.getInstance().getChannelId_mac_map().size());
		Map<Integer,String> map=CacheUtil.getInstance().getChannelId_mac_map();
		map.remove(2);
		System.out.println("b---"+CacheUtil.getInstance().getChannelId_mac_map().size());
		System.out.println("c---"+map.size());
		CacheUtil.getInstance().addChannelId_mac_map(4, "4");
		System.out.println("d---"+CacheUtil.getInstance().getChannelId_mac_map().size());
		System.out.println("e---"+map.size());
	}
    /**
     * 
     * 方法的描述:job执行方法
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-11-29 上午10:25:58
     * @see com.smarthome.socket.service.job.BaseJob#execute()
     */
	public synchronized void  execute(){
		try{
			//判断保存心跳时间的map是否有数据
			if(mac_time_heart_map!=null&&!mac_time_heart_map.isEmpty()){
				print("------execute heartBeat check, size:"+mac_time_heart_map.size());
				//获取map key集合
				Set<String> keySet=mac_time_heart_map.keySet();
				if(keySet!=null&&!keySet.isEmpty()){
					for(String key:keySet){
						if(key!=null){
							//print("--mac:"+key);
							//上次心跳时间
							Date existDate=mac_time_heart_map.get(key);
							if(existDate!=null){
								//当前时间
								Date currentDate=new Date();
								//上次心跳时间与当前时间间隔
								long diffSeconds=DateTimeUtil.getDiffSecond(currentDate, existDate);
								//大于60s，说明终端已经有60s没有发送心跳了，目前规定终端是每隔20s向服务器发送一次心跳
								int limit = 60;
								if(diffSeconds>limit){	
									print("current client id:"+key+" not accept heartbeat over "+limit +"s");
									//获得设备编号与mac
									String deviceNoMac=mac_deviceNoMac_map.get(key);
									
									if(deviceNoMac!=null){
										nettyService.loginOut(deviceNoMac);
										//获得某个终端连接管道
										Channel channel=deviceNoMac_channel_map.get(deviceNoMac);
										if(channel!=null){
											//管道id
											Integer channelId=channel.getId();
											if(channelId!=null){
												//清空session数据
												this.removeDataFromMap(channelId);
												//连接关闭
												channel.close();
											}
										}else{
											//删除无效的数据
											mac_time_heart_map.remove(key);
										}
									}
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 方法的描述: 清除session数据
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 下午10:57:23
	 * @param channelId
	 */
	private void removeDataFromMap(Integer channelId) {
		
		SessionUtil.removeDataFromMap(channelId_mac_map, mac_deviceNoMac_map, deviceNoMac_channel_map, deviceNoMac_sentDataClient_map, mac_time_heart_map,nettyService, log, channelId);
	}
	
}
