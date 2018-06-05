package com.biencloud.smarthome.web.housemgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.service.IPropertyCompanyInfoService;
import com.biencloud.smarthome.web.housemgr.vo.PropertyCompanyInfoVo;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.PropertyCompanyInfo;

/**
 * 物业公司 Service 实现类
 * 
 * @author jsun  
 * @since 1.0 2012-5-17 上午9:22:03
 */
public class PropertyCompanyInfoServiceImpl extends
		BaseService<PropertyCompanyInfoVo> implements
		IPropertyCompanyInfoService {
	private static final String CREATE_TIME = "createTime";

	@Override
	public PropertyCompanyInfoVo getPropertyCompanyInfoDetail(Integer id) {
		PropertyCompanyInfoVo propertyCompanyInfoVo = new PropertyCompanyInfoVo();
		copyProperties(getSmartHomeService().getPropertyCompanyInfo(id),
				propertyCompanyInfoVo, CREATE_TIME);
		return propertyCompanyInfoVo;
	}

	@Override
	public void update(PropertyCompanyInfoVo propertyCompany) {
		PropertyCompanyInfo pc = new PropertyCompanyInfo();
		copyProperties(propertyCompany, pc);

		getSmartHomeService().updatePropertyCompanyInfo(pc);
	}

	@Override
	public PagingVO<PropertyCompanyInfoVo> queryPropertyCompanyInfosForPaging(
			PropertyCompanyInfoVo condition, int pageNum, int pageSize) {
		// Web层VO(Web层依赖这些模型) -> service层Model(service层依赖这些模型)
		PropertyCompanyInfo pc = new PropertyCompanyInfo();		
		copyProperties(condition, pc);

		Paging paging = getSmartHomeService().queryPropertyCompanyInfosForPaging(pc, pageNum, pageSize);

		return convertToPagingVO(paging, CREATE_TIME);
	}

	@Override
	public List<PropertyCompanyInfoVo> findPropertyCompanyInfos(PropertyCompanyInfoVo condition) {
		List<PropertyCompanyInfoVo> companies = new ArrayList<PropertyCompanyInfoVo>();

		PropertyCompanyInfo pc = new PropertyCompanyInfo();		
		copyProperties(condition, pc);
		List<PropertyCompanyInfo> list = getSmartHomeService().findPropertyCompanyInfos(pc);

		if (list != null) {
			for (PropertyCompanyInfo model : list) {
				PropertyCompanyInfoVo vo = new PropertyCompanyInfoVo();
				copyProperties(model, vo, CREATE_TIME);
				companies.add(vo);
			}
		}
		return companies;
	}

	@Override
	public int save(PropertyCompanyInfoVo propertyCompany) {
		PropertyCompanyInfo model = new PropertyCompanyInfo();		
		copyProperties(propertyCompany, model);

		return getSmartHomeService().savePropertyCompanyInfo(model);
	}

	@Override
	public void deletePropertyCompany(PropertyCompanyInfoVo propertyCompany) {
		PropertyCompanyInfo model = new PropertyCompanyInfo();
		copyProperties(propertyCompany, model);
		getSmartHomeService().removePropertyCompanyInfo(model);
		
	}
}
