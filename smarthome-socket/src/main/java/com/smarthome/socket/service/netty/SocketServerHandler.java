package com.smarthome.socket.service.netty;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ChildChannelStateEvent;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bsh.StringUtil;

import com.smarthome.socket.common.constant.Constant;
import com.smarthome.socket.common.constant.PubConstant;
import com.smarthome.socket.common.model.ResultEntity;
import com.smarthome.socket.common.util.CacheUtil;
import com.smarthome.socket.common.util.DateTimeUtil;
import com.smarthome.socket.common.util.JsonUtil;
import com.smarthome.socket.common.util.SessionUtil;
import com.smarthome.socket.service.business.adapater.RegistRsp;
import com.smarthome.socket.service.job.CronTimeUpdateJob;
import com.smarthome.socket.service.job.HeartBeatJob;
import com.smarthome.socket.service.job.InitDeviceJob;
import com.smarthome.socket.service.job.PushJob;
import com.smarthome.socket.service.netty.service.NettyService;
import com.smarthome.socket.service.quartz.QuartzManagerJob;
import com.smarthome.socket.service.vo.BaseVO;
import com.smarthome.socket.service.vo.HeartBeatVO;
import com.smarthome.socket.service.vo.LoginVO;

/**
 * 
 * 项目名称：smarthome-socket 类名称：SocketServerHandler 类描述： 处理类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-4-19 下午5:54:32
 */
public class SocketServerHandler extends SimpleChannelUpstreamHandler {

	Logger log = LoggerFactory.getLogger(SocketServerHandler.class.getName());

	private String push_job_name_upgrade = "upgrade";
//	private String push_job_name_secondary = "smartHomeDataPushSecondary";
//	private String push_job_name = "smartHomeDataPush";
	private String cron_time_update_job_name = "cronTimeUpdate";
	private static boolean isInitedDeviceStatusOutline = true;

	private String heartbeat_time_job_name = "heart_beat_time";
	private String init_device_job_name = "initDeviceJob";
	// 业务处理接口
	private NettyService nettyService;
	// 设备编号与设备MAC对应关系,key由deviceNo_mac组成
	private Map<String, Channel> deviceNoMac_channel_map;
	// 设备Mac与deviceNo_mac关系
	private Map<String, String> mac_deviceNoMac_map;

	// 连接id与设备Mac的关系
	private Map<Integer, String> channelId_mac_map;
	// 设备MAC与发送数据ID的关系,key由deviceNo_mac组成
	private Map<String, Long> deviceNoMac_sentDataClient_map;
	// 心跳时间
	private Map<String, Date> mac_time_heart_map;
//	// 紧急推送数据的时间计划
	private String instancyCronJob = "0/5 * * * * ?";
//	// 次要推送数据的时间计划
//	private String secondaryCronJob = "0/10 * * * * ?";
//	// 普通推送的时间计划
//	private String normalCronJob = "0/15 * * * * ?";
	// 心跳检测的时间计划
	private String heartBeatCronJob = "0/30 * * * * ?";
	// job定时时间更新检查间隔
	private String cronTimeUpdateJob = "0 0/5 * * * ?";
	// 初始化设备任务的时间计划
	private String initDeviceCronJob = "0/10 * * * * ?";

	// 分组
	private static ChannelGroup recipients = new DefaultChannelGroup();

	public SocketServerHandler() {
		super();
	}

