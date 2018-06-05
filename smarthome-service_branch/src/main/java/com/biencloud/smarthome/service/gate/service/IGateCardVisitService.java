package com.biencloud.smarthome.service.gate.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.gate.model.GateCardVisit;

/**
 * 门卡刷卡管理领域服务接口。
 * @author kouy
 * @since 1.0 2012-5-4
 * @see IService
 * @throws RuntimeException 如果操作执行失败
 */
public interface IGateCardVisitService extends IService<GateCardVisit, String> {

	/**
	 * 查询门卡刷卡记录列表，分页。
	 * @param gateCardVisit 门卡刷卡对象
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public Paging<GateCardVisit> queryGateCardVisitsForPaging(
			GateCardVisit gateCardVisit, int pageNum, int pageSize);
	
	/**
	 * 
	 * 方法的描述: 查询所有门卡刷卡记录
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-22 下午2:51:25
	 * @param gateCardVisit
	 * @return	List<GateCardVisit>
	 */
	public List<GateCardVisit> queryAllGateCardVisits(GateCardVisit gateCardVisit);
}
