<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="monitor" /></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
<jsp:include  page="../common/include_common.jsp"/>
</head>

<body>
    <form action="<s:url action="remoteUnlock"/>" method="post">
    <input type="hidden" name="personalDeviceCode" value="<s:property value="personalDeviceCode" />"/>
    <div class="main_section">
        <div class="all_aside">
            <div class="all_menu">
                <a href="#" class="all_menu_a"><s:text name="monitor.remoteUnlock" /></a>
                <s:if test="loginUserType == '01'">
                    <a href="<s:url action="remoteApplianceControl"/>"><s:text name="monitor.remoteApplianceControl" /></a>
                    <a href="<s:url action="remoteApplianceSceneControl"/>"><s:text name="monitor.remoteApplianceControl.scene" /></a>
                    <a href="<s:url action="deployment"/>"><s:text name="monitor.deployment" /></a>
                </s:if>
            </div>
        </div>
        <div class="main_outside">
            <div class="all_inside">
                <table cellspacing="0" class="all_tab_body all_tab_bodys">
                    <tbody>
                        <tr>
                            <td width="120"><s:text name="monitor.remoteUnlock.location" /></td>
                            <td>
				                <s:if test="district">${district.name}</s:if>
				                <s:if test="house">${house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.name} ${house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name} ${house.THmBuildingCellInfo.THmRegionBuildingInfo.name} ${house.THmBuildingCellInfo.name} ${house.name}</s:if>
                            </td>
                        </tr>
                        <tr>
                            <td><s:text name="monitor.remoteUnlock.target" /></td>
                            <td>
                                <div class="select_div">
                                    <select name="targetDeviceCode">
                                        <s:iterator value="targetDeviceList" var="targetDevice">
                                            <option value="${targetDevice.deviceCode}" <s:if test="#targetDevice.deviceCode!='' && #targetDevice.deviceCode == targetDeviceCode">selected</s:if>>${targetDevice.deviceName}</option>
                                        </s:iterator>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><s:text name="monitor.remoteUnlock.password" /></td>
                            <td><input type="password" name="personalDevicePwd" id="personalDevicePwd" class="{required:true}" /></td>
                        </tr>
                        <tr>
                            <td><s:text name="monitor.remoteUnlock.confirmPassword" /></td>
                            <td><input type="password" id="personalDevicePwdConfirm" class="{equalTo: '#personalDevicePwd'}" /></td>
                        </tr>
                    </tbody>
                </table>
                <div class="all_tab_butb"><input type="submit" value="<s:text name="monitor.remoteUnlock.confirmUnlock" />" /></div>
                <div class="layer none" id="confirmPassword">
                    <div>
                        <p><s:text name="common.system.info"/></p>
                        <ul>
                            <li>
                                <s:text name="monitor.remoteUnlock.confirmPasswordInfo"/>
                            </li>
                            <li>
                                <a href="#"><s:text name="common.element.action.confirm"/></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <s:if test="successFlag == true">                                       
                    <div class="layer" id="confirmFrame">
                        <div>
                            <p><s:text name="common.system.info"/></p>
                            <ul>
                                <li>
                                    <s:text name="common.element.action.operationSuccess"/>
                                </li>
                                <li>
                                    <a href="<s:url action="remoteUnlockInput"/>" onclick="$('#confirmFrame').hide();"><s:text name="common.element.action.confirm"/></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </s:if>
                <s:if test="successFlag == false && submitConfirmUnlock == true">
                    <div class="layer" id="confirmFrame">
                        <div>
                            <p><s:text name="common.system.info"/></p>
                            <ul>
                                <li>
                                    <s:text name="login.action.input.wrong.psw"/>
                                </li>
                                <li>
                                    <a href="#" onclick="$('#confirmFrame').hide();"><s:text name="common.element.action.confirm"/></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </s:if>
            </div>
        </div>
    </div>
    </form>
</body>
</html>

