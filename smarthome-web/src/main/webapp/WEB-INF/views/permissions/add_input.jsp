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
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/permissions/permissions.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.textbox.js'/>"></script>
        <s:include value="/WEB-INF/views/common/include_common.jsp"></s:include>
        <script>
            $(function() {
                $("#roleDesc").textbox({
                    maxLength: 100
                }); 
            });
        </script>
    </head>

    <body>
        <form action="<s:url action="save"/>" method="post">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action="queryList"/>"><s:text name="permissions.management.list"/></a>
                    <a href="<s:url action='addInput'/>" class="all_menu_a"><s:text name="permissions.management.add"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0" class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><dfn></dfn><s:text name="permissions.name"/></td>
                                <td>
                                    <input type="text" size="20" maxlength="20" id="roleName" name="role.roleName" class="{required:true,maxlength:20}" value="<s:property value='role.roleName'/>" />
                                    <label id="existRoleNameMsg" class="error" style="display:none;"><s:text name="error.rolename.exist"/></label>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top"><s:text name="permissions.desc"/></td>
                                <td><textarea id="roleDesc" name="role.roleDesc" class="{maxlength:100}"><s:property value="role.roleDesc"/></textarea></td>
                            </tr>                           
                            <tr>
                                <td><s:text name="permissions.status"/></td>
                                <td class="all_tbs_radio">
                                    <input type="radio" name="role.status" value="0" checked/><label><s:text name="permissions.status.enabled"/></label>
                                    <input type="radio" name="role.status" value="1" <s:if test="role.status != '' && role.status == 1">checked</s:if>/><label><s:text name="permissions.status.disabled"/></label>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top"><dfn></dfn><s:text name="permissions.menu"/></td>
                                <td class="jur_tab_top">                                   
                                    <p>
                                        <a href="javascript:selectAllMenus('<s:text name="common.element.action.selectall"/>', '<s:text name="common.element.action.selectnone"/>')" id="selectAllMenus" selectFlag="true" class="notetitle all_hover_but"><s:text name="common.element.action.selectall"/></a>
                                    </p>
                                    <ul class="all_tbs_checkbox">
                                        <s:iterator value="%{role.menus}" var="menu">
                                            <li>
                                                <label><s:property value="#menu.menuName"/></label>
                                                <input type="checkbox" name="menuCodes" onchange="validateMenus();" value="<s:property value='#menu.menuCode'/>"/>
                                            </li>
                                        </s:iterator>                                                                              
                                    </ul>
                                    <p class="hr_error"><label id="unselectedMenuCodeMsg" class="error" style="display:none;"><s:text name="error.required"/></label></p>                                   
                                </td>
                            </tr>                                                        
                        </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="submit" value="<s:text name='common.element.action.add' />" /></div>
                    <s:if test="successFlag == true">                                       
                        <div class="layer" id="confirmFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                    <li>
                                        <s:text name="common.add.success"/>
                                    </li>
                                    <br/>
                                    <li>
                                        <a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.confirm"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </s:if>                   
                </div>
            </div>
        </div>
        </form>
        <script>
            var existRoleNameUrl = "<s:url action='existRoleName' namespace='/json/permissions'/>";
        </script>
    </body>
</html>