	public SocketServerHandler(NettyService nettyService) {
		super();
		CacheUtil.getInstance().clearAllMaps();
		this.nettyService = nettyService;
		deviceNoMac_channel_map = CacheUtil.getInstance().getDeviceNoMac_channel_map();
		mac_deviceNoMac_map = CacheUtil.getInstance().getMac_deviceNoMac_map();
		channelId_mac_map = CacheUtil.getInstance().getChannelId_mac_map();
		deviceNoMac_sentDataClient_map = CacheUtil.getInstance().getDeviceNoMac_sentDataClient_map();
		mac_time_heart_map = CacheUtil.getInstance().getMac_time_heart_map();
		CacheUtil.getInstance().addCache(PubConstant.CRON_TIME_INSTANCY, instancyCronJob);
//		CacheUtil.getInstance().addCache(PubConstant.CRON_TIME_SECONDARY, secondaryCronJob);
//		CacheUtil.getInstance().addCache(PubConstant.CRON_TIME_NORMAL, normalCronJob);
		CacheUtil.getInstance().addCache(PubConstant.CRON_TIME_HEARTBEAT, heartBeatCronJob);
//		nettyService.initialDeviceStatus(PubConstant.OUTLINE);
//		// 设置为初始化完成标志位
//		log.info("设置为初始化完成标志位...");
//		CacheUtil.getInstance().addCache(PubConstant.INITAL_ALL_DEVICE, PubConstant.INITAL_ALL_DEVICE_STATUS);
		//service_status_map.put("init_device_status", "false");
		// log.info("【添加检测service存在心跳】开始(每3秒推送一次)...");
		// QuartzManagerJob.addJob("udp_heart_beat_send", new
		// UdpClientSendHeartBeatJob(null,service_status_map,nettyService),
		// "0/3 * * * * ?");

		log.info("【添加紧急推送定时任务】开始(每5秒推送一次)...");
		QuartzManagerJob.addJob(push_job_name_upgrade, new PushJob(mac_time_heart_map, channelId_mac_map, mac_deviceNoMac_map,
				deviceNoMac_channel_map, deviceNoMac_sentDataClient_map, nettyService), instancyCronJob);
//
//		log.info("【添加次要推送定时任务】开始(每10秒推送一次)...");
//		QuartzManagerJob.addJob(push_job_name_secondary, new PushJob(mac_time_heart_map, channelId_mac_map, mac_deviceNoMac_map,
//				deviceNoMac_channel_map, deviceNoMac_sentDataClient_map, nettyService, PubConstant.PUSH_KINDS_SECONDARY), secondaryCronJob);

//		log.info("【添加普通推送定时任务】开始(每15秒推送一次)...");
//		QuartzManagerJob.addJob(push_job_name, new PushJob(mac_time_heart_map, channelId_mac_map, mac_deviceNoMac_map,
//				deviceNoMac_channel_map, deviceNoMac_sentDataClient_map, nettyService, null), normalCronJob);

		log.info("【添加初始化检查定时任务】开始(每10秒检查一次)...");
		QuartzManagerJob.addJob(init_device_job_name, new InitDeviceJob(nettyService), initDeviceCronJob);
		
		log.info("【添加心跳检查定时任务】开始(每30秒检查一次)...");
		QuartzManagerJob.addJob(heartbeat_time_job_name, new HeartBeatJob(mac_time_heart_map, channelId_mac_map, mac_deviceNoMac_map,
				deviceNoMac_channel_map, deviceNoMac_sentDataClient_map, nettyService), heartBeatCronJob);

		log.info("【定时检查其他任务时间变更】开始(每5分钟检查一次)...");
		QuartzManagerJob.addJob(cron_time_update_job_name, new CronTimeUpdateJob(nettyService, null,
				null, null, heartbeat_time_job_name), cronTimeUpdateJob);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)throws Exception {
		InetSocketAddress address = null;
		try {
			address = (InetSocketAddress) e.getChannel().getRemoteAddress();

			String msg = (String) e.getMessage();
			log.info("received[channelId:"+e.getChannel().getId().toString()+"]:" + msg);
			if (msg != null && !"".equals(msg)) {
				msg = msg.trim();
//				log.info("accept client[ip:" + address.getAddress().getHostAddress() + ",hostName:" + address.getAddress().getHostName()
//				+ ",localName:" + address.getAddress().getLocalHost().getHostAddress() + "] Data" + msg);
//				log.info("------server received [channelId:"+e.getChannel().getId().toString()+"]:" + msg);
				//
				Boolean receiveFlag = false;
				// json格式
				if (msg.startsWith("{") && msg.endsWith("}")) {
					// json数据解析
					JSONObject jb = JsonUtil.jsonStringToJsonObject(msg);
					if (jb != null) {
						String jsonType = JsonUtil.getDataFromJsonObject(jb, "jsonType");
						String deviceMac = JsonUtil.getDataFromJsonObject(jb, "mac");
						String deviceNo = JsonUtil.getDataFromJsonObject(jb, "deviceNo");
//						String deviceName = JsonUtil.getDataFromJsonObject(jb, "deviceName");
//						String areaNo = JsonUtil.getDataFromJsonObject(jb, "estateno");
//						String ip = JsonUtil.getDataFromJsonObject(jb, "ip");
						String mac = JsonUtil.getDataFromJsonObject(jb, "mac");
						/**升级json*/
						String update = JsonUtil.getDataFromJsonObject(jb, "update");
						/**列丰开锁json*/
						String openlock = JsonUtil.getDataFromJsonObject(jb, "openlock");

//						String url = JsonUtil.getDataFromJsonObject(jb, "url");
						//nettyService.saveClientLog(deviceNo, mac, jsonType, deviceName, msg, areaNo, ip, PubConstant.DATA_FROM_APPCLIENT);
						if (deviceMac != null)
							// 避免一个设备建立多个长连接
							keepOneLongConnect(e, deviceMac);
						if ("login".equals(jsonType)) {
							// 判断同一台是否正在注册，如果是，该设备再发注册信息，将不处理
							boolean isExistKey = CacheUtil.getInstance().isContainKey(deviceMac);
							System.out.println("CacheUtil.getInstance():"+CacheUtil.getInstance().getValue(deviceMac));
							if (!isExistKey) {// 没有正在注册
								CacheUtil.getInstance().addCache(deviceMac, e.getChannel().getId());
								CacheUtil.getInstance().addDeviceNoMac_channel_map(deviceMac, e.getChannel());
								login(e, jb, jsonType, deviceMac);
								log.info("client login......");
							}

						}else if ("register".equals(jsonType)) {
							// 判断同一台是否正在注册，如果是，该设备再发注册信息，将不处理
							boolean isExistKey = CacheUtil.getInstance().isContainKey(deviceMac);
							if (!isExistKey) {// 没有正在注册
								CacheUtil.getInstance().addCache(deviceMac, e.getChannel().getId());
								CacheUtil.getInstance().addDeviceNoMac_channel_map(deviceMac, e.getChannel());
								register(e, jb, jsonType, deviceMac);
								mac_time_heart_map.put(mac, new Date());
								log.info("channelId:"+e.getChannel().getId().toString()+", client registering............");
							}
						}else if (update!=null) {
							transUpdateData(msg, mac, e);
						//判断开锁
						}else if (openlock!=null) {
							transUnlockData(msg, e);
						}else {

							// 没有登陆
							if (!mac_deviceNoMac_map.containsKey(deviceMac)) {
								log.info("channelId:"+e.getChannel().getId().toString()+", client not login............");
								this.removeDataFromMap(e.getChannel().getId());
								e.getChannel().disconnect();
								e.getChannel().close();// 关闭连接
							} else// 已登陆
							{
								//log.info("channelId:"+e.getChannel().getId().toString()+ ", client  logined............");
								// String pushId =
								// JsonUtil.getDataFromJsonObject(jb, "pushId");
								if ("heartBeat".equals(jsonType)) {
									mac_time_heart_map.put(mac, new Date());
									HeartBeatVO hb = new HeartBeatVO();
									hb.setJsonType(jsonType);
									hb.setMessage("ok");
									hb.setDeviceNo(deviceNo);
									hb.setCode(BaseVO.SUCCESS);
									hb.setServerTime(DateTimeUtil.getCurrentServerTime());
									String jsonString = JsonUtil.objectToJsonString(hb) + "\r\n";
									ChannelFuture future = e.getChannel().write(jsonString);
//									log.info("--------heartBeat result:[channelId:"+e.getChannel().getId().toString()+", isSuccess:"+future.isSuccess()+", isDone:"+future.isDone()+"],server ack:"+jsonString);
									// 更新设备状态为在线状态
									nettyService.updateDeviceStatusByMac(PubConstant.ONLINE, deviceMac);
									// 写日志
									//nettyService.saveClientLog(deviceNo, mac, jsonType, deviceName, jsonString, areaNo, ip,
									//		PubConstant.DATA_FROM_SERVER);
//									log.info("reply client[deviceNo:" + deviceNo + ",mac:" + mac + "]heartBeat message:" + jsonString);

								}else if("trans".equals(jsonType)){
									String data = JsonUtil.getDataFromJsonObject(jb, "data");
									//处理透传数据
									transData(data, mac, e);
								}else if("refresh".equals(jsonType)){
									Map<String, Object> mp = new HashMap<String, Object>();
									mp.put("code", true);
									CacheUtil.getInstance().addCache(e.getChannel().getId().toString(),mp);
								}else {
									// 数据接收
									boolean flag = nettyService.acceptData(jb, jsonType, deviceMac, null);
								}

							}
						}
					}
				}
			} else {
//				e.getChannel().disconnect();
//				e.getChannel().close();
			}
		} catch (Exception ee) {
			this.removeDataFromMap(e.getChannel().getId());
			e.getChannel().disconnect();
			e.getChannel().close();
			ee.printStackTrace();
			log.error("client exception:" + (address == null ? "" : address.getHostName()) + ":" + ee.getMessage());
		}

	}

