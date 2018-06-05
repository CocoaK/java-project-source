package com.biencloud.smarthome.web.appdata.service.impl;

import com.biencloud.smarthome.web.appdata.constant.AppDataConstant;
import com.biencloud.smarthome.web.appdata.service.ICompanyInfoService;
import com.biencloud.smarthome.web.appdata.vo.CompanyInfoVO;
import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.housemgr.vo.PropertyCompanyInfoVo;
import com.biencloud.smarthome.web.wsclient.stub.PropertyCompanyInfo;
/**
 * 
 * 类名称：CompanyInfoServiceImpl 
 * 类描述： 物业信息接口实现类
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-14 上午9:08:50
 */
public class CompanyInfoServiceImpl extends BaseService<CompanyInfoVO> implements
ICompanyInfoService{

	@Override
	public CompanyInfoVO queryCompanyInfo(String deviceNo) {
		CompanyInfoVO cif=null;
		if(deviceNo!=null)
		{
			PropertyCompanyInfo pcif=getSmartHomeService().getPropertyCompanyInfoByDeviceCode(deviceNo);
			if(pcif!=null)
			{
				cif=new CompanyInfoVO(pcif.getName(), pcif.getProfile(), pcif.getContact(), pcif.getCharge());				
				cif.setDeviceNo(deviceNo);
				cif.setCode(AppDataConstant.SUCCESS);
			}
			
		}
		return cif;
	}

}
