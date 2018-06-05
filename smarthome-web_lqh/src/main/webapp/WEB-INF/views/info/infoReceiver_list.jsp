<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@page import="com.biencloud.smarthome.web.info.vo.InfoReceiverVO"%>
<%@page import="com.biencloud.smarthome.web.charge.vo.ChargeDataVO"%>
<%@page import="com.biencloud.smarthome.web.common.action.ActionUtils"%>
<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String path=request.getContextPath(); Boolean editResult=request.getAttribute("editResult")==null?null:(Boolean)request.getAttribute("editResult"); %>
<% request.setAttribute("STATUSNO",InfoReceiverVO.STATUSNO);request.setAttribute("INFOTYPEPERSON",InfoReceiverVO.INFO_TYPE_PERSON);
request.setAttribute("INFOTYPECOMMUNITY",InfoReceiverVO.INFO_TYPE_COMMUNITY);
request.setAttribute("INFOTYPESYSTEM",InfoReceiverVO.INFO_TYPE_SYSTEM);
request.setAttribute("LOGINUSERTYPEPAUSER",Constants.LOGIN_USER_TYPE_PAUSER);
request.setAttribute("LOGINUSERTYPEOWNER",Constants.LOGIN_USER_TYPE_OWNER);
String userType=(String)request.getAttribute("userType");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="inforeceiver.management"/></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/dtree.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/validateUtil.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
</head>

<body>
	<form action="<s:url action="queryInfoReceiverList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                    <a href="<%=path%>/info/queryInfoSendList.action"><s:text name="infosend.management.list"/></a>
                        <a href="<%=path%>/info/goToInputInfoSend.action"><s:text name="infosend.add"/></a>
                        <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)||Constants.LOGIN_USER_TYPE_OWNER.equals(userType)){ %>
                    <a href="<%=path%>/info/queryInfoReceiverList.action" class="all_menu_a"><s:text name="inforeceiver.management.list"/></a>   
                    <%} %>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">    
                        <!--                   
                            <label><s:text name="inforeceiver.status"/></label>
                            <div class="select_div">
                            <select id="infoReceiver.status" name="infoReceiver.status">
                            	<option value=""><s:text name="common.title.all"/></option>
                            	<option value="<%=InfoReceiverVO.STATUSNO%>"><s:text name="inforeceiver.statusno"/></option>
                            	<option value="<%=InfoReceiverVO.STATUSYES%>"><s:text name="inforeceiver.statusyes"/></option>
                            </select>
                            </div> -->      
                            <label><s:text name="infosend.type"/></label>
                            <div class="select_div">
                            <select id="infoReceiver.receiverType" name="infoReceiver.receiverType">
                                <option value=""><s:text name="common.title.all"/></option>
                            	<option value="<%=InfoReceiverVO.INFO_TYPE_PERSON%>" >
                            	<s:text name="info.personinfo"/>
                            	</option>
                            	<s:if test="#request.LoginUserType==#request.LOGINUSERTYPEOWNER">
                            	<option value="<%=InfoReceiverVO.INFO_TYPE_COMMUNITY%>"><s:text name="info.cominfo"/></option> 
                            	</s:if>
                            	<option value="<%=InfoReceiverVO.INFO_TYPE_SYSTEM%>"><s:text name="info.sysinfo"/></option> 
                            </select>
                            </div>
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />      
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                     <th><s:text name="infosend.title"/></th>
                              <!--       <th><s:text name="inforeceiver.status"/></th> -->  
                                    <th><s:text name="infosend.sendtime"/></th>
                                    <th><s:text name="infosend.sendusername"/></th>
                                    <th><s:text name="infosend.type"/></th>
                                     <th width="200px"><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="infoReceiver">
                                    <tr>
                                        <td><s:property value="#infoReceiver.infoSend.title"/></td>
                                        <!-- 
                                        <td>
                                        <s:if test="#request.LoginUserType==#request.LOGINUSERTYPEPAUSER&&#infoReceiver.receiverType==#request.INFOTYPEPERSON"></s:if>
                                        <s:elseif test="#request.LoginUserType==#request.LOGINUSERTYPEOWNER&&#infoReceiver.receiverType==#request.INFOTYPESYSTEM"></s:elseif>
                                        <s:elseif test="#request.LoginUserType==#request.LOGINUSERTYPEOWNER && #infoReceiver.receiverType==#request.INFOTYPEPERSON"></s:elseif>
                                        <s:elseif test="#infoReceiver.status==#request.STATUSNO"><s:text name="inforeceiver.statusno"/></s:elseif>
                                        <s:else><s:text name="inforeceiver.statusyes"/></s:else>
                                        </td> 
                                         -->
                                        <td><s:date name="#infoReceiver.infoSend.sendTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><s:property value="#infoReceiver.infoSend.sendUserName"/></td>
                                        <td>
                                        <s:if test="#infoReceiver.receiverType==#request.INFOTYPEPERSON"><s:text name="info.personinfo"/></s:if>
                                        <s:elseif test="#infoReceiver.receiverType==#request.INFOTYPECOMMUNITY"><s:text name="info.cominfo"/></s:elseif>
                                        <s:else><s:text name="info.sysinfo"/></s:else>
                                        </td>
                                        <td><a href="<%=path%>/info/findInfoReceiver.action?requestId=<s:property value='#infoReceiver.id'/>&<%=ActionUtils.SHOWDETAIL%>" class="all_hover_but">
                                                <s:text name="common.element.action.detail"/>
                                            </a></td>
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
		//optionSelectInt('${infoReceiver.status}',"infoReceiver.status",3);
		optionSelectInt('${infoReceiver.receiverType}',"infoReceiver.receiverType",4);
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
