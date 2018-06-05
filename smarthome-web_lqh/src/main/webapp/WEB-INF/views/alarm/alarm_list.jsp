<%@page import="com.biencloud.smarthome.web.login.vo.LoginVO"%>
<%@page import="com.biencloud.smarthome.web.alarm.vo.AlarmVO"%>
<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@page import="com.biencloud.smarthome.web.info.vo.InfoReceiverVO"%>
<%@page import="com.biencloud.smarthome.web.info.vo.InfoSendVO"%>
<%@page import="com.biencloud.smarthome.web.charge.vo.ChargeDataVO"%>
<%@page import="com.biencloud.smarthome.web.common.action.ActionUtils"%>
<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String path=request.getContextPath(); Boolean editResult=request.getAttribute("editResult")==null?null:(Boolean)request.getAttribute("editResult"); %>
<% request.setAttribute("HANLDERSTATUSNO",AlarmVO.HANLDER_STATUS_NO); request.setAttribute("HANLDERSTATUSYES",AlarmVO.HANLDER_STATUS_YES);
request.setAttribute("HANLDERSTATUSCANCEL",AlarmVO.HANLDER_STATUS_CANCEL);
LoginVO vo=(LoginVO)session.getAttribute(Constants.KEY_LOGIN_SESSION);
request.setAttribute("LOGINUSERTYPEPAUSER_SESSIONN",vo.getUserType());
request.setAttribute("LOGINUSERTYPEPAUSER",Constants.LOGIN_USER_TYPE_PAUSER);
request.setAttribute("LOGINUSERTYPEOWNER",Constants.LOGIN_USER_TYPE_OWNER);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="alarm.management"/></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script><%--  --%>
        <script type="text/javascript" src="<s:url value='/js/dtree.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/validateUtil.js'/>"></script>
</head>

