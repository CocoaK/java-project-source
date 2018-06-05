<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@page import="com.biencloud.smarthome.web.charge.vo.ChargeDataVO"%>
<%@page import="com.biencloud.smarthome.web.charge.vo.ChargeTypeVO"%>
<%@page import="com.biencloud.smarthome.web.common.action.ActionUtils"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% String path=request.getContextPath(); Boolean showDetail=request.getAttribute(ActionUtils.SHOWDETAIL)==null?false:(Boolean)request.getAttribute(ActionUtils.SHOWDETAIL); 
Boolean firstGotoPage=request.getAttribute(ChargeDataVO.FIRSTGOTOPAGE)==null?false:(Boolean)request.getAttribute(ChargeDataVO.FIRSTGOTOPAGE); 
Boolean typeIsEmpty=request.getAttribute("typeIsEmpty")==null?false:(Boolean)request.getAttribute("typeIsEmpty"); 
String userType=(String)request.getAttribute("userType");
request.setAttribute("noFilterOwner","1");
Boolean editResult=request.getAttribute("editResult")==null?null:(Boolean)request.getAttribute("editResult"); 
%>
<% request.setAttribute("YearFlag", ChargeTypeVO.QUARTER[0]); request.setAttribute("MonthFlag", ChargeTypeVO.QUARTER[1]);
request.setAttribute("HalfYearFlag", ChargeTypeVO.QUARTER[2]); request.setAttribute("QuarterFlag", ChargeTypeVO.QUARTER[3]);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="chargedata.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
                    <a href="<%=path%>/charge/queryChargeCalModeList.action"><s:text name="chargecalmode.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeCalMode.action"><s:text name="chargecalmode.add"/></a>
                        <a href="<%=path%>/charge/queryChargeTypeList.action"><s:text name="chargetype.management.list"/></a>
                        <a href="<%=path%>/charge/goToInputChargeType.action"><s:text name="chargetype.add"/></a>
                        <a href="<%=path%>/charge/queryChargeDataList.action"><s:text name="chargedata.management.list"/></a>
                        <% if(showDetail){%>
                        <a class="all_menu_a"><s:text name="chargedata.detail"/></a>
                        <%}else{ %>
                        <s:if test="chargeData!=null">
                        <a href="<%=path%>/charge/goToInputChargeData.action" class="all_menu_a"><s:text name="chargedata.update"/></a>
                        </s:if><s:else>
                        <a href="<%=path%>/charge/goToInputChargeData.action" class="all_menu_a"><s:text name="chargedata.add"/></a>
                        </s:else>
                        <%} %>
                        <%} %>
                        <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)||Constants.LOGIN_USER_TYPE_OWNER.equals(userType)){ %>
                        <a href="<%=path%>/charge/queryChargeDetailList.action"><s:text name="chargedetail.management"/></a>
                         <%} %>
                         <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
                        <a href="<%=path%>/charge/statisticsCharge.action"><s:text name="chargestatistics.management.list"/></a>
                      <%} %>
                </div>
            </div>
            <% if(!firstGotoPage){%>
            <form action="<s:url action="saveChargeData"/>" method="post" id="formname" name="formname">
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodyq">
                        <tbody>
                            <tr>
                                <td><dfn></dfn><s:text name="chargedata.roomname"/></td>
                                <td>
                                <s:if test="chargeTypeList==null||chargeTypeList.isEmpty()">
                                <input type="hidden"  name="chargeData.cellHouseholdInfo.id" id="chargeData.cellHouseholdInfo.id" value="<s:property value='chargeData.cellHouseholdInfo.id'/>"/>
                                 <input type="hidden"  name="chargeData.regionBuildingInfo.id" id="chargeData.regionBuildingInfo.id"  value="<s:property value='chargeData.regionBuildingInfo.id'/>"/>
                                 <input type="text"  readonly="readonly" id="roomTextDetail"  value="<s:property value='chargeData.cellHouseholdInfo.name'/>"/>
                                </s:if>
                                <s:else>
                                <input type="hidden"  name="chargeData.cellHouseholdInfo.id" id="chargeData.cellHouseholdInfo.id" value="<%=request.getAttribute("roomId")%>"/>
                                 <input type="hidden"  name="chargeData.regionBuildingInfo.id" id="chargeData.regionBuildingInfo.id"  value="<%=request.getAttribute("homeId")%>"/>
                                 <input type="text"  readonly="readonly" id="roomTextDetail"  value="<%=request.getAttribute("roomText")%>"/>
                                 <a  class="all_hover_buts" href="javascript:searchHouseTip()" ><s:text name='user.select.house.info' /></a>
                                </s:else>
                                </td>
                            </tr> 
                            <tr>
                                <td width="120"><s:text name="chargedata.ownername"/></td>
                                <td>
                                <s:if test="chargeTypeList==null||chargeTypeList.isEmpty()">
                                <input type="text" size="40" name="chargeData.ownerName" id="chargeData.ownerName"  value="<s:property value='chargeData.ownerName'/>" maxlength="10" class="{required:false,maxlength:10}"/>
                                </s:if>
                                <s:else>
                                <input type="text" size="40" name="chargeData.ownerName" id="chargeData.ownerName"  value="<%=request.getAttribute("ownerName")%>" maxlength="10" class="{required:false,maxlength:10}"/>
                                </s:else>
                                <input type="hidden" size="40" name="chargeData.id" value="<s:property value='chargeData.id'/>"/>
                                <input type="hidden" size="40" name="removeIndex" id="removeIndex" value=""/>
                                <input type="hidden" size="40" name="chargeData.isproductDetail" value="<s:property value='chargeData.isproductDetail'/>"/>
                                <input type="hidden" size="40" name="chargeData.createTime" value="<s:date name="chargeData.createTime" format="yyyy-MM-dd HH:mm:ss" />"/>
                                <input type="hidden" size="40" name="chargeData.paUser.userId" value="<s:property value='chargeData.paUser.userId'/>"/>
                                <input type="hidden" size="40" name="chargeData.housingDistrictInfo.id" value="<s:property value='chargeData.housingDistrictInfo.id'/>"/>
                                </td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="chargedata.chargeime"/></td>
                                <td>
                                <input type="text" class="{required:true}" onclick="SelectDate(this,'yyyy-MM',0,-150)" readonly="readonly" class="pbc_input" id="chargeData.chargeTime" name="chargeData.chargeTime" value="<s:property value='chargeData.chargeTime'/>"/>
                                </td>
                            </tr>
                            <!-- 更新 -->
                            <s:if test="chargeTypeList==null||chargeTypeList.isEmpty()">
                            	<input type="hidden" size="40" name="chargeData.monetaryUnit" value="<s:property value='chargeData.monetaryUnit'/>"/>
	                             <s:iterator value="chargeTypeResultList" var="chargeTypeResult" status="sta">
	                                    <tr>
	                                        <td>
	                                        <dfn></dfn><s:property value="#chargeTypeResult.chargeTypeName"/>(<s:property value="#chargeTypeResult.calUnit"/>)</td>
	                                        <td>
	                                        	<div id="TotalIndex<s:property value='#sta.index'/>">
	                                        	<label><s:text name="charge.common.startindex"/>：</label>
	                                        	<input type="text" size="20" class="{required:true,num:[1,15,2],maxlength:20}" maxlength="20" onkeydown="resetEndTotal(<s:property value='#sta.index'/>);" name="chargeTypeResultList[<s:property value='#sta.index'/>].startTotal" id="chargeTypeResultList[<s:property value='#sta.index'/>].startTotal" value="<s:property value='#chargeTypeResult.startTotal'/>"/>
	                                        	<label><s:text name="charge.common.endtotal"/>：</label>
	                                        	<input type="text" size="20" class="{required:true,num:[1,15,2],maxlength:20}" maxlength="20" onblur="proTotal(this.value,<s:property value='#sta.index'/>);" name="chargeTypeResultList[<s:property value='#sta.index'/>].endTotal" id="chargeTypeResultList[<s:property value='#sta.index'/>].endTotal" value="<s:property value='#chargeTypeResult.endTotal'/>"/>
	                                        	<label><s:text name="charge.common.total"/>：</label>
	                                        	<input readonly="readonly"  class="{required:true}" type="text" size="20" name="chargeTypeResultList[<s:property value='#sta.index'/>].actualTotal" id="chargeTypeResultList[<s:property value='#sta.index'/>].actualTotal" value="<s:property value='#chargeTypeResult.actualTotal'/>"/>
												</div>
	                                        	<div id="playMoney<s:property value='#sta.index'/>" style="display: none;"><input type="text" disabled="disabled" value="<s:property value='#chargeTypeResult.standard'/>"/><input type="hidden" size="20" readonly="readonly" name="chargeTypeResultList[<s:property value='#sta.index'/>].playMoney" id="chargeTypeResultList[<s:property value='#sta.index'/>].playMoney" value="<s:property value='#chargeTypeResult.standard'/>"/></div>
	                                        	<div id="yearChecbox<s:property value='#sta.index'/>"  style="display: none;" class="radio_border check_hack"><input type="checkbox" checked="checked" id="isGet<s:property value='#sta.index'/>" onclick="setIsSumbit(<s:property value='#sta.index'/>,this.checked);" id="isGet<s:property value='#sta.index'/>"/><label><s:text name="common.title.isgetchargeplaynow"/></label></div>
	                                        	<s:if test="#request.YearFlag==#chargeTypeResult.calUnit||#request.MonthFlag==#chargeTypeResult.calUnit||#request.HalfYearFlag==#chargeTypeResult.calUnit||#request.QuarterFlag==#chargeTypeResult.calUnit">
	                                        	<script type="text/javascript">
	                                        	 $("#TotalIndex<s:property value='#sta.index'/>").hide();
	                                        	 $("#playMoney<s:property value='#sta.index'/>").show();
	                                        	</script>
		                                        	<s:if test="#request.MonthFlag!=#chargeTypeResult.calUnit">
		                                        		<script type="text/javascript">
			                                        	 $("#yearChecbox<s:property value='#sta.index'/>").show();
		                                        		</script>
		                                        	</s:if>
	                                        	</s:if>
												<input type="hidden" name="chargeTypeResultList[<s:property value='#sta.index'/>].id" value="<s:property value='#chargeTypeResult.id'/>"/>
												<input type="hidden" name="chargeTypeResultList[<s:property value='#sta.index'/>].calUnit" value="<s:property value='#chargeTypeResult.calUnit'/>"/>
												<input type="hidden" name="chargeTypeResultList[<s:property value='#sta.index'/>].chargeDataId" value="<s:property value='#chargeTypeResult.chargeDataId'/>"/>
												<input type="hidden" name="chargeTypeResultList[<s:property value='#sta.index'/>].remark" value="<s:property value='#chargeTypeResult.remark'/>"/>
												<input type="hidden" name="chargeTypeResultList[<s:property value='#sta.index'/>].standard" value="<s:property value='#chargeTypeResult.standard'/>"/>
												<input type="hidden" name="chargeTypeResultList[<s:property value='#sta.index'/>].chargeTypeName" value="<s:property value='#chargeTypeResult.chargeTypeName'/>"/>
												<input type="hidden" name="chargeTypeResultList[<s:property value='#sta.index'/>].calMode" value="<s:property value='#chargeTypeResult.calMode'/>"/>
												</td>
	                                    </tr>
	                                </s:iterator>
                            </s:if>
                            <!-- 保存 -->
                            <s:else>
	                            	<s:iterator value="%{chargeTypeList}" var="chargeType" status="sta">
	                                    <tr>
	                                        <td>
	                                        <s:if test="#sta.index==0">
	                                        	<input type="hidden" size="40" name="chargeData.monetaryUnit" value="<s:property value='#chargeType.chargeMonetaryUnit.codeName'/>"/>
	                                        </s:if>
	                                        <dfn></dfn><s:property value="#chargeType.name"/>(<s:property value="#chargeType.chargeCalUnit.name"/>)</td>
	                                        <td>
	                                        	<div id="TotalIndex<s:property value='#sta.index'/>">
	                                        	<label><s:text name="charge.common.startindex"/>：</label>
	                                        	<input type="text" size="20" class="{required:true,num:[1,15,2],maxlength:20}" maxlength="20" onkeydown="resetEndTotal(<s:property value='#sta.index'/>);" name="chargeTypeResultList[<s:property value='#sta.index'/>].startTotal" id="chargeTypeResultList[<s:property value='#sta.index'/>].startTotal" />
	                                        	<label><s:text name="charge.common.endtotal"/>：</label>
	                                        	<input type="text" size="20" class="{required:true,num:[1,15,2],maxlength:20}" maxlength="20" onblur="proTotal(this.value,<s:property value='#sta.index'/>);" name="chargeTypeResultList[<s:property value='#sta.index'/>].endTotal" id="chargeTypeResultList[<s:property value='#sta.index'/>].endTotal" />
	                                        	<label><s:text name="charge.common.total"/>：</label>
	                                        	<input readonly="readonly" class="{required:true}"  type="text" size="20" name="chargeTypeResultList[<s:property value='#sta.index'/>].actualTotal" id="chargeTypeResultList[<s:property value='#sta.index'/>].actualTotal"/>
	                                        	</div>
	                                        	<div id="playMoney<s:property value='#sta.index'/>" style="display: none;"><input type="text" disabled="disabled" value="<s:property value='#chargeType.standard'/>"/><input type="hidden" size="20" readonly="readonly" name="chargeTypeResultList[<s:property value='#sta.index'/>].playMoney" id="chargeTypeResultList[<s:property value='#sta.index'/>].playMoney" value="<s:property value='#chargeType.standard'/>"/></div>
	                                        	<div id="yearChecbox<s:property value='#sta.index'/>"  style="display: none;" class="radio_border check_hack"><input type="checkbox" id="isGet<s:property value='#sta.index'/>" onclick="setIsSumbit(<s:property value='#sta.index'/>,this.checked);" id="isGet<s:property value='#sta.index'/>"/><label><s:text name="common.title.isgetchargeplaynow"/></label></div>
	                                        	<s:if test="#request.YearFlag==#chargeType.chargeCalUnit.name||#request.MonthFlag==#chargeType.chargeCalUnit.name||#request.HalfYearFlag==#chargeType.chargeCalUnit.name||#request.QuarterFlag==#chargeType.chargeCalUnit.name">
	                                        	<script type="text/javascript">
	                                        	 $("#TotalIndex<s:property value='#sta.index'/>").hide();
	                                        	 $("#playMoney<s:property value='#sta.index'/>").show();
	                                        	</script>
		                                        	<s:if test="#request.MonthFlag!=#chargeType.chargeCalUnit.name">
		                                        		<script type="text/javascript">
			                                        	 $("#yearChecbox<s:property value='#sta.index'/>").show();
				                                        	 var removeIndexValue=$("#removeIndex").val();
				                             				 var newValue=removeIndexValue+","+<s:property value='#sta.index'/>;
			                             					 $("#removeIndex").val(newValue);
		                                        		</script>
		                                        	</s:if>
	                                        	</s:if>
	                                        	<input type="hidden" size="20" name="chargeTypeResultList[<s:property value='#sta.index'/>].calUnit" id="chargeTypeResultList[<s:property value='#sta.index'/>].calUnit" value="<s:property value='#chargeType.chargeCalUnit.name'/>"/>
	                                        	<input type="hidden" size="20" name="chargeTypeResultList[<s:property value='#sta.index'/>].calMode" id="chargeTypeResultList[<s:property value='#sta.index'/>].calMode" value="<s:property value='#chargeType.chargeCalMode.name'/>"/>
	                                        	<input type="hidden" size="20" name="chargeTypeResultList[<s:property value='#sta.index'/>].chargeTypeName" id="chargeTypeResultList[<s:property value='#sta.index'/>].chargeTypeName" value="<s:property value='#chargeType.name'/>"/>
	                                        	<input type="hidden" size="20" name="chargeTypeResultList[<s:property value='#sta.index'/>].standard" id="chargeTypeResultList[<s:property value='#sta.index'/>].standard" value="<s:property value='#chargeType.standard'/>"/>
												</td>
	                                    </tr>
	                                </s:iterator>
                                 </s:else> 
                        </tbody>
                    </table>
                    <s:if test="chargeTypeList==null||chargeTypeList.isEmpty()">
                    <div class="all_tab_butb"><input type="button" id="buttonButtonId" value="<s:text name="common.element.action.confirm.update"/>" onclick="savedata();"/></div>
                    </s:if><s:else>
                    <div class="all_tab_butb"><input type="button" id="buttonButtonId" value="<s:text name="common.element.action.add"/>" onclick="savedata();"/></div>
                    </s:else>
                </div>
            </div>
            </form>
            <% }%>
        </div>
        <jsp:include  page="../common/confirm_tip.jsp"/>
        <jsp:include  page="../common/queryHouse.jsp"/>
        <form action="<s:url action="saveChargeData?getChargeType"/>" method="post" id="getTypeForm" name="getTypeForm">
        <input type="hidden" id="roomText" name="roomText"  value=""/>
        <input type="hidden" id="ownerName" name="ownerName"  value=""/>
        <input type="hidden" id="roomId" name="roomId"  value=""/>
        <input type="hidden" id="homeId" name="homeId"  value=""/>
        </form>
        <script type="text/javascript">
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
			function savedata() {
				    	if ('<s:property value='chargeData.id'/>' == '')
							document.formname.action = "<%=path%>/charge/saveChargeData.action";
						else
							document.formname.action = "<%=path%>/charge/updateChargeData.action";
			}
			function linkFunction(noGet,roomId,roomName,cellId,cellName,buildingId,buildingName,regionName,ownerName){
				document.getElementById("roomId").value=roomId;
				document.getElementById("homeId").value=buildingId;
				document.getElementById("ownerName").value=ownerName;
				var roomText=regionName+buildingName+cellName+roomName;
				$("#roomText").val(roomText);
				document.getTypeForm.submit();
	        }
			function searchHouseTip(){
				$("#list_click").show();
			}
			function proTotal(value,indexValue){
				var endtotal=parseFloat(value);
				var startName="chargeTypeResultList["+indexValue+"].startTotal";
				var endName="chargeTypeResultList["+indexValue+"].endTotal";
				var totalName="chargeTypeResultList["+indexValue+"].actualTotal";
				var startvalue=document.getElementById(startName).value;
				if(startvalue=='') {alert("<s:text name='common.title.pleaseinputfirsttotal'/>");document.getElementById(endName).value='';return false;}
				var starttotal=parseFloat(startvalue);
				if(startvalue>endtotal) {alert("<s:text name='chargedata.alertmsg.startlessend'/>");document.getElementById(endName).value='';return false;}
				if(value!='') document.getElementById(totalName).value=(endtotal-starttotal).toFixed(2);
			}
			function setIsSumbit(indexValue,selected){
				var removeIndexValue=$("#removeIndex").val();
				var indexVa=","+indexValue;
				 if(selected){
					 removeIndexValue=removeIndexValue.replace(indexVa,"");	
					 $("#removeIndex").val(removeIndexValue);
				 }else {
					 var newValue=removeIndexValue+indexVa;
					 $("#removeIndex").val(newValue);
				 }
			}
			function resetEndTotal(indexValue){
				var endName="chargeTypeResultList["+indexValue+"].endTotal";
				document.getElementById(endName).value='';
			}
			<% if(showDetail){%>
				$("#ButtonId").hide();
				$("input").attr("readonly","readonly");
			<% }%>
			<% if(firstGotoPage){%>
			searchHouseTip();
			<% if(typeIsEmpty){%>
				alert("<s:text name='common.title.thisownernochargeproject'/>");
			<% }}%>
			
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
