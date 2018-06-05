<%@page import="com.biencloud.smarthome.web.systemgroup.vo.SystemGroupVO"%>
<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String path=request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="deviceno.management"/></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/dtree.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
</head>

<body>
	<form action="<s:url action="querydeviceNoList"/>" onsubmit="return searchData();" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="#" class="all_menu_a"><s:text name="deviceno.management.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                            <label class="all_border_right"><s:text name="deviceno.devicecode"/></label>
                            <input type="text" name="deviceNo.deviceNo" id="deviceNo.deviceNo" value="${deviceNo.deviceNo}" class="all_tab_top_input" maxlength="100"/>
                            <label class="all_border_right"><s:text name="deviceno.devicename"/></label>
                            <input type="text" name="deviceNo.deviceName" id="deviceNo.deviceName" value="${deviceNo.deviceName}" class="all_tab_top_input" maxlength="50"/>
                            <label class="all_border_right"><s:text name="deviceno.areaName"/></label>
                            <input type="text" name="areaName" id="areaName"  class="all_tab_top_input" maxlength="20"/>                   
                            <input type="submit" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="deviceno.devicecode"/></th>
                                    <th><s:text name="deviceno.devicename"/></th>
                                    <th><s:text name="deviceno.devicetype"/></th>
                                    <th><s:text name="deviceno.devicestatus"/></th>
                                    <th><s:text name="deviceno.districtname"/></th>
                                    <th><s:text name="deviceno.areaName"/></th>
                                    <th><s:text name="deviceno.buildingname"/></th>
                                    <th><s:text name="deviceno.unitname"/></th>
                                    <th><s:text name="deviceno.roomname"/></th>
                                    <th><s:text name="deviceno.createtime"/></th> 
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="deviceno">
                                    <tr>
                                        <td>
                                            <a href="<%=path%>/device/deviceDetail.action?deviceId=<s:property value='#deviceno.device.deviceId'/>">
                                                <s:property value="#deviceno.deviceNo"/>
                                            </a>
                                        </td>
                                        <td><s:property value="#deviceno.device.deviceName"/></td>
                                        <td><s:property value="#deviceno.device.deviceType.deviceName"/></td>
                                       <td>
                                        	<s:if test="#deviceno.device.deviceStatus==1">
                                        		<s:text name="device.devicestatus.online"/>
                                        	</s:if>
                                        	<s:if test="#deviceno.device.deviceStatus==0">
                                        		<s:text name="device.devicestatus.offline"/>
                                        	</s:if>
                                        </td>    
                                        <td><s:property value="#deviceno.device.housingDistrictInfo.name"/></td>
                                        <td><s:property value="#deviceno.device.housingDistrictRegionInfo.name"/></td>
                                        <td><s:property value="#deviceno.device.regionBuildingInfo.name"/></td>
                                        <td><s:property value="#deviceno.device.buildingCellInfo.name"/> </td>
                                       <td><s:property value="#deviceno.device.cellHouseholdInfo.name"/> </td>
                                        <td><s:date name="#deviceno.device.createdTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <s:include value="/views/common/paging.jsp"/>     
                        <div id="list_click" style="display:none;" class="layers">
		                	<div>
		                    	<p><strong><s:text name='deviceno.searchresults'/></strong><span style="float: right;"><a href="#" class="closediv">X</a></span></p>
		                        <ul class="layers_search_list" id="searchResult">
		                        </ul>
		                    </div>
            			</div>                               
                    </div>
                </div>
            </div>
        </form>
	<script>
		function searchData(){
			var areaName=$("#areaName").val();
			if(areaName!='') {
				searchArea(areaName);
				return false;
			}else return true;
		}
		 function searchArea(areaName){
        	 $.ajax({
          		type: "post",
          		url: "<%=path%>/sysgroup/querySystemGroupComList.action",
          		data:"groupName="+areaName+"&<%=SystemGroupVO.SELECTCOM_FLAG%>",
          		beforeSend: function(XMLHttpRequest){
          			//ShowLoading();
          		},
          		success: function(data, textStatus){
          			$("#searchResult").html("");
          			/*$("item",data).each(function(i, domEle){
          				$(".ajax.ajaxResult").append("<li>"+$(domEle).children("title").text()+"</li>");
          			});*/
          			$("#searchResult").append(data);
          			$("#list_click").show();
          		},
          		complete: function(XMLHttpRequest, textStatus){
          			//HideLoading();
          		},
          		error: function(){
          			//请求出错处理
          		}
          });
         }
		 function linkFunction(value){
        	 $("#list_click").hide();
        	 document.location.href="<%=path%>/deviceno/querydeviceNoList.action?areaId="+value;
         }
	</script>
</body>
</html>
