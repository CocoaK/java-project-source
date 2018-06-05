package com.biencloud.smarthome.service.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @desc   跨域处理
 * @author cocoa
 * @since  2018-4-19
 */
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig var1) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		servletResponse.addHeader("Access-Control-Allow-Origin", "*");
		chain.doFilter(request, response);
	}
	
    public void destroy() {}
}