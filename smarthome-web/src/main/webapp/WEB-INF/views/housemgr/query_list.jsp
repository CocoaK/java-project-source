<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="housemgr.district"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_clorbox.js'/>"></script>
		<style type="text/css">
		    #cboxNext,#cboxPrevious,#cboxCurrent,#cboxSlideshow{ display:none !important;}
		</style>
    </head>

    <body>
        <form action="<s:url action="queryList"/>">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="#" class="all_menu_a"><s:text name="housemgr.district.list"/></a>
                        <a href="queryRegionList" class="all_menu_b"><s:text name="housemgr.region.list"/></a>
                        <a href="queryBuildingList" class="all_menu_b"><s:text name="housemgr.building.list"/></a>
                        <a href="queryCellList" class="all_menu_b"><s:text name="housemgr.cell.list"/></a>
                        <a href="queryHouseholdList" class="all_menu_b"><s:text name="housemgr.house.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                            <label class="all_border_right" for="housingDistrictName"><s:text name="housemgr.district.list.search.district"/></label>
                            <input type="text" name="housingDistrictName" id="housingDistrictName" value="${param.housingDistrictName}" class="search_tex"/>                    
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th width="10%"><s:text name="housemgr.district.list.code"/></th>
                                    <th width="10%"><s:text name="housemgr.district.list.name"/></th>
                                    <th width="10%"><s:text name="housemgr.district.list.floorPlan"/></th>
                                    <th width="20%"><s:text name="housemgr.district.list.propertyCompanyInfo"/></th>
                                    <th width="20%"><s:text name="housemgr.district.list.district"/></th>
                                    <th width="10%"><s:text name="housemgr.district.list.propertyCompanyAddress"/></th>
                                    <th width="20%"><s:text name="housemgr.district.list.constructionArea"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="district">
                                    <tr>
                                        <td>
                                            <a href="<s:url action="viewDetail"><s:param name="housingDistrictInfoId" value="#district.id"/></s:url>">
                                                <s:property value="#district.code"/>
                                            </a>
                                        </td>
                                        <td><s:property value="#district.name"/></td>
                                        <td>
                                            <s:if test="#district.floorPlan != null && #district.floorPlan.length() > 0">
                                                <a href="${fileServerUrl}/<s:property value="#district.floorPlan" />" rel="colorbox1" target="_blank"><s:text name="common.element.action.show"/></a>
                                            </s:if>
                                        </td>
                                        <td><s:property value="#district.propertyCompanyInfo.name"/></td>
                                        <td><s:property value="#district.district"/></td>
                                        <td><s:property value="#district.propertyCompanyAddress"/></td>
                                        <td><s:property value="#district.constructionArea"/></td>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>
                        <s:include value="/views/common/paging.jsp"/>                                      
                    </div>
                </div>
            </div>
        </form>
        <script>
            QueryLoader.selectorPreload = "body";
            QueryLoader.init();

	        $(document).ready(function(){
	            $("a[rel^='colorbox']").colorbox({title:"",slideshow:true,slideshowAuto:false,previous:"上一张",next:"下一张",close:"关闭",slideshowStart:"播放",slideshowStop:"暂停",current:"第 {current} 张 (共{total}张)"});
            });
        </script>
    </body>
</html>
