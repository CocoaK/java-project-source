package com.biencloud.smarthome.web.ad.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 广告投放目标系统值对象。
 * @author kouy
 * @since 1.0 2012-5-30
 */
public class AdSysVO extends BaseVO {

	private static final long serialVersionUID = 561067182080669944L;
	
	private String sysCode;//广告投放目标系统代码
	private String sysName;//广告投放目标系统名称
	private String sysDesc;//广告投放目标系统描述
	
	
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	
	public String getSysDesc() {
		return sysDesc;
	}
	public void setSysDesc(String sysDesc) {
		this.sysDesc = sysDesc;
	}	
}
