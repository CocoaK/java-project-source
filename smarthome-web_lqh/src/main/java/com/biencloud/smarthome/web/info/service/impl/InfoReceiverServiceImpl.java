package com.biencloud.smarthome.web.info.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.info.service.IInfoReceiverService;
import com.biencloud.smarthome.web.info.service.IInfoSendService;
import com.biencloud.smarthome.web.info.vo.InfoReceiverVO;
import com.biencloud.smarthome.web.info.vo.InfoSendVO;
import com.biencloud.smarthome.web.user.service.IOwnerUserService;
import com.biencloud.smarthome.web.wsclient.stub.InfoReceiver;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 
 * 项目名称：smarthome-web-new 类名称：InfoReceiverServiceImpl 类描述： 信息接收管理领域服务接口
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-6-12 下午2:07:01
 */
public class InfoReceiverServiceImpl extends BaseService<InfoReceiverVO> implements IInfoReceiverService {

	private IInfoSendService infoSendService;

	private IOwnerUserService ownerUserService;

	public static final String[] IGNOREPRO_PERTIES = { "infoSend" };

	@Override
	public PagingVO<InfoReceiverVO> queryInfoReceiverVOForPaging(InfoReceiverVO paramsOb, int pageNum, int pageSize, String userid, String userType) {
		InfoReceiver ob = new InfoReceiver();
		if (paramsOb != null) {
			ob = covertVotoOb(paramsOb);
		}
		Paging paging = getSmartHomeService().queryInfoReceiverForPaging(ob, pageNum, pageSize);
		PagingVO<InfoReceiverVO> pagingVO = convertToVO(paging, IGNOREPRO_PERTIES);
		List<Object> list = paging.getResults();
		if (list != null && list.size() > 0) {
			List<InfoReceiverVO> results = new ArrayList<InfoReceiverVO>();
			for (int index = 0, size = list.size(); index < size; index++) {
				covertObtoVoForPagging((InfoReceiver) list.get(index), userid, userType, results);
			}
			pagingVO.setResults(results);
		}
		return pagingVO;
	}

	private long getHouseId(String userId) {
		return new Long(ownerUserService.getOwnerUserDetail(userId).getHouseId());
	}

	@Override
	public List<InfoReceiverVO> queryInfoReceiverVOForIndex(String loginUserType, String userId, String districtId) {
		InfoReceiver paramsOb = new InfoReceiver();
		if (Constants.LOGIN_USER_TYPE_OWNER.equals(loginUserType)) {// 业主登录
			paramsOb.setStatus(InfoReceiverVO.STATUSNO);
			paramsOb.setHouseId(getHouseId(userId));
		}
		List<InfoReceiver> list = getSmartHomeService().queryInfoReceiverForIndex(paramsOb);
		List<InfoReceiverVO> results = new ArrayList<InfoReceiverVO>();
		if (list != null && list.size() > 0) {
			for (int index = 0, size = list.size(); index < size; index++) {
				results.add(covertObtoVo((InfoReceiver) list.get(index)));
			}
		}
		return results;
	}

