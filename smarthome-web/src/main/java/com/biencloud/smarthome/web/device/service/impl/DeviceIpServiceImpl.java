package com.biencloud.smarthome.web.device.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.CollectionUtils;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.service.IDeviceIpService;
import com.biencloud.smarthome.web.device.service.IDeviceService;
import com.biencloud.smarthome.web.device.vo.DeviceIpVO;
import com.biencloud.smarthome.web.housemgr.util.HouseManagementModelVoConvert;
import com.biencloud.smarthome.web.wsclient.stub.DeviceIp;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 
 * 类名称：DeviceIpServiceImpl 
 * 类描述： 设备IPweb端实现类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-19 上午11:38:59
 */
public class DeviceIpServiceImpl extends BaseService<DeviceIpVO> implements IDeviceIpService{
	
	public IDeviceService deviceService;

	public IDeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@Override
	public PagingVO<DeviceIpVO> queryDeviceIpForPaging(DeviceIpVO deviceIp,
			int pageNum, int pageSize) {
		DeviceIp _deviceIp = new DeviceIp();
		PagingVO<DeviceIpVO> paging;
		if(deviceIp != null){
			if(deviceIp.getHousingDistrictInfo()!=null)
				_deviceIp.setHousingDistrictInfo(HouseManagementModelVoConvert.district2Model(deviceIp.getHousingDistrictInfo()));
			_deviceIp.setIpId(deviceIp.getIpId());
			_deviceIp.setIpAddress(deviceIp.getIpAddress());
			_deviceIp.setSubnet(deviceIp.getSubnet());
			_deviceIp.setStatus(deviceIp.getStatus());
			_deviceIp.setDevice(deviceService.voToDevice(deviceIp.getDevice()));
		}
		Paging _paging = getSmartHomeService().queryDeviceIpForPaging(_deviceIp, pageNum, pageSize);
		//将Paging对象转换成PagingVO对象
		paging = toPagingVO(_paging);
		return paging;
	}

	@Override
	public DeviceIpVO queryDeviceIpByIp(String ipAddress,String distrcitId) {
		DeviceIp deviceIp = getSmartHomeService().queryDeviceIpByIp(ipAddress,distrcitId);
		DeviceIpVO deviceIpVO = new DeviceIpVO();
		//DeviceIp对象转换成DeviceIpVO对象
		//copyProperties(deviceIp,deviceIpVO);
		if(deviceIp != null){
			if(deviceIp.getHousingDistrictInfo()!=null)
				deviceIpVO.setHousingDistrictInfo(HouseManagementModelVoConvert.housingDistrict2Vo(deviceIp.getHousingDistrictInfo()));
			deviceIpVO.setIpId(deviceIp.getIpId());
			deviceIpVO.setIpAddress(deviceIp.getIpAddress());
			deviceIpVO.setSubnet(deviceIp.getSubnet());
			deviceIpVO.setStatus(deviceIp.getStatus());
			deviceIpVO.setDevice(deviceService.deviceToVO(deviceIp.getDevice()));
		}
		return deviceIpVO;
	}

	@Override
	public int saveSubnetIps(DeviceIpVO deviceIp) {
		DeviceIp _deviceIp = new DeviceIp();
		//copyProperties(deviceIp,_deviceIp);
		if(deviceIp != null){
			if(deviceIp.getHousingDistrictInfo()!=null)
				_deviceIp.setHousingDistrictInfo(HouseManagementModelVoConvert.district2Model(deviceIp.getHousingDistrictInfo()));
			_deviceIp.setIpId(deviceIp.getIpId());
			_deviceIp.setIpAddress(deviceIp.getIpAddress());
			_deviceIp.setSubnet(deviceIp.getSubnet());
			_deviceIp.setStatus(deviceIp.getStatus());
			_deviceIp.setDevice(deviceService.voToDevice(deviceIp.getDevice()));
		}
		return getSmartHomeService().saveSubnetIps(_deviceIp);
	}

	
	/**
	 * 
	 * 方法的描述: paging转换
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-30 下午1:20:08
	 * @param paging
	 * @return
	 */
	public PagingVO<DeviceIpVO> toPagingVO(Paging paging){
		PagingVO<DeviceIpVO> pagingVO = new PagingVO<DeviceIpVO>();
		if(paging == null)
			return pagingVO;					
		copyProperties(paging, pagingVO);
		List<DeviceIpVO> voResults = new ArrayList<DeviceIpVO>();
		List<Object> results = paging.getResults();
		if(!CollectionUtils.isEmpty(results)){
			for (int index = 0, size = results.size(); index < size; index++) {
				DeviceIpVO vo = new DeviceIpVO();
				DeviceIp deviceIp = (DeviceIp) results.get(index);
				if(deviceIp.getHousingDistrictInfo()!=null)
					vo.setHousingDistrictInfo(HouseManagementModelVoConvert.housingDistrict2Vo(deviceIp.getHousingDistrictInfo()));
				vo.setIpId(deviceIp.getIpId());
				vo.setIpAddress(deviceIp.getIpAddress());
				vo.setSubnet(deviceIp.getSubnet());
				vo.setStatus(deviceIp.getStatus());;
				vo.setDevice(deviceService.deviceToVO(deviceIp.getDevice()));
				voResults.add(vo);
			}
		}
		pagingVO.setResults(voResults);
		
		return pagingVO;
	}
}
