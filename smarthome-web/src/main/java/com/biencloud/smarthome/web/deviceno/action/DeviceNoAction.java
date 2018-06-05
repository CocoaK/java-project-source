package com.biencloud.smarthome.web.deviceno.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.deviceno.service.IDeviceNoService;
import com.biencloud.smarthome.web.deviceno.vo.DeviceNoVo;
import com.biencloud.smarthome.web.devicetype.vo.DeviceTypeVO;
import com.biencloud.smarthome.web.sysparam.vo.SystemParamVO;
import com.biencloud.smarthome.web.wsclient.stub.DeviceNo;

/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：DeviceNoAction 
 * 类描述： 设备编号管理类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午1:54:36
 */
public class DeviceNoAction extends BaseAction<DeviceNoVo> {

	private static final long serialVersionUID = 1L;

	private IDeviceNoService deviceNoService;

	private DeviceNoVo deviceNo;

	/**
	 * 
	 * 方法的描述: 设备编号列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:54:27
	 * @return
	 */
	public String queryList() {
		try {
			PagingVO<DeviceNoVo> page = getPage();
			if (page == null)
				page = new PagingVO<DeviceNoVo>();
			String areaId = getParameter("areaId");
			if (StringUtils.isNotBlank(areaId)){
				if(deviceNo==null) deviceNo=new DeviceNoVo();
				deviceNo.setAreaId(areaId);
			}
			PagingVO<DeviceNoVo> pagingVO = getDeviceNoService().queryDeviceForPaging(deviceNo, page.getPageNum(),page.getPageSize());
			List<DeviceNoVo> results = pagingVO.getResults();
			List<DeviceNoVo> list = new ArrayList<DeviceNoVo>();
			if(!CollectionUtils.isEmpty(results)){
				for(DeviceNoVo vo : results){
					DeviceVO dvo=vo.getDevice();
					DeviceTypeVO deviceType = new DeviceTypeVO();
					deviceType.setDeviceName(this.getText(vo.getDevice().getDeviceType().getDeviceName()));
					dvo.setDeviceType(deviceType);
					vo.setDevice(dvo);
					list.add(vo);
				}
				pagingVO.setResults(list);
			}
			setPage(pagingVO);
			 return "list";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public IDeviceNoService getDeviceNoService() {
		return deviceNoService;
	}

	public void setDeviceNoService(IDeviceNoService deviceNoService) {
		this.deviceNoService = deviceNoService;
	}

	public DeviceNoVo getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(DeviceNoVo deviceNo) {
		this.deviceNo = deviceNo;
	}

}
