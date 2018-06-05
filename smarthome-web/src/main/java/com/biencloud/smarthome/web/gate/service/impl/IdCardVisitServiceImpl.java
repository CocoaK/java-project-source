package com.biencloud.smarthome.web.gate.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.gate.service.IIdCardVisitService;
import com.biencloud.smarthome.web.gate.vo.IdCardVisitVO;
import com.biencloud.smarthome.web.wsclient.stub.IdCardVisit;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 身份证刷卡管理调用服务实现。
 * @author kouy
 * @since 1.0 2012-5-10
 */
public class IdCardVisitServiceImpl extends BaseService<IdCardVisitVO> implements
		IIdCardVisitService {

	private static final String VISIT_TIME = "visitTime";
	private static final String BEGIN_TIME = "beginTime";
	private static final String END_TIME = "endTime";
	
	@Override
	public PagingVO<IdCardVisitVO> queryIdCardVisitsForPaging(
			IdCardVisitVO idCardVisit, int pageNum, int pageSize) {
		IdCardVisit icv = new IdCardVisit();
		copyProperties(idCardVisit, icv, false, 
				VISIT_TIME, BEGIN_TIME, END_TIME);
		
		Paging paging = getSmartHomeService().queryIdCardVisitsForPaging(
				icv, pageNum, pageSize);		
		
		return convertToPagingVO(paging, VISIT_TIME, BEGIN_TIME, END_TIME);
	}

	@Override
	public IdCardVisitVO getIdCardVisitDetail(String visitId) {
		IdCardVisitVO icvVO = new IdCardVisitVO();
		copyProperties(getSmartHomeService().getIdCardVisitDetail(visitId), 
				icvVO, true, VISIT_TIME, BEGIN_TIME, END_TIME);
		return icvVO;
	}

	@Override
	public void saveOrUpdateIdCardVisit(IdCardVisitVO idCardVisit) {
		IdCardVisit icv = new IdCardVisit();
		copyProperties(idCardVisit, icv, false, VISIT_TIME, BEGIN_TIME, END_TIME);
		getSmartHomeService().saveOrUpdateIdCardVisit(icv);
		
	}

	@Override
	public void removeIdCardVisit(IdCardVisitVO idCardVisit) {
		IdCardVisit icv = new IdCardVisit();
		copyProperties(idCardVisit, icv, false, VISIT_TIME, BEGIN_TIME, END_TIME);
		getSmartHomeService().removeIdCardVisit(icv);
	}

	@Override
	public List<IdCardVisitVO> queryIdCardVisits(IdCardVisitVO idCardVisit) {
		IdCardVisit icv = new IdCardVisit();
		copyProperties(idCardVisit, icv, false, VISIT_TIME, BEGIN_TIME, END_TIME);
		List<IdCardVisitVO> list = new ArrayList<IdCardVisitVO>();
		List<IdCardVisit> idCardVisits = getSmartHomeService().queryIdCardVisits(icv);
		if(idCardVisits == null || idCardVisits.size() == 0)
			return list;
		for (IdCardVisit icVisit : idCardVisits) {
			IdCardVisitVO idCardVisitVO = new IdCardVisitVO();
			copyProperties(icVisit, idCardVisitVO,true, VISIT_TIME,BEGIN_TIME,END_TIME);
			list.add(idCardVisitVO);
		}
		return list;
	}
}
