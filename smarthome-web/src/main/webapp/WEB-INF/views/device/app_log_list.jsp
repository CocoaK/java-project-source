<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
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
<title><s:text name="appdata.log.management"/></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
<script type="text/javascript">
	function doSubmit(){
   		document.getElementById("pageNum").value=1;
   		document.forms[0].submit();
   	}
   	
</script>
</head>

<body>
	<form action="<s:url action="appDataLogList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="<%=path %>/push/pushAction_queryByDeviceNo.action?pushVO.pushClientId=${deviceNumber}&pushVO.pushKind=all&deviceNumber=${deviceNumber}&macAddr=${macAddr}"><s:text name="datapush.unpush"/></a>
						<a href="<%=path %>/push/pushFinishAction_listByDeviceNo.action?pushFinishVO.pushClientId=${deviceNumber}&pushFinishVO.pushKind=all&deviceNumber=${deviceNumber}&macAddr=${macAddr}" ><s:text name="datapush.pushed"/></a>
						<a href="<%=path %>/log/regeditLogList.action?diviceRegeditLog.macAddr=${macAddr}&deviceNumber=${deviceNumber}&macAddr=${macAddr}" ><s:text name="deviceregedit.log.management.list"/></a>
						<a href="<%=path %>/log/clientLogs.action?clientLog.deviceMac=${macAddr}&deviceNumber=${deviceNumber}&macAddr=${macAddr}" ><s:text name="client.log.list" /></a>
                        <a href="<%=path %>/log/appDataLogs.action?appDataLog.deviceNo=${deviceNumber}&deviceNumber=${deviceNumber}&macAddr=${macAddr}" class="all_menu_a"><s:text name="appdata.log.management.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                
                        	<input class="all_tab_top_input" type="hidden" name="macAddr" value="${macAddr}" />
							<input class="all_tab_top_input" type="hidden" name="deviceNumber" value="${deviceNumber}" />
							<input class="all_tab_top_input" type="hidden" name="appDataLog.deviceNo" value="${deviceNumber}" />            
                           	<label class="all_border_right"><s:text name="client.log.devicename"/></label>
                            <input type="text" maxlength="40" name="appDataLog.deviceName" value="${appDataLog.deviceName}" class="all_tab_top_input"/>
                            <!--<label class="all_border_right"><s:text name="client.log.mac"/></label>
                            <input type="text" maxlength="20" name="appDataLog.mac" value="${appDataLog.mac}" class="all_tab_top_input"/>
                            <label class="all_border_right"><s:text name="client.log.ip"/></label>
                            <input type="text" maxlength="20" name="appDataLog.ip" value="${appDataLog.ip}" class="all_tab_top_input"/> -->
                            <label class="all_border_right"><s:text name="deviceregedit.log.location"/></label>
                            <input type="text" maxlength="100" name="appDataLog.position" value="${appDataLog.position}" class="all_tab_top_input"/>
                            <label class="all_border_right"><s:text name="client.log.add.time"/></label>
							<input type='text' name='appDataLog.beginTime' value='<s:date name="appDataLog.beginTime" format="yyyy-MM-dd HH:mm" />' onclick='SelectDate(this,"yyyy-MM-dd hh:mm",0,-150)' readonly='readonly' class="date_input time_input"/> 
							<label class="all_border_right"><s:text name="common.element.title.to"/></label>
	                        <input type='text' name='appDataLog.endTime' value='<s:date name="appDataLog.endTime" format="yyyy-MM-dd HH:mm" />' onclick='SelectDate(this,"yyyy-MM-dd hh:mm",0,-150)' readonly='readonly' class="date_input time_input"/>        
                            <label><s:text name="client.log.result"/></label>
	                        <div class="select_div">
		                    	<select name="appDataLog.result">
	                                <option value="">
	                                	<s:text name="common.title.all"/>
	                                </option>
	                                <option value="1" <s:if test="appDataLog.result != '' && appDataLog.result == 1">selected</s:if>>
	                                   	<s:text name="operation.success" />
	                                </option>
	                                <option value="2" <s:if test="appDataLog.result != '' && appDataLog.result == 2">selected</s:if>>
	                                  	<s:text name="operation.fail" />
	                               	</option>
	                               	<option value="3" <s:if test="appDataLog.result != '' && appDataLog.result == 3">selected</s:if>>
	                                   	<s:text name="operation.error" />
	                              	</option>                          
	                            </select>
                            </div>
                            <input type="button" value="<s:text name='common.element.action.search' />" onclick="doSubmit()" class="search_but" />
                                              
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th width="120"><s:text name="client.log.deviceno"/></th>
                                    <th><s:text name="client.log.devicename"/></th>
                                    <th><s:text name="deviceregedit.log.hourseno"/></th>
                                    <th><s:text name="client.log.ip"/></th>
                                    <th><s:text name="client.log.mac"/></th>
                                    <th><s:text name="appdata.log.action"/></th>
                                    <th><s:text name="appdata.log.requesttime"/></th>
                                    <th><s:text name="client.log.result"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="appLog">
                                    <tr>
                                        <td>
                                        	<a href="<s:url action="appDataDetail"><s:param name="appDataLog.id" value="#appLog.id"/><s:param name="deviceNumber" value="#appLog.deviceNo"/>
                                        		<s:param name="macAddr" value="#appLog.mac"/></s:url>">
                                        		<s:property value="#appLog.deviceNo"/>
                                        	</a>
                                        </td>
                                        <td><s:property value="#appLog.deviceName"/></td>
                                        <td><s:property value="#appLog.fullRoomNo"/></td>
                                        <td><s:property value="#appLog.ip"/></td>
                                        <td><s:property value="#appLog.mac"/></td>
                                        <td><s:property value="#appLog.action"/></td>
                                        <td><s:date name="#appLog.requestTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>
                                        	<s:if test="#appLog.result==1"><s:text name="operation.success"/></s:if>
                                        	<s:if test="#appLog.result==2"><s:text name="operation.fail"/></s:if>
                                        	<s:if test="#appLog.result==3"><s:text name="operation.error"/></s:if>
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
