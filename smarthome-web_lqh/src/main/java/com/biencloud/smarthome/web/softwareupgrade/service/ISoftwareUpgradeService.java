package com.biencloud.smarthome.web.softwareupgrade.service;

import java.util.Date;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.softwareupgrade.vo.SoftwareUpgradeVO;

/** 
 * 软件升级模块调用服务接口。
 * @author kouy
 * @since 1.0 2012-5-3
 */
public interface ISoftwareUpgradeService {

	/**
	 * 查询软件升级列表，分页。
	 * @param softwareName 软件名称，如果为空不作为查询条件，否则作为模糊查询条件
	 * @param deviceType 设备类型，如果为空不作为查询条件
	 * @param status 状态，如果为空不作为查询条件
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return 分页信息
	 */
	public PagingVO<SoftwareUpgradeVO> querySoftwaresForPaging(String softwareName, 
			String deviceType, String status, int pageNum, int pageSize);
	
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
	 * 新增软件升级信息。
	 * @param softwareUpgradeVO 软件升级值对象
	 */
	public void addSoftware(SoftwareUpgradeVO softwareUpgradeVO);
	
	/**
	 * 获取软件升级详细信息。
	 * @param softwareId 软件编号
	 * @return
	 */
	public SoftwareUpgradeVO getSoftwareDetail(String softwareId);
	
	/**
	 * 删除软件升级信息。
	 * @param softwareId 软件编号
	 */
	public void removeSoftware(String softwareId);
	
	/**
	 * 升级软件。
	 * @param softwareUpgradeVO 待升级的软件值对象
	 */
	public void upgradeSoftware(SoftwareUpgradeVO softwareUpgradeVO);
	
	/**
	 * 通过软件代码获取最新的软件信息，获取不到返回Null。
	 * @param softwareCode 软件代码
	 * @return 最新的软件信息
	 */
	public SoftwareUpgradeVO getLatestSoftware(String softwareCode);
	
	/**
	 * 通知统一升级软件。
	 * @param softwareId 软件编号
	 * @param upgradedTime 升级时间
	 * @param upgradedUser 通知升级人员登录名
	 */
	public void notifyUpgrade(String softwareId, Date upgradedTime, String upgradedUser);
}
