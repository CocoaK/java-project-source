package com.biencloud.smarthome.web.charge.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.charge.service.IChargeDataService;
import com.biencloud.smarthome.web.charge.service.IChargeDetailService;
import com.biencloud.smarthome.web.charge.vo.ChargeDataVO;
import com.biencloud.smarthome.web.charge.vo.ChargeDetailVO;
import com.biencloud.smarthome.web.charge.vo.ChargeStatiticsVO;
import com.biencloud.smarthome.web.charge.vo.ChargeTypeResultVO;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.util.HouseManagementModelVoConvert;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.RegionBuildingInfoVo;
import com.biencloud.smarthome.web.push.vo.PushVO;
import com.biencloud.smarthome.web.wsclient.stub.CellHouseholdInfo;
import com.biencloud.smarthome.web.wsclient.stub.ChargeData;
import com.biencloud.smarthome.web.wsclient.stub.ChargeDetail;
import com.biencloud.smarthome.web.wsclient.stub.ChargeTypeResult;
import com.biencloud.smarthome.web.wsclient.stub.HousingDistrictInfo;
import com.biencloud.smarthome.web.wsclient.stub.OwnerUser;
import com.biencloud.smarthome.web.wsclient.stub.PaUser;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.Push;
import com.biencloud.smarthome.web.wsclient.stub.RegionBuildingInfo;

/**
 * 设备编号管理领域服务接口。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IService
 * @throws RuntimeException
 *             如果操作执行失败
 */
public class ChargeDetailServiceImpl extends BaseService<ChargeDetailVO> implements IChargeDetailService {
	private IChargeDataService chargeDataService;

	private static final String[] IGNOREPRO_PERTIES = { "chargeData", "chargeTypeResults" };

	@Override
	public PagingVO<ChargeDetailVO> queryChargeDetailVOForPaging(ChargeDetailVO paramsOb, int pageNum, int pageSize) {
		ChargeDetail ob = new ChargeDetail();
		if (paramsOb != null) {
			ob = covertVotoOb(paramsOb);
		}
		Paging paging = getSmartHomeService().queryChargeDetailForPaging(ob, pageNum, pageSize);
		PagingVO<ChargeDetailVO> pagingVO = convertToVO(paging, IGNOREPRO_PERTIES);
		List<Object> list = paging.getResults();
		if (list != null && list.size() > 0) {
			List<ChargeDetailVO> results = new ArrayList<ChargeDetailVO>();
			for (int index = 0, size = list.size(); index < size; index++) {
				results.add(covertObtoVo((ChargeDetail) list.get(index)));
			}
			pagingVO.setResults(results);
		}
		return pagingVO;
	}

	@Override
	public List<ChargeDetailVO> queryChargeDetailVOForIndex(String loginUserType, String userId, String districtId) {
		ChargeDetail ob = new ChargeDetail();
		if (Constants.LOGIN_USER_TYPE_OWNER.equals(loginUserType)) {// 业主登录
			ChargeData cd = new ChargeData();
			CellHouseholdInfo chi = new CellHouseholdInfo();
			OwnerUser ou = new OwnerUser();
			ou.setUserId(userId);
			chi.setOwner(ou);
			cd.setCellHouseholdInfo(chi);
			// ob.setChargeStatus(ChargeDetailVO.FeeStatusYES.toString());
			ob.setChargeData(cd);
		}
		Paging paging = getSmartHomeService().queryChargeDetailForPaging(ob, 1, Constants.SELECT_COUNT_FOR_INDEX);
		PagingVO<ChargeDetailVO> pagingVO = convertToVO(paging, IGNOREPRO_PERTIES);
		List<ChargeDetailVO> results = new ArrayList<ChargeDetailVO>();
		List<Object> list = paging.getResults();
		if (list != null && list.size() > 0) {
			for (int index = 0, size = list.size(); index < size; index++) {
				results.add(covertObtoVo((ChargeDetail) list.get(index)));
			}
			pagingVO.setResults(results);
		}
		return results;
	}

