<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="deviceregedit.log.management.detail"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                	<a href="<s:url action="operationLogList" namespace='/log'/>" ><s:text name="operationlog.management.list"/></a>
                    <a href="<s:url action="clientLogList" namespace='/log'/>" ><s:text name="client.log.list"/></a>
                    <a href="<s:url action="fileUploadLogList" namespace='/log'/>" ><s:text name="fileupload.log.management.list"/></a>
                    <a href="<s:url action="diviceRegeditLogList" namespace='/log'/>" ><s:text name="deviceregedit.log.management.list"/></a>
                    <a href="" class="all_menu_a"><s:text name="deviceregedit.log.management.detail"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="deviceregedit.log.name"/></td>
                                <td><s:property value="diviceRegeditLog.name"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="deviceregedit.log.deviceno"/></td>
                                <td><s:property value="diviceRegeditLog.deviceNo"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="deviceregedit.log.divecetype"/></td>
                                <td><s:property value="diviceRegeditLog.diveceType"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="deviceregedit.log.hourseno"/></td>
                                <td>
                                        <s:property value="diviceRegeditLog.districtName"/>&nbsp;
                                        <s:property value="diviceRegeditLog.regionName"/>&nbsp;
                                        <s:property value="diviceRegeditLog.buildingName"/>&nbsp;
                                        <s:property value="diviceRegeditLog.cellName"/>&nbsp;
                                        <s:property value="diviceRegeditLog.hourseNo"/>
                                        </td>  
                            </tr>
                            <tr>
                                <td><s:text name="deviceregedit.log.siteno"/></td>
                                <td><s:property value="diviceRegeditLog.siteNo" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="deviceregedit.log.addtime"/></td>
                                <td><s:date name="diviceRegeditLog.addTime" format="yyyy-MM-dd HH:mm" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="operationlog.ip"/></td>
                                <td><s:property value="diviceRegeditLog.userIp" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="deviceregedit.log.location"/></td>
                                <td><s:property value="diviceRegeditLog.location" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="deviceregedit.log.macaddr"/></td>
                                <td><s:property value="diviceRegeditLog.macAddr" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="deviceregedit.log.action"/></td>
                                <td><s:property value="diviceRegeditLog.eventAciton" /></td>
                            </tr>
                        </tbody>
                    </table>                   
                </div>
            </div>
        </div>
    </body>
</html>
