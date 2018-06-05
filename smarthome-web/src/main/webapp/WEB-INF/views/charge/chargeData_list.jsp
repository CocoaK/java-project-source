<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@page import="com.biencloud.smarthome.web.charge.vo.ChargeDataVO"%>
<%@page import="com.biencloud.smarthome.web.common.action.ActionUtils"%>
<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String path=request.getContextPath(); Boolean editResult=request.getAttribute("editResult")==null?null:(Boolean)request.getAttribute("editResult");
Boolean promptError=request.getAttribute("promptError")==null?null:(Boolean)request.getAttribute("promptError");
Boolean isExist=request.getAttribute("isExist")==null?null:(Boolean)request.getAttribute("isExist");
String userType=(String)request.getAttribute("userType");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="chargedata.management"/></title>
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
	<form action="<s:url action="queryChargeDataList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                    	<% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
                    	<a href="<%=path%>/charge/queryChargeCalModeList.action"><s:text name="chargecalmode.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeCalMode.action"><s:text name="chargecalmode.add"/></a>
                        <a href="<%=path%>/charge/queryChargeTypeList.action"><s:text name="chargetype.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeType.action"><s:text name="chargetype.add"/></a>
                        <a href="<%=path%>/charge/queryChargeDataList.action" class="all_menu_a"><s:text name="chargedata.management.list"/></a>
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
                        	<label><s:text name="chargedata.isproductdetail"/></label>
                            <div class="select_div">
                            <select id="chargeData.isproductDetail" name="chargeData.isproductDetail">
                            	<option value=""><s:text name="common.title.all"/></option>
                            	<option value="<%=ChargeDataVO.YES%>"><s:text name="common.charge.yes"/></option>
                            	<option value="<%=ChargeDataVO.NO%>"><s:text name="common.charge.no"/></option>
                            </select>
                            </div>                        
                            <label><s:text name="chargedata.chargeime"/></label>
		                    <input type="text" onclick="SelectDate(this,'yyyy-MM',0,-150)" readonly="readonly" class="date_input time_input" name="chargeData.chargeTime" id="chargeData.chargeTime" value="<s:property value="chargeData.chargeTime"/>"/>
                             <label><s:text name="chargedata.createtime"/></label>
				                    <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="chargeData.createStartTime" id="chargeData.createStartTime" value="<s:date name="chargeData.createStartTime" format="yyyy-MM-dd HH:mm" />"/>
				                    <span><s:text name="common.element.title.to"/></span>
				                    <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="chargeData.createEndTime" id="chargeData.createEndTime" value="<s:date name="chargeData.createEndTime" format="yyyy-MM-dd HH:mm" />"/>
                            <div class="click_div"><strong class="screening_show search_but"><s:text name="common.element.action.moreFilter"/></strong>
		                    <ul class="screening_body">
			                    <li>
		                          <label class="all_border_right"><s:text name="chargedata.homename"/></label>
                            <input type="text" name="chargeData.regionBuildingInfo.name" id="chargeData.regionBuildingInfo.name" value="${chargeData.regionBuildingInfo.name}" class="all_tab_top_input" maxlength="10"/>
			                    </li>
			                    <li>
			                    <label class="all_border_right"><s:text name="chargedata.roomname"/></label>
                            <input type="text" name="chargeData.cellHouseholdInfo.name" id="chargeData.cellHouseholdInfo.name" value="${chargeData.cellHouseholdInfo.name}" class="all_tab_top_input" maxlength="10"/>
			                    </li>
			                    <li>
			                    <label class="all_border_right"><s:text name="chargedata.ownername"/></label>
                            <input type="text" name="chargeData.ownerName" id="chargeData.ownerName" value="${chargeData.ownerName}" class="all_tab_top_input" maxlength="10"/>
			                    </li>
		                    </ul>
		                    </div>
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />      
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="chargedata.roomname"/></th>
                                    <th><s:text name="chargedata.ownername"/></th>
                                    <th><s:text name="chargedata.chargeime"/></th>
                                    <th><s:text name="chargedata.isproductdetail"/></th>
                                    <th><s:text name="chargedata.createtime"/></th>
                                    <th width="200px"><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="chargeData">
                                    <tr>
                                    	<td><s:property value="#chargeData.cellHouseholdInfo.name"/></td>
                                        <td><s:property value="#chargeData.ownerName"/></td>
                                        <td><s:property value="#chargeData.chargeTime"/></td>
                                        <td>
                                        <s:if test="#chargeData.isproductDetail==#request.GeneratorDetailYes"><s:text name="common.charge.yes"/></s:if>
                                        <s:else><s:text name="common.charge.no"/></s:else>
                                        </td> 
                                        <td><s:date name="#chargeData.createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td >
                                        	<a href="<%=path%>/charge/findChargeData.action?requestId=<s:property value='#chargeData.id'/>&<%=ActionUtils.SHOWDETAIL%>" class="all_hover_but">
                                                <s:text name="common.element.action.detail"/>
                                            </a>
                                            <s:if test="#chargeData.isproductDetail!=#request.GeneratorDetailYes">
                                            <a href="<%=path%>/charge/findChargeData.action?requestId=<s:property value='#chargeData.id'/>" class="all_hover_but">
                                                <s:text name="common.element.action.update"/>
                                            </a>
                                            <a href="javascript:confirmAction('<%=path%>/charge/delChargeData.action','<s:property value='#chargeData.id'/>','<s:text name="common.confirm.remove"/>')" class="all_hover_but">
                                                <s:text name="common.element.action.remove"/>
                                            </a>
                                            </s:if>
                                            <s:else>
                                            <a class="all_hover_but opacity"><s:text name="common.element.action.update"/></a>
                                            <a class="all_hover_but opacity"><s:text name="common.element.action.remove"/></a>
                                            </s:else>
                                        </td>
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <s:include value="/views/common/paging.jsp"/>  
                        <s:if test="page.totalCount>0">
                        <div class="all_tab_butb"><input type="button" value="<s:text name="common.charge.generatorchargedetail"/>" onclick="goToUrl();"/></div>   
                        </s:if>
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
	optionSelectInt('${chargeData.isproductDetail}',"chargeData.isproductDetail",3); 
		function goToUrl(){
	    	document.location.href="<%=path%>/charge/saveChargeDetail.action";
	    }
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
        $("#ConfirmButtonId").attr("href","javascript:$('form')[0].submit();");
        $("#tipMsgDiv").show();
        <%}%>
        
        <% if(promptError!=null){
        	   if(promptError) {
         %>
         $("#showMsg").append("<s:text name='chargedata.title.notype'/>");
         <%}%>
         $("#ConfirmButtonId").attr("href","javascript:$('form')[0].submit();");
         $("#tipMsgDiv").show();
         <%}%>
         
         <% if(isExist!=null){
      	   if(isExist) {
       %>
       $("#showMsg").append("<s:text name='chargedata.alermsg.chargedataisexist'/>");
       <%}%>
       $("#ConfirmButtonId").attr("href","javascript:$('form')[0].submit();");
       $("#tipMsgDiv").show();
       <%}%>
	</script>
</body>
</html>
