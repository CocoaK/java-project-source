package com.biencloud.smarthome.web.base.service;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.util.CollectionUtils;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.SmartHomePubService;

/**
 * 服务代理基类。
 * @author kouy
 * @since 1.0 2012-4-27
 */
public class BaseService<T> extends ApplicationObjectSupport {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	protected Class<T> voClass = null;
	
	private SmartHomePubService smartHomeService;
	
	@SuppressWarnings("unchecked")
	public BaseService(){
		voClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
		
	public SmartHomePubService getSmartHomeService() {
		return smartHomeService;
	}

	public void setSmartHomeService(SmartHomePubService smartHomeService) {
		this.smartHomeService = smartHomeService;
	}
	
	/**
	 * 通过Bean名称获取指定Bean对象。
	 * @param beanName
	 * @return
	 */
	protected Object getBean(String beanName){
		return getApplicationContext().getBean(beanName);
	}
	
	/**
	 * 将分页信息转换为分页值对象。
	 * @param paging 分页对象
	 * @param xmlDateProperties 分页对象中结果对象XMLGregorianCalendar的属性
	 * @return
	 */
	protected PagingVO<T> convertToPagingVO(Paging paging, 
			String... xmlDateProperties){
		PagingVO<T> pagingVO = new PagingVO<T>();
		if(paging == null)
			return pagingVO;
					
		org.springframework.beans.BeanUtils.copyProperties(paging, pagingVO);
		
		List<T> voResults = new ArrayList<T>();
		List<Object> results = paging.getResults();
		if(!CollectionUtils.isEmpty(results)){
			for (int index = 0, size = results.size(); index < size; index++) {
				T vo = org.springframework.beans.BeanUtils.instantiate(voClass);			
				copyProperties(results.get(index), vo, true, xmlDateProperties);				
				voResults.add(vo);
			}
		}
		pagingVO.setResults(voResults);
		
		return pagingVO;
	}
	
	/**
	 * 将分页信息转换为分页值对象。
	 * @param paging 分页对象
	 * @param ignoreProperties 分页对象中结果对象忽略属性集
	 * @param xmlDateProperties 分页对象中结果对象XMLGregorianCalendar的属性
	 * @return
	 */
	protected PagingVO<T> convertToVO(Paging paging, 
			String[] ignoreProperties, String... xmlDateProperties){
		PagingVO<T> pagingVO = new PagingVO<T>();
		if(paging == null)
			return pagingVO;
					
		org.springframework.beans.BeanUtils.copyProperties(paging, pagingVO);
		
		List<T> voResults = new ArrayList<T>();
		List<Object> results = paging.getResults();
		if(results!=null&&!CollectionUtils.isEmpty(results)){
			for (int index = 0, size = results.size(); index < size; index++) {
				T vo = org.springframework.beans.BeanUtils.instantiate(voClass);			
				copyProperties(results.get(index), vo, ignoreProperties, true, xmlDateProperties);				
				voResults.add(vo);
			}
		}
		pagingVO.setResults(voResults);
		
		return pagingVO;
	}
	
	/**
	 * 将源数组中对象的属性值拷贝到目标数组的对象，可将XMLGregorianCalendar和Date类型互转。
	 * @param source 源对象
	 * @param target 目标对象
	 * @param isXmlCalendarToDate 如果为true则XMLGregorianCalendar转为Date，否则相反
	 * @param dateProperties 日期属性
	 */
	protected void copyArray(Object[] source, Object[] target, 
			boolean isXmlCalendarToDate, String... dateProperties){
		if(ArrayUtils.isEmpty(source) 
				|| ArrayUtils.isEmpty(target))
			return;
		for (int index = 0, size = source.length; index < size; index++) 
			copyProperties(source[index], target[index], 
					isXmlCalendarToDate, dateProperties);
	}

	/**
	 * 将源集合中对象的属性值拷贝到目标集合的对象，可将XMLGregorianCalendar和Date类型互转。
	 * @param source 源对象
	 * @param target 目标对象
	 * @param isXmlCalendarToDate 如果为true则XMLGregorianCalendar转为Date，否则相反
	 * @param dateProperties 日期属性
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void copySet(Set source, Set target, 
			boolean isXmlCalendarToDate, String... dateProperties){	
		if(CollectionUtils.isEmpty(source) 
				|| CollectionUtils.isEmpty(target))
			return;
		
		copyArray(source.toArray(new Object[0]), target.toArray(new Object[0]), 
				isXmlCalendarToDate, dateProperties);
	}
	
	/**
	 * 将源列表中对象的属性值拷贝到目标列表的对象，可将XMLGregorianCalendar和Date类型互转。
	 * @param source 源对象
	 * @param target 目标对象
	 * @param isXmlCalendarToDate 如果为true则XMLGregorianCalendar转为Date，否则相反
	 * @param dateProperties 日期属性
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void copyList( List source, List target, 
			boolean isXmlCalendarToDate, String... dateProperties){
		if(CollectionUtils.isEmpty(source) 
				|| CollectionUtils.isEmpty(target))
			return;
		
		copyArray(source.toArray(new Object[0]), target.toArray(new Object[0]), 
				isXmlCalendarToDate, dateProperties);
	}
	
	/**
	 * 将源对象的属性值拷贝到目标对象，可将XMLGregorianCalendar和Date类型互转。
	 * @param source 源对象
	 * @param target 目标对象
	 * @param isXmlCalendarToDate 如果为true则XMLGregorianCalendar转为Date，否则相反
	 * @param dateProperties 日期属性
	 */
	protected void copyProperties(Object source, Object target, 
			boolean isXmlCalendarToDate, String... dateProperties){
		if(source == null || target == null)
			return;
		
		org.springframework.beans.BeanUtils.copyProperties(
				source, target, dateProperties);
		for (String property : dateProperties) {
			if (isXmlCalendarToDate) {
				XMLGregorianCalendar xmlCal = (XMLGregorianCalendar)getProperty(source, property);
				if(xmlCal != null)
					copyProperty(target, property, convertToDate(xmlCal));
			} else {
				Date date = (Date)getProperty(source, property);				
				if(date != null)
					copyProperty(target, property, convertToXMLGregorianCalendar(date));
			}
								
		}
	}
	
	/**
	 * 将源对象的属性值拷贝到目标对象，可将XMLGregorianCalendar和Date类型互转。
	 * @param source 源对象
	 * @param target 目标对象
	 * @param ignoreProperties 忽略属性集
	 * @param isXmlCalendarToDate 如果为true则XMLGregorianCalendar转为Date，否则相反
	 * @param dateProperties 日期属性
	 */
	protected void copyProperties(Object source, Object target, 
			String[] ignoreProperties, boolean isXmlCalendarToDate, String... dateProperties){
		if(source == null || target == null)
			return;
		
		org.springframework.beans.BeanUtils.copyProperties(
				source, target, (String[])ArrayUtils.addAll(ignoreProperties, dateProperties));
		for (String property : dateProperties) {
			if (isXmlCalendarToDate) {
				XMLGregorianCalendar xmlCal = (XMLGregorianCalendar)getProperty(source, property);
				if(xmlCal != null)
					copyProperty(target, property, convertToDate(xmlCal));
			} else {
				Date date = (Date)getProperty(source, property);
				if(date != null)
					copyProperty(target, property, convertToXMLGregorianCalendar(date));
			}
								
		}
	}
	
	/**
	 * 将源对象的属性值拷贝到目标对象，可将XMLGregorianCalendar和Date类型互转。
	 * @param source 源对象
	 * @param target 目标对象
	 * @param ignoreProperties 忽略属性集
	 */
	protected void copyProperties(Object source, 
			Object target, String... ignoreProperties){
		if(source == null || target == null)
			return;
		
		org.springframework.beans.BeanUtils.copyProperties(
				source, target, ignoreProperties);
	}
	
	/**
	 * 将Date转换成XMLGregorianCalendar类型日期。
	 * @param date 日期
	 * @return
	 */
	protected XMLGregorianCalendar convertToXMLGregorianCalendar(Date date){
		if(date == null)
			return null;
		XMLGregorianCalendar xmlGregorianCalendar = null;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		try{
			xmlGregorianCalendar= DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		}catch (DatatypeConfigurationException e) {
			logger.error("************Date转换成XMLGregorianCalendar发生异常：{}", e);
		}
		
		return xmlGregorianCalendar;
	}

	/**
	 * 将XMLGregorianCalendar转换成Date类型日期。
	 * @param xmlGregorianCalendar xml日历
	 * @return
	 */
	protected Date convertToDate(XMLGregorianCalendar xmlGregorianCalendar){
        if(xmlGregorianCalendar == null)
        	return null;
		GregorianCalendar cal =xmlGregorianCalendar.toGregorianCalendar();
        return cal.getTime();
    }
	
	/**
	 * 获取目标对象指定属性的值。
	 * @param obj 目标对象
	 * @param property 指定属性
	 * @return
	 */
	protected Object getProperty(Object obj, String property){
		if(obj == null || StringUtils.isBlank(property))
			return null;
		
		try {
			return PropertyUtils.getProperty(obj, property);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	
	/**
	 * 设置目标对象指定属性的值。
	 * @param obj 目标对象
	 * @param property 指定属性
	 * @param value 属性值
	 */
	protected void copyProperty(Object obj, String property, Object value){
		if(obj == null || StringUtils.isBlank(property))
			return;
		
		try {
			BeanUtils.copyProperty(obj, property, value);
		} catch (Exception e) {			
			throw new RuntimeException(e);
		} 
	}
}
