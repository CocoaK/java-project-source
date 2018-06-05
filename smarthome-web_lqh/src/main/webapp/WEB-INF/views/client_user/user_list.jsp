<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="device.management"/></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/user/user.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/housemgr/districtList.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery.parseQuerystring.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/device/device.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<script>
   	
</script>
</head>

<body>
	<form action="<s:url action="userList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="<s:url action="userList"/>" class="all_menu_a">用户管理列表</a>
                       <!--  <s:if test="userType!=01">
                        	<a href="<s:url action="ipList"/>"><s:text name="device.ip.list"/></a>
                        </s:if> -->
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th>编号</th>
                                    <th>用户名</th>
                                    <th>手机号</th>
                                    <th>邮箱</th>
                                    <th>类型</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%--<tr>
                                  <td>1</td>
                                  <td>kkkk</td>
                                  <td>1515555661</td>
                                  <td>615165165@qq.com</td>
                                  <td>1</td>
                                  <td><a href="<s:url action="updateDetail2" />" class="all_hover_but">修改密码</a></td>
                                </tr> 
                                --%>
                                <s:iterator value="clientUsers" var="user">
                                    <tr>
                                        <td><a href="<s:url action="userDetail" />" ><s:property value="#user.id"/></a></td>
                                        <td><s:property value="#user.username"/></td>
                                        <td><s:property value="#user.phone"/></td>
                                        <td><s:property value="#user.email"/></td>
                                        <td><s:property value="#user.type"/></td>
                                        <td>
                                        <a href="<s:url action="updateDetail" />" class="all_hover_but">
                                        <i><s:property value="#user.operation"/></i>
                                        </a>
                                        </td>                  
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>      
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
