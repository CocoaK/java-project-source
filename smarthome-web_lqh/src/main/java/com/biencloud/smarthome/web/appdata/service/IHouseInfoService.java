package com.biencloud.smarthome.web.appdata.service;

import com.biencloud.smarthome.web.appdata.vo.CompanyInfoVO;
import com.biencloud.smarthome.web.appdata.vo.HouseInfoVO;

/**
 * 
 * 类名称：ICompanyInfoService 
 * 类描述： 物业信息接口 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-14 上午9:09:26
 */
public interface IHouseInfoService {
	/**
	 * 
	 * 方法的描述: 查询房产信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-14 下午7:21:47
	 * @param areaCode
	 * @return
	 */
    public HouseInfoVO queryHouseInfo(String roomNo);
}
