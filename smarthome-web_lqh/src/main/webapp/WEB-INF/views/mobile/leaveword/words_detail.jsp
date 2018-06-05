<%@ page language="java"
	import="java.util.*,com.biencloud.smarthome.web.common.vo.*"
	pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	function locationAction(url) {
		window.location.href = url;

	}
	function viewImage(path) {
		var url="viewImage.action?imageUrl="+path;
		locationAction(url);

	}
	
</script>
<body>
	<s:include value="/WEB-INF/views/mobile/head.jsp"/>
	<div class="list_top"><s:text name="leave.word.detail"/></div>


	<table cellspacing="0" cellspacing="0" class="list_tab list_tab_cont">

		<tbody>

			<tr>
				<td class="tab_left"><s:text name="leave.word.caller"/>:</td>

				<td ><s:property value="callRecordVO.caller" />
				</td>
			</tr>
			<tr>
				<td class="tab_left"><s:text name="leave.word.type"/>:</td>

				<td ><s:if test="callRecordVO.callType==0">
						<s:text name="leave.word.callout"/>
					</s:if> <s:if test="callRecordVO.callType==1">
						<s:text name="leave.word.callin"/>
					</s:if> <s:if test="callRecordVO.callType==2">
						<s:text name="leave.word.answer"/> 
					</s:if> <s:if test="callRecordVO.callType==3">
						<s:text name="leave.word.leavword"/>
					</s:if>
				</td>
			</tr>
			<tr>
				<td class="tab_left"><s:text name="leave.word.duration"/>:</td>

				<td ><s:property value="callRecordVO.talkTime" /></td>
			</tr>
			<tr>
				<td class="tab_left"><s:text name="leave.word.time"/>:</td>

				<td ><s:date name="callRecordVO.callTime"
						format="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
			<s:if test="callRecordVO.pictureNetPath!=null">
			<tr>
				<td class="tab_left"><s:text name="leave.word.pic"/>:</td>

				<td >
				
				<a href="javascript:viewImage('<s:property value="appDownloadUrl"/><s:property value='callRecordVO.pictureNetPath'/>')" ><s:text name="leave.word.view"/></a><a href="<s:property value="appDownloadUrl"/><s:property value='callRecordVO.pictureNetPath'/>" rel="colorbox1" target="_blank" ><s:text name="leave.word.download"/></a>
				
				</td>
			</tr>
			</s:if>
			<s:if test="callRecordVO.videoNetPath!=null">
			<tr>
				<td class="tab_left"><s:text name="leave.word.video"/>:</td>

				<td >
				
				<a href="<%=basePath %>mobile/play_html5.html?purl=<s:property value="appDownloadUrl"/><s:property value='callRecordVO.videoNetPath'/>"><s:text name="leave.word.play"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<s:property value="appDownloadUrl"/><s:property value='callRecordVO.videoNetPath'/>" ><s:text name="leave.word.download"/></a>
				
				</td>
			</tr>
			</s:if>

		</tbody>
	</table>
	 <div class="submit">    	
        <a href="javascript:history.back();"><s:text name="leave.word.back"/></a>
    </div>
</body>
</html>
