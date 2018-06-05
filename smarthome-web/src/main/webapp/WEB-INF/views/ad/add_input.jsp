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
    </head>

    <body>
        <div class="main_section">
        	<div class="all_aside">
            	<div class="all_menu">
                    <a href="<s:url action='queryList'/>"><s:text name="ad.management.list"/></a>
                    <a href="<s:url action='addInput'/>" class="all_menu_a"><s:text name="ad.management.add"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <div class="main_tab_body building_body">
                        <iframe class="iframe" src="<s:url action='addWebInput'/>" width="100%" frameborder="0" height="1100" scrolling="no"></iframe>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
    		$(".iframe").load(function() { 
    			$(this).height($(this).contents().height()); 
    		}); 
    	</script>
    </body>
</html>
