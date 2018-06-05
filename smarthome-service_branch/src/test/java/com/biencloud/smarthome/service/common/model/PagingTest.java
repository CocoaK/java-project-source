package com.biencloud.smarthome.service.common.model;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 分页实体对象测试类。
 * @author Matt Weng
 * @since 1.0 2012-4-19
 */
public class PagingTest {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	public void paging(){
		Paging<String> paging = new Paging<String>();
		paging.setPageNum(1);		
		paging.setTotalCount(998);
		
		Assert.assertEquals(100, paging.getTotalPages());
		Assert.assertFalse(paging.hasPrevious());
		Assert.assertTrue(paging.hasNext());
		
		paging.setPageNum(4);
		paging.setPageSize(20);
		Assert.assertEquals(50, paging.getTotalPages());
		Assert.assertTrue(paging.hasPrevious());
		Assert.assertTrue(paging.hasNext());
		
		paging.setPageNum(50);
		Assert.assertFalse(paging.hasNext());
		
		logger.info("分页信息：{}", paging);
	}
}