	/**
	 * 
	 * 方法的描述: 登陆
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-11 上午8:32:09
	 * @param e
	 * @param jb
	 *            json对象
	 * @param jsonType
	 *            数据类型
	 * @param deviceMac
	 *            终端mac地址
	 */
	private void login(MessageEvent e, JSONObject jb, String jsonType, String deviceMac) {
		LoginVO lv = nettyService.login(jb, jsonType, deviceMac);
		RegistRsp rr = lv.getRegistRsp();
		String deviceName = JsonUtil.getDataFromJsonObject(jb, "deviceName");
		String areaNo = JsonUtil.getDataFromJsonObject(jb, "area");
		String ip = JsonUtil.getDataFromJsonObject(jb, "ip");
		String jsonString = JsonUtil.objectToJsonString(rr) + "\r\n";
		if (!(PubConstant.CLIENT_REGEIST_SUCCESS.equals(rr.getCode()))) {// 失败
			e.getChannel().write(jsonString);
			this.removeDataFromMap(e.getChannel().getId());
			e.getChannel().disconnect();
			e.getChannel().close();
			nettyService.loginOut(deviceMac);
			nettyService.saveClientLog(rr.getDeviceNo(), deviceMac, jsonType, deviceName, jsonString, areaNo, ip,
					PubConstant.DATA_FROM_SERVER);
			log.info("reply client[mac:" + deviceMac + ",deviceNo:" + (rr.getDeviceNo() == null ? "" : rr.getDeviceNo()) + "] login:"
					+ jsonString);
		} else {// 成功
			this.deleteOldSessionDataFromMapByMacAndNotUpdateDeviceStatus(deviceMac);
			String conflictMac = lv.getConflictMac();
			// 处理ip冲突的设备
			dealConflictIPDevice(conflictMac);
			saveInfoToMap(deviceMac, deviceNoMac_channel_map, channelId_mac_map, mac_deviceNoMac_map, e, rr.getDeviceNo());

			e.getChannel().write(jsonString);
			nettyService.saveClientLog(rr.getDeviceNo(), deviceMac, jsonType, deviceName, jsonString, areaNo, ip,
					PubConstant.DATA_FROM_SERVER);
			log.info("reply client[mac:" + deviceMac + ",deviceNo:" + (rr.getDeviceNo() == null ? "" : rr.getDeviceNo()) + "] login:"
					+ jsonString);
		}
		// 删除同一台设备注册阻塞操作
		CacheUtil.getInstance().removeKey(deviceMac);
	}

