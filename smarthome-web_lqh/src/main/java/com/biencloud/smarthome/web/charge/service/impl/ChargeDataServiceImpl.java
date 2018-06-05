package com.biencloud.smarthome.web.charge.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.charge.service.IChargeDataService;
import com.biencloud.smarthome.web.charge.vo.ChargeDataVO;
import com.biencloud.smarthome.web.charge.vo.ChargeStatiticsVO;
import com.biencloud.smarthome.web.charge.vo.ChargeTypeResultVO;
import com.biencloud.smarthome.web.charge.vo.ChargeTypeVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.util.HouseManagementModelVoConvert;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.housemgr.vo.RegionBuildingInfoVo;
import com.biencloud.smarthome.web.user.vo.PaUserVO;
import com.biencloud.smarthome.web.wsclient.stub.CellHouseholdInfo;
import com.biencloud.smarthome.web.wsclient.stub.ChargeData;
import com.biencloud.smarthome.web.wsclient.stub.ChargeStatitics;
import com.biencloud.smarthome.web.wsclient.stub.ChargeTypeResult;
import com.biencloud.smarthome.web.wsclient.stub.HousingDistrictInfo;
import com.biencloud.smarthome.web.wsclient.stub.PaUser;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
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
public class ChargeDataServiceImpl extends BaseService<ChargeDataVO> implements IChargeDataService {

	public static final String[] CREATED_TIME = { "createTime", "createStartTime", "createEndTime" };
	public static final String[] IGNOREPRO_PERTIES = { "regionBuildingInfo", "cellHouseholdInfo", "chargeTypeResults", "paUser", "housingDistrictInfo" };

	@Override
	public PagingVO<ChargeDataVO> queryChargeDataVOForPaging(ChargeDataVO paramsOb, int pageNum, int pageSize) {
		ChargeData ob = new ChargeData();
		if (paramsOb != null) {
			ob = covertVotoOb(paramsOb);
		}
		Paging paging = getSmartHomeService().queryChargeDataForPaging(ob, pageNum, pageSize);
		PagingVO<ChargeDataVO> pagingVO = convertToVO(paging, IGNOREPRO_PERTIES, CREATED_TIME);
		List<Object> list = paging.getResults();
		if (list != null && list.size() > 0) {
			List<ChargeDataVO> results = new ArrayList<ChargeDataVO>();
			for (int index = 0, size = list.size(); index < size; index++) {
				results.add(covertObtoVo((ChargeData) list.get(index)));
			}
			pagingVO.setResults(results);
		}
		return pagingVO;
	}

