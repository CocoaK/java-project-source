<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="head">
    <a href="<s:url action='index' namespace='/mobile/login'/>" class="head_left"><s:text name="head.mobile.action.home"/></a>    
    <img src="<s:url value='/mobile/images/logo.png'/>" />  
    <a href="<s:url action='logout' namespace='/mobile/login'/>" class="head_right"><s:text name="head.page.exit"/></a> 
</div>