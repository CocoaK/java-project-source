package com.biencloud.smarthome.service.udp.job;

import java.util.Date;
import java.util.Map;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.biencloud.smarthome.service.app.service.IAppService;
import com.biencloud.smarthome.service.common.constants.AppConstants;
import com.biencloud.smarthome.service.common.utils.DateTimeUtil;


/**
 * 
 * 类名称：PushJob 类描述：推送工作
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-9 下午3:28:54
 */
public class HeartBeatJob  extends TimerTask  {
	Logger log = LoggerFactory.getLogger(HeartBeatJob.class.getName());

	private Map<String, Date> time_heart_map;

	private IAppService appService;

	public HeartBeatJob(Map<String, Date> time_heart_map, IAppService appService) {
		super();

		this.time_heart_map = time_heart_map;
		this.appService = appService;
	}

	

	@Override
	public synchronized void run() {
		if (time_heart_map != null && !time_heart_map.isEmpty()) {

			Date existDate = time_heart_map.get("tcp_server_heat_beat");
			if (existDate != null) {
				Date currentDate = new Date();
				long diffSeconds = DateTimeUtil.getDiffSecond(currentDate, existDate);
				if (diffSeconds > (2 * 60)) {
					appService.updateDeviceStatusForAll(AppConstants.OUTLINE);
				}
			}

		}

		
	}

}
