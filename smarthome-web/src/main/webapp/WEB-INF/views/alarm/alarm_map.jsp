<%@page import="com.biencloud.smarthome.web.alarm.vo.AlarmVO"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
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
<% String path=request.getContextPath(); 
Boolean showDetail=request.getAttribute(ActionUtils.SHOWDETAIL)==null?false:(Boolean)request.getAttribute(ActionUtils.SHOWDETAIL); %>
<%List<Map<String,String>> houseTextList=request.getAttribute("houseTextList")==null?null:(List<Map<String,String>>)request.getAttribute("houseTextList");
List<Map<String,String>> useMsgTextList=request.getAttribute("useMsgTextList")==null?null:(List<Map<String,String>>)request.getAttribute("useMsgTextList");
String districtMapPath=(String)request.getAttribute("districtMapPath");
String fileServerUrl=(String)request.getAttribute("fileServerUrl");
request.setAttribute("LOGINUSERTYPEPAUSER",Constants.LOGIN_USER_TYPE_PAUSER);
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
    </head>

    <body>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<%=path%>/alarm/queryAlarmList.action"><s:text name="alarm.management.list"/></a>
                    <s:if test="#request.LOGINUSERTYPEPAUSER == #session.login.userType"> <a href="<%=path%>/alarm/getAlarmMapMsg.action" class="all_menu_a"><s:text name="alarm.management.map"/></a></s:if>
                </div>
            </div>
            <div class="main_outside">
            <div class="all_inside">
                <div class="mapxyshow">
                	<div id="map" style="width:810px; position:relative; margin-bottom:10px;">
                	<%-- <img src="<%=path %>/images/map.png" />--%>
                		<s:if test="#request.districtMapPath==''">暂无未处理和未取消记录</s:if>
                     	<s:else><img src="${fileServerUrl}${districtMapPath}" /></s:else>
                    	<% 
                               if(houseTextList!=null){
                            	   String houseNoText="";
                            	   for (int index = 0, size = houseTextList.size(); index < size; index++) {
                            		   Map<String,String> houseMap=houseTextList.get(index);
                            		   String houseNo=houseMap.get("houseNo");
                            		   Integer count=1;
                            		   String strs[]=houseNo.split(",");
                            		   for(int i=0;i<strs.length;i++){
                            			   String houserCount=houseMap.get(strs[i])==null?1+"":houseMap.get(strs[i]);
                            			   houseNoText+=strs[i]+"("+houserCount+"次)";
                            			   if(i!=strs.length-1)   houseNoText+=",";
                            		   }
                            		   if(StringUtils.isNotBlank(houseNo)) count=houseNo.split(",").length; 
                            %>
                        <p style="left:<%=houseMap.get("XCoordinates")%>px; top:<%=houseMap.get("YCoordinates")%>px;"><span></span><a href="javascript:showUserMsg('<%=houseMap.get("uniqueFlag")%>');"><%=houseNoText%><i><%=count%>户</i></a></p>
                        <%}} %>
                    </div>
                </div>
                <table cellspacing="0" class="all_tab_body">
                    <thead class="all_tab_thead">
                        <tr>
                            <th><s:text name="common.title.name"/></th>
                            <th><s:text name="common.title.address"/></th>
                            <%--<th><s:text name="common.title.workunit"/></th>--%>
                            <th><s:text name="common.title.officephone"/></th>
                            <th><s:text name="common.title.emergencycontact"/></th>
                            <th><s:text name="alarm.status"/></th>
                            <th><s:text name="common.element.title.action"/></th>
                        </tr>
                    </thead>
                    <tbody id="tbodyId">
                    <% 
                               if(useMsgTextList!=null){
                            	   for (int index = 0, size = useMsgTextList.size(); index < size; index++) {
                            		   Map<String,String> usermsgMap=useMsgTextList.get(index);
                            %>
                        <tr id="<%=usermsgMap.get("uniqueFlag")%>" style="display: none;">
                            <td><%=usermsgMap.get("userName")%></td>
                            <td><%=usermsgMap.get("houseText")%></td>
                           <%-- <td><%=usermsgMap.get("workUnit")%></td>--%>
                            <td><%=usermsgMap.get("companyPhone")%></td>
                            <td><%=usermsgMap.get("contact")%></td>
                            <td>
                            <% if(usermsgMap.get("status").toString().equals(AlarmVO.HANLDER_STATUS_CANCEL.toString())){ %>
                            <s:text name="alarm.statuscancel"/>
                            <%}else if(usermsgMap.get("status").toString().equals(AlarmVO.HANLDER_STATUS_NO.toString())){ %>
                            <s:text name="alarm.statusno"/>
                            <%}else if(usermsgMap.get("status").toString().equals(AlarmVO.HANLDER_STATUS_YES.toString())){ %>
                            <s:text name="alarm.statusyes"/>
                            <%} %>
                            </td> 
                            <td>
                            <% if(!usermsgMap.get("status").toString().equals(AlarmVO.HANLDER_STATUS_YES.toString())){ %>
                            <s:if test="#request.LOGINUSERTYPEPAUSER == #session.login.userType">
                            <a href="javascript:upateStatus(<%=usermsgMap.get("AlarmId")%>);" class="all_hover_but"><s:text name="common.element.action.finish"/></a>
                            <input type="hidden" value="" id="requsetUniqueFlag"/>
                            </s:if>
                            <%} %>
                            </td>
                        </tr>
                        <%}} %>
                    </tbody>
                </table>
            </div>
        </div>
        </div>
        <script>
        	function showUserMsg(uniqueFlag){
        		if(uniqueFlag!=null){
        			$("#requsetUniqueFlag").val(uniqueFlag);
        			var trIds=uniqueFlag.split(",");
        			document.getElementById("tbodyId").style.display="none";
        			$("tbody > tr").css("display","none");
        			for(var i=0;i<trIds.length;i++){
        				document.getElementById(trIds[i]).style.display="";
        				//$(trIds[i]).show();
        			}
        			document.getElementById("tbodyId").style.display="";
        			//$("tbodyId").show();
        		} 
         	}
        	function upateStatus(alarmId){
        		var requsetUniqueFlag=$("#requsetUniqueFlag").val();
        		document.location.href="<%=path%>/alarm/updateAlarmStatus.action?requestId="+alarmId+"&status=<%=AlarmVO.HANLDER_STATUS_YES%>&requsetUniqueFlag="+requsetUniqueFlag+"&map";
        	}
        	if('${requsetUniqueFlag}'!='') showUserMsg('${requsetUniqueFlag}');
        </script>
</body>
</html>
