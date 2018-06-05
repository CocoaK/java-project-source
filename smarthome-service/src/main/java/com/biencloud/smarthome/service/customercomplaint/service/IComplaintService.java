package com.biencloud.smarthome.service.customercomplaint.service;

import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.customercomplaint.model.Complaint;

/**
 * 客服投诉Service
 * 
 * @author jsun  
 * @since 1.0 2012-5-30
 */
public interface IComplaintService extends IService<Complaint, String> {
	/**
	 * 投诉的处理状态: 待处理
	 */
	public static final String PROCESSING_STATUS_PENDING = "0";
	/**
	 * 投诉的处理状态: 已处理
	 */
	public static final String PROCESSING_STATUS_PROCESSED = "1";
	/**
	 * 投诉的处理状态: 未提交
	 */
	public static final String PROCESSING_STATUS_UNCOMMITTED = "2";

	/**
	 * 投诉类型: 物业投诉
	 */
	public static final String PROPERTY_COMPLAINT_TYPE = "0";

	/**
	 * 投诉类型: 业主投诉
	 */
	public static final String OWNER_COMPLAINT_TYPE = "1";

	/**
	 * 分页查询物业投诉信息
	 * 物业投诉信息为(物业投诉系统)
	 * 
	 * @param condition
	 * @param endComplaintDate 设置投诉时间的一个区间, 投诉实体中包含的投诉时间为开始时间, 这个为结束时间
	 * @param endProcessingDate 设置处理时间的一个区间, 投诉实体中包含的处理时间为开始时间, 这个为结束时间
	 * @param pageNum
	 * @param pageSize
	 * @param excludeUncommitted 是否排除未提交的投诉
	 * @return
	 */
	public Paging<Complaint> queryPropertyComplaintForPaging(Complaint condition,
			Date endComplaintDate, Date endProcessingDate, int pageNum, int pageSize,
			boolean excludeUncommitted);

	/**
	 * 分页查询业主投诉信息
	 * 业主投诉信息为(业主投诉物业)
	 * 
	 * @param condition
	 * @param endComplaintDate
	 * @param endProcessingDate
	 * @param pageNum
	 * @param pageSize
	 * @param excludeUncommitted 当物业查看业主投诉的信息, 需要排除未提交的投诉
	 * @return
	 */
	public Paging<Complaint> queryOwnerComplaintForPaging(Complaint condition,
			Date endComplaintDate, Date endProcessingDate, int pageNum, int pageSize,
			boolean excludeUncommitted);

	/**
	 * 新增物业投诉
	 * 物业投诉为物业 -投诉-> 系统
	 * 
	 * @param complaint
	 */
	public void addPropertyComplaint(Complaint complaint);

	/**
	 * 新增业主投诉
	 * 业主投诉为业主 -投诉-> 物业
	 * 
	 * @param complaint
	 */
	public void addOwnerComplaint(Complaint complaint) throws Exception;

	/**
	 * 回复投诉信息给出处理意见
	 * 
	 * @param id
	 * @param processingLoginName
	 * @param suggestion
	 */
	public void replySuggestion(String id, String processingLoginName, String suggestion);

	/**
	 * 修改投诉信息
	 * 
	 * @param id
	 * @param title
	 * @param content
	 * @param submit 是否提交投诉, 否则只是保存投诉(投诉还处于未提交状态)
	 */
	public void updateComplaint(String id, String title, String content, boolean submit);

	/**
	 * 查询投诉总数
	 * 
	 * @param condition
	 * @param today 是查询当天还是全部
	 * @return
	 */
	public long queryComplaintCount(Complaint condition, boolean today);

	/**
	 * 查询最近的投诉信息
	 * 
	 * @param condition
	 * @param quantity
	 * @param today 是查询当天还是全部
	 * @return
	 */
	public List<Complaint> queryRecentComplaint(Complaint condition, int quantity, boolean today);
	
	/**
	 * 根据onlyValue来删除业主投诉信息(用于app的接口)
	 * 
	 * @param onlyValue
	 */
	public void removeOwnerComplaint(String onlyValue);

	/**
	 * 根据设备编号查询业主投诉信息
	 * 
	 * @param deviceCode
	 * @return
	 */
	public List<Complaint> queryComplaintByDeviceCode(String deviceCode);
}
