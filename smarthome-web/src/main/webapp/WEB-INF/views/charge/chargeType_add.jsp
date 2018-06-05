<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@page import="com.sun.xml.txw2.Document"%>
<%@page import="com.biencloud.smarthome.web.charge.vo.ChargeMonetaryUnitVO"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% String path=request.getContextPath();  String userType=(String)request.getAttribute("userType");
Boolean editResult=request.getAttribute("editResult")==null?null:(Boolean)request.getAttribute("editResult"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="chargetype.add" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.textbox.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
    </head>

    <body>
        <form action="<s:url action="saveChargeType"/>" method="post" id="formname" name="formname">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    	<% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
                    	<a href="<%=path%>/charge/queryChargeCalModeList.action"><s:text name="chargecalmode.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeCalMode.action"><s:text name="chargecalmode.add"/></a>
                        <a href="<%=path%>/charge/queryChargeTypeList.action"><s:text name="chargetype.management.list"/></a>
                        <s:if test="chargeType!=null&&chargeType.id!=null">
                        <a href="<%=path%>/charge/goToInputChargeType.action" class="all_menu_a"><s:text name="chargetype.update"/></a>
                        </s:if><s:else>
                        <a href="<%=path%>/charge/goToInputChargeType.action" class="all_menu_a"><s:text name="chargetype.add"/></a>
                        </s:else>
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
	                                <td width="120"><dfn></dfn><s:text name="chargetype.name"/></td>
	                                <td><input type="text" size="40" name="chargeType.name" onkeyup="setRepeatValidate(this.value)" id="chargeTypeName" value="<s:property value='chargeType.name'/>" maxlength="20" class="{required:true,maxlength:20}"/>
	                                <input type="hidden" size="40" name="chargeType.id" value="<s:property value='chargeType.id'/>"/>
	                                <input type="hidden" size="40" name="chargeType.paUser.userId" value="<s:property value='chargeType.paUser.userId'/>"/>
	                                <input type="hidden" size="40" name="chargeType.housingDistrictInfo.id" value="<s:property value='chargeType.housingDistrictInfo.id'/>"/>
	                                </td>
	                            </tr>
	                            <tr>
	                                <td><dfn></dfn><s:text name="chargetype.calmode"/></td>
	                                <td> <s:select list="chargeCalModeList" theme="simple" listKey="id" listValue="name" name="chargeType.chargeCalMode.id"></s:select></td>
	                            </tr>  
	                            <tr>
	                                <td><dfn></dfn><s:text name="chargetype.standard"/></td>
	                                <td class="up_imgs">
	                                <input name="chargeType.standard" class="{required:true,num:[1,15,2]}" value="<s:property value="chargeType.standard"/>" maxlength="20"/>
	                                <s:select list="chargeMonetaryUnitList" theme="simple" listKey="id" listValue="codeName" name="chargeType.chargeMonetaryUnit.id" id="chargeType.chargeMonetaryUnit.id" disabled="false"></s:select>
	                                <select name="chargeType.chargeCalUnit.id" class="{required:true}">
				                        <s:iterator value="chargeCalUnitList" var="at">
				                            <!-- <option value=""><s:text name="common.element.select.default"/></option> -->
				                            <option value="<s:property value='#at.id'/>" <s:if test="chargeType.chargeCalUnit.id== #at.id">selected</s:if>>
				                                <s:property value="#at.name" />
				                            </option>
				                        </s:iterator>
				                    </select>
				                    <p>
                                        <i><s:text name='common.title.number.msg'><s:param>13</s:param><s:param>2</s:param></s:text></i>
                                    </p>
	                                <input name="chargeMonetaryUnitId" id="chargeMonetaryUnitId" type="hidden" value="<s:property value="chargeType.chargeMonetaryUnit.id"/>"/>
	                                <%
	                                ChargeMonetaryUnitVO unitVo=request.getAttribute("ChargeMonetaryUnit")==null?null:(ChargeMonetaryUnitVO)request.getAttribute("ChargeMonetaryUnit");
	                                Long total=request.getAttribute("total")==null?null:(Long)request.getAttribute("total");
	                                if(total!=null){
	                                %>
	                                <font color="#ff0000">  <s:text name="charge.common.currentexit"/><%=total%><s:text name="charge.common.itemchargeprojectused"/><%=unitVo.getCodeName()%>,<s:text name="charge.common.donotchange"/></font>
	                                <input name="chargeType.chargeMonetaryUnit.id" id="chargeType.chargeMonetaryUnit.id" type="hidden" type="hidden" value="<%=unitVo.getId()%>"/>
	                                <%} %>
	                                </td>
	                            </tr>  
	                            <tr>
	                                <td><s:text name="chargetype.remark"/></td>
	                                <td><textarea name="chargeType.remark" id="remark" class="{required:false,maxlength:50}"><s:property value="chargeType.remark"/></textarea></td>
	                            </tr>                         
	                        </tbody>
	                    </table>
	                    <s:if test="chargeType!=null&&chargeType.id!=null">
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
			  $("#formname00").validate({
	                rules: {
	                	"chargeType.name": {
	                        required: true,
	                        remote: {
	                            url: "<%=path%>/charge/getName.action",
	                            async: false,
	                            type: "POST", 
	                            data: {                  
	                            	chargeTypeName: function () {
	                                    return $("#chargeTypeName").val();
	                                }
	                            }
	                        }
	                    },
	                },
	                messages: {
	                	"chargeType.name": {
	                        remote: "exist"
	                    }
	                },
	        		onkeyup: false

	            });
	   </script>
        	<script type="text/javascript">
			function savedata() {
				    	if ('<s:property value='chargeType.id'/>' == '')
							document.formname.action = "<%=path%>/charge/saveChargeType.action";
						else
							document.formname.action = "<%=path%>/charge/updateChargeType.action";
			}
			</script>
			
			<script type="text/javascript">
			function setRepeatValidate(name){
        		$("#chargeTypeName").removeAttr("repeatValidate");
        		<s:if test="chargeType!=null">
        			if(name!='<s:property value='chargeType.name' escape='false'/>') addRepeatValidate();
            	</s:if>
            	<s:else>
            		addRepeatValidate();
            	</s:else>
        	}
        	function addRepeatValidate(){
        		$("#chargeTypeName").attr("repeatValidate","<%=path%>/charge/getName.action");
        	}
				function optionSelectInt(backValue, targetId,maxOptionsSum) {
					try {
						 var targetSelected=document.getElementById(targetId);
						  if(backValue!=''){
							  for(var i=1;i<maxOptionsSum;i++){
							    if(targetSelected.options[i].value==backValue) targetSelected.options[i].selected=true;
							  }
						  }
					} catch (e) {}
			     }
				if('<%=unitVo%>'!='null'){
					document.getElementById("chargeType.chargeMonetaryUnit.id").disabled=true;
					optionSelectInt('<%=unitVo==null?null:unitVo.getId()%>',"chargeType.chargeMonetaryUnit.id",100);
				}
				if ('<s:property value='chargeType.id'/>' != '') document.getElementById("chargeType.chargeMonetaryUnit.id").disabled=true;
				
				
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
