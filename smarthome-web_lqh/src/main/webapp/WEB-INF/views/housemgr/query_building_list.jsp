<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="housemgr.building"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.parseQuerystring.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/districtList.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/regionList.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/location.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/housemgr.js'/>"></script>
    </head>

    <body>
        <form action="<s:url action="queryBuildingList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                    	<!-- <a href="queryHouseholdList" class="all_menu_b"><s:text name="housemgr.house.list"/></a>
                        <a href="queryRegionList" class="all_menu_b"><s:text name="housemgr.region.list"/></a> -->
                        <a href="queryHouseholdList" class="all_menu_b">小区房号列表</a>
                        <a href="queryRegionList" class="all_menu_b">小区区域列表</a>
                        <!-- <a href="#" class="all_menu_a"><s:text name="housemgr.building.list"/></a>
                        <a href="queryCellList" class="all_menu_b"><s:text name="housemgr.cell.list"/></a> -->
                        <a href="#" class="all_menu_a">小区楼栋列表</a>
                        <a href="queryCellList" class="all_menu_b">小区单元列表</a>
                        <a href="queryHouseholdList?print"><s:text name="hoursemgr.common.printsitenumber"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                            <label class="all_border_right" for="districtId"><s:text name="housemgr.region.list.search.district"/></label>
                            <div class="select_div">
                                <select id="districtId" name="districtId"></select>
                            </div>
                            <label class="all_border_right" for="regionId"><s:text name="housemgr.region.list.search.regionName"/></label>
                            <div class="select_div">
                                <select id="regionId" name="regionId" regionId="<s:property value='regionId'/>"></select>
                            </div>
                            <label class="all_border_right" for="buildingName"><s:text name="housemgr.building.list.search.buildingName"/></label>
                            <input type="text" id="buildingName" name="buildingName" maxlength="10" value="${param.buildingName}" class="all_tab_top_input"/>                    
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th width="6%"><s:text name="housemgr.building.list.code"/></th>
                                    <th width="10%"><s:text name="housemgr.building.list.name"/></th>
                                    <th width="6%"><s:text name="housemgr.building.list.coordinate"/></th>
                                    <th width="12%"><s:text name="housemgr.building.list.coverArea"/></th>
                                    <th width="12%"><s:text name="housemgr.building.list.constructionArea"/></th>
                                    <th width="14%"><s:text name="housemgr.district.list.name"/></th>
                                    <th width="10%"><s:text name="housemgr.region.list.name"/></th>
                                    <th width="10%"><s:text name="housemgr.common.sitenumber"/></th>
                                    <th><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="building">
                                    <tr>
                                        <td><s:property value="#building.code"/></td>
                                        <td><s:property value="#building.name"/></td>
                                        <td>
                                            <a href="javascript:void(0)" class="fakeNotetitle all_hover_but" onclick="showLocation('${fileServerUrl}/', '<s:property value="#building.THmHousingDistrictRegionInfo.housingDistrictInfo.floorPlan" />', '<s:property value="#building.coordinate"/>')"><s:text name="common.element.action.show"/></a>
                                        </td>
                                        <td><s:property value="#building.coverArea"/></td>
                                        <td><s:property value="#building.constructionArea"/></td>
                                        <td><s:property value="#building.THmHousingDistrictRegionInfo.housingDistrictInfo.name"/></td>
                                        <td><s:property value="#building.THmHousingDistrictRegionInfo.name"/></td>
                                        <td><s:property value="#building.THmHousingDistrictRegionInfo.housingDistrictInfo.code"/>&nbsp;<s:property value="#building.THmHousingDistrictRegionInfo.code"/>&nbsp;<s:property value="#building.code"/></td>
                                        <td>
                                            <a href="<s:url action="updateBuildingInput"><s:param name="buildingId" value="#building.id"/></s:url>" class="all_hover_but">
                                                <s:text name="common.element.action.update"/>
                                            </a>
                                            <a href="javascript:confirmRemove('<s:url action="removeBuilding"/>','buildingId','<s:property value="#building.id"/>','<s:text name="common.confirm.remove"/>')" class="all_hover_but">
                                                <s:text name="common.element.action.remove"/>
                                            </a>
                                            <a href="<s:url action="generateCellsInput"><s:param name="buildingId" value="#building.id"/></s:url>" class="all_hover_but">
                                                <s:text name="housemgr.cell.generate"/>
                                            </a>
                                            <a href="<s:url action="queryCellList"><s:param name="buildingId" value="#building.id"/></s:url>" class="all_hover_but">
                                                <s:text name="housemgr.cell.list"/>
                                            </a>
                                        </td>
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <s:include value="/views/common/paging.jsp"/>                        
                        <s:if test="successFlag == true">                                       
                            <div class="layer" id="promptFrame">
                                <div>
                                    <p><s:text name="common.system.info"/></p>
                                    <ul>
                                        <li>
                                            <s:text name="common.element.action.operationSuccess" /><br/><br/>
                                        </li>
                                        <li>
                                            <a href="javascript:hideTip('promptFrame');"><s:text name="common.element.action.confirm"/></a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </s:if>
                        <s:elseif test="fromRemove == true && successFlag == false">
                            <div class="layer" id="promptFrame">
                                <div>
                                    <p><s:text name="common.element.action.operationfailed"/></p>
                                    <ul>
                                        <li>
                                            <s:text name="housemgr.building.exist.related.data" /><br/><br/>
                                        </li>
                                        <li>
                                            <a href="javascript:hideTip('promptFrame');"><s:text name="common.element.action.confirm"/></a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </s:elseif>
                        <div id="list_click" style="display:none;" class="mapxy">
                            <div class="mapxyshow">
                                <div id="map" style="width:810px; overflow:hidden; position:absolute;">
                                    <img src="#" />
                                    <span id="spShow" style="width:20px; height:10px; border-radius:10px;" /></span>
                                </div>
                                <div class="all_tab_butb"><a href="#" class="closediv all_hover_but"><s:text name="common.element.action.close"/></a></div>
                            </div>
                        </div>
                        <div class="layer" id="removeFrame" style="display:none;">
                            <div>
                                <p id="removeMsg"></p>
                                <ul>
                                    <li>
                                        <a id="removeUrl" href="#">
                                            <s:text name="common.element.action.confirm"/>
                                        </a>
                                        <a href="javascript:hideTip('removeFrame');"><s:text name="common.element.action.cancel"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <script>
            var selectAllTitle = "<s:text name='common.title.all'/>";
        </script>
    </body>
</html>
