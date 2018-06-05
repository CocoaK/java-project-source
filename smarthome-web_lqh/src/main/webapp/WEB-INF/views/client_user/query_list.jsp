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
	<form action="<s:url action='queryList'/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="<s:url action="queryList"/>" class="all_menu_a">用户管理列表</a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                    
                    
                    <form action="queryList" method="post">
	                    <div class="all_tab_top">                               
	                            <label class="all_border_right"><s:text name="用户名"/></label>
	                            <input type="text" maxlength="20" name="user.username" value="${user.username}" class="all_tab_top_input"/>
	                            <label class="all_border_right"><s:text name="手机号"/></label>
	                            <input type="text" maxlength="20" name="user.mobile" value="${user.mobile }" class="all_tab_top_input"/>    
	                            <label class="all_border_right"><s:text name="类型"/></label>
	                            <div class="select_div">
		                            <select name="user.type">
			                        	<option value="">
			                        		<s:text name="common.title.all"/>
			                            </option>
			                            <option>
			                                <s:text name="0"/>
			                            </option>
			                            <option value="1" <s:if test="user.type != '' && user.type == 1">selected</s:if>>
			                                <s:text name="1"/>
			                            </option>                              
			                        </select>
			                        
		                        </div>
		                        <label><s:text name="状态"/></label>
		                            	<div class="select_div">
			                            	<select name="user.status">
		                                		<option value="">
		                                			<s:text name="common.title.all"/>
		                                		</option>
		                                		<option>
		                                    		<s:text name="0" />
		                                		</option>
		                                		<option value="1" <s:if test="user.status != '' && user.status == 1">selected</s:if>>
		                                    		<s:text name="1" />
		                                		</option>                               
		                            		</select>
	                            		</div>
		                       	<input type="submit" value="<s:text name='common.element.action.search' />" class="search_but" />
	                        </div>
                        </form>
                        
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th>编号</th>
                                    <th>用户名</th>
                                    <th>密码</th>
                                    <th>类型</th>
                                    <th>状态</th>
                                    <th>手机号</th>
                                    <th>sip用户名</th>
                                    <th>sip密码</th>
                                    <th>电子邮件地址</th>
                                    <th>创建时间</th>
                                    <th>更新时间</th>
                                    <th>备注</th>
                                    <th>sessionKey</th>
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
                                <s:iterator value="%{page.results}" var="user">
                                    <tr>
                                        <%--<td><a href="<s:url action="deviceDetailz" />" ><s:property value="#user.id"/></a></td>
                                        <td><s:property value="#user.username"/></td>
                                        <td><s:property value="#user.phone"/></td>
                                        <td><s:property value="#user.email"/></td>
                                        <td><s:property value="#user.type"/></td>
                                        <td>
                                        <a href="<s:url action="updateDetail2" />" class="all_hover_but">
                                        <i><s:property value="#user.operation"/></i>
                                        </a>
                                        </td>                  
                                    --%>
                                    <td><a href="<s:url action="userDetail"><s:param name="user.id" value="#user.id"/></s:url>"><s:property value="#user.id"/></a></td>
                                    <td><s:property value="#user.username"/></td>
                                    <td><s:property value="#user.password"/></td>
                                    <td><s:property value="#user.type"/></td>
                                    <td><s:property value="#user.status"/></td>
                                    <td><s:property value="#user.mobile"/></td>
                                    <td><s:property value="#user.sipid"/></td>
                                    <td><s:property value="#user.sipPasswd"/></td>
                                    <td><s:property value="#user.email"/></td>
                                    <td><s:date name="#user.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td><s:date name="#user.updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td><s:property value="#user.remark"/></td>
                                    <td><s:property value="#user.sessionKey"/></td>
                                    
                                    
                                    <td><a href="<s:url action="updateDetail3"><s:param name="user.id" value="#user.id"/></s:url>" class="all_hover_but">修改</a>
                                        <a href="javascript:confirmAction('<s:url action="delete"><s:param name="user.id" value="#user.id"/></s:url>','<s:text name="确定删除？"/>')" class="all_hover_but"><s:text name="删除"/></a> 
                                    </td>
                            
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </table>
                        <s:include value="/views/common/paging.jsp"/> 
                        <div class="layer" id="confirmFrame" style="display:none;">
                            <div>
                                <p id="confirmMsg"></p>
                                <ul>
                                    <li>
                                        <a id="targetUrl" href="#">
                                            <s:text name="common.element.action.confirm"/>
                                        </a>
                                        <a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.cancel"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>      
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
