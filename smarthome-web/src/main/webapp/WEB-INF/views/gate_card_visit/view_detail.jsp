<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="gatecard.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="../js/jquery_clorbox.js"></script>
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action="queryList" namespace='/gateCard'/>"><s:text name="gatecard.management.list"/></a>
                    <a href="<s:url action='addInput' namespace='/gateCard'/>"><s:text name="gatecard.management.add"/></a>
                    <a href="<s:url action="queryList"/>"><s:text name="gatecard.management.gatecardvisit.list"/></a>
                    <a href="<s:url action='queryList' namespace='/idCardVisit'/>"><s:text name="gatecard.management.idcardvisit.list"/></a>
                    <a href="<s:url action="viewDetail"><s:param name="visitId" value="gateCardVisit.visitId"/></s:url>" class="all_menu_a"><s:text name="gatecard.management.gatecardvisit.detail"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="gatecard.cardno"/></td>
                                <td><s:property value="gateCardVisit.cardNo"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="gatecard.name"/></td>
                                <td><s:property value="gateCardVisit.ownerName"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="gatecard.idcard"/></td>
                                <td><s:property value="gateCardVisit.ownerIdCard"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="gatecard.gate"/></td>
                                <td><s:property value="gateCardVisit.deviceAlias"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="gatecard.visittime"/></td>
                                <td><s:date name="gateCardVisit.visitTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="gatecard.capture.pic"/></td>
                                <td>
                                    <s:if test="gateCardVisit.picPath1 != null && gateCardVisit.picPath1 != ''">
                                    	<a href="<s:property value='gateCardVisit.picPath1'/>" rel="colorbox1" target="_blank" class="all_hover_but"><s:text name="common.element.action.show"/></a>                                                
                                    </s:if>
                                </td>
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