package com.biencloud.smarthome.service.softwareupgrade.service;

import java.util.Date;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.softwareupgrade.model.SoftwareUpgrade;

/**
 * 软件升级模块领域服务接口。
 * @author kouy
 * @since 1.0 2012-4-23
 * @see IService
 * @throws RuntimeException 当方法执行发生异常时
 */
public interface ISoftwareUpgradeService extends IService<SoftwareUpgrade, String> {

	/**
	 * 查询软件升级列表，分页。
	 * @param softwareName 软件名称，如果为空不作为查询条件，否则作为模糊查询条件
	 * @param deviceType 设备类型，如果为空不作为查询条件
	 * @param status 状态，如果为空不作为查询条件
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return 分页信息
	 */
	public Paging<SoftwareUpgrade> querySoftwaresForPaging(String softwareName, 
			String deviceType, String status, int pageNum, int pageSize);
	
	/**
	 * 升级软件。
	 * @param su 待升级的软件实体对象
	 */
	public void upgradeSoftware(SoftwareUpgrade su);
	
	/**
	 * 通过软件代码获取最新的软件信息，获取不到返回Null。
	 * @param softwareCode 软件代码
	 * @return 最新的软件信息
	 */
	public SoftwareUpgrade getLatestSoftware(String softwareCode);
	
	/**
	 * 审核软件。
	 * @param softwareId 软件编号
	 * @param isApproved 是否审核通过
	 * @param approvedUser 审核人员登录名
	 */
	public void approveSoftware(String softwareId, boolean isApproved, String approvedUser);
	
	/**
	 * 即时发布软件。
	 * @param softwareId 软件编号
	 * @param publishedUser 发布人员登录名
	 */
	public void publishSoftware(String softwareId, String publishedUser);
	
	/**
	 * 定时发布软件。
	 * @param softwareId 软件编号
	 * @param publishedTime 发布时间
	 * @param publishedUser 发布人员登录名
	 */
	public void publishTimingSoftware(String softwareId, Date publishedTime, String publishedUser);
	
	/**
	 * 处理等待发布的软件。
	 */
	public void publishTimingSoftwares();
	
	/**
	 * 通知统一升级软件。
	 * @param softwareId 软件编号
	 * @param upgradedTime 升级时间
	 * @param upgradedUser 通知升级人员登录名
	 */
	public void notifyUpgradeSoftware(String softwareId, Date upgradedTime, String upgradedUser);
}
