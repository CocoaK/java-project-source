package com.biencloud.smarthome.web.charge.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.charge.service.IChargeCalModeService;
import com.biencloud.smarthome.web.charge.vo.ChargeCalModeVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.wsclient.stub.ChargeCalMode;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 收费计算模式管理领域服务接口。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-11
 * @see IService
 * @throws RuntimeException
 *             如果操作执行失败
 */
public class ChargeCalModeServiceImpl extends BaseService<ChargeCalModeVO> implements IChargeCalModeService {

	@Override
	public PagingVO<ChargeCalModeVO> queryChargeCalModeVOForPaging(ChargeCalModeVO paramsOb, int pageNum, int pageSize) {
		ChargeCalMode ob = new ChargeCalMode();
		if (paramsOb != null) {
			this.copyProperties(paramsOb, ob);
		}
		Paging paging = getSmartHomeService().queryChargeCalModeForPaging(ob, pageNum, pageSize);
		PagingVO<ChargeCalModeVO> pagingVO = convertToVO(paging, null);
		return pagingVO;
	}

	@Override
	public boolean updateChargeCalModeVO(ChargeCalModeVO entity) {
		try {
			ChargeCalMode ob = new ChargeCalMode();
			this.copyProperties(entity, ob);
			getSmartHomeService().updateChargeCalMode(ob);
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public boolean saveChargeCalModeVO(ChargeCalModeVO entity) {
		try {
			ChargeCalMode ob = new ChargeCalMode();
			this.copyProperties(entity, ob);
			getSmartHomeService().saveChargeCalMode(ob);
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public boolean delChargeCalModeVO(String id) {
		try {
			getSmartHomeService().delChargeCalMode(getSmartHomeService().getChargeCalMode(id));
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public ChargeCalModeVO getChargeCalModeVO(String entityId) {
		ChargeCalModeVO vo = new ChargeCalModeVO();
		copyProperties(getSmartHomeService().getChargeCalMode(entityId), vo);
		return vo;
	}

	@Override
	public List<ChargeCalModeVO> queryChargeCalModeForList(ChargeCalModeVO paramsOb) {
		ChargeCalMode ob = new ChargeCalMode();
		if (paramsOb != null) {
			copyProperties(paramsOb, ob);
		}
		List<ChargeCalMode> result = getSmartHomeService().queryChargeCalModeForList(ob);
		List<ChargeCalModeVO> target = new ArrayList<ChargeCalModeVO>();
		if(result!=null){
			for (int i = 0; i < result.size(); i++) {
				ChargeCalModeVO vo = new ChargeCalModeVO();
				ChargeCalMode cob = result.get(i);
				copyProperties(cob, vo);
				target.add(vo);
			}
		}
		return target;
	}

	@Override
	public ChargeCalModeVO queryChargeCalModeByParams(ChargeCalModeVO paramsOb) {
		ChargeCalMode ob = new ChargeCalMode();
		this.copyProperties(paramsOb, ob);
		ChargeCalMode result = getSmartHomeService().queryChargeCalModeByParams(ob);
		ChargeCalModeVO vo = new ChargeCalModeVO();
		copyProperties(result, vo);
		return vo;
	}

}
