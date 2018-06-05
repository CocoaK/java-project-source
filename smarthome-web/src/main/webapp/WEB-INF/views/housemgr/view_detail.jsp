<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="housemgr.district" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_clorbox.js'/>"></script>
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="#" class="all_menu_a"><s:text name="housemgr.district.detail"/></a>
                </div>
            </div>
            <div class="main_outside">
	            <div class="all_inside">
	                <table cellspacing="0" class="all_tab_body all_tab_bodys">
	                    <tbody>
	                        <tr>
	                            <td width="120"><s:text name="housemgr.district.list.name"/></td>
	                            <td><s:property value="housingDistrictInfo.name"/></td>
	                            <td width="120"><s:text name="housemgr.district.list.district"/></td>
	                            <td><s:property value="housingDistrictInfo.district"/></td>
	                        </tr>
	                        <tr>
	                            <td><s:text name="housemgr.district.list.constructionArea"/></td>
	                            <td><s:property value="housingDistrictInfo.constructionArea"/></td>
	                            <td><s:text name="housemgr.district.detail.developer"/></td>
	                            <td><s:property value="housingDistrictInfo.developer"/></td>
	                        </tr>
	                        <tr>
	                            <td><s:text name="housemgr.district.list.floorPlan"/></td>
	                            <td>
                                    <s:if test="housingDistrictInfo.floorPlan != null && housingDistrictInfo.floorPlan.length() > 0">
                                        <a href="${fileServerUrl}<s:property value="housingDistrictInfo.floorPlan" />" rel="colorbox1" target="_blank" class="all_hover_but"><s:text name="common.element.action.show"/></a>
                                    </s:if>
	                            </td>
	                            <td><s:text name="housemgr.district.list.propertyCompanyInfo"/></td>
	                            <td>
                                    <a href="<s:url action="viewPropertyCompanyDetail"><s:param name="propertyCompanyInfoId" value="housingDistrictInfo.propertyCompanyInfo.id"/></s:url>">
                                        <s:property value="housingDistrictInfo.propertyCompanyInfo.name"/>
                                    </a>
                                </td>
	                        </tr>
	                    </tbody>
	                </table>
	                <div class="all_tab_butb">
                        <a href="<s:url action="updateInput"><s:param name="housingDistrictInfoId" value="housingDistrictInfo.id"/></s:url>">
                            <s:text name="housemgr.district.upate"/>
                        </a>
                        <a href="<s:url action="generateRegionsInput"><s:param name="districtId" value="housingDistrictInfo.id"/></s:url>">
                            <s:text name="housemgr.region.generate"/>
                        </a>
                        <a href="<s:url action="updatePropertyCompanyInput"><s:param name="propertyCompanyInfoId" value="housingDistrictInfo.propertyCompanyInfo.id"/></s:url>">
                            <s:text name="housemgr.propertyCompany.update"/>
                        </a>
	                </div>
	            </div>
            </div>
        </div>
        <script>
    		QueryLoader.selectorPreload = "body";
    		QueryLoader.init();

            $(document).ready(function(){
                $("a[rel^='colorbox']").colorbox({title:"",slideshow:true,slideshowAuto:false,previous:"上一张",next:"下一张",close:"关闭",slideshowStart:"播放",slideshowStop:"暂停",current:"第 {current} 张 (共{total}张)"});
            });
    	</script>
    </body>
</html>
