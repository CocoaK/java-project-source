<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="operationlog.management.detail"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="operationLogList.action" ><s:text name="operationlog.management.list"/></a>
                    <a href="#" class="all_menu_a"><s:text name="operationlog.management.detail"/></a>
                    <a href="clientLogList.action" ><s:text name="client.log.list"/></a>
                    <a href="fileUploadLogList.action" ><s:text name="fileupload.log.management.list"/></a>
                    <a href="diviceRegeditLogList.action"><s:text name="deviceregedit.log.management.list"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="operationlog.logid"/></td>
                                <td><s:property value="operationLog.logId"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="operationlog.optuser"/></td>
                                <td><s:property value="operationLog.operateUser"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="operationlog.menuname"/></td>
                                <td><s:property value="operationLog.menuCode"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="operationlog.opttime"/></td>
                                <td><s:date name="operationLog.operateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>                            
                            <tr>
                                <td><s:text name="operationlog.operationname"/></td>
                                <td><s:property value="operationLog.operationCode"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="operationlog.ip"/></td>
                                <td><s:property value="operationLog.ip"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="operationlog.optresult"/></td>
                                <td>
                                	<s:if test="operationLog.operateResult==1"><s:text name="operation.success"/></s:if>
                                    <s:if test="operationLog.operateResult==2"><s:text name="operation.fail"/></s:if>
                                    <s:if test="operationLog.operateResult==3"><s:text name="operation.error"/></s:if>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="operationlog.remark"/></td>
                                <td><s:property value="operationLog.remark"/></td>
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
