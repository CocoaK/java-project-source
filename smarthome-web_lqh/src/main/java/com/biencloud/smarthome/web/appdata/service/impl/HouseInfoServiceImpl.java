package com.biencloud.smarthome.web.appdata.service.impl;

import com.biencloud.smarthome.web.appdata.service.ICompanyInfoService;
import com.biencloud.smarthome.web.appdata.service.IHouseInfoService;
import com.biencloud.smarthome.web.appdata.vo.CompanyInfoVO;
import com.biencloud.smarthome.web.appdata.vo.HouseInfoVO;
import com.biencloud.smarthome.web.base.service.BaseService;
/**
 * 
 * 类名称：HouseInfoServiceImpl 
 * 类描述： 查询房产信息接口实现类
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-14 下午7:22:12
 */
public class HouseInfoServiceImpl extends BaseService<HouseInfoVO> implements
IHouseInfoService{

	@Override
	public HouseInfoVO queryHouseInfo(String roomNo) {
		HouseInfoVO hiv=null;
		if(roomNo!=null)
		{
			hiv=new HouseInfoVO("555", "1", "2房2厅", "http://192.168.1.10:8080/ssss.jpg", Float.valueOf("220.2"));
		}
		return hiv;
	}

	

}
