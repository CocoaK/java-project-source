<%@page import="com.biencloud.smarthome.web.charge.vo.ChargeTypeVO"%>
<%@page import="com.biencloud.smarthome.web.charge.vo.ChargeDetailVO"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="chargedetail.detail"/></title>
<link href="<s:url value='/mobile/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>
</head>
<% request.setAttribute("YearFlag", ChargeTypeVO.QUARTER[0]); request.setAttribute("MonthFlag", ChargeTypeVO.QUARTER[1]);
request.setAttribute("HalfYearFlag", ChargeTypeVO.QUARTER[2]); request.setAttribute("QuarterFlag", ChargeTypeVO.QUARTER[3]);
request.setAttribute("FeeFlag", ChargeDetailVO.FeeStatusYES);
%>
<body>
	<s:include value="/WEB-INF/views/mobile/head.jsp"/>
    <div class="list_top"><s:property value='chargedetail.detail'/></div>
    <table cellspacing="0" class="list_tab">
    	<thead>
    	
        	<tr>
            	<th><s:text name="charge.project"/></th>
                <th><s:text name="charge.common.total"/></th>
                <th><s:text name="chargetype.standard"/></th>
                <th><s:text name="charge.money"/></th>
            </tr>
        </thead>
        <tbody>
        <s:iterator value="chargeTypeResultList" var="chargeTypeResult" status="sta">
        	<tr>
            	<td><s:property value="#chargeTypeResult.chargeTypeName"/></td>
                <td id="TotalIndex<s:property value='#sta.index'/>"><s:property value="#chargeTypeResult.actualTotal"/><s:property value="#chargeTypeResult.calUnit"/></td>
                <td><s:property value="#chargeTypeResult.standard"/><s:text name="charge.common.yuan"/>/<s:property value="#chargeTypeResult.calUnit"/></td>
                <td><s:property value="#chargeTypeResult.playMoney"/></td>
            </tr>
	                                        	<s:if test="#request.YearFlag==#chargeTypeResult.calUnit||#request.MonthFlag==#chargeTypeResult.calUnit||#request.HalfYearFlag==#chargeTypeResult.calUnit||#request.QuarterFlag==#chargeTypeResult.calUnit">
	                                        	<script type="text/javascript">
	                                        	 $("#TotalIndex<s:property value='#sta.index'/>").text("");
	                                        	</script>
	                                        	</s:if>            
        </s:iterator>
        </tbody>
        <tfoot>
        	<tr>
            	<td colspan="4"><s:text name="chargedata.totalmoney"/>：<s:property value="chargeDetail.chargeData.totalMoney"/> (<s:property value="chargeDetail.chargeData.monetaryUnit"/>)</td>
            </tr>
        </tfoot>
    </table>
    <div class="page">
        <a href="javascript:history.back(-1);" class="right_page"><s:text name="common.element.action.return" /></a>
    </div>
</body>
</html>
