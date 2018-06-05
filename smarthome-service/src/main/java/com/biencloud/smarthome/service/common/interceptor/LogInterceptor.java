package com.biencloud.smarthome.service.common.interceptor;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志记录拦截器。
 * @author kouy
 * @since 1.0 2012-6-8
 */
public class LogInterceptor implements MethodInterceptor {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private String[] excludePatterns;

	/**
	 * 设置要忽略记录日志的正则表达式（表示方法）。
	 * @param excludePatterns 忽略记录日志的正则表达式
	 */
	public void setExcludePatterns(String[] excludePatterns) {
		this.excludePatterns = excludePatterns;
	}


	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		boolean logFlag = isLog(mi.getMethod().getName());
		if(logFlag)
			logger.info("********************开始调用方法{}，参数为：{}", 
					new Object[]{mi.getMethod(), Arrays.toString(mi.getArguments())});
		Object obj = null;
		try {
			obj = mi.proceed();
		} catch (Exception e) {
			logger.error("********************调用方法{}发生异常：{}", new Object[]{mi.getMethod(), e});
			throw e;
		}finally{
			if(logFlag)
				logger.info("********************结束调用方法{}，返回值为：{}", new Object[]{mi.getMethod(), obj});
		}
		
		return obj;
	}
	
	//是否记录日志
	private boolean isLog(String methodName){
		if(ArrayUtils.isEmpty(excludePatterns))
			return true;
				
		for (String ep : excludePatterns) {
			if(Pattern.matches(ep, methodName))
				return false;
		}
		
		return true;
	}
}
