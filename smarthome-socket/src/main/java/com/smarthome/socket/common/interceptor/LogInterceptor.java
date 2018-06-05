package com.smarthome.socket.common.interceptor;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志记录拦截器。
 * @author kouy
 * @since 1.0 2012-6-8
 */
public class LogInterceptor implements MethodInterceptor {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		logger.info("********************开始调用方法{}，参数为：{}", 
				new Object[]{mi.getMethod(), Arrays.toString(mi.getArguments())});
		Object obj = null;
		try {
			obj = mi.proceed();
		} catch (Exception e) {
			logger.error("********************调用方法{}发生异常：{}", new Object[]{mi.getMethod(), e});
			throw e;
		}finally{
			logger.info("********************结束调用方法{}，返回值为：{}", new Object[]{mi.getMethod(), obj});
		}
		
		return obj;
	}
}
