package com.biencloud.smarthome.web.deviceno.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.service.IDeviceService;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.deviceno.service.IDeviceNoService;
import com.biencloud.smarthome.web.deviceno.vo.DeviceNoVo;
import com.biencloud.smarthome.web.wsclient.stub.Device;
import com.biencloud.smarthome.web.wsclient.stub.DeviceNo;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 设备编号管理领域服务接口。
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IService
 * @throws RuntimeException 如果操作执行失败
 */
public class DeviceNoServiceImpl extends BaseService<DeviceNoVo>  implements IDeviceNoService {
	

	private static final String[] IGNOREPRO_PERTIES = {"device"};
	private IDeviceService deviceService;

	@Override
	public PagingVO<DeviceNoVo> queryDeviceForPaging(DeviceNoVo paramsOb, int pageNum, int pageSize) {
		try {
			DeviceNo ob=new DeviceNo();
			if(paramsOb!=null){
				ob.setAreaId(paramsOb.getAreaId());
				ob.setDeviceNo(paramsOb.getDeviceNo());
				Device dOb=new Device();
				dOb.setDeviceName(paramsOb.getDeviceName());
				ob.setDevice(dOb);
			}
			Paging paging=getSmartHomeService().queryDeviceNoForPaging(ob, pageNum, pageSize);
			PagingVO<DeviceNoVo> pagingVO = convertToVO(paging, IGNOREPRO_PERTIES);
			List<Object> list = paging.getResults();
			if(list != null && list.size() > 0){
				List<DeviceNoVo> results = new ArrayList<DeviceNoVo>();
				for (int index = 0, size = list.size(); index < size; index++) {
					DeviceNo dnoOb=(DeviceNo)list.get(index);
					DeviceVO dOb=deviceService.deviceToVO(dnoOb.getDevice());
					DeviceNoVo dnoVo=new DeviceNoVo();
					copyProperties(dnoOb, dnoVo,  IGNOREPRO_PERTIES);
					dnoVo.setDevice(dOb);
					results.add(dnoVo);
				}
				pagingVO.setResults(results);
			}
			return pagingVO;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	

}
