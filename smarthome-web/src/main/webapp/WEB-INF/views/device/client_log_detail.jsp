<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<title><s:text name="client.log.data.content"/></title>
<link href="<%=basePath%>css/layout.css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>js/jqueryLoader.js"></script>
        <script type="text/javascript" src="<%=basePath%>js/jquery_all.js"></script>
        <script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.min.js"></script>
</head>
  
  <body>
	<div class="main_section">
    	<div class="all_aside">
			<div class="all_menu">
 				<a href="<%=path %>/push/pushAction_queryByDeviceNo.action?pushVO.pushClientId=${deviceNumber}&pushVO.pushKind=all&deviceNumber=${deviceNumber}&macAddr=${macAddr}"><s:text name="datapush.unpush"/></a>
				<a href="<%=path %>/push/pushFinishAction_listByDeviceNo.action?pushFinishVO.pushClientId=${deviceNumber}&pushFinishVO.pushKind=all&deviceNumber=${deviceNumber}&macAddr=${macAddr}" ><s:text name="datapush.pushed"/></a>
				<a href="<%=path %>/log/regeditLogList.action?diviceRegeditLog.macAddr=${macAddr}&deviceNumber=${deviceNumber}&macAddr=${macAddr}" ><s:text name="deviceregedit.log.management.list"/></a>
				<a href="<%=path %>/log/clientLogs.action?clientLog.deviceMac=${macAddr}&deviceNumber=${deviceNumber}&macAddr=${macAddr}" ><s:text name="client.log.list" /></a>
                <a href="#" class="all_menu_a"><s:text name="client.log.data.content.menu"/></a>
				<a href="<%=path %>/log/appDataLogs.action?appDataLog.deviceNo=${deviceNumber}&deviceNumber=${deviceNumber}&macAddr=${macAddr}" ><s:text name="appdata.log.management.list"/></a>
			</div>
		</div>
        <div class="main_outside">
            <div class="all_inside">
                <table cellspacing="0" class="all_tab_body all_tab_bodys all_tab_font">
                    <tbody>
                        <tr>
                            <td width="120"><s:text name="client.log.data.content"/>:</td>
                            <td class="all_tab_fonthack"><s:property value="clientLog.dataContent"/></td>
                        </tr>
                       
                       
                    </tbody>
                </table>
                
            </div>
        </div>
    </div>
</body>
</html>
