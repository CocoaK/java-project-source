package com.biencloud.smarthome.web.ad.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 广告类型值对象。
 * @author kouy
 * @since 1.0 2012-5-30
 */
public class AdTypeVO extends BaseVO {

	private static final long serialVersionUID = -8602343356946605100L;
	
	private String typeCode;//广告类型代码
	private String typeName;//广告类型名称
	
	
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
