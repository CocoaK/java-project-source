<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="housemgr.region"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/map.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/housemgr.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/region.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
    </head>

    <body>
        <form action="<s:url action="generateRegions"/>" method="post">
        <input type="hidden" id="districtId" name="districtId" value="<s:property value='districtId'/>"/>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <%-- <a href="queryHouseholdList" class="all_menu_b"><s:text name="housemgr.house.list"/></a>
                    <a href="<s:url action="queryRegionList"/>"><s:text name="housemgr.region.list"/></a>
                    <a href="queryBuildingList" class="all_menu_b"><s:text name="housemgr.building.list"/></a>
                    <a href="queryCellList" class="all_menu_b"><s:text name="housemgr.cell.list"/></a> --%>
                    <a href="queryHouseholdList" class="all_menu_b">小区房号列表</a>
                    <a href="<s:url action="queryRegionList"/>">小区区域列表</a>
                    <a href="queryBuildingList" class="all_menu_b">小区楼栋列表</a>
                    <a href="queryCellList" class="all_menu_b">小区单元列表</a>
                    <a href="#" class="all_menu_a"><s:text name="housemgr.region.generate"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="housemgr.district.list.name"/></td>
                                <td><input type="hidden" name="districtName" value="<s:property value='districtName'/>"/><s:property value='districtName'/></td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="housemgr.region.generate.quantity"/></td>
                                <td><input id="generateCount" type="text" size="10" value="2" maxlength="2" class="{required:true, digits: true, min: 1}" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="housemgr.region.generate"/></td>
                                <td class="harea_tab_body">
                                    <ol id="newRegionList">
                                        <li>
                                            <label><dfn></dfn><s:text name="housemgr.region.list.code"/></label>
                                            <input name="regionCodes" type="text" size="10" value="01" maxlength="2" class="{required: true, digits: true}" onkeyup="markUniqueEle('regionCodes');hideExistMsg(this);"/>
                                            <label class="error exist" style="display: none"><s:text name="housemgr.region.generate.codeRepeat"/></label>
                                            <label><dfn></dfn><s:text name="housemgr.region.list.name"/></label>
                                            <input name="regionNames" type="text" size="10" maxlength="10" value="A" class="{required:true, maxlength: 10}" onkeyup="markUniqueEle('regionNames');hideExistMsg(this);"/>
                                            <label class="error exist" style="display: none"><s:text name="housemgr.region.name.exist"/></label>
                                            <label><!-- <dfn></dfn> --><s:text name="housemgr.region.list.coordinate"/></label>
		                                    <input type="hidden" name="coordinates" value=""/>
		                                    <input type="text" size="10" name="coordinatesText" value="0,0" disabled="disabled" onselect="validateCoordinate(this);"/>
		                                    <label class="error" style="display: none"><s:text name="housemgr.region.generate.coordinates"/></label>
                                            <a href="javascript:void(0)" class="notetitle all_hover_but"><s:text name="housemgr.update.location"/></a>
                                        </li>
                                        <li>
                                            <label><dfn></dfn><s:text name="housemgr.region.list.code"/></label>
                                            <input name="regionCodes" type="text" size="10" value="02" maxlength="2" class="{required: true, digits: true}" onkeyup="markUniqueEle('regionCodes');hideExistMsg(this);"/>
                                            <label class="error exist" style="display: none"><s:text name="housemgr.region.generate.codeRepeat"/></label>
                                            <label><dfn></dfn><s:text name="housemgr.region.list.name"/></label>
                                            <input name="regionNames" type="text" size="10" maxlength="10" value="B" class="{required:true, maxlength: 10}" onkeyup="markUniqueEle('regionNames');hideExistMsg(this);"/>
                                            <label class="error exist" style="display: none"><s:text name="housemgr.region.name.exist"/></label>
                                            <label><!-- <dfn></dfn> --><s:text name="housemgr.region.list.coordinate"/></label>
                                            <input type="hidden" name="coordinates" value=""/>
                                            <input type="text" size="10" name="coordinatesText" value="0,0" disabled="disabled" onselect="validateCoordinate(this);"/>
                                            <label class="error" style="display: none"><s:text name="housemgr.region.generate.coordinates"/></label>
                                            <a href="javascript:void(0)" class="notetitle all_hover_but"><s:text name="housemgr.update.location"/></a>
                                        </li>
                                    </ol>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="submit" value="<s:text name='common.element.action.add' />" /></div>
                    <div id="list_click" style="display:none;" class="mapxy">
                        <div class="mapxyshow">
                            <div id="map">
                                <img src="${fileServerUrl}<s:property value="districtFloorPlan" />" />
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
