<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@page import="com.biencloud.smarthome.web.requestrepair.vo.RequestRepairVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.biencloud.smarthome.web.common.action.ActionUtils"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% String path=request.getContextPath(); Boolean showDetail=request.getAttribute(ActionUtils.SHOWDETAIL)==null?false:(Boolean)request.getAttribute(ActionUtils.SHOWDETAIL); %>
<%
boolean isTree=request.getAttribute("treeList")==null?false:true;
request.setAttribute("isTree",isTree);
request.setAttribute("LOGINUSERTYPEOWNER",Constants.LOGIN_USER_TYPE_OWNER);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="requestrepair.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
    </head>

    <body>
        <form action="<s:url action="saveRequestRepair"/>" method="post" id="formname" name="formname">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<%=path%>/requestRepair/queryRequestRepairList.action"><s:text name="requestrepair.management.list"/></a>
                    <s:if test="#request.LOGINUSERTYPEOWNER == #request.userType"> <a href="<%=path%>/requestRepair/goToInputRequestRepair.action"><s:text name="requestrepair.add"/></a></s:if>
                    <a href="#" class="all_menu_a"><s:text name="requestrepair.detail"/></a>
                    </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_bodyq">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="requestrepair.title"/></td>
                                <td >
                                <s:property value='requestRepair.title'/>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="requestrepair.content"/></td>
                                <td>
                                <s:property value="requestRepair.content"/></td>
                            </tr> 
                            <tr >
                                <td><dfn></dfn><s:text name="requestrepair.phone"/></td>
                                <td>
                                <s:property value='requestRepair.phone'/>
                                </td>
                            </tr> 
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        </form>
       
</body>
</html>