<body>
	<form action="<s:url action="queryAlarmList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                    <a href="<%=path%>/alarm/queryAlarmList.action" class="all_menu_a"><s:text name="alarm.management.list"/></a>
                    <s:if test="#request.LOGINUSERTYPEPAUSER == #session.login.userType"> <a href="<%=path%>/alarm/getAlarmMapMsg.action"><s:text name="alarm.management.map"/></a></s:if>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">     
                            <label><s:text name="alarm.houseno"/></label>
                            <input type="text" name="alarm.houseNo" id="alarm.houseNo" value="${alarm.houseNo}" maxlength="4" class="all_tab_top_input" maxlength="10"/>
                            <%-- <label><s:text name="alarm.ownername"/></label>
                            <input type="text" name="alarm.ownerUser.userName" id="alarm.ownerUser.userName" value="${alarm.ownerUser.userName}" class="all_tab_top_input" maxlength="10"/>
                            --%>
                            <label><s:text name="alarm.hanlderuser"/></label>
                            <input type="text" name="alarm.paUser.userName" id="alarm.paUser.userName" maxlength="20" value="${alarm.paUser.userName}" class="all_tab_top_input" maxlength="10"/>
                            <label><s:text name="alarm.alarmtype"/></label>
                            <div class="select_div">
                            <select name="alarm.alarmType.alarmType" id="alarmTy">
                            	<option value=""><s:text name='common.title.all'/></option>
                            	<s:iterator value="alarmTypeList" var="gcv">
                            		<option value="<s:property value="#gcv.alarmType"/>"><s:property value="#gcv.alarmName"/></option>
                            	</s:iterator>
                            </select>
                            </div>
                            <label><s:text name="alarm.status"/></label>
                            <div class="select_div">
                            <select id="alarm.status" name=alarm.status>
                            <option value=""><s:text name="common.title.all"/></option>
                            <option value="<%=AlarmVO.HANLDER_STATUS_YES%>"><s:text name="alarm.statusyes"/></option>
                            <option value="<%=AlarmVO.HANLDER_STATUS_NO%>"><s:text name="alarm.statusno"/></option>
                            <option value="<%=AlarmVO.HANLDER_STATUS_CANCEL%>"><s:text name="alarm.statuscancel"/></option>
                            </select>
                            </div>
                            <div class="click_div"><strong class="screening_show search_but"><s:text name="common.element.action.moreFilter"/></strong>
		                    <ul class="screening_body">
			                    <li>
			                        <label><s:text name="alarm.createdtime"/></label>
		                            <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="alarm.alarmStartTime" id="alarm.alarmStartTime" value="<s:date name="alarm.alarmStartTime" format="yyyy-MM-dd HH:mm" />"/>
		                            <span><s:text name="common.element.title.to"/></span>
		                            <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="alarm.alarmEndTime" id="alarm.alarmEndTime" value="<s:date name="alarm.alarmEndTime" format="yyyy-MM-dd HH:mm" />"/>
			                    </li>
			                    <li>
			                        <label><s:text name="alarm.hanldertime"/></label>
		                            <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="alarm.handlerStartTime" id="alarm.handlerStartTime" value="<s:date name="alarm.handlerStartTime" format="yyyy-MM-dd HH:mm" />"/>
		                            <span><s:text name="common.element.title.to"/></span>
		                            <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="alarm.handlerEndTime" id="alarm.handlerEndTime" value="<s:date name="alarm.handlerEndTime" format="yyyy-MM-dd HH:mm" />"/>
			                    </li>
		                    </ul>
		                    </div>
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />      
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="alarm.createdtime"/></th>
                                    <th><s:text name="alarm.houseno"/></th>
                                    <th><s:text name="alarm.alarmtype"/></th>
                                    <th><s:text name="alarm.ownername"/></th>
                                    <th><s:text name="alarm.ownerphone"/></th>
                                    <th><s:text name="alarm.status"/></th>
                                    <th><s:text name="alarm.hanldertime"/></th>
                                    <th><s:text name="alarm.hanlderuser"/></th>
                                    <th width="200px"><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="alarm">
                                    <tr>
                                        <td><s:date name="#alarm.createdTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td>&nbsp;<s:property value="#alarm.houseNo"/></td>
                                        <td>
                                        <s:if test="#request.LOGINUSERTYPEPAUSER_SESSIONN==#request.LOGINUSERTYPEOWNER">
                                        <s:property value="#alarm.alarmType.alarmName"/>
                                        </s:if>
                                        <s:elseif test="#alarm.houseNo==''"><!-- 位置为空表示报警的设备编号已被删掉 -->
                                        <span  class="all_hover_but opacity"><s:property value="#alarm.alarmType.alarmName"/></span>
                                        </s:elseif>
                                        <s:else>
                                        <a href="<%=path%>/alarm/getAlarmMapMsg.action?requestId=<s:property value='#alarm.alarmId'/>" class="all_hover_but">
                                        <s:property value="#alarm.alarmType.alarmName"/>
                                        </a>
                                        </s:else>
                                        </td>
                                        <td><s:property value="#alarm.ownerUser.userName"/></td>
                                        <td><s:property value="#alarm.ownerUser.mobilePhone"/></td>
                                        <td>
                                        <s:if test="#alarm.status==#request.HANLDERSTATUSNO"><s:text name="alarm.statusno"/></s:if>
                                        <s:elseif test="#alarm.status==#request.HANLDERSTATUSCANCEL"><s:text name="alarm.statuscancel"/></s:elseif>
                                        <s:else><s:text name="alarm.statusyes"/></s:else>
                                        </td> 
                                        <td><s:date name="#alarm.hanlderTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><s:property value="#alarm.paUser.userName"/></td>
                                        <td >
                                        	<s:if test="#alarm.status!=#request.HANLDERSTATUSYES&&#request.LOGINUSERTYPEPAUSER_SESSIONN==#request.LOGINUSERTYPEPAUSER&&#alarm.houseNo!=''">
                                            <a href="javascript:updateStatus('<%=path%>/alarm/updateAlarmStatus.action','<s:property value='#alarm.alarmId'/>','status','<%=AlarmVO.HANLDER_STATUS_YES%>')" class="all_hover_but">
                                                <s:text name="common.element.action.finish"/>
                                            </a>
                                            </s:if>
                                            <s:elseif test="#request.LOGINUSERTYPEPAUSER_SESSIONN==#request.LOGINUSERTYPEPAUSER">
                                            <a class="all_hover_but opacity"><s:text name="common.element.action.finish"/></a>
                                            </s:elseif>
                                            <s:if test="#request.LOGINUSERTYPEPAUSER_SESSIONN==#request.LOGINUSERTYPEOWNER">
                                            <s:if test="#alarm.status==#request.HANLDERSTATUSNO&&#alarm.houseNo!=''">
                                            <a href="javascript:updateStatus('<%=path%>/alarm/updateAlarmStatus.action','<s:property value='#alarm.alarmId'/>','status','<%=AlarmVO.HANLDER_STATUS_CANCEL%>')" class="all_hover_but">
                                                <s:text name="common.element.action.cancel"/>
                                            </a>
                                            </s:if>
                                            <s:else>
                                            <a class="all_hover_but opacity"><s:text name="common.element.action.cancel"/></a>
                                            </s:else>
                                           <%-- <s:if test="#alarm.status!=#request.HANLDERSTATUSYES">
                                            <a href="<%=path%>/alarmAction!delById.action?requestId=<s:property value='#alarm.alarmId'/>" class="all_hover_but">
                                                <s:text name="common.element.action.remove"/>
                                            </a>
                                            </s:if> --%> 
                                            </s:if>
                                        </td>
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <s:include value="/views/common/paging.jsp"/>  
                    </div>
                </div>
            </div>
              <div class="layer" id="tipMsgDiv" style="display:none;">
                	<div>
                    	<p><s:text name="common.system.info"/></p>
                    	
                        <ul>
                            <li id="showMsg"></li>
                            <li style="padding-top: 10px"><a href="javascript:hideTip('tipMsgDiv');" id="ConfirmButtonId"><s:text name="common.element.action.confirm"/></a>
                            <span id="cancelButonId" style="display: none"><a href="javascript:hideTip('tipMsgDiv');" ><s:text name="common.element.action.cancel"/></a></span>
                            </li>
                        </ul>
                    </div>
                </div>
        </form>
	<script>
	optionSelectInt("<s:property value='alarm.status'/>","alarm.status",5);
	optionSelectInt("<s:property value='alarm.alarmType.alarmType'/>","alarmTy",8);
	    $("#showMsg").html("");
        <%
          if(editResult!=null){
       	   if(editResult) {
        %>
        $("#showMsg").append("<s:text name='common.element.action.operationSuccess'/>");
        <%}else{%>
        $("#showMsg").append("<s:text name='common.element.action.operationfailed'/>");
        <%}%>
        $("#ConfirmButtonId").attr("href","javascript:$('form')[0].submit();");
        $("#tipMsgDiv").show();
        <%}%>
	</script>
</body>
</html>
