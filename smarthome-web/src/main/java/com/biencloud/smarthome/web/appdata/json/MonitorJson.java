package com.biencloud.smarthome.web.appdata.json;

import java.util.List;
import com.biencloud.smarthome.web.appdata.vo.MonitorDeviceVO;

/**
 * 
 * 类名称：MonitorJson 
 * 类描述： 监控设备Json
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-21 下午2:34:53
 */
public class MonitorJson extends Json{
	
	private List<MonitorDeviceVO> monitorList;

	public List<MonitorDeviceVO> getMonitorList() {
		return monitorList;
	}

	public void setMonitorList(List<MonitorDeviceVO> monitorList) {
		this.monitorList = monitorList;
	}

}
