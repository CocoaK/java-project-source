package com.smarthome.socket.service.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.smarthome.socket.common.constant.PubConstant;
import com.smarthome.socket.common.util.CacheUtil;
import com.smarthome.socket.service.netty.service.NettyService;
import com.smarthome.socket.service.quartz.QuartzManagerJob;

/**
 * 
 * 类名称：PushJob 类描述：初始化设备任务
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-9 下午3:28:54
 */
public class InitDeviceJob extends BaseJob {
	Logger log = LoggerFactory.getLogger(InitDeviceJob.class.getName());
	private NettyService nettyService;

	public InitDeviceJob(NettyService nettyService) {
		super();
		this.nettyService = nettyService;
	}

	public void execute() {
		// 打印分割线
		printSplitLine();
		CacheUtil.getInstance().getAllMapsSize();
		boolean service_is_ok = CacheUtil.getInstance().serviceIsOK(PubConstant.TELNET_SERVICE_STATUS);
		// 初始化设备状态
		boolean initalAllDeviceStatus = CacheUtil.getInstance().initalAllDevicesIsOK(PubConstant.INITAL_ALL_DEVICE);
		if (service_is_ok) {
			// 如果没有完成初始所有设备状态
			if (!initalAllDeviceStatus) {
				print("---------init all devices offline status:" + initalAllDeviceStatus);
				try{
					print("---------init all devices offline status...");
					// 初始化所有设备状态
					nettyService.initAllDeviceStatus(PubConstant.OUTLINE);
					// 设置为初始化完成标志位
					CacheUtil.getInstance().addCache(PubConstant.INITAL_ALL_DEVICE, PubConstant.INITAL_ALL_DEVICE_STATUS);
					print("---------init all devices offline status:" + CacheUtil.getInstance().initalAllDevicesIsOK(PubConstant.INITAL_ALL_DEVICE));
					print("---------init all devices status complete,remove job.");
					QuartzManagerJob.removeJob("initDeviceJob");
				}catch(Exception e){
					e.getCause();
				}
			}
		} else {
			print("---service_is_ok:" + service_is_ok+", init all device outline status:"+initalAllDeviceStatus);
		}
	}

}
