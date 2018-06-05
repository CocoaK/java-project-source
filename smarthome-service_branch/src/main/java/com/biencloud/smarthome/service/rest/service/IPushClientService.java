package com.biencloud.smarthome.service.rest.service;

import java.util.List;
import java.util.Map;

import cn.jpush.api.JPushClient;

import com.biencloud.smarthome.service.rest.model.PushClient;

/**
 * 推送服务
 * @author Cocoa
 *
 */
public interface IPushClientService{
	
	/**
	 * 发送IOS推送通知
	 * @param alert
	 * @param tokens
	 * @param badge  角标数
	 */
	void sendIosPush(String alert,String sound,List<String> tokens, int badge);
	
	/**
	 * 发送极光推送通知
	 * @param alert	 //推送消息
	 * @param title	 //标题
	 * @param extras 参见极光推送，此处留空
	 * @param registrationIds 推送目标id
	 */
//	void sendAndroidJpush(String alert,String title,Map<String,String> extras,List<String> registrationIds, JPushClient jPushClient);
	
	/**
	 * 发送极光推送通知
	 * @param alert	 //推送消息
	 * @param title	 //标题
	 * @param sound 音乐声:abc.wav
	 * @param registrationIds 推送目标id
	 */
	void sendAndroidJpush(String alert,String title,String sound,List<String> registrationIds,JPushClient jPushClient);

	/**
	 * 推送通用方法
	 * @param pushClient
	 */
	void pushToClient(PushClient pushClient);

	/**
	 * 推送设防状态改变信息
	 * @param pushClient
	 */
	void pushDefenceStatusToClient(PushClient pushClient);
	
	/**
	 * 发送IOS推送通知
	 * @param alert
	 * @param status
	 * @param tokens
	 */
	void sendIosPushWithDefenceStatus(String alert,Map<String,String> map,List<String> tokens);

	/**
	 * 发送IOS推送通知(1byone证书)
	 * @param alert
	 * @param tokens
	 * @param badge  角标数
	 */
	void sendIosPush1byone(String alert, String sound, List<String> tokens, int badge);

	/**
	 * 发送IOS推送通知(1byone证书)
	 * @param alert
	 * @param status
	 * @param tokens
	 */
	void sendIosPushWithDefenceStatus1byone(String alert, Map<String,String> map,List<String> tokens);
	
}
