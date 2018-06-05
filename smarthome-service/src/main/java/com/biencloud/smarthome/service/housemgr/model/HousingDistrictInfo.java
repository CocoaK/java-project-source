package com.biencloud.smarthome.service.housemgr.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.biencloud.smarthome.service.base.model.BaseEntity;
import com.biencloud.smarthome.service.sysgroup.model.SystemGroup;

/**
 * 小区信息 实体类
 * 
 * 实体间的关系如下(从小区开始, 逐层展开):
 * <ul>
 * <li>小区(XX小区)包含区域(A区, B区...)</li>
 * <li>区域包含楼宇(A栋, B栋...)</li>
 * <li>楼宇包含单元(1单元, 2单元...)</li>
 * <li>单元包含房号(户)</li>
 * <ul>
 * 示例如下:
 * <pre>
 *              XX小区
 *                |
 *              -----
 *             /     \
 *            A区            B区
 *             |
 *           -----  
 *          /     \
 *         A栋              B栋 
 *          |
 *        -----
 *       /     \
 *     1单元           2单元
 *      |
 *    ------
 *   /      \
 * 001户           002户
 * </pre>
 * 
 * 房号管理中的所有编码(CODE)都是由用户定义的, 用于标示唯一性
 * 例如用户用01表示A区, 02表示B区, 这种关系是由用户自己来定义的
 * 
 * 编码为定长数字, 数位不足的前面补0, 规则如下:
 * 小区 4位 0001
 * 区域 2位       01
 * 楼宇 3位    001
 * 单元 2位       01
 * 房号 4位 0001
 * 
 * 完整描述示例 0001(小区)02(区域)001(栋)01(单元)4801(房)
 * 
 * @author jsun
 * @since 1.0 2012-5-12
 */
@Entity
@Table(name="t_hm_housing_district_info")
public class HousingDistrictInfo extends BaseEntity {
	private static final long serialVersionUID = 1280111595623990271L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	/**
	 * 小区ID
	 */
	private String id;
	/**
	 * 小区编码(定长), 由用户输入的4位数字, 从0001开始
	 */
	private String code;
	/**
	 * 小区名称
	 */
	private String name;

	/**
	 * 总建筑面积(平方米)
	 */
	@Column(name="CONSTRUCTION_AREA")
	private String constructionArea;

	/**
	 * 创建时间
	 */
	@Column(name="CREATE_TIME")
	private Date createTime;
	/**
	 * 创建用户的登录帐号
	 */
	@Column(name="CREATE_USER_ID")
	private String createUserId;

	/**
	 * 开发商
	 */
	private String developer;

	/**
	 * 地址
	 */
	private String district;

	/**
	 * 小区平面图(只保存文件的路径)
	 */
	@Column(name="FLOOR_PLAN")
	private String floorPlan;

	/**
	 * 所属的组织机构
	 * 
	 * @see SystemGroup#getGroupNo()
	 */
	@Column(name="GROUP_ID")
	private String groupId;

	/**
	 * 物业地址
	 */
	@Column(name="PROPERTY_COMPANY_ADDRESS")
	private String propertyCompanyAddress;

	/**
	 * 所属物业公司
	 */
    @ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="PROPERTY_COMPANY_ID")
	private PropertyCompanyInfo propertyCompanyInfo;

    /**
     * 小区中包含的区域
     */
    // OneToMany <-> ManyToOne 这种循环引用会陷入死循环问题, 因此只标示单向关系
    @Transient
	private List<HousingDistrictRegionInfo> housingDistrictRegionInfos;
    
    /**
     * 小区坐标（真实经纬度）
     */
    @Column(name="position")
    private String position;

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 小区编码(定长), 由用户输入的4位数字, 从0001开始
	 * 
	 * @return
	 */
	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 总建筑面积(平方米)
	 * 
	 * @return
	 */
	public String getConstructionArea() {
		return this.constructionArea;
	}
	public void setConstructionArea(String constructionArea) {
		this.constructionArea = constructionArea;
	}

	/**
	 * 用户在系统中创建小区的时间
	 * 
	 * @return
	 */
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 创建小区的用户ID
	 * 
	 * @return
	 */
	public String getCreateUserId() {
		return this.createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * 开发商
	 */
	public String getDeveloper() {
		return this.developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	/**
	 * 归属区域
	 */
	public String getDistrict() {
		return this.district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * 小区平面图(只保存文件的路径)
	 * 
	 * @return
	 */
	public String getFloorPlan() {
		return this.floorPlan;
	}
	public void setFloorPlan(String floorPlan) {
		this.floorPlan = floorPlan;
	}

	/**
	 * 所属的组织机构
	 * 
	 * @return
	 * @see SystemGroup#getGroupNo()
	 */
	public String getGroupId() {
		return this.groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 物业地址
	 * 
	 * @return
	 */
	public String getPropertyCompanyAddress() {
		return this.propertyCompanyAddress;
	}
	public void setPropertyCompanyAddress(String propertyCompanyAddress) {
		this.propertyCompanyAddress = propertyCompanyAddress;
	}

	/**
	 * 所属物业公司
	 * 
	 * @return
	 */
	public PropertyCompanyInfo getPropertyCompanyInfo() {
		return this.propertyCompanyInfo;
	}
	public void setPropertyCompanyInfo(PropertyCompanyInfo propertyCompanyInfo) {
		this.propertyCompanyInfo = propertyCompanyInfo;
	}
	
	/**
	 * 小区中包含的区域
	 * 
	 * @return
	 */
	public List<HousingDistrictRegionInfo> getHousingDistrictRegionInfos() {
		return this.housingDistrictRegionInfos;
	}
	public void setHousingDistrictRegionInfos(List<HousingDistrictRegionInfo> housingDistrictRegionInfos) {
		this.housingDistrictRegionInfos = housingDistrictRegionInfos;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}
