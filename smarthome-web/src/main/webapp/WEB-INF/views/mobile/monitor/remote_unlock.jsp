<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="monitor.remoteUnlock" /></title>
<link href="<s:url value='/mobile/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<script type="text/javascript">
        	var requiredErrMsg = "<s:text name='error.mobile.required'/>";
        	function checkEmpty(){
            	var validateFlag = validatePwd();
            	if(validateFlag){
            		$("form")[0].submit();
                	$("#submitLogin").attr("disabled","disabled");
                }
            }
            
            function validatePwd(){
            	var pwdErrObj = $("#userPasswordError");
            	var pwdVal = $.trim($("#personalDevicePwd").val());
            	var pwdErrMsg = "";
            	if(pwdVal == ""){
            		pwdErrMsg = requiredErrMsg;
            	}
            	pwdErrObj.html(pwdErrMsg);          		
            	return (pwdErrMsg == "")?true:false;
            }
        </script>
</head>
<body>
    <form action="<s:url action="remoteUnlock"/>" method="post">
    <s:include value="/WEB-INF/views/mobile/head.jsp"/>
	<div class="list_top"><s:text name="monitor.remoteUnlock" /></div>
    <input type="hidden" name="personalDeviceCode" value="<s:property value="personalDeviceCode" />"/>
    	<table cellspacing="0" class="list_tab list_tab_cont">
        	<thead>
            	<tr>
            		<td class="tab_left"><s:text name="monitor.remoteUnlock.location" /></td>
            		<td>
				    	<s:if test="district">${district.name}</s:if>
				        <s:if test="house">${house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.name} ${house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name} ${house.THmBuildingCellInfo.THmRegionBuildingInfo.name} ${house.THmBuildingCellInfo.name} ${house.name}</s:if>
                    </td>
            	</tr>
				<tr>
                	<td class="tab_left"><s:text name="monitor.remoteUnlock.target" /></td>
                    <td>
                        <select name="targetDeviceCode">
                            <s:iterator value="targetDeviceList" var="targetDevice">
                            	<option value="${targetDevice.deviceCode}" <s:if test="#targetDevice.deviceCode!='' && #targetDevice.deviceCode == targetDeviceCode">selected</s:if>>${targetDevice.deviceName}</option>
                            </s:iterator>
                        </select>
                	</td>
              	</tr>
                <tr>
                	<td class="tab_left"><s:text name="monitor.remoteUnlock.password" /></td>
                	<td><ul><li><span id="userPasswordError"></span></li><li><input type="password" maxlength="40" name="personalDevicePwd" id="personalDevicePwd" onchange="validatePwd()" /></li></ul></td>
                </tr>
           	</thead>
    	</table>
       <div class="submit">
       		<a href="#" onclick="checkEmpty()"><s:text name="monitor.remoteUnlock.confirmUnlock" /></a>
       		<a href="<s:url action='remoteIndex' namespace='/mobile/monitor'/>" ><s:text name="common.element.action.return" /></a>
       </div>
       <s:if test="successFlag == true">
       		<div id="errorMsgFrame" class="layer">
       			<div><s:text name="common.element.action.operationSuccess" /><a href="remoteUnlockInput" onclick="javascript:hideTip('errorMsgFrame');"><s:text name="common.element.action.close" /></a></div>
       		</div>
       </s:if>
       <s:if test="successFlag == false && submitConfirmUnlock == true">
       		<div id="errorMsgFrame" class="layer">
       			<div><s:text name="login.action.input.wrong.psw"/><a href="#" onclick="javascript:hideTip('errorMsgFrame');"><s:text name="common.element.action.close" /></a></div>
       		</div>
       </s:if>
    </form>
</body>
</html>

