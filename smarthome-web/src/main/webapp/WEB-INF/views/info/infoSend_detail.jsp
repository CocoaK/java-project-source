<%@page import="com.biencloud.smarthome.web.info.vo.InfoReceiverVO"%>
<%@page import="com.biencloud.smarthome.web.common.util.DateTimeUtil"%>
<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@page import="com.biencloud.smarthome.web.info.service.impl.InfoSendServiceImpl"%>
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
<%
boolean isTree=request.getAttribute("treeList")==null?false:true;
request.setAttribute("isTree",isTree);
request.setAttribute("LOGINUSERTYPEOWNER",Constants.LOGIN_USER_TYPE_OWNER);
request.setAttribute("LOGINUSERTYPEPAUSER",Constants.LOGIN_USER_TYPE_PAUSER);
String userType=(String)request.getAttribute("userType");
request.setAttribute("INFOTYPEPERSON",InfoReceiverVO.INFO_TYPE_PERSON);
request.setAttribute("INFOTYPEPERSON",InfoReceiverVO.INFO_TYPE_PERSON);
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
        <jsp:include  page="../common/include_common.jsp"/>
        <script type="text/javascript" src="<s:url value='/js/validateUtil.js'/>"></script>
    </head>

    <body>
        <form action="<s:url action="saveInfoSend"/>" method="post" id="formname" name="formname">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<%=path%>/info/queryInfoSendList.action"><s:text name="infosend.management.list"/></a>
                        <a href="<%=path%>/info/goToInputInfoSend.action"><s:text name="infosend.add"/></a>
                        <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)||Constants.LOGIN_USER_TYPE_OWNER.equals(userType)){ %>
                    <a href="<%=path%>/info/queryInfoReceiverList.action"><s:text name="inforeceiver.management.list"/></a>   
                    <%} %>
                    <a  class="all_menu_a"><s:text name="inforeceiver.detail"/></a> 
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
                                <s:property value='infoSend.title'/>
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="infosend.content"/></td>
                                <td>
                                <s:property value="infoSend.content"/></td>
                            </tr> 
                            <s:if test="infoSend.type!=#request.INFOTYPEPERSON">
                            <tr>
                                <td><s:text name="infosend.sendmode"/></td>
                                <td><label id="nowsendLabel"><s:text name="infosend.nowsend"/></label>
                                <label id="timimgsendLabel" style="display: none"><s:text name="infosend.timimgsend"/></label>
                               </td>
                            </tr> 
                            <tr id="timingId" style="display: none;">
                                <td><s:text name="infosend.timimgtime"/></td>
                                <td>
                                <s:date name="infoSend.timimgTime" format="yyyy-MM-dd HH:mm" />
                                </td>
                            </tr> 
                            <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
                            <tr>
                                <td><s:text name="infosend.senddevice"/></td>
                                <td>
                                <ul class="">
				                    <s:iterator value="%{deviceTypes}" var="dt"
				                        status="st">
				                        <s:if test="#dt.deviceType!='01'">
					                        <li>
					                            <s:iterator value="%{#request.dts}" var="deviceType">
					                            	<s:if test="#dt.deviceType == #deviceType"><label><s:text name='#dt.deviceName' /></label></s:if>
					                          	</s:iterator> 
					                          	
					                        </li>
				                        </s:if>
				                    </s:iterator>
				                    <s:if test="#request.dts.size==0">无</s:if>
				                </ul>
                               </td>
                            </tr> 
                            <%} %>
                            <s:if test="#request.isTree">
                            <tr>
                                <td><s:text name="infosend.sendob"/></td>
                                <td><s:property value='infoSend.sendOb'/>
                                <s:if test="#request.LOGINUSERTYPEPAUSER == #session.login.userType">
                                	<jsp:include  page="../common/group_tree_dynamic.jsp">
                                	  <jsp:param value="detail" name="promt"/>
                                	</jsp:include>
		                         </s:if>
		                         <s:else>
		                         <jsp:include  page="../common/group_tree.jsp"/>
		                         </s:else>
                                </td>
                            </tr> 
                            </s:if>
                            </s:if>
                            <s:else>
                            <tr >
                                <td><s:text name="infosend.reply"/></td>
                                <td><s:property value='infoSend.reply'/>
                                </td>
                            </tr>
                            </s:else>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        </form>
       
	<script type="text/javascript">
		if ('<s:property value='infoSend.id'/>' != ''){
			if('<s:property value='infoSend.sendMode'/>' == '<%=InfoSendVO.SENDMODE_TIMIMG%>'){
				$("#timimgsendLabel").show();
				$("#nowsendLabel").hide();
				$("#timingId").show();
				$("#submitId").hide();
			}
		}
		$("#systemGroupSearch").hide();
	</script>
</body>
</html>
