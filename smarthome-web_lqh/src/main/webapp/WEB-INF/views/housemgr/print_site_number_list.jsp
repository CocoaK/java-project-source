<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="housemgr.house.list"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_clorbox.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.parseQuerystring.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/districtList.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/regionList.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/buildingList.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/houseList.js'/>"></script>
        <style type="text/css">
            #cboxNext,#cboxPrevious,#cboxCurrent,#cboxSlideshow{ display:none !important;}
        </style>
         <script type="text/javascript">
         var selectAllTitle = "<s:text name='common.title.all'/>";
	function preview(oper) { 
		if (oper < 10){ 
			bdhtml=window.document.body.innerHTML;
			sprnstr="<!--startprint"+oper+"-->"; 
			eprnstr="<!--endprint"+oper+"-->";
			prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18);
			prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
			window.document.body.innerHTML=prnhtml; 
			window.print(); 
			window.document.body.innerHTML=bdhtml; 
			} else{ 
		window.print(); 
		} 
	} 
</script>
    </head>

    <body>
        <form action="<s:url action="queryHouseholdList?print"/>" method="post">
            <input type="hidden" name="cellId" value="<s:property value="cellId"/>" />
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                    	<!-- <a href="queryHouseholdList"><s:text name="housemgr.house.list"/></a>
                        <a href="queryRegionList" class="all_menu_b"><s:text name="housemgr.region.list"/></a> -->
                        <a href="queryHouseholdList">小区房号列表</a>
                        <a href="queryRegionList" class="all_menu_b">小区区域列表</a>
                        <!-- <a href="queryBuildingList" class="all_menu_b"><s:text name="housemgr.building.list"/></a>
                        <a href="queryCellList" class="all_menu_b"><s:text name="housemgr.cell.list"/></a> -->
                        <a href="queryBuildingList" class="all_menu_b">小区楼栋列表</a>
                        <a href="queryCellList" class="all_menu_b">小区单元列表</a>
                        <a href="#" class="all_menu_a"><s:text name="hoursemgr.common.printsitenumber"/></a>
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
                            <label class="all_border_right" for="buildingId"><s:text name="housemgr.building.list.search.buildingName"/></label>
			                            <div class="select_div">
			                                <select id="buildingId" name="buildingId" buildingId="<s:property value='buildingId'/>"></select>
			                            </div>
							<label class="all_border_right" for="cellName"><s:text name="housemgr.cell.list.search.cellName"/></label>
			                            <input type="text" id="cellName" name="cellName" value="${param.cellName}" maxlength="20" class="all_tab_top_input"/>			                            
                            <label class="all_border_right" for="houseName"><s:text name="housemgr.house.list.name"/></label>
			                            <input type="text" maxlength="4" id="houseName" name="house.name" value="<s:property value='house.name'/>" class="all_tab_top_input"/>
                            <input type="submit" value="<s:text name='common.element.action.search' />" class="search_but" />
                          <%--   <a href="<s:url action="queryCellList?print"></s:url>" class="all_hover_buts"><s:text name="hoursemgr.common.printcellnumber"/></a>
                            <a href="<s:url action="queryBuildingList?print"></s:url>" class="all_hover_buts"><s:text name="hoursemgr.common.printbuillingnumber"/></a>
                            <a href="<s:url action="queryRegionList?print"></s:url>" class="all_hover_buts"><s:text name="hoursemgr.common.printregionnumber"/></a> --%>
                        </div>
                        <!--startprint1-->
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th ><s:text name="common.title.name2"/></th>
                                    <th ><s:text name="hoursemgr.common.sitenumber"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="house">
                                    <tr>
                                    	<td>
                                    	<s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.name"/>&nbsp;
                                        <s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name"/>&nbsp;
                                        <s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.name"/>&nbsp;
                                        <s:property value="#house.THmBuildingCellInfo.name"/>&nbsp;
                                        <s:property value="#house.name"/>
                                    	</td>
                                        <td><s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.code"/>&nbsp;
                                        <s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.code"/>&nbsp;
                                        <s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.code"/>&nbsp;
                                        <s:property value="#house.THmBuildingCellInfo.code"/>&nbsp;
                                        <s:property value="#house.code"/></td>
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <!--endprint1-->
                        <s:include value="/views/common/paging.jsp"/>
                        <s:if test="%{page.totalCount>0}">
		               	<div class="all_tab_butb"><input type="button" value="<s:text name="common.element.action.confirm.print"/>" onclick="preview(1)" /></div>
		               	</s:if>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
