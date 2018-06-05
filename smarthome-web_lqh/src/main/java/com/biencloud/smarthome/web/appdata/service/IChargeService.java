package com.biencloud.smarthome.web.appdata.service;

import com.biencloud.smarthome.web.appdata.json.ChargeJson;
import com.biencloud.smarthome.web.charge.vo.ChargeDetailVO;

/**
 * 
 * 类名称：IChargeService 
 * 类描述： 收费接口
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-7-10 上午9:14:31
 */
public interface IChargeService {
	
	/**
	 * 方法的描述: 查询收费
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-11-27 上午10:58:26
	 * @param deviceNo
	 * @param chargeDetail
	 * @return
	 */
	public ChargeJson queryList(String deviceNo,ChargeDetailVO chargeDetail);
}
