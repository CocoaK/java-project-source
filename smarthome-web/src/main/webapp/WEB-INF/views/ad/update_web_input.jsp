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
        <form action="<s:url action='update'/>" method="post" enctype="multipart/form-data">
            <div class="main_section">
                <div class="all_aside">
                	<div class="all_menu">
                    	<a href="<s:url action='queryList'/>"><s:text name="ad.management.list"/></a>
                    	<a href="<s:url action='addInput'/>"><s:text name="ad.management.add"/></a>
                        <a href="<s:url action='updateInput'><s:param name='adId' value='ad.adId'/></s:url>" class="all_menu_a"><s:text name="ad.management.web.update"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <s:include value="/WEB-INF/views/ad/include_save_web_ad.jsp"/>
                        <div class="all_tab_butb"><input type="button" onclick="saveWebAd();" value="<s:text name='common.element.action.confirm.update' />" /></div>
                        <s:include value="/WEB-INF/views/ad/include_result_msg.jsp"/>
                	</div>
            	</div>
        	</div>
        </form>
    </body>
</html>
