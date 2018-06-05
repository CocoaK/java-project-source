package com.smarthome.socket.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.netty.channel.Channel;

import com.smarthome.socket.common.constant.PubConstant;

/**
 * 
 * 类名称：CacheUtil 类描述： 缓存工具类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-10-31 下午3:17:14
 */
public class CacheUtil {
	private static CacheUtil uniqueInstance = null;
	private static Map<String, Object> map = new HashMap<String, Object>();
	// 设备编号与设备MAC对应关系,key由deviceNo_mac组成
	private static Map<String, Channel> deviceNoMac_channel_map = new HashMap<String, Channel>();
	// 设备Mac与deviceNo_mac关系
	private static Map<String, String> mac_deviceNoMac_map = new HashMap<String, String>();

	// 连接id与设备Mac的关系
	private static Map<Integer, String> channelId_mac_map = new HashMap<Integer, String>();
	// 设备MAC与发送数据ID的关系,key由deviceNo_mac组成
	private static Map<String, Long> deviceNoMac_sentDataClient_map = new HashMap<String, Long>();
	// 心跳时间
	private static Map<String, Date> mac_time_heart_map = new ConcurrentHashMap<String, Date>();

	private CacheUtil() {

	}

	public static CacheUtil getInstance() {
		if (uniqueInstance == null) {

			uniqueInstance = new CacheUtil();

		}

		return uniqueInstance;

	}

