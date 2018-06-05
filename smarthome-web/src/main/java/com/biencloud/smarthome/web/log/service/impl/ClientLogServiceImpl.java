package com.biencloud.smarthome.web.log.service.impl;

import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.DateTimeUtil;
import com.biencloud.smarthome.web.common.util.MapUtil;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.hdfstask.vo.HdfsTaskVO;
import com.biencloud.smarthome.web.log.service.IClientLogService;
import com.biencloud.smarthome.web.log.vo.ClientLogVO;
import com.biencloud.smarthome.web.wsclient.stub.ClientLog;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 
 * 类名称：ClientLogServiceImpl 类描述：终端日志业务接口实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-7-24 下午4:15:52
 */
public class ClientLogServiceImpl extends BaseService<ClientLogVO> implements IClientLogService {

	@Override
	public PagingVO<ClientLogVO> queryClientLogForPaging(int pageNum, int pageSize, ClientLogVO clientLogVO) {
		PagingVO<ClientLogVO> pv = new PagingVO<ClientLogVO>();
		MapUtil.clearMap();
		String hql = null;
		List<Object> list = null;
		if (clientLogVO != null) {

			if (!"".equals(clientLogVO.getDataType()) && clientLogVO.getDataType() != null) {
				MapUtil.addKeyValueToMap("dataType like", "%"+ clientLogVO.getDataType().trim().replace("%", "\\%").replace("_", "\\_") + "%");
			}

			if (!"".equals(clientLogVO.getAreaName()) && clientLogVO.getAreaName() != null) {
				MapUtil.addKeyValueToMap("areaName like", "%" + clientLogVO.getAreaName().trim().replace("%", "\\%").replace("_", "\\_") + "%");

			}
			if (!"".equals(clientLogVO.getDeviceName())&& clientLogVO.getDeviceName() != null) {
				MapUtil.addKeyValueToMap("deviceName like", "%" + clientLogVO.getDeviceName().trim().replace("%", "\\%").replace("_", "\\_") + "%");

			}
			if (!"".equals(clientLogVO.getDeviceMac()) && clientLogVO.getDeviceMac() != null) {
				MapUtil.addKeyValueToMap("deviceMac like", "%" + clientLogVO.getDeviceMac().trim().replace("%", "\\%").replace("_", "\\_") + "%");

			}
			if (!"".equals(clientLogVO.getDeviceNo()) && clientLogVO.getDeviceNo() != null) {
				MapUtil.addKeyValueToMap("deviceNo like", "%" + clientLogVO.getDeviceNo().trim().replace("%", "\\%").replace("_", "\\_") + "%");

			}
			if (!"".equals(clientLogVO.getIp()) && clientLogVO.getIp() != null) {
				MapUtil.addKeyValueToMap("ip like", "%" + clientLogVO.getIp().trim().replace("%", "\\%").replace("_", "\\_") + "%");

			}
			hql = MapUtil.covertMapKeyToCondition();
			list = MapUtil.covertMapValueToObjectList();

		}
		pv = this.queryClientLogForPaging(pageNum, pageSize, hql, "addTime", "desc", list);
		return pv;
	}
    /**
     * 
     * 方法的描述: 分页查询
     * @author: kouy  
     * @version: 0.1
     * @date: 2012-7-24 下午4:23:48
     * @param pageNum
     * @param pageSize
     * @param condition
     * @param orderString
     * @param orderBy
     * @param conditionValue
     * @return
     */
	public PagingVO<ClientLogVO> queryClientLogForPaging(int pageNum, int pageSize, String condition, String orderString, String orderBy,
			List<Object> conditionValue) {
		String order = null;
		if (orderString != null && orderBy != null) {
			order = " order by " + orderString + " " + orderBy;
		}
		Paging paging = getSmartHomeService().queryClientLogForPaging(pageNum, pageSize, condition, order, conditionValue);
		PagingVO<ClientLogVO> pv = super.convertToPagingVO(paging, "addTime");
		return pv;
	}

	@Override
	public ClientLogVO queryClientLogById(Long id) {
		ClientLogVO clv=null;
		ClientLog cl=getSmartHomeService().queryClientLogById(id);
		if(cl!=null)
		{
			clv=new ClientLogVO(cl.getId(), cl.getDeviceNo(), cl.getDeviceMac(), cl.getDeviceName(), cl.getDataType(), cl.getDataContent(),DateTimeUtil.convertXMLGregorianCalendarToDate(cl.getAddTime(), "yyyy-MM-dd HH:mm:ss"),
					cl.getIp(), cl.getAreaName(),cl.getLaunch());
		}
		return clv;
	}

}
