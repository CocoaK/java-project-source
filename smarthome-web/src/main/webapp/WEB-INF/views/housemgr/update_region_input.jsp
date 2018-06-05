<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="housemgr.region" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/map.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/housemgr.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/updateRegion.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
    </head>

    <body>
        <form action="<s:url action="updateRegion"/>" method="post">
        <input type="hidden" id="regionId" name="region.id" value="<s:property value='region.id'/>"/>
        <input type="hidden" id="districtId" name="region.housingDistrictInfo.id" value="<s:property value='region.housingDistrictInfo.id'/>"/>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action="queryRegionList"/>"><s:text name="housemgr.region.list"/></a>
                    <a href="#" class="all_menu_a"><s:text name="housemgr.region.update"/></a>
                    <a href="queryBuildingList" class="all_menu_b"><s:text name="housemgr.building.list"/></a>
                    <a href="queryCellList" class="all_menu_b"><s:text name="housemgr.cell.list"/></a>
                    <a href="queryHouseholdList" class="all_menu_b"><s:text name="housemgr.house.list"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="housemgr.district.list.name"/></td>
                                <td><input type="hidden" name="region.housingDistrictInfo.name" value="<s:property value='region.housingDistrictInfo.name'/>"/><s:property value="region.housingDistrictInfo.name"/></td>
                            </tr>
                            <tr>
                                <td width="120"><dfn></dfn><s:text name="housemgr.region.list.code"/></td>
                                <td>
                                    <input type="text" size="40" name="region.code" value="<s:property value="region.code"/>" maxlength="2" class="{required: true, digits: true}" onkeyup="hideExistMsg(this);" />
                                    <label class="error exist" style="display: none"><s:text name="housemgr.region.generate.codeRepeat"/></label>
                                </td>
                            </tr>
                            <tr>
                                <td width="120"><dfn></dfn><s:text name="housemgr.region.list.name"/></td>
                                <td>
                                    <input type="text" size="40" name="region.name" value="<s:property value="region.name"/>" maxlength="10" class="{required:true, maxlength: 10}" onkeyup="hideExistMsg(this);" />
                                    <label class="error exist" style="display: none"><s:text name="housemgr.region.name.exist"/></label>
                                </td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="housemgr.region.list.coordinate"/></td>
                                <td class="harea_tab_body">
                                    <ol><li>
                                        <input type="hidden" id="coordinateValue" name="region.coordinate" value="<s:property value='region.coordinate'/>"/>
                                        <input type="text" size="10" id="coordinateText" value="<s:property value="region.coordinate"/>" disabled="disabled" />
                                        <a href="javascript:void(0)" class="notetitle all_hover_but"><s:text name="housemgr.update.location"/></a>
                                    </li></ol>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="submit" value="<s:text name='common.element.action.confirm.update' />" /></div>
	                <div id="list_click" style="display:none;" class="mapxy">
	                    <div class="mapxyshow">
	                        <div id="map">
                                <img src="${fileServerUrl}/<s:property value="region.housingDistrictInfo.floorPlan" />" />
                                <span id="spShow" /></span>
                                <input type="hidden" id="switch" value="1"/>
	                        </div>
	                        <div class="all_tab_butb">
		                        <a href="#" class="all_hover_but"><s:text name="housemgr.update.location"/></a>
		                        <a href="#" class="closediv all_hover_but"><s:text name="common.element.action.close"/></a>
	                        </div>
	                    </div>
	                </div>
                    <s:include value="/WEB-INF/views/housemgr/include_result_msg.jsp"/>                   
                </div>
            </div>
        </div>
        </form>
    </body>
</html>
