package com.biencloud.smarthome.service.gate.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.gate.model.GateCard;

/**
 * 门禁管理领域服务接口。
 * @author kouy
 * @since 1.0 2012-5-4
 * @see IService
 * @throws RuntimeException 如果操作执行失败
 */
public interface IGateCardService extends IService<GateCard, String> {

	/**
	 * 查询门卡列表，分页。
	 * @param gateCard 门卡对象
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public Paging<GateCard> queryGateCardsForPaging(GateCard gateCard, int pageNum, int pageSize);
	
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
	 * @param cardNo 门禁卡号
	 * @return
	 */
	public boolean existCardNo(GateCard gateCard);
	
	/**
	 * 通过设备代码获取门卡列表。
	 * @param deviceCode 设备代码
	 * @return
	 */
	public List<GateCard> queryByDeviceCode(String deviceCode);
	
	/**
	 * 删除指定设备编号列表关联的门禁权限。
	 * @param deviceIds 设备编号列表
	 */
	public void removeGatePermissionsDevices(List<String> deviceIds);
	
	/**
	 * 根据卡号查询门卡信息
	 * @param cardNo 门卡卡号
	 */
	public GateCard queryGateCardByCardNo(String districtId, String cardNo);
}