	/**
	 * 
	 * 方法的描述: 无房号信息关联的设备登录注册
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-11 上午8:32:09
	 * @param e
	 * @param jb
	 *            json对象
	 * @param jsonType
	 *            数据类型
	 * @param deviceMac
	 *            终端mac地址
	 */
	private void register(MessageEvent e, JSONObject jb, String jsonType, String deviceMac) {
		LoginVO lv = nettyService.register(jb, jsonType, deviceMac);
		RegistRsp rr = lv.getRegistRsp();
//		String deviceName = JsonUtil.getDataFromJsonObject(jb, "deviceName");
//		String areaNo = JsonUtil.getDataFromJsonObject(jb, "estateno");
//		String ip = JsonUtil.getDataFromJsonObject(jb, "ip");
		String jsonString = JsonUtil.objectToJsonString(rr) + "\r\n";
//		String password = StringUtil.randomNum(4);
		if (!(PubConstant.CLIENT_REGEIST_SUCCESS.equals(rr.getCode()))) {// 失败
			log.info("--------registration failed..."+rr.getCode());
			e.getChannel().write(jsonString);
			this.removeDataFromMap(e.getChannel().getId());
			e.getChannel().disconnect();
			e.getChannel().close();
			nettyService.loginOut(deviceMac);
//			nettyService.saveClientLog(rr.getDeviceNo(), deviceMac, jsonType, deviceName, jsonString, areaNo, ip,
//					PubConstant.DATA_FROM_SERVER);
//			log.info("reply client[mac:" + deviceMac + ",deviceNo:" + (rr.getDeviceNo() == null ? "" : rr.getDeviceNo()) + "] login:"
//					+ jsonString);
		} else {// 成功
			this.deleteOldSessionDataFromMapByMacAndNotUpdateDeviceStatus(deviceMac);
			nettyService.updateDeviceStatusByMac(PubConstant.ONLINE, deviceMac);
//			String conflictMac = lv.getConflictMac();
			// 处理ip冲突的设备
//			dealConflictIPDevice(conflictMac);
			saveInfoToMap(deviceMac, deviceNoMac_channel_map, channelId_mac_map, mac_deviceNoMac_map, e, rr.getDeviceNo());
			ChannelFuture future = e.getChannel().write(jsonString);
			log.info("--------registration success:[channelID:"+future.getChannel().getId()+", isSuccess:"+future.isSuccess()+", isDone:"+future.isDone()+"],server ack:"+jsonString);
//			nettyService.saveClientLog(rr.getDeviceNo(), deviceMac, jsonType, deviceName, jsonString, areaNo, ip,
//					PubConstant.DATA_FROM_SERVER);
//			log.info("reply client[mac:" + deviceMac + ",deviceNo:" + (rr.getDeviceNo() == null ? "" : rr.getDeviceNo()) + "] login:"
//					+ jsonString);
		}
		// 删除同一台设备注册阻塞操作
		CacheUtil.getInstance().removeKey(deviceMac);
	}

	
	private void dealConflictIPDevice(String conflictMac) {
		if (conflictMac != null) {
			if (conflictMac.contains("_")) {
				String[] mac = conflictMac.split("_");
				if (mac != null && mac.length > 0) {
					for (String _mac : mac) {
						deleteSessionDataFromMapByMac(_mac);
					}
				}
			}

		}
	}

