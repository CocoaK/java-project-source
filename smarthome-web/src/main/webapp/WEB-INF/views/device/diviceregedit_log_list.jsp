<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
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
<title><s:text name="deviceregedit.log.management"/></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
                 <script type="text/javascript">
	function preview(oper) { 
		if (oper < 10){ 
			bdhtml=window.document.body.innerHTML;
			sprnstr="<!--startprint"+oper+"-->"; 
			eprnstr="<!--endprint"+oper+"-->";
			prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18);
			prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
			window.document.body.innerHTML=prnhtml; 
			window.print(); 
			window.document.body.innerHTML=bdhtml; 
			} else{ 
		window.print(); 
		} 
	} 
</script>
</head>

<body>
	<form action="<s:url action="regeditLogList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                    	<a href="<%=path %>/push/pushAction_queryByDeviceNo.action?pushVO.pushClientId=${deviceNumber}&pushVO.pushKind=all&deviceNumber=${deviceNumber}&macAddr=${macAddr}"><s:text name="datapush.unpush"/></a>
						<a href="<%=path %>/push/pushFinishAction_listByDeviceNo.action?pushFinishVO.pushClientId=${deviceNumber}&pushFinishVO.pushKind=all&deviceNumber=${deviceNumber}&macAddr=${macAddr}" ><s:text name="datapush.pushed"/></a>
						<a href="<%=path %>/log/regeditLogList.action?diviceRegeditLog.macAddr=${macAddr}&deviceNumber=${deviceNumber}&macAddr=${macAddr}" class="all_menu_a"><s:text name="deviceregedit.log.management.list" /></a>
						<a href="<%=path %>/log/clientLogs.action?clientLog.deviceMac=${macAddr}&deviceNumber=${deviceNumber}&macAddr=${macAddr}" ><s:text name="client.log.list"/></a>
						<a href="<%=path %>/log/appDataLogs.action?appDataLog.deviceNo=${deviceNumber}&deviceNumber=${deviceNumber}&macAddr=${macAddr}" ><s:text name="appdata.log.management.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">
                        	<input class="all_tab_top_input" type="hidden" name="macAddr" value="${macAddr}" />
							<input class="all_tab_top_input" type="hidden" name="deviceNumber" value="${deviceNumber}" />                
                            <label class="all_border_right"><s:text name="deviceregedit.log.macaddr"/></label>
                            <input type="text" readonly="readonly" name="diviceRegeditLog.macAddr" value="${diviceRegeditLog.macAddr}" class="all_tab_top_input"/>
                            <label><s:text name="client.log.add.time"/></label>
		                            <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="diviceRegeditLog.addStartTime" id="diviceRegeditLog.addStartTime" value="<s:date name="diviceRegeditLog.addStartTime" format="yyyy-MM-dd HH:mm" />"/>
		                            <span><s:text name="common.element.title.to"/></span>
		                            <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="diviceRegeditLog.addEndTime" id="diviceRegeditLog.addEndTime" value="<s:date name="diviceRegeditLog.addEndTime" format="yyyy-MM-dd HH:mm" />"/>                 
                            <div class="click_div"><strong class="screening_show search_but"><s:text name="common.element.action.moreFilter"/></strong>
		                    <ul class="screening_body">
			                    <li>
			                        <label class="all_border_right"><s:text name="deviceregedit.log.districtname"/></label>
                            		<input type="text" maxlength="20" name="diviceRegeditLog.districtName" value="${diviceRegeditLog.districtName}" class="all_tab_top_input"/>
			                    </li>
			                    <li>
			                        <label class="all_border_right"><s:text name="deviceregedit.log.regionname"/></label>
                            		<input type="text" maxlength="20" name="diviceRegeditLog.regionName" value="${diviceRegeditLog.regionName}" class="all_tab_top_input"/>
			                    </li>
			                    <li>
			                        <label class="all_border_right"><s:text name="deviceregedit.log.buildingname"/></label>
                            		<input type="text" maxlength="20" name="diviceRegeditLog.buildingName" value="${diviceRegeditLog.buildingName}" class="all_tab_top_input"/>
			                    </li>
			                    <li>
			                        <label class="all_border_right"><s:text name="deviceregedit.log.cellname"/></label>
                            		<input type="text" maxlength="20" name="diviceRegeditLog.cellName" value="${diviceRegeditLog.cellName}" class="all_tab_top_input"/>
			                    </li>
			                    <li>
			                        <label class="all_border_right"><s:text name="deviceregedit.log.hourseno"/></label>
                            		<input type="text" maxlength="20" name="diviceRegeditLog.hourseNo" value="${diviceRegeditLog.hourseNo}" class="all_tab_top_input"/>
			                    </li>
		                    </ul>
		                    </div>
                            <input type="submit" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                         <!--startprint1-->
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="deviceregedit.log.name"/></th>
                                    <th><s:text name="deviceregedit.log.deviceno"/></th>
                                    <th><s:text name="deviceregedit.log.divecetype"/></th>
                                    <th><s:text name="deviceregedit.log.hourseno"/></th>
                                    <th><s:text name="operationlog.ip"/></th>
                                    <th><s:text name="deviceregedit.log.location"/></th>
                                    <th><s:text name="deviceregedit.log.addtime"/></th>
                                     <th><s:text name="client.log.action"/></th><!--endprint1--> <!--startprint1-->
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="diviceRegeditLog">
                                    <tr>
                                        <td><s:property value="#diviceRegeditLog.name"/></td>
                                        <td><s:property value="#diviceRegeditLog.deviceNo"/></td>
                                        <td><s:property value="#diviceRegeditLog.diveceType"/></td>  
                                        <td>
                                        <s:property value="#diviceRegeditLog.districtName"/>&nbsp;
                                        <s:property value="#diviceRegeditLog.regionName"/>&nbsp;
                                        <s:property value="#diviceRegeditLog.buildingName"/>&nbsp;
                                        <s:property value="#diviceRegeditLog.cellName"/>&nbsp;
                                        <s:property value="#diviceRegeditLog.hourseNo"/>
                                        </td>  
                                        <td><s:property value="#diviceRegeditLog.userIp"/></td> 
                                        <td><s:property value="#diviceRegeditLog.location"/></td> 
                                        <td><s:date name="#diviceRegeditLog.addTime" format="yyyy-MM-dd HH:mm" /></td> 
                                        <td>
                                        	<a  href="<s:url action='regeditLogDetail'><s:param name="requestId" value="#diviceRegeditLog.id"/><s:param name="deviceNumber" value="#diviceRegeditLog.deviceNo"/><s:param name="macAddr" value="#diviceRegeditLog.macAddr"/></s:url>" class="all_hover_but"><s:text name="client.log.data.view"/></a>
                                        </td>                                  
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <!--endprint1-->
                        <s:include value="/views/common/paging.jsp"/>
                        <s:if test="%{page.totalCount>0}">
		               	<div class="all_tab_butb"><input type="button" value="<s:text name="common.element.action.confirm.print"/>" onclick="preview(1)" /></div>
		               	</s:if>                                      
                    </div>
                </div>
            </div>
        </form>
</body>
</html>
