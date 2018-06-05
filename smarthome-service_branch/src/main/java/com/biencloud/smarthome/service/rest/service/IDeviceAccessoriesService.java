package com.biencloud.smarthome.service.rest.service;

import java.util.List;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.common.model.ResultList;
import com.biencloud.smarthome.service.common.page.Page;
import com.biencloud.smarthome.service.rest.model.DeviceAccessories;

public interface IDeviceAccessoriesService extends IBaseResService<DeviceAccessories>{
	
	List<DeviceAccessories> queryList(DeviceAccessories da);
	
	ResultEntity<ResultList<List<DeviceAccessories>>> getResultListForPaging(Page<DeviceAccessories> p,DeviceAccessories da);
	
	ResultEntity<String> save(DeviceAccessories da);
	
	DeviceAccessories getByEntity(DeviceAccessories da);
	
	ResultEntity<String> deleteByEntity(DeviceAccessories da);

	ResultEntity<String> searchDeviceAccessories(DeviceAccessories da);
	
	ResultEntity<ResultList<List<DeviceAccessories>>> searchList(DeviceAccessories da);
}
