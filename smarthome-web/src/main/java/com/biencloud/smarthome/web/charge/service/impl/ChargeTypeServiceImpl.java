package com.biencloud.smarthome.web.charge.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.charge.service.IChargeTypeService;
import com.biencloud.smarthome.web.charge.vo.ChargeCalModeVO;
import com.biencloud.smarthome.web.charge.vo.ChargeCalUnitVO;
import com.biencloud.smarthome.web.charge.vo.ChargeMonetaryUnitVO;
import com.biencloud.smarthome.web.charge.vo.ChargeTypeVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.user.vo.PaUserVO;
import com.biencloud.smarthome.web.wsclient.stub.ChargeCalMode;
import com.biencloud.smarthome.web.wsclient.stub.ChargeCalUnit;
import com.biencloud.smarthome.web.wsclient.stub.ChargeMonetaryUnit;
import com.biencloud.smarthome.web.wsclient.stub.ChargeType;
import com.biencloud.smarthome.web.wsclient.stub.HousingDistrictInfo;
import com.biencloud.smarthome.web.wsclient.stub.PaUser;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 设备编号管理领域服务接口。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IService
 * @throws RuntimeException
 *             如果操作执行失败
 */
public class ChargeTypeServiceImpl extends BaseService<ChargeTypeVO> implements IChargeTypeService {

	private static final String CREATED_TIME = "createTime";
	private static final String[] IGNOREPRO_PERTIES = { "chargeCalMode", "chargeMonetaryUnit", "chargeCalUnit", "paUser", "housingDistrictInfo" };

	@Override
	public PagingVO<ChargeTypeVO> queryChargeTypeVOForPaging(ChargeTypeVO paramsOb, int pageNum, int pageSize) {
		ChargeType ob = new ChargeType();
		if (paramsOb != null) {
			ob.setName(paramsOb.getName());
			HousingDistrictInfo hdOb = new HousingDistrictInfo();
			hdOb.setId(paramsOb.getHousingDistrictInfo().getId());
			ob.setHousingDistrictInfo(hdOb);
		}
		Paging paging = getSmartHomeService().queryChargeTypeForPaging(ob, pageNum, pageSize);
		PagingVO<ChargeTypeVO> pagingVO = convertToVO(paging, IGNOREPRO_PERTIES, CREATED_TIME);
		List<Object> list = paging.getResults();
		if (list != null && list.size() > 0) {
			List<ChargeTypeVO> results = new ArrayList<ChargeTypeVO>();
			for (int index = 0, size = list.size(); index < size; index++) {
				results.add(covertObtoVo((ChargeType) list.get(index)));
			}
			pagingVO.setResults(results);
		}
		return pagingVO;
	}

