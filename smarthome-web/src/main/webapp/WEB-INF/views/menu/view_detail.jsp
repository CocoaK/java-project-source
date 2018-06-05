<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="menu.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action="queryList"/>"><s:text name="menu.management.list"/></a>
                    <a href="<s:url action='viewDetail'><s:param name='menuCode' value='menu.menuCode'/></s:url>" class="all_menu_a"><s:text name="menu.management.detail"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="menu.code"/></td>
                                <td><s:property value="menu.menuCode"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="menu.parent.code"/></td>
                                <td><s:property value="menu.parentCode"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="menu.name"/></td>
                                <td><s:property value="menu.menuName"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="menu.url"/></td>
                                <td><s:property value="menu.menuUrl"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="menu.status"/></td>
                                <td><s:if test="menu.status == 0"><s:text name="menu.status.display"/></s:if><s:else><s:text name="menu.status.hide"/></s:else></td>
                            </tr>
                            <tr>
                                <td valign="top"><s:text name="menu.desc"/></td>
                                <td><s:property value="menu.menuDesc"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.action.updateduser"/></td>
                                <td><s:property value="menu.updatedUser"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.action.updatedtime"/></td>
                                <td><s:date name="menu.updatedTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                            </tr>
                        </tbody>
                    </table>                   
                </div>
            </div>
        </div>
        <script>
    		QueryLoader.selectorPreload = "body";
    		QueryLoader.init();
    	</script>
    </body>
</html>
