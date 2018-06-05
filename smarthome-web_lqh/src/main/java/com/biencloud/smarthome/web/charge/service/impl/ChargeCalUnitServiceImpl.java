package com.biencloud.smarthome.web.charge.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.charge.service.IChargeCalUnitService;
import com.biencloud.smarthome.web.charge.vo.ChargeCalUnitVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.wsclient.stub.ChargeCalUnit;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 收费计算单位管理领域服务接口。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IService
 * @throws RuntimeException
 *             如果操作执行失败
 */
public class ChargeCalUnitServiceImpl extends BaseService<ChargeCalUnitVO> implements IChargeCalUnitService {

	@Override
	public PagingVO<ChargeCalUnitVO> queryChargeCalUnitVOForPaging(ChargeCalUnitVO paramsOb, int pageNum, int pageSize) {
		ChargeCalUnit ob = new ChargeCalUnit();
		if (paramsOb != null) {
			this.copyProperties(paramsOb, ob);
		}
		Paging paging = getSmartHomeService().queryChargeCalUnitForPaging(ob, pageNum, pageSize);
		PagingVO<ChargeCalUnitVO> pagingVO = convertToVO(paging, null);
		return pagingVO;
	}

	@Override
	public boolean updateChargeCalUnitVO(ChargeCalUnitVO entity) {
		try {
			ChargeCalUnit ob = new ChargeCalUnit();
			this.copyProperties(entity, ob);
			getSmartHomeService().updateChargeCalUnit(ob);
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public boolean saveChargeCalUnitVO(ChargeCalUnitVO entity) {
		try {
			ChargeCalUnit ob = new ChargeCalUnit();
			this.copyProperties(entity, ob);
			getSmartHomeService().saveChargeCalUnit(ob);
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public boolean delChargeCalUnitVO(String id) {
		try {
			getSmartHomeService().delChargeCalUnit(getSmartHomeService().getChargeCalUnit(id));
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public ChargeCalUnitVO getChargeCalUnitVO(String entityId) {
		ChargeCalUnitVO vo = new ChargeCalUnitVO();
		copyProperties(getSmartHomeService().getChargeCalUnit(entityId), vo);
		return vo;
	}

	@Override
	public List<ChargeCalUnitVO> queryChargeCalUnitForList(ChargeCalUnitVO paramsOb) {
		ChargeCalUnit ob = new ChargeCalUnit();
		if (paramsOb != null) {
			copyProperties(paramsOb, ob);
		}
		List<ChargeCalUnit> result = getSmartHomeService().queryChargeCalUnitForList(ob);
		List<ChargeCalUnitVO> target = new ArrayList<ChargeCalUnitVO>();
		if(result!=null){
			for (int i = 0; i < result.size(); i++) {
				ChargeCalUnitVO vo = new ChargeCalUnitVO();
				ChargeCalUnit cob = result.get(i);
				copyProperties(cob, vo);
				target.add(vo);
			}
		}
		return target;
	}

	@Override
	public ChargeCalUnitVO queryChargeCalUnitByParams(ChargeCalUnitVO paramsOb) {
		ChargeCalUnit ob = new ChargeCalUnit();
		this.copyProperties(paramsOb, ob);
		ChargeCalUnit result = getSmartHomeService().queryChargeCalUnitByParams(ob);
		ChargeCalUnitVO vo = new ChargeCalUnitVO();
		copyProperties(result, vo);
		return vo;
	}

}