	@Override
	public boolean updateChargeTypeVO(ChargeTypeVO entity) {
		try {
			getSmartHomeService().updateChargeType(setFkId(entity));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean saveChargeTypeVO(ChargeTypeVO entity) {
		try {
			getSmartHomeService().saveChargeType(setFkId(entity));
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public boolean delChargeTypeVO(String id) {
		try {
			getSmartHomeService().delChargeType(getSmartHomeService().getChargeType(id));
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public ChargeTypeVO getChargeTypeVO(String entityId) {
		ChargeTypeVO vo = new ChargeTypeVO();
		ChargeType ob = getSmartHomeService().getChargeType(entityId);
		vo = covertObtoVo(ob);
		vo.setChargeMonetaryUnit(vo.getChargeMonetaryUnit());
		return vo;
	}

	@Override
	public List<ChargeTypeVO> queryChargeTypeForList(ChargeTypeVO paramsOb) {
		ChargeType ob = new ChargeType();
		if (paramsOb != null) {
			ob.setName(paramsOb.getName());
			ob.setRemark(paramsOb.getRemark());
		}
		List<ChargeType> result = getSmartHomeService().queryChargeTypeForList(ob);
		List<ChargeTypeVO> results = new ArrayList<ChargeTypeVO>();
		if (result != null && result.size() > 0) {
			for (int index = 0, size = result.size(); index < size; index++) {
				results.add(covertObtoVo((ChargeType) result.get(index)));
			}
		}
		return results;
	}

	private void copyPropertiesObToVo(ChargeType ob, ChargeTypeVO vo) {
		this.copyProperties(ob, vo, IGNOREPRO_PERTIES, true, CREATED_TIME);
	}

	private void copyPropertiesVoToOb(ChargeTypeVO vo, ChargeType ob) {
		this.copyProperties(vo, ob, IGNOREPRO_PERTIES, true, CREATED_TIME);
	}

	private ChargeType setFkId(ChargeTypeVO entity) {
		ChargeType ob = new ChargeType();
		copyPropertiesVoToOb(entity, ob);
		ChargeCalUnit ccuOb = new ChargeCalUnit();
		ccuOb.setId(entity.getChargeCalUnit().getId());
		ob.setChargeCalUnit(ccuOb);
		ChargeCalMode ccmvo = new ChargeCalMode();
		ccmvo.setId(entity.getChargeCalMode().getId());
		ob.setChargeCalMode(ccmvo);
		ChargeMonetaryUnit cmuvo = new ChargeMonetaryUnit();
		cmuvo.setId(entity.getChargeMonetaryUnit().getId());
		ob.setChargeMonetaryUnit(cmuvo);
		PaUser puOb = new PaUser();
		puOb.setUserId(entity.getPaUser().getUserId());
		ob.setPaUser(puOb);
		HousingDistrictInfo hdOb = new HousingDistrictInfo();
		hdOb.setId(entity.getHousingDistrictInfo().getId());
		ob.setHousingDistrictInfo(hdOb);
		return ob;
	}

	private ChargeTypeVO covertObtoVo(ChargeType dnoOb) {
		if (dnoOb == null)
			return null;
		ChargeCalModeVO dOb = new ChargeCalModeVO();
		copyProperties(dnoOb.getChargeCalMode(), dOb, false);
		ChargeMonetaryUnitVO cmuVo = new ChargeMonetaryUnitVO();
		copyProperties(dnoOb.getChargeMonetaryUnit(), cmuVo, false);
		ChargeTypeVO ctVo = new ChargeTypeVO();
		copyPropertiesObToVo(dnoOb, ctVo);
		ctVo.setChargeCalMode(dOb);
		ctVo.setChargeMonetaryUnit(cmuVo);
		PaUserVO puOb = new PaUserVO();
		if (dnoOb.getPaUser() != null) {
			if (dnoOb.getPaUser().getUserId() != null)
				puOb.setUserId(dnoOb.getPaUser().getUserId());
		}
		ctVo.setPaUser(puOb);
		HousingDistrictInfoVo hdVo = new HousingDistrictInfoVo();
		if (dnoOb.getHousingDistrictInfo() != null) {
			if (dnoOb.getHousingDistrictInfo().getId() != null)
				hdVo.setId(dnoOb.getHousingDistrictInfo().getId());
		}
		ctVo.setHousingDistrictInfo(hdVo);
		ChargeCalUnitVO ccuvVo = new ChargeCalUnitVO();
		copyProperties(dnoOb.getChargeCalUnit(), ccuvVo, false);
		ctVo.setChargeCalUnit(ccuvVo);
		return ctVo;
	}

	@Override
	public ChargeTypeVO queryChargeTypeByParams(ChargeTypeVO paramsOb, String comId) {
		ChargeType ob = new ChargeType();
		ob.setName(paramsOb.getName());
		HousingDistrictInfo hdi = new HousingDistrictInfo();
		hdi.setId(comId);
		ob.setHousingDistrictInfo(hdi);
		ChargeType result = getSmartHomeService().queryChargeTypeByParams(ob);
		return covertObtoVo(result);
	}

}
