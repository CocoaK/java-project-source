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
        <form action="<s:url action='update'/>" method="post">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action='queryList'/>"><s:text name="owneruser.management.list"/></a>
                    <a href="<s:url action='addInput'/>"><s:text name="owneruser.management.add"/></a>
                    <a href="<s:url action='updateInput'><s:param name='currUserId' value='user.userId'/></s:url>" class="all_menu_a"><s:text name="owneruser.management.update"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0" class="all_tab_body all_tab_bodys">
                        <s:include value="/WEB-INF/views/user/owner_admin/include_save_owner_info.jsp"/>
                        <tbody>
                            <tr>
                                <td><s:text name="user.account"/></td>
                                <td><s:property value='user.login.loginName'/><input type="hidden" name="user.login.loginId" value="<s:property value='user.login.loginId'/>"/></td>
                                <td><dfn></dfn><s:text name="user.alias"/></td>
                                <td><input type="text" maxlength="25" name="user.login.loginAlias" class="{required:true,maxlength:25}" value="<s:property value='user.login.loginAlias'/>" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="user.status"/></td>
                                <td colspan="3">
                                    <select name="user.login.status">
                                        <option value="0" <s:if test="user.login.status != '' && user.login.status == 0">selected</s:if>><s:text name="common.element.action.enable"/></option>
                                        <option value="1" <s:if test="user.login.status != '' && user.login.status == 1">selected</s:if>><s:text name="common.element.action.disable"/></option>
                                    </select>                                   
                                </td>                                
                            </tr>                           
                        </tbody>                                               
                    </table>
                    <div class="all_tab_butb"><input type="button" onclick="saveOwnerUser('', '<s:url action="existHouseId" namespace="/json/ownerUser"/>')" value="<s:text name='common.element.action.confirm.update' />" /></div>                    
                    <s:include value="/WEB-INF/views/user/include_update_msg.jsp"/>
                    <s:include value="/WEB-INF/views/user/owner_admin/include_select_house_msg.jsp"/>
                </div>
            </div>
        </div>
        </form>
    </body>
</html>
