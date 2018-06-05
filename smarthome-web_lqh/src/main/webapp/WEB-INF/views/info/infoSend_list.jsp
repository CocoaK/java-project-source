<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@page import="com.biencloud.smarthome.web.info.vo.InfoReceiverVO"%>
<%@page import="com.biencloud.smarthome.web.info.vo.InfoSendVO"%>
<%@page import="com.biencloud.smarthome.web.charge.vo.ChargeDataVO"%>
<%@page import="com.biencloud.smarthome.web.common.action.ActionUtils"%>
<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String path=request.getContextPath(); Boolean editResult=request.getAttribute("editResult")==null?null:(Boolean)request.getAttribute("editResult"); %>
<% request.setAttribute("STATUSNOAUDIT",InfoSendVO.STATUSNOAUDIT); request.setAttribute("STATUSNOSEND",InfoSendVO.STATUSNOSEND);request.setAttribute("STATUSYESSEND",InfoSendVO.STATUSYESSEND);
request.setAttribute("INFOTYPEPERSON",InfoReceiverVO.INFO_TYPE_PERSON);
request.setAttribute("INFOTYPECOMMUNITY",InfoReceiverVO.INFO_TYPE_COMMUNITY);
request.setAttribute("STATUSNOAUDITTHROUGH",InfoSendVO.STATUSNOAUDIT_THROUGH);
request.setAttribute("STATUSNOAUDITNOTHROUGH",InfoSendVO.STATUSNOAUDIT_NOTHROUGH);
request.setAttribute("LOGINUSERTYPEOWNER",Constants.LOGIN_USER_TYPE_OWNER);
request.setAttribute("LOGINUSERTYPEPAUSER",Constants.LOGIN_USER_TYPE_PAUSER);
request.setAttribute("SENDMODEGOING",InfoSendVO.SENDMODE_GOING);
request.setAttribute("SENDMODETIMIMG",InfoSendVO.SENDMODE_TIMIMG);
String userType=(String)request.getAttribute("userType");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="infosend.management"/></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/dtree.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
        <script type="text/javascript" src="<s:url value='/js/validateUtil.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
</head>

