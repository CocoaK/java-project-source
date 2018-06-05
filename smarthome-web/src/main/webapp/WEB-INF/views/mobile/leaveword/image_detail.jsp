<%@ page language="java"
	import="java.util.*,com.biencloud.smarthome.web.common.vo.*"
	pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="<s:url value='/mobile/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>

</head>

<body>
	<s:include value="/WEB-INF/views/mobile/head.jsp"/>
	<div class="list_top"><s:text name="leave.word.view"/></div>
    <div class="video"><img src="<s:property value="imageUrl" />" /></div>
    <div class="submit">
        <a href="javascript:history.back();"><s:text name="leave.word.back"/></a>
    </div>
	
</body>
</html>
