package com.biencloud.smarthome.web.base.interceptor;

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 针对请求参数值做前后空格处理的拦截器。
 * @author kouy
 * @since 1.0 2012-9-13
 */
@SuppressWarnings({"serial"})
public class ParamTrimInterceptor extends MethodFilterInterceptor {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		try {
			ActionContext ac = actionInvocation.getInvocationContext();
			Map<String, Object> params = ac.getParameters();			
			if(params != null && !params.isEmpty()){
				for (Iterator<String> it = params.keySet().iterator(); it.hasNext();)
					trimParam(params, it.next());
			}
		} catch (Exception e) {
			LOGGER.error("********************过滤{}前后空格发生异常不影响目标动作的调用，异常信息如下：{}", actionInvocation.getAction(), e);
		}
		return actionInvocation.invoke();
	}

	//对参数值做空格处理
	private void trimParam(Map<String, Object> params, String paramName){
		Object value = params.get(paramName);
		if(value == null)
			return;
		
		if(value instanceof String){
			String srcValue = (String) value;
			if(srcValue != null)
				params.put(paramName, srcValue.trim());
		}else if(value instanceof String[]){
			String[] srcValues = (String[]) value;
			if(srcValues != null && srcValues.length > 0){
				for (int idx = 0, size = srcValues.length; idx < size; idx++) {
					if(srcValues[idx] != null)
						srcValues[idx] = srcValues[idx].trim();
				}
				params.put(paramName, srcValues);
			}
		}
	}	
}
