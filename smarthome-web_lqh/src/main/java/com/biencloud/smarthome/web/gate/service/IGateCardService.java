package com.biencloud.smarthome.web.gate.service;

import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.gate.vo.GateCardVO;

/**
 * 门卡模块调用服务接口。
 * @author kouy
 * @since 1.0 2012-5-7
 */
public interface IGateCardService {
	
	/**
	 * 查询门卡列表，分页。
	 * @param gateCard 门卡对象
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public PagingVO<GateCardVO> queryGateCardsForPaging(
			GateCardVO gateCard, int pageNum, int pageSize);
	
	/**
	 * 获取门卡详细信息。
	 * @param gateCardId 门卡编号
	 * @return
	 */
	public GateCardVO getGateCardDetail(String gateCardId);
	
	/**
	 * 新增门卡详细信息。
	 * @param gateCard 门卡信息
	 */
	public void addGateCard(GateCardVO gateCard);
	
	/**
	 * 更新门卡详细信息。
	 * @param gateCard 门卡信息
	 */
	public void updateGateCard(GateCardVO gateCard);
	
	/**
	 * 删除门卡详细信息。
	 * @param gateCardId 门卡编号
	 */
	public void removeGateCard(String gateCardId);
	
	/**
	 * 更新门卡状态。
	 * @param gateCardId 门卡编号
	 * @param status 门卡状态
	 * @param updatedUser 操作人员登录名
	 */
	public void updateGateCardStatus(String gateCardId, 
			String status, String updatedUser);
	
	/**
	 * 判断门禁卡号是否存在。
	 * @param gateCard 门禁信息
	 * @return
	 */
	public boolean existCardNo(GateCardVO gateCard);
	
	/**
	 * 通过设备代码获取门卡列表。
	 * @param deviceCode 设备代码
	 * @return
	 */
	public List<GateCardVO> queryByDeviceCode(String deviceCode);
}
