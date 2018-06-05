package com.biencloud.smarthome.web.push.service.impl;

import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.DateTimeUtil;
import com.biencloud.smarthome.web.common.util.MapUtil;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.push.service.IPushService;
import com.biencloud.smarthome.web.push.vo.PushVO;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.Push;
import com.biencloud.smarthome.web.wsclient.stub.PushFinish;

/**
 * 
 * 类名称：PushServiceImpl 
 * 类描述： 数据推送接口实现类
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-11 下午3:34:39
 */
public class PushServiceImpl extends BaseService<PushVO> implements
IPushService{

	@Override
	public PagingVO<PushVO> queryPushForPaging(int pageNum, int pageSize,PushVO pushVO) throws Exception{
		PagingVO<PushVO> pv=new PagingVO<PushVO>();
		MapUtil.clearMap();
		String hql=null;
		List<Object> list=null;
		if(pushVO!=null)
		{			
			if(!"all".equals(pushVO.getPushKind()))
			{
				MapUtil.addKeyValueToMap("pushKind", pushVO.getPushKind());
			}
			if(pushVO.getPushName()!=null&&!"".equals(pushVO.getPushName()))
			{				
				MapUtil.addKeyValueToMap("pushName like", "%"+pushVO.getPushName()+"%");				
			}
			if(pushVO.getPushClientId()!=null&&!"".equals(pushVO.getPushClientId()))
			{				
				MapUtil.addKeyValueToMap("pushClientId like", "%"+pushVO.getPushClientId()+"%");				
			}
			hql=MapUtil.covertMapKeyToCondition();
			list=MapUtil.covertMapValueToObjectList();
			
			
		}
		pv=this.queryPushForPaging(pageNum, pageSize, hql, "addTime", "desc", list);
		return pv;
	}

	@Override
	public PagingVO<PushVO> query(int pageNum, int pageSize, PushVO pushVO) throws Exception{
		PagingVO<PushVO> pv=new PagingVO<PushVO>();
		MapUtil.clearMap();
		if(pushVO!=null)
		{			
			if(!"all".equals(pushVO.getPushKind()))
			{
				MapUtil.addKeyValueToMap("pushKind", pushVO.getPushKind());
			}
			if(pushVO.getPushName()!=null&&!"".equals(pushVO.getPushName()))
			{				
				MapUtil.addKeyValueToMap("pushName like", "%"+pushVO.getPushName()+"%");				
			}
			if(pushVO.getPushClientId()!=null&&!"".equals(pushVO.getPushClientId()))
			{				
				MapUtil.addKeyValueToMap("pushClientId like", "%"+pushVO.getPushClientId()+"%");				
			}
			String hql=MapUtil.covertMapKeyToCondition();
			List<Object> list=MapUtil.covertMapValueToObjectList();
			
			pv=this.queryPushForPaging(pageNum, pageSize, hql, "addTime", "desc", list);
		}
		return pv;
	}

	@Override
	public void repush(Long id) throws Exception{
		if(id!=null&&id>0)
		{
			PushFinish pv=getSmartHomeService().findPushFinishById(id);
			if(pv!=null)
			{
				Push p=new Push();
				p.setAddTime(DateTimeUtil.convertDateToXMLGregorianCalendar(new Date()));
				p.setPushClientId(pv.getPushClientId());
				p.setPushContent(pv.getPushContent());
				p.setPushDescription(pv.getPushDescription());
				p.setPushKind(pv.getPushKind());
				p.setPushName(pv.getPushName());
				p.setPushVersion(pv.getPushVersion());
				p.setSize(pv.getSize());
				getSmartHomeService().insertPush(p);
			}
			
		}
		
	}

	@Override
	public PagingVO<PushVO> queryPushForPaging(int pageNum, int pageSize, String condition, String orderString, String orderBy,
			List<Object> list) throws Exception{
		String order=null;
		if(orderString!=null&&orderBy!=null)
		{
			order=" order by "+orderString +" "+orderBy;
		}
		Paging paging = getSmartHomeService().queryPushForPagingByKeyValue(pageNum, pageSize, condition,order,list);
		PagingVO<PushVO> pv=super.convertToPagingVO(paging, "addTime");
		return pv;
	}

	@Override
	public PushVO queryById(Long id) throws Exception{
		PushVO pv = null;
		if (id != null && id > 0) {
			Push p = getSmartHomeService().findPushById(id);
			if (p!= null) {
				pv = new PushVO(p.getId(), p.getPushName(), p.getPushKind(), p.getPushContent(), p.getPushVersion(), DateTimeUtil.convertXMLGregorianCalendarToDate(p.getAddTime(), "yyyy-MM-dd HH:mm:ss"), p.getPushClientId(),
						p.getSize(), p.getPushDescription());
			}
		}
		return pv;
	}

}