	/**
	 * 
	 * 方法的描述: 根据MAC地址删除session数据，并更新设备状态
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-6 下午4:32:46
	 * @param mac
	 */
	private void deleteSessionDataFromMapByMac(String mac) {
		String deviceNoMac = mac_deviceNoMac_map.get(mac);
		deleteSessionDataFromMapByDeviceNoMac(deviceNoMac);
	}

	/**
	 * 
	 * 方法的描述: 根据MAC地址删除session数据但不更新设备状态
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-6 下午4:38:01
	 * @param mac
	 */
	private void deleteOldSessionDataFromMapByMacAndNotUpdateDeviceStatus(String mac) {
		String deviceNoMac = mac_deviceNoMac_map.get(mac);
		if (deviceNoMac != null) {
			Channel channel = deviceNoMac_channel_map.get(deviceNoMac);
			if (channel != null) {
				this.removeOldConnectionDataFromMap(channel.getId());
			}
		}
	}

	/**
	 * 
	 * 方法的描述: 根据设备编号和MAC地址删除session数据，并更新设备状态
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-6 下午4:28:09
	 * @param deviceNoMac
	 */
	private void deleteSessionDataFromMapByDeviceNoMac(String deviceNoMac) {
		if (deviceNoMac != null) {
			Channel channel = deviceNoMac_channel_map.get(deviceNoMac);
			if (channel != null) {
				this.removeDataFromMap(channel.getId());
			}
		}
	}

	public static void main(String[] arg) {
		String s = "a_b";
		String s1 = "a";
		String ss = "5b010a00230100000000000000000d88";
		System.out.println(ss.substring(10,12));
	}

	/**
	 * 
	 * 方法的描述: 一个设备一个长连接
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-11 上午8:31:13
	 * @param e
	 * @param deviceMac
	 */
	private void keepOneLongConnect(MessageEvent e, String mac) {

		if (mac_deviceNoMac_map.containsKey(mac)) {
			String deviceNo_mac = mac_deviceNoMac_map.get(mac);
			if (deviceNo_mac != null) {
				Channel oldChannel = deviceNoMac_channel_map.get(deviceNo_mac);
				Channel newChannel = e.getChannel();
				if (!oldChannel.getId().equals(newChannel.getId())) {
					nettyService.loginOut(mac);
					this.removeOldConnectionDataFromMap(oldChannel.getId());
					oldChannel.disconnect();
					oldChannel.close();
				}
			}
		}
	}

