package com.biencloud.smarthome.service.device.dao.impl;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.device.dao.IDeviceDao;
import com.biencloud.smarthome.service.device.model.Device;

/**
 * 设备管理数据访问对象，基于JPA实现。
 * @author kouy
 * @since 1.0 2012-5-7
 * @see IDeviceDao
 */
@Transactional(propagation=Propagation.REQUIRED)
public class DeviceDaoJpa extends BaseDao<Device, String> implements IDeviceDao {
	
	
	@Override
	public int saveDevice(Device device) {
		if (jpaTemplate(device, "add") == 1) {   
            return 1;// 返回1表示成功插入数据库   
        } else {   
            return 0;// 返回0表示未成功的插入数据库   
        }
	}

	@Override
	public int updateDevice(Device device) {
		if (jpaTemplate(device, "update") == 1) {   
            return 1;// 返回1表示成功写入数据库   
        } else {   
            return 0;// 返回0表示未成功的写入数据库   
        }
	}

	@Override
	public int deleteDevice(Device device) {
		if (jpaTemplate(device, "delete") == 1) {   
            return 1;// 返回1表示成功写数据库   
        } else {
            return 0;// 返回0表示未成功的写数据库   
        }
	}
	
	public int jpaTemplate(Device device, String flag) {   
        try {   
            if (flag.equals("add")) {   
                //"添加设备";   
                getJpaTemplate().persist(device);   
            } else if (flag.equals("update")) {   
                //"更新设备";   
                getJpaTemplate().merge(device);   
            } else if (flag.equals("delete")) {   
                //"删除设备";    
	                Device u = device;   
	                getJpaTemplate().merge(u);   
	                remove(u);}
        }catch (Exception ex) {   
            //setRollbackOnly();   
            System.out.println(ex.getMessage()); 
            return 0;// 返回0表示未成功写入数据库   
        }
        return 1;   
    }
//
//	@Override
//	public Device queryDeviceByIp(String deviceIp,String districtId) {
//		Device device = new Device();
//		device.setDeviceIp(deviceIp);
//		String queryString = "SELECT Device FROM Device device WHERE device.deviceIp = ?1 and device.housingDistrictInfo.id = ?2 ";
//		List<Device> list = find(queryString,device.getDeviceIp(),districtId);
//		if(list!=null && list.size()!=0)
//			return list.get(0);
//		return null;
//	}

	@Override
	public Device queryDeviceByDeviceNo(String deviceNo) {
		Device device=null;
		if(deviceNo!=null)
		{
			List<Device> list=super.find("from Device where deviceCode=?", deviceNo);
			if(list!=null&&!list.isEmpty())
			{
				device=list.get(0);
			}
		}
		return device;
	}

	@Override
	public List<Device> findAll(String areaNo) {
		String hql="from Device";
		if(areaNo!=null)
		{
			hql+="  "+" where housingDistrictInfo.code='" + areaNo + "'";
		}
		List<Device> list=super.find(hql);
		return list;
	}

}
