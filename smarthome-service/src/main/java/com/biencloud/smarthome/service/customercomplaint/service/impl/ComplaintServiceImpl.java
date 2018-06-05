package com.biencloud.smarthome.service.customercomplaint.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.common.utils.Utils;
import com.biencloud.smarthome.service.customercomplaint.model.Complaint;
import com.biencloud.smarthome.service.customercomplaint.service.IComplaintService;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.device.service.IDeviceService;
import com.biencloud.smarthome.service.login.model.Login;
import com.biencloud.smarthome.service.login.service.ILoginService;
import com.biencloud.smarthome.service.user.enums.UserType;
import com.biencloud.smarthome.service.user.model.OwnerUser;
import com.biencloud.smarthome.service.user.model.PaUser;
import com.biencloud.smarthome.service.user.model.SaUser;
import com.biencloud.smarthome.service.user.service.IOwnerUserService;
import com.biencloud.smarthome.service.user.service.IPaUserService;
import com.biencloud.smarthome.service.user.service.ISaUserService;

/**
 * 客服投诉Service实现类
 * 
 * @author jsun  
 * @since 1.0 2012-5-30
 */
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class ComplaintServiceImpl extends BaseService<Complaint, String>
		implements IComplaintService {
	/**
	 * 用于获取投诉人/处理人的用户信息
	 */
	private ILoginService loginService;

	/**
	 * 用于获取业主用户的信息
	 */
	private IOwnerUserService ownerUserService;
	/**
	 * 用于获取物业用户的信息
	 */
	private IPaUserService paUserService;
	/**
	 * 用于获取系统用户的信息
	 */
	private ISaUserService saUserService;

	private IDeviceService deviceService;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addPropertyComplaint(Complaint complaint) {
		complaint.setType(PROPERTY_COMPLAINT_TYPE);
		save(complaint);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addOwnerComplaint(Complaint complaint) throws Exception {
		complaint.setType(OWNER_COMPLAINT_TYPE);

		if (complaint.getOnlyValue() != null) { // 判断是否已经有存在的记录?
			List<String> ids = findIds("FROM Complaint complaint where onlyValue='" + complaint.getOnlyValue()+"'");
			if (ids != null && !ids.isEmpty()) {
				throw new Exception("onlyValue repeat");
			}
		} else {
			complaint.setOnlyValue("a"+complaint.getComplaintLoginName() + System.currentTimeMillis());
		}

		save(complaint);
	}

	@Override
	public Paging<Complaint> queryPropertyComplaintForPaging(Complaint condition,
			Date endComplaintDate, Date endProcessingDate, int pageNum, int pageSize,
			boolean excludeUncommitted) {
		condition.setType(PROPERTY_COMPLAINT_TYPE);
		return queryForPaging(condition, endComplaintDate, endProcessingDate, pageNum,
				pageSize, excludeUncommitted);
	}

	@Override
	public Paging<Complaint> queryOwnerComplaintForPaging(Complaint condition,
			Date endComplaintDate, Date endProcessingDate, int pageNum, int pageSize,
			boolean excludeUncommitted) {
		condition.setType(OWNER_COMPLAINT_TYPE);
//		condition.setProcessingStatus(IComplaintService.PROCESSING_STATUS_UNCOMMITTED);
		return queryForPaging(condition, endComplaintDate, endProcessingDate, pageNum,
				pageSize, excludeUncommitted);
	}

	@Override
	public Complaint get(String id) {
		Complaint complaint = super.get(id);
		initLoginInfo(complaint);
		return complaint;
	}

	@Override
	public long queryComplaintCount(Complaint condition, boolean today) {
		Date endComplaintDate = null;

		if (today == true) {
			Date complaintStartDate = new Date();
			endComplaintDate = Utils.getDayEndingDate(complaintStartDate);
			condition.setComplaintDate(complaintStartDate);
		}

		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, condition, endComplaintDate,
				null, true);
		jpql.insert(0, "SELECT COUNT(complaint) FROM Complaint complaint");

		return getTotalCountByNamedParams(jpql.toString(), params);
	}

	@Override
	public List<Complaint> queryRecentComplaint(Complaint condition, int quantity, boolean today) {
		Date endComplaintDate = null;

		if (today == true) {
			Date complaintStartDate = new Date();
			endComplaintDate = Utils.getDayEndingDate(complaintStartDate);
			condition.setComplaintDate(complaintStartDate);
		}

		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, condition, endComplaintDate,
				null, false);

		jpql.insert(0, "SELECT " + REPLACE_CHARS + " FROM Complaint complaint");
		String queryString = jpql.toString().replace(REPLACE_CHARS, "complaint");
		String queryStringCount = jpql.toString().replace(REPLACE_CHARS,
				"COUNT(complaint)");
		queryString = queryString + " order by complaintDate desc ";

		// 通过分页查询来限制最多查询多少投诉信息
		Paging<Complaint> complaints = queryByNamedParamsForPaging(
				1, quantity, queryString, queryStringCount, params);

		for (int i = 0, length = complaints.getResults().size(); i < length; i++) {
			initLoginInfo(complaints.getResults().get(i));
		}

		return complaints.getResults();
	}

	private Paging<Complaint> queryForPaging(Complaint condition,
			Date endComplaintDate, Date endProcessingDate, int pageNum, int pageSize,
			boolean excludeUncommitted) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createParams(jpql, condition, endComplaintDate,
				endProcessingDate, excludeUncommitted);

		jpql.insert(0, "SELECT " + REPLACE_CHARS + " FROM Complaint complaint");
		String queryString = jpql.toString().replace(REPLACE_CHARS, "complaint");
		String queryStringCount = jpql.toString().replace(REPLACE_CHARS,
				"COUNT(complaint)");
		queryString = queryString + " order by complaintDate desc ";

		Paging<Complaint> complaints = queryByNamedParamsForPaging(
				pageNum, pageSize, queryString, queryStringCount, params);

		for (int i = 0, length = complaints.getResults().size(); i < length; i++) {
			initLoginInfo(complaints.getResults().get(i));
		}
		return complaints;
	}
	
	/**
	 * 从投诉信息中的取出了用户登录账号, 但没有关联用户的相关信息, 如这里需要的用户名, 需要通过登录模块来获得
	 * 
	 * @see ILoginService#getLoginByLoginName(String)
	 */
	private void initLoginInfo(Complaint complaint) {
		complaint.setComplaintLogin(loginService.getLoginByLoginName(complaint.getComplaintLoginName()));
		complaint.setProcessingLogin(loginService.getLoginByLoginName(complaint.getProcessingLoginName()));
	}

	/**
	 * 根据实体属性动态生成查询条件
	 * 
	 * @param jpql
	 * @param condition
	 * @param endComplaintDate 设置投诉时间的一个区间, 投诉实体中包含的投诉时间为开始时间, 这个为结束时间
	 * @param endProcessingDate 设置处理时间的一个区间, 投诉实体中包含的处理时间为开始时间, 这个为结束时间
	 * @param excludeUncommitted 是否排除未提交的投诉
	 * @return
	 */
	private Map<String, Object> createParams(StringBuilder jpql,
			Complaint condition, Date endComplaintDate, Date endProcessingDate,
			boolean excludeUncommitted) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(condition == null)
			return params;

		// 投诉类型
		if (StringUtils.isNotBlank(condition.getType())) {
			appendCondition(jpql, "complaint.type = :type", "type", 
					condition.getType(), params);

			ifAddLikeUserNameWhereClause(jpql, condition, params);
		}
		// 排除未提交的投诉
		if (excludeUncommitted) {
			appendCondition(jpql, "complaint.processingStatus != :uncommitted", "uncommitted", 
					PROCESSING_STATUS_UNCOMMITTED, params);
		}
		// 投诉标题
		if (StringUtils.isNotBlank(condition.getTitle())) {
			appendCondition(jpql, "complaint.title LIKE :title", "title", 
					"%" + condition.getTitle() + "%", params);
		}
		// 处理状态
		if (StringUtils.isNotBlank(condition.getProcessingStatus())) {
			appendCondition(jpql, "complaint.processingStatus = :processingStatus", "processingStatus", 
					condition.getProcessingStatus(), params);
		}
		// 投诉位置
		if (StringUtils.isNotBlank(condition.getLocation())) {
			appendCondition(jpql, "complaint.location LIKE :location", "location", 
					"%" + condition.getLocation() + "%", params);
		}
		// 投诉时间区间, 开始时间
		if (condition.getComplaintDate() != null) {
			appendCondition(jpql, "complaint.complaintDate >= :complaintDate", "complaintDate", 
					condition.getComplaintDate(), params);
		}
		// 投诉时间区间, 结束时间
		if (endComplaintDate != null) {
			appendCondition(jpql, "complaint.complaintDate <= :endComplaintDate", "endComplaintDate", 
					endComplaintDate, params);
		}
		// 处理时间区间, 开始时间
		if (condition.getProcessingDate() != null) {
			appendCondition(jpql, "complaint.processingDate >= :processingDate", "processingDate", 
					condition.getProcessingDate(), params);
		}
		// 处理时间区间, 结束时间
		if (endProcessingDate != null) {
			appendCondition(jpql, "complaint.processingDate <= :endProcessingDate", "endProcessingDate", 
					endProcessingDate, params);
		}
		// 投诉人登陆名
		if (StringUtils.isNotBlank(condition.getComplaintLoginName())) {
			appendCondition(jpql, "complaint.complaintLoginName = :loginName", "loginName", 
					condition.getComplaintLoginName(), params);
		}
		if (StringUtils.isNotBlank(condition.getDistrictId())) {
			appendCondition(jpql, "complaint.districtId = :districtId", "districtId", 
					condition.getDistrictId(), params);
		}
		if (condition.getAppId()!=null) {
			appendCondition(jpql, "complaint.appId = :appId", "appId", 
					condition.getAppId(), params);
		}
		if (StringUtils.isNotBlank(condition.getDeviceNo())) {
			Device device=deviceService.queryDeviceByCode(condition.getDeviceNo());
			if(device!=null){
				appendCondition(jpql, "complaint.houseId = :houseId", "houseId", 
						device.getCellHouseholdInfo().getId(), params);
			}
		}

		return params;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void replySuggestion(String id, String processingLoginName, String suggestion) {
		StringBuilder jpql = new StringBuilder(
				"UPDATE Complaint complaint SET complaint.processingStatus = ?1," +
				"complaint.processingDate = current_timestamp, complaint.processingLoginName = ?2," +
				"complaint.suggestion = ?3 WHERE complaint.id = ?4");
		Object[] values = { PROCESSING_STATUS_PROCESSED, processingLoginName, suggestion, id };
		update(jpql.toString(), values);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateComplaint(String id, String title, String content, boolean submit) {
		String processingStatus = PROCESSING_STATUS_UNCOMMITTED;
		if (submit) {
			processingStatus = PROCESSING_STATUS_PENDING;
		}

		StringBuilder jpql = new StringBuilder(
				"UPDATE Complaint complaint SET complaint.processingStatus = ?1," +
				"complaint.title = ?2, complaint.content = ?3, complaint.complaintDate = ?4 WHERE complaint.id = ?5");
		Object[] values = { processingStatus, title, content,new Date(System.currentTimeMillis()), id };
		update(jpql.toString(), values);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeOwnerComplaint(String onlyValue) {
		String jpql = "delete from Complaint complaint WHERE complaint.onlyValue = ?1 and complaint.type = " + OWNER_COMPLAINT_TYPE;
		Object[] values = { onlyValue };
		update(jpql, values);
	}

	@Override
	public List<Complaint> queryComplaintByDeviceCode(String deviceCode) {
		Device device = deviceService.queryDeviceByCode(deviceCode);
		if (device != null && device.getCellHouseholdInfo() != null
				&& device.getCellHouseholdInfo().getOwner() != null) {
			OwnerUser ownerUser = ownerUserService.get(device.getCellHouseholdInfo().getOwner().getUserId());
			String complaintLoginName = ownerUser.getLogin().getLoginName();

			String jpql = "from Complaint complaint WHERE complaint.complaintLoginName = ?1 and complaint.type = " + OWNER_COMPLAINT_TYPE;
			Object[] values = { complaintLoginName };
			return find(jpql, values);
		}
		return null;
	}

	/**
	 * 是否添加通过用户名来模糊查询投诉信息的查询条件
	 * 
	 * @param jpql
	 * @param condition
	 * @param params
	 */
	private void ifAddLikeUserNameWhereClause(StringBuilder jpql, Complaint condition,
			Map<String, Object> params) {
		// 投诉用户登录信息, 用于LIKE投诉人姓名
		Login complaintUserlogin = condition.getComplaintLogin();
		// 处理用户登录信息, 用于LIKE处理人姓名
		Login processingUserlogin = condition.getProcessingLogin();

		// 根据用户姓名查询出来的用户登录名
		List<String> complaintLoginNames = null;
		List<String> processingLoginNames = null;

		if (complaintUserlogin != null && StringUtils.isNotBlank(complaintUserlogin.getUserName())) {
			if (condition.getType().equals(PROPERTY_COMPLAINT_TYPE)) { // 物业投诉, 投诉人为物业用户
				complaintLoginNames = findLoginNameByLikeUserName(
						complaintUserlogin.getUserName(), UserType.PA);
			} else if (condition.getType().equals(OWNER_COMPLAINT_TYPE)) { // 业主投诉, 投诉人为业主用户
				complaintLoginNames = findLoginNameByLikeUserName(
						complaintUserlogin.getUserName(), UserType.OWNER);
			}

			if (complaintLoginNames != null && !complaintLoginNames.isEmpty()) { // 查询到用户则将用户登录名作为in条件
				appendCondition(jpql, "complaint.complaintLoginName in :complaintLoginNames",
						"complaintLoginNames", complaintLoginNames, params);
			} else { // 查询不到用户, 则直接导致整个查询无结果
				appendCondition(jpql, "complaint.id = :fakeId", "fakeId", "0", params);
			}
		}

		if (processingUserlogin != null && StringUtils.isNotBlank(processingUserlogin.getUserName())) {
			if (condition.getType().equals(PROPERTY_COMPLAINT_TYPE)) { // 物业投诉, 处理人为系统用户
				processingLoginNames = findLoginNameByLikeUserName(
						processingUserlogin.getUserName(), UserType.SA);
			} else if (condition.getType().equals(OWNER_COMPLAINT_TYPE)) { // 业主投诉, 处理人为物业用户
				processingLoginNames = findLoginNameByLikeUserName(
						processingUserlogin.getUserName(), UserType.PA);
			}

			if (processingLoginNames != null && !processingLoginNames.isEmpty()) { // 查询到用户则将用户登录名作为in条件
				appendCondition(jpql, "complaint.processingLoginName in :processingLoginNames",
						"processingLoginNames", processingLoginNames, params);
			} else { // 查询不到用户, 则直接导致整个查询无结果, 这里用ID=0使查询不出任何结果
				appendCondition(jpql, "complaint.id = :fakeId", "fakeId", "0", params);
			}
		}
	}

	/**
	 * 根据用户姓名和用户类型查询出用户登录名
	 * 
	 * 用户表与用户登录信息的关系为:
	 * 用户分3种(系统用户{SaUser}, 物业用户{PaUser}, 业主用户{OwnerUser}), 每种为不同的实体, 对应不同的表.
	 * 用户登录信息保存在登录信息{Login}表中, 区分userId和userType
	 * 
	 * 由于投诉信息表中只保存了用户登录名(loginName)即登录账号, 现在需要通过用户名(userName)来做LIKE查询投诉信息.
	 * 因此分2步走:
	 * 1. 先通过like userName查询出loginName
	 * 2. 再通过in loginName过滤投诉信息
	 * 
	 * @param userName
	 * @param userType
	 * @return
	 */
	private List<String> findLoginNameByLikeUserName(String userName, UserType userType) {
		List<String> loginNames = new ArrayList<String>();

		if (userType.equals(UserType.PA)) {
			// TODO DRY
			// TODO 查询限制权限, 只能查询物业小区所有的物业人员
			List<PaUser> users = paUserService.findUsersByLikeName(userName);
			for (int i = 0, length = (users != null ? users.size() : 0); i < length; i++) {
				Login login = users.get(i).getLogin();
				if (login != null && StringUtils.isNotBlank(login.getLoginName())) {
					loginNames.add(login.getLoginName());
				}
			}
		} else if (userType.equals(UserType.SA)) {
			// TODO DRY
			List<SaUser> users = saUserService.findUsersByLikeName(userName);
			for (int i = 0, length = (users != null ? users.size() : 0); i < length; i++) {
				Login login = users.get(i).getLogin();
				if (login != null && StringUtils.isNotBlank(login.getLoginName())) {
					loginNames.add(login.getLoginName());
				}
			}
		} else if (userType.equals(UserType.OWNER)) {
			// TODO DRY
			// TODO 查询限制权限, 只能查询业主小区所有的业主
			List<OwnerUser> users = ownerUserService.findUsersByLikeName(userName);
			for (int i = 0, length = (users != null ? users.size() : 0); i < length; i++) {
				Login login = users.get(i).getLogin();
				if (login != null && StringUtils.isNotBlank(login.getLoginName())) {
					loginNames.add(login.getLoginName());
				}
			}
		}

		return loginNames;
	}
	
	public ILoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}
	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
	}
	public IPaUserService getPaUserService() {
		return paUserService;
	}
	public void setPaUserService(IPaUserService paUserService) {
		this.paUserService = paUserService;
	}
	public ISaUserService getSaUserService() {
		return saUserService;
	}
	public void setSaUserService(ISaUserService saUserService) {
		this.saUserService = saUserService;
	}
	public IDeviceService getDeviceService() {
		return deviceService;
	}
	public void setDeviceService(IDeviceService deviceService) {
		this.deviceService = deviceService;
	}
}
