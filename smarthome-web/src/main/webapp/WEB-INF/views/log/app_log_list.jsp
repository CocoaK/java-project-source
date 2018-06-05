<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>

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
                        <a href="<s:url action="operationLogList" namespace='/log'/>"><s:text name="operationlog.management.list"/></a>
                        <a href="<s:url action="clientLogList" namespace='/log'/>" ><s:text name="client.log.list"/></a>
                        <a href="<s:url action="fileUploadLogList" namespace='/log' />" ><s:text name="fileupload.log.management.list"/></a>
                        <a href="<s:url action="diviceRegeditLogList" namespace='/log' />"><s:text name="deviceregedit.log.management.list"/></a>
                        <a href="<s:url action="appDataLogList" namespace='/log' />" class="all_menu_a"><s:text name="appdata.log.management.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                           	<label class="all_border_right"><s:text name="client.log.devicename"/></label>
                            <input type="text" maxlength="40" name="appDataLog.deviceName" value="${appDataLog.deviceName}" class="all_tab_top_input"/>
                            <label class="all_border_right"><s:text name="client.log.mac"/></label>
                            <input type="text" maxlength="20" name="appDataLog.mac" value="${appDataLog.mac}" class="all_tab_top_input"/>
                            <label class="all_border_right"><s:text name="client.log.ip"/></label>
                            <input type="text" maxlength="20" name="appDataLog.ip" value="${appDataLog.ip}" class="all_tab_top_input"/>
                            <!-- <label class="all_border_right"><s:text name="deviceregedit.log.location"/></label>
                            <input type="text" maxlength="100" name="appDataLog.position" value="${appDataLog.position}" class="all_tab_top_input"/> -->
                            <label class="all_border_right"><s:text name="client.log.add.time"/></label>
							<input type='text' name='appDataLog.beginTime' value='<s:date name="appDataLog.beginTime" format="yyyy-MM-dd HH:mm" />' onclick='SelectDate(this,"yyyy-MM-dd hh:mm",0,-150)' readonly='readonly' class="date_input time_input"/> 
							<label class="all_border_right"><s:text name="common.element.title.to"/></label>
	                        <input type='text' name='appDataLog.endTime' value='<s:date name="appDataLog.endTime" format="yyyy-MM-dd HH:mm" />' onclick='SelectDate(this,"yyyy-MM-dd hh:mm",0,-150)' readonly='readonly' class="date_input time_input"/>        
                            <label id="ac"><s:text name="appdata.log.action"/></label>
	                        <div class="select_div">
		                    	<select name="appDataLog.action">
	                                <option value=""><s:text name="common.title.all"/></option>
	                                <option value="gate_data" <s:if test="appDataLog.action != '' && appDataLog.action == 'gate_data'">selected</s:if>><s:text name="app.gate_data" /></option>
                        			<option value="query_call_record" <s:if test="appDataLog.action != '' && appDataLog.action == 'query_call_record'">selected</s:if>><s:text name="app.query_call_record" /></option>
                        			<option value="save_call_record" <s:if test="appDataLog.action != '' && appDataLog.action == 'save_call_record'">selected</s:if>><s:text name="app.save_call_record" /></option>
                        			<option value="remove_call_record" <s:if test="appDataLog.action != '' && appDataLog.action == 'remove_call_record'">selected</s:if>><s:text name="app.remove_call_record" /></option>
                        			<option value="query_gate_card_visit" <s:if test="appDataLog.action != '' && appDataLog.action == 'query_gate_card_visit'">selected</s:if>><s:text name="app.query_gate_card_visit" /></option>
                        			<option value="save_gate_card_visit" <s:if test="appDataLog.action != '' && appDataLog.action == 'save_gate_card_visit'">selected</s:if>><s:text name="app.save_gate_card_visit" /></option>
                        			<option value="remove_gate_card_visit" <s:if test="appDataLog.action != '' && appDataLog.action == 'remove_gate_card_visit'">selected</s:if>><s:text name="app.remove_gate_card_visit" /></option>
                        			<option value="query_id_card_visit" <s:if test="appDataLog.action != '' && appDataLog.action == 'query_id_card_visit'">selected</s:if>><s:text name="app.query_id_card_visit" /></option>
                        			<option value="save_id_card_visit" <s:if test="appDataLog.action != '' && appDataLog.action == 'save_id_card_visit'">selected</s:if>><s:text name="app.save_id_card_visit" /></option>
                        			<option value="remove_id_card_visit" <s:if test="appDataLog.action != '' && appDataLog.action == 'remove_id_card_visit'">selected</s:if>><s:text name="app.remove_id_card_visit" /></option>
                        			<option value="query_alarm" <s:if test="appDataLog.action != '' && appDataLog.action == 'query_alarm'">selected</s:if>><s:text name="app.query_alarm" /></option>
                        			<option value="save_update_alarm" <s:if test="appDataLog.action != '' && appDataLog.action == 'save_update_alarm'">selected</s:if>><s:text name="app.save_update_alarm" /></option>
                        			<option value="cancel_alarm" <s:if test="appDataLog.action != '' && appDataLog.action == 'cancel_alarm'">selected</s:if>><s:text name="app.cancel_alarm" /></option>
                        			<option value="update_device_passwd" <s:if test="appDataLog.action != '' && appDataLog.action == 'update_device_passwd'">selected</s:if>><s:text name="app.update_device_passwd" /></option>
                        			<option value="save_address_book" <s:if test="appDataLog.action != '' && appDataLog.action == 'save_address_book'">selected</s:if>><s:text name="app.save_address_book" /></option>
                        			<option value="delete_address_book" <s:if test="appDataLog.action != '' && appDataLog.action == 'delete_address_book'">selected</s:if>><s:text name="app.delete_address_book" /></option>
                        			<option value="list_address_book" <s:if test="appDataLog.action != '' && appDataLog.action == 'list_address_book'">selected</s:if>><s:text name="app.list_address_book" /></option>
                        			<option value="query_charge_detail" <s:if test="appDataLog.action != '' && appDataLog.action == 'query_charge_detail'">selected</s:if>><s:text name="app.query_charge_detail" /></option>
                        			<option value="query_owner_complaint" <s:if test="appDataLog.action != '' && appDataLog.action == 'query_owner_complaint'">selected</s:if>><s:text name="app.query_owner_complaint" /></option>
                        			<option value="query_property_complaint" <s:if test="appDataLog.action != '' && appDataLog.action == 'query_property_complaint'">selected</s:if>><s:text name="app.query_property_complaint" /></option>
                        			<option value="save_complaint" <s:if test="appDataLog.action != '' && appDataLog.action == 'save_complaint'">selected</s:if>><s:text name="app.save_complaint" /></option>
                        			<option value="remove_complaint" <s:if test="appDataLog.action != '' && appDataLog.action == 'remove_complaint'">selected</s:if>><s:text name="app.remove_complaint" /></option>
                        			<option value="update_complaint" <s:if test="appDataLog.action != '' && appDataLog.action == 'update_complaint'">selected</s:if>><s:text name="app.update_complaint" /></option>
                        			<option value="save_request_repair" <s:if test="appDataLog.action != '' && appDataLog.action == 'save_request_repair'">selected</s:if>><s:text name="app.save_request_repair" /></option>
                        			<option value="company_info" <s:if test="appDataLog.action != '' && appDataLog.action == 'company_info'">selected</s:if>><s:text name="app.company_info" /></option>
                        			<option value="query_room_device" <s:if test="appDataLog.action != '' && appDataLog.action == 'query_room_device'">selected</s:if>><s:text name="app.query_room_device" /></option>
                        			<option value="query_monitor_device" <s:if test="appDataLog.action != '' && appDataLog.action == 'query_monitor_device'">selected</s:if>><s:text name="app.query_monitor_device" /></option>
                        			<option value="room_info" <s:if test="appDataLog.action != '' && appDataLog.action == 'room_info'">selected</s:if>><s:text name="app.room_info" /></option>
                        			<option value="scene_data" <s:if test="appDataLog.action != '' && appDataLog.action == 'scene_data'">selected</s:if>><s:text name="app.scene_data" /></option>
                        			<option value="scene_device_data" <s:if test="appDataLog.action != '' && appDataLog.action == 'scene_device_data'">selected</s:if>><s:text name="app.scene_device_data" /></option>
                        			<option value="server_time" <s:if test="appDataLog.action != '' && appDataLog.action == 'server_time'">selected</s:if>><s:text name="app.server_time" /></option>
                        			<option value="delete_scene" <s:if test="appDataLog.action != '' && appDataLog.action == 'delete_scene'">selected</s:if>><s:text name="app.delete_scene" /></option>
                        			<option value="delete_scene_device" <s:if test="appDataLog.action != '' && appDataLog.action == 'delete_scene_device'">selected</s:if>><s:text name="app.delete_scene_device" /></option>
                        			<option value="save_device_password" <s:if test="appDataLog.action != '' && appDataLog.action == 'save_device_password'">selected</s:if>><s:text name="app.save_device_password" /></option>
                        			<option value="query_device_password" <s:if test="appDataLog.action != '' && appDataLog.action == 'query_device_password'">selected</s:if>><s:text name="app.query_device_password" /></option>
                        			<option value="query_room" <s:if test="appDataLog.action != '' && appDataLog.action == 'query_room'">selected</s:if>><s:text name="app.query_room" /></option>
                        			<option value="query_manage_device" <s:if test="appDataLog.action != '' && appDataLog.action == 'query_manage_device'">selected</s:if>><s:text name="app.query_manage_device" /></option>
                        			<option value="device_location" <s:if test="appDataLog.action != '' && appDataLog.action == 'device_location'">selected</s:if>><s:text name="app.device_location"/></option>
	                            </select>
                            </div>
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
                                    <!-- <th width="400"><s:text name="appdata.log.requestdata"/></th> -->
                                    <!-- <th><s:text name="appdata.log.responsedata"/></th> -->
                                    <th><s:text name="appdata.log.requesttime"/></th>
                                    <th><s:text name="client.log.result"/></th>
                                    <th><s:text name="client.log.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="appLog">
                                    <tr>
                                        <td><s:property value="#appLog.deviceNo"/></td>
                                        <td><s:property value="#appLog.deviceName"/></td>
                                        <td><s:property value="#appLog.fullRoomNo"/></td>
                                        <td><s:property value="#appLog.ip"/></td>
                                        <td><s:property value="#appLog.mac"/></td>
                                        <td><s:property value="#appLog.action"/></td>
                                        <!-- <td title="<s:property value="#appLog.requestData"/>"><div class="all_tb_content_auto all_tb_ct2"><s:property value="#appLog.requestData"/></div></td> -->
                                        <!-- <td title="<s:property value="#appLog.responseData"/>"><div class="all_tb_content_auto all_tb_ct2"><s:property value="#appLog.responseData"/></div></td> -->
                                        <td><s:date name="#appLog.requestTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>
                                        	<s:if test="#appLog.result==1"><s:text name="operation.success"/></s:if>
                                        	<s:if test="#appLog.result==2"><s:text name="operation.fail"/></s:if>
                                        	<s:if test="#appLog.result==3"><s:text name="operation.error"/></s:if>
                                        </td>
                                        <td>
                                        	<a href="<s:url action="appDataLogDetail"><s:param name="appDataLog.id" value="#appLog.id"/></s:url>" class="all_hover_but"><s:text name="client.log.data.view"/></a>
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
