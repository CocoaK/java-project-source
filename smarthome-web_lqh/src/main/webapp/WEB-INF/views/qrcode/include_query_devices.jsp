<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="list_click" style="display:none;" class="layers layerw">
    <div>
        <p>
            <label><s:text name="gatecard.areaname" /></label>
            <input type="text" size="12" maxlength="10" id="areaName" name="device.housingDistrictRegionInfo.name" value="<s:property value='#parameters["device.housingDistrictRegionInfo.name"]'/>" />
            <label><s:text name="gatecard.buildingname" /></label>
            <input type="text" size="12" maxlength="10" id="buildingName" name="device.regionBuildingInfo.name" value="<s:property value='#parameters["device.buildingName"]'/>" />
            <label><s:text name="gatecard.unitname" /></label>
            <input type="text" size="12" maxlength="10" id="unitName" name="device.buildingCellInfo.name" value="<s:property value='#parameters["device.buildingCellInfo.name"]'/>" />
            <input type="button" onclick="queryDevices()" value="<s:text name='common.element.action.search' />" class="all_hover_but layerspbt" />
        </p>

        <ul class="layers_search_list layers_padding">
            <li>
                <table cellspacing="0" class="all_tab_body">
                    <thead>
                        <tr>
                            <th width="28"><s:text name="common.element.action.select" /></th>
                            <th><s:text name="gatecard.device.name" /></th>
                            <th width="20%"><s:text name="gatecard.device.type" /></th>
                            <th width="15%"><s:text name="common.title.areaname" /></th>
                            <th width="15%"><s:text name="common.title.building" /></th>
                            <th width="15%"><s:text name="common.title.unitname" /></th>
                        </tr>
                    </thead>
                    <tbody id="deviceResult"></tbody>
                </table>
            </li>
        </ul>

        <a href="javascript:appendDevices();" class="all_hover_but"><s:text name='common.element.action.confirm' /></a>
        <a href="javascript:clearDeviceResult();" class="closediv all_hover_but"><s:text name='common.element.action.cancel' /></a>
    </div>
</div>