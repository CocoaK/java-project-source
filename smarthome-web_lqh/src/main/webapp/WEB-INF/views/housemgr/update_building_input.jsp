<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="housemgr.building" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/map.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/housemgr.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/updateBuilding.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
    </head>

    <body>
        <form action="<s:url action="updateBuilding"/>" method="post">
        <input type="hidden" id="buildingId" name="building.id" value="<s:property value='building.id'/>"/>
        <input type="hidden" id="regionId" name="building.THmHousingDistrictRegionInfo.id" value="<s:property value='building.THmHousingDistrictRegionInfo.id'/>"/>
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
                    <a href="#" class="all_menu_a">小区楼栋修改</a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="housemgr.district.list.name"/></td>
                                <td><s:property value="building.THmHousingDistrictRegionInfo.housingDistrictInfo.name"/></td>
                            </tr>
                            <tr>
                                <td width="120"><s:text name="housemgr.region.list.name"/></td>
                                <td><s:property value="building.THmHousingDistrictRegionInfo.name"/></td>
                            </tr>
                            <tr>
                                <td width="120"><dfn></dfn><s:text name="housemgr.building.list.code"/></td>
                                <td>
                                    <input type="text" size="40" name="building.code" value="<s:property value="building.code"/>" maxlength="2" class="{required: true, digits: true}" onkeyup="hideExistMsg(this);"/>
                                    <label class="error exist" style="display: none"><s:text name="housemgr.building.generate.codeRepeat"/></label>
                                </td>
                            </tr>
                            <tr>
                                <td width="120"><dfn></dfn><s:text name="housemgr.building.list.name"/></td>
                                <td>
                                    <input type="text" size="40" name="building.name" value="<s:property value="building.name"/>" maxlength="10" class="{required:true, maxlength: 10}" onkeyup="hideExistMsg(this);"/>
                                    <label class="error exist" style="display: none"><s:text name="housemgr.building.name.exist"/></label>
                                </td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="housemgr.building.list.coordinate"/></td>
                                <td class="harea_tab_body">
                                    <ol><li>
                                        <input type="hidden" id="coordinateValue" name="building.coordinate" value="<s:property value='building.coordinate'/>"/>
                                        <input type="text" size="10" id="coordinateText" value="<s:property value="building.coordinate"/>" disabled="disabled" />
                                        <a href="javascript:void(0)" class="notetitle all_hover_but"><s:text name="housemgr.update.location"/></a>
                                    </li></ol>
                                </td>
                            </tr>
                            
                            <tr>
                                <td><s:text name="housemgr.building.list.coverArea"/></td>
                                <td class="up_imgs">
                                    <p>
                                        <input type="text" size="40" name="building.coverArea" maxlength="13" value="<s:property value="building.coverArea"/>" class="{num:[1,12,2]}"/>
                                    </p>
                                    <p>
                                        <i><s:text name='common.title.number.msg'><s:param>10</s:param><s:param>2</s:param></s:text></i>
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="housemgr.building.list.constructionArea"/></td>
                                <td class="up_imgs">
                                    <p>
                                        <input type="text" size="40" name="building.constructionArea" maxlength="13" value="<s:property value="building.constructionArea"/>" class="{num:[1,12,2]}" />
                                    </p>
                                    <p>
                                        <i><s:text name='common.title.number.msg'><s:param>10</s:param><s:param>2</s:param></s:text></i>
                                    </p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="submit" value="<s:text name='common.element.action.confirm.update' />" /></div>
                    <div id="list_click" style="display:none;" class="mapxy">
                        <div class="mapxyshow">
                            <div id="map">
                                <img src="${fileServerUrl}<s:property value="building.THmHousingDistrictRegionInfo.housingDistrictInfo.floorPlan" />" />
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
