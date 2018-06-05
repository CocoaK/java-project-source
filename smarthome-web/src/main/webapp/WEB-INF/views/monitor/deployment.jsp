<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="monitor" /></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/monitor/deployment.js'/>"></script>
</head>
<body>
<input type="hidden" name="deviceCode" value="<s:property value="deviceCode" />"/>
<div class="main_section">
        <div class="all_aside">
            <div class="all_menu">
                <a href="<s:url action="remoteUnlockInput"/>" class="all_menu_b"><s:text name="monitor.remoteUnlock" /></a>
                <a href="<s:url action="remoteApplianceControl"/>" class="all_menu_b"><s:text name="monitor.remoteApplianceControl" /></a>
                <a href="<s:url action="remoteApplianceSceneControl"/>"><s:text name="monitor.remoteApplianceControl.scene" /></a>
                <a href="#" class="all_menu_a"><s:text name="monitor.deployment" /></a>
            </div>
        </div>
        <div class="main_outside">
            <div class="all_inside">
                <table cellspacing="0" class="all_tab_bodyq">
                    <tbody>
                        <tr>
                            <td width="120"><s:text name="monitor.deployment.location" /></td>
                            <td>${house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.name} ${house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name} ${house.THmBuildingCellInfo.THmRegionBuildingInfo.name} ${house.THmBuildingCellInfo.name} ${house.name}</td>
                        </tr>
                        <tr>
                            <td><s:text name="monitor.remoteApplianceControl.currentScene" /></td> 
                            <td><span id="useScene"></span><input type="hidden" name="useSceneId" /></td>
                        </tr>
                        <tr>
                            <td><s:text name="monitor.deployment.sceneModelSelect" /></td> 
                            <td>
                                <div class="select_div">
                                    <select id="sceneList"></select>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <s:include value="/WEB-INF/views/monitor/include_monitor_layer.jsp"/>
            </div>
            <div class="all_tab_butb"><input type="button" id="submitControlConfirm" value="<s:text name="common.element.action.submit"/>" /></div>
        </div>
    </div>
</body>
</html>

