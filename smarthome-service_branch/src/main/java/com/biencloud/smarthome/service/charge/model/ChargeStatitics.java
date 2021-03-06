package com.biencloud.smarthome.service.charge.model;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：ChargeStatitics 
 * 类描述： 收费统计实体类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:28:03
 */
public class ChargeStatitics {
	
	private String buildingName;//栋名称
	
	private String typeName;//收费项目名称
	
	private Integer totalCustomer;//总客户数
	
	private Integer acTotalCustomer;//实际客户数
	
	private Double totalMoney;//总金额
	
	private Double acTotalMoney;//实际应收金额
	
	private Double feeTotalMoney;//欠费金额
	
	private String customerPercent;//客户已交百分比
	
	private String moneyPercent;//金额已交百分比
	
	private String monetaryUnit;//货币单位
	
	private String chargetTime;//收费时间
	
	

	public String getMonetaryUnit() {
		return monetaryUnit;
	}

	public void setMonetaryUnit(String monetaryUnit) {
		this.monetaryUnit = monetaryUnit;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getTotalCustomer() {
		return totalCustomer;
	}

	public void setTotalCustomer(Integer totalCustomer) {
		this.totalCustomer = totalCustomer;
	}

	public Integer getAcTotalCustomer() {
		return acTotalCustomer;
	}

	public void setAcTotalCustomer(Integer acTotalCustomer) {
		this.acTotalCustomer = acTotalCustomer;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Double getAcTotalMoney() {
		return acTotalMoney;
	}

	public void setAcTotalMoney(Double acTotalMoney) {
		this.acTotalMoney = acTotalMoney;
	}

	public Double getFeeTotalMoney() {
		return feeTotalMoney;
	}

	public void setFeeTotalMoney(Double feeTotalMoney) {
		this.feeTotalMoney = feeTotalMoney;
	}

	public String getCustomerPercent() {
		return customerPercent;
	}

	public void setCustomerPercent(String customerPercent) {
		this.customerPercent = customerPercent;
	}

	public String getMoneyPercent() {
		return moneyPercent;
	}

	public void setMoneyPercent(String moneyPercent) {
		this.moneyPercent = moneyPercent;
	}

	public String getChargetTime() {
		return chargetTime;
	}

	public void setChargetTime(String chargetTime) {
		this.chargetTime = chargetTime;
	}

	

}
