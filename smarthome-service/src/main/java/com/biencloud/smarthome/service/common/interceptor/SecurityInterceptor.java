package com.biencloud.smarthome.service.common.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.biencloud.smarthome.service.rest.model.AuthSession;
import com.biencloud.smarthome.service.rest.service.IAuthSessionService;

public class SecurityInterceptor implements HandlerInterceptor {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private transient IAuthSessionService authSessionService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Map map = request.getParameterMap();
		//签名验证
//		if(map != null){
//			Iterator<Entry<String,String[]>> it = map.entrySet().iterator();
//
//			while (it.hasNext()) {
//			    Entry<String,String[]> entry = it.next();
//			    String name = entry.getKey();
//
//			    if (name.startsWith(portletNamespace)) {
//			        mpParams2.put(name.substring(portletNamespace.length(), name.length()), entry.getValue());
//			    }
//			    else {
//			    	map.put(newkey,map.remove(oldkey));    mpParams2.put(name, entry.getValue());
//			    }
//			    
//			}
//		}
		AuthSession as = null;
		String ssid = request.getParameter("sessionKey") + "";		
		HttpSession httpSession = request.getSession();
		logger.debug("request ssid:" + ssid + ",request.getSession():" + httpSession.getId());
		if(!StringUtils.isEmpty(ssid)){
			as = (AuthSession) httpSession.getAttribute(AuthSession.AUTH_SESSION_KEY);
			if(as != null && as.getSessionKey().equals(ssid)){
				return true;
			}else{
				as = authSessionService.getBySessionKey(ssid);
				if(as != null){
					httpSession.setAttribute(AuthSession.AUTH_SESSION_KEY, as);
					return true;
				}
			}
		}	
		if(as == null){
			//do something
		}
		
		throw new AuthorizationException();
		// intercept
//		HttpSession session = request.getSession();
//		if (session.getAttribute("user") == null) {
//			throw new AuthorizationException();
//		} else {
//			return true;
//		}
	}

	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse res, Object arg2, Exception arg3)
			throws Exception {
		logger.debug("this is requst afterCompletion");
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2, ModelAndView arg3) throws Exception {
		logger.debug("this is requst postHandle");
		
	}
}
