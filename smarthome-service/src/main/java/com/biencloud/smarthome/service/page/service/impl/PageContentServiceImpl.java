package com.biencloud.smarthome.service.page.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.page.dao.IPageContentDao;
import com.biencloud.smarthome.service.page.model.PageContent;
import com.biencloud.smarthome.service.page.service.IPageContentService;
/**
 * 类名称：PageContentServiceImpl 
 * 类描述： 终端电商页面
 * @author: ykou  
 * @version: 0.1
 */
@Transactional(propagation = Propagation.SUPPORTS)
public class PageContentServiceImpl extends BaseService<PageContent,String> implements IPageContentService{

	private IPageContentDao pageContentDao;
	
	// 创建属性变量名和属性值映射。
	private Map<String, Object> createQueryParams(StringBuilder jpql, 
			PageContent pageContent) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (pageContent == null)
			return params;
		if (StringUtils.isNotBlank(pageContent.getUpdateId()))
			appendCondition(jpql, "p.updateId = :updateId", "updateId", 
					pageContent.getUpdateId(), params);
		
		return params;
	}
	

	public IPageContentDao getPageContentDao() {
		return pageContentDao;
	}


	public void setPageContentDao(IPageContentDao pageContentDao) {
		this.pageContentDao = pageContentDao;
	}

	@Override
	public List<PageContent> queryAllPageContents(PageContent pageContent) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();

		jpql.insert(0, "SELECT p FROM PageContent p");
		if(pageContent == null){
			return findByNamedParams(jpql.toString(), params);
		}
		if (StringUtils.isNotBlank(pageContent.getUpdateId())){
			appendCondition(jpql, "p.updateId = :updateId", "updateId",pageContent.getUpdateId(), params);
		}
		return findByNamedParams(jpql.toString(), params);
	}

	@Override
	public Paging<PageContent> queryPageForPaging(PageContent pageContent,
			int pageNum, int pageSize) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> params = createQueryParams(jpql, pageContent);
		jpql.insert(0, "SELECT " + REPLACE_CHARS + " FROM PageContent p");
		String queryString = jpql.toString().replace(REPLACE_CHARS, "p");
		String queryStringCount = jpql.toString().replace(REPLACE_CHARS, "COUNT(p)");
		//queryString = queryString + " order by p.createdTime desc ";
		return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
	}

}
