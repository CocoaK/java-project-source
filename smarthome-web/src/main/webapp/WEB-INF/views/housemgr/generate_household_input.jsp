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
        <script type="text/javascript" src="<s:url value='/js/housemgr/house.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
    </head>

    <body>
        <form action="<s:url action="generateHouseholds"/>" method="post">
        <input type="hidden" name="cell.id" value="<s:property value='cell.id'/>"/>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action="queryRegionList"/>"><s:text name="housemgr.region.list"/></a>
                    <a href="queryBuildingList" class="all_menu_b"><s:text name="housemgr.building.list"/></a>
                    <a href="queryCellList" class="all_menu_b"><s:text name="housemgr.cell.list"/></a>
                    <a href="queryHouseholdList" class="all_menu_b"><s:text name="housemgr.house.list"/></a>
                    <a href="#" class="all_menu_a"><s:text name="housemgr.house.generate"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
	                 <table cellspacing="0" class="all_tab_body all_tab_bodys">
	                    <tbody>
	                        <tr>
	                            <td width="120"><s:text name="housemgr.district.list.name"/></td>
	                            <td><input type="hidden" name="cell.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.name" value="<s:property value='cell.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.name'/>"/><s:property value='cell.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.name'/></td>
	                            <td width="120"><s:text name="housemgr.region.list.name"/></td>
	                            <td><input type="hidden" name="cell.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name" value="<s:property value='cell.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name'/>"/><s:property value='cell.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name'/></td>
	                        </tr>
	                        <tr>
	                            <td width="120"><s:text name="housemgr.building.list.name"/></td>
	                            <td><input type="hidden" name="cell.THmRegionBuildingInfo.name" value="<s:property value='cell.THmRegionBuildingInfo.name'/>"/><s:property value='cell.THmRegionBuildingInfo.name'/></td>
                                <td width="120"><s:text name="housemgr.cell.list.name"/></td>
                                <td><input type="hidden" name="cell.name" value="<s:property value='cell.name'/>"/><s:property value='cell.name'/></td>
	                        </tr>
	                    </tbody>
	                </table>
	                <div class="main_tab_body building_body">
	                    <div>
	                        <table cellspacing="0" class="all_tab_body all_tab_bodys">
	                            <tbody id="newHouseTable">
	                                <tr>
	                                    <td width="120">房号结构</td>
	                                    <td>
	                                    	<div>
	                                    		<label class="all_border_right"><dfn></dfn>起始层数:</label>
	                                    		<input id="startFloor" type="text" size="10" value="1" maxlength="2" class="{required:true, num12:true}" />
	                                    		<label class="all_border_right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<dfn></dfn>结束层数:</label>
	                                    		<input id="endFloor" type="text" size="10" value="1" maxlength="2" class="{required:true, num12:true}" />
	                                    		<label class="all_border_right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<dfn></dfn>每层户数:</label>
	                                    		<input id="houseCount" type="text" size="10" value="3" maxlength="2" class="{required:true, num12:true}" />
	                                    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                    		<a href="#" id="houseCreate" class="all_hover_but">生成</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font id="msg" color="red"></font>
	                                    	</div>
	                                    </td>

	                                    
	                                </tr>
	                                <tr>
	                                    <td width="120" class="all_tab_bscor">1</td>
	                                    <td>
                                           <label><dfn></dfn><s:text name="housemgr.house.list.code" /></label>
                                           <input name="houseCodes" type="text" size="10" value="0101" maxlength="4" class="{required: true, digits: true}" onkeyup="markUniqueEle('houseCodes');hideExistMsg(this);"/>
                                           <label class="error exist" style="display: none"><s:text name="housemgr.house.generate.codeExist"/></label>
                                           <label><s:text name="housemgr.house.list.size" /></label>
                                           <select name="sizeIds">
	                                           <s:iterator value="sizeMap">
	                                               <option value="<s:property value="key"/>"><s:property value="value"/></option>
	                                           </s:iterator>
                                           </select>
	                                    </td>
	                                </tr>
	                                <tr>
                                        <td class="all_tab_bscor">2</td>
                                        <td>
                                           <label><dfn></dfn><s:text name="housemgr.house.list.code" /></label>
                                           <input name="houseCodes" type="text" size="10" value="0102" maxlength="4" class="{required: true, digits: true}" onkeyup="markUniqueEle('houseCodes');hideExistMsg(this);"/>
                                           <label class="error exist" style="display: none"><s:text name="housemgr.house.generate.codeExist"/></label>
                                           <label><s:text name="housemgr.house.list.size" /></label>
                                           <select name="sizeIds">
                                               <s:iterator value="sizeMap">
                                                   <option value="<s:property value="key"/>"><s:property value="value"/></option>
                                               </s:iterator>
                                           </select>
                                        </td>
	                                </tr>
	                                <tr>
                                        <td class="all_tab_bscor">3</td>
                                        <td>
                                           <label><dfn></dfn><s:text name="housemgr.house.list.code" /></label>
                                           <input name="houseCodes" type="text" size="10" value="0103" maxlength="4" class="{required: true, digits: true}" onkeyup="markUniqueEle('houseCodes');hideExistMsg(this);"/>
                                           <label class="error exist" style="display: none"><s:text name="housemgr.house.generate.codeExist"/></label>
                                           <label><s:text name="housemgr.house.list.size" /></label>
                                           <select name="sizeIds">
                                               <s:iterator value="sizeMap">
                                                   <option value="<s:property value="key"/>"><s:property value="value"/></option>
                                               </s:iterator>
                                           </select>
                                        </td>
	                                </tr>
	                            </tbody>
	                        </table>
	                    </div>
	                </div>
                    <div class="all_tab_butb"><input type="submit" value="<s:text name='common.element.action.add' />" /></div>                    
                    <s:include value="/WEB-INF/views/housemgr/include_result_msg.jsp"/>                   
                </div>
                <s:if test="unallowedFlag == true">
                    <div class="layer" id="undefinedCellSizeFrame">
                        <div>
                            <p>
                                <s:text name="common.system.info" />
                            </p>
                            <ul>
                                <li>
                                    <s:text name="error.cellsize.undefined" /><br/><br/>
                                </li>
                            </ul>
                        </div>
                    </div>
                </s:if>
            </div>
        </div>
        </form>
        <script>
            QueryLoader.selectorPreload = "body";
            QueryLoader.init();
            
        </script>
    </body>
</html>
