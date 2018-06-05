<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="user.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/user/user.js'/>"></script>
        <s:include value="/WEB-INF/views/common/include_common.jsp"></s:include>        
    </head>

    <body>
        <form action="<s:url action='save'/>" method="post">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <s:if test="#session.login.userType == '03'">
                        <a href="<s:url action="queryList" namespace='/saUser'/>"><s:text name="sauser.management.list"/></a>
                        <a href="<s:url action="addInput" namespace='/saUser'/>"><s:text name="sauser.management.add"/></a>
                    </s:if>
                    <s:if test="#session.login.userType == '02'">
                        <a href="<s:url action='viewPropertyCompanyDetail' namespace='/housemgr'/>"><s:text name="housemgr.propertyCompany.detail"/></a>
                        <a href="<s:url action="updatePropertyCompanyInput" namespace='/housemgr'/>"><s:text name="housemgr.propertyCompany.update"/></a>
                    </s:if>
                    <a href="<s:url action="queryList"/>"><s:text name="pauser.management.list"/></a>
                    <a href="<s:url action="addInput"/>" class="all_menu_a"><s:text name="pauser.management.add"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0" class="all_tab_body all_tab_bodys">
                        <s:include value="/WEB-INF/views/user/property_admin/include_save_pauser_info.jsp"/>
                        <tbody>
                            <tr>
                                <td><dfn></dfn><s:text name="user.account"/></td>
                                <td><input type="text" maxlength="20" name="user.login.loginName" id="loginName" class="{required:true,useraccount:true}" value="<s:property value='user.login.loginName'/>" onkeyup="hideTip('existLoginNameMsg');"/><label id="existLoginNameMsg" class="error" style="display:none;"><s:text name="error.loginname.exist"/></label></td>
                                <td><s:text name="user.select.permissions"/></td>
                                <td>
                                    <select name="user.roleCode">
                                        <s:iterator value="%{roles}" var="role">
                                            <option value="<s:property value='#role.roleCode'/>" <s:if test="#role.roleCode == user.roleCode">selected</s:if>><s:property value='#role.roleName'/></option>
                                        </s:iterator>
                                    </select>                                   
                                </td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="user.alias"/></td>
                                <td><input type="text" maxlength="25" name="user.login.loginAlias" class="{required:true,maxlength:25}" value="<s:property value='user.login.loginAlias'/>" /></td>
                                <td><s:text name="user.status"/></td>
                                <td>
                                    <select name="user.login.status">
                                        <option value="0" <s:if test="user.login.status != '' && user.login.status == 0">selected</s:if>><s:text name="common.element.action.enable"/></option>
                                        <option value="1" <s:if test="user.login.status != '' && user.login.status == 1">selected</s:if>><s:text name="common.element.action.disable"/></option>
                                    </select>                                   
                                </td>                                
                            </tr>                           
                        </tbody>                                               
                    </table>
                    <div class="all_tab_butb"><input type="button" onclick="savePaUser('<s:url action="existLoginName" namespace="/json/login"/>')" value="<s:text name='common.element.action.add' />" /></div>                    
                    <s:include value="/WEB-INF/views/user/include_add_msg.jsp"/>
                    <s:include value="/WEB-INF/views/user/property_admin/include_select_district_msg.jsp"/>
                    <s:include value="/WEB-INF/views/user/include_no_role_msg.jsp"/>                   
                </div>
            </div>
        </div>
        </form>
    </body>
</html>
