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
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/user/user.js'/>"></script>
        <s:include value="/WEB-INF/views/common/include_common.jsp"></s:include>
    </head>

    <body>
        <form action="<s:url action='updatePass'/>" method="post">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <s:if test="userType == '01'">
                        <a href="<s:url action='viewPersonalInfo' namespace='/ownerUser'/>"><s:text name="user.personal.info.view"/></a>
                        <a href="<s:url action='updatePersonalInfoInput' namespace='/ownerUser'/>"><s:text name="user.personal.info.update"/></a>
                    </s:if>
                    <s:elseif test="userType == '02'">
                        <a href="<s:url action='viewPersonalInfo' namespace='/paUser'/>"><s:text name="user.personal.info.view"/></a>
                        <a href="<s:url action='updatePersonalInfoInput' namespace='/paUser'/>"><s:text name="user.personal.info.update"/></a>
                    </s:elseif>
                    <s:else>
                        <a href="<s:url action='viewPersonalInfo' namespace='/saUser'/>"><s:text name="user.personal.info.view"/></a>
                        <a href="<s:url action='updatePersonalInfoInput' namespace='/saUser'/>"><s:text name="user.personal.info.update"/></a>
                    </s:else>                   
                    <a href="<s:url action='updatePassInput' namespace='/login'/>" class="all_menu_a"><s:text name="user.pass.update"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0" class="all_tab_body all_tab_bodys">                                                    
                        <tbody>
                            <tr>
                            	<td width="120"><dfn></dfn><s:text name="user.update.current.pass"/></td>
                                <td><input type="password" size="20" maxlength="20" name="currPass" class="{required:true,pass:true}" /></td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="user.update.new.pass"/></td>
                                <td><input type="password" size="20" maxlength="20" id="newPass" name="newPass" <s:if test="successFlag == false">value="<s:property value='newPass'/>"</s:if> class="{required:true,pass:true}" /></td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="user.update.confirm.new.pass"/></td>
                                <td>
                                    <input type="password" size="20" maxlength="20" id="confirmNewPass" name="confirmNewPass" <s:if test="successFlag == false">value="<s:property value='confirmNewPass'/>"</s:if> onfocus="notEqualPassMsg(false)" class="{required:true,pass:true}" />
                                    <label id="notEqualMsg" class="error" style="display:none;"><s:text name="error.not.equal" /></label>
                                </td>
                            </tr>
                        </tbody>                                                  
                    </table>
                    <div class="all_tab_butb"><input type="button" onclick="savePass()" value="<s:text name='common.element.action.confirm.update' />" /></div>
                    <s:if test="promptFlag == true">
                        <div class="layer" id="confirmFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                    <li><s:actionmessage />&nbsp;</li>
                                    <li><a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.confirm"/></a></li>
                                </ul>
                            </div>
                        </div>
                    </s:if>                   
                </div>
            </div>
        </div>
        </form>
    </body>
</html>
