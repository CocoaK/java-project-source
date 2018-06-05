package com.biencloud.smarthome.web.requestrepair.vo;

import java.util.Date;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;
import com.biencloud.smarthome.web.user.vo.PaUserVO;

/**
 * 
 * 项目名称：smarthome-web-new 类名称：RequestRepairVO 类描述： 报修VO类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-6-12 下午2:22:26
 */
public class RequestRepairVO extends BaseVO {

	/**
	 * 保存草稿，未提交
	 */
	public static final Integer STATUS_NOSUMBIT = 0;
	/**
	 * 未处理
	 */
	public static final Integer STATUS_NOPROCESS = 1;
	/**
	 * 已处理
	 */
	public static final Integer STATUS_YESPROCESS = 2;

	private static final long serialVersionUID = -1056144805030329705L;
	private CellHouseholdInfoVo cellHouseholdInfo;// 房间对象
	private OwnerUserVO ownerUser;// 业主对象
	private PaUserVO paUser;// 物业用户对象
	private Long id;
	private String title;// 标题
	private String content;// 内容
	private Date requestTime;// 报修时间
	private String status;// 状态
	private String phone;// 电话号码
	private Date repairTime;// 处理时间
	private String repairOpinion;// 处理意见
	private String remark;// 描述
	private Long districtId;// 小区ID
	private Date requestStartTime;// 报修开始时间
	private Date requestEndTime;// 报修结束时间
	private Date repairStartTime;// 处理开始时间
	private Date repairEndTime;// 处理结束时间
	private boolean isexcuteNoSubmit;// 是否排除未提交的

	public boolean isIsexcuteNoSubmit() {
		return isexcuteNoSubmit;
	}

	public void setIsexcuteNoSubmit(boolean isexcuteNoSubmit) {
		this.isexcuteNoSubmit = isexcuteNoSubmit;
	}

	public CellHouseholdInfoVo getCellHouseholdInfo() {
		return cellHouseholdInfo;
	}

	public void setCellHouseholdInfo(CellHouseholdInfoVo cellHouseholdInfo) {
		this.cellHouseholdInfo = cellHouseholdInfo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OwnerUserVO getOwnerUser() {
		return ownerUser;
	}

	public void setOwnerUser(OwnerUserVO ownerUser) {
		this.ownerUser = ownerUser;
	}

	public PaUserVO getPaUser() {
		return paUser;
	}

	public void setPaUser(PaUserVO paUser) {
		this.paUser = paUser;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getRepairEndTime() {
		return repairEndTime;
	}

	public void setRepairEndTime(Date repairEndTime) {
		this.repairEndTime = repairEndTime;
	}

	public String getRepairOpinion() {
		return repairOpinion;
	}

	public void setRepairOpinion(String repairOpinion) {
		this.repairOpinion = repairOpinion;
	}

	public Date getRepairStartTime() {
		return repairStartTime;
	}

	public void setRepairStartTime(Date repairStartTime) {
		this.repairStartTime = repairStartTime;
	}

	public Date getRepairTime() {
		return repairTime;
	}

	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}

	public Date getRequestEndTime() {
		return requestEndTime;
	}

	public void setRequestEndTime(Date requestEndTime) {
		this.requestEndTime = requestEndTime;
	}

	public Date getRequestStartTime() {
		return requestStartTime;
	}

	public void setRequestStartTime(Date requestStartTime) {
		this.requestStartTime = requestStartTime;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
