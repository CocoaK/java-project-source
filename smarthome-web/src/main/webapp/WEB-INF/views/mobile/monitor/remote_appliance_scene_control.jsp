<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="monitor.remoteApplianceControl.scene" /></title>
<link href="<s:url value='/mobile/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/mobile/js/monitor/remoteApplianceControl.js'/>"></script>
</head>
<body onload="loadScene()">
	<s:include value="/WEB-INF/views/mobile/head.jsp"/>
	<div class="list_top"><s:text name="monitor.remoteApplianceControl.scene" /></div>
		<input type="hidden" name="deviceCode" value="<s:property value="deviceCode" />"/>
                <table cellspacing="0" class="list_tab list_tab_cont">
                    <tbody>
                        <tr>
                            <td class="tab_left"><s:text name="monitor.deployment.location" /></td>
                            <td>${house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.name} ${house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name} ${house.THmBuildingCellInfo.THmRegionBuildingInfo.name} ${house.THmBuildingCellInfo.name} ${house.name}</td>
                        </tr>
                        <tr>
                            <td class="tab_left"><s:text name="monitor.remoteApplianceControl.currentScene" /></td> 
                            <td>
                            	<i id="useScene"></i>
                            	<input type="hidden" name="scene.sceneId" value="<s:property value="scene.sceneId" />"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="tab_left"><s:text name="monitor.deployment.sceneModelSelect" /></td> 
                            <td>
                                <div class="select_div">
                                    <select id="sceneList"></select>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            <div class="submit">
            	<a href="#" onclick="doSceneControl()"><s:text name="common.element.action.submit"/></a>
            	<a href="<s:url action='remoteIndex' namespace='/mobile/monitor'/>" ><s:text name="common.element.action.return" /></a>
            </div>
    		<s:if test="successFlag == true" >
	       		<div id="msgFrame" class="layer">
	       			<div><s:text name="common.element.action.operationSuccess"/><a href="<s:url action="remoteSceneControl"/>" onclick="javascript:hideTip('msgFrame');"><s:text name="common.element.action.close" /></a></div>
	       		</div>
       		</s:if>
       	<s:include value="/WEB-INF/views/mobile/monitor/include_monitor_layer.jsp"/>
</body>
</html>

