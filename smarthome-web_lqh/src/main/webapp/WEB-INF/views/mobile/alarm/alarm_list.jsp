<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="M003505"/></title>
<link href="<s:url value='/mobile/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
<script type="text/javascript">
	function doSubmit(){
   		document.getElementById("pageNum").value=1;
   		document.forms[0].submit();
   	}
</script>
</head>

<body>
	<form action="<s:url action="alarmList"/>" method="post"> 
	    <s:include value="/WEB-INF/views/mobile/head.jsp"/>
	    <div class="list_top"><s:text name="M003505" /></div>
	    <div class="search">
	    	<label><s:text name="alarm.alarmtype" />:</label>
            <select name="alarm.alarmType.alarmType" class="search2 select">
            	<option value=""><s:text name="common.title.all" /></option>
                <s:iterator value="alarmTypeList" var="alarmtype">
                    <option value="<s:property value="#alarmtype.alarmType" />" <s:if test="alarm.alarmType.alarmType != '' && alarm.alarmType.alarmType == #alarmtype.alarmType">selected</s:if>>
                    	<s:property value="#alarmtype.alarmName"/>
                    </option>
                </s:iterator>
           	</select>
           	<label><s:text name="alarm.status" />:</label>
            <select name="alarm.status" class="search2 select">
            	<option value="" <s:if test="alarm.status == ''">selected</s:if>><s:text name="common.title.all" /></option>
                <option value="0" <s:if test="alarm.status != '' && alarm.status == 0">selected</s:if>><s:text name="alarm.statuscancel"/></option>
                <option value="1" <s:if test="alarm.status != '' && alarm.status == 1">selected</s:if>><s:text name="alarm.statusno"/></option>
                <option value="2" <s:if test="alarm.status != '' && alarm.status == 2">selected</s:if>><s:text name="alarm.statusyes"/></option>
           	</select>
	        <input type="button" value='<s:text name="common.element.action.search" />' onclick="doSubmit()" class="search_button" />
	    </div>
		<table cellspacing="0" class="list_tab">
        	<thead>
            	<tr>
                	<th><s:text name="alarm.alarmtype" /></th>
                    <th><s:text name="alarm.hanlderuser" /></th>
                    <th><s:text name="alarm.createdtime" /></th>
                    <th><s:text name="alarm.status" /></th>
                </tr>
           	</thead>
            <tbody>
            <s:iterator value="%{page.results}" var="alarm">
            	<tr>
            		<td><s:property value="#alarm.alarmType.alarmName"/></td>
            		<td><s:property value="#alarm.paUser.userName"/></td>
            		<td><s:date name="#alarm.createdTime" format="yyyy-MM-dd HH:mm:ss" /></td>
            		<td>
            			<s:if test="#alarm.status==0"><s:text name="alarm.statuscancel" /></s:if>
            			<s:if test="#alarm.status==1"><s:text name="alarm.statusno" /></s:if>
            			<s:if test="#alarm.status==2"><s:text name="alarm.statusyes" /></s:if>
            		</td>  
            	</tr>
            </s:iterator>                       
            </tbody>
    	</table>
            <s:include value="/WEB-INF/views/mobile/common/paging.jsp"/>
	</form>
</body>
</html>