	@Override
	public boolean saveChargeDetailVO(ChargeDetailVO entity) {
		try {
			ChargeDetail ob = new ChargeDetail();
			this.copyProperties(entity, ob);
			getSmartHomeService().saveChargeDetail(ob);
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public boolean delChargeDetailVO(String id) {
		try {
			getSmartHomeService().delChargeDetail(getSmartHomeService().getChargeDetail(id));
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public ChargeDetailVO getChargeDetailVO(String entityId) {
		ChargeDetail ob = getSmartHomeService().getChargeDetail(entityId);
		return covertObtoVo(ob);
	}

	@Override
	public List<ChargeDetailVO> queryChargeDetailForList(ChargeDetailVO paramsOb) {
		ChargeDetail ob = new ChargeDetail();
		if (paramsOb != null) {
			ob = covertVotoOb(paramsOb);
		}
		List<ChargeDetail> list = getSmartHomeService().queryChargeDetailForList(ob);
		List<ChargeDetailVO> results = new ArrayList<ChargeDetailVO>();
		if (list != null && list.size() > 0) {
			for (int index = 0, size = list.size(); index < size; index++) {
				results.add(covertObtoVo((ChargeDetail) list.get(index)));
			}
		}
		return results;
	}

	private void copyPropertiesObToVo(ChargeDetail ob, ChargeDetailVO vo) {
		this.copyProperties(ob, vo, IGNOREPRO_PERTIES);
	}

	private void copyPropertiesVoToOb(ChargeDetailVO vo, ChargeDetail ob) {
		this.copyProperties(vo, ob, IGNOREPRO_PERTIES);
	}

	private ChargeDetailVO covertObtoVo(ChargeDetail dnoOb) {
		ChargeDetailVO result = new ChargeDetailVO();
		copyPropertiesObToVo(dnoOb, result);
		// 转换对象
		ChargeDataVO dataVo = new ChargeDataVO();
		chargeDataService.copyPropertiesObToVo(dnoOb.getChargeData(), dataVo);
		RegionBuildingInfoVo bciVo = HouseManagementModelVoConvert.regionBuildingInfo2Vo(dnoOb.getChargeData().getRegionBuildingInfo());
		dataVo.setRegionBuildingInfo(bciVo);
		CellHouseholdInfoVo chiVo = HouseManagementModelVoConvert.house2VoLite(dnoOb.getChargeData().getCellHouseholdInfo());
		dataVo.setCellHouseholdInfo(chiVo);
		chiVo.setName(dnoOb.getChargeData().getCellHouseholdInfo().getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getName() + dnoOb.getChargeData().getCellHouseholdInfo().getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getName() + dnoOb.getChargeData().getCellHouseholdInfo().getTHmBuildingCellInfo().getName() + dnoOb.getChargeData().getCellHouseholdInfo().getName());
		result.setChargeData(dataVo);
		// 转换列表
		List<ChargeTypeResult> ctrObList = dnoOb.getChargeTypeResults();
		List<ChargeTypeResultVO> ctrVoList = new ArrayList<ChargeTypeResultVO>();
		if (ctrObList != null && ctrObList.size() > 0) {
			for (int i = 0; i < ctrObList.size(); i++) {
				ChargeTypeResultVO ctrVo = new ChargeTypeResultVO();
				copyProperties(ctrObList.get(i), ctrVo);
				ctrVoList.add(ctrVo);
			}
		}
		result.setChargeTypeResults(ctrVoList);
		return result;
	}

	private ChargeDetail covertVotoOb(ChargeDetailVO entity) {
		ChargeDetail result = new ChargeDetail();
		copyPropertiesVoToOb(entity, result);
		// 转换对象
		ChargeData dataOb = new ChargeData();
		chargeDataService.copyPropertiesVoToOb(entity.getChargeData(), dataOb);
		RegionBuildingInfo bciOb = new RegionBuildingInfo();
		if (entity.getChargeData().getRegionBuildingInfo() != null) {
			bciOb.setId(entity.getChargeData().getRegionBuildingInfo().getId());
			bciOb.setName(entity.getChargeData().getRegionBuildingInfo().getName());
		}
		dataOb.setRegionBuildingInfo(bciOb);
		CellHouseholdInfo chiOb = new CellHouseholdInfo();
		if (entity.getChargeData().getCellHouseholdInfo() != null) {
			if (entity.getChargeData().getCellHouseholdInfo().getId() != null)
				chiOb.setId(entity.getChargeData().getCellHouseholdInfo().getId());
			chiOb.setName(entity.getChargeData().getCellHouseholdInfo().getName());
			if (entity.getChargeData().getCellHouseholdInfo().getOwner() != null) {
				OwnerUser ou = new OwnerUser();
				ou.setUserId(entity.getChargeData().getCellHouseholdInfo().getOwner().getUserId());
				chiOb.setOwner(ou);
			}
		}
		PaUser puOb = new PaUser();
		if (entity.getChargeData().getPaUser() != null) {
			if (entity.getChargeData().getPaUser().getUserId() != null)
				puOb.setUserId(entity.getChargeData().getPaUser().getUserId());
		}
		dataOb.setPaUser(puOb);
		HousingDistrictInfo hdOb = new HousingDistrictInfo();
		if (entity.getChargeData().getHousingDistrictInfo() != null) {
			if (entity.getChargeData().getHousingDistrictInfo().getId() != null)
				hdOb.setId(entity.getChargeData().getHousingDistrictInfo().getId());
		}
		dataOb.setHousingDistrictInfo(hdOb);
		dataOb.setCellHouseholdInfo(chiOb);
		result.setChargeData(dataOb);
		return result;
	}

	@Override
	public ChargeStatiticsVO statisticsChargeForIndex(String loginUserType, String userId, String districtId) {
		ChargeData ob = new ChargeData();
		HousingDistrictInfo hdOb = new HousingDistrictInfo();
		hdOb.setId(districtId);
		ob.setHousingDistrictInfo(hdOb);
		ChargeDetail cd = new ChargeDetail();
		cd.setChargeData(ob);
		cd.setChargeStatus(ChargeDetailVO.FeeStatusYES.toString());
		List<ChargeDetail> result = getSmartHomeService().queryChargeDetailForList(cd);
		ChargeStatiticsVO resultOb = new ChargeStatiticsVO();
		Double acMoney = 0.0;// 实收
		Double feeMoney = 0.0;// 欠费
		for (int i = 0; i < result.size(); i++) {
			acMoney = acMoney + new Double(result.get(i).getChargeData().getTotalMoney());
		}
		cd.setChargeStatus(ChargeDetailVO.FeeStatusNO.toString());
		result = getSmartHomeService().queryChargeDetailForList(cd);
		for (int i = 0; i < result.size(); i++) {
			feeMoney = feeMoney + new Double(result.get(i).getChargeData().getTotalMoney());
		}
		resultOb.setAcTotalMoney(acMoney);
		resultOb.setFeeTotalMoney(feeMoney);
		return resultOb;
	}

	@Override
	public boolean updateChargeDetailVO(String id) {
		try {
			ChargeDetail entity = getSmartHomeService().getChargeDetail(id);
			entity.setChargeStatus(ChargeDetailVO.FeeStatusYES.toString());
			entity.setChargeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			getSmartHomeService().updateChargeDetail(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public IChargeDataService getChargeDataService() {
		return chargeDataService;
	}

	public void setChargeDataService(IChargeDataService chargeDataService) {
		this.chargeDataService = chargeDataService;
	}

	@Override
	public boolean publishChargeInfo(PushVO pushOb, String roomId, String id) {
		try {
			Push ob = new Push();
			ob.setPushContent(pushOb.getPushContent());
			boolean result = getSmartHomeService().publishChargeInfo(ob, roomId);
			if (result) {
				ChargeDetail cob = getSmartHomeService().getChargeDetail(id);
				cob.setInfoId(new Long(cob.getInfoId() == null ? 1 : cob.getInfoId() + 1));
				getSmartHomeService().updateChargeDetail(cob);
			}
			return result;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
			return false;
		}
	}

}
