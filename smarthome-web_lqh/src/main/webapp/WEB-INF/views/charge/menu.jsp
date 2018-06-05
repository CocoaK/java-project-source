<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@page import="com.biencloud.smarthome.web.login.vo.LoginVO"%>
<%@page import="com.biencloud.smarthome.web.base.action.BaseAction"%>
<%@page import="com.biencloud.smarthome.web.common.action.ActionUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//LoginVO vo=(LoginVO)session.getAttribute(Constants.KEY_LOGIN_SESSION);
//String userType=vo.getUserType();
String userType=(String)request.getAttribute("userType");
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
        <%--             <% if(Constants.LOGIN_USER_TYPE_SYSTEM.equals(userType)){ %>
                      	<a href="<%=path%>/charge/queryChargeMonetaryUnitList.action" class="all_menu_a"><s:text name="chargemonetaryunit.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeMonetaryUnit.action"><s:text name="chargemonetaryunit.add"/></a>
                        <a href="<%=path%>/charge/queryChargeCalModeList.action"><s:text name="chargecalmode.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeCalMode.action"><s:text name="chargecalmode.add"/></a>
                      <%} %> --%>  
                      <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
                        <a href="<%=path%>/charge/queryChargeTypeList.action"><s:text name="chargetype.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeType.action"><s:text name="chargetype.add"/></a>
                        <a href="<%=path%>/charge/queryChargeDataList.action"><s:text name="chargedata.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeData.action"><s:text name="chargedata.add"/></a>
                        <%} %>
                        <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)||Constants.LOGIN_USER_TYPE_OWNER.equals(userType)){ %>
                        <a href="<%=path%>/charge/queryChargeDetailList.action"><s:text name="chargedetail.management"/></a>
                         <%} %>
                         <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
                        <a href="<%=path%>/charge/statisticsCharge.action"><s:text name="chargestatistics.management.list"/></a>
                      <%} %>
                      
