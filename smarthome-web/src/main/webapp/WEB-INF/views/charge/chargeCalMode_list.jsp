<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String path=request.getContextPath(); Boolean editResult=request.getAttribute("editResult")==null?null:(Boolean)request.getAttribute("editResult"); %>
<%Boolean delResult=request.getAttribute("delResult")==null?null:(Boolean)request.getAttribute("delResult"); 
String userType=(String)request.getAttribute("userType");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="chargecalmode.management"/></title>
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
	<form action="<s:url action="queryChargeCalModeList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                    	<% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
                    	<a href="<%=path%>/charge/queryChargeCalModeList.action" class="all_menu_a"><s:text name="chargecalmode.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeCalMode.action"><s:text name="chargecalmode.add"/></a>
                        <a href="<%=path%>/charge/queryChargeTypeList.action"><s:text name="chargetype.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeType.action"><s:text name="chargetype.add"/></a>
                        <a href="<%=path%>/charge/queryChargeDataList.action"><s:text name="chargedata.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeData.action"><s:text name="chargedata.add"/></a>
                        <%} %>
                        <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)||Constants.LOGIN_USER_TYPE_OWNER.equals(userType)){ %>
                        <a href="<%=path%>/charge/queryChargeDetailList.action"><s:text name="chargedetail.management"/></a>
                         <%} %>
                         <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
                        <a href="<%=path%>/charge/statisticsCharge.action"><s:text name="chargestatistics.management.list"/></a>
                      	<%} %>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                            <label class="all_border_right"><s:text name="chargecalmode.name"/></label>
                            <input type="text" name="chargeCalMode.name" id="chargeCalMode.name" value="${chargeCalMode.name}" class="all_tab_top_input" maxlength="20"/>
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="chargecalmode.name"/></th>
                                    <th><s:text name="chargecalmode.remark"/></th>
                                    <th width="10%"><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="chargeCalMode">
                                    <tr>
                                        <td><s:property value="#chargeCalMode.name"/></td>
                                        <td><s:property value="#chargeCalMode.reamrk"/></td>
                                        <td>
                                            <a href="<%=path%>/charge/findChargeCalMode.action?requestId=<s:property value='#chargeCalMode.id'/>" class="all_hover_but">
                                                <s:text name="common.element.action.update"/>
                                            </a>
                                            <a href="javascript:confirmAction('<%=path%>/charge/delChargeCalMode.action','<s:property value='#chargeCalMode.id'/>','<s:text name="common.confirm.remove"/>')" class="all_hover_but">
                                                <s:text name="common.element.action.remove"/>
                                            </a>
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
                            <li style="padding-top: 10px">
                            <a href="javascript:hideTip('tipMsgDiv');" id="ConfirmButtonId"><s:text name="common.element.action.confirm"/></a>
                            <span id="cancelButonId" style="display: none"><a href="javascript:hideTip('tipMsgDiv');" ><s:text name="common.element.action.cancel"/></a></span>
                            </li>
                        </ul>
                    </div>
                </div>
        </form>
	<script>
		function searchData(){
			var areaName=$("#areaName").val();
			if(areaName!='') {
				searchArea(areaName);
				return false;
			}else return true;
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
        $("#ConfirmButtonId").attr("href","<%=path%>/charge/queryChargeCalModeList.action");
        $("#tipMsgDiv").show();
        <%}%>
        <%
        if(delResult!=null){
     	   if(delResult) {
      %>
      $("#showMsg").append("<s:text name='common.element.action.operationSuccess'/>");
      <%}else{%>
      $("#showMsg").append("<s:text name='common.targetused'/>");
      <%}%>
     $("#ConfirmButtonId").attr("href","javascript:$('form')[0].submit();");
      $("#tipMsgDiv").show();
      <%}%>
	</script>
</body>
</html>
