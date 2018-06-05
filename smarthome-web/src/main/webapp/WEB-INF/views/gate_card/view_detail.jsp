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
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action="queryList"/>"><s:text name="gatecard.management.list"/></a>
                    <a href="<s:url action='addInput'/>"><s:text name="gatecard.management.add"/></a>
                    <a href="<s:url action="viewDetail"><s:param name="gateCardId" value="gateCard.gateCardId"/></s:url>" class="all_menu_a"><s:text name="gatecard.management.detail"/></a>
                    <!-- 
                    <a href="<s:url action="queryList" namespace='/gateCardVisit'/>"><s:text name="gatecard.management.gatecardvisit.list"/></a>
                    <a href="<s:url action="queryList" namespace='/idCardVisit'/>"><s:text name="gatecard.management.idcardvisit.list"/></a>
                	 -->
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="gatecard.cardno"/></td>
                                <td><s:property value="gateCard.cardNo"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="gatecard.name"/></td>
                                <td><s:property value="gateCard.ownerName"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="gatecard.idcard"/></td>
                                <td><s:property value="gateCard.ownerIdCard"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="gatecard.status"/></td>
                                <td>
                                    <s:if test="gateCard.status == 0">
                                    	<s:text name="gatecard.status.enable"/>
                                    </s:if>
                                    <s:else>
                                    	<s:text name="gatecard.status.disable"/>
                                    </s:else>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top"><s:text name="gatecard.permissions"/></td>
                                <td class="jur_tab_body">
                                    <p><em><s:text name="gatecard.gate"/></em><i><s:text name="gatecard.validtime"/></i></p>
                                    <ul>
                                        <s:iterator value="%{gateCard.gatePermissions}" var="gp">
                                            <li>
                                                <strong class="jur_tab_long"><s:property value="#gp.device.deviceAlias"/></strong>
                                                <s:date name="#gp.beginTime" format="yyyy-MM-dd HH:mm" />&nbsp;---&nbsp;<s:date name="#gp.endTime" format="yyyy-MM-dd HH:mm" />
                                            </li>
                                        </s:iterator>                                        
                                    </ul>
                                </td>
                            </tr>                                                      
                        </tbody>
                    </table>                   
                </div>
            </div>
        </div>
    </body>
</html>
