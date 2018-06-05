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
        <script type="text/javascript" src="<s:url value='/js/housemgr/district.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
    </head>

    <body>
        <form action="<s:url action="update"/>" method="post" enctype="multipart/form-data">
        <input type="hidden" name="housingDistrictInfo.id" value="<s:property value='housingDistrictInfo.id'/>"/>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action="viewDetail"/>"><s:text name="housemgr.district.detail"/></a>
                    <a href="#" class="all_menu_a"><s:text name="housemgr.district.upate"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="housemgr.district.list.code"/></td>
                                <td><s:property value="housingDistrictInfo.code"/><input type="hidden" name="housingDistrictInfo.code" value="<s:property value="housingDistrictInfo.code"/>" /></td>
                            </tr>
                            <tr>
                                <td width="120"><s:text name="housemgr.district.list.name"/></td>
                                <td><input type="hidden" name="housingDistrictInfo.name" value="<s:property value="housingDistrictInfo.name"/>" /><s:property value="housingDistrictInfo.name"/></td>
                            </tr>
                            <tr>                                
                                <td><s:if test="housingDistrictInfo.floorPlan == null || housingDistrictInfo.floorPlan == ''"><dfn></dfn></s:if><s:text name="housemgr.district.list.floorPlan"/></td>
                                <td class="up_img">
                                    <p>
                                        <input type="text" name="floorPlanFileName" id="floorPlanFileName" class="viewfile" size="50" readonly />                  
                                        <label for="upload" class="all_hover_but file_label"><s:text name="common.element.action.select" /></label>
                                        <input type="file" name="floorPlan" onchange="selectFloorPlanFileName(this.value);hideTip('errorFileSizeMsg');" class="file" />
                                        <label id="picErrorMsg" class="error" style="display:none;"></label>
                                        <s:if test="exceedFileSizeFlag == true || errorFlag == true">
                                            <label id="errorFileSizeMsg" class="error">
                                                <s:if test="exceedFileSizeFlag == true">
                                                    <s:text name="error.file.too.large"/>
                                                </s:if>
                                                <s:else>
                                                    <s:fielderror><s:param>floorPlan</s:param></s:fielderror>
                                                </s:else>
                                            </label>
                                        </s:if>
                                    </p>
                                    <p>
                                        <i>
                                            <s:text name='common.title.file.msg'><s:param><s:property value='#request.picExt'/></s:param><s:param>10M</s:param></s:text>
                                            , <s:text name='common.title.image.resolution.msg'><s:param><s:property value='#request.maxResolution'/></s:param></s:text>
                                        </i>
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td>小区坐标</td>
                                <td>
                                	<p>
                                    <input id="housingDistrictInfoPosition" name="housingDistrictInfo.position" type="text" 
                                    value="<s:property value='housingDistrictInfo.position'/>" size="40" maxlength="40" class="{maxlength: 40}"
                                    onblur="checkPosition(this.value);"/></p><label id="positionMsg" class="error" style="display:none;"></label>
                                	
                                	<p>
                                        <i>请输入真实的经纬度坐标，格式为：116.503694,39.927552。<a target="_blank" href="http://api.map.baidu.com/lbsapi/getpoint/index.html">查询坐标</a></i>
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="housemgr.district.list.propertyCompanyInfo"/></td>
                                <td>
                                    <a href="<s:url action="updatePropertyCompanyInput"/>"><s:property value="housingDistrictInfo.propertyCompanyInfo.name"/></a>
                                    <input id="housingDistrictInfoPropertyCompanyInfoId" type="hidden" name="housingDistrictInfo.propertyCompanyInfo.id" value="<s:property value='housingDistrictInfo.propertyCompanyInfo.id'/>"/>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="housemgr.district.list.propertyCompanyAddress"/></td>
                                <td><input type="text" size="40" name="housingDistrictInfo.propertyCompanyAddress" value="<s:property value="housingDistrictInfo.propertyCompanyAddress"/>" maxlength="30" class="{maxlength: 30}" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="housemgr.district.list.district"/></td>
                                <td><input type="text" size="40" name="housingDistrictInfo.district" value="<s:property value="housingDistrictInfo.district"/>" maxlength="30" class="{maxlength: 30}" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="housemgr.district.list.constructionArea"/></td>
                                <td class="up_imgs">
                                    <p>
                                        <input type="text" size="40" name="housingDistrictInfo.constructionArea" value="<s:property value="housingDistrictInfo.constructionArea"/>" maxlength="16" class="{num:[1,15,2]}" />
                                    </p>
                                    <p>
                                        <i><s:text name='common.title.number.msg'><s:param>13</s:param><s:param>2</s:param></s:text></i>
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;dianz<s:text name="housemgr.district.detail.developer"/></td>
                                <td><input type="text" size="40" name="housingDistrictInfo.developer" value="<s:property value="housingDistrictInfo.developer"/>" maxlength="30" class="{maxlength: 30}" /></td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="submit" value="<s:text name='common.element.action.confirm.update' />" /></div>
	                <div id="list_click" style="display:none;" class="layers">
	                    <div>
	                        <p>
	                            <label for="propertyCompanyName"><s:text name="housemgr.propertyCompany.detail.name"/></label>
	                            <input type="text" size="20" id="propertyCompanyName" />
	                            <input id="searchPropertyCompanyActor" type="button" value="<s:text name="common.element.action.search"/>" class="all_hover_but layerspbt" />
	                        </p>
	                        <ul class="layers_search_list" id="propertyCompanyList">
                                <li><label><input type="radio" name="propertyCompanyId" value="propertyCompanyId" />propertyCompanyName</label></li>
	                        </ul>
	                        <a id="selectPropertyCompanyAct" href="#" class="all_hover_but"><s:text name="common.element.action.confirm"/></a>
	                        <a href="#" class="closediv all_hover_but"><s:text name="common.element.action.cancel"/></a>
	                    </div>
	                </div>
                    <s:include value="/WEB-INF/views/housemgr/include_result_msg.jsp"/>                   
                </div>
            </div>
        </div>
        </form>
        <script>
            QueryLoader.selectorPreload = "body";
            QueryLoader.init();
            
            var ignoreEmpty = "<s:property value='housingDistrictInfo.floorPlan'/>" != "";
            
            function checkPosition(position) {
 				var filter  = /^([0-9\.])+\,([0-9\.])+$/;
 				if (filter.test(position)){
 					return true;
 				}
 				else {
 					$("#positionMsg").removeAttr("style");
 					$("#positionMsg").html("<font color='red'>您的坐标格式不正确</font>");
 					return false;
 				}
}
        </script>
    </body>
</html>
