<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="softwareupgrade.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action='queryList'/>"><s:text name="softwareupgrade.management.list"/></a>
                    <a href="<s:url action='addInput'/>"><s:text name="softwareupgrade.management.add"/></a>
                    <a href="<s:url action="viewDetail"><s:param name='softwareId' value='softwareUpgrade.softwareId'/></s:url>" class="all_menu_a"><s:text name="softwareupgrade.management.detail"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodyq">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="softwareupgrade.code"/></td>
                                <td><s:property value="softwareUpgrade.softwareCode"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="softwareupgrade.name"/></td>
                                <td><s:property value="softwareUpgrade.softwareName"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="softwareupgrade.version"/></td>
                                <td><s:property value="softwareUpgrade.versionName"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="softwareupgrade.size"/></td>
                                <td><s:property value="softwareUpgrade.size"/></td>
                            </tr>
                            <tr>
                                <td valign="top"><s:text name="common.title.select.district"/></td>
                                <td>
                                    <s:include value="/WEB-INF/views/common/group_tree.jsp" />                                    
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="softwareupgrade.devicetype"/></td>
                                <td>
                                    <s:iterator value="%{deviceTypes}" var="dt">
                                    	<s:property value="#dt.deviceName"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                    </s:iterator>                                    
                                </td>
                            </tr>
                            <tr>
                                <td valign="top"><s:text name="softwareupgrade.desc"/></td>
                                <td><s:property value="softwareUpgrade.softwareDesc"/></td>
                            </tr>                           
                            <tr>
                                <td><s:text name="softwareupgrade.status"/></td>
                                <td>
                                    <s:if test="softwareUpgrade.status == 0">
                                    	<s:text name="softwareupgrade.status.applied"/>                                           
                                    </s:if>
                                    <s:elseif test="softwareUpgrade.status == 1">
                                        <s:text name="softwareupgrade.status.approved"/>                                           
                                    </s:elseif>
                                    <s:elseif test="softwareUpgrade.status == 2">
                                        <s:text name="softwareupgrade.status.refused"/>                                           
                                    </s:elseif>
                                    <s:elseif test="softwareUpgrade.status == 3">
                                        <s:text name="softwareupgrade.status.publishing"/>                                           
                                    </s:elseif>
                                    <s:elseif test="softwareUpgrade.status == 4">
                                        <s:text name="softwareupgrade.status.published"/>                                           
                                    </s:elseif>                                       
                                    <s:else>
                                    	<s:text name="softwareupgrade.status.disabled"/>
                                    </s:else>
                                </td>
                            </tr>                                                       
                            <tr>
                                <td><s:text name="common.action.applyeduser"/></td>
                                <td><s:property value="softwareUpgrade.applyedUser"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.action.applyedtime"/></td>
                                <td><s:date name="softwareUpgrade.applyedTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.action.approveduser"/></td>
                                <td><s:property value="softwareUpgrade.approvedUser"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.action.approvedtime"/></td>
                                <td><s:date name="softwareUpgrade.approvedTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.action.publisheduser"/></td>
                                <td><s:property value="softwareUpgrade.publishedUser"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.action.publishedtime"/></td>
                                <td><s:date name="softwareUpgrade.publishedTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                            </tr>
                        </tbody>
                    </table>                   
                </div>
            </div>
        </div>
    </body>
</html>
