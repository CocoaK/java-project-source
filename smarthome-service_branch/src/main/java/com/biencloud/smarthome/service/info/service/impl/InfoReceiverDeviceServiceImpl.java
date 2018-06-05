package com.biencloud.smarthome.service.info.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.charge.service.IChargeDataService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.info.model.InfoReceiverDevice;
import com.biencloud.smarthome.service.info.service.IInfoReceiverDeviceService;

/**
 * 信息接收设备领域服务实现类。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-15
 * @see IInfoReceiverDeviceService
 * @see BaseService
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class InfoReceiverDeviceServiceImpl extends BaseService<InfoReceiverDevice, Long> implements IInfoReceiverDeviceService {

	private IChargeDataService chargeDataService;

	@Override
	public Paging<InfoReceiverDevice> queryInfoReceiverDeviceForPaging(InfoReceiverDevice paramsOb, int pageNum, int pageSize) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM InfoReceiverDevice dn");
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(" order by id desc");
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
		return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}

	@Override
	public List<InfoReceiverDevice> queryInfoReceiverDeviceForList(InfoReceiverDevice paramsOb) {
		StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM InfoReceiverDevice dn");
		Map<String, Object> params = new HashMap<String, Object>();
		if (paramsOb.getInfoSend() != null && paramsOb.getInfoSend().getId() != null)
			appendCondition(hql, "dn.infoSend.id = :infoSendId", "infoSendId", paramsOb.getInfoSend().getId(), params);
		hql.append(" order by id desc");
		String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
		return findByNamedParams(queryString, params);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateInfoReceiverDevice(InfoReceiverDevice entity) {
		super.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveInfoReceiverDevice(InfoReceiverDevice entity) {
		super.save(entity);
	}

	@Override
	public InfoReceiverDevice getInfoReceiverDevice(String entityId) {
		return super.get(new Long(entityId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void DelInfoReceiverDevice(InfoReceiverDevice entity) {
		super.remove(entity);
	}

	public IChargeDataService getChargeDataService() {
		return chargeDataService;
	}

	public void setChargeDataService(IChargeDataService chargeDataService) {
		this.chargeDataService = chargeDataService;
	}

}
