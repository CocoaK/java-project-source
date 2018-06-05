package com.biencloud.smarthome.service.rest.service.impl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import bsh.This;
import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.rest.model.MsgToken;
import com.biencloud.smarthome.service.rest.model.PushClient;
import com.biencloud.smarthome.service.rest.service.IDevicesService;
import com.biencloud.smarthome.service.rest.service.IMsgTokenService;
import com.biencloud.smarthome.service.rest.service.IPushClientService;

/**
 * 推送服务实现类
 * @author Cocoa
 *
 */
@Service
public class PushClientService implements IPushClientService{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private IMsgTokenService msgTokenService;
	@Autowired
	private IDevicesService devicesService;
	//IOS默认推送铃声
	String defaultSound = "default";
	
	/** IOS推送证书路径*/
    private String certPath = This.class.getClassLoader().getResource(Constants.IOS_PUSH_CERT_NAME).getPath();
    /** IOS推送证书路径-1byone*/
    private String certPath_1byone = This.class.getClassLoader().getResource(Constants.IOS_PUSH_CERT_NAME_1BYONE).getPath();
    
	@Override
	public void sendIosPush(final String alert,final String sound, final List<String> tokens, final int badge) {
		Thread t = new Thread(  
                new Thread(){  
                    @Override  
                    public void run() {  
                    	sendIosPushByCert(alert,sound, tokens, badge, certPath);
                    }  
                }  
        );
		t.start();
	}
	
	@Override
	public void sendIosPush1byone(final String alert, final String sound, final List<String> tokens, final int badge) {
		Thread t = new Thread(  
                new Thread(){  
                    @Override  
                    public void run() {  
                    	sendIosPushByCert(alert,sound, tokens, badge, certPath_1byone);
                    }  
                }  
        );
		t.start();
	}
	
	@Override
	public void sendIosPushWithDefenceStatus(final String alert, final Map<String,String> map,final List<String> tokens) {
		Thread t = new Thread(  
                new Thread(){  
                    @Override  
                    public void run() {  
                    	sendIosPushWithDefenceStatusByCert(alert, map, tokens, certPath);
                    }  
                }  
        );
		t.start();
		
	}
	
	@Override
	public void sendIosPushWithDefenceStatus1byone(final String alert, final Map<String,String> map,final List<String> tokens) {
		Thread t = new Thread(  
                new Thread(){  
                    @Override  
                    public void run() {  
                    	sendIosPushWithDefenceStatusByCert(alert, map, tokens, certPath_1byone);
                    }  
                }  
        );
		t.start();
		
	}

