<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="device.management"/></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/validate/check.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/validate/validate.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<script type="text/javascript">
	//检查全部
   	function checkAll(){
   	alert("ipadd");
    	var ip = document.getElementById("deviceIp.ipAddress").value;
      	var result = true;
      	if(!checkEmpty("deviceIp.ipAddress","<s:text name='device.ip.empty'/>","ip")){
        	//document.getElementById("deviceIp.ipAddress").focus();
        	result = false;
        	return result;
		} if(!isIP(ip)){
      		document.getElementById("ip").innerHTML='<s:text name="device.ip.format.error"/>';
     		result = false;
      	}
      return result;
	}
	function clearErrInfo(errid){
     	document.getElementById(errid).innerHTML="";
   	 }
</script>
</head>

<body>
	<form action="<s:url action="ipAdd"/>" onsubmit="return checkAll()" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                    	<a href="deviceList"><s:text name="device.management.list"/></a>
                    	<a href="ipList.action"><s:text name="device.ip.list"/></a>
                        <a href="#" class="all_menu_a"><s:text name="device.ip.add"/></a>
                    </div>
                </div>
				<div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody class="all_tab_body">
                            <tr>
                                <td width="20%"><s:text name="device.ip.add"/></td>
                                <td>
                                	<input type="text" size="15" id="deviceIp.ipAddress" name="deviceIp.ipAddress" onfocus="clearErrInfo('ip')"/>
                                	<div id="ip"><s:text name="device.ip.add.declare"/></div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="submit" value="<s:text name='common.element.action.add'/>"/></div>
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
