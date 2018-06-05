<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title><s:text name='M003'/></title>
        <link href="<s:url value='/mobile/css/layout.css'/>" rel="stylesheet" />
    </head>
    
    <body>
        <div class="head">   
            <img src="<s:url value='/mobile/images/logo.png'/>" />  
            <a href="<s:url action='logout' namespace='/mobile/login'/>" class="head_right"><s:text name="head.page.exit"/></a> 
        </div>
        
        <div class="ico_list">
            <a href="<s:url action='updatePassInput' namespace='/mobile/login'/>">
                <img src="<s:url value='/mobile/images/ico_01.png'/>" />
                <span><s:text name="M003501"/></span>
            </a>
            <a href="<s:url action='wordList' namespace='/mobile/leaveWord'/>">  
                <img src="<s:url value='/mobile/images/ico_02.png'/>" />  
                <span><s:text name="M003502"/></span>   
            </a>
            <a href="<s:url action='remoteIndex' namespace='/mobile/monitor'/>">  
                <img src="<s:url value='/mobile/images/ico_03.png'/>" />   
                <span><s:text name="M003503"/></span>   
            </a>    
            <a href="<s:url action='queryChargeList' namespace='/mobile/charge'/>">    
                <img src="<s:url value='/mobile/images/ico_04.png'/>" />   
                <span><s:text name="M003504"/></span>
            </a>   
            <a href="<s:url action='alarmList' namespace='/mobile/alarm'/>">    
                <img src="<s:url value='/mobile/images/ico_05.png'/>" />    
                <span><s:text name="M003505"/></span>    
            </a>    
            <a href="<s:url action='queryInfoSendList' namespace='/mobile/info'/>">   
                <img src="<s:url value='/mobile/images/ico_06.png'/>" />    
                <span><s:text name="M003506"/></span>   
            </a>    
        </div>       
    </body>
</html>