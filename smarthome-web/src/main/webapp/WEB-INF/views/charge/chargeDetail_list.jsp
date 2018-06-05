<%@page import="com.biencloud.smarthome.web.login.vo.LoginVO"%>
<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@page import="com.biencloud.smarthome.web.charge.vo.ChargeDetailVO"%>
<%@page import="com.biencloud.smarthome.web.common.action.ActionUtils"%>
<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String path=request.getContextPath(); Boolean editResult=request.getAttribute("editResult")==null?null:(Boolean)request.getAttribute("editResult"); %>
<% request.setAttribute("FeeFlag", ChargeDetailVO.FeeStatusYES);
String userType=(String)request.getAttribute("userType");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="chargedetail.management"/></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/dtree.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/validateUtil.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.textbox.js'/>"></script>
</head>

<body>
	<form action="<s:url action="queryChargeDetailList"/>" method="post" name="searchForm" id="searchForm">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                    	<% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
                    	<a href="<%=path%>/charge/queryChargeCalModeList.action"><s:text name="chargecalmode.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeCalMode.action"><s:text name="chargecalmode.add"/></a>
                        <a href="<%=path%>/charge/queryChargeTypeList.action"><s:text name="chargetype.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeType.action"><s:text name="chargetype.add"/></a>
                        <a href="<%=path%>/charge/queryChargeDataList.action"><s:text name="chargedata.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeData.action"><s:text name="chargedata.add"/></a>
                        <%} %>
                        <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)||Constants.LOGIN_USER_TYPE_OWNER.equals(userType)){ %>
                        <a href="<%=path%>/charge/queryChargeDetailList.action" class="all_menu_a"><s:text name="chargedetail.management"/></a>
                         <%} %>
                         <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
                        <a href="<%=path%>/charge/statisticsCharge.action"><s:text name="chargestatistics.management.list"/></a>
                      <%} %>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top"> 
                        	<label><s:text name="chargedetail.chargestatus"/></label>
                            <div class="select_div">
                            <select id="chargeDetail.chargeStatus" name="chargeDetail.chargeStatus">
                            	<option value=""><s:text name="common.title.all"/></option>
                            	<option value="<%=ChargeDetailVO.FeeStatusYES%>"><s:text name="common.charge.yesfee"/></option>
                            	<option value="<%=ChargeDetailVO.FeeStatusNO%>"><s:text name="common.charge.nofee"/></option>
                            </select>
                            </div>
                        	<label><s:text name="chargedata.chargeime"/></label>
		                    <input type="text" onclick="SelectDate(this,'yyyy-MM',0,-150)" readonly="readonly" class="date_input time_input" name="chargeDetail.chargeData.chargeTime" id="chargeDetail.chargeData.chargeTime" value="<s:property value="chargeDetail.chargeData.chargeTime"/>"/>                           
                            <label><s:text name="chargedetail.chargeime"/></label>
		                    <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="chargeDetail.chargeStartTime" id="chargeDetail.chargeStartTime" value="<s:property value="chargeDetail.chargeStartTime"/>"/>
		                    <span><s:text name="common.element.title.to"/></span>
		                    <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="chargeDetail.chargeEndTime" id="chargeDetail.chargeEndTime" value="<s:property value="chargeDetail.chargeEndTime"/>"/>
                            <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
                            <div class="click_div"><strong class="screening_show search_but"><s:text name="common.element.action.moreFilter"/></strong>
		                    <ul class="screening_body">
			                    <li>
		                            <label class="all_border_right"><s:text name="chargedata.homename"/></label>
                           		 <input type="text" name="chargeDetail.chargeData.regionBuildingInfo.name" id="chargeDetail.chargeData.regionBuildingInfo.name" value="${chargeDetail.chargeData.regionBuildingInfo.name}" class="all_tab_top_input" maxlength="4"/>
			                    </li>
			                    <li>
			                    <label class="all_border_right"><s:text name="chargedata.roomname"/></label>
                            <input type="text" name="chargeDetail.chargeData.cellHouseholdInfo.name" id="chargeDetail.chargeData.cellHouseholdInfo.name" value="${chargeDetail.chargeData.cellHouseholdInfo.name}" class="all_tab_top_input" maxlength="10"/>
			                    </li>
			                    <li>
			                    <label class="all_border_right"><s:text name="chargedata.ownername"/></label>
                            <input type="text" name="chargeDetail.chargeData.ownerName" id="chargeDetail.chargeData.ownerName" value="${chargeDetail.chargeData.ownerName}" class="all_tab_top_input" maxlength="10"/>
			                    </li>
			                    <li>
			                        <label><s:text name="chargedata.createtime"/></label>
				                    <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="chargeDetail.chargeData.createStartTime" id="chargeDetail.chargeData.createStartTime" value="<s:date name="chargeDetail.chargeData.createStartTime" format="yyyy-MM-dd HH:mm" />"/>
				                    <span><s:text name="common.element.title.to"/></span>
				                    <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="chargeDetail.chargeData.createEndTime" id="chargeDetail.chargeData.createEndTime" value="<s:date name="chargeDetail.chargeData.createEndTime" format="yyyy-MM-dd HH:mm" />"/>
			                    </li>
		                    </ul>
		                    </div>
		                    <%} %>
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />      
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                	<th><s:text name="chargedata.roomname"/></th>
                                    <th><s:text name="chargedata.ownername"/></th>
                                    <th><s:text name="chargedata.totalmoney"/></th>
                                    <th><s:text name="chargetype.monetaryunit"/></th>
                                    <th><s:text name="chargedata.chargeime"/></th>
                                    <th><s:text name="chargedata.createtime"/></th>
                                     <th><s:text name="chargedetail.printsn"/></th>
                                     <th><s:text name="chargedetail.chargestatus"/></th>
                                     <th><s:text name="chargedetail.chargeime"/></th>
                                     <th><s:text name="chargedetail.infocount"/></th>
                                    <th width="350px"><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="chargeDetail">
                                    <tr>
                                    	<td><s:property value="#chargeDetail.chargeData.cellHouseholdInfo.name"/></td>
                                        <td><s:property value="#chargeDetail.chargeData.ownerName"/></td>
                                        <td>
										<s:property value="#chargeDetail.chargeData.totalMoney"/>
                                        </td>
                                        <td><s:property value="#chargeDetail.chargeData.monetaryUnit"/></td>
                                        <td><s:property value="#chargeDetail.chargeData.chargeTime"/></td>
                                        <td><s:date name="#chargeDetail.chargeData.createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><s:property value="#chargeDetail.printSn"/></td>
                                        <td>
											 <s:if test="#chargeDetail.chargeStatus==#request.FeeFlag"><s:text name="common.charge.yesfee"/></s:if>
											 <s:else><s:text name="common.charge.nofee"/></s:else>
														</td>
                                        <td><s:property value="#chargeDetail.chargeTime"/></td>
                                        <td>
                                        <s:if test="#chargeDetail.infoId==null">
                                        0
                                        </s:if>
                                        <s:else>
                                        <s:property value="#chargeDetail.infoId"/>
                                        </s:else>
                                        </td>
                                        <td >
                                        	<a href="<%=path%>/charge/findChargeDetail.action?requestId=<s:property value='#chargeDetail.id'/>&<%=ActionUtils.SHOWDETAIL%>" class="all_hover_but">
                                                <s:text name="common.element.action.detail"/>
                                            </a>
                                            <a href="<%=path%>/charge/findChargeDetail.action?requestId=<s:property value='#chargeDetail.id'/>&<%=ActionUtils.SHOWDETAIL%>" class="all_hover_but">
                                                <s:text name="common.element.action.print"/>
                                            </a>
                                            <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
                                            <s:if test="#chargeDetail.chargeStatus!=#request.FeeFlag">
                                            <a href="javascript:confirmAction('<%=path%>/charge/updateChargeDetailStatus.action','<s:property value='#chargeDetail.id'/>','<s:text name="chargedetail.alertmsg.confirmfee"/>')" id="updateId" class="all_hover_but">
                                                <s:text name="charge.common.fee"/>
                                            </a>
                                              <a href="javascript:confirmAction('<%=path%>/charge/delChargeDetail.action','<s:property value='#chargeDetail.id'/>','<s:text name="common.confirm.remove"/>')" class="all_hover_but">
                                                <s:text name="common.element.action.remove"/>
                                            </a></s:if>
                                            <s:else>
                                            <a class="all_hover_but opacity"><s:text name="charge.common.fee"/></a>
                                            <a class="all_hover_but opacity"><s:text name="common.element.action.remove"/></a>
                                            </s:else>
                                            <s:if test="#chargeDetail.chargeStatus!=#request.FeeFlag"><a href="javascript:showNotice(<s:property value='#chargeDetail.chargeData.cellHouseholdInfo.id'/>,<s:property value='#chargeDetail.id'/>);" class="all_hover_but">
                                                <s:text name="common.element.action.feenotice"/>
                                            </a></s:if>
                                            <s:else>
                                            <a class="all_hover_but opacity"><s:text name="common.element.action.feenotice"/></a>
                                            </s:else>
                                            <%} %>
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
        <form action="<s:url action="publishChargeInfo"/>" method="post" name="publishForm" id="publishForm"> 
        <div id="list_click" style="display:none;" class="layers">
                	<div>
                    	<p><strong><s:text name="common.element.action.feenotice"/></strong></p>
                        <ul class="all_tbs_checkbox all_tbs_checkboxs">
                            <li>
                            <input type="hidden" id="roomId" name="roomId"/>
                            <input type="hidden" id="id" name="id"/>
                            <textarea id="content" name="content"></textarea></li>
                            
                        <!--     <li class="all_tbsc_box">
                                <input type="checkbox" /><label><s:text name="common.title.phonemsg"/></label>
                                <input type="checkbox" /><label><s:text name="common.title.notice"/></label>
                            </li>
                         -->
                        </ul>
                        <input type="button" value=" <s:text name="common.title.send"/>" class="all_hover_but" onclick="return publishContent();"/>
                        <input type="button" value=" <s:text name="common.element.action.cancel"/>" class="all_hover_but" onclick="hideTip('list_click');;"/>
                    </div>
            	</div>
         </form>    	
	<script>
	$(function() {
		$("#content").textbox({
    		maxLength: 50
		}); 
	});
	optionSelectInt('${chargeDetail.chargeStatus}',"chargeDetail.chargeStatus",3); 
		function searchData(){
			var areaName=$("#areaName").val();
			if(areaName!='') {
				searchArea(areaName);
				return false;
			}else return true;
		}
		function publishContent(){
			var content=$("#content").val();
			if(content=='') {
				alert("<s:text name='charge.common.content.notnull'/>");
				return false;
			}if(content.length>500) {
				alert("<s:text name='charge.common.content.lenth.invalide'/>");
				return false;
			}else {
				$("#publishForm").submit();
				return true;
			}
		}
		function showNotice(roomId,id){
			$("#roomId").val(roomId);
			$("#id").val(id);
			$("#list_click").show();
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
</body>
</html>
