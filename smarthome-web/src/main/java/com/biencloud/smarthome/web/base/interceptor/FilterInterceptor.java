package com.biencloud.smarthome.web.base.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.util.DFAUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 敏感词拦截器。
 * @since 1.0 2015-7-23
 */
@SuppressWarnings("serial")
public class FilterInterceptor extends MethodFilterInterceptor {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	

		
	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		Object action = actionInvocation.getAction();
		String result = null;
		if(!(action instanceof BaseAction)){
			return result;
		}
		try {
			ActionContext ac = actionInvocation.getInvocationContext();
			Map<String, Object> params = ac.getParameters();			
			if(params != null && !params.isEmpty()){
				for (Iterator<String> it = params.keySet().iterator(); it.hasNext();){
					Object value = params.get(it.next());
					result = filterParam(value);
					if(result!=null && !"".equals(result)){
						return Constants.WORDS_FORBIDDEN;
					}
				}
			}
		} catch (Exception e) {
			logger.error("********************过滤敏感字发生异常：{}", actionInvocation.getAction(), e);
		}
		String re = actionInvocation.invoke();
		return re;
	}

	//对参数值做敏感字过滤检查
	private String filterParam(Object value) throws UnsupportedEncodingException, IOException{
		String result = null;
		if(value instanceof String){
			result = DFAUtil.searchKeyword((String)value);
		}
		else if(value instanceof String[]){
			String[] srcValues = (String[]) value;
			if(srcValues != null && srcValues.length > 0){
				for (int idx = 0, size = srcValues.length; idx < size; idx++) {
					if(srcValues[idx] != null && !"".equals(srcValues[idx])){
						result = DFAUtil.searchKeyword(srcValues[idx]);
					}
					if(result!=null && !"".equals(result)){
						break;
					}
				}
			}
		}
		return result;
	}	
}
