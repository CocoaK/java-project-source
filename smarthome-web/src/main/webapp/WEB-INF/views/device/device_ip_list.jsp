<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="device.management"/></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/device/device.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/user/user.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<jsp:include  page="../common/include_common.jsp"/>

</head>

<body>
	<form name="form1" action="<s:url action="ipList"/>" method="post">  
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                    	<a href="<s:url action="deviceList"/>"><s:text name="device.management.list"/></a>
                        <a href="<s:url action="ipList"/>"  class="all_menu_a"><s:text name="device.ip.list"/></a>
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
					            <input type="hidden" id="selectedDistrictId" name=deviceIp.device.housingDistrictInfo.id value="<s:property value='deviceIp.device.housingDistrictInfo.id'/>" />
					            <input type="text" id="selectedDistrictName" size="30" name="deviceIp.device.housingDistrictInfo.name" value="<s:property value="deviceIp.device.housingDistrictInfo.name" />" readonly="readonly" class="all_tab_top_input"/>
					            <a href="javascript:void(0);queryDistricts('<s:url action="getDistrictsByName" namespace="/housemgr/json"/>');" class="notetitle all_hover_but but_left"><s:text name="common.element.action.select" /></a> 
					        </s:if>                      
                            <label class="all_border_right"><s:text name="device.ip.ipaddress"/></label>
                            <input type="text" maxlength="15" name="deviceIp.ipAddress" value="${deviceIp.ipAddress}" class="all_tab_top_input"/>
                            <label class="all_border_right"><s:text name="device.ip.subnet"/></label>
                            <input type="text" maxlength="3" name="deviceIp.subnet" value="${deviceIp.subnet}" class="all_tab_top_input"/>
                            <div class="click_div">
                            <strong class="screening_show search_but"><s:text name="common.element.action.moreFilter"/></strong>
                            <ul class="screening_body">
                            	<li>
	                            	<label><s:text name="device.ip.status"/></label>
	                            	<select name="deviceIp.status">
                                		<option value="">
                                			<s:text name="common.title.all"/>
                                		</option>
                                		<option value="0" <s:if test="deviceIp.status != '' && deviceIp.status == 0">selected</s:if>>
                                    		<s:text name="device.ip.status.false" />
                                		</option>
                                		<option value="1" <s:if test="deviceIp.status != '' && deviceIp.status == 1">selected</s:if>>
                                    		<s:text name="device.ip.status.true" />
                                		</option>                               
                            		</select>
	                            </li>
	                            <li>
	                            	<label class="all_border_right"><s:text name="device.devicename"/></label>
	                            	<input type="text" size="40" maxlength="40" name="deviceIp.device.deviceName" value="${deviceIp.device.deviceName}"/>
	                            </li>
	                            <li>
	                            	<label class="all_border_right"><s:text name="device.areaname"/></label>
	                            	<input type="text" maxlength="20" name="deviceIp.device.housingDistrictRegionInfo.name" value="${deviceIp.device.housingDistrictRegionInfo.name}" />
	                            </li>
	                            <li>
	                            	<label class="all_border_right"><s:text name="device.buildingname"/></label>
	                            	<input type="text" maxlength="20" name="deviceIp.device.regionBuildingInfo.name" value="${deviceIp.device.regionBuildingInfo.name}" />
	                            </li>
	                            <li>
	                            	<label class="all_border_right"><s:text name="device.unitname"/></label>
	                            	<input type="text" maxlength="20" name="deviceIp.device.buildingCellInfo.name" value="${deviceIp.device.buildingCellInfo.name}" /> 
	             				</li>
	             				<li>
	             					<label class="all_border_right"><s:text name="device.roomname"/></label>
	                            	<input type="text" maxlength="30" name="deviceIp.device.cellHouseholdInfo.name" value="${deviceIp.device.cellHouseholdInfo.name}" />
	                            </li>
                            </ul>
                            </div>
                            <input type="submit" onclick="queryPagingList()" value="<s:text name='common.element.action.search' />" class="search_but" />                  
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="device.ip.ipaddress"/></th>
                                    <th><s:text name="device.ip.subnet"/></th>
                                    <th><s:text name="device.ip.status"/></th>
                                    <th><s:text name="device.districtname"/></th>
                                    <th><s:text name="device.areaname"/></th>
                                    <th><s:text name="device.buildingname"/></th>
                                    <th><s:text name="device.unitname"/></th>
                                    <th><s:text name="device.roomname"/></th>
                                    <th><s:text name="device.devicename"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="deviceIp">
                                    <tr>
                                        <td><s:property value="#deviceIp.ipAddress"/></td>
                                        <td><s:property value="#deviceIp.subnet"/></td>  
                                        <td>
                                        	<s:if test="#deviceIp.status==1">
                                        		<s:text name="device.ip.status.true"/>
                                        	</s:if>
                                        </td>  
                                        <td><s:property value="#deviceIp.housingDistrictInfo.name"/></td>
                                        <td><s:property value="#deviceIp.device.housingDistrictRegionInfo.name"/></td>
                                        <td><s:property value="#deviceIp.device.regionBuildingInfo.name"/></td>
                                        <td><s:property value="#deviceIp.device.buildingCellInfo.name"/></td>
                                        <td><s:property value="#deviceIp.device.cellHouseholdInfo.name"/></td>
                                        <td><s:property value="#deviceIp.device.deviceName"/></td>                    
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <s:include value="/views/common/paging.jsp"/>
                        <s:if test="userType==03">
                        	<s:include value="/WEB-INF/views/device/include_select_district_msg.jsp"/> 
                        </s:if>                            
                    </div>
                </div>
            </div>
        </form>
    <script>
		QueryLoader.selectorPreload = "body";
		QueryLoader.init();
	</script>
 	
	<s:if test="successFlag == 'success'">                                       
    	<div class="layer" id="confirmFrame">
    		<div>
    			<p><s:text name="common.system.info"/></p>
    				<ul>
    					<li>
    						<s:text name="common.add.success"/>
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
    						<a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.confirm"/></a>
    					</li>
    				</ul>
    		</div>
    	</div>
    </s:if>												
</body>
</html>
