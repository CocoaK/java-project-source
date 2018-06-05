package com.biencloud.smarthome.web.charge.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.charge.service.IChargeMonetaryUnitService;
import com.biencloud.smarthome.web.charge.vo.ChargeMonetaryUnitVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.wsclient.stub.ChargeMonetaryUnit;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 收费货币单位管理领域服务接口。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IService
 * @throws RuntimeException
 *             如果操作执行失败
 */
public class ChargeMonetaryUnitServiceImpl extends BaseService<ChargeMonetaryUnitVO> implements IChargeMonetaryUnitService {

	@Override
	public PagingVO<ChargeMonetaryUnitVO> queryChargeMonetaryUnitVOForPaging(ChargeMonetaryUnitVO paramsOb, int pageNum, int pageSize) {
		ChargeMonetaryUnit ob = new ChargeMonetaryUnit();
		if (paramsOb != null) {
			ob.setCodeName(paramsOb.getCodeName());
		}
		Paging paging = getSmartHomeService().queryChargeMonetaryUnitForPaging(ob, pageNum, pageSize);
		PagingVO<ChargeMonetaryUnitVO> pagingVO = convertToVO(paging, null);
		return pagingVO;
	}

	@Override
	public boolean updateChargeMonetaryUnitVO(ChargeMonetaryUnitVO entity) {
		try {
			ChargeMonetaryUnit ob = new ChargeMonetaryUnit();
			this.copyProperties(entity, ob);
			getSmartHomeService().updateChargeMonetaryUnit(ob);
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public boolean saveChargeMonetaryUnitVO(ChargeMonetaryUnitVO entity) {
		try {
			ChargeMonetaryUnit ob = new ChargeMonetaryUnit();
			this.copyProperties(entity, ob);
			getSmartHomeService().saveChargeMonetaryUnit(ob);
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public boolean delChargeMonetaryUnitVO(String id) {
		try {
			getSmartHomeService().delChargeMonetaryUnit(getSmartHomeService().getChargeMonetaryUnit(id));
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public ChargeMonetaryUnitVO getChargeMonetaryUnitVO(String entityId) {
		ChargeMonetaryUnitVO vo = new ChargeMonetaryUnitVO();
		copyProperties(getSmartHomeService().getChargeMonetaryUnit(entityId), vo);
		return vo;
	}

	@Override
	public List<ChargeMonetaryUnitVO> queryChargeMonetaryUnitForList(ChargeMonetaryUnitVO paramsOb) {
		ChargeMonetaryUnit ob = new ChargeMonetaryUnit();
		List<ChargeMonetaryUnit> result = getSmartHomeService().queryChargeMonetaryUnitForList(ob);
		List<ChargeMonetaryUnitVO> target = new ArrayList<ChargeMonetaryUnitVO>();
		if (result != null && result.size() > 0) {
			for (int i = 0; i < result.size(); i++) {
				ChargeMonetaryUnitVO vo = new ChargeMonetaryUnitVO();
				ChargeMonetaryUnit cob = result.get(i);
				copyProperties(cob, vo);
				target.add(vo);
			}
		}
		return target;
	}

	@Override
	public ChargeMonetaryUnitVO queryChargeMonetaryUnitByParams(ChargeMonetaryUnitVO paramsOb) {
		ChargeMonetaryUnit ob = new ChargeMonetaryUnit();
		ob.setCodeName(paramsOb.getCodeName());
		ChargeMonetaryUnit result = getSmartHomeService().queryChargeMonetaryUnitByParams(ob);
		ChargeMonetaryUnitVO vo = new ChargeMonetaryUnitVO();
		copyProperties(result, vo);
		return vo;
	}

}
