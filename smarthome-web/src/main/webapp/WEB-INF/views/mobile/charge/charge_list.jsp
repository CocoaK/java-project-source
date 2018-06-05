<%@page import="com.biencloud.smarthome.web.charge.vo.ChargeDetailVO"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="chargedetail.management"/></title>
<link href="<s:url value='/mobile/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>
<script type="text/javascript">
	function doSubmit(){
   		document.getElementById("pageNum").value=1;
   		document.forms[0].submit();
   	}
</script>
<% request.setAttribute("FeeFlag", ChargeDetailVO.FeeStatusYES);
%>
</head>

<body>
<s:include value="/WEB-INF/views/mobile/head.jsp"/>
	<form action="<s:url action="queryChargeList"/>" method="post"> 
	    <div class="list_top"><s:text name="chargedetail.management" /></div>
	    <div class="search">
	    	<label><s:text name="chargedata.chargeime" /></label>
	        <input type="text" name="chargeDetail.chargeData.chargeTime" value="<s:property value="chargeDetail.chargeData.chargeTime"/>" class="search_text" maxlength="7"/>
	        <input type="button" value='<s:text name="common.element.action.search" />' onclick="doSubmit()" class="search_button" />
	    </div>
		<table cellspacing="0" class="list_tab">
        	<thead>
            	<tr>
                	<th><s:text name="chargedata.totalmoney"/></th>
                	<th><s:text name="chargedata.chargeime"/></th>
                	<th><s:text name="chargedetail.chargestatus"/></th>
                    <th><s:text name="chargedetail.chargeime" /></th>
                </tr>
           	</thead>
            <tbody>
            <s:iterator value="%{page.results}" var="chargeDetail">
            	<tr>
            		<td class="text_left">
            		<a href="<s:url action="findById"><s:param name='requestId' value='#chargeDetail.id'/></s:url>">
            		<s:property value="#chargeDetail.chargeData.totalMoney"/>
            		</a>
            		</td>
            		<td>
            		<s:property value="#chargeDetail.chargeData.chargeTime"/>
            		</td>
            		<td>
											 <s:if test="#chargeDetail.chargeStatus==#request.FeeFlag"><s:text name="common.charge.yesfee"/></s:if>
											 <s:else><s:text name="common.charge.nofee"/></s:else>
														</td>
                                        <td><s:property value="#chargeDetail.chargeTime"/></td>
            	</tr>
            </s:iterator>                       
            </tbody>
    	</table>
            <s:include value="/WEB-INF/views/mobile/common/paging.jsp"/>
	</form>
</body>
</html>
