package com.biencloud.smarthome.service.sysgroup.model;

// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import com.biencloud.smarthome.service.base.model.BaseEntity;

/**
 * SystemGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_system_group")
public class SystemGroup extends BaseEntity {

	private static final long serialVersionUID = -5710591496134771779L;

	// Fields
	/** 标志下面无子节点 */
	public static final Short HashChild_NO = 0;
	/** 标志下面有子节点 */
	public static final Short HashChild_YES = 1;
	/** 标志为国家 */
	public static final String NOPARENT = "-1";
	/** 小区固定深度 */
	public static final Short DEEP_COMMUNITY = 5;
	private Long groupNo;//组织编号
	private String groupName;//组织名称
	private Short deep;//深度
	private String groupParentNo;//父编号
	private Short hasChild;//是否有子节点
	private String createUser;//创建用户
	private Date createTime;//创建时间
	private String updateUser;//更新用户
	private Date updateTime;//更新时间
	private String desc;//描述
	private String groupEqualName;//是否匹配名称

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "group_no", unique = true, nullable = false)
	public Long getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(Long groupNo) {
		this.groupNo = groupNo;
	}

	@Transient
	public String getGroupEqualName() {
		return groupEqualName;
	}

	public void setGroupEqualName(String groupEqualName) {
		this.groupEqualName = groupEqualName;
	}

	@Column(name = "group_name", nullable = false, length = 50)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "deep", nullable = false)
	public Short getDeep() {
		return this.deep;
	}

	public void setDeep(Short deep) {
		this.deep = deep;
	}

	@Column(name = "group_parentNo", nullable = false, length = 50)
	public String getGroupParentNo() {
		return this.groupParentNo;
	}

	public void setGroupParentNo(String groupParentNo) {
		this.groupParentNo = groupParentNo;
	}

	@Column(name = "hasChild", nullable = false)
	public Short getHasChild() {
		return this.hasChild;
	}

	public void setHasChild(Short hasChild) {
		this.hasChild = hasChild;
	}

	@Column(name = "create_user", length = 50)
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_user", length = 50)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "update_time", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "remark", length = 65535)
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}