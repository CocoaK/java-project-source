package com.biencloud.smarthome.web.push.service.impl;

import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.DateTimeUtil;
import com.biencloud.smarthome.web.common.util.MapUtil;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.push.service.IPushFinishService;
import com.biencloud.smarthome.web.push.vo.PushFinishVO;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.PushFinish;

/**
 * 
 * 类名称：PushFinishServiceImpl 类描述： 数据推送完成接口实现类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-11 下午3:34:20
 */
public class PushFinishServiceImpl extends BaseService<PushFinishVO> implements IPushFinishService {

	@Override
	public PagingVO<PushFinishVO> queryPushFinishForPaging(int pageNum, int pageSize,PushFinishVO pushFinishVO) {
		PagingVO<PushFinishVO> pv = new PagingVO<PushFinishVO>();
		MapUtil.clearMap();
		String condition = null;
		List<Object> list=null;
		//封装查询条件
		if (pushFinishVO != null) {
			String hql = "";
			if (!"all".equals(pushFinishVO.getPushKind())) {				
				MapUtil.addKeyValueToMap("pushKind", pushFinishVO.getPushKind());
			}
			if (pushFinishVO.getPushName() != null && !"".equals(pushFinishVO.getPushName())) {				
				MapUtil.addKeyValueToMap("pushName like", "%" + pushFinishVO.getPushName() + "%");				
			}
			if (pushFinishVO.getPushClientId() != null && !"".equals(pushFinishVO.getPushClientId())) {
				MapUtil.addKeyValueToMap("pushClientId like", "%" + pushFinishVO.getPushClientId() + "%");				
			}
			//转成查询条件
			condition = MapUtil.covertMapKeyToCondition();
			//转成list集合
			list=MapUtil.covertMapValueToObjectList();
			
		}
		//查询
		pv = this.queryPushFinishForPaging(pageNum, pageSize, condition, "pushFinishTime", "desc", list);
		return pv;
	}

	@Override
	public PagingVO<PushFinishVO> query(int pageNum, int pageSize, PushFinishVO pushFinishVO) {
		PagingVO<PushFinishVO> pv = new PagingVO<PushFinishVO>();
		MapUtil.clearMap();
		//封装查询条件
		if (pushFinishVO != null) {
			String hql = "";
			if (!"all".equals(pushFinishVO.getPushKind())) {				
				MapUtil.addKeyValueToMap("pushKind", pushFinishVO.getPushKind());
			}
			if (pushFinishVO.getPushName() != null && !"".equals(pushFinishVO.getPushName())) {				
				MapUtil.addKeyValueToMap("pushName like", "%" + pushFinishVO.getPushName() + "%");				
			}
			if (pushFinishVO.getPushClientId() != null && !"".equals(pushFinishVO.getPushClientId())) {
				MapUtil.addKeyValueToMap("pushClientId like", "%" + pushFinishVO.getPushClientId() + "%");				
			}
			//转成查询条件
			String condition = MapUtil.covertMapKeyToCondition();
			//转成list集合
			List<Object> list=MapUtil.covertMapValueToObjectList();
			//查询
			pv = this.queryPushFinishForPaging(pageNum, pageSize, condition, "pushFinishTime", "desc", list);
		}
		return pv;
	}

	@Override
	public PushFinishVO findById(Long id) {
		PushFinishVO pv = null;
		if (id != null && id > 0) {
			//查询
			PushFinish pf = getSmartHomeService().findPushFinishById(id);
			if (pf != null) {
				//封装新对象
				pv = new PushFinishVO(pf.getId(), pf.getPushName(), pf.getPushContent(), pf.getPushKind(),
						DateTimeUtil.convertXMLGregorianCalendarToDate(pf.getAddTime(), "yyyy-MM-dd HH:mm:ss"),
						DateTimeUtil.convertXMLGregorianCalendarToDate(pf.getPushFinishTime(), "yyyy-MM-dd HH:mm:ss"),
						pf.getPushClientId(), pf.getSize(), pf.getPushDescription());
			}
		}
		return pv;
	}

	@Override
	public PagingVO<PushFinishVO> queryPushFinishForPaging(int pageNum, int pageSize, String condition, String orderString, String orderBy,
			List<Object> list) {
		String order = null;
		if (orderString != null && orderBy != null) {
			order = " order by " + orderString + " " + orderBy;
		}
		//查询
		Paging paging = getSmartHomeService().queryPushFinishForPagingByKeyValue(pageNum, pageSize, condition, order,list);
		return super.convertToPagingVO(paging, "addTime", "pushFinishTime");
	}

}
