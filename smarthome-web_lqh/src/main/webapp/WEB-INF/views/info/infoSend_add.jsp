<%@page import="com.biencloud.smarthome.web.common.util.DateTimeUtil"%>
<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@page import="com.biencloud.smarthome.web.info.vo.InfoSendVO"%> 
<%@page import="com.biencloud.smarthome.web.common.action.ActionUtils"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% String path=request.getContextPath(); %>
<%
boolean isTree=request.getAttribute("treeList")==null?false:true;
request.setAttribute("isTree",isTree);
request.setAttribute("LOGINUSERTYPEOWNER",Constants.LOGIN_USER_TYPE_OWNER);
request.setAttribute("LOGINUSERTYPEPAUSER",Constants.LOGIN_USER_TYPE_PAUSER);
String userType=(String)request.getAttribute("userType");
Boolean editResult=request.getAttribute("editResult")==null?null:(Boolean)request.getAttribute("editResult"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="infosend.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
        <script type="text/javascript" src="<s:url value='/js/validateUtil.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.textbox.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
    </head>

    <body>
        <form action="<s:url action="saveInfoSend"/>" method="post" id="formname" name="formname">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<%=path%>/info/queryInfoSendList.action"><s:text name="infosend.management.list"/></a>
                        <a href="<%=path%>/info/goToInputInfoSend.action" class="all_menu_a"><s:text name="infosend.add"/></a>
                        <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)||Constants.LOGIN_USER_TYPE_OWNER.equals(userType)){ %>
                    <a href="<%=path%>/info/queryInfoReceiverList.action"><s:text name="inforeceiver.management.list"/></a>   
                    <%} %>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodyq">
                        <tbody>
                            <tr>
                                <td width="120"><dfn></dfn><s:text name="infosend.title"/></td>
                                <td >
                                <input type="text" size="40" name="infoSend.title" value="<s:property value='infoSend.title'/>" maxlength="16" class="{required:true,maxlength:16}"/>
                                <input type="hidden" size="40" name="infoSend.id" value="<s:property value='infoSend.id'/>"/>
                                <input type="hidden" size="40" name="infoSend.createTime" value="<s:date name="infoSend.createTime" format="yyyy-MM-dd HH:mm:ss" />"/>
                                <input type="hidden" size="40" name="infoSend.status" id="infoSend.status" value=""/>
                                <input type="hidden" size="40" name="comStrSel" id="comStrSel" value=""/>
                                <input type="hidden" size="40" name="send" id="send" value=""/>
                                </td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="infosend.content"/></td>
                                <td>
                                <textarea name="infoSend.content" id="remark" class="{required:true,maxlength:1000}"><s:property value="infoSend.content"/></textarea></td>
                            </tr> 
                            <s:if test="#request.LoginUserType!=#request.LOGINUSERTYPEOWNER">
                            <tr>
                                <td class="all_tab_bscor"><dfn></dfn><s:text name="infosend.sendmode"/></td>
                                <td class="radio_border"><input type="radio" id="goingSendMode" name="infoSend.sendMode" value="<%=InfoSendVO.SENDMODE_GOING%>" onclick="setTiming(true,this.value);" checked="checked"/><label><s:text name="infosend.nowsend"/></label>
                                <input type="radio" id="timingSendMode" name="infoSend.sendMode" value="<%=InfoSendVO.SENDMODE_TIMIMG%>" onclick="setTiming(false,this.value);"/><label><s:text name="infosend.timimgsend"/></label>
                               </td>
                            </tr> 
                            </s:if>
                            <tr id="timingId" style="display: none;">
                                <td><dfn></dfn><s:text name="infosend.timimgtime"/></td>
                                <td>
                                <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="time_input" name="infoSend.timimgTime" id="infoSend.timimgTime" value="<s:date name="infoSend.timimgTime" format="yyyy-MM-dd HH:mm" />"/>
                                </td>
                            </tr> 
                            <s:if test="#request.isTree">
                            <tr>
                                <td><dfn></dfn><s:text name="infosend.sendob"/></td>
                                <td><s:property value='infoSend.sendOb'/>
                                 <s:if test="#request.LOGINUSERTYPEPAUSER == #session.login.userType">
                                	<jsp:include  page="../common/group_tree_dynamic.jsp"/>
	                                <s:text name="common.element.title.infosenddevice"/>                                       
		                         </s:if>
		                         <s:else>
		                         <jsp:include  page="../common/group_tree.jsp"/>
		                         </s:else>
                                </td>
                            </tr> 
                            </s:if>
                            <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
                            <tr >
                                <td><s:text name="infosend.senddevice"/></td>
                                <td>
                                <ul class="all_tbs_checkbox">
				                    <s:iterator value="%{deviceTypes}" var="dt"
				                        status="st">
				                        <s:if test="#dt.deviceType!='01'">
				                        <li><input type="checkbox"
				                            name="deviceTypes.deviceType"
				                            value="<s:property value='#dt.deviceType'/>"
				                            <s:iterator value="%{#request.dts}" var="deviceType">
				                                                            <s:if test="#dt.deviceType == #deviceType">checked</s:if>
				                                                        </s:iterator> />
				                            <label><s:text name='#dt.deviceName' />
				                        </label></li>
				                        </s:if>
				                    </s:iterator>
				                </ul>
                                </td>
                            </tr> 
                            <%} %>
                        </tbody>
                    </table>
                    <div class="all_tab_butb" id="ButtonId">
                    <s:if test="#request.LoginUserType==#request.LOGINUSERTYPEOWNER">
                    <input type="button" id="submitButtonId" value="<s:text name="common.element.action.submit"/>" onclick="savedata('<%=InfoSendVO.STATUSNOAUDIT%>');" />
                    </s:if>
                    <s:else>
                    <span id="submitId"><input type="button" id="submitButtonId" value="<s:text name="common.element.action.submit"/>" onclick="return savedata('<%=InfoSendVO.STATUSYESSEND%>');" /></span>
                    <input type="button" id="buttonButtonId" value="<s:text name="common.element.action.draft"/>" onclick="return savedata('<%=InfoSendVO.STATUSNOSEND %>');" />
                    </s:else>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include  page="../common/queryHouse.jsp"/>
        <jsp:include  page="../common/confirm_tip.jsp"/>
        </form>
       
	<script type="text/javascript">
		if ('<s:property value='infoSend.id'/>' != ''){
			if('<s:property value='infoSend.sendMode'/>' == '<%=InfoSendVO.SENDMODE_TIMIMG%>'){
				$("#timingSendMode").attr("checked","checked");
				$("#timingId").show();
				$("#submitId").hide();
				$("#send").val('<%=InfoSendVO.SENDMODE_TIMIMG%>');
			}
		}
		function savedata(statusValue) {
			if(!$("#formname").valid()) return false;
	    	document.getElementById("infoSend.status").value=statusValue;
	    	var sendmode=$("#send").val();
	    	if(sendmode==<%=InfoSendVO.SENDMODE_TIMIMG%>){
	    		var timing=document.getElementById("infoSend.timimgTime").value;
	    		if(timing==''){
	    			alert("<s:text name='common.title.alertmsgtiming'/>");
		    		return false;
	    		}else {
	    			var yourtime = timing.replace("-","/");//替换字符，变成标准格式
	    			var d2=new Date();//取今天的日期
	    			var d1 = new Date(Date.parse(yourtime));
	    			if(d1<=d2){
	    				alert("<s:text name='sysgroup.timinmgtimeselectalert'/>");
	    				return false;
	    			}
	    		}
	    	} 
	    	<s:if test="#request.isTree">
	    	var out=showsel();
	    	if(out=='') {
	    		alert("<s:text name='common.title.alertmsgsendob'/>");
	    		return false;
	    	}
	    	$("#comStrSel").val(out);
	    	</s:if>
	    	if ('<s:property value='infoSend.id'/>' == '')
				document.formname.action = "<%=path%>/info/saveInfoSend.action";
			else
				document.formname.action = "<%=path%>/info/updateInfoSend.action";
			document.formname.submit();
		}
		function setTiming(bool,value){
			if(bool){ 
				$("#timingId").hide();
				$("#submitId").show();
				$("#send").val(value);
			}
			else {
				$("#timingId").show();
				$("#submitId").hide();
				$("#send").val(value);
			}
		}
		
		function searchHouseTip(){
			$("#list_click").show();
		}
		$(function() {
    		$("#remark").textbox({
        		maxLength: 1000
    		}); 
		});
		
		$("#showMsg").html("");
	    <%
	      if(editResult!=null){
	   	   if(editResult) {
	    %>
	    $("#showMsg").append("<s:text name='common.element.action.operationSuccess'/>");
	    <%}else{%>
	    $("#showMsg").append("<s:text name='common.element.action.operationfailed'/>");
	    <%}%>
	    $("#tipMsgDiv").show();
	    <%}%>
	</script>
</body>
</html>
