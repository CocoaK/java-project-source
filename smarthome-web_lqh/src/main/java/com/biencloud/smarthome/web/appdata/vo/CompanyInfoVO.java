package com.biencloud.smarthome.web.appdata.vo;

import com.biencloud.smarthome.web.appdata.json.Json;

/**
 * 
 * 类名称：CompanyInfo 类描述： 物业管理信息类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-14 上午8:53:58
 */
public class CompanyInfoVO extends Json {
	//private String areaCode;
	// 名称
	private String companyName;
	// 公司介绍
	private String companyDes;
	// 联系方式
	private String phone;
	// 负责人
	private String chief;
	

	public CompanyInfoVO() {
		super();
	}

	public CompanyInfoVO(String companyName, String companyDes, String phone, String chief) {
		super();
		this.companyName = companyName;
		this.companyDes = companyDes;
		this.phone = phone;
		this.chief = chief;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyDes() {
		return companyDes;
	}

	public void setCompanyDes(String companyDes) {
		this.companyDes = companyDes;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getChief() {
		return chief;
	}

	public void setChief(String chief) {
		this.chief = chief;
	}

	/*public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}*/

}
