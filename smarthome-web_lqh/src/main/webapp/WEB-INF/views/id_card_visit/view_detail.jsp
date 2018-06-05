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
                    <a href="<s:url action="queryList" namespace='/gateCard'/>"><s:text name="gatecard.management.list"/></a>
                    <a href="<s:url action='addInput' namespace='/gateCard'/>"><s:text name="gatecard.management.add"/></a>
                    <a href="<s:url action="queryList" namespace='/gateCardVisit'/>"><s:text name="gatecard.management.gatecardvisit.list"/></a>
                    <a href="<s:url action='queryList'/>"><s:text name="gatecard.management.idcardvisit.list"/></a>
                    <a href="<s:url action="viewDetail"><s:param name="visitId" value="idCardVisit.visitId"/></s:url>" class="all_menu_a"><s:text name="gatecard.management.idcardvisit.detail"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="gatecard.name"/></td>
                                <td><s:property value="idCardVisit.visitorName"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="gatecard.idcard"/></td>
                                <td><s:property value="idCardVisit.idCard"/></td>
                            </tr>                           
                            <tr>
                                <td><s:text name="common.title.gender"/></td>
                                <td>
                                    <s:if test="idCardVisit.gender != '' && idCardVisit.gender == 0">
                                    	<s:text name="common.title.gender.male" />
                                    </s:if>
                                    <s:elseif test="idCardVisit.gender != '' && idCardVisit.gender == 1">
                                    	<s:text name="common.title.gender.female" />
                                    </s:elseif>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="common.title.address"/></td>
                                <td><s:property value="idCardVisit.visitorAddress"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="gatecard.reason"/></td>
                                <td><s:property value="idCardVisit.reason"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="gatecard.visittime"/></td>
                                <td><s:date name="idCardVisit.visitTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                            </tr>                                                      
                        </tbody>
                    </table>                   
                </div>
            </div>
        </div>
    </body>
</html>
