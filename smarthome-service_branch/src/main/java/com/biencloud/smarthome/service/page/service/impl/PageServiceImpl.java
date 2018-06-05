package com.biencloud.smarthome.service.page.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.ad.enums.AdStatus;
import com.biencloud.smarthome.service.ad.model.AdTarget;
import com.biencloud.smarthome.service.ad.model.Advertisement;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.HousingDistrictInfo;
import com.biencloud.smarthome.service.page.dao.IPageDao;
import com.biencloud.smarthome.service.page.model.Component;
import com.biencloud.smarthome.service.page.model.PageLayout;
import com.biencloud.smarthome.service.page.model.PageDistrict;
import com.biencloud.smarthome.service.page.service.IComponentService;
import com.biencloud.smarthome.service.page.service.IPageService;
/**
 * 类名称：PageServiceImpl 
 * 类描述： 终端电商页面
 * @author: ykou  
 * @version: 0.1
 */
@Transactional(propagation = Propagation.SUPPORTS)
public class PageServiceImpl extends BaseService<PageLayout,String> implements IPageService{

	private IPageDao pageDao;
	private IComponentService componentService;
	
	// 创建属性变量名和属性值映射。
	private Map<String, Object> createQueryParams(StringBuilder jpql, 
			PageLayout page) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (page == null)
			return params;
		if (StringUtils.isNotBlank(page.getId()))
			appendCondition(jpql, "p.id = :id", "id", 
					page.getId(), params);
		
		if (StringUtils.isNotBlank(page.getDistrictId()))
			appendCondition(jpql, "pd.housingDistrictInfo.id = :districtId", "districtId", 
					page.getDistrictId(), params);
		
		if (StringUtils.isNotBlank(page.getId()))
			appendCondition(jpql, "p.updateId = :updateId", "updateId", 
					page.getUpdateId(), params);

		if (StringUtils.isNotBlank(page.getName()))
			appendCondition(jpql, "p.name like :name", "name", 
					"%"+page.getName()+"%", params);
	
		return params;
	}
	
	
	public IPageDao getPageDao() {
		return pageDao;
	}

	public void setPageDao(IPageDao pageDao) {
		this.pageDao = pageDao;
	}

	@Override
	public List<PageLayout> queryAllPages(PageLayout page) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();

		jpql.insert(0, "SELECT page FROM Page page,PageDistrict pd WHERE page.id=pd.pageId");
		if(page == null){
			return findByNamedParams(jpql.toString(), params);
		}
		if (StringUtils.isNotBlank(page.getId())){
			appendCondition(jpql, "page.id = :id", "id",page.getId(), params);
		}
		if (StringUtils.isNotBlank(page.getUpdateId())){
			appendCondition(jpql, "page.updateId = :updateId", "updateId",page.getUpdateId(), params);
		}
		if (StringUtils.isNotBlank(page.getName())){
			appendCondition(jpql, "page.name = :name", "name", page.getName(), params);
		}
		if (StringUtils.isNotBlank(page.getDistrictId()))
			appendCondition(jpql, "pd.housingDistrictInfo.id = :districtId", "districtId",page.getDistrictId(), params);
		return findByNamedParams(jpql.toString(), params);
	}


	@Override
	public Paging<PageLayout> queryPageForPaging(PageLayout page, int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		jpql.insert(0, "SELECT " + REPLACE_CHARS + " FROM Page p,PageDistrict pd WHERE p.id=pd.pageId");
		Map<String, Object> params = createQueryParams(jpql, page);
		String queryString = jpql.toString().replace(REPLACE_CHARS, "p");
		String queryStringCount = jpql.toString().replace(REPLACE_CHARS, "COUNT(p)");
		//queryString = queryString + " order by p.createdTime desc ";
		return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(PageLayout page) {
		getDao().save(page);
		
		List<String> districtIds = page.getDistricts();
		//List<PageDistrict> list = page.getDistricts();
		if(CollectionUtils.isEmpty(districtIds))
			return;
		
		for (String districtId : districtIds) {
			PageDistrict pd = new PageDistrict();
			HousingDistrictInfo housingDistrictInfo = new HousingDistrictInfo();
			housingDistrictInfo.setId(districtId);
			pd.setHousingDistrictInfo(housingDistrictInfo);
			pd.setPageId(page.getId());
			getDao().saveObject(pd);
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void remove(PageLayout page){
		Component component = new Component();
		component.setPageId(page.getId());
		List<Component> components = componentService.queryComponents(component);
		//删除页面所属组件
		componentService.removeAll(components);
		
		//删除页面和小区的关系
		List<Object> pds = getDao().findObjects("FROM PageDistrict pd WHERE pd.pageId=?1",page.getId());
		getDao().removeObjects(pds);
		
		//删除页面
		getPageDao().remove(page);
	}


	public IComponentService getComponentService() {
		return componentService;
	}


	public void setComponentService(IComponentService componentService) {
		this.componentService = componentService;
	}
	
	
}
