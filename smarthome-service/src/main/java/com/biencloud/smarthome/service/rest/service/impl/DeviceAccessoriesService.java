package com.biencloud.smarthome.service.rest.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.common.model.ResultList;
import com.biencloud.smarthome.service.common.page.Page;
import com.biencloud.smarthome.service.rest.mapper.DeviceAccessoriesMapper;
import com.biencloud.smarthome.service.rest.model.DeviceAccessories;
import com.biencloud.smarthome.service.rest.service.IDeviceAccessoriesService;

@Service
public class DeviceAccessoriesService extends BaseResService<DeviceAccessories> implements
		IDeviceAccessoriesService {

	@Autowired
	private DeviceAccessoriesMapper deviceAccessoriesMapper;

	@Override
	public BaseMapper<DeviceAccessories> getBaseMapper() {
		return deviceAccessoriesMapper;
	}

	@Override
	public ResultEntity<ResultList<List<DeviceAccessories>>> getResultListForPaging(
			Page<DeviceAccessories> p, DeviceAccessories da) {
		deviceAccessoriesMapper.getForPage(p, da);
		return super.proccessResultList(p.getTotal(), System.currentTimeMillis(), p.getResult());
	}

	@Override
	public List<DeviceAccessories> queryList(DeviceAccessories da) {
		// TODO Auto-generated method stub
		return deviceAccessoriesMapper.getList(da);
	}

	@Override
	public ResultEntity<String> save(DeviceAccessories da) {
		ResultEntity<String> re = new ResultEntity<String>();
		if(da == null) return re;
		DeviceAccessories device = new DeviceAccessories();
		device.setCode(da.getCode());
		device.setDeviceId(da.getDeviceId());
//		device.setType(da.getType());
		List<DeviceAccessories> list = deviceAccessoriesMapper.getList(device);
		if(list!=null && list.size()>0){
			DeviceAccessories acc = list.get(0);
			acc.setStatus(2);
			deviceAccessoriesMapper.updateOnActive(acc);
			re.setCode(ResultEntity.SUCCESS);
			return re;
		}
		int i = deviceAccessoriesMapper.insert(da);
		if(i>0){
			re.setCode(ResultEntity.SUCCESS);
		}
		return re;
	}
	
	@Override
	public ResultEntity<String> searchDeviceAccessories(DeviceAccessories da) {
		//将当前主设备下的所有状态为2(新增)的，修改为状态1(有效)。即非新增。
		ResultEntity<String> re = new ResultEntity<String>();
		deviceAccessoriesMapper.updateNewStatus(da.getDeviceId());
		re.setCode(ResultEntity.SUCCESS);
		return re;
	}

	@Override
	public DeviceAccessories getByEntity(DeviceAccessories da) {
		List<DeviceAccessories> list = deviceAccessoriesMapper.getList(da);
		DeviceAccessories accessories = new DeviceAccessories();
		if(list!=null && list.size()>0){
			accessories = list.get(0);
		}
		return accessories;
	}

	@Override
	public ResultEntity<String> deleteByEntity(DeviceAccessories da) {
		// TODO Auto-generated method stub
		int i = deviceAccessoriesMapper.deleteByEntity(da);
		return super.proccessResultEntity(i>0 ? ResultEntity.SUCCESS : ResultEntity.FAILD, "", "");
	}

	public ResultEntity<ResultList<List<DeviceAccessories>>> searchList(DeviceAccessories da){
		ResultEntity<ResultList<List<DeviceAccessories>>> re = new ResultEntity<ResultList<List<DeviceAccessories>>>();
		if(da == null || da.getDeviceId() == null){
			return re;
		}
		DeviceAccessories de = new DeviceAccessories();
		//状态2表示新增状态
		de.setStatus(2);
		de.setDeviceId(da.getDeviceId());
		//查询状态为2的当前主设备的配件，如果有，则表示有新增成功。
		List<DeviceAccessories> list = deviceAccessoriesMapper.getList(de);
		if(list != null && list.size()>0){
			re = this.getForResultList(da);
		}
		return re;
	}
	
	@Override
	public ResultEntity<String> addListForResultEntity(List<DeviceAccessories> list){
		ResultEntity<String> re = new ResultEntity<String>();
		if(list!=null && list.size()>0){
			for(DeviceAccessories deviceAccessories : list){
				DeviceAccessories da = new DeviceAccessories();
				da.setCode(deviceAccessories.getCode());
				da.setDeviceId(deviceAccessories.getDeviceId());
				List<DeviceAccessories> das = deviceAccessoriesMapper.getList(da);
				if(das==null || das.size()==0){
					deviceAccessoriesMapper.insert(deviceAccessories);
					re.setCode(ResultEntity.SUCCESS);
				}
			}
		}
		return re;
	}
	
	@Override
	public ResultEntity<String> addListForResultEntityAndSync(List<DeviceAccessories> list){
		ResultEntity<String> re = new ResultEntity<String>();
		if(list!=null && list.size()>0){
			Long deviceId = list.get(0).getDeviceId();
			DeviceAccessories acc = new DeviceAccessories();
			acc.setDeviceId(deviceId);
			//获得数据库中保存的数据list
			List<DeviceAccessories> accs = deviceAccessoriesMapper.getList(acc);
			//循环此list
			for(DeviceAccessories de : accs){
				//如果从设备同步的数据不包含此配件，则从数据库删除此配件
				if(!list.contains(de)){
					deviceAccessoriesMapper.delete(de.getId());
				}
			}
			
			for(DeviceAccessories deviceAccessories : list){
				DeviceAccessories da = new DeviceAccessories();
				da.setCode(deviceAccessories.getCode());
				da.setDeviceId(deviceAccessories.getDeviceId());
				List<DeviceAccessories> das = deviceAccessoriesMapper.getList(da);
				if(das==null || das.size()==0){
					deviceAccessoriesMapper.insert(deviceAccessories);
					re.setCode(ResultEntity.SUCCESS);
				}
			}
		}
		return re;
	}
}  
