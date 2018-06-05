package com.biencloud.smarthome.service.device.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.device.dao.IDeviceIpDao;
import com.biencloud.smarthome.service.device.model.DeviceIp;

/**
 * 设备ip数据访问对象，基于JPA实现。
 * @author Cocoa
 * @since 1.0 2012-5-15
 */
@Transactional(propagation=Propagation.REQUIRED)
public class DeviceIpDaoJpa extends BaseDao<DeviceIp, String> implements IDeviceIpDao {

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveDeviceIp(DeviceIp deviceIp) {
		if (jpaTemplate(deviceIp, "add") == 1) {   
            return 1;// 返回1表示成功插入数据库   
        } else {   
            return 0;// 返回0表示未成功的插入数据库   
        }
	}

	@Override
	public int updateDeviceIp(DeviceIp deviceIp) {
		if (jpaTemplate(deviceIp, "update") == 1) {   
            return 1;// 返回1表示成功写入数据库   
        } else {   
            return 0;// 返回0表示未成功的写入数据库   
        }
	}

	@Override
	public int deleteDeviceIp(DeviceIp deviceIp) {
		if (jpaTemplate(deviceIp, "delete") == 1) {   
            return 1;// 返回1表示成功写入数据库   
        } else {   
            return 0;// 返回0表示未成功的写入数据库   
        }
	}

	public int jpaTemplate(DeviceIp deviceIp, String flag) {   
        try {   
            if (flag.equals("add")) {   
                //"添加设备IP";   
                getJpaTemplate().persist(deviceIp);   
            } else if (flag.equals("update")) {   
                //"更新设备IP";   
                getJpaTemplate().merge(deviceIp);   
            } else if (flag.equals("delete")) {   
                //"删除设备IP";    
	                DeviceIp u = deviceIp;   
	                getJpaTemplate().merge(u);   
	                remove(u);}
        }catch (Exception ex) {   
            //setRollbackOnly();   
            System.out.println(ex.getMessage()); 
            return 0;// 返回0表示未成功写入数据库   
        }
        return 1;   
    }

	/**
	 * 方法的描述:修改deviceIp为未使用，并清空deviceIp中的device字段
	 * @author: ykou
	 * @version: 0.1
	 * @date: 2012-8-7 下午2:53:06
	 * @see com.biencloud.smarthome.service.device.dao.IDeviceIpDao#clearDeviceId(java.lang.String)
	 * @param deviceId
	 */
	@Override
	public void clearDeviceId(String deviceId) {
		if(StringUtils.isBlank(deviceId)){
			return;
		}
		String updateString = "UPDATE DeviceIp SET device=?1 ,status=?2 where device.deviceId=?3";
		String queryString = "SELECT COUNT(deviceIp) FROM DeviceIp deviceIp WHERE device.deviceId=?1";
		Long count= super.getTotalCount(queryString, deviceId);
		if(count<1)
			return;
		super.update(updateString, null,Constants.IP_NOT_USED,deviceId);
		
	}

	@Override
	public void deleteDeviceIpByDevice(String deviceId) {
		if(StringUtils.isBlank(deviceId)){
			return;
		}
		String removeString = "DELETE FROM DeviceIp WHERE device.deviceId=?1";
		super.removeByParams(removeString, deviceId);
	}

	

	
}
