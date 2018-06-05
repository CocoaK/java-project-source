package com.biencloud.smarthome.web.base;

import org.apache.struts2.StrutsSpringTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * 测试基类。
 * 
 * @author Matt Weng
 * @since 1.0 2012-4-10
 */

public class BaseTest  {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private ApplicationContext applicationContext;

	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
	}

	@After
	public void tearDown() throws Exception {
		applicationContext = null;
	}

	/**
	 * 获取应用上下文。
	 * 
	 * @return
	 */
	protected ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 通过对象名称获取指定的对象。
	 * 
	 * @param beanName
	 *            对象名称
	 * @return
	 */
	protected Object getBean(String beanName) {
		return getApplicationContext().getBean(beanName);
	}

	/**
	 * 通过对象名称和类获取指定的对象。
	 * 
	 * @param beanName
	 *            对象名称
	 * @param cl
	 *            对象类
	 * @return
	 */
	protected <T> T getBean(String beanName, Class<T> cl) {
		return getApplicationContext().getBean(beanName, cl);
	}

	
}
