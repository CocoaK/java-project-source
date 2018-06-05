<%@page import="com.biencloud.smarthome.web.charge.vo.ChargeDetailVO"%>
<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.biencloud.smarthome.web.charge.vo.ChargeTypeVO"%>
<%@page import="com.biencloud.smarthome.web.common.action.ActionUtils"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% String path=request.getContextPath(); Boolean showDetail=request.getAttribute(ActionUtils.SHOWDETAIL)==null?false:(Boolean)request.getAttribute(ActionUtils.SHOWDETAIL); %>
<% request.setAttribute("YearFlag", ChargeTypeVO.QUARTER[0]); request.setAttribute("MonthFlag", ChargeTypeVO.QUARTER[1]);
request.setAttribute("HalfYearFlag", ChargeTypeVO.QUARTER[2]); request.setAttribute("QuarterFlag", ChargeTypeVO.QUARTER[3]);
String userType=(String)request.getAttribute("userType");
request.setAttribute("FeeFlag", ChargeDetailVO.FeeStatusYES);
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
        <jsp:include  page="../common/include_common.jsp"/>
<script type="text/javascript">
	function preview(oper) { 
		if (oper < 10){ 
			bdhtml=window.document.body.innerHTML;
			sprnstr="<!--startprint"+oper+"-->"; 
			eprnstr="<!--endprint"+oper+"-->";
			prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18);
			prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
			window.document.body.innerHTML=prnhtml; 
			window.print(); 
			window.document.body.innerHTML=bdhtml; 
			} else{ 
		window.print(); 
		} 
	} 
</script>
    </head>

    <body>
        <form action="<s:url action="saveChargeDetail"/>" method="post" id="formname" name="formname">
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
                        <a href="<%=path%>/charge/queryChargeDetailList.action"><s:text name="chargedetail.management"/></a>
                        <a  class="all_menu_a"><s:text name="chargedetail.detail"/></a>
                         <%} %>
                         <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
                        <a href="<%=path%>/charge/statisticsCharge.action"><s:text name="chargestatistics.management.list"/></a>
                      <%} %>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                <!--startprint1-->
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="chargedata.ownername"/></td>
                                <td colspan=""><s:property value='chargeDetail.chargeData.ownerName'/></td>
                                <td width="120"><s:text name="chargedata.chargeime"/></td>
                                <td colspan=""><s:property value='chargeDetail.chargeData.chargeTime'/></td>
                            </tr>
                            <tr>
                                <td><s:text name="chargedata.homename"/></td>
                                <td><s:property value="chargeDetail.chargeData.regionBuildingInfo.name"/></td>
                                <td width="100px;"><s:text name="chargedata.roomname"/></td>
                                <td><s:property value="chargeDetail.chargeData.cellHouseholdInfo.name"/></td>
                            </tr> 
	                             <s:iterator value="chargeTypeResultList" var="chargeTypeResult" status="sta">
	                                    <tr>
	                                        <td>
	                                        <s:property value="#chargeTypeResult.chargeTypeName"/>(<s:property value="#chargeTypeResult.calUnit"/>)</td>
	                                        <td colspan="3" class="charges_list">
	                                         
                                 <ul>
                                 <i id="TotalIndex<s:property value='#sta.index'/>">
	                                       <li> <s:text name="charge.common.startindex"/>：
												<s:property value="#chargeTypeResult.startTotal"/>
	                                        	</li>
	                                        <li>	<s:text name="charge.common.endtotal"/>：
												<s:property value="#chargeTypeResult.endTotal"/>
	                                        	</li>
	                                       <li> 	<s:text name="charge.common.total"/>：
												<s:property value="#chargeTypeResult.actualTotal"/>
	                                        	</li>
	                                        	 </i>
	                                        	 <i id="playMoney<s:property value='#sta.index'/>" style="display: none;">
	                                        	<li>
												<s:property value="#chargeTypeResult.standard"/>
	                                        	</li>
	                                        	</i>
	                                        	<li> 	<s:text name="chargetype.standard"/>：
												<s:property value="#chargeTypeResult.standard"/>
	                                        	<s:text name="charge.common.yuan"/>/<s:property value="#chargeTypeResult.calUnit"/>
	                                        	</li>
	                                        	<li> 	<s:text name="charge.common.smallmoney"/>：
												<s:property value="#chargeTypeResult.playMoney"/>
	                                        	</li>
	                                        	   </ul>
	                                        	  
	                                        	<i id="playMoney<s:property value='#sta.index'/>" style="display: none;">
	                                        	<li>
												<s:property value="#chargeTypeResult.standard"/>
	                                        	</li>
	                                        	</i>
	                                        	<s:if test="#request.YearFlag==#chargeTypeResult.calUnit||#request.MonthFlag==#chargeTypeResult.calUnit||#request.HalfYearFlag==#chargeTypeResult.calUnit||#request.QuarterFlag==#chargeTypeResult.calUnit">
	                                        	<script type="text/javascript">
	                                        	 $("#TotalIndex<s:property value='#sta.index'/>").hide();
	                                        	 $("#playMoney<s:property value='#sta.index'/>").show();
	                                        	</script>
	                                        	</s:if>
												</td>
	                                    </tr>
	                                </s:iterator>
	                        <tr>
                                <td><s:text name="chargedata.totalmoney"/></td>
                                <td colspan="3" >
										<s:property value="chargeDetail.chargeData.totalMoney"/>
                                (<s:property value="chargeDetail.chargeData.monetaryUnit"/>)</td>
                            </tr>
                           
                        </tbody>
                        <tfoot>
                    	<tr>
                        	<td colspan="4" class="charges_foot">
                            	<i> <s:text name="common.charge.printDate"/>：<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %></i>
                                <i> <s:text name="common.charge.printName"/>：<s:property value='#session.login.userName'/></i>
                                 <i> 
<s:if test="chargeDetail.chargeStatus==#request.FeeFlag"><s:text name="chargedetail.chargeime"/>：<s:property value="chargeDetail.chargeTime"/></s:if>
											 <s:else><s:text name="common.charge.nofee"/></s:else>
</i>
                                <strong> <s:text name="charge.common.ownerdignature"/>：</strong>
                            </td>
                        </tr>
                    </tfoot>
                    </table>
                    <!--endprint1-->
                    <div class="all_tab_butb"><input type="button" id="buttonButtonId" value="<s:text name="common.element.action.print"/>" onclick="preview(1)" /></div>
                </div>
            </div>
        </div>
        </form>
    </body>
</html>
