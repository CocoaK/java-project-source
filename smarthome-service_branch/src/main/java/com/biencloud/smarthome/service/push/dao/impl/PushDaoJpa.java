package com.biencloud.smarthome.service.push.dao.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.push.dao.IPushDao;
import com.biencloud.smarthome.service.push.model.Push;
/**
 * 
 * 类名称：PushDaoJpa 
 * 类描述： 推送Dao接口实现类
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-9 上午11:14:04
 */
public class PushDaoJpa extends BaseDao<Push, Long> implements IPushDao {

	@Override
	public boolean insertPush(Push push) {
		boolean isSucess=false;
		super.save(push);
		isSucess=true;
		return isSucess;
	}

	@Override
	public boolean inserPushCollection(List<Push> pushList) {
		boolean isSucess=false;
		super.saveOrUpdateCollection(pushList);
		isSucess=true;
		return isSucess;
	}

	@Override
	public void pushToClients(List<Device> deviceList, Push push) {
		if(deviceList!=null&&!deviceList.isEmpty()&&push!=null)
		{
			List<Push> pushList=new ArrayList<Push>();
			for(Device d:deviceList)
			{
				if(d!=null)
				{
					String deviceNo=d.getDeviceCode();
					if(deviceNo!=null)
					{
						Push p=new Push(push.getPushName(), push.getPushKind(), push.getPushContent(), push.getPushVersion(), new Date(),
								deviceNo,push.getSize());
						pushList.add(p);
					}
				}
			}
			if(pushList!=null&&!pushList.isEmpty())
			{
				this.inserPushCollection(pushList);
			}
		}
		
	}

	

	
	




	
}
