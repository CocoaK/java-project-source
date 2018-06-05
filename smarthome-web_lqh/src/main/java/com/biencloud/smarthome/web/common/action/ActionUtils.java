package com.biencloud.smarthome.web.common.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * 类名称：action公共类型
 * 
 * @author: dehuaye
 * @version: 0.1
 * @date: 2012-5-7 上午10:55:57
 */
public class ActionUtils {

	/** 是否直接到添加页面的标识 */
	public static final String ForwardJspMark = "ForwardJspMark";
	/** 是否显示明细页面的标识  */
	public static final String SHOWDETAIL= "ShowDetail";
	
	/**
	 * 
	 * 方法的描述:获取请示的 URI
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-29 下午3:29:30
	 * @param request
	 * @return
	 */
	public static String getRequestURI(HttpServletRequest request) {
		String path = "";
		try {
			path = request.getRequestURI();
		} catch (Exception e) {
		}
		return path;
	}
	
	/**
	 * 
	 * 方法的描述:把字符串通过流写出 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-29 下午3:29:07
	 * @param response
	 * @param targetContent
	 */
	public static void printMsg(HttpServletResponse response,String targetContent){
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			out = response.getWriter();
			out.write(targetContent);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 *判断是否请求直接到页面
	 * 
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static boolean judgToPage(HttpServletRequest request) {
		String QueryString = request.getQueryString() == null ? "" : request.getQueryString();
		if (QueryString.indexOf(ForwardJspMark) == -1) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 *判断是否显示明细（用来区分更新，因为显示明细和更细共同一个页面)
	 * 
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static boolean judgShowDetail(HttpServletRequest request) {
		String QueryString = request.getQueryString() == null ? "" : request.getQueryString();
		if (QueryString.indexOf(SHOWDETAIL) == -1) {
			return false;
		} else {
			return true;
		}
	}



	/**
	 *判断请求链接是否包括指定的内容
	 * 
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static boolean judgContainsValue(HttpServletRequest request, String targetValue) {
		String QueryString = request.getQueryString() == null ? "" : request.getQueryString();
		if (QueryString.indexOf(targetValue) == -1) {
			return false;
		} else {
			return true;
		}
	}

}
