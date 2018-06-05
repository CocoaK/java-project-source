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
	<form action="<s:url action="deviceList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="<s:url action="deviceList"/>" class="all_menu_a"><s:text name="device.management.list"/></a>
                        <s:if test="userType!=01">
                        	<a href="<s:url action="ipList"/>"><s:text name="device.ip.list"/></a>
                        </s:if>
                        <s:if test="userType!=03">
                        	<a href="<s:url action="messageList"/>"><s:text name="device.message.list"/></a>
                        </s:if>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">
                        <s:if test="userType==03">
                        	<label><s:text name="device.districtname"/></label>
				            <input type="hidden" id="selectedDistrictId" name=device.housingDistrictInfo.id value="<s:property value='device.housingDistrictInfo.id'/>" />
				             <input type="text" id="selectedDistrictName" size="20" name="device.housingDistrictInfo.name" value="<s:property value="device.housingDistrictInfo.name" />" class="all_tab_top_input"/>
				            <a href="javascript:void(0);queryDistricts('<s:url action="getDistrictsByName" namespace="/housemgr/json"/>');" class="notetitle all_hover_but but_left"><s:text name="common.element.action.select" /></a> 
				        </s:if>                     
                            <label class="all_border_right"><s:text name="device.devicecode"/></label>
                            <input type="text" maxlength="20" name="device.deviceCode" value="${device.deviceCode}" class="all_tab_top_input"/>
                            <label class="all_border_right"><s:text name="device.devicename"/></label>
                            <input type="text" maxlength="20" name="device.deviceName" value="${device.deviceName}" class="all_tab_top_input"/>
                            <label class="all_border_right"><s:text name="device.devicetype"/></label>
                            <div class="select_div">
	                            <select name="device.deviceType.deviceType">
		                        	<option value="">
		                        		<s:text name="common.title.all"/>
		                            </option>
		                            <s:iterator value="%{deviceTypes}" var="dtype">
		                            	<option value="<s:property value='#dtype.deviceType'/>" <s:if test="device.deviceType.deviceType != '' && device.deviceType.deviceType == #dtype.deviceType">selected</s:if>>
		                            		<s:property value="#dtype.deviceName" />
		                            	</option>
		                            </s:iterator>                              
		                        </select>
	                        </div>
                            <div class="click_div">
                            <strong class="screening_show search_but"><s:text name="common.element.action.moreFilter"/></strong>
                            <ul class="screening_body">
                            	<li>
	                            	<label><s:text name="device.devicestatus"/></label>
	                            	<div class="select_div">
		                            	<select name="device.deviceStatus">
	                                		<option value="">
	                                			<s:text name="common.title.all"/>
	                                		</option>
	                                		<option value="0" <s:if test="device.deviceStatus != '' && device.deviceStatus == 0">selected</s:if>>
	                                    		<s:text name="device.devicestatus.offline" />
	                                		</option>
	                                		<option value="1" <s:if test="device.deviceStatus != '' && device.deviceStatus == 1">selected</s:if>>
	                                    		<s:text name="device.devicestatus.online" />
	                                		</option>                               
	                            		</select>
                            		</div>
	                            </li>

	                            <li>
	                            	<label><s:text name="device.areaname"/></label>
	                            	<input type="text" maxlength="20" name="device.housingDistrictRegionInfo.name" value="${device.housingDistrictRegionInfo.name}" />
	                            </li>
	                            <li>
	                            	<label><s:text name="device.buildingname"/></label>
	                            	<input type="text" maxlength="20" name="device.regionBuildingInfo.name" value="${device.regionBuildingInfo.name}" /> 
	                            </li>
	                            <li>
	                            	<label><s:text name="device.unitname"/></label>
	                            	<input type="text" maxlength="20" name="device.buildingCellInfo.name" value="${device.buildingCellInfo.name}" /> 
	                            </li>
	                            <li><label><s:text name="device.roomname"/></label>
	                            	<input type="text" maxlength="20" name="device.cellHouseholdInfo.name" value="${device.cellHouseholdInfo.name}" /> 
	                            </li>                 
	                       	</ul>
	                       	</div>  
	                       	<input type="button" value="<s:text name='common.element.action.search' />" onclick="queryPagingList()" class="search_but" />
	                       	<input type="button" value="<s:text name='device.ping' />" onclick="ping('<s:url action="netAction_toPing" namespace="/net"></s:url>','')" class="search_but" />
                                              
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="device.devicecode"/></th>
                                    <th><s:text name="device.devicename"/></th>
                                    <th><s:text name="device.devicetype"/></th>
                                    <th width="50"><s:text name="device.devicestatus"/></th>
                                    <th><s:text name="device.districtname"/></th>
                                    <th><s:text name="device.areaname"/></th>
                                    <th><s:text name="device.buildingname"/></th>
                                    <th><s:text name="device.unitname"/></th>
                                    <th width="50"><s:text name="device.roomname"/></th>
                                    <th width="50"><s:text name="device.position"/></th>
                                    <!-- <th width="110"><s:text name="device.devicemac"/></th> -->
                                    <th width="120"><s:text name="device.createdtime"/></th>
                                    <s:if test="userType==03">
                                    	<th width="120">SIP账号</th>
                                    </s:if>
                                    <s:if test="userType!=01">
                                    	<th><s:text name="common.element.title.action"/></th>
                                    </s:if>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="device">
                                    <tr>
                                        <td>
                                            <a href="<s:url action="deviceDetail"><s:param name="deviceId" value="#device.deviceId"/></s:url>">
                                                <s:property value="#device.deviceCode"/>
                                            </a>
                                        </td>
                                        <td class="text_left"><s:property value="#device.deviceName"/></td>
                                        <td><s:property value="#device.deviceType.deviceName"/></td>  
                                        <td>
                                        	<s:if test="#device.deviceStatus==1">
                                        		<s:text name="device.devicestatus.online"/>
                                        	</s:if>
                                        	<s:if test="#device.deviceStatus==0">
                                        		<s:text name="device.devicestatus.offline"/>
                                        	</s:if>
                                        </td>  
                                        <td><s:property value="#device.housingDistrictInfo.name"/></td> 
                                        <td><s:property value="#device.housingDistrictRegionInfo.name"/></td> 
                                        <td><s:property value="#device.regionBuildingInfo.name"/></td>
                                        <td><s:property value="#device.buildingCellInfo.name"/></td> 
                                        <td><s:property value="#device.cellHouseholdInfo.name"/></td>
                                        <td><s:property value="#device.position" /></td>
                                        <!-- <td><s:property value="#device.deviceMac"/></td>  -->
                                        <td><s:date name="#device.createdTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                        
                                        <s:if test="userType==03">
	                                        <s:if test="#device.sipid==''">
	                                        	<td width="180">
	                                        		<font id="label<s:property value='#device.deviceId'/>"></font>
	                                        		<input id="sip<s:property value='#device.deviceId'/>" type="text" size="15"><s:property value="#device.sipid"/></input>
	                                        		<a href="#" id="a<s:property value='#device.deviceId'/>" class="all_hover_but" onClick="postAdd(<s:property value='#device.deviceId'/>)">绑定</a>
	                                        	</td>
	                                        </s:if>
	                                        <s:else>
	                                        	<td><s:property value="#device.sipid"/></td>
	                                        </s:else>
                                        	<td>
                                        	<a href="<%=path %>/push/pushAction_queryByDeviceNo.action?pushVO.pushClientId=<s:property value="#device.deviceCode"/>&pushVO.pushKind=all&macAddr=<s:property value="#device.deviceMac"/>&deviceNumber=<s:property value="#device.deviceCode"/>" class="all_hover_but"><s:text name="device.data.trace"/></a>
                                        	<s:if test="#device.deviceStatus==0">
                                        		<a href="javascript:confirmAction('<s:url action="removeDevice"><s:param name="deviceId" value="#device.deviceId"/></s:url>','<s:text name="common.confirm.remove"/>')" class="all_hover_but"><s:text name="common.element.action.remove"/></a>
                                        	</s:if>
                                        	<s:else>
                                        		<a href="#" class="all_hover_but opacity"><s:text name="common.element.action.remove"/></a>
                                        	</s:else>
                                        	<!-- 
                                        	<a href="javascript:ping('<s:url action="netAction_toPing" namespace="/net"><s:param name="net.ip" value="#device.deviceIp"/></s:url>','ping')" class="all_hover_but"><s:text name="device.ping"/></a>
                                        	  -->
                                        	</td>
                                        </s:if>
                                        <s:if test="userType==02">
                                        	<td>
                                        	<s:if test="#device.deviceStatus==0">
                                        		<a href="javascript:confirmAction('<s:url action="removeDevice"><s:param name="deviceId" value="#device.deviceId"/></s:url>','<s:text name="common.confirm.remove"/>')" class="all_hover_but"><s:text name="common.element.action.remove"/></a>
                                        	</s:if>
                                        	<s:else>
                                        		<a href="#" class="all_hover_but opacity"><s:text name="common.element.action.remove"/></a>
                                        	</s:else>
                                        	<s:if test="#device.deviceType.deviceType == 02 || #device.deviceType.deviceType == 06" >
                                        		<a href='<s:url action="updateCoordinateDetail"><s:param name="deviceId" value="#device.deviceId"/></s:url>' class="all_hover_but"><s:text name="housemgr.update.location"/></a>
                                        	</s:if>
                                        	<s:else>
                                        		<a href="#" class="all_hover_but opacity"><s:text name="housemgr.update.location"/></a>
                                        	</s:else>
                                        	</td>
                                        </s:if>                                                       
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <s:include value="/views/common/paging.jsp"/>
                        <s:include value="/WEB-INF/views/device/include_select_district_msg.jsp"/>   
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
                        
                        <s:if test="successFlag == 'success'">                                       
                        <div class="layer" id="noticeFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                    <li>
                                        <s:text name="common.element.action.operationSuccess"/>
                                    </li>
                                    <li>
                                        <a href="javascript:hideTip('noticeFrame');"><s:text name="common.element.action.confirm"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </s:if>
                    <s:if test="successFlag == 'fail'">                                       
                        <div class="layer" id="noticeFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                    <li>
                                        <s:text name="common.element.action.operationfailed"/>
                                    </li>
                                    <li>
                                        <a href="javascript:hideTip('noticeFrame');"><s:text name="common.element.action.confirm"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </s:if>   
                    <s:if test="successFlag == 'online'">                                       
                        <div class="layer" id="noticeFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                	<li>
                                        <s:text name="common.element.action.operationfailed"/>
                                    </li>
                                    <li>
                                        <s:text name="device.status.online"/>
                                    </li>
                                    <li>
                                        <a href="javascript:hideTip('noticeFrame');"><s:text name="common.element.action.confirm"/></a>
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
