<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="user.personal.info" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action='viewPersonalInfo'/>" class="all_menu_a"><s:text name="user.personal.info.view"/></a>
                    <a href="<s:url action='updatePersonalInfoInput'/>"><s:text name="user.personal.info.update"/></a>
                    <a href="<s:url action='updatePassInput' namespace='/login'/>"><s:text name="user.pass.update"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">                       
                        <thead>
                            <tr>
                                <th colspan="4" class="font_left"><s:text name="pauser.management.info"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="user.name"/></td>
                                <td colspan="3"><s:property value="user.userName"/></td>                               
                            </tr>
                            <tr>                                
                                <td><s:text name="common.title.gender"/></td>
                                <td>
                                    <s:if test="user.gender != '' && user.gender == 0">
                                        <s:text name="common.title.gender.male" />
                                    </s:if>
                                    <s:elseif test="user.gender != '' && user.gender == 1">
                                        <s:text name="common.title.gender.female" />
                                    </s:elseif>
                                </td>
                                <td width="120"><s:text name="common.title.districtname"/></td>
                                <td><s:property value="user.districtName"/></td>                              
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
                                <td><s:text name="common.title.degree"/></td>
                                <td><s:property value="user.degree"/></td>
                                <td><s:text name="common.title.major"/></td>
                                <td><s:property value="user.major"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="user.intro"/></td>
                                <td colspan="3"><s:property value="user.intro"/></td>
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
                                <td><s:text name="user.alias"/></td>
                                <td><s:property value="user.login.loginAlias"/></td>
                            </tr>                                                       
                        </tbody>
                    </table>                   
                </div>
            </div>
        </div>
    </body>
</html>
