package com.biencloud.smarthome.service.housemgr.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.biencloud.smarthome.service.base.BaseTest;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.housemgr.model.PropertyCompanyInfo;

/**
 * 物业公司 Service 单元测试
 * 
 * @author jsun
 * @since 1.0 2012-5-14
 */
public class PropertyCompanyInfoServiceTest extends BaseTest {
	@Autowired
	private IPropertyCompanyInfoService service;

	@Test
	public void testUpdatePropertyCompanyInfo() {
		PropertyCompanyInfo entity = service.get(Integer.valueOf(1));
		entity.setName("b");
		service.update(entity);

		logger.info("修改物业公司信息后：{}", entity);
	}

	@Test
	public void testQueryPaging() {
		PropertyCompanyInfo propertyCompany = new PropertyCompanyInfo();
		propertyCompany.setName("a");

		Paging<PropertyCompanyInfo> paging = service.queryPropertyCompanyInfosForPaging(
				propertyCompany, 1, 10);

		logger.info("物业公司模糊查询分页信息：{}", paging);
	}

	@Test
	public void testSave() {
		PropertyCompanyInfo propertyCompany = new PropertyCompanyInfo();
		propertyCompany.setName("test");

		service.save(propertyCompany);
	}

	@Test
	public void testGetPropertyCompanyInfoByDeviceCode() {
		PropertyCompanyInfo propertyCompany = service.getPropertyCompanyInfoByDeviceCode("MC00021");
		logger.info("根据设备编号查询物业公司信息：{}", propertyCompany);
	}

	@Test
	public void testFindPropertyCompanyInfos() {
		PropertyCompanyInfo propertyCompany = new PropertyCompanyInfo();
		propertyCompany.setName("");

		List<PropertyCompanyInfo> companies = service.findPropertyCompanyInfos(propertyCompany);

		logger.info("物业公司模糊查询：{}", companies);
	}
}