	@Override
	public boolean updateInfoReceiverVO(InfoReceiverVO entity) {
		try {
			entity.setStatus(InfoReceiverVO.STATUSYES);
			getSmartHomeService().updateInfoReceiver(covertVotoOb(entity));
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public boolean saveInfoReceiverVO(InfoReceiverVO entity) {
		try {
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delInfoReceiverVO(String id) {
		try {
			getSmartHomeService().delInfoReceiver(getSmartHomeService().getInfoReceiver(id));
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public InfoReceiverVO getInfoReceiverVO(String entityId, String loginUserType) {
		InfoReceiver ob = getSmartHomeService().getInfoReceiver(entityId);
		if (Constants.LOGIN_USER_TYPE_PAUSER.equals(loginUserType) && InfoReceiverVO.INFO_TYPE_PERSON == ob.getReceiverType()) {
		}
		if (Constants.LOGIN_USER_TYPE_OWNER.equals(loginUserType) && InfoReceiverVO.INFO_TYPE_SYSTEM == ob.getReceiverType()) {
		} else {
			ob.setStatus(InfoReceiverVO.STATUSYES);
			getSmartHomeService().updateInfoReceiver(ob);
		}
		return covertObtoVo(ob);
	}

	@Override
	public Set<Long> queryInfoReceiverIdSet(InfoReceiverVO paramsOb, boolean isDistrict) {
		InfoReceiver ob = new InfoReceiver();
		ob.setReceiverType(paramsOb.getReceiverType());
		ob.setInfoSendId(paramsOb.getId());
		List<InfoReceiver> result = getSmartHomeService().queryInfoReceiverForList(ob);
		Set<Long> set = new HashSet<Long>();
		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				if (isDistrict)
					set.add(new Long(result.get(i).getAreaId()));
				else
					set.add(new Long(result.get(i).getHouseId()));
			}
		}
		return set;
	}

	@Override
	public List<InfoReceiverVO> queryInfoReceiverForList(InfoReceiverVO paramsOb) {
		// TODO Auto-generated method stub
		return null;
	}

	public void copyPropertiesObToVo(InfoReceiver ob, InfoReceiverVO vo) {
		this.copyProperties(ob, vo, IGNOREPRO_PERTIES);
	}

	public void copyPropertiesVoToOb(InfoReceiverVO vo, InfoReceiver ob) {
		this.copyProperties(vo, ob, IGNOREPRO_PERTIES);
	}

	private void covertObtoVoForPagging(InfoReceiver dnoOb, String userid, String userType, List<InfoReceiverVO> results) {
		InfoReceiverVO result = new InfoReceiverVO();
		copyPropertiesObToVo(dnoOb, result);
		// 转换对象
		InfoSendVO isVo = infoSendService.getInfoSendVO(dnoOb.getInfoSendId().toString());
		result.setInfoSend(isVo);
		if (InfoReceiverVO.INFO_TYPE_PERSON == isVo.getType()) {
			if (Constants.LOGIN_USER_TYPE_OWNER.equals(userType) && !userid.equals(isVo.getSendUserId().toString()))
				results.add(result);
			if (Constants.LOGIN_USER_TYPE_PAUSER.equals(userType))
				results.add(result);
		} else
			results.add(result);
	}

	private InfoReceiverVO covertObtoVo(InfoReceiver dnoOb) {
		InfoReceiverVO result = new InfoReceiverVO();
		copyPropertiesObToVo(dnoOb, result);
		// 转换对象
		InfoSendVO isVo = infoSendService.getInfoSendVO(dnoOb.getInfoSendId().toString());
		// getInfoSendService().copyPropertiesObToVo(dnoOb.getInfoSend(),isVo);
		result.setInfoSend(isVo);
		return result;
	}

	private InfoReceiver covertVotoOb(InfoReceiverVO entity) {
		InfoReceiver result = new InfoReceiver();
		copyPropertiesVoToOb(entity, result);
		return result;
	}

	public IInfoSendService getInfoSendService() {
		return infoSendService;
	}

	public void setInfoSendService(IInfoSendService infoSendService) {
		this.infoSendService = infoSendService;
	}

	@Override
	public Long getNoReadReceiverCount(String loginUserType, String userId, String districtId) {
		InfoReceiver paramsOb = new InfoReceiver();
		if (Constants.LOGIN_USER_TYPE_OWNER.equals(loginUserType)) {// 业主登录
			paramsOb.setReceiverType(InfoReceiverVO.INFO_TYPE_COMMUNITY);
			paramsOb.setHouseId(getHouseId(userId));
		} else if (Constants.LOGIN_USER_TYPE_PAUSER.equals(loginUserType)) {// 物业登录
			paramsOb.setReceiverType(InfoReceiverVO.INFO_TYPE_SYSTEM);
			paramsOb.setAreaId(new Long(districtId));
		}
		return getSmartHomeService().getNoReadReceiverCount(paramsOb);
	}

	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}

	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
	}

}
