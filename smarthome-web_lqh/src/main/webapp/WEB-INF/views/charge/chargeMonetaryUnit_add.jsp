<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% String path=request.getContextPath(); Boolean editResult=request.getAttribute("editResult")==null?null:(Boolean)request.getAttribute("editResult");  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="chargemonetaryunit.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.textbox.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
    </head>

    <body>
        <form action="<s:url action="saveChargeMonetaryUnit"/>" method="post" id="formname" name="formname">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                   		 <a href="<%=path%>/charge/queryChargeMonetaryUnitList.action"><s:text name="chargemonetaryunit.management.list"/></a>
                    	<s:if test="chargeMonetaryUnit!=null">
                        <a href="<%=path%>/charge/goToInputChargeMonetaryUnit.action" class="all_menu_a"><s:text name="chargemonetaryunit.update"/></a>
                        </s:if><s:else>
                        <a href="<%=path%>/charge/goToInputChargeMonetaryUnit.action" class="all_menu_a"><s:text name="chargemonetaryunit.add"/></a>
                        </s:else>
                        <a href="<%=path%>/charge/queryChargeCalUnitList.action"><s:text name="chargecalunit.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeCalUnit.action"><s:text name="chargecalunit.add"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><dfn></dfn><s:text name="chargemonetaryunit.monetaryename"/></td>
                                <td><input type="text" size="40" name="chargeMonetaryUnit.codeName" id="unitCodeName" onkeyup="setRepeatValidate(this.value)" value="<s:property value='chargeMonetaryUnit.codeName'/>" maxlength="10" class="{required:true,maxlength:10}"/>
                                <input type="hidden" size="40" name="chargeMonetaryUnit.id" value="<s:property value='chargeMonetaryUnit.id'/>"/>
                                </td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="chargemonetaryunit.monetarycode"/></td>
                                <td><input type="text" size="40" name="chargeMonetaryUnit.monetaryCode" value="<s:property value="chargeMonetaryUnit.monetaryCode"/>" maxlength="20" class="{required:true,maxlength:20}" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="chargemonetaryunit.remark"/></td>
                                <td><textarea name="chargeMonetaryUnit.remark" id="remark" class="{required:false,maxlength:50}"><s:property value="chargeMonetaryUnit.remark"/></textarea></td>
                            </tr>                         
                        </tbody>
                    </table>
                    <s:if test="chargeMonetaryUnit!=null">
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
        		$("#unitCodeName").removeAttr("repeatValidate");
        		<s:if test="chargeMonetaryUnit!=null">
        			if(name!='<s:property value='chargeMonetaryUnit.codeName' escape='false'/>') addRepeatValidate();
            	</s:if>
            	<s:else>
            		addRepeatValidate();
            	</s:else>
        	}
        	function addRepeatValidate(){
        		$("#unitCodeName").attr("repeatValidate","<%=path%>/charge/getChargeMonetaryUnitName.action");
        	}
			function savedata() {
				    	if ('<s:property value='chargeMonetaryUnit.id'/>' == '')
							document.formname.action = "<%=path%>/charge/saveChargeMonetaryUnit.action";
						else
							document.formname.action = "<%=path%>/charge/updateChargeMonetaryUnit.action";
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
		    $("#tipMsgDiv").show();
		    <%}%>
			</script>
    </body>
</html>
