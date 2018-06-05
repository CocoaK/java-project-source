<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String path=request.getContextPath(); Boolean editResult=request.getAttribute("editResult")==null?null:(Boolean)request.getAttribute("editResult"); 
String userType=(String)request.getAttribute("userType");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="chargestatistics.management.list"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_clorbox.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.parseQuerystring.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/districtList.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/regionList.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/buildingList.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/houseList.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript">
        var selectAllTitle = "<s:text name='common.title.all'/>";
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
	<form action="<s:url action="statisticsCharge"/>" method="post">    
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
                         <%} %>
                         <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
                        <a href="<%=path%>/charge/statisticsCharge.action" class="all_menu_a"><s:text name="chargestatistics.management.list"/></a>
                      <%} %>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">  
                        	<span><s:text name="chargedata.selectbuillding"/></span>    
                           <div class="select_div select_divs">
                           <strong><s:text name="common.title.district"/></strong>
                           <select id="districtId" name="districtId"></select>
                           <strong class="all_border_right"><s:text name="common.title.region"/></strong>
                            <select id="regionId" name="regionId" regionId="<s:property value='regionId'/>"></select>
                            <strong class="all_border_right"><s:text name="common.title.building"/></strong>
                            <select id="buildingId" name="buildingId" buildingId="<s:property value='buildingId'/>"></select>
                            </div>
                                <label><s:text name="chargedata.chargeime"/></label>
                                 <input type="text" onclick="SelectDate(this,'yyyy-MM',0,-150)" readonly="readonly" value="<s:property value="chargeData.monetaryUnit"/>" class="date_input" id="chargeData.monetaryUnit" name="chargeData.monetaryUnit"/>
                                  <label><s:text name="common.element.title.to"/></label>
                                 <input type="text" onclick="SelectDate(this,'yyyy-MM',0,-150)" readonly="readonly" class="date_input" value="<s:property value="chargeData.chargeTime"/>" id="chargeData.chargeTime" name="chargeData.chargeTime"/>
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />  
                        </div>
                        <!--startprint1-->
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="chargedata.homename"/></th>
                                    <th><s:text name="chargetyperesult.chargetypename"/></th>
                                   <%-- <th><s:text name="chargedata.chargeime"/></th> --%> 
                                    <th><s:text name="chargedata.customercount"/></th>
                                    <th><s:text name="chargedata.feecustomercount"/></th>
                                    <th><s:text name="chargedata.actualstatistics"/></th>
                                    <th><s:text name="chargedata.moneystatistics"/></th>
                                    <th><s:text name="chargedata.feemoney"/></th>
                                    <th><s:text name="chargetype.monetaryunit"/></th>
                                    <th><s:text name="chargedata.percentmoney"/></th>
                                    <th><s:text name="chargedata.percentcustomer"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{ChargeStatiticsList}" var="chargeStatitics">
                                    <tr>
                                        <td><s:property value="#chargeStatitics.buildingName"/></td>
                                         <td><s:property value="#chargeStatitics.typeName"/></td>
                                        <%--  <td><s:property value="#chargeStatitics.chargetTime"/></td>--%>  
                                          <td><s:property value="#chargeStatitics.totalCustomer"/></td>
                                           <td><s:property value="#chargeStatitics.acTotalCustomer"/></td>
                                           <td>
                                           <s:text name="global.format.money">  
											  <s:param value="#chargeStatitics.totalMoney"></s:param>  
										   </s:text> 
                                          </td>
                                         <td>
                                         <s:text name="global.format.money">  
											  <s:param value="#chargeStatitics.acTotalMoney"></s:param>  
										   </s:text> 
                                         </td>
                                          <td>
                                          <s:text name="global.format.money">  
											  <s:param value="#chargeStatitics.feeTotalMoney"></s:param>  
										   </s:text> 
                                          </td>
                                          <td><s:property value="#chargeStatitics.monetaryUnit"/></td>
                                           <td><s:property value="#chargeStatitics.moneyPercent"/></td>
                                           <td><s:property value="#chargeStatitics.customerPercent"/></td>
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                         <!--endprint1-->
                <s:if test="ChargeStatiticsList.size>0">
               	<div class="all_tab_butb"><input type="button" value="<s:text name="common.element.action.confirm.print"/>" onclick="preview(1)" /></div>
               	</s:if>
                    </div>
                </div>
            </div>
            </form>
                
</body>
</html>

