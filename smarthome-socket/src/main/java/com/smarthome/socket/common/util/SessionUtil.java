package com.smarthome.socket.common.util;

import java.util.Date;
import java.util.Map;

import org.jboss.netty.channel.Channel;
import org.slf4j.Logger;

import com.smarthome.socket.service.netty.service.NettyService;

/**
 * 
 * 类名称：SessionUtil 类描述： session工具类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-9-25 下午4:26:13
 */
public class SessionUtil {
	/**
	 * 
	 * 方法的描述: 删除session数据
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-9-25 下午4:25:59
	 * @param channelId_mac_map
	 * @param mac_deviceNoMac_map
	 * @param deviceNoMac_channel_map
	 * @param deviceNoMac_sentDataClient_map
	 * @param nettyService
	 * @param log
	 * @param channelId
	 */
	public static void removeDataFromMap(Map<Integer, String> channelId_mac_map, Map<String, String> mac_deviceNoMac_map,
			Map<String, Channel> deviceNoMac_channel_map, Map<String, Long> deviceNoMac_sentDataClient_map,
			Map<String, Date> mac_time_heart_map, NettyService nettyService, Logger log, Integer channelId) {

		if (channelId_mac_map.containsKey(channelId)) {
			String mac = channelId_mac_map.get(channelId);
			if (mac != null) {
				deleteSession(channelId_mac_map, mac_deviceNoMac_map, deviceNoMac_channel_map, deviceNoMac_sentDataClient_map,
						mac_time_heart_map, log, channelId, mac);
				nettyService.loginOut(mac);
			}

		}

	}
	public static void removeOldConnnectionDataFromMap(Map<Integer, String> channelId_mac_map, Map<String, String> mac_deviceNoMac_map,
			Map<String, Channel> deviceNoMac_channel_map, Map<String, Long> deviceNoMac_sentDataClient_map,
			Map<String, Date> mac_time_heart_map, NettyService nettyService, Logger log, Integer channelId) {

		if (channelId_mac_map.containsKey(channelId)) {
			String mac = channelId_mac_map.get(channelId);
			if (mac != null) {
				deleteSession(channelId_mac_map, mac_deviceNoMac_map, deviceNoMac_channel_map, deviceNoMac_sentDataClient_map,
						mac_time_heart_map, log, channelId, mac);				
			}

		}

	}
	/**
	 * 
	 * 方法的描述: 删除session
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-9-28 下午3:30:52
	 * @param channelId_mac_map
	 * @param mac_deviceNoMac_map
	 * @param deviceNoMac_channel_map
	 * @param deviceNoMac_sentDataClient_map
	 * @param mac_time_heart_map
	 * @param nettyService
	 * @param log
	 * @param channelId
	 */

	private static void deleteSession(Map<Integer, String> channelId_mac_map, Map<String, String> mac_deviceNoMac_map,
			Map<String, Channel> deviceNoMac_channel_map, Map<String, Long> deviceNoMac_sentDataClient_map,
			Map<String, Date> mac_time_heart_map, Logger log, Integer channelId, String mac) {
		String deviceNo = "";
		if (mac != null) {
			String deviceNo_mac = mac_deviceNoMac_map.get(mac);
			if (deviceNo_mac != null) {
				//deviceNo = deviceNo_mac.split("_")[0];
				if (deviceNoMac_channel_map.containsKey(deviceNo_mac)) {
					deviceNoMac_channel_map.remove(deviceNo_mac);
				}
				if (deviceNoMac_sentDataClient_map.containsKey(deviceNo_mac)) {
					deviceNoMac_sentDataClient_map.remove(deviceNo_mac);
				}

			}
			mac_deviceNoMac_map.remove(mac);
			mac_time_heart_map.remove(mac);
		}
		channelId_mac_map.remove(channelId);
		CacheUtil.getInstance().removeKey(channelId.toString());
		log.info("client[channelId:"+channelId+", mac: " + mac + "]:destroy session data..........");
		// nettyService.loginOut(mac);
	}
}
