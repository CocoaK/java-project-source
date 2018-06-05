<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% String path=request.getContextPath(); 
String userType=(String)request.getAttribute("userType");
Boolean editResult=request.getAttribute("editResult")==null?null:(Boolean)request.getAttribute("editResult"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="chargecalmode.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.textbox.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
    </head>

    <body>
        <form action="<s:url action="saveChargeCalMode"/>" method="post" id="formname" name="formname">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                        <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
                        <a href="<%=path%>/charge/queryChargeCalModeList.action"><s:text name="chargecalmode.management.list"/></a>
                        <s:if test="chargeCalMode!=null">
                        <a href="<%=path%>/charge/goToInputChargeCalMode.action" class="all_menu_a"><s:text name="chargecalmode.update"/></a>
                        </s:if><s:else>
                        <a href="<%=path%>/charge/goToInputChargeCalMode.action" class="all_menu_a"><s:text name="chargecalmode.add"/></a>
                        </s:else>
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
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><dfn></dfn><s:text name="chargecalmode.name"/></td>
                                <td><input type="text" size="40" name="chargeCalMode.name" id="chargeCalModName"  onkeyup="setRepeatValidate(this.value)" value="<s:property value='chargeCalMode.name'/>" maxlength="20" class="{required:true,maxlength:20}"/>
                                <input type="hidden" size="40" name="chargeCalMode.id" value="<s:property value='chargeCalMode.id'/>"/>
                                <input type="hidden" size="40" name="chargeCalMode.districtId" value="<s:property value='chargeCalMode.districtId'/>"/>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="chargecalmode.remark"/></td>
                                <td><textarea name="chargeCalMode.reamrk" id="remark" class="{required:false,maxlength:50}"><s:property value="chargeCalMode.reamrk"/></textarea></td>
                            </tr>                         
                        </tbody>
                    </table>
                    <s:if test="chargeCalMode!=null">
                    <div class="all_tab_butb"><input type="button" id="buttonButtonId" value="<s:text name="common.element.action.confirm.update"/>" onclick="savedata();"/></div>
                    </s:if><s:else>
                    <div class="all_tab_butb"><input type="button" id="buttonButtonId" value="<s:text name="common.element.action.add"/>" onclick="savedata();"/></div>
                    </s:else>
                </div>
            </div>
        </div>
        </form>
        <jsp:include  page="../common/confirm_tip.jsp"/>
        <script type="text/javascript">
        $(function() {
    		$("#remark").textbox({
        		maxLength: 50
    		}); 
		});
			$(document).ready(function() {
				$("#formname").validate();
				$("#buttonButtonId").click(function(){
				    if($("#formname").valid()){
				     $("#formname").submit();
				 }
				});
			});
	   </script>
        	<script type="text/javascript">
        	function setRepeatValidate(name){
        		$("#chargeCalModName").removeAttr("repeatValidate");
        		<s:if test="chargeCalMode!=null">
        			if(name!='<s:property value='chargeCalMode.name' escape='false'/>') addRepeatValidate();
            	</s:if>
            	<s:else>
            		addRepeatValidate();
            	</s:else>
        	}
        	function addRepeatValidate(){
        		$("#chargeCalModName").attr("repeatValidate","<%=path%>/charge/getChargeCalModeName.action");
        	}
			function savedata() {
				    	if ('<s:property value='chargeCalMode.id'/>' == '')
							document.formname.action = "<%=path%>/charge/saveChargeCalMode.action";
						else
							document.formname.action = "<%=path%>/charge/updateChargeCalMode.action";
			}
			$(".all_menu a").click(function(){
				$(".all_menu a").attr('class' , '');
				$(this).attr('class' , 'all_menu_a');
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
