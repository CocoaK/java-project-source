package com.biencloud.smarthome.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jpush.api.JPushClient;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.PushClient;
import com.biencloud.smarthome.service.rest.service.IPushClientService;

@Controller
@RequestMapping("/push/client")
public class PushClientController extends BaseResController<PushClient>{
		
	@Autowired
	private IPushClientService pushClientService;
	
	@Override
	public IBaseResService<PushClient> getBaseResService() {
		return null;
	}

	/**
	 * 状态改变调用接口
	 * @param record
	 * @return
	 */
	@RequestMapping(value="/pushStatusChanged", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> pushStatusChanged(@RequestBody PushClient record) {
		if(record==null){
			return new ResultEntity<String>();
		}else{
			pushClientService.pushDefenceStatusToClient(record);
		}
		return new ResultEntity<String>(ResultEntity.SUCCESS,"","");
	}
	
	/**
	 * 云对讲室内机推送通知到IOS手机接口
	 * @param content
	 * @param tokens
	 * @return
	 */
	@RequestMapping(value="/pushToIosClient", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> pushToIosClient(String content,String title,String... tokens) {
		if(content==null || "".equals(content)){
			content = "new call";
		}
		if(tokens==null || tokens.length==0){
			return new ResultEntity<String>();
		}else{
			List<String> list = Arrays.asList(tokens);
			pushClientService.sendIosPushIntercom(content, null, list, 1);
		}
		return new ResultEntity<String>(ResultEntity.SUCCESS,"","");
	}
	
	/**
	 * 云对讲室内机推送通知到Android手机接口
	 * @param content
	 * @param tokens
	 * @return
	 */
	@RequestMapping(value="/pushToAndroidClient", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> pushToAndroidClient(String content,String title, String... tokens) {
//		if(content==null || "".equals(content)){
//			content = "calling";
//		}
		if(tokens==null || tokens.length==0){
			return new ResultEntity<String>();
		}else{
			List<String> tokenList = Arrays.asList(tokens);
			JPushClient jPushClient = new JPushClient(Constants.PUSH_MASTER_SECRET_JPUSH_INTERCOM,Constants.PUSH_APP_KEY_JPUSH_INTERCOM);
			pushClientService.sendAndroidJpush(content,title,null,tokenList,jPushClient);
		}
		return new ResultEntity<String>(ResultEntity.SUCCESS,"","");
	}
	
	/**
	 * 云对讲室内机推送通知到Android手机接口
	 * @param content
	 * @param tokens
	 * @return
	 */
	@RequestMapping(value="/pushToAndroidClientIntercom", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> pushToAndroidClientIntercom(String content,String title, String deviceNo) {
//		if(content==null || "".equals(content)){
//			content = "calling";
//		}
		if(deviceNo==null || "".equals(deviceNo)){
			return new ResultEntity<String>();
		}else{
			JPushClient jPushClient = new JPushClient(Constants.PUSH_MASTER_SECRET_JPUSH_INTERCOM,Constants.PUSH_APP_KEY_JPUSH_INTERCOM);
			pushClientService.sendAndroidJpushIntercom(deviceNo, content, title, null, jPushClient);
		}
		return new ResultEntity<String>(ResultEntity.SUCCESS,"","");
	}
	
	/**
	 * 云对讲室内机推送通知到IOS手机接口
	 * @param content
	 * @param tokens
	 * @return
	 */
	@RequestMapping(value="/pushToIosClientIntercom", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> pushToIosClientIntercom(String content,String title,String deviceNo) {
		if(content==null || "".equals(content)){
			content = "您有新的呼叫";
		}
		if(deviceNo==null || "".equals(deviceNo)){
			return new ResultEntity<String>();
		}else{
			pushClientService.sendIosPushIntercom(content, null, deviceNo, 1);
		}
		return new ResultEntity<String>(ResultEntity.SUCCESS,"","");
	}
	
	/**
	 * 云对讲门口机推送通知到手机接口---直呼版本
	 * @param content
	 * @param tokens
	 * @return
	 */
	@RequestMapping(value="/pushToIntercom", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> pushToIosIntercom(String content,String title,String username) {
		if(content==null || "".equals(content)){
			content = "您有新的呼叫";
		}
		if(username==null || "".equals(username)){
			return new ResultEntity<String>();
		}else{
			pushClientService.pushIntercom(content, title, username);
		}
		return new ResultEntity<String>(ResultEntity.SUCCESS,"","");
	}
	
}
