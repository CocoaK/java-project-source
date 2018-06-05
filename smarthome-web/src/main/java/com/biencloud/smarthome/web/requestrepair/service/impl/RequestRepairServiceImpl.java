package com.biencloud.smarthome.web.requestrepair.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.util.HouseManagementModelVoConvert;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.requestrepair.service.IRequestRepairService;
import com.biencloud.smarthome.web.requestrepair.vo.RequestRepairVO;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;
import com.biencloud.smarthome.web.user.vo.PaUserVO;
import com.biencloud.smarthome.web.wsclient.stub.CellHouseholdInfo;
import com.biencloud.smarthome.web.wsclient.stub.OwnerUser;
import com.biencloud.smarthome.web.wsclient.stub.PaUser;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.RequestRepair;

/**
 * 
 * 项目名称：smarthome-web-new 类名称：RequestRepairServiceImpl 类描述： 报修管理领域服务接口
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-6-12 下午2:22:12
 */
public class RequestRepairServiceImpl extends BaseService<RequestRepairVO> implements IRequestRepairService {

	private IRequestRepairService requestRepairService;
	private static final String[] TIMES = { "repairEndTime", "repairStartTime", "repairTime", "requestEndTime", "requestStartTime", "requestTime" };
	private static final String[] IGNOREPRO_PERTIES = { "cellHouseholdInfo", "ownerUser", "paUser" };
	private static final String[] OWNER_IGNOREPRO_PERTIES = { "birthDate", "createdTime", "updatedTime", "login" };
	public static final String splitFlag = "A";

	@Override
	public PagingVO<RequestRepairVO> queryRequestRepairVOForPaging(RequestRepairVO paramsOb, int pageNum, int pageSize) {
		RequestRepair ob = new RequestRepair();
		if (paramsOb != null) {
			ob = covertVotoOb(paramsOb);
		}
		Paging paging = getSmartHomeService().queryRequestRepairForPaging(ob, pageNum, pageSize);
		PagingVO<RequestRepairVO> pagingVO = convertToVO(paging, IGNOREPRO_PERTIES, TIMES);
		List<Object> list = paging.getResults();
		if (list != null && list.size() > 0) {
			List<RequestRepairVO> results = new ArrayList<RequestRepairVO>();
			for (int index = 0, size = list.size(); index < size; index++) {
				results.add(covertObtoVo((RequestRepair) list.get(index)));
			}
			pagingVO.setResults(results);
		}
		return pagingVO;
	}

	/**
	 * 业主查询报修数
	 * 
	 * @return
	 */
	@Override
	public Long getRequestRepairCount(String loginUserType, String userId, String districtId) {
		RequestRepair paramsOb = new RequestRepair();
		if (Constants.LOGIN_USER_TYPE_OWNER.equals(loginUserType)) {// 业主登录
			OwnerUser ou = new OwnerUser();
			ou.setUserId(userId);
			paramsOb.setOwnerUser(ou);
		}
		return getSmartHomeService().getRequestRepairCount(paramsOb);
	}

	@Override
	public List<RequestRepairVO> queryRequestRepairVOForIndex(String loginUserType, String userId, String districtId) {
		RequestRepair ob = new RequestRepair();
		if (Constants.LOGIN_USER_TYPE_PAUSER.equals(loginUserType)) {// 物业管理员登录
			ob.setDistrictId(new Long(districtId));
			ob.setRequestTime(this.convertToXMLGregorianCalendar(new Date()));
		} else if (Constants.LOGIN_USER_TYPE_OWNER.equals(loginUserType)) {// 业主登录
			OwnerUser ou = new OwnerUser();
			ou.setUserId(userId);
			ob.setOwnerUser(ou);
		}
		Paging paging = getSmartHomeService().queryRequestRepairForPaging(ob, 1, Constants.SELECT_COUNT_FOR_INDEX);
		List<Object> list = paging.getResults();
		List<RequestRepairVO> results = new ArrayList<RequestRepairVO>();
		if (list != null && list.size() > 0) {
			for (int index = 0, size = list.size(); index < size; index++) {
				results.add(covertObtoVo((RequestRepair) list.get(index)));
			}
		}
		return results;
	}

