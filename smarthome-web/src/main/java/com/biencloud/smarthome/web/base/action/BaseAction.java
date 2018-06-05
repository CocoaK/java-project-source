package com.biencloud.smarthome.web.base.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.login.vo.LoginVO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 动作基类。
 * @author kouy
 * @since 1.0 2012-4-9
 * @see ActionSupport
 */
@SuppressWarnings("serial")
public class BaseAction<T> extends ActionSupport implements ServletRequestAware, 
		ServletResponseAware, ServletContextAware, SessionAware {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private String menuCode;
	private String operationCode;
	private boolean ignoreLog;
	private Set<String> errorResults;
	
	private PagingVO<T> page;
	
	private HttpServletRequest request;
	private HttpServletResponse response;	
	private Map<String, Object> session;
	private ServletContext servletContext;
	private String jsonString;
	private String macAddr;
	private String deviceNumber;
	
	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public boolean isIgnoreLog() {
		return ignoreLog;
	}

	public void setIgnoreLog(boolean ignoreLog) {
		this.ignoreLog = ignoreLog;
	}

	public Set<String> getErrorResults() {
		return errorResults;
	}

	public void setErrorResults(String errorResults) {
		this.errorResults = new HashSet<String>();
		if(StringUtils.isNotBlank(errorResults)){
			String[] ers = StringUtils.split(errorResults, ',');
			for (String er : ers) {
				this.errorResults.add(er);
			}
		}
	}
	
	public PagingVO<T> getPage() {
		return page;
	}

	public void setPage(PagingVO<T> page) {
		this.page = page;
	}	

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	
	/**
	 * 获取HttpServletResponse对象。
	 * @return
	 */
	protected HttpServletResponse getResponse(){
		return response;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
	
	/**
	 * 获取HttpServletRequest对象。
	 * @return
	 */
	protected HttpServletRequest getRequest(){
		return request;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;		
	}
	
	/**
	 * 获取Session键值映射。
	 * @return
	 */
	public Map<String, Object> getSession() {
		return this.session;		
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;		
	}

	/**
	 * 获取Web应用上下文。
	 * @return
	 */
	public ServletContext getServletContext() {
		return servletContext;
	}
	
	/**
	 * 获取IP地址。
	 * @return
	 */
	public String getIp(){
		String ip = getRequest().getHeader("x-forwarded-for");
		if(isValidIp(ip))
			return ip;
		ip = getRequest().getHeader("Proxy-Client-IP");
		if(isValidIp(ip))
			return ip;
		ip = getRequest().getHeader("WL-Proxy-Client-IP");
		if(isValidIp(ip))
			return ip;
	    return getRequest().getRemoteAddr();
	}
	
	/**
	 * 设置请求属性的值。
	 * @param attName 属性名
	 * @param attValue 属性值
	 */
	protected void setRequestAttribute(String attName, 
			Object attValue){
		request.setAttribute(attName, attValue);
	}
	
	/**
	 * 获取请求属性的值。
	 * @param attName 属性名
	 */
	protected Object getRequestAttribute(String attName){
		return request.getAttribute(attName);
	}
	
	/**
	 * 删除指定的请求属性。
	 * @param attName 属性名
	 */
	protected void removeRequestAttribute(String attName){
		request.removeAttribute(attName);
	}
	
	/**
	 * 设置会话属性的值。
	 * @param attName 属性名
	 * @param attValue 属性值
	 */
	protected void setSessionAttribute(String attName, 
			Object attValue){
		getSession().put(attName, attValue);
	}
	
	/**
	 * 获取会话属性的值。
	 * @param attName 属性名
	 */
	protected Object getSessionAttribute(String attName){
		return getSession().get(attName);
	}
	
	/**
	 * 删除指定的会话属性。
	 * @param attName 属性名
	 */
	protected void removeSessionAttribute(String attName){
		getSession().remove(attName);
	}
	
	/**
	 * 获取用户登录信息。
	 * @return
	 */
	protected LoginVO getLoginVO(){
		return (LoginVO)getSessionAttribute(Constants.KEY_LOGIN_SESSION);
	}
	
	/**
	 * 获取用户登录名。
	 * @return
	 */
	protected String getLoginName(){
		return getLoginVO().getLoginName();
	}
	
	/**
	 * 获取用户ID。
	 * @return
	 */
	protected String getUserId(){
		return getLoginVO().getUserId();
	}
	
	/**
	 * 获取用户登录名。
	 * 获取用户类型。
	 * @return
	 */
	public String getUserType(){
		return getLoginVO().getUserType();
	} 
	
	/**
	 * 获取用户所属角色代码。
	 * @return
	 */
	protected String getRoleCode(){
		return getLoginVO().getRoleCode();
	}
	
	/**
	 * 获取小区编号。
	 * @return
	 */
	protected String getDistrictId(){
		if(getLoginVO() != null){
			return getLoginVO().getDistrictId();
		}
		return null;
	}
	
	/**
	 * 设置时段查询时起始日期的时间和结束日期的时间。<br/>
	 * 起始日期的时间设置为00:00:00。<br/>
	 * 结束日期的时间设置为23:59:59。<br/>
	 * @param beginDate 起始日期
	 * @param endDate 结束日期
	 */
	protected void setDateRange(Date beginDate, Date endDate){
		if(beginDate == null && endDate == null)
			return;
		
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
        
		if(beginDate != null){
			cal.setTime(beginDate);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			beginDate.setTime(cal.getTimeInMillis());
		}
		
		if(endDate != null){
			cal.setTime(endDate);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			endDate.setTime(cal.getTimeInMillis());
		}
	}
	
	/**
	 * 获取指定请求参数的值，如果不为空则过滤掉前后空格。
	 * @param paramName 请求参数名
	 * @return
	 */
	protected String getParameter(String paramName){
		String val = getRequest().getParameter(paramName);
		if(val == null)
			return val;
		return val.trim();
	}
	
	/**
	 * 获取指定请求参数的值（数组），如果不为空则过滤掉前后空格。
	 * @param paramName 请求参数名
	 * @return
	 */
	protected String[] getParameterValues(String paramName){
		String[] values = getRequest().getParameterValues(paramName);
		if(values == null || values.length == 0)
			return values;
		
		for (int idx = 0, size = values.length; idx < size; idx++) {
			if(values[idx] != null)
				values[idx] = values[idx].trim();			
		}
		
		return values;
	}
	
	
	private boolean isValidIp(String ip){
		return StringUtils.isNotBlank(ip) 
				&& !"unknown".equalsIgnoreCase(ip);
	}

	public String getMacAddr() {
		return macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

}