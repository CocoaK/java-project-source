package com.biencloud.smarthome.web.charge.service.impl;

import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.charge.service.IChargeTypeResultService;
import com.biencloud.smarthome.web.charge.vo.ChargeTypeResultVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.wsclient.stub.ChargeTypeResult;
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
public class ChargeTypeResultServiceImpl extends BaseService<ChargeTypeResultVO> implements IChargeTypeResultService {

	@Override
	public PagingVO<ChargeTypeResultVO> queryChargeTypeResultVOForPaging(ChargeTypeResultVO paramsOb, int pageNum, int pageSize) {
		ChargeTypeResult ob = new ChargeTypeResult();
		Paging paging = getSmartHomeService().queryChargeTypeResultForPaging(ob, pageNum, pageSize);
		PagingVO<ChargeTypeResultVO> pagingVO = convertToVO(paging, null);
		return pagingVO;
	}

	@Override
	public boolean updateChargeTypeResultVO(ChargeTypeResultVO entity) {
		try {
			ChargeTypeResult ob = new ChargeTypeResult();
			this.copyProperties(entity, ob);
			getSmartHomeService().updateChargeTypeResult(ob);
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public boolean saveChargeTypeResultVO(ChargeTypeResultVO entity) {
		try {
			ChargeTypeResult ob = new ChargeTypeResult();
			this.copyProperties(entity, ob);
			getSmartHomeService().saveChargeTypeResult(ob);
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public boolean delChargeTypeResultVO(String id) {
		try {
			getSmartHomeService().delChargeTypeResult(getSmartHomeService().getChargeTypeResult(id));
			return true;
		} catch (Exception e) {
			logger.error("********************发生异常:{}", e);
		}
		return false;
	}

	@Override
	public ChargeTypeResultVO getChargeTypeResultVO(String entityId) {
		ChargeTypeResultVO vo = new ChargeTypeResultVO();
		copyProperties(getSmartHomeService().getChargeTypeResult(entityId), vo);
		return vo;
	}

	@Override
	public List<ChargeTypeResultVO> queryChargeTypeResultForList(ChargeTypeResultVO paramsOb) {
		// TODO Auto-generated method stub
		return null;
	}

}
