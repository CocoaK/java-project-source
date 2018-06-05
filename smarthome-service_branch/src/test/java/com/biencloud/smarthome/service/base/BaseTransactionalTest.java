package com.biencloud.smarthome.service.base;

import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * 测试基类，事务提交。
 * @author Matt Weng
 * @since 1.0 2012-5-7
 */
@Ignore
@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
public class BaseTransactionalTest extends AbstractJUnit4SpringContextTests {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 获取应用上下文。
	 * @return
	 */
	protected ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	/**
	 * 通过对象名称获取指定的对象。
	 * @param beanName 对象名称
	 * @return
	 */
	protected Object getBean(String beanName){
		return getApplicationContext().getBean(beanName);
	}
	
	/**
	 * 通过对象名称和类获取指定的对象。
	 * @param beanName 对象名称
	 * @param cl 对象类
	 * @return
	 */
	protected <T> T getBean(String beanName, Class<T> cl){
		return getApplicationContext().getBean(beanName, cl);
	}
}
