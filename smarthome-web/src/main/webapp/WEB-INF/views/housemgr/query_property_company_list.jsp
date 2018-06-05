<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="housemgr.propertyCompany"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_clorbox.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/device/device.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
		<style type="text/css">
		    #cboxNext,#cboxPrevious,#cboxCurrent,#cboxSlideshow{ display:none !important;}
		</style>
    </head>

    <body>
        <form action="<s:url action="queryList"/>">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="#" class="all_menu_a">物业公司列表</a>
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
                                    <th width="10%">编号</th>
                                    <th width="45%">物业公司名称</th>
                                    <th width="15%">联系方式</th>
                                    <th width="15%">创建时间</th>
                                    <th width="15%">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="propertyInfo">
                                    <tr>
                                        <td><s:property value="#propertyInfo.id"/></td>
                                        <td><s:property value="#propertyInfo.name"/></td>
                                        <td><s:property value="#propertyInfo.contact"/></td>
                                        <td><s:date name="#propertyInfo.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>
                                        	<a href="javascript:confirmAction('<s:url action="deletePropertyCompany"><s:param name="propertyCompanyInfo.id" value="#propertyInfo.id"/></s:url>','<s:text name="common.confirm.remove"/>')" class="all_hover_but"><s:text name="common.element.action.remove"/></a>
                                        	<a href="<s:url action="updatePropertyCompanyInfoInput"><s:param name="propertyCompanyInfoId" value="#propertyInfo.id"/></s:url>" class="all_hover_but"><s:text name="common.element.action.update"/></a>
                                        	<a href="<s:url action="detail"><s:param name="propertyCompanyInfoId" value="#propertyInfo.id"/></s:url>" class="all_hover_but">查看</a>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>
                        <s:include value="/views/common/paging.jsp"/> 
                        <div class="layer" id="confirmFrame" style="display:none;">
                            <div>
                                <p id="confirmMsg"></p>
                                <ul>
                                    <li>
                                        <a id="targetUrl" href="#">
                                            <s:text name="common.element.action.confirm"/>
                                        </a>
                                        <a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.cancel"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>                                      
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