	public void sendAndroidJpush(String alert, String title,
			Map<String, String> extras, List<String> registrationIds,JPushClient jPushClient) {
		//JPushClient jp = new JPushClient(Constants.PUSH_MASTER_SECRET_JPUSH,Constants.PUSH_APP_KEY_JPUSH);
        PushPayload pp = PushPayload.newBuilder()
        		.setPlatform(Platform.all())//设置接受的平台  
        		.setAudience(Audience.registrationId(registrationIds))//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                .setNotification(Notification.android(alert, title, extras))  .build();
        System.out.println();
        System.out.println("registrationIds:"+registrationIds);
        try {
        	jPushClient.sendPush(pp);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void pushToClient(PushClient pushClient){
		if(pushClient != null){
			//主设备的ID
			List<MsgToken> list = msgTokenService.getListByDeviceId(pushClient.getDeviceId());
			if(list!=null && list.size()>0){
				//安卓skeeper推送列表
				List<String> tokensAndroidJpush = new ArrayList<String>();
				//安卓1byone推送列表
				List<String> tokensAndroidJpush1byone = new ArrayList<String>();
				//安卓skeeper GCM推送列表
				List<String> tokensAndroidGcm = new ArrayList<String>();
				//IOS skeeper推送列表
				List<String> tokensIosApns = new ArrayList<String>();
				//IOS 1byone推送列表
				List<String> tokensIosApns1byone = new ArrayList<String>();
				for(MsgToken token : list){
					//排除在外token不发推送
					if(token.getToken()!=null && !token.getToken().equals(pushClient.getExcludeToken())){
						if(MsgToken.TYPE_ANDROID_JPUSH.equals(token.getType())){
							tokensAndroidJpush.add(token.getToken());
						}
						if(MsgToken.TYPE_ANDROID_JPUSH_1BYONE.equals(token.getType())){
							tokensAndroidJpush1byone.add(token.getToken());
						}
						if(MsgToken.TYPE_ANDROID_GCM.equals(token.getType())){
							tokensAndroidGcm.add(token.getToken());
						}
						if(MsgToken.TYPE_IOS_APNS.equals(token.getType())){
							tokensIosApns.add(token.getToken());
						}
						if(MsgToken.TYPE_IOS_APNS_1BYONE.equals(token.getType())){
							tokensIosApns1byone.add(token.getToken());
						}
					}
				}
				String title = pushClient.getTitle();
				String status = pushClient.getStatus();
				String deviceId = pushClient.getDeviceId().toString();
				StringBuilder sb = new StringBuilder();
				sb.append(pushClient.getContent1());
				sb.append(pushClient.getContent2());
				sb.append(pushClient.getContent3());
				sb.append(pushClient.getContent4());
				Map<String,String> map = new HashMap<String,String>();
				//System.out.println("##########################tokensIosApns:"+tokensIosApns.size());
				map.put("status", status);
				map.put("deviceId", deviceId);
				if(tokensAndroidJpush.size()>0){
					JPushClient jPushClient = new JPushClient(Constants.PUSH_MASTER_SECRET_JPUSH,Constants.PUSH_APP_KEY_JPUSH);
					this.sendAndroidJpush(sb.toString(),title,map,tokensAndroidJpush,jPushClient);
				}
				if(tokensAndroidJpush1byone.size()>0){
					JPushClient jPushClient = new JPushClient(Constants.PUSH_MASTER_SECRET_JPUSH_1BYONE,Constants.PUSH_APP_KEY_JPUSH_1BYONE);
					this.sendAndroidJpush(sb.toString(),title,map,tokensAndroidJpush1byone,jPushClient);
				}
				if(tokensIosApns.size()>0){
					this.sendIosPushWithDefenceStatus(sb.toString(),map, tokensIosApns);
				}
				if(tokensIosApns1byone.size()>0){
					this.sendIosPushWithDefenceStatus1byone(sb.toString(),map, tokensIosApns1byone);
				}
			}
		}
	}
	
	@Override
	public void pushDefenceStatusToClient(final PushClient pushClient){
		if(pushClient!=null){
			com.biencloud.smarthome.service.rest.model.Device device = devicesService.getForOne(pushClient.getDeviceId().intValue());
			pushClient.setContent1("[");
			pushClient.setContent2(device.getDeviceName());
			pushClient.setContent3("]");
			pushClient.setContent4(" status changed!");
			new Thread(new Runnable(){
				public void run(){
					pushToClient(pushClient);
				}
			}).start();
			//pushToClient(pushClient);
		}
	}
	
	private void sendIosPushByCert(String alert,String sound, List<String> tokens, int badge,String path) {
		try{
            PushNotificationPayload payLoad = new PushNotificationPayload();
            payLoad.addAlert(alert); // 消息内容
            payLoad.addBadge(badge); // iphone应用图标上小红圈上的数值
           // payLoad.addCustomAlertBody("噢嘿嘿~捏嘻嘻~哇咔咔咔~");
            //设防状态
            if (!StringUtils.isBlank(sound)){
                payLoad.addSound(sound);//铃音
            }else{
            	 payLoad.addSound(defaultSound);//默认铃音
            }
            PushNotificationManager pushManager = new PushNotificationManager();
            //此处注意true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
            pushManager.initializeConnection(new AppleNotificationServerBasicImpl(URLDecoder.decode(path,"utf-8"), Constants.IOS_PUSH_CERT_PASSWD, Constants.IS_PUSH_PRODUCT));
            List<PushedNotification> notifications = new ArrayList<PushedNotification>();
            // 发送push消息
            List<Device> device = new ArrayList<Device>();
            //打印调试数据,测试完后删掉
            List<String> debug = new ArrayList<String>();
            if(tokens!=null && tokens.size()>0){
            	//去重复
            	List<String> tks = new ArrayList<String>(new HashSet<String>(tokens));
            	for (String token : tks){
                    device.add(new BasicDevice(token));
                    debug.add(token.substring(token.length()-5));
                }
            }
            notifications = pushManager.sendNotifications(payLoad, device);
            
            List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
            List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
            int failed = failedNotifications.size();
            int successful = successfulNotifications.size();
            System.out.println("IOS push count ["+tokens.size()+"],successful:["+successful+"] failed:["+failed+"] token:"+debug);
            pushManager.stopConnection();
        }
        catch (Exception e){
            e.printStackTrace();
        }
	}
	
	private void sendIosPushWithDefenceStatusByCert(String alert, Map<String,String> map,List<String> tokens,String path) {
		try{
            PushNotificationPayload payLoad = new PushNotificationPayload();
            payLoad.addAlert(alert); // 消息内容
            //payLoad.addBadge(badge); // iphone应用图标上小红圈上的数值
           // payLoad.addCustomAlertBody("噢嘿嘿~捏嘻嘻~哇咔咔咔~");
            //设防状态
            payLoad.addCustomDictionary("status", map.get("status"));
            payLoad.addCustomDictionary("deviceId", map.get("deviceId"));
            payLoad.addSound(defaultSound);//铃音
            PushNotificationManager pushManager = new PushNotificationManager();
            //此处注意true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
            pushManager.initializeConnection(new AppleNotificationServerBasicImpl(URLDecoder.decode(path,"utf-8"), Constants.IOS_PUSH_CERT_PASSWD, Constants.IS_PUSH_PRODUCT));
            List<PushedNotification> notifications = new ArrayList<PushedNotification>();
            // 发送push消息
            List<Device> device = new ArrayList<Device>();
            //打印调试数据,测试完后删掉
            List<String> debug = new ArrayList<String>();
            if(tokens!=null && tokens.size()>0){
            	//去重复
            	List<String> tks = new ArrayList<String>(new HashSet<String>(tokens));
            	for (String token : tks){
                    device.add(new BasicDevice(token));
                    debug.add(token.substring(token.length()-5));
                }
            }
            notifications = pushManager.sendNotifications(payLoad, device);
            
            List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
            List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
            int failed = failedNotifications.size();
            int successful = successfulNotifications.size();
            System.out.println("IOS status push count ["+tokens.size()+"],successful:["+successful+"] failed:["+failed+"] token:"+debug);
            pushManager.stopConnection();
        }
        catch (Exception e){
            e.printStackTrace();
        }
	}

	@Override
	public void sendAndroidJpush(final String alert, final String title, String sound,
			final List<String> registrationIds, final JPushClient jPushClient) {
		final Map<String,String> extras = new HashMap<String,String>();
		if(sound!=null){
			extras.put("sound", sound);
		}else{
			extras.put("sound", defaultSound);
		}
		Thread t = new Thread(  
                new Thread(){  
                    @Override  
                    public void run() {  
                    	sendAndroidJpush(alert, title, extras, registrationIds,jPushClient);
                    }  
                }  
        );
		t.start();
	}
}
