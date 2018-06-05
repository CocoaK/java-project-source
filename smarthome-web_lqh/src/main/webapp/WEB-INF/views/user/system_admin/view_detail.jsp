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
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action="queryList"/>"><s:text name="sauser.management.list"/></a>
                    <a href="<s:url action='addInput'/>"><s:text name="sauser.management.add"/></a>
                    <a href="<s:url action='viewDetail'><s:param name='currUserId' value='user.userId'/></s:url>" class="all_menu_a"><s:text name="sauser.management.detail"/></a>
                    <a href="<s:url action="queryList" namespace='/paUser'/>"><s:text name="pauser.management.list"/></a>
                    <a href="<s:url action="addInput" namespace='/paUser'/>"><s:text name="pauser.management.add"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">                       
                        <thead>
                            <tr>
                                <th colspan="4" class="font_left"><s:text name="sauser.management.info"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="user.name"/></td>
                                <td><s:property value="user.userName"/></td>
                                <td width="120"><s:text name="common.title.gender"/></td>
                                <td>
                                    <s:if test="user.gender != '' && user.gender == 0">
                                    	<s:text name="common.title.gender.male" />
                                    </s:if>
                                    <s:elseif test="user.gender != '' && user.gender == 1">
                                        <s:text name="common.title.gender.female" />
                                    </s:elseif>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="user.department"/></td>
                                <td><s:property value="user.department"/></td>
                                <td><s:text name="user.post"/></td>
                                <td><s:property value="user.post"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.title.idcard"/></td>
                                <td><s:property value="user.idCard"/></td>
                                <td><s:text name="common.title.birthdate"/></td>
                                <td><s:date name="user.birthDate" format="yyyy-MM-dd" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.title.email"/></td>
                                <td><s:property value="user.email"/></td>
                                <td><s:text name="common.title.address"/></td>
                                <td><s:property value="user.addr"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.title.officephone"/></td>
                                <td><s:property value="user.officePhone"/></td>
                                <td><s:text name="common.title.mobilephone"/></td>
                                <td><s:property value="user.mobilePhone"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.action.createduser"/></td>
                                <td><s:property value="user.createdUser"/></td>
                                <td><s:text name="common.action.createdtime"/></td>
                                <td><s:date name="user.createdTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.action.updateduser"/></td>
                                <td><s:property value="user.updatedUser"/></td>
                                <td><s:text name="common.action.updatedtime"/></td>
                                <td><s:date name="user.updatedTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                            </tr>                                                                                                              
                        </tbody>
                        <thead>
                            <tr>
                                <th colspan="4" class="font_left"><s:text name="user.login.info"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><s:text name="user.account"/></td>
                                <td><s:property value="user.login.loginName"/></td>
                                <td><s:text name="user.permissions"/></td>
                                <td><s:property value="user.roleName"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="user.alias"/></td>
                                <td><s:property value="user.login.loginAlias"/></td>
                                <td><s:text name="user.status"/></td>
                                <td>
                                    <s:if test="user.login.status == 0">                                           
                                    	<s:text name="common.element.action.enable"/>
                                    </s:if>
                                    <s:else>                                           
                                        <s:text name="common.element.action.disable"/>
                                    </s:else>
                                </td>                                
                            </tr>                           
                        </tbody>
                    </table>                   
                </div>
            </div>
        </div>
        <script>
    		QueryLoader.selectorPreload = "body";
    		QueryLoader.init();
    	</script>
    </body>
</html>