	/**
	 * 
	 * 方法的描述: 判断是否包含key
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-6 下午5:20:04
	 * @param key
	 * @return 包含为true,否则为false
	 */
	public boolean isContainKey(String key) {
		boolean flag = false;
		if (map.containsKey(key)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * 方法的描述: 根据key,value保存到缓存中
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-10-31 下午3:18:06
	 * @param key
	 * @param value
	 */
	public void addCache(String key, Object value) {

		map.put(key, value);

	}

	/**
	 * 
	 * 方法的描述: 清空map
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午10:32:15
	 */
	public void clearCache() {
		map.clear();

	}

	/**
	 * 
	 * 方法的描述: 添加key,value到map，设备编号与设备MAC对应关系,key由deviceNo_mac组成
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:07:53
	 * @param key
	 * @param value
	 */
	public void addDeviceNoMac_channel_map(String key, Channel value) {
		deviceNoMac_channel_map.put(key, value);
	}

	/**
	 * 
	 * 方法的描述:根据key,获得value
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:09:56
	 * @param key
	 * @return
	 */
	public Channel getValueFromDeviceNoMac_channel_map(String key) {
		return deviceNoMac_channel_map.get(key);
	}

	/**
	 * 
	 * 方法的描述: 从deviceNoMac_channel_map中删除key
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:29:21
	 * @param key
	 */
	public void removeKeyFromDeviceNoMac_channel_map(String key) {
		if (deviceNoMac_channel_map.containsKey(key)) {
			deviceNoMac_channel_map.remove(key);
		}
	}

	/**
	 * 
	 * 方法的描述: 清空deviceNoMac_channel_map
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:31:26
	 */
	public void clearDeviceNoMac_channel_map() {
		deviceNoMac_channel_map.clear();
	}

	/**
	 * 
	 * 方法的描述: 添加key,value到map,设备Mac与deviceNo_mac关系
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:12:40
	 * @param key
	 * @param value
	 */
	public void addMac_deviceNoMac_map(String key, String value) {
		mac_deviceNoMac_map.put(key, value);
	}

	/**
	 * 
	 * 方法的描述: 根据key,获得value,value为mac与设备编号mac组合关系
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:14:14
	 * @param key
	 * @return
	 */
	public String getValueFromMac_deviceNoMac_map(String key) {
		return mac_deviceNoMac_map.get(key);
	}

	/**
	 * 
	 * 方法的描述: 从mac_deviceNoMac_map删除key
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:33:00
	 * @param key
	 */
	public void removeKeyFromMac_deviceNoMac_map(String key) {
		if (mac_deviceNoMac_map.containsKey(key)) {
			mac_deviceNoMac_map.remove(key);
		}
	}

	/**
	 * 
	 * 方法的描述: 清空mac_deviceNoMac_map
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:34:02
	 */
	public void clearMac_deviceNoMac_map() {
		mac_deviceNoMac_map.clear();
	}

	/**
	 * 
	 * 方法的描述: 添加key,value到map,连接id与设备Mac的关系
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:17:04
	 * @param key
	 * @param value
	 */
	public void addChannelId_mac_map(Integer key, String value) {
		channelId_mac_map.put(key, value);
	}

	/**
	 * 
	 * 方法的描述:根据key,获得value,value为连接id与设备Mac的关系
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:18:44
	 * @param key
	 * @return
	 */
	public String getValueFromChannelId_mac_map(Integer key) {
		return channelId_mac_map.get(key);
	}

	/**
	 * 
	 * 方法的描述: 从channelId_mac_map中删除key
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:35:29
	 * @param key
	 */
	public void removeKeyFromChannelId_mac_map(Integer key) {
		if (channelId_mac_map.containsKey(key)) {
			channelId_mac_map.remove(key);
		}
	}

	/**
	 * 
	 * 方法的描述: 清空channelId_mac_map
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:36:27
	 */
	public void clearChannelId_mac_map() {
		channelId_mac_map.clear();
	}

	/**
	 * 
	 * 方法的描述: 添加key,value到map,设备MAC与发送数据ID的关系,key由deviceNo_mac组成
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:21:15
	 * @param key
	 * @param value
	 */
	public void addDeviceNoMac_sentDataClient_map(String key, Long value) {
		deviceNoMac_sentDataClient_map.put(key, value);
	}

	/**
	 * 
	 * 方法的描述: 根据key,获得value,value为设备MAC与发送数据ID的关系
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:23:17
	 * @param key
	 * @return
	 */
	public Long getValueFromDeviceNoMac_sentDataClient_map(String key) {
		return deviceNoMac_sentDataClient_map.get(key);
	}

	/**
	 * 
	 * 方法的描述: 从deviceNoMac_sentDataClient_map删除key
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:37:43
	 * @param key
	 */
	public void removeKeyFromDeviceNoMac_sentDataClient_map(String key) {
		if (deviceNoMac_sentDataClient_map.containsKey(key)) {
			deviceNoMac_sentDataClient_map.remove(key);
		}
	}

	/**
	 * 
	 * 方法的描述: 清空deviceNoMac_sentDataClient_map
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:38:46
	 */
	public void clearDeviceNoMac_sentDataClient_map() {
		deviceNoMac_sentDataClient_map.clear();
	}

	/**
	 * 
	 * 方法的描述: 添加key,value到map,保存mac与心跳时间关系
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:25:24
	 * @param key
	 * @param value
	 */
	public void addMac_time_heart_map(String key, Date value) {
		mac_time_heart_map.put(key, value);
	}

	/**
	 * 
	 * 方法的描述: 根据key,获得value,value为设备心跳时间
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:27:03
	 * @param key
	 * @return
	 */
	public Date getValueFromMac_time_heart_map(String key) {
		return mac_time_heart_map.get(key);
	}

	/**
	 * 
	 * 方法的描述: 从mac_time_heart_map中删除key
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:40:30
	 * @param key
	 */
	public void removeKeyFromMac_time_heart_map(String key) {
		if (mac_time_heart_map.containsKey(key)) {
			mac_time_heart_map.remove(key);
		}
	}

	/**
	 * 
	 * 方法的描述: 清空mac_time_heart_map
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午9:41:17
	 */
	public void clearMac_time_heart_map() {
		mac_time_heart_map.clear();
	}

	/**
	 * 
	 * 方法的描述: 从缓存中取值
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-10-31 下午3:18:41
	 * @param key
	 * @return
	 */
	public Object getValue(String key) {

		Object obj = map.get(key);

		return obj;

	}

	/**
	 * 
	 * 方法的描述: 删除key
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-10-31 下午4:11:57
	 * @param key
	 */
	public void removeKey(String key) {

		if (map.containsKey(key)) {
			map.remove(key);
		}

	}

	/**
	 * 
	 * 方法的描述: 判断service服务器是否正常
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-10-31 下午4:29:47
	 * @param key
	 * @return 正常为true,否则为false
	 */
	public boolean serviceIsOK(String key) {

		boolean serice_is_ok = false;
		Object obj = map.get(key);
		if (obj != null) {
			String serviceStatus = (String) obj;
			if (PubConstant.TELNET_SERVICE_STATUS_SUCCESS.equals(serviceStatus)) {
				serice_is_ok = true;
			}
		}
		return serice_is_ok;
	}

	/**
	 * 
	 * 方法的描述: 初始化所有设备状态
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-1 上午10:12:11
	 * @param key
	 * @return true表示初始成功，false表示失败
	 */
	public boolean initalAllDevicesIsOK(String key) {

		boolean inital_is_ok = false;
		Object obj = map.get(key);
		if (obj != null) {
			String initalStatus = (String) obj;
			if (PubConstant.INITAL_ALL_DEVICE_STATUS.equals(initalStatus)) {
				inital_is_ok = true;
			}
		}
		return inital_is_ok;
	}

	/**
	 * 
	 * 方法的描述: 清空所有map
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午10:29:41
	 */
	public void clearAllMaps() {
		clearCache();
		clearChannelId_mac_map();
		clearDeviceNoMac_channel_map();
		clearDeviceNoMac_sentDataClient_map();
		clearMac_deviceNoMac_map();
		clearMac_time_heart_map();
		System.out.println("清除缓存...");
	}
    /**
     * 
     * 方法的描述: 所有map大小
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-12-6 上午10:45:08
     */
	public void getAllMapsSize() {
		System.out.println("map.size:" + map.size() + ",deviceNoMac_channel_map.size:" + deviceNoMac_channel_map.size()
				+ ",mac_deviceNoMac_map.size:" + mac_deviceNoMac_map.size() + ",channelId_mac_map.size:" + channelId_mac_map.size()
				+ ",deviceNoMac_sentDataClient_map.size:" + deviceNoMac_sentDataClient_map.size() + ",mac_time_heart_map.size:"
				+ mac_time_heart_map.size());
	}

	public Map<String, Channel> getDeviceNoMac_channel_map() {
		return deviceNoMac_channel_map;
	}

	public Map<String, String> getMac_deviceNoMac_map() {
		return mac_deviceNoMac_map;
	}

	public Map<Integer, String> getChannelId_mac_map() {
		return channelId_mac_map;
	}

	public Map<String, Long> getDeviceNoMac_sentDataClient_map() {
		return deviceNoMac_sentDataClient_map;
	}

	public Map<String, Date> getMac_time_heart_map() {
		return mac_time_heart_map;
	}

}
