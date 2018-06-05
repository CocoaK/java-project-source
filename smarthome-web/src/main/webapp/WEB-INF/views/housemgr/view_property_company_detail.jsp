<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="housemgr.propertyCompany" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="#" class="all_menu_a"><s:text name="housemgr.propertyCompany.detail"/></a>
                    <a href="<s:url action="updatePropertyCompanyInput" />"><s:text name="housemgr.propertyCompany.update"/></a>
                    <!-- <a href="<s:url action="queryList" namespace="/paUser" />"><s:text name="pauser.management.list"/></a>
                    <a href="<s:url action="addInput" namespace="/paUser" />"><s:text name="pauser.management.add"/></a> -->
                </div>
            </div>
            <div class="main_outside">
	            <div class="all_inside">
	                <div class="main_detail">
	                    <h2><s:property value="propertyCompanyInfo.name"/></h2>
	                    <div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="propertyCompanyInfo.profile"/></div>
	                    <p>
	                       <span><s:text name="housemgr.propertyCompany.detail.contact"/>： <s:property value="propertyCompanyInfo.contact"/></span>
	                       <strong><s:text name="housemgr.propertyCompany.detail.charge"/>： <s:property value="propertyCompanyInfo.charge"/></strong>
	                    </p>
	                </div>
	            </div>
            </div>
        </div>
        <script>
    		QueryLoader.selectorPreload = "body";
    		QueryLoader.init();
    	</script>
    </body>
</html>
