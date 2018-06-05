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
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/user/user.js'/>"></script>
        <s:include value="/WEB-INF/views/common/include_common.jsp"></s:include>
    </head>

    <body>
        <form action="<s:url action='updatePersonalInfo'/>" method="post">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action='viewPersonalInfo'/>"><s:text name="user.personal.info.view"/></a>
                    <a href="<s:url action='updatePersonalInfoInput'/>" class="all_menu_a"><s:text name="user.personal.info.update"/></a>
                    <a href="<s:url action='updatePassInput' namespace='/login'/>"><s:text name="user.pass.update"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0" class="all_tab_body all_tab_bodys">
                        <thead>
                            <tr>
                                <th colspan="4" class="font_left"><s:text name="sauser.management.info"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="user.name"/></td>
                                <td>
                                    <s:property value='user.userName'/>
                                </td>
                                <td width="120"><s:text name="common.title.gender"/></td>
                                <td>
                                    <select name="user.gender">
                                        <option value="0" <s:if test="user.gender != '' && user.gender == 0">selected</s:if>><s:text name="common.title.gender.male" /></option>
                                        <option value="1" <s:if test="user.gender != '' && user.gender == 1">selected</s:if>><s:text name="common.title.gender.female" /></option>
                                    </select>                                   
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="user.department"/></td>
                                <td><input type="text" size="30" maxlength="25" name="user.department" class="{maxlength:25}" value="<s:property value='user.department'/>" /></td>
                                <td><s:text name="user.post"/></td>
                                <td><input type="text" size="30" maxlength="25" name="user.post" class="{maxlength:25}" value="<s:property value='user.post'/>" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.title.idcard"/></td>
                                <td><s:property value='user.idCard'/></td>
                                <td><dfn></dfn><s:text name="common.title.birthdate"/></td>
                                <td>
                                    <input type="text" name="user.birthDate" class="date_input" validateType="birthDate" required="true" dateFormat="yyyy-MM-dd" errorEleId="errorBirthDateMsg" value="<s:date name='user.birthDate' format='yyyy-MM-dd' />" onclick="SelectDate(this,'yyyy-MM-dd',0,-150)" onchange="checkUserBirthDate(this);" readonly="true" />
                                    <label id="errorBirthDateMsg" class="error" style="display:none;"></label>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="common.title.email"/></td>
                                <td><input type="text" size="30" maxlength="50" name="user.email" class="{email:true,maxlength:50}" value="<s:property value='user.email'/>" /></td>
                                <td><s:text name="common.title.address"/></td>
                                <td><input type="text" size="50" maxlength="50" name="user.addr" class="{maxlength:50}" value="<s:property value='user.addr'/>" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="common.title.officephone"/></td>
                                <td><input type="text" maxlength="20" name="user.officePhone" class="{phone:true}" value="<s:property value='user.officePhone'/>" /></td>
                                <td><s:text name="common.title.mobilephone"/></td>
                                <td><input type="text" maxlength="20" name="user.mobilePhone" class="{mobilephone:true}" value="<s:property value='user.mobilePhone'/>" /></td>
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
                                <td><s:property value='user.login.loginName'/></td>
                                <td><dfn></dfn><s:text name="user.alias"/></td>
                                <td><input type="text" maxlength="25" name="user.login.loginAlias" class="{required:true,maxlength:25}" value="<s:property value='user.login.loginAlias'/>" /></td>                               
                            </tr>                                                                                  
                        </tbody>                                               
                    </table>
                    <div class="all_tab_butb"><input type="button" onclick="updateUser()" value="<s:text name='common.element.action.confirm.update' />" /></div>                   
                    <s:include value="/WEB-INF/views/user/include_update_personal_info_msg.jsp"/>
                </div>
            </div>
        </div>
        </form>
    </body>
</html>
