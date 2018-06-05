package com.biencloud.smarthome.web.appdata.service;

import com.biencloud.smarthome.web.appdata.vo.CompanyInfoVO;

/**
 * 
 * 类名称：ICompanyInfoService 
 * 类描述： 物业信息接口 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-14 上午9:09:26
 */
public interface ICompanyInfoService {
	/**
	 * 
	 * 方法的描述: 查询物业信息
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-14 上午9:10:54
	 * @param deviceCode
	 * @return
	 */
    public CompanyInfoVO queryCompanyInfo(String deviceCode);
}
