package com.biencloud.smarthome.web.deviceno.service;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.deviceno.vo.DeviceNoVo;
import com.biencloud.smarthome.web.wsclient.stub.Device;

/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：IDeviceNoService 
 * 类描述： 设备编号管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午1:53:03
 */
public interface IDeviceNoService {

	/**
	 * 
	 * 方法的描述: 查询设备编号列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午1:52:37
	 * @param paramsOb：设备编号对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public PagingVO<DeviceNoVo> queryDeviceForPaging(DeviceNoVo paramsOb, int pageNum, int pageSize);
	
}
