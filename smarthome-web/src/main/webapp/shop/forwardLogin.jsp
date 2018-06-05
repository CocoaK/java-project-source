<%@ page contentType="text/html;  charset=utf-8" pageEncoding="utf-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		
	</head>
	<script type="text/javascript">
	var errorCode = "<s:property value='%{#request.errorCode}'/>";
	var targetUrl = '<s:url action="logout" namespace="/shop/login"></s:url>';
	if(errorCode != "")
		targetUrl = targetUrl + "?errorCode=" + errorCode;
		
	out();
	function out() {	    
		window.parent.parent.location.href = targetUrl;    
	}
</script>
	<body>
		
		</body>
</html>
