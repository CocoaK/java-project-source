package com.biencloud.smarthome.web.device.action;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.service.IDeviceIpService;
import com.biencloud.smarthome.web.device.vo.DeviceIpVO;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;

/**
 * 
 * 类名称：DeviceIpAction 
 * 类描述： 设备IP动作类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-16 下午5:16:07
 */
public class DeviceIpAction extends BaseAction<DeviceIpVO>{
		
	private static final long serialVersionUID = 3155302979010643718L;
	//设备ip接口
	private IDeviceIpService deviceIpService;
	//设备ip实体
	public DeviceIpVO deviceIp;
	//设备ip地址id
	public String ipId;
	//ip地址
	public String ipAddr;
	//成功标志
	public String successFlag;
	/**
	 * 
	 * 方法的描述: 登录用户小区的ip列表分页
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-6-17 下午7:08:15
	 * @return
	 * @throws Exception
	 */
	public String ipList() throws Exception{
			if(deviceIp == null){
				deviceIp = new DeviceIpVO();
			}
			if(Constants.LOGIN_USER_TYPE_PAUSER.equals(super.getUserType())){
				//如果是物管用户，设置小区为其所属小区
				setHousingDistrict(deviceIp);
			}
			PagingVO<DeviceIpVO> page = getPage();
			if(page == null)
				page = new PagingVO<DeviceIpVO>();
			//查询并分页
			PagingVO<DeviceIpVO> ipList = getDeviceIpService()
					.queryDeviceIpForPaging(getDeviceIp(), page.getPageNum(), page.getPageSize());
			setPage(ipList);
		return "list";
	}
	
	/**
	 * 
	 * 方法的描述: 新增小区IP
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-7-17 下午7:08:59
	 * @return
	 * @throws Exception
	 */
	public String ipAdd() throws Exception{
		String flag = "fail";
		DeviceIpVO deviceIpVO = new DeviceIpVO();
		setHousingDistrict(deviceIpVO);
		if(getIpAddr()!=null){
			deviceIpVO.setIpAddress(getIpAddr());
			//保存IP地址段
			int result = getDeviceIpService().saveSubnetIps(deviceIpVO);
			if(result==Constants.DEVICE_IP_SAVE_SUCCESS)
				flag="success";
		}
		ipList();
		setSuccessFlag(flag);
		return "add";
	}
	
	/**
	 * 方法的描述: 设置ip地址所属小区
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-24 上午10:57:09
	 * @param deviceIpVO
	 */
	private void setHousingDistrict(DeviceIpVO deviceIpVO){
		HousingDistrictInfoVo housingDistrictInfo = new HousingDistrictInfoVo();
		housingDistrictInfo.setId(super.getDistrictId());
		deviceIpVO.setHousingDistrictInfo(housingDistrictInfo);
	}
	
	public IDeviceIpService getDeviceIpService() {
		return deviceIpService;
	}

	public void setDeviceIpService(IDeviceIpService deviceIpService) {
		this.deviceIpService = deviceIpService;
	}

	public DeviceIpVO getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(DeviceIpVO deviceIp) {
		this.deviceIp = deviceIp;
	}

	public String getIpId() {
		return ipId;
	}

	public void setIpId(String ipId) {
		this.ipId = ipId;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getSuccessFlag() {
		return successFlag;
	}
	
	public void setSuccessFlag(String successFlag) {
		this.successFlag = successFlag;
	}
}
