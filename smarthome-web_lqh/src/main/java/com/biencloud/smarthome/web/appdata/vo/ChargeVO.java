package com.biencloud.smarthome.web.appdata.vo;

import com.biencloud.smarthome.web.appdata.json.Json;

/**
 * 类名称：ChargeVO 
 * 类描述： 缴费类VO
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-11-27 上午10:53:26
 */
@SuppressWarnings("serial")
public class ChargeVO extends Json{
	//缴费时间
	private String chargeTime;
	//总费用
	private Float totalMoney;
	//缴费类型
	private String chargeType;
	//缴费状态
	private String chargeStatus;
	
	public String getChargeTime() {
		return chargeTime;
	}
	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
	}
	public Float getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public String getChargeStatus() {
		return chargeStatus;
	}
	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
	}
	
}
