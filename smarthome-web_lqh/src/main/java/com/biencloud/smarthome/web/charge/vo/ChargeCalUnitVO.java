
package com.biencloud.smarthome.web.charge.vo;

import com.biencloud.smarthome.web.base.vo.BaseVO;
/**
 * 
 * 项目名称：smarthome-web-new 
 * 类名称：ChargeCalModeVO 
 * 类描述： 收费计算单位VO类
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:37:02
 */
public class ChargeCalUnitVO extends BaseVO {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;//计算单位名称
	private String reamrk;//描述
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReamrk() {
		return reamrk;
	}
	public void setReamrk(String reamrk) {
		this.reamrk = reamrk;
	}

    


}
