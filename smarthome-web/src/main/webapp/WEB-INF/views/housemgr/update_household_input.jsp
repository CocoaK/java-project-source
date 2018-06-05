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
        <script type="text/javascript" src="<s:url value='/js/housemgr/updateHouse.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
    </head>

    <body>
        <form action="<s:url action="updateHousehold"/>" method="post">
        <input type="hidden" name="houseId" value="<s:property value='houseId'/>"/>
        <input type="hidden" name="cellId" value="<s:property value='house.THmBuildingCellInfo.id'/>"/>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action="queryRegionList"/>"><s:text name="housemgr.region.list"/></a>
                    <a href="queryBuildingList" class="all_menu_b"><s:text name="housemgr.building.list"/></a>
                    <a href="queryCellList" class="all_menu_b"><s:text name="housemgr.cell.list"/></a>
                    <a href="queryHouseholdList" class="all_menu_b"><s:text name="housemgr.house.list"/></a>
                    <a href="#" class="all_menu_a"><s:text name="housemgr.house.update"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="housemgr.district.list.name"/></td>
                                <td><input type="hidden" name="house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.name" value="<s:property value='house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.name'/>"/><s:property value="house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.name"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="housemgr.region.list.name"/></td>
                                <td><input type="hidden" name="house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name" value="<s:property value='house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name'/>"/><s:property value="house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="housemgr.building.list.name"/></td>
                                <td><input type="hidden" name="house.THmBuildingCellInfo.THmRegionBuildingInfo.name" value="<s:property value='house.THmBuildingCellInfo.THmRegionBuildingInfo.name'/>"/><s:property value="house.THmBuildingCellInfo.THmRegionBuildingInfo.name"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="housemgr.cell.list.name"/></td>
                                <td><input type="hidden" name="house.THmBuildingCellInfo.name" value="<s:property value='house.THmBuildingCellInfo.name'/>"/><s:property value="house.THmBuildingCellInfo.name"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="housemgr.house.list.size"/></td>
                                <td>
                                    <select name="sizeId">
                                        <s:iterator value="%{sizeMap}" var="sm">
                                            <option value="<s:property value='key'/>" <s:if test="sizeId == key">selected</s:if>>
                                                <s:property value="value" />
                                            </option>
                                        </s:iterator>
                                    </select>
                                </td>
                            </tr>                            
                            <tr>
                                <td><dfn></dfn><s:text name="housemgr.house.list.code"/></td>
                                <td>
                                    <input type="text" name="house.code" value="<s:property value='house.code'/>" maxlength="4" class="{required: true, digits: true}" onkeyup="hideExistMsg(this);"/>
                                    <label class="error exist" style="display: none"><s:text name="housemgr.house.generate.codeExist"/></label>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="housemgr.house.list.area"/></td>
                                <td class="up_imgs">
                                    <p>
                                        <input type="text" name="house.area" maxlength="7" value="<s:property value='house.area'/>" class="{num:[1,6,2]}"/>
                                    </p>
                                    <p>
                                        <i><s:text name='common.title.number.msg'><s:param>4</s:param><s:param>2</s:param></s:text></i>
                                    </p>
                                </td>
                            </tr>                            
                            <tr>
                                <td><s:text name="housemgr.house.list.chargeTypes"/></td>
                                <td valign="top">                                  
                                	<s:if test="chargeTypes.size() > 0">
                                        <ul class="all_tbs_checkbox room_checkobx">                                   
                                            <s:iterator value="%{chargeTypes}" var="ct">
                                                <li>                                                
                                                    <input type="checkbox" name="selectedChargeTypeIds" value="<s:property value='#ct.id'/>" <s:iterator value="%{house.chargeTypes}" var="chargeType"><s:if test="#chargeType.id == #ct.id">checked</s:if></s:iterator> />
                                                    <label><s:text name='#ct.name' /></label>
                                                </li>                                           
                                            </s:iterator>
                                        </ul>
                                        <p class="hr_error"><label id="unselectedMsg" class="error" style="display:none;"><s:text name="error.required"/></label></p>
                                    </s:if>
                                    <s:else>
                                        <label><s:text name="housemgr.house.no.chargetype"/></label>
                                    </s:else>                                                                       
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
