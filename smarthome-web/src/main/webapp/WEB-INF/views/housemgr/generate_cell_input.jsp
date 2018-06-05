<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="housemgr.cell"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/housemgr.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/cell.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
    </head>

    <body>
        <form action="<s:url action="generateCells"/>" method="post">
        <input type="hidden" id="buildingId" name="buildingId" value="<s:property value='buildingId'/>"/>
        <input type="hidden" id="regionId" name="building.THmHousingDistrictRegionInfo.id" value="<s:property value='building.THmHousingDistrictRegionInfo.id'/>"/>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action="queryRegionList"/>"><s:text name="housemgr.region.list"/></a>
                    <a href="queryBuildingList" class="all_menu_b"><s:text name="housemgr.building.list"/></a>
                    <a href="queryCellList" class="all_menu_b"><s:text name="housemgr.cell.list"/></a>
                    <a href="#" class="all_menu_a"><s:text name="housemgr.cell.generate"/></a>
                    <a href="queryHouseholdList" class="all_menu_b"><s:text name="housemgr.house.list"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
	                 <table cellspacing="0" class="all_tab_body all_tab_bodys">
	                    <tbody>
	                        <tr>
	                            <td width="120"><s:text name="housemgr.district.list.name"/></td>
	                            <td><input type="hidden" name="building.THmHousingDistrictRegionInfo.housingDistrictInfo.name" value="<s:property value='building.THmHousingDistrictRegionInfo.housingDistrictInfo.name'/>"/><s:property value='building.THmHousingDistrictRegionInfo.housingDistrictInfo.name'/></td>
	                            <td width="120"><s:text name="housemgr.region.list.name"/></td>
	                            <td><input type="hidden" name="building.THmHousingDistrictRegionInfo.name" value="<s:property value='building.THmHousingDistrictRegionInfo.name'/>"/><s:property value='building.THmHousingDistrictRegionInfo.name'/></td>
	                        </tr>
	                        <tr>
	                            <td width="120"><s:text name="housemgr.building.list.name"/></td>
	                            <td colspan="3"><input type="hidden" name="building.name" value="<s:property value='building.name'/>"/><s:property value='building.name'/></td>
	                        </tr>
	                    </tbody>
	                </table>
	                <div class="main_tab_body building_body">
	                    <div>
	                        <table cellspacing="0" class="all_tab_body all_tab_bodys">
	                            <tbody id="newCellTable">
	                                <tr>
	                                    <td width="120"><dfn></dfn><s:text name="housemgr.cell.generate.manual.quantity"/></td>
	                                    <td><input id="generateCount" type="text" size="10" maxlength="2" value="3" class="{required:true, digits: true, min: 1}" /></td>
	                                </tr>
	                                <tr>
	                                    <td>1</td>
	                                    <td>
	                                       <label><dfn></dfn><s:text name="housemgr.cell.list.code" /></label>
	                                       <input name="cellCodes" type="text" size="10" value="01" maxlength="2" class="{required: true, digits: true}" onkeyup="markUniqueEle('cellCodes');hideExistMsg(this);"/>
	                                       <label class="error exist" style="display: none"><s:text name="housemgr.cell.generate.codeRepeat"/></label>
	                                       <label><dfn></dfn><s:text name="housemgr.cell.list.name" /></label>
	                                       <input name="cellNames" type="text" size="10" value="01" maxlength="10" class="{required:true, maxlength: 10}" onkeyup="markUniqueEle('cellNames');hideExistMsg(this);"/>
                                           <label class="error exist" style="display: none"><s:text name="housemgr.cell.name.exist"/></label>
	                                    </td>
	                                </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>
                                           <label><dfn></dfn><s:text name="housemgr.cell.list.code" /></label>
                                           <input name="cellCodes" type="text" size="10" value="02" maxlength="2" class="{required: true, digits: true}" onkeyup="markUniqueEle('cellCodes');hideExistMsg(this);"/>
                                           <label class="error exist" style="display: none"><s:text name="housemgr.cell.generate.codeRepeat"/></label>
                                           <label><dfn></dfn><s:text name="housemgr.cell.list.name" /></label>
                                           <input name="cellNames" type="text" size="10" value="02" maxlength="10" class="{required:true, maxlength: 10}" onkeyup="markUniqueEle('cellNames');hideExistMsg(this);"/>
                                           <label class="error exist" style="display: none"><s:text name="housemgr.cell.name.exist"/></label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>
                                           <label><dfn></dfn><s:text name="housemgr.cell.list.code" /></label>
                                           <input name="cellCodes" type="text" size="10" value="03" maxlength="2" class="{required: true, digits: true}" onkeyup="markUniqueEle('cellCodes');hideExistMsg(this);"/>
                                           <label class="error exist" style="display: none"><s:text name="housemgr.cell.generate.codeRepeat"/></label>
                                           <label><dfn></dfn><s:text name="housemgr.cell.list.name" /></label>
                                           <input name="cellNames" type="text" size="10" value="03" maxlength="10" class="{required:true, maxlength: 10}" onkeyup="markUniqueEle('cellNames');hideExistMsg(this);"/>
                                           <label class="error exist" style="display: none"><s:text name="housemgr.cell.name.exist"/></label>
                                        </td>
                                    </tr>
	                            </tbody>
	                        </table>
	                    </div>
	                </div>
                    <div class="all_tab_butb"><input type="submit" value="<s:text name='common.element.action.add' />" /></div>
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
