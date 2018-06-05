<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="M003503" /></title>
<link href="<s:url value='/mobile/css/layout.css'/>" rel="stylesheet" />
</head>

<body>
	<s:include value="/WEB-INF/views/mobile/head.jsp"/>
	
    <div class="ico_list">

    	<ul>

        	<li><a href="<s:url action="remoteUnlockInput"/>"><font size="200%"><s:text name="monitor.remoteUnlock" /></font></a></li>

            <li><a href="<s:url action="remoteApplianceControl"/>"><font size="200%"><s:text name="monitor.remoteApplianceControl" /></font></a></li>
            
            <li><a href="<s:url action="remoteSceneControl"/>"><font size="200%"><s:text name="monitor.deployment" /></font></a></li>
            
            <li><a href="<s:url action="remoteApplianceSceneControl"/>"><font size="200%"><s:text name="monitor.remoteApplianceControl.scene" /></font></a></li>

        </ul>

    </div>

</body>

</html>

