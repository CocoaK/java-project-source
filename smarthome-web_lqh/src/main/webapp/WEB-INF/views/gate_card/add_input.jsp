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
<OBJECT classid="clsid:8AD9C840-044E-11D1-B3E9-00805F499D93" id="cardReader" width="0" height="0"> 
<PARAM NAME="code" VALUE="CardReaderApplet.class"> 
<PARAM NAME="codebase" VALUE="../applets"> 
<PARAM NAME="type" VALUE="application/x-java-applet;version=1.6"> 
<PARAM NAME="ARCHIVE" VALUE="CardReaderApplet.jar,RXTXcomm.jar" > 
<PARAM NAME="scriptable" VALUE="true"> 
</OBJECT> 
<script type="text/javascript">
function process(){
	try{
         var ready = cardReader.isReady();
         if(ready){
            var msg = cardReader.getMsg();
            if(msg!=""){
            	htmlDoJob(msg);
            }
            cardReader.setReady(false);
       	 }
    }catch(e){}
         
         //setTimeout('process()',35);
}
//process();
window.setInterval("process()", 100);


function htmlDoJob(msg){
         $("#cardNo").val(msg);
}
</script>

    <body>
        <form action="<s:url action='save'/>" method="post">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action="queryList"/>"><s:text name="gatecard.management.list"/></a>
                    <a href="<s:url action='addInput'/>" class="all_menu_a"><s:text name="gatecard.management.add"/></a>
                    <!--
                    <a href="<s:url action="queryList" namespace='/gateCardVisit'/>"><s:text name="gatecard.management.gatecardvisit.list"/></a>
                    <a href="<s:url action="queryList" namespace='/idCardVisit'/>"><s:text name="gatecard.management.idcardvisit.list"/></a>
                     -->
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0" class="all_tab_body all_tab_bodys">
                        <tbody>
                        	<tr>
                                <td>当前房号</td>
                                <td>
                                    <s:property value="houseVO.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.name"/>&nbsp;
                                    <s:property value="houseVO.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name"/>区
                                    <s:property value="houseVO.THmBuildingCellInfo.THmRegionBuildingInfo.name"/>栋
                                    <s:property value="houseVO.THmBuildingCellInfo.name"/>单元
                                    <s:property value="houseVO.name"/>
                                    <i><font color="green">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请确认当前房号是否是需要授权的房号</font></i>
                                </td>
                            </tr>
                            <tr>
                                <td width="120"><dfn></dfn><s:text name="gatecard.cardno"/></td>
                                <td>
                                    <input type="text" size="30" maxlength="30" id="cardNo" name="gateCard.cardNo" class="{required:true,digits:true,maxlength:30}" onkeyup="hideTip('existCardNoMsg');" value="<s:property value='gateCard.cardNo'/>" />
                                    <label id="existCardNoMsg" class="error" style="display:none;"><s:text name="error.cardno.exist"/></label>
                                </td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="gatecard.name"/></td>
                                <td>
                                    <input type="text" maxlength="25" name="gateCard.ownerName" class="{required:true,maxlength:25}" value="<s:property value='gateCard.ownerName'/>" />
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
                            	    <input type="radio" name="gateCard.status" value="0" checked/><label><s:text name="gatecard.status.enable"/></label>
                                    <input type="radio" name="gateCard.status" value="1" <s:if test="gateCard.status != '' && gateCard.status == 1">checked</s:if>/><label><s:text name="gatecard.status.disable"/></label>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top"><dfn></dfn><s:text name="gatecard.permissions.select"/></td>
                                <td class="jur_tab_top room_hack">
                                    <p>
                                        <a href="javascript:void(0);queryDevices();" class="notetitle all_hover_but"><s:text name="common.element.action.select"/></a>
                                        <label id="unselectedDeviceMsg" class="error" style="display:none;"><s:text name="error.required"/></label>
                                    </p>
                                    <ul id="selectedDevices" class="all_tbs_checkbox">&nbsp;                                          
                                    </ul>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top"><s:text name="gatecard.permissions.set"/></td>
                                <td class="jur_tab_body">
                                    <p><em><s:text name="gatecard.gate"/></em><i><s:text name="gatecard.validtime"/></i></p>
                                    <ul id="selectedGates" class="jur_tab_hack">                                       
                                    </ul>
                                </td>
                            </tr>                                                                                                              
                        </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="button" onclick="saveGateCard('<s:url action="existCardNo" namespace="/json/gc"/>')" value="<s:text name='common.element.action.add' />" /></div>
                    <s:include value="/WEB-INF/views/gate_card/include_query_devices.jsp"/>
                    <s:if test="promptFlag == true">                                       
                        <div class="layer" id="confirmFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                    <li>
                                        <s:text name="common.add.success"/><br /><br />
                                    </li>
                                    <li>
                                        <a href="<s:url action='addInput'/>"><s:text name="common.element.action.confirm"/></a>
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
