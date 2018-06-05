<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="device.management.detail"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                	<a href="<s:url action="deviceList"/>" ><s:text name="device.management.list"/></a>
                    <a href="#" class="all_menu_a"><s:text name="device.management.detail"/></a>
                    <s:if test="userType==02">
                        <a href="ipList.action"><s:text name="device.ip.list"/></a>
                        <a href="<s:url action="messageList"/>"><s:text name="device.message.list"/></a>
                    </s:if>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="device.devicecode"/></td>
                                <td colspan="3"><s:property value="device.deviceCode"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="device.devicename"/></td>
                                <td colspan="3"><s:property value="device.deviceName"/></td>
                            </tr>
                            <tr>
                            	<td><s:text name="device.devicealias"/></td>
                                <td colspan="3"><s:property value="device.deviceAlias" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="device.devicetype"/></td>
                                <td colspan="3"><s:text name="%{device.deviceType.deviceName}"/></td>
                            </tr>
                            <s:if test="userType!=03"><!-- userType:01业主，02物管，03系统管理员 -->
	                            <tr>
	                            	<td width="120"><s:text name="device.message.list"/></td>
	                                <td colspan="3">
	                                	<a href="<s:url action="messageList"><s:param name="callRecordVO.device.deviceId" value="device.deviceId"/></s:url>" class="all_hover_but"><s:text name="common.element.action.show"/></a>
	                                </td>
	                            </tr>
                            </s:if>
                            <tr>
                                <td><s:text name="device.districtname"/></td>
                                <td colspan="3"><s:property value="device.housingDistrictInfo.name"/></td>
                            </tr>                            
                            <tr>
                                <td><s:text name="device.areaname"/></td>
                                <td width="30%"><s:property value="device.housingDistrictRegionInfo.name"/></td>
                                <td width="120"><s:text name="device.buildingname"/></td>
                                <td><s:property value="device.regionBuildingInfo.name"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="device.unitname"/></td>
                                <td><s:property value="device.buildingCellInfo.name" /></td>
                                <td><s:text name="device.roomname"/></td>
                                <td><s:property value="device.cellHouseholdInfo.name" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="device.position"/></td>
                                <td><s:property value="device.position" /></td>
                                <td><s:text name="device.devicestatus"/></td>
                                <td>
                                	<s:if test="device.deviceStatus==1">
                                        <s:text name="device.devicestatus.online"/>
                                    </s:if>
                                    <s:if test="device.deviceStatus==0">
                                        <s:text name="device.devicestatus.offline"/>
                                    </s:if>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="device.device.ip"/></td>
                                <td><s:property value="device.deviceIp" /></td>
                                <td><s:text name="device.devicemac"/></td>
                                <td><s:property value="device.deviceMac" /></td>

                            </tr>
                            <tr>
                                <td><s:text name="device.createdtime"/></td>
                                <td><s:date name="device.createdTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                <td><s:text name="device.updatedtime"/></td>
                                <td><s:date name="device.updatedTime" format="yyyy-MM-dd HH:mm:ss"/></td>
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