	@Override
	public boolean updateChargeDataVO(ChargeDataVO entity) {
		try {
			setChargeDataVO(entity);
			getSmartHomeService().updateChargeData(covertVotoOb(entity));
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public boolean saveChargeDataVO(ChargeDataVO entity) {
		try {
			setChargeDataVO(entity);
			java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
			entity.setCreateTime(now);
			entity.setIsproductDetail(ChargeDataVO.NO.toString());
			getSmartHomeService().saveChargeData(covertVotoOb(entity));
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isExistForChargeTimeByUserId(String houseId, String chargeTime) {
		try {
			ChargeData cd = new ChargeData();
			CellHouseholdInfo chi = new CellHouseholdInfo();
			chi.setId(houseId);
			cd.setCellHouseholdInfo(chi);
			cd.setChargeTime(chargeTime);
			List result = getSmartHomeService().queryChargeDataForList(cd);
			if (result!=null&&!result.isEmpty())
				return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	private void setChargeDataVO(ChargeDataVO entity) {
		// 计费付费金额
		List<ChargeTypeResultVO> reList = entity.getChargeTypeResultVOs();
		List<ChargeTypeResultVO> resultList = new ArrayList<ChargeTypeResultVO>();
		BigDecimal money = new BigDecimal(0);
		if (reList != null && reList.size() > 0) {
			for (int i = 0; i < reList.size(); i++) {
				ChargeTypeResultVO reOb = reList.get(i);
				String calUnit = reOb.getCalUnit();
				if (ChargeTypeVO.QUARTER[0].equals(calUnit) || ChargeTypeVO.QUARTER[1].equals(calUnit) || ChargeTypeVO.QUARTER[2].equals(calUnit) || ChargeTypeVO.QUARTER[3].equals(calUnit)) {
					money = money.add(new BigDecimal(reOb.getStandard()));
				} else {
					BigDecimal current = new BigDecimal(reOb.getActualTotal()).multiply(new BigDecimal(reOb.getStandard()));
					money = money.add(current);
					reOb.setPlayMoney(current.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}
				resultList.add(reOb);
			}
			entity.setChargeTypeResultVOs(resultList);
			entity.setTotalMoney(money.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		}
	}

	@Override
	public boolean delChargeDataVO(String id) {
		try {
			getSmartHomeService().delChargeData(getSmartHomeService().getChargeData(id));
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public ChargeDataVO getChargeDataVO(String entityId) {
		ChargeData ob = getSmartHomeService().getChargeData(entityId);
		return covertObtoVo(ob);
	}

	@Override
	public List<ChargeDataVO> queryChargeDataForList(ChargeDataVO paramsOb) {
		ChargeData ob = new ChargeData();
		if (paramsOb != null) {
			ob = covertVotoOb(paramsOb);
		}
		List<ChargeData> list = getSmartHomeService().queryChargeDataForList(ob);
		List<ChargeDataVO> results = new ArrayList<ChargeDataVO>();
		if (list != null && list.size() > 0) {
			for (int index = 0, size = list.size(); index < size; index++) {
				results.add(covertObtoVo((ChargeData) list.get(index)));
			}
		}
		return results;
	}

	public void copyPropertiesObToVo(ChargeData ob, ChargeDataVO vo) {
		this.copyProperties(ob, vo, IGNOREPRO_PERTIES, true, CREATED_TIME);
	}

	public void copyPropertiesVoToOb(ChargeDataVO vo, ChargeData ob) {
		this.copyProperties(vo, ob, IGNOREPRO_PERTIES, false, CREATED_TIME);
	}

	private ChargeDataVO covertObtoVo(ChargeData dnoOb) {
		ChargeDataVO result = new ChargeDataVO();
		copyPropertiesObToVo(dnoOb, result);
		// 转换对象
		RegionBuildingInfoVo bciVo = HouseManagementModelVoConvert.regionBuildingInfo2Vo(dnoOb.getRegionBuildingInfo());
		result.setRegionBuildingInfo(bciVo);
		CellHouseholdInfoVo chiVo = HouseManagementModelVoConvert.house2VoLite(dnoOb.getCellHouseholdInfo());
		result.setCellHouseholdInfo(chiVo);
		// 设置房号的完整路径
		chiVo.setName(dnoOb.getCellHouseholdInfo().getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getTHmHousingDistrictRegionInfo().getName() + dnoOb.getCellHouseholdInfo().getTHmBuildingCellInfo().getTHmRegionBuildingInfo().getName() + dnoOb.getCellHouseholdInfo().getTHmBuildingCellInfo().getName() + dnoOb.getCellHouseholdInfo().getName());
		PaUserVO puOb = new PaUserVO();
		if (dnoOb.getPaUser() != null) {
			if (dnoOb.getPaUser().getUserId() != null)
				puOb.setUserId(dnoOb.getPaUser().getUserId());
		}
		result.setPaUser(puOb);
		HousingDistrictInfoVo hdVo = new HousingDistrictInfoVo();
		if (dnoOb.getHousingDistrictInfo() != null) {
			if (dnoOb.getHousingDistrictInfo().getId() != null)
				hdVo.setId(dnoOb.getHousingDistrictInfo().getId());
		}
		result.setHousingDistrictInfo(hdVo);
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
		// copyProperty(result, "chargeTypeResults", ctrObList);
		result.setChargeTypeResultVOs(ctrVoList);
		return result;
	}

	private ChargeData covertVotoOb(ChargeDataVO entity) {
		ChargeData result = new ChargeData();
		copyPropertiesVoToOb(entity, result);
		// 转换对象
		RegionBuildingInfo bciOb = new RegionBuildingInfo();
		if (entity.getRegionBuildingInfo() != null) {
			bciOb.setId(entity.getRegionBuildingInfo().getId());
			bciOb.setName(entity.getRegionBuildingInfo().getName());
		}
		result.setRegionBuildingInfo(bciOb);
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
		HousingDistrictInfo hdOb = new HousingDistrictInfo();
		if (entity.getHousingDistrictInfo() != null) {
			if (entity.getHousingDistrictInfo().getId() != null)
				hdOb.setId(entity.getHousingDistrictInfo().getId());
		}
		result.setHousingDistrictInfo(hdOb);
		// 转换列表
		List<ChargeTypeResultVO> ctrVoList = entity.getChargeTypeResultVOs();
		// List<ChargeTypeResult> ctrObList=new ArrayList<ChargeTypeResult>();
		List<ChargeTypeResult> ctrObList = result.getChargeTypeResults();
		if (ctrVoList != null) {
			for (int i = 0; i < ctrVoList.size(); i++) {
				ChargeTypeResult ctrOb = new ChargeTypeResult();
				copyProperties(ctrVoList.get(i), ctrOb, true);
				ctrObList.add(ctrOb);
			}
		}
		// copyProperty(result, "chargeTypeResults", ctrObList);
		// result.setChargeTypeResults(ctrObList);
		return result;
	}

	/*
	 * private ChargeData setFkId(ChargeDataVO entity){ ChargeData ob=new
	 * ChargeData(); copyPropertiesVoToOb(entity, ob); RegionBuildingInfo
	 * bciOb=new RegionBuildingInfo();
	 * bciOb.setId(entity.getRegionBuildingInfo().getId());
	 * ob.setRegionBuildingInfo(bciOb); CellHouseholdInfo chiOb=new
	 * CellHouseholdInfo(); chiOb.setId(new
	 * Integer(entity.getCellHouseholdInfo().getId()));
	 * ob.setCellHouseholdInfo(chiOb); return ob; }
	 */
	@Override
	public List<ChargeStatiticsVO> statisticsCharge(ChargeDataVO paramsOb) {
		ChargeData ob = covertVotoOb(paramsOb);
		return getStatisticsCharge(ob);
	}

	private List<ChargeStatiticsVO> getStatisticsCharge(ChargeData ob) {
		List<ChargeStatitics> resultList = getSmartHomeService().statisticsCharge(ob);
		List<ChargeStatiticsVO> result = new ArrayList<ChargeStatiticsVO>();
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				ChargeStatitics csOb = resultList.get(i);
				ChargeStatiticsVO vo = new ChargeStatiticsVO();
				copyProperties(csOb, vo);
				result.add(vo);
			}
		}
		return result;
	}

}
