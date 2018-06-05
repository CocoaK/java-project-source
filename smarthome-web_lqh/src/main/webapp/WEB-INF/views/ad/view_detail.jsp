<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="ad.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="../js/jquery_clorbox.js"></script>
        <style type="text/css">
            #cboxNext,#cboxPrevious,#cboxCurrent,#cboxSlideshow{ display:none !important;}
        </style>
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action='queryList'/>"><s:text name="ad.management.list"/></a>
                    <a href="<s:url action='addInput'/>"><s:text name="ad.management.add"/></a>
                    <a href="<s:url action='viewDetail'><s:param name='adId' value='ad.adId'/></s:url>" class="all_menu_a"><s:if test="ad.adLocation.adSys.sysCode == '01'"><s:text name="ad.management.app.detail"/></s:if><s:else><s:text name="ad.management.web.detail"/></s:else></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0" class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="ad.name"/></td>
                                <td><s:property value="ad.adName"/></td>
                                <td width="120"><s:text name="ad.type"/></td>
                                <td><s:property value="ad.adType.typeName"/></td>                                                               
                            </tr>
                            <tr>
                                <td><s:text name="ad.status"/></td>
                                <td>
                                    <s:if test="ad.status == 0">
                                    	<s:text name="ad.status.applied"/>
                                    </s:if>
                                    <s:elseif test="ad.status == 1">
                                    	<s:text name="ad.status.publishing"/>
                                    </s:elseif>
                                    <s:elseif test="ad.status == 2">
                                    	<s:text name="ad.status.published"/>
                                    </s:elseif>
                                    <s:elseif test="ad.status == 3">
                                    	<s:text name="ad.status.running"/>
                                    </s:elseif>
                                    <s:else>
                                    	<s:text name="ad.status.stopped"/>
                                    </s:else>
                                </td>
                                <td><s:text name="ad.appliedtime"/></td>
                                <td><s:date name="ad.applyedTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                            </tr>                            
                            <tr>
                                <td><s:text name="ad.begintime"/></td>
                                <td><s:date name="ad.adBeginTime" format="yyyy-MM-dd HH:mm" /></td>
                                <td><s:text name="ad.endtime"/></td>
                                <td><s:date name="ad.adEndTime" format="yyyy-MM-dd HH:mm" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="ad.pic"/></td>
                                <td>
                                    <s:if test="ad.adPicUrl != ''">
                                        <a href="<s:property value='ad.adPicUrl'/>" rel="colorbox1" target="_blank" class="all_hover_but"><s:text name="common.element.action.preview"/></a>
                                    </s:if>
                                    <s:else>
                                        <i class="all_hover_but opacity"><s:text name="common.element.action.preview"/></i>
                                    </s:else>
                                </td>
                                <s:if test="ad.adLocation.adSys.sysCode == '01'">
                                    <td><s:text name="ad.pic.detail"/></td>
                                    <td>
                                        <s:if test="ad.adPicUrl != ''">
                                            <a href="<s:property value='ad.adDetailPicUrl'/>" rel="colorbox1" target="_blank" class="all_hover_but"><s:text name="common.element.action.preview"/></a>
                                        </s:if>
                                        <s:else>
                                            <i class="all_hover_but opacity"><s:text name="common.element.action.preview"/></i>
                                        </s:else>
                                    </td>
                                </s:if>
                                <s:else>
                                    <td><s:text name="ad.link.address"/></td>
                                    <td><s:property value="ad.adLinkUrl"/></td>
                                </s:else>                               
                            </tr>                            
                            <tr>
                                <td><s:text name="ad.sys"/></td>
                                <td>
                                    <s:property value="ad.adLocation.adSys.sysName"/>
                                </td>
                                <td><s:text name="ad.location"/></td>
                                <td>
                                    <s:property value="ad.adLocation.locationName"/>
                                </td>
                            </tr>
                            <s:if test="ad.adLocation.adSys.sysCode == '01'">                            
                            <tr>
                                <td><s:text name="ad.device"/></td>
                                <td colspan="3" class="jur_tab_body sadwa">
                                    <p><em><s:text name="common.title.districtname"/></em><i><s:text name="common.title.devicenalias"/></i></p>
                                    <ul>
                                        <s:iterator value="%{ad.adTargets}" var="at">
                                            <li>
                                                <strong class="jur_tab_long"><s:property value="#at.device.housingDistrictInfo.name"/></strong>
                                                <s:property value="#at.device.deviceAlias"/>
                                            </li>
                                        </s:iterator>                                        
                                    </ul>
                                </td>
                            </tr>
                            </s:if>
                            <tr>
                                <td><s:text name="ad.desc"/></td>
                                <td colspan="3"><s:property value="ad.adDesc"/></td>
                            </tr>                                                                                                              
                        </tbody>                       
                    </table>                   
                </div>
            </div>
        </div>
        <script>
            /*<![CDATA[*/
        		$(document).ready(function(){
					$("a[rel^='colorbox']").colorbox({title:"",slideshow:true,slideshowAuto:false,previous:"<s:text name='common.title.browse.previous'/>",next:"<s:text name='common.title.browse.next'/>",close:"<s:text name='common.element.action.close'/>",slideshowStart:"<s:text name='common.element.action.broadcast'/>",slideshowStop:"<s:text name='common.element.action.pause'/>",current:"<s:text name='common.title.browse.info'/>"})
				;})
    		/*]]>*/
        </script>
    </body>
</html>
