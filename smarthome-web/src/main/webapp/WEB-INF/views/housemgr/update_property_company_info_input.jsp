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
        <script type="text/javascript" src="<s:url value='/js/jquery.textbox.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
    </head>

    <body>
        <form action="<s:url action="updatePropertyCompanyInfo"/>" method="post">
        <input type="hidden" name="propertyCompanyInfo.id" value="<s:property value='propertyCompanyInfo.id'/>"/>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="#" class="all_menu_a"><s:text name="housemgr.propertyCompany.update"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><dfn></dfn><s:text name="housemgr.propertyCompany.detail.name"/></td>
                                <td><input type="text" size="40" name="propertyCompanyInfo.name" value="<s:property value="propertyCompanyInfo.name"/>" maxlength="30" class="{required:true, maxlength: 30}" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="housemgr.propertyCompany.detail.profile"/></td>
                                <td><textarea id="profile" name="propertyCompanyInfo.profile" class="{maxlength: 500}"><s:property value="propertyCompanyInfo.profile"/></textarea></td>
                            </tr>
                            <tr>
                                <td><s:text name="housemgr.propertyCompany.detail.contact"/></td>
                                <td><input type="text" size="40" name="propertyCompanyInfo.contact" maxlength="20" class="{phone:true}" value="<s:property value="propertyCompanyInfo.contact"/>" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="housemgr.propertyCompany.detail.charge"/></td>
                                <td><input type="text" size="40" name="propertyCompanyInfo.charge" maxlength="20" class="{maxlength: 20}" value="<s:property value="propertyCompanyInfo.charge"/>" /></td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="submit" value="<s:text name='common.element.action.confirm.update' />" /></div>
                    <s:include value="/WEB-INF/views/housemgr/include_result_msg.jsp"/>                   
                </div>
            </div>
        </div>
        </form>       
        <script>
            QueryLoader.selectorPreload = "body";
            QueryLoader.init();
            $(function() {
        		$("#profile").textbox({
            		maxLength: 500
        		}); 
    		});
        </script>
    </body>
</html>
