<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="datapush.management"/></title>
<link href="<%=basePath%>css/layout.css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>/js/jqueryLoader.js"></script>
        <script type="text/javascript" src="<%=basePath%>js/jquery_all.js"></script>
<script type="text/javascript">
 function locationAction(url)
    {      
      window.location.href =url;	  
	  
    }
 function repush(id,deviceNo)
    {      
      var url="push/pushAction_deviceRepush.action?pushFinishVO.id="+id+"&pushVO.pushKind=all&pushVO.pushClientId="+deviceNo;
      locationAction(url);	  
	  
    }
    function detail(id)
    {      
      var url="push/pushFinishAction_devicevViewDetail.action?pushFinishVO.id="+id;
      locationAction(url);	  
	  
    }
    function doSubmit()
    {
       document.getElementById("pageNum").value=1;
       document.forms[0].submit();
    }
</script>
</head>
<body>
	<div class="main_section">
		<div class="all_aside">
			<div class="all_menu">
				<a href="<%=path %>/push/pushAction_queryByDeviceNo.action?pushVO.pushClientId=${deviceNumber}&pushVO.pushKind=all&deviceNumber=${deviceNumber}&macAddr=${macAddr}"><s:text name="datapush.unpush"/></a>
				<a href="<%=path %>/push/pushFinishAction_listByDeviceNo.action?pushFinishVO.pushClientId=${deviceNumber}&pushFinishVO.pushKind=all&deviceNumber=${deviceNumber}&macAddr=${macAddr}" class="all_menu_a"><s:text name="datapush.pushed"/></a>
				<a href="<%=path %>/log/regeditLogList.action?diviceRegeditLog.macAddr=${macAddr}&deviceNumber=${deviceNumber}&macAddr=${macAddr}" ><s:text name="deviceregedit.log.management.list"/></a>
				<a href="<%=path %>/log/clientLogs.action?clientLog.deviceMac=${macAddr}&deviceNumber=${deviceNumber}&macAddr=${macAddr}" ><s:text name="client.log.list"/></a>
				<a href="<%=path %>/log/appDataLogs.action?appDataLog.deviceNo=${deviceNumber}&deviceNumber=${deviceNumber}&macAddr=${macAddr}" ><s:text name="appdata.log.management.list"/></a>
			</div>
		</div>
		<div class="main_outside">
		<s:form action="push/pushFinishAction_listByDeviceNo.action"  method="post"  theme="simple">
			<div class="all_inside">
				<div class="all_tab_top">
					<input class="all_tab_top_input" type="hidden" name="macAddr" value="${macAddr}" />
					<input class="all_tab_top_input" type="hidden" name="deviceNumber" value="${deviceNumber}" />
					<label><s:text name="datapush.management.kind"/></label> 
					<div class="select_div">
					<select name="pushFinishVO.pushKind">
					    <option value="all" <s:if test="pushFinishVO.pushKind==\"all\"">selected</s:if>><s:text name="datapush.kind.all"/></option>
						<option value="message" <s:if test="pushFinishVO.pushKind==\"message\"">selected</s:if>><s:text name="datapush.kind.message"/></option>
						<option value="software" <s:if test="pushFinishVO.pushKind==\"software\"">selected</s:if>><s:text name="datapush.kind.software"/></option>
						<option value="gateData" <s:if test="pushFinishVO.pushKind==\"gateData\"">selected</s:if>><s:text name="datapush.kind.gate"/></option>
						<option value="ad" <s:if test="pushFinishVO.pushKind==\"ad\"">selected</s:if>><s:text name="datapush.kind.ad"/></option>
						<!-- 
						<option value="command" <s:if test="pushFinishVO.pushKind==\"command\"">selected</s:if>><s:text name="datapush.kind.command"/></option>
						<option value="updatDevicePsw" <s:if test="pushVO.pushKind==\"updatDevicePsw\"">selected</s:if>><s:text name="datapush.kind.editdevicepsw"/></option>
						<option value="updateDeviceName" <s:if test="pushVO.pushKind==\"updateDeviceName\"">selected</s:if>><s:text name="datapush.kind.devicename"/></option>
						 -->
						<option value="openDoorCommand" <s:if test="pushFinishVO.pushKind==\"openDoorCommand\"">selected</s:if>><s:text name="datapush.kind.openDoorCommand"/></option>
						<option value="protectionCommand" <s:if test="pushFinishVO.pushKind==\"protectionCommand\"">selected</s:if>><s:text name="datapush.kind.protectionCommand"/></option>
						<!-- <option value="removalCommand" <s:if test="pushFinishVO.pushKind==\"removalCommand\"">selected</s:if>><s:text name="datapush.kind.removalCommand"/></option>
						  -->
						<option value="lampSceneCommand" <s:if test="pushFinishVO.pushKind==\"lampSceneCommand\"">selected</s:if>><s:text name="datapush.kind.lampSceneCommand"/></option>
						<!--<option value="openLampCommand" <s:if test="pushVO.pushKind==\"openLampCommand\"">selected</s:if>><s:text name="datapush.kind.openLampCommand"/></option>
						<option value="openCurtainCommand" <s:if test="pushVO.pushKind==\"openCurtainCommand\"">selected</s:if>><s:text name="datapush.kind.openCurtainCommand"/></option>
						<option value="closeCurtainCommand" <s:if test="pushVO.pushKind==\"closeCurtainCommand\"">selected</s:if>><s:text name="datapush.kind.closeCurtainCommand"/></option>
						 -->
						<option value="weather" <s:if test="pushFinishVO.pushKind==\"weather\"">selected</s:if>><s:text name="datapush.kind.weather"/></option>
						<option value="chargeNotice" <s:if test="pushFinishVO.pushKind==\"chargeNotice\"">selected</s:if>><s:text name="datapush.kind.charge.notice"/></option>
					    <!-- <option value="getSceneDevice" <s:if test="pushFinishVO.pushKind==\"getSceneDevice\"">selected</s:if>><s:text name="datapush.kind.getSceseDevice"/></option> -->
					    <option value="setSceneDeviceStatus" <s:if test="pushFinishVO.pushKind==\"setSceneDeviceStatus\"">selected</s:if>><s:text name="datapush.kind.setSceneDeviceStatus"/></option>
						<option value="roomData" <s:if test="pushFinishVO.pushKind==\"roomData\"">selected</s:if>><s:text name="datapush.kind.roomData"/></option>
						<option value="serverIPChange" <s:if test="pushFinishVO.pushKind==\"serverIPChange\"">selected</s:if>><s:text name="datapush.kind.serverIPChange"/></option>
						<option value="houseInfo" <s:if test="pushFinishVO.pushKind==\"houseInfo\"">selected</s:if>><s:text name="datapush.kind.houseInfo"/></option>
						<option value="unlockPassword" <s:if test="pushFinishVO.pushKind==\"unlockPassword\"">selected</s:if>><s:text name="datapush.kind.unlockPassword"/></option>
						<option value="deviceLocation" <s:if test="pushFinishVO.pushKind==\"deviceLocation\"">selected</s:if>><s:text name="datapush.kind.deviceLocation"/></option>
                        <option value="sceneUpload" <s:if test="pushFinishVO.pushKind==\"sceneUpload\"">selected</s:if>><s:text name="datapush.kind.uploadSceneCommand"/></option>
					</select>  
					</div>
					<label><s:text name="datapush.name"/></label> <s:textfield cssClass="all_tab_top_input" name="pushFinishVO.pushName" maxlength="20"/> <label><s:text name="datapush.management.deviceid"/></label>
					<input class="all_tab_top_input" readonly="readonly" name="pushFinishVO.pushClientId" value="${pushFinishVO.pushClientId}" maxlength="20"/> 
					<input type="button" value='<s:text name="datapush.management.button.search"/>' class="search_but"  onclick="doSubmit();"/>
				</div>
				<table cellspacing="0" class="all_tab_body">
					<thead class="all_tab_thead">
						<tr>
							<th width="10%"><s:text name="datapush.name"/></th>
							<th width="10%"><s:text name="datapush.management.kind"/></th>							
							<th width="10%"><s:text name="datapush.addtime"/></th>
							<th width="10%"><s:text name="datapush.management.deviceid"/></th>
							<th width="10%"><s:text name="datapush.push.size"/></th>							
							<th width="10%"><s:text name="datapush.pushedtime"/></th>
							<th width="10%"><s:text name="datapush.action"/></th>
						</tr>
					</thead>
					<tbody>
					  <s:iterator value="%{page.results}" var="push">
						<tr>
							<td class="text_left"><s:property value="#push.pushName"/></td>
							<td><s:if test="#push.pushKind==\"message\"">
							   <s:text name="datapush.kind.message"/>
							   </s:if>
							   <s:elseif test="#push.pushKind==\"software\""><s:text name="datapush.kind.software"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"gateData\""><s:text name="datapush.kind.gate"/></s:elseif>							   
							   <s:elseif test="#push.pushKind==\"ad\""><s:text name="datapush.kind.ad"/></s:elseif>
							   <!-- 
							   <s:elseif test="#push.pushKind==\"command\""><s:text name="datapush.kind.command"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"updatDevicePsw\""><s:text name="datapush.kind.editdevicepsw"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"updateDeviceName\""><s:text name="datapush.kind.devicename"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"setBackGround\""><s:text name="datapush.kind.setbackground"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"setRing\""><s:text name="datapush.kind.setring"/></s:elseif>
							    -->
							   <s:elseif test="#push.pushKind==\"openDoorCommand\""><s:text name="datapush.kind.openDoorCommand"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"removalCommand\""><s:text name="datapush.kind.removalCommand"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"lampSceneCommand\""><s:text name="datapush.kind.lampSceneCommand"/></s:elseif>
							   <!--
							   <s:elseif test="#push.pushKind==\"closeLampCommand\""><s:text name="datapush.kind.closeLampCommand"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"openLampCommand\""><s:text name="datapush.kind.openLampCommand"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"openCurtainCommand\""><s:text name="datapush.kind.openCurtainCommand"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"closeCurtainCommand\""><s:text name="datapush.kind.closeCurtainCommand"/></s:elseif>
							   -->
							   <s:elseif test="#push.pushKind==\"protectionCommand\""><s:text name="datapush.kind.protectionCommand"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"weather\""><s:text name="datapush.kind.weather"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"chargeNotice\""><s:text name="datapush.kind.charge.notice"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"getSceneDevice\""><s:text name="datapush.kind.getSceseDevice"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"setSceneDeviceStatus\""><s:text name="datapush.kind.setSceneDeviceStatus"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"roomData\""><s:text name="datapush.kind.roomData"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"serverIPChange\""><s:text name="datapush.kind.serverIPChange"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"houseInfo\""><s:text name="datapush.kind.houseInfo"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"unlockPassword\""><s:text name="datapush.kind.unlockPassword"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"password\""><s:text name="datapush.kind.password"/></s:elseif>
							   <s:elseif test="#push.pushKind==\"deviceLocation\""><s:text name="datapush.kind.deviceLocation"/></s:elseif>
                               <s:elseif test="#push.pushKind==\"sceneUpload\""><s:text name="datapush.kind.uploadSceneCommand"/></s:elseif>
							   <s:else><s:property value="#push.pushKind"/></s:else> 
							   </td>
							
							
							<td><s:date name="#push.addTime" format="yyyy-MM-dd HH:mm:ss" /></td>
							<td><s:property value="#push.pushClientId"/>
							</td>
							<td class="text_left"><s:if test="#push.size!=null"><s:property value="#push.size"/></s:if>
							</td>
							
							<td><s:date name="#push.pushFinishTime" format="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
							<a  href="push/pushFinishAction_devicevViewDetail.action?pushFinishVO.id=<s:property value="#push.id"/>&deviceNumber=${deviceNumber}&macAddr=${macAddr}" class="all_hover_but"><s:text name="datapush.buttion.detail"/></a>
							<a  href="javascript:repush('<s:property value="#push.id"/>','<s:property value="#push.pushClientId"/>');" class="all_hover_but"><s:text name="datapush.button.repush"/></a>
							</td>
						</tr>
						
						</s:iterator>
					</tbody>
				</table>
				<s:include value="/views/common/paging.jsp"/>
			</div>
			</s:form>
		</div>
	</div>
	<script>
		QueryLoader.selectorPreload = "body";
		QueryLoader.init();
	</script>
</body>
</html>
