<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><s:text name="client.log.data.content"/></title>
    <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
    <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
</head>
  
  <body>
	<div class="main_section">
    	<div class="all_aside">
			<div class="all_menu">
				<a href="<s:url action='operationLogList' namespace='/log'></s:url>" ><s:text name="operationlog.management.list"/></a>
                <a href="<s:url action='clientLogList' namespace='/log'></s:url>" ><s:text name="client.log.list"/></a>
                <a href="<s:url action='clientLogDetail' namespace='/log'><s:param name='clientLog.id' value='clientLog.id'/></s:url>" class="all_menu_a"><s:text name="client.log.data.content.menu"/></a>
                <a href="<s:url action='fileUploadLogList' namespace='/log' />" ><s:text name="fileupload.log.management.list"/></a>
                <a href="<s:url action='diviceRegeditLogList' namespace='/log' />"><s:text name="deviceregedit.log.management.list"/></a>
			</div>
		</div>
        <div class="main_outside">
            <div class="all_inside">
                <table cellspacing="0" class="all_tab_body all_tab_bodys all_tab_font">
                    <tbody>
                        <tr>
                            <td width="120"><s:text name="client.log.data.content"/>:</td>
                            <td class="all_tab_fonthack"><s:property value="clientLog.dataContent"/></td>
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
