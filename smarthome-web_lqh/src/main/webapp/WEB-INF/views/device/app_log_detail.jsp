<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
        <title><s:text name="appdata.log.management"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<%=path %>/push/pushAction_queryByDeviceNo.action?pushVO.pushClientId=${deviceNumber}&pushVO.pushKind=all&deviceNumber=${deviceNumber}&macAddr=${macAddr}"><s:text name="datapush.unpush"/></a>
					<a href="<%=path %>/push/pushFinishAction_listByDeviceNo.action?pushFinishVO.pushClientId=${deviceNumber}&pushFinishVO.pushKind=all&deviceNumber=${deviceNumber}&macAddr=${macAddr}" ><s:text name="datapush.pushed"/></a>
					<a href="<%=path %>/log/regeditLogList.action?diviceRegeditLog.macAddr=${macAddr}&deviceNumber=${deviceNumber}&macAddr=${macAddr}" ><s:text name="deviceregedit.log.management.list"/></a>
					<a href="<%=path %>/log/clientLogs.action?clientLog.deviceMac=${macAddr}&deviceNumber=${deviceNumber}&macAddr=${macAddr}" ><s:text name="client.log.list" /></a>
                    <a href="<%=path %>/log/appDataLogs.action?appDataLog.deviceNo=${deviceNumber}&deviceNumber=${deviceNumber}&macAddr=${macAddr}"><s:text name="appdata.log.management.list"/></a>
                    <a href="#" class="all_menu_a"><s:text name="appdata.log.management.detail"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="client.log.deviceno"/></td>
                                <td><s:property value="appDataLog.deviceNo"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="client.log.devicename"/></td>
                                <td><s:property value="appDataLog.deviceName"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="deviceregedit.log.hourseno"/></td>
                                <td><s:property value="appDataLog.fullRoomNo"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="appdata.log.position"/></td>
                                <td><s:property value="appDataLog.position"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="client.log.ip"/></td>
                                <td><s:property value="appDataLog.ip"/></td>
                            </tr>                            
                            <tr>
                                <td><s:text name="client.log.mac"/></td>
                                <td><s:property value="appDataLog.mac"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="appdata.log.action"/></td>
                                <td><s:property value="appDataLog.action"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="appdata.log.requestdata"/></td>
                                <td><s:property value="appDataLog.requestData"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="appdata.log.responsedata"/></td>
                                <td><s:property value="appDataLog.responseData"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="appdata.log.requesttime"/></td>
                                <td><s:date name="appDataLog.requestTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="client.log.result"/></td>
                                <td>
                                	<s:if test="appDataLog.result==1"><s:text name="operation.success"/></s:if>
                                    <s:if test="appDataLog.result==2"><s:text name="operation.fail"/></s:if>
                                    <s:if test="appDataLog.result==3"><s:text name="operation.error"/></s:if>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="fileupload.log.exception"/></td>
                                <td><s:property value="appDataLog.resultDesc"/></td>
                            </tr>

                        </tbody>
                    </table>                   
                </div>
            </div>
        </div>
        <script>
    		QueryLoader.selectorPreload = "body";
    		QueryLoader.init();
    	</script>
    </body>
</html>
