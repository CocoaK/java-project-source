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
        <script type="text/javascript" src="<s:url value='/js/housemgr/districtList.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/regionList.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/location.js'/>"></script>
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
        <form action="<s:url action="queryBuildingList?print"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="queryRegionList" class="all_menu_b"><s:text name="housemgr.region.list"/></a>
                        <a href="queryBuildingList" class="all_menu_b"><s:text name="housemgr.building.list"/></a>
                        <a href="queryCellList" class="all_menu_b"><s:text name="housemgr.cell.list"/></a>
                        <a href="queryHouseholdList"><s:text name="housemgr.house.list"/></a>
                        <a href="#" class="all_menu_a"><s:text name="hoursemgr.common.printsitenumber"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                            <label class="all_border_right" for="districtId"><s:text name="housemgr.region.list.search.district"/></label>
                            <div class="select_div">
                                <select id="districtId" name="districtId" ></select>
                            </div>
                            <label class="all_border_right" for="regionId"><s:text name="housemgr.region.list.search.regionName"/></label>
                            <div class="select_div">
                                <select id="regionId" name="regionId" regionId="<s:property value='regionId'/>"></select>
                            </div>
                            <label class="all_border_right" for="buildingName"><s:text name="housemgr.building.list.search.buildingName"/></label>
                            <input type="text" id="buildingName" name="buildingName" value="${param.buildingName}" maxlength="20" class="all_tab_top_input"/>                    
                            <input type="submit" value="<s:text name='common.element.action.search' />" class="search_but" />       
                            <a href="<s:url action="queryHouseholdList?print"></s:url>" class="all_hover_buts"><s:text name="hoursemgr.common.printsitenumber"/></a>
                            <a href="<s:url action="queryCellList?print"></s:url>" class="all_hover_buts"><s:text name="hoursemgr.common.printcellnumber"/></a>
                            <a href="<s:url action="queryRegionList?print"></s:url>" class="all_hover_buts"><s:text name="hoursemgr.common.printregionnumber"/></a>              
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
                                <s:iterator value="%{page.results}" var="building">
                                    <tr>
                                        <td>
                                    	<s:property value="#building.THmHousingDistrictRegionInfo.housingDistrictInfo.name"/>&nbsp;
                                        <s:property value="#building.THmHousingDistrictRegionInfo.name"/>&nbsp;
                                        <s:property value="#building.name"/>&nbsp;
                                    	</td>
                                        <td><s:property value="#building.THmHousingDistrictRegionInfo.housingDistrictInfo.code"/>&nbsp;
                                        <s:property value="#building.THmHousingDistrictRegionInfo.code"/>&nbsp;
                                        <s:property value="#building.code"/>&nbsp;
                                        </td>
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
