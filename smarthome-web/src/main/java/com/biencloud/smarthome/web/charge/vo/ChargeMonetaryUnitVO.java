package com.biencloud.smarthome.web.charge.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 
 * 项目名称：smarthome-web-new 类名称：ChargeMonetaryUnitVO 类描述： 收费货币单位VO类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-6-12 上午10:35:04
 */
public class ChargeMonetaryUnitVO extends BaseVO {

	private static final long serialVersionUID = 7791704145748885327L;
	private Long id;
	private String monetaryCode;// 货币编码
	private String codeName;// 货币名称
	private String remark;// 描述

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMonetaryCode() {
		return monetaryCode;
	}

	public void setMonetaryCode(String monetaryCode) {
		this.monetaryCode = monetaryCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
