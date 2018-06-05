package com.biencloud.smarthome.service.requestrepair.model;
// default package

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.housemgr.model.CellHouseholdInfo;
import com.biencloud.smarthome.service.user.model.OwnerUser;
import com.biencloud.smarthome.service.user.model.PaUser;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：RequestRepair 
 * 类描述： 报修实体类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午2:19:35
 */
@Entity
@Table(name = "t_request_repair")
public class RequestRepair extends BaseEntity {
	
	private static final long serialVersionUID = -78214242587886L;
	
	/**
	 * 保存草稿，未提交
	 */
	public static final Integer STATUS_NOSUMBIT=0;
	/**
	 * 未处理
	 */
	public static final Integer STATUS_NOPROCESS=1;
	/**
	 * 已处理
	 */
	public static final Integer STATUS_YESPROCESS=2;

	// Fields

	private Long id;
	private CellHouseholdInfo cellHouseholdInfo;//房间对象
	private OwnerUser ownerUser;//业主对象
	private PaUser paUser;//物业用户对象
	private String title;//标题
	private String content;//内容
	private Date requestTime;//报修时间
	private String status;//状态
	private String phone;//电话号码
	private Date repairTime;//处理时间
	private String repairOpinion;//处理意见
	private String remark;//描述
	private Long districtId;//小区ID
	private Date requestStartTime;//报修开始时间
	private Date requestEndTime;//报修结束时间
	private Date repairStartTime;//处理开始时间
	private Date repairEndTime;//处理结束时间
	private boolean isexcuteNoSubmit;//是否排除未提交的
	
	

	// Constructors

	@Transient
	public Date getRequestStartTime() {
		return requestStartTime;
	}

	public void setRequestStartTime(Date requestStartTime) {
		this.requestStartTime = requestStartTime;
	}

	@Transient
	public Date getRequestEndTime() {
		return requestEndTime;
	}

	public void setRequestEndTime(Date requestEndTime) {
		this.requestEndTime = requestEndTime;
	}

	@Transient
	public Date getRepairStartTime() {
		return repairStartTime;
	}

	public void setRepairStartTime(Date repairStartTime) {
		this.repairStartTime = repairStartTime;
	}

	@Transient
	public Date getRepairEndTime() {
		return repairEndTime;
	}
	
	@Transient
	public boolean isIsexcuteNoSubmit() {
		return isexcuteNoSubmit;
	}

	public void setIsexcuteNoSubmit(boolean isexcuteNoSubmit) {
		this.isexcuteNoSubmit = isexcuteNoSubmit;
	}

	public void setRepairEndTime(Date repairEndTime) {
		this.repairEndTime = repairEndTime;
	}

	/** default constructor */
	public RequestRepair() {
	}

	/** minimal constructor */
	public RequestRepair(Long id, CellHouseholdInfo cellHouseholdInfo, OwnerUser ownerUser, String title, Long districtId) {
		this.id = id;
		this.cellHouseholdInfo = cellHouseholdInfo;
		this.ownerUser = ownerUser;
		this.title = title;
		this.districtId = districtId;
	}

	/** full constructor */
	public RequestRepair(Long id, CellHouseholdInfo cellHouseholdInfo, OwnerUser ownerUser, PaUser paUser, String title, String content,
			Date requestTime, String status, String phone, Date repairTime, String repairOpinion, String remark, Long districtId) {
		this.id = id;
		this.cellHouseholdInfo = cellHouseholdInfo;
		this.ownerUser = ownerUser;
		this.paUser = paUser;
		this.title = title;
		this.content = content;
		this.requestTime = requestTime;
		this.status = status;
		this.phone = phone;
		this.repairTime = repairTime;
		this.repairOpinion = repairOpinion;
		this.remark = remark;
		this.districtId = districtId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roomId", nullable = false)
	public CellHouseholdInfo getCellHouseholdInfo() {
		return this.cellHouseholdInfo;
	}

	public void setCellHouseholdInfo(CellHouseholdInfo cellHouseholdInfo) {
		this.cellHouseholdInfo = cellHouseholdInfo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ownerId")
	public OwnerUser getOwnerUser() {
		return this.ownerUser;
	}

	public void setOwnerUser(OwnerUser ownerUser) {
		this.ownerUser = ownerUser;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pauserId")
	public PaUser getPaUser() {
		return this.paUser;
	}

	public void setPaUser(PaUser paUser) {
		this.paUser = paUser;
	}

	@Column(name = "title", nullable = false, length = 200)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "request_time", length = 19)
	public Date getRequestTime() {
		return this.requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	@Column(name = "status", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "phone", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "repair_time", length = 19)
	public Date getRepairTime() {
		return this.repairTime;
	}

	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}

	@Column(name = "repair_opinion", length = 300)
	public String getRepairOpinion() {
		return this.repairOpinion;
	}

	public void setRepairOpinion(String repairOpinion) {
		this.repairOpinion = repairOpinion;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "districtId", nullable = false)
	public Long getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

}