	/**
	 * 
	 * 方法的描述: 保存终端与连接信息
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-11 上午8:31:40
	 * @param mac
	 * @param macDeviceNoMap
	 * @param channelMacMap
	 * @param channelIDMacMap
	 * @param e
	 * @param deviceNo
	 */
	private void saveInfoToMap(String mac, Map<String, Channel> deviceNoMac_channel_map, Map<Integer, String> channelId_mac_map,
			Map<String, String> mac_deviceNoMac_map, MessageEvent e, String deviceNo) {
		if (deviceNo != null && mac != null) {
			mac_time_heart_map.put(mac, new Date());
			if (mac_deviceNoMac_map.containsKey(mac)) {
				String deviceNoMac = mac_deviceNoMac_map.get(mac);
				if (deviceNoMac != null) {
					if (deviceNoMac_channel_map.containsKey(deviceNoMac)) {
						Channel channel = deviceNoMac_channel_map.get(deviceNoMac);
						if (channel != null) {
							Integer channelId = channel.getId();
							if (channelId != null) {
								this.removeDataFromMap(channelId);
							}
							channel.disconnect();
							channel.close();
						}

					}

				}
			}
			deviceNoMac_channel_map.put(deviceNo + "_" + mac, e.getChannel());
			channelId_mac_map.put(e.getChannel().getId(), mac);
			mac_deviceNoMac_map.put(mac, deviceNo + "_" + mac);
		}
	}

