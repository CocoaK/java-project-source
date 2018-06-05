<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="inforeceiver.management"/></title>
<link href="<s:url value='/mobile/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.textbox.js'/>"></script>
</head>

<body>
	<s:include value="/WEB-INF/views/mobile/head.jsp"/>
	<form action="<s:url action="saveInfoSend"/>" method="post"> 
	    <div class="list_top"><s:text name="infosend.add" /></div>
    
    <div class="list_top submit_top"><strong><s:text name="infosend.title"/><dfn>*</dfn>：</strong><input type="text" name="infoSend.title" id="title" maxlength="16"/></div>
    <div class="submit_content">
    	<p><s:text name="infosend.content"/><dfn>*</dfn>：</p>
        <textarea name="infoSend.content" id="remark"></textarea>
    </div>
    <div class="submit">
         <a href="javascript:saveData();" class="search_button"><s:text name="infosend.add" /></a>
    </div>
	</form>
	 <div class="layer" id="tipMsgDiv" style="display:none;">
                	<div>
                        <ul>
                            <li id="showMsg"></li>
                            <li style="padding-top: 10px"><a href="javascript:hideTip('tipMsgDiv');" id="ConfirmButtonId"><s:text name="common.element.action.confirm"/></a>
                            </li>
                        </ul>
                    </div>
                </div>
	<script type="text/javascript">
	function saveData(){
		if($("#title").val()==''){
			$("#showMsg").text("<s:text name='title isvalidate' />");
	        $("#tipMsgDiv").show();
		}else if($("#remark").val()==''){
			$("#showMsg").text("<s:text name='content isvalidate' />");
	        $("#tipMsgDiv").show();
		}else{
			$('form')[0].submit();
		}
 	}
	$(function() {
		$("#remark").textbox({
    		maxLength: 1000
		}); 
	});
	</script>
</body>
</html>
