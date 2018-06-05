<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="housemgr.region" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/housemgr.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/updateCell.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
    </head>

    <body>
        <form action="<s:url action="updateCell"/>" method="post">
        <input type="hidden" id="cellId" name="cell.id" value="<s:property value='cell.id'/>"/>
        <input type="hidden" id="buildingId" name="cell.THmRegionBuildingInfo.id" value="<s:property value='cell.THmRegionBuildingInfo.id'/>"/>
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
                    <a href="#" class="all_menu_a">小区单元修改</a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="housemgr.district.list.name"/></td>
                                <td><input type="hidden" name="cell.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.name" value="<s:property value='cell.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.name'/>"/><s:property value="cell.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.name"/></td>
                            </tr>
                            <tr>
                                <td width="120"><s:text name="housemgr.region.list.name"/></td>
                                <td><input type="hidden" name="cell.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name" value="<s:property value='cell.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name'/>"/><s:property value="cell.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name"/></td>
                            </tr>
                            <tr>
                                <td width="120"><s:text name="housemgr.building.list.name"/></td>
                                <td><input type="hidden" name="cell.THmRegionBuildingInfo.name" value="<s:property value='cell.THmRegionBuildingInfo.name'/>"/><s:property value="cell.THmRegionBuildingInfo.name"/></td>
                            </tr>
                            <tr>
                                <td width="120"><dfn></dfn><s:text name="housemgr.cell.list.code"/></td>
                                <td>
                                    <input type="text" size="40" name="cell.code" value="<s:property value="cell.code"/>" maxlength="2" class="{required: true, digits: true}" onkeyup="hideExistMsg(this);"/>
                                    <label class="error exist" style="display: none"><s:text name="housemgr.cell.generate.codeRepeat"/></label>
                                </td>
                            </tr>
                            <tr>
                                <td width="120"><dfn></dfn><s:text name="housemgr.cell.list.name"/></td>
                                <td>
                                    <input type="text" size="40" name="cell.name" maxlength="10" value="<s:property value="cell.name"/>" class="{required:true, maxlength: 10}" onkeyup="hideExistMsg(this);"/>
                                    <label class="error exist" style="display: none"><s:text name="housemgr.cell.name.exist"/></label>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="submit" value="<s:text name='common.element.action.confirm.update' />" /></div>
                    <s:include value="/WEB-INF/views/housemgr/include_result_msg.jsp"/>                   
                </div>
            </div>
        </div>
        </form>
        <script>
            QueryLoader.selectorPreload = "body";
            QueryLoader.init();
        </script>
    </body>
</html>
