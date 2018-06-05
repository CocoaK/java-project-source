<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>批量申请二维码</title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_clorbox.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.parseQuerystring.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/districtList.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/regionList.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/buildingList.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/housemgr.js'/>"></script>
        <style type="text/css">
            #cboxNext,#cboxPrevious,#cboxCurrent,#cboxSlideshow{ display:none !important;}
        </style>
    </head>

    <body>
        <form action="<s:url action="queryHouseholdList"/>" method="post">
            <input type="hidden" name="cellId" value="<s:property value="cellId"/>" />
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="queryRegionList" class="all_menu_b"><s:text name="housemgr.region.list"/></a>
                        <a href="queryBuildingList" class="all_menu_b"><s:text name="housemgr.building.list"/></a>
                        <a href="queryCellList" class="all_menu_b"><s:text name="housemgr.cell.list"/></a>
                        <a href="#" class="all_menu_a"><s:text name="housemgr.house.list"/></a>
                        <a href="queryHouseholdList?print"><s:text name="hoursemgr.common.printsitenumber"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                            <label class="all_border_right" for="housingStatus"><s:text name="housemgr.house.list.housingStatus"/></label>
                            <div class="select_div">
                                <select id="housingStatus" name="house.housingStatus">
                                    <option value=""><s:text name="common.title.all"/></option>
                                    <option value="0" <s:if test="house.housingStatus != '' && house.housingStatus == 0">selected</s:if>><s:text name="housemgr.house.list.housingStatus.notIn"/></option>
                                    <option value="1" <s:if test="house.housingStatus != '' && house.housingStatus == 1">selected</s:if>><s:text name="housemgr.house.list.housingStatus.in"/></option>
                                </select>
                            </div>
                            <label class="all_border_right" for="houseName"><s:text name="housemgr.house.list.name"/></label>
			                <input type="text" maxlength="4" id="houseName" name="house.name" value="<s:property value='house.name'/>" class="all_tab_top_input"/>                            

		                    <div class="click_div">
		                        <strong class="screening_show search_but"><s:text name="common.element.action.moreFilter" /></strong>
		                        <ul class="screening_body">
		                            <li>
			                            <label class="all_border_right" for="districtId"><s:text name="housemgr.region.list.search.district"/></label>
			                            <div class="select_div">
			                                <select id="districtId" name="districtId"></select>
			                            </div>
		                            </li>
		                            <li>
			                            <label class="all_border_right" for="regionId"><s:text name="housemgr.region.list.search.regionName"/></label>
			                            <div class="select_div">
			                                <select id="regionId" name="regionId" regionId="<s:property value='regionId'/>"></select>
			                            </div>
		                            </li>
		                            <li>
			                            <label class="all_border_right" for="buildingId"><s:text name="housemgr.building.list.search.buildingName"/></label>
			                            <div class="select_div">
			                                <select id="buildingId" name="buildingId" buildingId="<s:property value='buildingId'/>"></select>
			                            </div>
		                            </li>
		                            <li>
			                            <label class="all_border_right" for="cellName"><s:text name="housemgr.cell.list.search.cellName"/></label>
			                            <input type="text" maxlength="10" id="cellName" name="cellName" value="${param.cellName}" class="all_tab_top_input"/>
		                            </li>
		                            <li>
			                            <label class="all_border_right" for="ownerName"><s:text name="housemgr.house.list.ownerName"/></label>
                            			<input type="text" maxlength="25" id="ownerName" name="house.owner.userName" value="<s:property value='house.owner.userName'/>" class="all_tab_top_input"/>
		                            </li>
		                        </ul>
		                    </div>
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th ><s:text name="housemgr.house.list.code"/></th>
                                    <th ><s:text name="housemgr.cell.list.name"/></th>
                                    <th ><s:text name="housemgr.building.list.name"/></th>
                                    <th ><s:text name="housemgr.region.list.name"/></th>
                                    <th width="120"><s:text name="housemgr.common.sitenumber"/></th>
                                    <th width="100">选择</th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="house">
                                    <tr>
                                        <td>
                                            <a href="<s:url action="viewHouseholdDetail"><s:param name="houseId" value="#house.id"/></s:url>">
                                                <s:property value="#house.code"/>
                                            </a>
                                        </td>
                                        <td><s:property value="#house.THmBuildingCellInfo.name"/></td>
                                        <td><s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.name"/></td>
                                        <td><s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name"/></td>
                                        <td><s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.code"/>&nbsp;<s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.code"/>&nbsp;<s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.code"/>&nbsp;<s:property value="#house.THmBuildingCellInfo.code"/>&nbsp;<s:property value="#house.code"/></td>
                                        
                                        <td width="100">
                                        	
                                            <a href="<s:url action="updateHouseholdInput"><s:param name="houseId" value="#house.id"/></s:url>" class="all_hover_but">
                                                <s:text name="common.element.action.update"/>
                                            </a>
                                            <a href="javascript:confirmRemove('<s:url action="removeHousehold"/>','houseId','<s:property value="#house.id"/>','<s:text name="common.confirm.remove"/>')" class="all_hover_but">
                                                <s:text name="common.element.action.remove"/>
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
                                            <s:text name="housemgr.house.exist.related.data" /><br/><br/>
                                        </li>
                                        <li>
                                            <a href="javascript:hideTip('promptFrame');"><s:text name="common.element.action.confirm"/></a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </s:elseif>
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

            $(document).ready(function(){
                $("a[rel^='colorbox']").colorbox({title:"",slideshow:true,slideshowAuto:false,previous:"上一张",next:"下一张",close:"关闭",slideshowStart:"播放",slideshowStop:"暂停",current:"第 {current} 张 (共{total}张)"});
            });
        </script>
    </body>
</html>
