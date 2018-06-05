package com.biencloud.smarthome.web.gate.service;

import java.util.List;

import com.biencloud.smarthome.web.appdata.json.Json;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.gate.vo.GateCardVisitVO;

/**
 * 门卡刷卡管理调用服务接口。
 * @author kouy
 * @since 1.0 2012-5-10
 */
public interface IGateCardVisitService {

	/**
	 * 查询门卡刷卡记录列表，分页。
	 * @param gateCardVisit 门卡刷卡对象
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public PagingVO<GateCardVisitVO> queryGateCardVisitsForPaging(
			GateCardVisitVO gateCardVisit, int pageNum, int pageSize);
	
	/**
	 * 获取门卡刷卡详细信息。
	 * @param visitId 门卡刷卡编号
	 * @return
	 */
	public GateCardVisitVO getGateCardVisitDetail(String visitId);
	
	/**
	 * 
	 * 方法的描述: 查询所有门卡记录
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-22 下午3:59:25
	 * @return List
	 */
	public List<GateCardVisitVO> queryGateCardVisits(GateCardVisitVO gateCardVisit);
	
	/**
	 * 
	 * 方法的描述: 保存门卡刷卡记录
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-22 下午4:00:32
	 * @param gateCardVisitVO
	 */
	public void saveGateCardVisit(GateCardVisitVO gateCardVisitVO);
	
	/**
	 * 
	 * 方法的描述: 保存刷卡记录
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-22 下午5:34:31
	 * @param gateCardVisitVO
	 */
	public void updateGateCardVisit(GateCardVisitVO gateCardVisitVO);
	
	/**
	 * 
	 * 方法的描述: 删除门卡刷卡记录
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-22 下午4:01:28
	 * @param gateCardVisitVO
	 */
	public void removeGateCardVisit(GateCardVisitVO gateCardVisitVO);
	
	/**
	 * 
	 * 方法的描述: 根据json保存门卡刷卡数据
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-26 上午10:00:45
	 * @param jsonString
	 * @return
	 */
	public Json saveGateCardVisit(String jsonString);
}
