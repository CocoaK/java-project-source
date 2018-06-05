package com.biencloud.smarthome.web.appdata.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.appdata.json.MonitorJson;
import com.biencloud.smarthome.web.appdata.service.IMonitorDeviceService;
import com.biencloud.smarthome.web.appdata.vo.MonitorDeviceVO;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.wsclient.stub.Device;
/**
 * 类名称：MonitorDeviceServiceImpl 
 * 类描述： 监控设备业务实现类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-27 上午11:03:21
 */
public class MonitorDeviceServiceImpl extends BaseService<MonitorDeviceVO> implements IMonitorDeviceService{

	@Override
	public MonitorJson queryMonitors(String deviceCode) {
		MonitorJson monitorJson = new MonitorJson();
		Device device = getSmartHomeService().queryDeviceByCode(deviceCode);
		List<Device> ds = getSmartHomeService().queryMonitorDevice(device);
		List<MonitorDeviceVO> list = new ArrayList<MonitorDeviceVO>();
		if(ds != null && ds.size() != 0){
			for(Device d : ds){
				MonitorDeviceVO monitor = new MonitorDeviceVO();
				if(d!=null){
					monitor.setDeviceName(d.getDeviceName());
					monitor.setDeviceIp(d.getDeviceIp());
					if(d.getDeviceType()!=null){
						monitor.setDeviceType(d.getDeviceType().getDeviceType());
					}
				}
				list.add(monitor);
			}
		}
		monitorJson.setMonitorList(list);
		return monitorJson;
	}

}
