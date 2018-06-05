<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="client.log.manage"/></title>
<link href="<%=basePath%>css/layout.css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jqueryLoader.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery_all.js"></script>
<script src="<%=basePath%>js/jquery-validation/lib/jquery.js" type="text/javascript"></script>
<script src="<%=basePath%>js/jquery-validation/jquery.validate.js" type="text/javascript"></script>

</head>
<script type="text/javascript">
        function locationAction(url)
       {      
        window.location.href =url;  
       }
		
    function doSubmit()
    {
       document.getElementById("pageNum").value=1;
       document.forms[0].submit();
    }
    function view(id)
    {      
      var url="log/clientLogAction_detail.action?clientLog.id="+id;
      locationAction(url);	  
	  
    }
	</script>
<body>
	<form action="<s:url action='clientLogList'/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">                      
                        <a href="<s:url action="operationLogList" namespace='/log'/>" ><s:text name="operationlog.management.list"/></a>
                        <a href="<s:url action="clientLogList" namespace='/log'/>" class="all_menu_a"><s:text name="client.log.list"/></a>
                        <a href="<s:url action="fileUploadLogList" namespace='/log' />"><s:text name="fileupload.log.management.list"/></a>
                        <a href="<s:url action="diviceRegeditLogList" namespace='/log' />"><s:text name="deviceregedit.log.management.list"/></a>
                        <a href="<s:url action="appDataLogList" namespace='/log' />" ><s:text name="appdata.log.management.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                            <label class="all_border_right"><s:text name="client.log.deviceno"/>:</label>
                            <s:textfield maxlength="20" name="clientLog.deviceNo" cssClass="search_tex"></s:textfield>                           
                            <label class="all_border_right"><s:text name="client.log.mac"/>:</label>
                             <s:textfield maxlength="40" name="clientLog.deviceMac" cssClass="search_tex"></s:textfield>
                             <label class="all_border_right"><s:text name="client.log.data.type"/>:</label>
                             <s:textfield maxlength="40" name="clientLog.dataType" cssClass="search_tex"></s:textfield>
                             <label class="all_border_right"><s:text name="client.log.ip"/>:</label>
                             <s:textfield maxlength="20" name="clientLog.ip" cssClass="search_tex"></s:textfield> 
                             <label class="all_border_right"><s:text name="client.log.devicename"/>:</label>
                             <s:textfield maxlength="40" name="clientLog.deviceName" cssClass="search_tex"></s:textfield> 
                             <label class="all_border_right"><s:text name="client.log.area.name"/>:</label>
                             <s:textfield maxlength="40" name="clientLog.areaName" cssClass="search_tex"></s:textfield>                                            
                            <input type="button" value="<s:text name='common.element.action.search' />" class="search_but" onclick="doSubmit();"/>                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="client.log.ip"/></th>
                                    <th><s:text name="client.log.mac"/></th>
                                    <th><s:text name="client.log.launch"/></th>
                                    <th><s:text name="client.log.deviceno"/></th>
                                    <th><s:text name="client.log.devicename"/></th>
                                    <th><s:text name="client.log.data.type"/></th>                                    
                                    <th><s:text name="client.log.area.name"/></th>                                    
                                    <th><s:text name="client.log.add.time"/></th>
                                    <th><s:text name="client.log.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="clientLog">
                                    <tr>
                                        <td>
                                            <s:property value="#clientLog.ip"/>
                                        </td>
                                        <td><s:property value="#clientLog.deviceMac"/></td>
                                        
                                        <td><s:if test="#clientLog.launch==\"appClient\""><s:text name="client.log.launch.appclient"/></s:if>
                                        <s:elseif test="#clientLog.launch==\"server\""><s:text name="client.log.launch.server"/></s:elseif>
                                        </td>
                                        <td><s:property value="#clientLog.deviceNo"/></td>
                                        <td><s:property value="#clientLog.deviceName"/></td>  
                                        <td><s:property value="#clientLog.dataType"/></td>  
                                        <td><s:property value="#clientLog.areaName"/></td>                                           
                                        <td><s:date name="#clientLog.addTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><!-- <a  href="javascript:view('<s:property value="#clientLog.id"/>');" class="all_hover_but"><s:text name="client.log.data.view"/></a> -->
                                        <a  href="<s:url action="clientLogDetail"><s:param name="clientLog.id" value="#clientLog.id"/></s:url>" class="all_hover_but"><s:text name="client.log.data.view"/></a>
                                        </td>                                
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <s:include value="/views/common/paging.jsp"/>                                      
                    </div>
                </div>
            </div>
        </form>
    <script>
		QueryLoader.selectorPreload = "body";
		QueryLoader.init();
	</script>
</body>
</html>
