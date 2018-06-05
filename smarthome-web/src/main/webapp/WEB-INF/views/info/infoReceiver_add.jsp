<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@page import="com.biencloud.smarthome.web.info.vo.InfoSendVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.biencloud.smarthome.web.charge.vo.ChargeTypeVO"%>
<%@page import="com.biencloud.smarthome.web.common.action.ActionUtils"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% String path=request.getContextPath(); Boolean showDetail=request.getAttribute(ActionUtils.SHOWDETAIL)==null?false:(Boolean)request.getAttribute(ActionUtils.SHOWDETAIL); %>
<% List<Map<String,String>> treeList=request.getAttribute("treeList")==null?null:(List<Map<String,String>>)request.getAttribute("treeList");
String comStr=request.getAttribute("comStr")==null?null:(String)request.getAttribute("comStr");
request.setAttribute("LOGINUSERTYPEOWNER",Constants.LOGIN_USER_TYPE_OWNER);
String userType=(String)request.getAttribute("userType");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="infosend.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/MzTreeView12.js'/>"></script>
		<style type="text/css">
			a.MzTreeview /* TreeView 链接的基本样式 */ { cursor: hand; color: #000080; margin-top: 5px; padding: 2 1 0 2; text-decoration: none; }
			.MzTreeview a.select /* TreeView 链接被选中时的样式 */ { color: highlighttext; background-color: highlight; }
			#kkk input{vertical-align:middle;}
			.MzTreeViewRow img,.MzTreeViewRow input{ margin-right:5px;}
			.MzTreeViewCell0{ height:24px; line-height:24px;}
			.MzTreeViewCell1{ height:24px; line-height:24px; border-bottom:1px solid #DDD; }
		</style>
    </head>

    <body>
        <form action="<s:url action="infoSendAction!save.action"/>" method="post" id="formname" name="formname">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<%=path%>/info/queryInfoSendList.action"><s:text name="infosend.management.list"/></a>
                        <a href="<%=path%>/info/goToInputInfoSend.action"><s:text name="infosend.add"/></a>
                        <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)||Constants.LOGIN_USER_TYPE_OWNER.equals(userType)){ %>
                    <a href="<%=path%>/info/queryInfoReceiverList.action"><s:text name="inforeceiver.management.list"/></a>   
                    <a  class="all_menu_a"><s:text name="inforeceiver.detail"/></a>
                    <%} %>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_bodyq">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="infosend.title"/></td>
                                <td >
                                <s:property value='infoReceiver.infoSend.title'/>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="infosend.content"/></td>
                                <td>
                                <s:property value="infoReceiver.infoSend.content"/></td>
                            </tr> 
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        
        </form>
        <script>
        $("input").attr("disabled","disabled");
		$("textarea").attr("disabled","disabled");
        </script>
</body>
</html>
