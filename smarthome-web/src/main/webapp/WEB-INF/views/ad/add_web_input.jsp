<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="ad.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/ad/ad.js'/>"></script>
        <s:include value="/WEB-INF/views/common/include_common.jsp"></s:include>
    </head>

    <body>
        <form action="<s:url action='saveWeb'/>" id="saveWebAd" method="post" enctype="multipart/form-data">
            <div class="main_tab_top">
                <ul>
                    <li class="selecteds"><s:text name="ad.syscode.02"/></li>
                    <li><a href="<s:url action='addAppInput'/>"><s:text name="ad.syscode.01"/></a></li>
                </ul>
            </div>
            <s:include value="/WEB-INF/views/ad/include_save_web_ad.jsp"/>
            <div class="all_tab_butb"><input type="button" onclick="saveWebAd();" value="<s:text name='common.element.action.add' />" /></div>
            <s:include value="/WEB-INF/views/ad/include_add_result_msg.jsp"/>
        </form>       
    </body>
</html>
