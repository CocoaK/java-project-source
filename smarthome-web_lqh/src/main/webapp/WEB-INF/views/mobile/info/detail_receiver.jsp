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
    <div class="list_top"><s:property value='infoReceiver.infoSend.title'/></div>
    <div class="search search2">
    <s:text name="infosend.sendperson" />：<s:property value="infoReceiver.infoSend.sendUserName"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <s:text name="infoReceiver.infosend.sendtime" />：
	<s:if test="infoReceiver.infoSend.sendTime==null">
            		<s:text name="infosend.nosend" />
            		</s:if>
            		<s:else>
            		<s:date name="infoReceiver.infoSend.sendTime" format="yyyy-MM-dd HH:mm:ss" />
            		</s:else>
	</div>
    <div class="content"><s:property value="infoReceiver.infoSend.content"/></div>
    <div class="page">
        <a href="javascript:history.back(-1);" class="right_page"><s:text name="common.element.action.return" /></a>
    </div>
</body>
</html>