	/**
	 * 
	 * 方法的描述: 清除旧session数据
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 下午10:57:23
	 * @param channelId
	 */
	private void removeOldConnectionDataFromMap(Integer channelId) {

		SessionUtil.removeOldConnnectionDataFromMap(channelId_mac_map, mac_deviceNoMac_map, deviceNoMac_channel_map,
				deviceNoMac_sentDataClient_map, mac_time_heart_map, nettyService, log, channelId);
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

		SessionUtil.removeDataFromMap(channelId_mac_map, mac_deviceNoMac_map, deviceNoMac_channel_map, deviceNoMac_sentDataClient_map,
				mac_time_heart_map, nettyService, log, channelId);
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		InetSocketAddress address = (InetSocketAddress) e.getChannel().getRemoteAddress();
		/*
		 * if(!isInitedDeviceStatusOutline) {
		 * log.info("System doesn't finished initized device status outline!");
		 * e.getChannel().disconnect(); e.getChannel().close();
		 * 
		 * }else { log.info("client:" + address.getAddress().getHostAddress() +
		 * "connect successfully!"); }
		 */
		boolean service_is_ok = CacheUtil.getInstance().serviceIsOK(PubConstant.TELNET_SERVICE_STATUS);
		if (!service_is_ok) {// service服务器异常或者没有启来，禁止所有设备登陆
			log.info("client:" + address.getAddress().getHostAddress() + "connect successfully,but service is exception!");
			e.getChannel().close();
		} else {
			// 初始化设备状态
			boolean initalAllDeviceStatus = CacheUtil.getInstance().initalAllDevicesIsOK(PubConstant.INITAL_ALL_DEVICE);
			if (!initalAllDeviceStatus) {// 没有初始化完成所有设备状态，禁止所有设备登陆
				e.getChannel().close();
			} else {// service服务器启来，并初始化完成所有设备状态，设备可以正常登陆
				log.info("client:" + address.getAddress().getHostAddress() + "connect successfully!");
			}
		}
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {

		InetSocketAddress address = (InetSocketAddress) e.getChannel().getRemoteAddress();

		// System.out.println("终端:" + address.getAddress().getHostAddress());

		super.channelDisconnected(ctx, e);
		removeDataFromMap(e.getChannel().getId());
		log.info("client:" + address.getAddress().getHostAddress() + " disconnect..." + ",channelId:" + e.getChannel().getId());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		InetSocketAddress address = (InetSocketAddress) e.getChannel().getRemoteAddress();
		removeDataFromMap(e.getChannel().getId());
		e.getChannel().close();
		log.info("client:" + address.getAddress().getHostAddress() + " exception:" + e.getCause() + ",channelId:" + e.getChannel().getId());
		log.info("**************exception:" + e);
	}

	@Override
	public void childChannelClosed(ChannelHandlerContext ctx, ChildChannelStateEvent e) throws Exception {

		super.childChannelClosed(ctx, e);
		InetSocketAddress address = (InetSocketAddress) e.getChannel().getRemoteAddress();

		// System.out.println("终端:" + address.getAddress().getHostAddress());
		removeDataFromMap(e.getChannel().getId());

		log.info("client:" + address.getAddress().getHostAddress() + " close..." + ",channelId:" + e.getChannel().getId());
	}

	public NettyService getNettyService() {
		return nettyService;
	}

	public void setNettyService(NettyService nettyService) {
		this.nettyService = nettyService;
	}

	private void initDevice(){
		System.out.println("执行...");
		while(true){
			boolean service_is_ok = CacheUtil.getInstance().serviceIsOK(PubConstant.TELNET_SERVICE_STATUS);
			if (service_is_ok) {
				System.out.println("service_is_ok...");
				// 初始化设备状态
				boolean initalAllDeviceStatus = CacheUtil.getInstance().initalAllDevicesIsOK(PubConstant.INITAL_ALL_DEVICE);
				// 如果没有完成初始所有设备状态
				if (!initalAllDeviceStatus){
					System.out.println("inital all device outline status:" + initalAllDeviceStatus);
					try{
						System.out.println("初始化所有设备状态...");
						// 初始化所有设备状态
						nettyService.initialDeviceStatus(PubConstant.OUTLINE);
						System.out.println("设置为初始化完成标志位...");
						// 设置为初始化完成标志位
						CacheUtil.getInstance().addCache(PubConstant.INITAL_ALL_DEVICE, PubConstant.INITAL_ALL_DEVICE_STATUS);
						break;
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			System.out.println("service_is_not_ok...");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//处理透传数据
	private void transData(String data, String mac, MessageEvent e) throws Exception {
		log.info("transData -> " + mac);
		Boolean receiveFlag = true;
		//判断非透传数据则返回
		if (!checkTransData(data)) {
			return;
		}
		Map<String, Object> mp = new HashMap<String, Object>();
		// str[0]=receiveFlag+"";
		mp.put("code", receiveFlag);
		String status = null;
		//报警器帧头
		String pref = data.substring(0,6);
		//插座帧头
		// 5b0200设置设防状态,5b0100查询设防状态,5b0404 MCU主动返回设防状态（定时预约）
		if (pref.equalsIgnoreCase("5b0200") || pref.equalsIgnoreCase("5b0404")) {
			// 截取设防状态：0设防，1撤防，2在家设防;
			status = data.substring(7, 8);
			nettyService.pushDefenceStatusChangedThread(status, mac);
		}
		else if (pref.equalsIgnoreCase("5b0100")) {
			// 截取设防状态：0设防，1撤防，2在家设防;
			status = data.substring(7, 8);
		}
		//版本查询帧头
		else if (pref.equalsIgnoreCase("5b010a")) {
			// 截取设防状态：00旧版本，01新版本;
			status = data.substring(10, 12);
		}
		//插座电源和灯的开关状态
		else if(Constant.CMD_OUTLET_POWER_PREFIX.equalsIgnoreCase(pref)
				||Constant.CMD_OUTLET_LIGHT_PREFIX.equalsIgnoreCase(pref)
				||Constant.CMD_OUTLET_ALL_PREFIX.equalsIgnoreCase(pref)){
			status = data.substring(7, 8);
		}
		//查询全部状态
		else if("6b0207".equalsIgnoreCase(pref)){
			status = data.substring(7, 8) +","+ data.substring(9, 10) +","+ data.substring(11, 12);
		}
		else if (pref.equalsIgnoreCase(Constant.CMD_ACCESSORIES_ADD_PREFIX)) {
			nettyService.saveDeviceAccessories(data, mac);
		}
		else if (pref.equalsIgnoreCase(Constant.CMD_ALARM_ADD_PREFIX)) {
			nettyService.saveAlarm(data, mac);
		}
		else if (pref.equalsIgnoreCase(Constant.CMD_ACCESSORIES_REFRESH_PREFIX)) {
			ResultEntity<String> res = nettyService.refreshAccessories(data,mac);
			System.out.println("res:" + res);
		}
		else if (pref.equalsIgnoreCase(Constant.CMD_ACCESSORIES_CLEAR_PREFIX)) {
			nettyService.clearDeviceAccessories(mac);
		}
		//json字符串数据，包括wifi列表，设置wifi等
		else if(data.startsWith("{")){
			status = data;
		}
		if(status!=null){
			mp.put("data", status);
		}
		// 将channelid写到缓存中，使nettyService 中的send方法可以判断是否有返回
		CacheUtil.getInstance().addCache(e.getChannel().getId().toString(),mp);
		//log.info("[addCache channelId:" + e.getChannel().getId().toString() + "]:" + mp);
		
		BaseVO vo = new BaseVO();
		vo.setCode(BaseVO.SUCCESS);
		vo.setJsonType("trans");
		String jsonString = JsonUtil.objectToJsonString(vo) + "\r\n";
		ChannelFuture future = e.getChannel().write(jsonString);
		log.info("----trans result:[channelId:" + e.getChannel().getId().toString() + ", isSuccess:" + future.isSuccess() + ", isDone:" + future.isDone()
				+ "],server ack:" + jsonString);
	}
	// 处理升级数据
	private void transUpdateData(String data, String mac, MessageEvent e)
			throws Exception {
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("code", true);
		mp.put("data", "update");
		// 将channelid写到缓存中，使nettyService 中的send方法可以判断是否有返回
		CacheUtil.getInstance().addCache(e.getChannel().getId().toString(), mp);
		log.info("[addCache channelId:" + e.getChannel().getId().toString()
				+ "]:" + mp);

		BaseVO vo = new BaseVO();
		vo.setCode(BaseVO.SUCCESS);
		vo.setJsonType("update");
		String jsonString = JsonUtil.objectToJsonString(vo) + "\r\n";
		ChannelFuture future = e.getChannel().write(jsonString);
		log.info("--------update result:[channelId:"
				+ e.getChannel().getId().toString() + ", isSuccess:"
				+ future.isSuccess() + ", isDone:" + future.isDone()
				+ "],server ack:" + jsonString);
	}
	
	// 处理开锁数据
	private void transUnlockData(String data, MessageEvent e)
			throws Exception {
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("code", true);
		mp.put("data", "unlock");
		// 将channelid写到缓存中，使nettyService 中的send方法可以判断是否有返回
		CacheUtil.getInstance().addCache(e.getChannel().getId().toString(), mp);
		log.info("[addCache channelId:" + e.getChannel().getId().toString()
				+ "]:" + mp);

		BaseVO vo = new BaseVO();
		vo.setCode(BaseVO.SUCCESS);
		vo.setJsonType("unlock");
		String jsonString = JsonUtil.objectToJsonString(vo) + "\r\n";
		ChannelFuture future = e.getChannel().write(jsonString);
		log.info("--------unlock result:[channelId:"
				+ e.getChannel().getId().toString() + ", isSuccess:"
				+ future.isSuccess() + ", isDone:" + future.isDone()
				+ "],server ack:" + jsonString);
	}
	
	/**
	 * 判断透传数据是否格式正确
	 * @param data
	 * @return
	 */
	private boolean checkTransData(String data){
		if(StringUtils.isBlank(data)){
			return false;
		}
		if("success".equals(data)){
			return true;
		}
		//过滤掉升级主动返回的数据
		if(data.substring(0,4).equalsIgnoreCase("5b0f")){
			return false;
		}
		//如果是json格式的数据，则返回true
		if(data.startsWith("{")&& data.endsWith("}")){
			JSONObject jsonObj = JsonUtil.jsonStringToJsonObject(data);
			if(jsonObj!=null){
				return true;
			}
		} 
		else if(!data.endsWith("88")){
			return false;
		}else {
			String prefix = data.substring(0,2);
			//报警器帧头
			if(prefix.equalsIgnoreCase("5b") || prefix.equalsIgnoreCase("5c")){
				return true;
			}
			//wifi插座帧头
			if(prefix.equalsIgnoreCase("6b")){
				return true;
			}
		}
		return false;
	}
}
