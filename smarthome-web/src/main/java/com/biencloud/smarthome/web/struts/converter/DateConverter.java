package com.biencloud.smarthome.web.struts.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

/**
 * 日期转换器。
 * @author kouy
 * @since 1.0 2012-5-10
 */
@SuppressWarnings("rawtypes")
public class DateConverter extends DefaultTypeConverter {
	
	private static final String SHORT = "yyyy-MM-dd";	
	private static final String MEDIUM = "yyyy-MM-dd HH:mm";
	private static final String LONG = "yyyy-MM-dd HH:mm:ss";
	
	//数组元素顺序应从复杂到简单
	private static final DateFormat[] SUPPORT_DATE_FORMATS = {
			new SimpleDateFormat(LONG), 
			new SimpleDateFormat(MEDIUM), 
			new SimpleDateFormat(SHORT)};
		
	@Override   
	public Object convertValue(Map context, Object value, Class toType) {   
		if (toType == Date.class) {
			String[] params = (String[])value;   
        	String dateString = params[0];
        	for (DateFormat df : SUPPORT_DATE_FORMATS) {
        		try {
					return df.parse(dateString);
				} catch (ParseException e) {
					//当转换发生异常时不需要捕获，继续尝试下一个转换
				}
			} 
		}      
		return null;   
	}	
}
