<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@page import="com.biencloud.smarthome.web.requestrepair.vo.RequestRepairVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.biencloud.smarthome.web.common.action.ActionUtils"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% String path=request.getContextPath(); Boolean showDetail=request.getAttribute(ActionUtils.SHOWDETAIL)==null?false:(Boolean)request.getAttribute(ActionUtils.SHOWDETAIL); %>
<%
boolean isTree=request.getAttribute("treeList")==null?false:true;
request.setAttribute("isTree",isTree);
request.setAttribute("LOGINUSERTYPEOWNER",Constants.LOGIN_USER_TYPE_OWNER);
Boolean editResult=request.getAttribute("editResult")==null?null:(Boolean)request.getAttribute("editResult"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="requestrepair.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.textbox.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
    </head>

    <body>
        <form action="<s:url action="saveRequestRepair"/>" method="post" id="formname" name="formname">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<%=path%>/requestRepair/queryRequestRepairList.action"><s:text name="requestrepair.management.list"/></a>
                    <s:if test="#request.LOGINUSERTYPEOWNER == #request.userType"> 
                    <s:if test="requestRepair!=null">
                        <a href="<%=path%>/requestRepair/goToInputRequestRepair.action" class="all_menu_a"><s:text name="requestrepair.update"/></a>
                        </s:if><s:else>
                        <a href="<%=path%>/requestRepair/goToInputRequestRepair.action" class="all_menu_a"><s:text name="requestrepair.add"/></a>
                        </s:else>
                    </s:if>
                    </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodyq">
                        <tbody>
                            <tr>
                                <td width="120"><dfn></dfn><s:text name="requestrepair.title"/></td>
                                <td >
                                <input type="text" size="40" name="requestRepair.title" value="<s:property value='requestRepair.title'/>" maxlength="100" class="{required:true,maxlength:100}"/>
                                <input type="hidden" size="40" name="requestRepair.id" value="<s:property value='requestRepair.id'/>"/>
                                <input type="hidden" size="40" name="requestRepair.status" id="requestRepair.status" value="<s:property value='requestRepair.status'/>"/>
                                <input type="hidden" size="40" name="requestRepair.districtId" value="<s:property value='requestRepair.districtId'/>"/>
                                <input type="hidden" size="40" name="requestRepair.ownerUser.userId" value="<s:property value='requestRepair.ownerUser.userId'/>"/>
                                <input type="hidden" size="40" name="requestRepair.cellHouseholdInfo.id" value="<s:property value='requestRepair.cellHouseholdInfo.id'/>"/>
                                <input type="hidden" size="40" name="requestRepair.requestTime" value="<s:date name="requestRepair.requestTime" format="yyyy-MM-dd HH:mm:ss" />"/>
                                </td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="requestrepair.content"/></td>
                                <td>
                                <textarea name="requestRepair.content" id="remark" class="{required:true,maxlength:1000}"><s:property value="requestRepair.content"/></textarea></td>
                            </tr> 
                            <tr >
                                <td><dfn></dfn><s:text name="requestrepair.phone"/></td>
                                <td>
                                <input type="text" size="40" name="requestRepair.phone" value="<s:property value='requestRepair.phone'/>" maxlength="20" class="{required:true,digits:true,maxlength:20}"/>
                                </td>
                            </tr> 
                        </tbody>
                    </table>
                    <div class="all_tab_butb" id="ButtonId">
                    <span id="submitId"><input type="button" id="submitButtonId" value="<s:text name="common.element.action.submit"/>" onclick="savedata('<%=RequestRepairVO.STATUS_NOPROCESS%>');" /></span>
                    <input type="button" id="buttonButtonId" value="<s:text name="common.element.action.draft"/>" onclick="savedata('<%=RequestRepairVO.STATUS_NOSUMBIT%>');" />
                    </div>
                </div>
            </div>
        </div>
        </form>
       <jsp:include  page="../common/confirm_tip.jsp"/>
     <script type="text/javascript">
     $(function() {
 		$("#remark").textbox({
     		maxLength: 1000
 		}); 
		});
		$(document).ready(function() {
			$("#formname").validate();
			$("#submitButtonId").click(function(){
			    if($("#formname").valid()){
			     $("#formname").submit();
			 }
			});
			$("#buttonButtonId").click(function(){
			    if($("#formname").valid()){
			     $("#formname").submit();
			 }
			});
		});
	 </script>
	<script type="text/javascript">
		function savedata(statusValue) {
	    	document.getElementById("requestRepair.status").value=statusValue;
	    	if ('<s:property value='requestRepair.id'/>' == '')
				document.formname.action = "<%=path%>/requestRepair/saveRequestRepair.action";
			else
				document.formname.action = "<%=path%>/requestRepair/updateRequestRepair.action";
		}
		
		
		<% if(showDetail){%>
		$("#ButtonId").hide();
		$("input").attr("readonly","readonly");
		$("textarea").attr("readonly","readonly");
	<% }%>
	
	
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
