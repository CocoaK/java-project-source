<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% 
String path=request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="monitor" /></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/dhtmlxcommon.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/dhtmlxtree.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/ext/dhtmlxtree_json.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/monitor/remoteApplianceControl.js'/>"></script>
</head>

<body onload="init()">
    <input type="hidden" name="houseId" value="<s:property value="houseId" />"/>
    <input type="hidden" name="deviceCode" value="<s:property value="deviceCode" />"/>
    <input type="hidden" id="deviceStatusOn" value="<s:text name="monitor.remoteApplianceControl.on" />"/>
    <input type="hidden" id="deviceStatusOff" value="<s:text name="monitor.remoteApplianceControl.off" />"/>
    <div class="main_section">
        <div class="all_aside">
            <div class="all_menu">
                <a href="<s:url action="remoteUnlockInput"/>" class="all_menu_b"><s:text name="monitor.remoteUnlock" /></a>
                <a href="#" class="all_menu_a"><s:text name="monitor.remoteApplianceControl" /></a>
                <a href="<s:url action="remoteApplianceSceneControl"/>"><s:text name="monitor.remoteApplianceControl.scene" /></a>
                <a href="<s:url action="deployment"/>" class="all_menu_b"><s:text name="monitor.deployment" /></a>
            </div>
        </div>
        <div class="main_outside">
            <div class="all_inside">
                <p class="dtree_top"><strong><s:text name="monitor.remoteApplianceControl" /></strong></p>
			    <div id="div_tree" style="padding:60px 0 0 20px;"></div>
			    <script type="text/javascript">
				var treeData = {
				    id:0,
				    item:[{
				        id:0.1, text: "${house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.name} ${house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name} ${house.THmBuildingCellInfo.THmRegionBuildingInfo.name} ${house.THmBuildingCellInfo.name} ${house.name}"
				    }]
				};
				var tree = new dhtmlXTreeObject("div_tree", "30%", "100%", 0);
				tree.setImagePath("<%=path%>/images/imgsTree/csh_vista/");
				tree.enableCheckBoxes(true);
				tree.enableThreeStateCheckboxes(true);
				tree.loadJSONObject(treeData);
				tree.openAllItems();

                tree.setOnCheckHandler(function() {
                    getCheckedDevice(tree);
                });
			    </script>
			    
                <div class="dtree_right all_lra">
                    <table cellspacing="0" class="all_tab_body">
                        <thead class="all_tab_thead">
                            <tr>
	                            <th><s:text name="monitor.remoteApplianceControl.name"/></th>
	                            <th><s:text name="monitor.remoteApplianceControl.status"/></th>
                            </tr>
                        </thead>
                        <tbody id="applianceTb"></tbody>
                    </table>
                    <div class="all_tab_butb">
                    	<input type="button" disabled="disabled" id="submitControlConfirm" value="<s:text name="monitor.remoteApplianceControl.device.submit"/>" />
                    </div>
                </div>
                <s:include value="/WEB-INF/views/monitor/include_monitor_layer.jsp"/>
            </div>
        </div>
    </div>
</body>
</html>

