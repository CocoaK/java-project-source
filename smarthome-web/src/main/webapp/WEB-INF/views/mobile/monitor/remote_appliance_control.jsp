<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="monitor.remoteApplianceControl" /></title>
<link href="<s:url value='/mobile/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/mobile/js/monitor/remoteApplianceControl.js'/>"></script>
</head>

<body onload="init()">
	<s:include value="/WEB-INF/views/mobile/head.jsp"/>
	<div class="list_top"><s:text name="monitor.remoteApplianceControl" /></div>
	<input type="hidden" name="houseId"	value="<s:property value="houseId" />" />
	<input type="hidden" name="deviceCode" value="<s:property value="deviceCode" />" />
	<input type="hidden" id="deviceStatusOn" value="<s:text name="monitor.remoteApplianceControl.on" />" />
	<input type="hidden" id="deviceStatusOff" value="<s:text name="monitor.remoteApplianceControl.off" />" />
		<table cellspacing="0" class="list_tab">
			<thead class="all_tab_thead">
				<tr>
					<th width="50%"><s:text name="monitor.remoteApplianceControl.name" />
					</th>
					<th><s:text name="monitor.remoteApplianceControl.status" />
					</th>
				</tr>
			</thead>
			<tbody id="applianceTb"></tbody>
		</table>
		<div class="submit">
			<input type="button" disabled="disabled" id="submitControlConfirm" value="<s:text name="monitor.remoteApplianceControl.device.submit"/>" />
			<input type="button" value="<s:text name="common.element.action.return"/>" onclick="javascript:history.back(-1);"/>
		</div>
	<s:include value="/WEB-INF/views/mobile/monitor/include_monitor_layer.jsp"/>
</body>
</html>

