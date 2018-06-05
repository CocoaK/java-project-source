package com.biencloud.smarthome.web.appdata.json;

import java.util.List;

import com.biencloud.smarthome.web.appdata.vo.ChargeVO;
import com.biencloud.smarthome.web.charge.vo.ChargeDetailVO;
/**
 * 
 * 类名称：ChargeJson 
 * 类描述： 收费json
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-5-31 下午5:42:39
 */
public class ChargeJson extends Json{
	
	private List<ChargeVO> charge;

	public List<ChargeVO> getCharge() {
		return charge;
	}

	public void setCharge(List<ChargeVO> charge) {
		this.charge = charge;
	}

}
