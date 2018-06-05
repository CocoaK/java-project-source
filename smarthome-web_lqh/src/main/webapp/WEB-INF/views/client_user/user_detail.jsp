<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="device.management.detail"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                	<a href="<s:url action="queryList"/>" >用户管理列表</a>
                    <a href="#" class="all_menu_a">用户详细信息</a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120">用户编号</td>
                                <td colspan="3"><s:property value="user.id"/></td>
                            </tr>
                            <tr>
                                <td>用户名称</td>
                                <td colspan="3"><s:property value="user.username"/></td>
                            </tr>
                            <%--<tr>
                            	<td>用户密码</td>
                                <td colspan="3"><s:property value="user.password"/></td>
                            </tr>
                            --%><tr>
                                <td>用户类型</td>
                                <td colspan="3"><s:property value="user.type"/></td>
                            </tr>
                            
                            <tr>
                                <td>用户状态</td>
                                <td colspan="3"><s:property value="user.status"/></td>
                            </tr>                            
                          
                            <tr>
                                <td>用户手机号</td>
                                <td colspan="3"><s:property value="user.mobile"/></td>
                            </tr>
                            <tr>
                                <td>用户邮箱</td>
                                <td colspan="3"><s:property value="user.email"/></td>
                            </tr>
                            <tr>
                                <td>用户注册时间</td>
                                <td width="30%"><s:date name="user.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                <td width="120">用户登录时间</td>
                                <td><s:date name="user.updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>            
                        </tbody>
                    </table>                   
                </div>
            </div>
        </div>
    </body>
</html>
