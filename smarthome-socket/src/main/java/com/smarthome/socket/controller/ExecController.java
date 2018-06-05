package com.smarthome.socket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smarthome.socket.common.constant.PubConstant;
import com.smarthome.socket.common.model.ResultEntity;
import com.smarthome.socket.service.business.service.IAuthSessionService;
import com.smarthome.socket.service.business.service.IDevicesService;
import com.smarthome.socket.service.netty.service.NettyService;
import com.smarthome.socket.service.vo.AuthSession;
import com.smarthome.socket.service.vo.Device;

@Controller
@RequestMapping("/rest/sock")
public class ExecController{
	@Autowired	
	private NettyService nettyService;
	@Autowired
	private IDevicesService devicesService;
	@Autowired	
	private IAuthSessionService authSessionService;
	
	@RequestMapping(value="/exec", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> exec(String jsonType,String data,String mac,String sessionKey,Integer timeout) throws Exception{
		if(!"".equals(sessionKey) && null!=sessionKey){
			AuthSession auth = authSessionService.getBySessionKey(sessionKey);
			if(auth!=null){
				return nettyService.sendData(jsonType,data, mac,timeout);
			}
		}
		//此处应该返回未授权的消息
		//ResultEntity<String> re = new ResultEntity(ResultEntity.FAILD,"unauthorized","");
		return nettyService.sendData(jsonType,data, mac,timeout);
	}
	
	@RequestMapping(value="/update", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> update(String data,String mac) {
		return nettyService.sendUpdateData(data, mac);
	}
	
	//门口机刷新房号数据接口
	@RequestMapping(value="/send", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> send(String deviceNo, String data,String jsonType) {
		ResultEntity<Device> result =  devicesService.getByDeviceNo(deviceNo);
		if(result!=null && result.getCode()==ResultEntity.SUCCESS){
			Device dev = result.getData();
			if(dev!=null){
				return nettyService.sendData(PubConstant.JSON_TYPE_REFRESH,data, dev.getDeviceCode(), dev.getDeviceMac());
			}
		}
		return new ResultEntity<String>();
	}

}
