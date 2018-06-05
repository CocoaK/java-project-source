<%@page import="com.biencloud.smarthome.web.info.vo.InfoSendVO"%>
<%@page import="com.biencloud.smarthome.web.info.vo.InfoReceiverVO"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
request.setAttribute("STATUSNOAUDITTHROUGH",InfoSendVO.STATUSNOAUDIT_THROUGH);
request.setAttribute("STATUSNOAUDITNOTHROUGH",InfoSendVO.STATUSNOAUDIT_NOTHROUGH); 
request.setAttribute("STATUSNOAUDIT",InfoSendVO.STATUSNOAUDIT);
request.setAttribute("STATUSNOSEND",InfoSendVO.STATUSNOSEND);
request.setAttribute("STATUSYESSEND",InfoSendVO.STATUSYESSEND);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="infosend.management"/></title>
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
	<form action="<s:url action="queryInfoSendList"/>" method="post"> 
	    <div class="list_top"><s:text name="infosend.management" /></div>
	    <div class="search">
	    	<label><s:text name="infosend.title" /></label>
	        <input type="text" name="infoSend.title" value="${infoSend.title}"  class="search_text" maxlength="16"/>
	        <input type="button" value='<s:text name="common.element.action.search" />' onclick="doSubmit()" class="search_button" />
	    </div>
	    <div class="search search1">
    	<a href="<s:url action="gotoInputInfoSend"/>" class="search_button"><s:text name="infosend.add" /></a>
        <a href="<s:url action="queryInfoReceiverList"/>" class="search_button"><s:text name="inforeceiver.management" /></a>
    	</div>
		<table cellspacing="0" class="list_tab">
        	<thead>
            	<tr>
                	<th><s:text name="infosend.title" /></th>
                    <th><s:text name="infosend.sendtime" /></th>
                    <th><s:text name="infosend.status"/></th>
                </tr>
           	</thead>
            <tbody>
            <s:iterator value="%{page.results}" var="infoSend">
            	<tr>
            		<td class="text_left">
            		<a href="<s:url action="findInfoSend"><s:param name='requestId' value='#infoSend.id'/></s:url>">
            		<s:property value="#infoSend.title"/>
            		</a>
            		</td>
            		<td>
            		<s:date name="#infoSend.sendTime" format="yyyy-MM-dd HH:mm:ss" />
            		</td>
            		<td>
                                        <s:if test="#infoSend.status==#request.STATUSNOAUDIT"><s:text name="infosend.noaudit"/></s:if>
                                        <s:elseif test="#infoSend.status==#request.STATUSNOAUDITTHROUGH">
                                        <a href="<s:url action="sendInfo"><s:param name='requestId' value='#infoSend.id'/></s:url>">
                                        <s:text name="infosend.publish"/>
                                        </a>
                                        </s:elseif>
                                        <s:elseif test="#infoSend.status==#request.STATUSNOAUDITNOTHROUGH"><s:text name="common.element.action.approveno"/></s:elseif>
                                        <s:else><s:text name="infosend.yessend"/></s:else>
                                        </td>
            	</tr>
            </s:iterator>                       
            </tbody>
    	</table>
            <s:include value="/WEB-INF/views/mobile/common/paging.jsp"/>
	</form>
</body>
</html>
