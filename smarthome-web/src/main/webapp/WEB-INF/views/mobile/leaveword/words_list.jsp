<%@ page language="java"
	import="java.util.*,com.biencloud.smarthome.web.common.vo.*"
	pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="<s:url value='/mobile/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>

</head>
<script type="text/javascript">
    function locationAction(url)
    {      
      window.location.href =url;	  
	  
    }
    function view(id)
    {      
      var url="viewDetail.action?callRecordVO.id="+id;	
      locationAction(url);  
	  
    }
    function doSubmit(){
   		document.getElementById("pageNum").value=1;
   		document.forms[0].submit();
   	}
</script>
<body>
<form action="<s:url action="wordList"/>" method="post"> 
	<s:include value="/WEB-INF/views/mobile/head.jsp"/>
	<div class="list_top"><s:text name="leave.word.list"/></div>
	<div class="search">
		<label> <select name="callRecordVO.callType">
	                                <option value="">
	                                	<s:text name="common.title.all"/>
	                                </option>
	                               	<option value="0" <s:if test="callRecordVO.callType != '' && callRecordVO.callType == 0">selected</s:if>>
	                                	<s:text name="leave.word.callout" />
	                                </option>
	                                <option value="1" <s:if test="callRecordVO.callType != '' && callRecordVO.callType == 1">selected</s:if>>
	                               		<s:text name="leave.word.callin" />
	                                </option>
	                                <option value="2" <s:if test="callRecordVO.callType != '' && callRecordVO.callType == 2">selected</s:if>>
	                               		<s:text name="leave.word.answer" />
	                                </option>
	                                <option value="3" <s:if test="callRecordVO.callType != '' && callRecordVO.callType == 3">selected</s:if>>
	                               		<s:text name="leave.word.leavword" />
	                                </option>                               
	                            </select><s:text name="leave.word.caller"/></label> <s:textfield name="callRecordVO.caller" cssClass="search_text" maxlength="20"/> <input type="button" value="<s:text name="leave.word.search"/>" class="search_button" onclick="doSubmit();" />
	</div>

	<table cellspacing="0" class="list_tab">
		<thead>
			<tr>
				<th><s:text name="leave.word.caller"/></th>
				
				<th><s:text name="leave.word.time"/></th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="%{page.results}" var="result">
				<tr>
					<td style="text-align:left"><a href="javascript:view('<s:property value="#result.id" />');"><s:property value="#result.caller" /></a>
					</td>
					
					<td><s:date name="#result.callTime"
							format="yyyy-MM-dd HH:mm:ss" />
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<s:include value="/WEB-INF/views/mobile/common/paging.jsp"/>
	</form>
</body>
</html>
