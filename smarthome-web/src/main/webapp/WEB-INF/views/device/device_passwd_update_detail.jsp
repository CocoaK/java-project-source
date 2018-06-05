<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="device.passwd.change"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/validate/check.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/md5.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/device/device.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
		<jsp:include  page="../common/include_common.jsp"/>

    </head>

    <body>
    <form action="<s:url action="pwdChangeResult"/>" method="post">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                	<a href="<s:url action="deviceList"/>"><s:text name="device.management.list"/></a>
                    <a href="#" class="all_menu_a"><s:text name="device.passwd.change"/></a>
                    <s:if test="userType!=01">
                    	<a href="<s:url action="ipList"/>"><s:text name="device.ip.list"/></a>
                    	<a href="<s:url action="messageList"/>"><s:text name="device.message.list"/></a>
                    </s:if>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120">
                                	<input type="hidden" id="deviceId" name="deviceId" value="<s:property value="device.deviceId"/>" />
                                	<s:text name="device.devicecode"/>
                                </td>
                                <td><s:property value="device.deviceCode"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="device.devicename"/></td>
                                <td><s:property value="device.deviceName"/></td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="device.passwd.oldpasswd"/></td>
                                <td>
                                	<input type="password" size="20" maxlength="40" id="device.devicePwd" name="device.devicePwd" class="{required:true}" />
                                </td>
                            </tr>
                            <tr>
                            	<td><dfn></dfn><s:text name="device.passwd.newpasswd"/></td>
                                <td>
                                	<input type="password" size="20" minlength="6" maxlength="40" id="newPasswd" name="newPasswd" class="{required:true}"/>
                                </td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="device.passwd.repasswd"/></td>
                                <td>
                                	<input type="password" size="20" maxlength="40" id="rePasswd" name="rePasswd" class="{equalTo: '#newPasswd'}"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="submit" value="<s:text name='common.element.action.update'/>"/></div>
                    <s:if test="successFlag == 'success'">                                       
                        <div class="layer" id="confirmFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                    <li>
                                        <s:text name="common.update.success"/>
                                    </li>
                                    <li>
                                        <a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.confirm"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </s:if>
                    <s:if test="successFlag == 'fail'">                                       
                        <div class="layer" id="confirmFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                    <li>
                                        <s:text name="common.element.action.operationfailed"/>
                                    </li>
                                    <li>
                                    	<a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.return"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </s:if>    
                    <s:if test="successFlag == 'passwordWrong'">                                       
                        <div class="layer" id="confirmFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                	<li>
                                        <s:text name="common.element.action.operationfailed"/>
                                    </li>
                                    <li>
                                        <s:text name="device.oldpassword.wrong"/>
                                    </li>
                                    <li>
                                    	<a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.return"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </s:if>  
                </div>
            </div>
        </div>
        </form>
        <script>
    		QueryLoader.selectorPreload = "body";
    		QueryLoader.init();
    	</script>
    </body>
</html>
