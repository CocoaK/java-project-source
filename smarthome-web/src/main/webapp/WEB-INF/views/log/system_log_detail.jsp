<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="systemlog.management.detail"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                	<a href="systemLogList.action" ><s:text name="systemlog.management.list"/></a>
                    <a href="operationLogList.action"><s:text name="operationlog.management.list"/></a>
                    <a href="#" class="all_menu_a"><s:text name="systemlog.management.detail"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="systemlog.logid"/></td>
                                <td><s:property value="systemLog.logId"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="systemlog.optuser"/></td>
                                <td><s:property value="systemLog.operateUser"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="systemlog.menucode"/></td>
                                <td><s:property value="systemLog.menuCode"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="systemlog.opttime"/></td>
                                <td><s:date name="systemLog.operateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>                            
                            <tr>
                                <td><s:text name="systemlog.optcode"/></td>
                                <td><s:property value="systemLog.operationCode"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="systemlog.errlocation"/></td>
                                <td><s:property value="systemLog.errorLocation" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="systemlog.errmsg"/></td>
                                <td><s:property value="systemLog.errorMsg" /></td>
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
