<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="gatecard.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/gatecard/gc.js'/>"></script>
        <s:include value="/WEB-INF/views/common/include_common.jsp"></s:include>
    </head>

    <body>
        <form action="<s:url action='update'/>" method="post">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action="queryList"/>"><s:text name="gatecard.management.list"/></a>
                    <a href="<s:url action="updateInput"><s:param name="gateCardId" value="gateCard.gateCardId"/></s:url>" class="all_menu_a"><s:text name="gatecard.management.update"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0" class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><dfn></dfn><s:text name="gatecard.cardno"/></td>
                                <td>
                                    <input type="text" size="30" maxlength="30" id="cardNo" name="gateCard.cardNo" class="{required:true,digits:true,maxlength:30}" onkeyup="hideTip('existCardNoMsg');" value="<s:property value='gateCard.cardNo'/>" />
                                    <label id="existCardNoMsg" class="error" style="display:none;"><s:text name="error.cardno.exist"/></label>
                                    <input type="hidden" id="gateCardId" name="gateCard.gateCardId" value="<s:property value='gateCard.gateCardId'/>"/>
                                </td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="gatecard.name"/></td>
                                <td>
                                    <input type="text" size="10" maxlength="25" name="gateCard.ownerName" class="{required:true,maxlength:25}" value="<s:property value='gateCard.ownerName'/>" />
                                </td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="gatecard.idcard"/></td>
                                <td>
                                    <input type="text" size="30" maxlength="30" name="gateCard.ownerIdCard" class="{required:true,idcard:true}" value="<s:property value='gateCard.ownerIdCard'/>" />
                                    <input type="hidden" name="gateCard.houseId" value="<s:property value='gateCard.houseId'/>" />
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="gatecard.status"/></td>
                                <td class="all_tbs_radio">
                                    <input type="radio" name="gateCard.status" value="0" <s:if test="gateCard.status == 0">checked</s:if>/><label><s:text name="gatecard.status.enable"/></label>
                                    <input type="radio" name="gateCard.status" value="1" <s:if test="gateCard.status == 1">checked</s:if>/><label><s:text name="gatecard.status.disable"/></label>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top"><dfn></dfn><s:text name="gatecard.permissions.select"/></td>
                                <td class="jur_tab_top room_hack">
                                    <p>
                                        <a href="javascript:void(0);queryDevices();" class="notetitle all_hover_but"><s:text name="common.element.action.select"/></a>
                                        <label id="unselectedDeviceMsg" class="error" style="display:none;"><s:text name="error.required"/></label>
                                    </p>
                                    <ul id="selectedDevices" class="all_tbs_checkbox">
                                        <s:iterator value="%{gateCard.gatePermissions}" var="gp">
                                                <li id='<s:property value="#gp.device.deviceId"/>'><label><s:property value="#gp.device.deviceAlias"/></label><input name='selectedDeviceId' type='checkbox' value='<s:property value="#gp.device.deviceId"/>' checked disabled/></li>
                                            </s:iterator>                                          
                                        </ul>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top"><s:text name="gatecard.permissions.set"/></td>
                                <td class="jur_tab_body">
                                    <p><em><s:text name="gatecard.gate"/></em><i><s:text name="gatecard.validtime"/></i></p>
                                    <ul id="selectedGates" class="jur_tab_hack">  
                                        <s:iterator value="%{gateCard.gatePermissions}" var="gp" status="sta">
                                            <li id='<s:property value="#gp.device.deviceId"/>'>
                                                <strong class='jur_tab_long'><s:property value="#gp.device.deviceAlias"/></strong>
                                                <input type='hidden' name='gatePermissions.gatePermissionsId' value='<s:property value="#gp.gatePermissionsId"/>'/>
                                                <input type='hidden' name='gatePermissions.device.deviceId' value='<s:property value="#gp.device.deviceId"/>'/> 
                                                <input type='text' id="beginTime<s:property value='#gp.device.deviceId'/>" timeId="<s:property value='#gp.device.deviceId'/>" name='gatePermissions.beginTime' value='<s:date name="#gp.beginTime" format="yyyy-MM-dd HH:mm" />' dateFormat='yyyy-MM-dd hh:mm' onclick='SelectDate(this,"yyyy-MM-dd hh:mm",0,-150)' onchange="validateDR(this, $('#endTime<s:property value="#gp.device.deviceId"/>'))" readonly='true' /> 
                                                <strong>---</strong>
                                                <input type='text' id="endTime<s:property value='#gp.device.deviceId'/>" timeId="<s:property value='#gp.device.deviceId'/>" name='gatePermissions.endTime' value='<s:date name="#gp.endTime" format="yyyy-MM-dd HH:mm" />' dateFormat='yyyy-MM-dd hh:mm' onclick='SelectDate(this,"yyyy-MM-dd hh:mm",0,-150)' onchange="validateDR($('#beginTime<s:property value="#gp.device.deviceId"/>'), this)" readonly='true' /> 
                                                <a href='javascript:removeGate(<s:property value="#gp.device.deviceId"/>)' class='all_hover_but'><s:text name='common.element.action.remove'/></a>
                                                <label id="notGreaterBeginTimeMsg<s:property value="#gp.device.deviceId"/>" class='error' style='display:none;'></label></li>
                                            </li>                                          
                                        </s:iterator>                                  
                                    </ul>
                                </td>
                            </tr>                                                                                                              
                        </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="button" onclick="updateGateCard('<s:url action="existCardNo" namespace="/json/gc"/>')" value="<s:text name='common.element.action.confirm.update' />" /></div>
                    <s:include value="/WEB-INF/views/gate_card/include_query_devices.jsp"/>                   
                    <s:if test="promptFlag == true">                                       
                        <div class="layer" id="confirmFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                    <li>
                                        <s:text name="common.update.success"/><br /><br />
                                    </li>
                                    <li>
                                        <a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.confirm"/></a>
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
            var removeButName = "<s:text name='common.element.action.remove'/>";
            var targetUrl = "<s:url action='queryGateCardDevices' namespace='/json'/>";
            var dateRangeMsg = "<s:text name='error.begintime.notgreater'/>";
            var beginTimeMsg = "<s:text name='error.begintime.notgreater.now'/>";
            var endTimeMsg = "<s:text name='error.endtime.notgreater.now'/>";                     
        </script>
    </body>
</html>