package com.biencloud.smarthome.service.wsservice;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.biencloud.smarthome.cxfservice.service.SmartHomeFileService;
import com.biencloud.smarthome.service.base.BaseTest;

public class WSServiceTest extends BaseTest {
	// @Test
	public static void main(String[] arg) {
		String file_wsdl = "http://127.0.0.1:8080/smarthomeservice/services/fileService?wsdl";
		testSmartHomeFileService(file_wsdl);
	}

	public static void testSmartHomeFileService(String wsdl) {

		SmartHomeFileService client = (SmartHomeFileService) getPubService(wsdl,SmartHomeFileService.class);
		
		String u = client.findById(new Long(1))==null?"no":client.findById(new Long(1)).getFileName();
		System.out.println(u);
	}

	public static Object getPubService(String wsdl,Class _class) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(_class);
		// factory.setServiceClass(SmartHomePubServiceImpl.class);

		factory.setAddress(wsdl);
		return factory.create();
	}
}
