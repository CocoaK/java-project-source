<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="fileupload.log.management.detail"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                	<a href="<s:url action="operationLogList" />" class="all_menu_b"><s:text name="operationlog.management.list"/></a>
                    <a href="<s:url action="clientLogList" />" ><s:text name="client.log.list"/></a>
                    <a href="<s:url action="fileUploadLogList" />"><s:text name="fileupload.log.management.list"/></a>
                    <a href="#" class="all_menu_a"><s:text name="fileupload.log.management.detail"/></a>
                    <a href="<s:url action="diviceRegeditLogList" />"><s:text name="deviceregedit.log.management.list"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="fileupload.log.source"/></td>
                                <td><s:property value="fileUploadLog.source"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="fileupload.log.name"/></td>
                                <td><s:property value="fileUploadLog.name"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="fileupload.log.format"/></td>
                                <td><s:property value="fileUploadLog.format"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="fileupload.log.size"/></td>
                                <td>
                                <s:property value="fileUploadLog.size"/>
                                </td>
                            </tr>                            
                            <tr>
                                <td><s:text name="fileupload.log.deviceno"/></td>
                                <td><s:property value="fileUploadLog.deviceNo"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="client.log.add.time"/></td>
                                <td><s:date name="fileUploadLog.addTime" format="yyyy-MM-dd HH:mm" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="fileupload.log.exception"/></td>
                                <td><s:property value="fileUploadLog.exception" /></td>
                            </tr>
                        </tbody>
                    </table>                   
                </div>
            </div>
        </div>
    </body>
</html>
