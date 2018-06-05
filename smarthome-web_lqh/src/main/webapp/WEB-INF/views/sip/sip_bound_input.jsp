<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>绑定SIP账号</title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/qrcode/gc.js'/>"></script>
<s:include value="/WEB-INF/views/common/include_common.jsp"></s:include>

</head>

<body>
	<form action="<s:url action="boundHouse"/>" method="post" onsubmit="return toVaild()">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="#" class="all_menu_a">绑定SIP账号</a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <table cellspacing="0" class="all_tab_body">
                            <tbody>
	                            <tr>
	                                <td width="150"><dfn></dfn>绑定SIP账号-<s:property value='userRoomNoVo.roomNo'/></td>
	                                <td colspan="3">
	                                	<input type="text" maxlength="20" name="sipid" class="{required:true, mobilephone:true}" value="<s:property value='sipid'/>" />
	                                	<input type="hidden" name="qrcodeVO.houseId" value="<s:property value='qrcodeVO.houseId'/>" />
	                                </td>
	                            </tr>
	                            <tr>
	                            	<td valign="top"><dfn></dfn>门口机</td>
                                	<td class="jur_tab_top room_hack" colspan="3">
                                		<input type="hidden" id="doorSipid" name="qrcodeVO.doorSipid" class="{required:true}"/>
                                        <a href="javascript:void(0);queryDevices();" class="notetitle all_hover_but"><s:text name="common.element.action.select"/></a>
                                        <label id="noSip" class="error" style="display:none;">门口机设备未绑定SIP账号！</label>
                                        <label id="unselectedDeviceMsg"></label>
                                    	<label id="selectedDevices" class="all_tbs_checkbox"></label>
                                </td>
	                            </tr>
                        	</tbody>
                        </table>
                        <div class="all_tab_butb"><input id="btn" type="submit" value="<s:text name='common.element.action.add' />" /></div>
                    	<s:include value="/WEB-INF/views/housemgr/include_result_msg.jsp"/>
                    	<s:include value="/WEB-INF/views/qrcode/include_query_devices.jsp"/>
                    </div>
                </div>
            </div>
        </form>
    <script>
    	var targetUrl = "<s:url action='queryGateCardDevices' namespace='/json'/>";
		QueryLoader.selectorPreload = "body";
		QueryLoader.init();
	</script>
</body>
</html>
