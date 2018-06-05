<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title><s:text name='error.sys.title'/></title>
        <link href="<s:url value='/mobile/css/layout.css'/>" rel="stylesheet" />
    </head>
    
    <body>
        <s:include value="/WEB-INF/views/mobile/head.jsp"/>
        
        <div class="list_top"><s:text name='error.info.title'/></div>
        <div class="error"><s:text name='error.sys.unexpected'/></div>
    </body>
</html>