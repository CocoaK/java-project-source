<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="inforeceiver.management"/></title>
<link href="<s:url value='/mobile/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>
<script type="text/javascript">
	function doSubmit(){
   		document.getElementById("pageNum").value=1;
   		document.forms[0].submit();
   	}
</script>
</head>

<body>
	<s:include value="/WEB-INF/views/mobile/head.jsp"/>
	<form action="<s:url action="queryInfoReceiverList"/>" method="post"> 
	    <div class="list_top"><s:text name="inforeceiver.management" /></div>
	    <div class="search search1">
    	<a href="<s:url action="gotoInputInfoSend"/>" class="search_button"><s:text name="infosend.add" /></a>
        <a href="<s:url action="queryInfoSendList"/>" class="search_button"><s:text name="infosend.management" /></a>
    	</div>
		<table cellspacing="0" class="list_tab">
        	<thead>
            	<tr>
                	<th><s:text name="infosend.title" /></th>
                    <th><s:text name="infosend.sendtime" /></th>
                </tr>
           	</thead>
            <tbody>
            <s:iterator value="%{page.results}" var="infoReceiver">
            	<tr>
            		<td class="text_left">
            		<a href="<s:url action="findInfoReceiver"><s:param name='requestId' value='#infoReceiver.id'/></s:url>">
            		<s:property value="#infoReceiver.infoSend.title"/></a></td>
            		<td><s:date name="#infoReceiver.infoSend.sendTime" format="yyyy-MM-dd HH:mm:ss" /></td>
            	</tr>
            </s:iterator>                       
            </tbody>
    	</table>
            <s:include value="/WEB-INF/views/mobile/common/paging.jsp"/>
	</form>
</body>
</html>
