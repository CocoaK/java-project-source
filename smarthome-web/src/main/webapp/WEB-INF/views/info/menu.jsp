<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@page import="com.biencloud.smarthome.web.login.vo.LoginVO"%>
<%@page import="com.biencloud.smarthome.web.info.vo.InfoReceiverVO"%>
<%@page import="com.biencloud.smarthome.web.common.action.ActionUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
LoginVO vo=(LoginVO)session.getAttribute(Constants.KEY_LOGIN_SESSION);
String userType=vo.getUserType();
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'menu.jsp' starting page</title>

  </head>
  
  <body>
    					<a href="<%=path%>/info/queryInfoSendList.action" class="all_menu_a"><s:text name="infosend.management.list"/></a>
                        <a href="<%=path%>/info/goToInputInfoSend.action"><s:text name="infosend.add"/></a>
                        <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)||Constants.LOGIN_USER_TYPE_OWNER.equals(userType)){ %>
                    <a href="<%=path%>/info/queryInfoReceiverList.action"><s:text name="inforeceiver.management.list"/></a>   
                    <%} %>
  </body>
</html>
