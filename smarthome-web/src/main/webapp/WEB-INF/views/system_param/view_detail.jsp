<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="sysparam.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action="queryList"/>"><s:text name="sysparam.management.list"/></a>
                    <a href="<s:url action='viewDetail'><s:param name='paramCode' value='systemParam.paramCode'/></s:url>" class="all_menu_a"><s:text name="sysparam.management.detail"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="sysparam.code"/></td>
                                <td><s:property value="systemParam.paramCode"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="sysparam.name"/></td>
                                <td><s:property value="systemParam.paramName"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="sysparam.value"/></td>
                                <td><s:property value="systemParam.paramValue"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="sysparam.desc"/></td>
                                <td><s:property value="systemParam.paramDesc"/></td>
                            </tr>                            
                            <tr>
                                <td><s:text name="common.action.updateduser"/></td>
                                <td><s:property value="systemParam.updatedUser"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.action.updatedtime"/></td>
                                <td><s:date name="systemParam.updatedTime" format="yyyy-MM-dd HH:mm:ss" /></td>
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
