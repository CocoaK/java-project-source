<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="housemgr.house.list" /></title>
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
                    <%-- <a href="queryHouseholdList" class="all_menu_b"><s:text name="housemgr.house.list"/></a>
                    <a href="<s:url action="queryRegionList"/>"><s:text name="housemgr.region.list"/></a> --%>
                    <a href="queryHouseholdList" class="all_menu_b">小区房号列表</a>
                    <a href="<s:url action="queryRegionList"/>">小区区域列表</a>
                    <!-- <a href="queryBuildingList" class="all_menu_b"><s:text name="housemgr.building.list"/></a>
                    <a href="queryCellList" class="all_menu_b"><s:text name="housemgr.cell.list"/></a> -->
                    <a href="queryBuildingList" class="all_menu_b">小区楼栋列表</a>
                    <a href="queryCellList" class="all_menu_b">小区单元列表</a>
                    <a href="#" class="all_menu_a"><s:text name="housemgr.house.detail"/></a>
                </div>
            </div>
            <div class="main_outside">
	            <div class="all_inside">
                    <table cellspacing="0" class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="housemgr.district.list.name"/></td>
                                <td><s:property value="house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.name"/></td>
                                <td width="120"><s:text name="housemgr.house.list.code"/></td>
                                <td><s:property value="house.code"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="housemgr.region.list.name"/></td>
                                <td colspan="3"><s:property value="house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name"/></td>
                               <%--  <td width="120"><s:text name="housemgr.house.list.size"/></td>
                                <td>
                                    <s:if test="house.THmCellSizeInfo">
                                        <a href="${fileServerUrl}/<s:property value="house.THmCellSizeInfo.plan" />" rel="colorbox1" target="_blank">
                                            <s:property value="house.THmCellSizeInfo.room"/><s:text name="housemgr.size.generate.room"/><s:property value="house.THmCellSizeInfo.hall"/><s:text name="housemgr.size.generate.hall"/>
                                        </a>
                                    </s:if>
                                </td> --%>
                            </tr>
                            <tr>
                                <td width="120"><s:text name="housemgr.building.list.name"/></td>
                                <td><s:property value="house.THmBuildingCellInfo.THmRegionBuildingInfo.name"/></td>
                                <td width="120"><s:text name="housemgr.house.list.ownerName"/></td>
                                <td><s:property value="house.owner.userName"/></td>
                            </tr>
                            <tr>
                                <td width="120"><s:text name="housemgr.cell.list.name"/></td>
                                <td><s:property value="house.THmBuildingCellInfo.name"/></td>
                                <td width="120"><s:text name="housemgr.house.list.checkInDate"/></td>
                                <td><s:date name="house.checkInDate" format="yyyy-MM-dd" /></td>
                            </tr>
                            <!-- <tr>
                                <td width="120"><s:text name="housemgr.house.list.code"/></td>
                                <td><s:property value="house.code"/></td>
                            </tr> 
                            <tr>
                                <td width="120"><s:text name="housemgr.house.list.size"/></td>
                                <td>
                                    <s:if test="house.THmCellSizeInfo">
                                        <a href="${fileServerUrl}/<s:property value="house.THmCellSizeInfo.plan" />" rel="colorbox1" target="_blank">
                                            <s:property value="house.THmCellSizeInfo.room"/><s:text name="housemgr.size.generate.room"/><s:property value="house.THmCellSizeInfo.hall"/><s:text name="housemgr.size.generate.hall"/>
                                        </a>
                                    </s:if>
                                </td>
                            </tr>
                            <tr>
                                <td width="120"><s:text name="housemgr.house.list.area"/></td>
                                <td><s:property value='house.area'/></td>
                            </tr>
                            <tr>
                                <td width="120"><s:text name="housemgr.house.list.ownerName"/></td>
                                <td><s:property value="house.owner.userName"/></td>
                            </tr>
                            <tr>
                                <td width="120"><s:text name="housemgr.house.list.housingStatus"/></td>
                                <td>
                                    <s:if test="house.housingStatus == null || house.housingStatus == '' || house.housingStatus == 0"><s:text name="housemgr.house.list.housingStatus.notIn"/></s:if>
                                    <s:if test="house.housingStatus == 1"><s:text name="housemgr.house.list.housingStatus.in"/></s:if>
                                </td>
                            </tr>    -->                        
                            <tr>
                            	<td width="120"><s:text name="housemgr.house.list.housingStatus"/></td>
                                <td>
                                    <s:if test="house.housingStatus == null || house.housingStatus == '' || house.housingStatus == 0"><s:text name="housemgr.house.list.housingStatus.notIn"/></s:if>
                                    <s:if test="house.housingStatus == 1"><s:text name="housemgr.house.list.housingStatus.in"/></s:if>
                                </td>
                                <td width="120"><s:text name="common.title.gender"/></td>
                                <td>
                                    <s:if test="house.owner.gender != '' && house.owner.gender == 0">
                                        <s:text name="common.title.gender.male" />
                                    </s:if>
                                    <s:elseif test="house.owner.gender != '' && house.owner.gender == 1">
                                        <s:text name="common.title.gender.female" />
                                    </s:elseif>
                                </td> 
                            </tr>
                            <tr>
                            	<td><s:text name="common.title.homephone"/></td>
                                <td><s:property value="house.owner.homePhone"/></td>
                                <td><s:text name="common.title.mobilephone"/></td>
                                <td><s:property value="house.owner.mobilePhone"/></td>
                            </tr>
                           <!--  <tr>
                            	<td>二维码数量</td>
        						<td colspan="3">
        							可视对讲:
        							<s:if test="qrcodeCountVO.sipCount != '' "><s:property value="qrcodeCountVO.sipCount"/></s:if>
        							<s:else>0</s:else>，
        							开锁:
        							<s:if test="qrcodeCountVO.lockCount != '' "><s:property value="qrcodeCountVO.lockCount"/></s:if>
        							<s:else>0</s:else>，
        							<a href='<s:url action="detailInput" namespace="/qrcode/count">
        								<s:param name="userId" value="user.userId"/></s:url>' class="all_hover_but">修改</a>
        						</td>
                            </tr> -->
                        </tbody>
                    </table>
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
