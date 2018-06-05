<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Index</title>
</head>
	<frameset rows="193,*" frameborder="no" framespacing="0" border="0">
    	<frame src="<s:url action='head' namespace='/login'/>" scrolling="no" />
        <frame src="<s:url action='main' namespace='/login'/>" scrolling="yes" name="main" />
        <noframes>
        	<body><s:text name="index.page.info"/></body>
        </noframes>
    </frameset>
</html>