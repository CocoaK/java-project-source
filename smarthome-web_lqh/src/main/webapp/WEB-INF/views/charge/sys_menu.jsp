<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@page import="com.biencloud.smarthome.web.login.vo.LoginVO"%>
<%@page import="com.biencloud.smarthome.web.base.action.BaseAction"%>
<%@page import="com.biencloud.smarthome.web.common.action.ActionUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
LoginVO vo=(LoginVO)session.getAttribute(Constants.KEY_LOGIN_SESSION);
String userType=vo.getUserType();
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
                      	<a href="<%=path%>/charge/queryChargeMonetaryUnitList.action"><s:text name="chargemonetaryunit.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeMonetaryUnit.action"><s:text name="chargemonetaryunit.add"/></a>
                        <a href="<%=path%>/charge/queryChargeCalModeList.action"><s:text name="chargecalmode.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeCalMode.action"><s:text name="chargecalmode.add"/></a>
                      