<body>
	<form action="<s:url action="queryInfoSendList"/>" method="post" name="formname" id="formname">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                    <a href="<%=path%>/info/queryInfoSendList.action" class="all_menu_a"><s:text name="infosend.management.list"/></a>
                        <a href="<%=path%>/info/goToInputInfoSend.action"><s:text name="infosend.add"/></a>
                        <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)||Constants.LOGIN_USER_TYPE_OWNER.equals(userType)){ %>
                    <a href="<%=path%>/info/queryInfoReceiverList.action"><s:text name="inforeceiver.management.list"/></a>   
                    <%} %>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">        
                        	<label><s:text name="infosend.status"/></label>
                            <div class="select_div">
                            <select id="infoSend.status" name="infoSend.status">
                                <option value=""><s:text name="common.title.all"/></option>
                            	<option value="<%=InfoSendVO.STATUSNOAUDIT%>"><s:text name="infosend.noaudit"/></option>
                            	<s:if test="#request.LoginUserType!=#request.LOGINUSERTYPEOWNER">
                            	<option value="<%=InfoSendVO.STATUSNOSEND%>"><s:text name="infosend.nosend"/></option>
                            	</s:if>
                            	<option value="<%=InfoSendVO.STATUSNOAUDIT_THROUGH%>"><s:text name="common.element.action.approveyes"/></option>
                            	<option value="<%=InfoSendVO.STATUSNOAUDIT_NOTHROUGH%>"><s:text name="common.element.action.approveno"/></option>
                            	<option value="<%=InfoSendVO.STATUSYESSEND%>"><s:text name="infosend.yessend"/></option>
                            </select>
                            </div>
                            <s:if test="#request.LoginUserType==#request.LOGINUSERTYPEPAUSER">
                            <label><s:text name="infosend.type"/></label>
                            <div class="select_div">
                            <select id="infoSend.remark" name="infoSend.remark">
                                <option value=""><s:text name="common.title.all"/></option>
                            	<option value="<%=InfoReceiverVO.INFO_TYPE_PERSON%>" >
                            	<s:text name="info.personinfo"/>
                            	</option>
                            	<option value="<%=InfoReceiverVO.INFO_TYPE_COMMUNITY%>"><s:text name="info.cominfo"/></option> 
                            </select>
                            </div>
                            </s:if>   
                            <s:if test="#request.LoginUserType!=#request.LOGINUSERTYPEOWNER">
                            <label><s:text name="infosend.sendmode"/></label>
                            <div class="select_div">
                            <select id="infoSend.sendMode" name="infoSend.sendMode">
                                <option value=""><s:text name="common.title.all"/></option>
                            	<option value="<%=InfoSendVO.SENDMODE_GOING%>" >
                            	<s:text name="infosend.nowsend"/>
                            	</option>
                            	<option value="<%=InfoSendVO.SENDMODE_TIMIMG%>"><s:text name="infosend.timimgsend"/></option> 
                            </select>
                            </div>
                            </s:if>                 
                            <label><s:text name="infosend.title"/></label>
                             <input type="text" name="infoSend.title" id="infoSend.title" value="${infoSend.title}" class="all_tab_top_input" maxlength="16"/>
                            <label><s:text name="infosend.sendtime"/></label>
                            <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="infoSend.sendStartTime" id="infoSend.sendStartTime" value="<s:date name="infoSend.sendStartTime" format="yyyy-MM-dd HH:mm" />"/>
                            <label><s:text name="common.element.title.to"/></label>
                            <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="infoSend.sendEndTime" id="infoSend.sendEndTime" value="<s:date name="infoSend.sendEndTime" format="yyyy-MM-dd HH:mm" />"/>
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />      
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="infosend.title"/></th>
                                    <th><s:text name="infosend.status"/></th>
                                    <th><s:text name="infosend.sendtime"/></th>
                                    <th><s:text name="common.action.createdtime"/></th>
                                    <s:if test="#request.LoginUserType!=#request.LOGINUSERTYPEOWNER">
                                     <th><s:text name="infosend.sendmode"/></th>
                                     <th><s:text name="infosend.timimgtime"/></th>
                                    </s:if> 
                                    <th><s:text name="infosend.sendusername"/></th>
                                    <th><s:text name="infosend.type"/></th>
                                     <% if(Constants.LOGIN_USER_TYPE_OWNER.equals(userType)){ %>
                                     <th width="280px"><s:text name="common.element.title.action"/></th>
                                     <%}else{ %>
                                     <th width="450px"><s:text name="common.element.title.action"/></th>
                                     <%} %>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="infoSend">
                                    <tr>
                                        <td class="text_left"><s:property value="#infoSend.title"/></td>
                                        <td>
                                        <s:if test="#infoSend.status==#request.STATUSNOAUDIT"><s:text name="infosend.noaudit"/></s:if>
                                        <s:elseif test="#infoSend.status==#request.STATUSNOSEND"><s:text name="infosend.nosend"/></s:elseif>
                                        <s:elseif test="#infoSend.status==#request.STATUSNOAUDITTHROUGH"><s:text name="common.element.action.approveyes"/></s:elseif>
                                        <s:elseif test="#infoSend.status==#request.STATUSNOAUDITNOTHROUGH"><s:text name="common.element.action.approveno"/></s:elseif>
                                        <s:else><s:text name="infosend.yessend"/></s:else>
                                        </td> 
                                        <td>
                                        <s:date name="#infoSend.sendTime" format="yyyy-MM-dd HH:mm:ss" />
                                        </td>
                                        <td>
                                        <s:date name="#infoSend.createTime" format="yyyy-MM-dd HH:mm:ss" />
                                        </td>
                                        <s:if test="#request.LoginUserType!=#request.LOGINUSERTYPEOWNER">
                                        <td>
                                        <s:if test="#infoSend.sendMode==#request.SENDMODEGOING"><s:text name="infosend.nowsend"/></s:if>
                                        <s:else><s:text name="infosend.timimgsend"/></s:else>
                                        </td>
                                        <td>
                                        <s:if test="#infoSend.sendMode==#request.SENDMODETIMIMG"><s:date name="#infoSend.timimgTime" format="yyyy-MM-dd HH:mm:ss" /></s:if>
                                        </td>
                                        </s:if>
                                        <td><s:property value="#infoSend.sendUserName"/></td>
                                        <td>
                                        <s:if test="#infoSend.type==#request.INFOTYPEPERSON">
                                        <s:text name="info.personinfo"/></s:if>
                                        <s:elseif test="#infoSend.type==#request.INFOTYPECOMMUNITY"><s:text name="info.cominfo"/></s:elseif>
                                        <s:else><s:text name="info.sysinfo"/></s:else>
                                        </td>
                                        <td >
                                        	<s:if test="#infoSend.status==#request.STATUSNOAUDIT&&#request.LoginUserType==#request.LOGINUSERTYPEPAUSER">
                                            <a href="javascript:showAutidtip(<s:property value='#infoSend.id'/>)" class="all_hover_but">
                                                <s:text name="common.element.action.approve"/>
                                            </a>
                                            </s:if>
                                            <s:elseif test="#request.LoginUserType==#request.LOGINUSERTYPEPAUSER">
                                            <a class="all_hover_but opacity"><s:text name="common.element.action.approve"/></a>
                                            </s:elseif>
                                       		<a href="<%=path%>/info/findInfoSend.action?requestId=<s:property value='#infoSend.id'/>&<%=ActionUtils.SHOWDETAIL%>" class="all_hover_but">
                                                <s:text name="common.element.action.detail"/>
                                            </a>
                                            <s:if test="((#infoSend.status==#request.STATUSNOSEND&&#infoSend.type!=#request.INFOTYPEPERSON)||(#infoSend.status==#request.STATUSNOAUDITTHROUGH&&#request.LoginUserType==#request.LOGINUSERTYPEOWNER))&&(#infoSend.sendMode==#request.SENDMODEGOING)">
                                            <a href="javascript:publish('<%=path%>/info/sendInfo.action','<s:property value='#infoSend.id'/>')" class="all_hover_but">
                                                <s:text name="common.element.action.publish"/>
                                            </a>
                                            </s:if>
                                            <s:else>
                                            <a class="all_hover_but opacity"><s:text name="common.element.action.publish"/></a>
                                            </s:else>
                                            <s:if test="#request.LoginUserType==#request.LOGINUSERTYPEOWNER">
                                            	<s:if test="#infoSend.status==#request.STATUSNOAUDIT">
	                                            <a href="<%=path%>/info/findInfoSend.action?requestId=<s:property value='#infoSend.id'/>" class="all_hover_but">
	                                                <s:text name="common.element.action.update"/>
	                                            </a>
	                                            </s:if>
	                                            <s:else>
	                                            <a class="all_hover_but opacity"><s:text name="common.element.action.update"/></a>
	                                            </s:else>
                                            </s:if>
                                            <s:else>
                                            	 <s:if test="#infoSend.status!=#request.STATUSYESSEND&&#infoSend.type!=#request.INFOTYPEPERSON">
                                            	 	<a href="<%=path%>/info/findInfoSend.action?requestId=<s:property value='#infoSend.id'/>" class="all_hover_but">
	                                                <s:text name="common.element.action.update"/>
	                                                </a>
                                            	 </s:if>
                                            	 <s:else>
	                                            <a class="all_hover_but opacity"><s:text name="common.element.action.update"/></a>
	                                            </s:else>
                                            </s:else>
                                            <s:if test="#infoSend.type==#request.INFOTYPEPERSON&&#request.LoginUserType==#request.LOGINUSERTYPEPAUSER">
                                            <a class="all_hover_but opacity"><s:text name="common.element.action.remove"/></a>
                                            </s:if>
                                            <s:else>
	                                            <a href="javascript:confirmAction('<%=path%>/info/delInfoSend.action','<s:property value='#infoSend.id'/>','<s:text name="common.confirm.remove"/>')" class="all_hover_but">
                                                <s:text name="common.element.action.remove"/>
                                            	</a>
	                                        </s:else>
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
        <form action="<s:url action="auditInfo"/>" method="post" name="auditForm" id="auditForm"> 
        <div class="layer" id="list_click" style="display:none;">
                            <div style="width: 350px;">
                                <p><s:text name="infosend.approveinfo"/></p>
                                <ul>
                                    <li>
                                    	<input type="hidden" id="requestId" name="requestId"/>
                                        <select name="infoSend.status" id="infoSendStatus" onchange="switchPast(this.value);">
                                        <option value="<%=InfoSendVO.STATUSNOAUDIT_THROUGH %>"><s:text name="common.element.title.past"/></option>
                                        <option value="<%=InfoSendVO.STATUSNOAUDIT_NOTHROUGH %>"><s:text name="common.element.title.nopast"/></option>
                                        </select>     
                                    </li>
                                    <li id="replyId" style="display: none">
										<strong id=""><label><s:text name="infosend.reply"/></label><input type="text" size="40" id="infoSendReply" name="infoSend.reply" onblur="$('#errorMsg').hide();"/></strong>
									</li>
									<li id="errorMsg" style="display:none;"><s:text name="infosend.reply"/><s:text name="common.element.notempty"/></li>
									<li id="lengthInvalid" style="display:none;"><s:text name="common.element.length.errormsg"/>250</li>
                                    <li class="layer_but_hack" >
                                    	<input type="button" value=" <s:text name="common.element.action.confirm"/>" onclick="return submitAudit();"/>
                                    	<input type="button" value=" <s:text name="common.element.action.cancel"/>" onclick="hideTip('list_click');;"/>
                                    </li>
                                </ul>                               
                            </div>
                        </div>    
                        </form>
	<script>
		optionSelectInt('${infoSend.status}',"infoSend.status",6);
		<s:if test="#request.LoginUserType==#request.LOGINUSERTYPEPAUSER">
		optionSelectInt('${infoSend.remark}',"infoSend.remark",3);
		</s:if>
		<s:if test="#request.LoginUserType!=#request.LOGINUSERTYPEOWNER">
		optionSelectInt('${infoSend.sendMode}',"infoSend.sendMode",3);
		</s:if>
	    function goToUrl(){
	    	document.location.href="<%=path%>/info/saveInfoSend.action";
	    }
		
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
	<script>
	function showAutidtip(id){
 		$("#list_click").show();
 		$("#requestId").val(id);
 	}
	 	function switchPast(value){
	 		$("#replyId").hide();
	 		if(value=='<%=InfoSendVO.STATUSNOAUDIT_NOTHROUGH %>') $("#replyId").show();
	 	}
	 	function submitAudit(){
	 		var infoSendStatus=$("#infoSendStatus").val();
	 		var infoSendReply=$("#infoSendReply").val();
	 		$("#errorMsg").hide();
	 		$("#lengthInvalid").hide();
	 		if(infoSendStatus=='<%=InfoSendVO.STATUSNOAUDIT_NOTHROUGH %>'){
	 			if(infoSendReply=='') {
	 				$("#errorMsg").show();
	 				return false;
	 			}
	 			if(infoSendReply.length>250){
	 				$("#lengthInvalid").show();
	 				return false;
	 			}else{
	 				$("#auditForm").submit();
	 				return true;
	 			}
	 		}
	 		else {
	 			$("#auditForm").submit();
	 			return true;
	 		}
	 	}
	</script>
	
</body>
</html>
