<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="permissions.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action='queryList'/>"><s:text name="permissions.management.list"/></a>
                    <a href="<s:url action='addInput'/>"><s:text name="permissions.management.add"/></a>
                    <a href="<s:url action='viewDetail'><s:param name='currRoleCode' value='role.roleCode'/></s:url>" class="all_menu_a"><s:text name="permissions.management.detail"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="permissions.code"/></td>
                                <td><s:property value="role.roleCode"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="permissions.name"/></td>
                                <td><s:property value="role.roleName"/></td>
                            </tr>
                            <tr>
                                <td valign="top"><s:text name="permissions.desc"/></td>
                                <td><s:property value="role.roleDesc"/></td>
                            </tr>                           
                            <tr>
                                <td><s:text name="permissions.status"/></td>
                                <td><s:if test="role.status == 0"><s:text name="permissions.status.enabled"/></s:if><s:else><s:text name="permissions.status.disabled"/></s:else></td>
                            </tr>
                            <tr>
                                <td valign="top"><s:text name="permissions.menu"/></td>
                                <td>
                                    <ul class="all_tbs_checkbox all_tbs_checkboxs">
                                    	<s:iterator value="%{role.menus}" var="menu">
                                            <li><label><s:property value="#menu.menuName"/></label></li>
                                        </s:iterator>                                       
                                    </ul>
                                </td>
                            </tr>                           
                            <tr>
                                <td><s:text name="common.action.createduser"/></td>
                                <td><s:property value="role.createdUser"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.action.createdtime"/></td>
                                <td><s:date name="role.createdTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.action.updateduser"/></td>
                                <td><s:property value="role.updatedUser"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.action.updatedtime"/></td>
                                <td><s:date name="role.updatedTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                            </tr>
                        </tbody>
                    </table>                   
                </div>
            </div>
        </div>
    </body>
</html>
