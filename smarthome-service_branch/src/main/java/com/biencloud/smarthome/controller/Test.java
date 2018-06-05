package com.biencloud.smarthome.controller;

import java.util.Map;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class Test {
//	public static void main(String[] args) throws APIConnectionException, APIRequestException{
//		
//		JPushClient jp = new JPushClient("5ddfd535348bb62900847b3c","e6095bbd178e75ad6f83226e");
//		//JPushClient j = new JPushClient();
//		
//        String title = "Alarm";
//        String alert = "门磁报警";
//        Map<String,String> extras = null;
//        //jp.sendAndroidNotificationWithRegistrationID(title, alert, extras, registrationID);
//        PushPayload pp = PushPayload.newBuilder()
//        		.setPlatform(Platform.all())//设置接受的平台  
//        		//.setAudience(Audience.all())
//                .setAudience(Audience.registrationId("1104a89792a95c1d5a7"))//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到  
//                .setNotification(Notification.android(alert, title, extras))  .build();
//        
//        System.out.println("------------:"+jp.sendPush(pp));
//	}

}
