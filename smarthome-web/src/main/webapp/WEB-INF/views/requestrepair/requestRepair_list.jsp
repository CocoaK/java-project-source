<%@page import="com.biencloud.smarthome.web.requestrepair.vo.RequestRepairVO"%>
<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@page import="com.biencloud.smarthome.web.info.vo.InfoReceiverVO"%>
<%@page import="com.biencloud.smarthome.web.charge.vo.ChargeDataVO"%>
<%@page import="com.biencloud.smarthome.web.common.action.ActionUtils"%>
<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String path=request.getContextPath(); Boolean editResult=request.getAttribute("editResult")==null?null:(Boolean)request.getAttribute("editResult"); %>
<% 
request.setAttribute("STATUSNOPROCESS",RequestRepairVO.STATUS_NOPROCESS.toString());
request.setAttribute("STATUSNOSUMBIT",RequestRepairVO.STATUS_NOSUMBIT.toString());
request.setAttribute("STATUSYESPROCESS",RequestRepairVO.STATUS_YESPROCESS.toString());
request.setAttribute("LOGINUSERTYPEOWNER",Constants.LOGIN_USER_TYPE_OWNER);
request.setAttribute("LOGINUSERTYPEPAUSER",Constants.LOGIN_USER_TYPE_PAUSER);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="requestrepair.management"/></title>
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
	<form action="<s:url action="queryRequestRepairList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                    <a href="<%=path%>/requestRepair/queryRequestRepairList.action" class="all_menu_a"><s:text name="requestrepair.management.list"/></a>
                    <s:if test="#request.LOGINUSERTYPEOWNER == #request.userType"> <a href="<%=path%>/requestRepair/goToInputRequestRepair.action"><s:text name="requestrepair.add"/></a></s:if>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                            <label><s:text name="requestrepair.title"/></label>
                             <input type="text" name="requestRepair.title" id="requestRepair.title" value="${requestRepair.title}" class="all_tab_top_input" maxlength="100"/>
	                        <label><s:text name="requestrepair.status"/></label>   
                            <div class="select_div">
                            <select id="requestRepair.status" name="requestRepair.status">
                            <option value=""><s:text name="common.title.all"/></option>
                            	<option value="<%=RequestRepairVO.STATUS_NOPROCESS%>" <s:if test="requestRepair.status == #request.STATUSNOPROCESS">selected</s:if>><s:text name="requestrepair.statusno"/></option>
                            	<s:if test="#request.LOGINUSERTYPEPAUSER != #session.login.userType"><option value="<%=RequestRepairVO.STATUS_NOSUMBIT%>" <s:if test="requestRepair.status == #request.STATUSNOSUMBIT">selected</s:if>><s:text name="requestrepair.statusnosumbit"/></option></s:if>
                            	<option value="<%=RequestRepairVO.STATUS_YESPROCESS%>" <s:if test="requestRepair.status == #request.STATUSYESPROCESS">selected</s:if>><s:text name="requestrepair.statusyes"/></option>
                            </select> 
                            </div>
                            <div class="click_div"><strong class="screening_show search_but"><s:text name="common.element.action.moreFilter"/></strong>
		                    <ul class="screening_body">
			                    <li>
			                        <label><s:text name="requestrepair.requesttime"/></label>
		                            <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="requestRepair.requestStartTime" id="requestRepair.requestStartTime" value="<s:date name="requestRepair.requestStartTime" format="yyyy-MM-dd HH:mm" />"/>
		                            <span><s:text name="common.element.title.to"/></span>
		                            <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="requestRepair.requestEndTime" id="requestRepair.requestEndTime" value="<s:date name="requestRepair.requestEndTime" format="yyyy-MM-dd HH:mm" />"/>
			                    </li>
			                    <li>
			                        <label><s:text name="requestrepair.repairtime"/></label>
		                            <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="requestRepair.repairStartTime" id="requestRepair.repairStartTime" value="<s:date name="requestRepair.repairStartTime" format="yyyy-MM-dd HH:mm" />"/>
		                           <span><s:text name="common.element.title.to"/></span>
		                            <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="requestRepair.repairEndTime" id="requestRepair.repairEndTime" value="<s:date name="requestRepair.repairEndTime" format="yyyy-MM-dd HH:mm" />"/>
			                    </li>
		                    </ul>
		                    </div>
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />      
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="requestrepair.title"/></th>
                                    <th><s:text name="requestrepair.status"/></th>
                                    <th><s:text name="requestrepair.requesttime"/></th>
                                    <th><s:text name="requestrepair.roomid"/></th>
                                    <th><s:text name="requestrepair.ownerid"/></th>
                                    <th><s:text name="requestrepair.repairtime"/></th>
                                    <th><s:text name="requestrepair.pauserid"/></th>
                                    <th width="300px"><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="requestRepair">
                                    <tr>
                                        <td>
                                        <div class="all_tb_content">
                                        <a href="<%=path%>/requestRepair/findRequestRepair.action?requestId=<s:property value='#requestRepair.id'/>&<%=ActionUtils.SHOWDETAIL%>"><s:property value="#requestRepair.title"/></a>
                                        </div>
                                        </td>
                                        <td>
                                        <s:if test="#requestRepair.status==#request.STATUSNOSUMBIT"><s:text name="requestrepair.statusnosumbit"/></s:if>
                                        <s:elseif test="#requestRepair.status==#request.STATUSNOPROCESS"><s:text name="requestrepair.statusno"/></s:elseif>
                                        <s:elseif test="#requestRepair.status==#request.STATUSYESPROCESS"><s:text name="requestrepair.statusyes"/></s:elseif>
                                        </td> 
                                        <td><s:date name="#requestRepair.requestTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><s:property value="#requestRepair.cellHouseholdInfo.name"/></td>
                                        <td><s:property value="#requestRepair.ownerUser.userName"/></td>
                                        <td><s:date name="#requestRepair.repairTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><s:property value="#requestRepair.paUser.userName"/></td>
                                        <td >
                                        	<s:if test="#request.LOGINUSERTYPEPAUSER != #session.login.userType">
                                            <s:if test="#requestRepair.status==#request.STATUSNOSUMBIT">
                                            <a href="javascript:updateStatus('<%=path%>/requestRepair/updateRequestRepairStatus.action','<s:property value='#requestRepair.id'/>','status','<%=RequestRepairVO.STATUS_NOPROCESS%>')" class="all_hover_but">
	                                                <s:text name="common.element.action.submit"/>
	                                        </a>
                                            <a href="<%=path%>/requestRepair/findRequestRepair.action?requestId=<s:property value='#requestRepair.id'/>" class="all_hover_but">
                                                <s:text name="common.element.action.update"/>
                                            </a>
                                            </s:if>
                                            <s:else>
                                            <a class="all_hover_but opacity"><s:text name="common.element.action.submit"/></a>
                                            <a class="all_hover_but opacity"><s:text name="common.element.action.update"/></a>
                                            </s:else>
                                            <a href="javascript:confirmAction('<%=path%>/requestRepair/delRequestRepair.action','<s:property value='#requestRepair.id'/>','<s:text name="common.confirm.remove"/>')" class="all_hover_but">
                                                <s:text name="common.element.action.remove"/>
                                            </a>
                                            </s:if>
                                            <s:else>
                                            	 <s:if test="#requestRepair.status==#request.STATUSNOPROCESS">
                                            	 	<a href="javascript:updateStatus('<%=path%>/requestRepair/updateRequestRepairStatus.action','<s:property value='#requestRepair.id'/>','status','<%=RequestRepairVO.STATUS_YESPROCESS%>')" class="all_hover_but">
	                                                <s:text name="common.element.action.process"/>
	                                                </a>
                                            	 </s:if>
                                            	 <s:else>
                                          		  <a class="all_hover_but opacity"><s:text name="common.element.action.process"/></a>
                                            </s:else>
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
	<script>
	    function goToUrl(){
	    	document.location.href="<%=path%>/requestRepair/saveRequestRepair.action";
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
        //$("#ConfirmButtonId").attr("href","<%=path%>/requestRepair/queryRequestRepairList.action");
        $("#ConfirmButtonId").attr("href","javascript:$('form')[0].submit();");
        $("#tipMsgDiv").show();
        <%}%>
	</script>
</body>
</html>