	@Override
	public boolean updateRequestRepairVO(RequestRepairVO entity) {
		try {
			RequestRepair ob = covertVotoOb(entity);
			if (ob.getPaUser() != null && ob.getPaUser().getUserId() == null)
				ob.setPaUser(null);
			if (ob.getOwnerUser() != null && ob.getOwnerUser().getUserId() == null)
				ob.setOwnerUser(null);
			getSmartHomeService().updateRequestRepair(ob);
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public boolean saveRequestRepairVO(RequestRepairVO entity) {
		try {
			RequestRepair ob = covertVotoOb(entity);
			getSmartHomeService().saveRequestRepair(ob);
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public boolean delRequestRepairVO(String id) {
		try {
			getSmartHomeService().delRequestRepair(getSmartHomeService().getRequestRepair(id));
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public RequestRepairVO getRequestRepairVO(String entityId) {
		RequestRepair ob = getSmartHomeService().getRequestRepair(entityId);
		return covertObtoVo(ob);
	}

	@Override
	public List<RequestRepairVO> queryRequestRepairForList(RequestRepairVO paramsOb) {
		// TODO Auto-generated method stub
		return null;
	}

	public void copyPropertiesObToVo(RequestRepair ob, RequestRepairVO vo) {
		this.copyProperties(ob, vo, IGNOREPRO_PERTIES, true, TIMES);
	}

	public void copyPropertiesVoToOb(RequestRepairVO vo, RequestRepair ob) {
		this.copyProperties(vo, ob, IGNOREPRO_PERTIES, false, TIMES);
	}

	private RequestRepairVO covertObtoVo(RequestRepair dnoOb) {
		RequestRepairVO result = new RequestRepairVO();
		copyPropertiesObToVo(dnoOb, result);
		// 转换列表
		CellHouseholdInfoVo chiVo = HouseManagementModelVoConvert.house2VoLite(dnoOb.getCellHouseholdInfo());
		chiVo.setName(dnoOb.getCellHouseholdInfo().getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getName() + dnoOb.getCellHouseholdInfo().getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getName() + dnoOb.getCellHouseholdInfo().getTHmBuildingCellInfo().getName() + dnoOb.getCellHouseholdInfo().getName());
		result.setCellHouseholdInfo(chiVo);
		PaUserVO puOb = new PaUserVO();
		if (dnoOb.getPaUser() != null) {
			if (dnoOb.getPaUser().getUserId() != null)
				puOb.setUserId(dnoOb.getPaUser().getUserId());
			puOb.setUserName(dnoOb.getPaUser().getUserName());
		}
		result.setPaUser(puOb);
		OwnerUserVO onOb = new OwnerUserVO();
		if (dnoOb.getOwnerUser() != null) {
			/*
			 * if(dnoOb.getOwnerUser().getUserId()!=null)
			 * onOb.setUserId(dnoOb.getOwnerUser().getUserId());
			 * onOb.setUserName(dnoOb.getOwnerUser().getUserName());
			 */
			copyProperties(dnoOb.getOwnerUser(), onOb, OWNER_IGNOREPRO_PERTIES);
		}
		result.setOwnerUser(onOb);
		return result;
	}

	private RequestRepair covertVotoOb(RequestRepairVO entity) {
		RequestRepair result = new RequestRepair();
		copyPropertiesVoToOb(entity, result);
		CellHouseholdInfo chiOb = new CellHouseholdInfo();
		if (entity.getCellHouseholdInfo() != null) {
			if (entity.getCellHouseholdInfo().getId() != null)
				chiOb.setId(entity.getCellHouseholdInfo().getId());
			chiOb.setName(entity.getCellHouseholdInfo().getName());
		}
		result.setCellHouseholdInfo(chiOb);
		PaUser puOb = new PaUser();
		if (entity.getPaUser() != null) {
			if (entity.getPaUser().getUserId() != null)
				puOb.setUserId(entity.getPaUser().getUserId());
		}
		result.setPaUser(puOb);
		OwnerUser onOb = new OwnerUser();
		if (entity.getOwnerUser() != null) {
			/*
			 * if(dnoOb.getOwnerUser().getUserId()!=null)
			 * onOb.setUserId(dnoOb.getOwnerUser().getUserId());
			 * onOb.setUserName(dnoOb.getOwnerUser().getUserName());
			 */
			copyProperties(entity.getOwnerUser(), onOb, OWNER_IGNOREPRO_PERTIES);
		}
		result.setOwnerUser(onOb);
		return result;
	}

	public IRequestRepairService getRequestRepairService() {
		return requestRepairService;
	}

	public void setRequestRepairService(IRequestRepairService requestRepairService) {
		this.requestRepairService = requestRepairService;
	}
}
