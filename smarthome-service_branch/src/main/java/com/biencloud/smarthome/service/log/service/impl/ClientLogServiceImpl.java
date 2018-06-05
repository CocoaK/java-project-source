package com.biencloud.smarthome.service.log.service.impl;

import java.util.Date;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.device.dao.IDeviceDao;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.housemgr.dao.IHousingDistrictInfoDao;
import com.biencloud.smarthome.service.log.model.ClientLog;
import com.biencloud.smarthome.service.log.service.IClientLogService;

/**
 * 
 * 类名称：ClientLogServiceImpl 类描述：终端日志业务接口实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-7-24 下午2:24:23
 */
@Transactional(propagation = Propagation.SUPPORTS)
public class ClientLogServiceImpl extends BaseService<ClientLog, Long> implements IClientLogService {
	private IHousingDistrictInfoDao areaDao;
	private IDeviceDao deviceDao;

	@Override
	public Paging<ClientLog> queryClientLogForPaging(int pageNum, int pageSize, String condition, String orderString, Object... values) {
		return super.queryForPagingByEntityNameAndCondition(pageNum, pageSize, "ClientLog", condition, orderString, values);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveClientLog(ClientLog clientLog) {
		boolean flag = false;
		if (clientLog != null) {
			clientLog.setAddTime(new Date());
			if (clientLog.getAreaNo() != null) {
				String areaName = areaDao.queryAreaName(clientLog.getAreaNo());
				if (areaName != null) {
					clientLog.setAreaName(areaName);
				}
			}
			if (clientLog.getDeviceNo() != null) {
				Device d = deviceDao.queryDeviceByDeviceNo(clientLog.getDeviceNo());
				if (d != null) {
					if (clientLog.getIp() == null) {
						String ip = d.getDeviceIp();
						clientLog.setIp(ip);
					}
					if (clientLog.getAreaNo() == null) {
						String areaName = d.getHousingDistrictInfo() == null ? null : d.getHousingDistrictInfo().getName();
						clientLog.setAreaName(areaName);
					}
					if (clientLog.getDeviceMac() == null) {
						String mac = d.getDeviceMac();
						clientLog.setDeviceMac(mac);
					}
					if (clientLog.getDeviceName() == null) {
						clientLog.setDeviceName(d.getDeviceName());
					}
					if (clientLog.getIp() == null) {
						clientLog.setIp(d.getDeviceIp());
					}
				}
			}
			if (clientLog.getIp() != null) {
				super.save(clientLog);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public ClientLog queryClientLogById(Long id) {
		ClientLog cl = null;
		if (id != null && id > 0) {
			cl = super.get(id);
		}
		return cl;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean deleteClientLogById(Long id) {
		boolean flag = false;
		if (id != null && id > 0) {
			ClientLog cl = super.get(id);
			if (cl != null) {
				super.remove(cl);
				flag = true;
			}
		}
		return flag;
	}

	public IHousingDistrictInfoDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(IHousingDistrictInfoDao areaDao) {
		this.areaDao = areaDao;
	}

	public IDeviceDao getDeviceDao() {
		return deviceDao;
	}

	public void setDeviceDao(IDeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

}
