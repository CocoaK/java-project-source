
package com.biencloud.smarthome.web.charge.vo;

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import com.biencloud.smarthome.web.base.vo.BaseVO;
import com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo;
import com.biencloud.smarthome.web.user.vo.PaUserVO;
/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：ChargeTypeVO 
 * 类描述： 收费项目VO类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:32:42
 */
public class ChargeTypeVO extends BaseVO {
	private static final long serialVersionUID = 1L;
	public static final String[] QUARTER={"年","月","半年","季度"};
	/** 用于标识判断业主有哪些收费项目 */
	public static final String OwnerChargeType="selected";

	private ChargeCalModeVO chargeCalMode;//计算模式
    private String chargeMode;//收费模式
    private ChargeMonetaryUnitVO chargeMonetaryUnit;//收费单位
    private Date createTime;//创建时间
    private Long id;
    private String name;//收费项目名称
    private String remark;//描述
    private String standard;//收费标准
    private PaUserVO paUser;//物管用户
    private HousingDistrictInfoVo housingDistrictInfo;//所属小区
    private ChargeCalUnitVO chargeCalUnit;//计算单位
	

    
    public PaUserVO getPaUser() {
		return paUser;
	}

	public void setPaUser(PaUserVO paUser) {
		this.paUser = paUser;
	}
	

	public HousingDistrictInfoVo getHousingDistrictInfo() {
		return housingDistrictInfo;
	}

	public void setHousingDistrictInfo(HousingDistrictInfoVo housingDistrictInfo) {
		this.housingDistrictInfo = housingDistrictInfo;
	}

	/**
     * Gets the value of the chargeCalMode property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeCalModeVO }
     *     
     */
    public ChargeCalModeVO getChargeCalMode() {
        return chargeCalMode;
    }

    /**
     * Sets the value of the chargeCalMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeCalModeVO }
     *     
     */
    public void setChargeCalMode(ChargeCalModeVO value) {
        this.chargeCalMode = value;
    }

    /**
     * Gets the value of the chargeMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeMode() {
        return chargeMode;
    }

    /**
     * Sets the value of the chargeMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeMode(String value) {
        this.chargeMode = value;
    }

    /**
     * Gets the value of the chargeMonetaryUnit property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeMonetaryUnitVO }
     *     
     */
    public ChargeMonetaryUnitVO getChargeMonetaryUnit() {
        return chargeMonetaryUnit;
    }

    /**
     * Sets the value of the chargeMonetaryUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeMonetaryUnitVO }
     *     
     */
    public void setChargeMonetaryUnit(ChargeMonetaryUnitVO value) {
        this.chargeMonetaryUnit = value;
    }


    /**
     * Gets the value of the createTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Sets the value of the createTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateTime(Date value) {
        this.createTime = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }


    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * Gets the value of the standard property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStandard() {
        return standard;
    }

    /**
     * Sets the value of the standard property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStandard(String value) {
        this.standard = value;
    }

	public ChargeCalUnitVO getChargeCalUnit() {
		return chargeCalUnit;
	}

	public void setChargeCalUnit(ChargeCalUnitVO chargeCalUnit) {
		this.chargeCalUnit = chargeCalUnit;
	}
    


}
