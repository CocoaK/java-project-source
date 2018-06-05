<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="infosend.management"/></title>
<link href="<s:url value='/mobile/css/layout.css'/>" rel="stylesheet" />
</head>

<body>
	<s:include value="/WEB-INF/views/mobile/head.jsp"/>
    <div class="list_top"><s:property value='infoSend.title'/></div>
    <div class="search search2"><s:text name="infosend.sendtime" />：
	<s:if test="infoSend.sendTime==null">
            		</s:if>
            		<s:else>
            		<s:date name="infoSend.sendTime" format="yyyy-MM-dd HH:mm:ss" />
            		</s:else>
	</div>
    <div class="content"><s:property value="infoSend.content"/></div>
    <div class="page">
        <a href="javascript:history.back(-1);" class="right_page"><s:text name="common.element.action.return" /></a>
    </div>
</body>
</html>
