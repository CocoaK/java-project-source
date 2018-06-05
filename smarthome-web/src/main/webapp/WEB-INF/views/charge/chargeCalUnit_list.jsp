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
<title><s:text name="chargecalunit.management"/></title>
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
	<form action="<s:url action="queryChargeCalUnitList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                    	<a href="<%=path%>/charge/queryChargeMonetaryUnitList.action"><s:text name="chargemonetaryunit.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeMonetaryUnit.action"><s:text name="chargemonetaryunit.add"/></a>
                        <a href="<%=path%>/charge/queryChargeCalUnitList.action" class="all_menu_a"><s:text name="chargecalunit.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeCalUnit.action"><s:text name="chargecalunit.add"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                            <label class="all_border_right"><s:text name="chargecalunit.name"/></label>
                            <input type="text" name="chargeCalUnit.name" id="chargeCalUnit.name" value="${chargeCalUnit.name}" class="all_tab_top_input" maxlength="10"/>
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="chargecalunit.name"/></th>
                                    <th><s:text name="chargecalunit.remark"/></th>
                                    <th width="10%"><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="chargeCalUnit">
                                    <tr>
                                        <td><s:property value="#chargeCalUnit.name"/></td>
                                        <td><s:property value="#chargeCalUnit.reamrk"/></td>
                                        <td>
                                            <a href="<%=path%>/charge/findChargeCalUnit.action?requestId=<s:property value='#chargeCalUnit.id'/>" class="all_hover_but">
                                                <s:text name="common.element.action.update"/>
                                            </a>
                                            <a href="javascript:confirmAction('<%=path%>/charge/delChargeCalUnit.action','<s:property value='#chargeCalUnit.id'/>','<s:text name="common.confirm.remove"/>')" class="all_hover_but">
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
        $("#ConfirmButtonId").attr("href","<%=path%>/charge/queryChargeCalUnitList.action");
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
