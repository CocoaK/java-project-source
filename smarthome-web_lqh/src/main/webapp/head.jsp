<%@page import="com.biencloud.smarthome.web.login.vo.LoginVO"%>
<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
LoginVO vo=(LoginVO)session.getAttribute(Constants.KEY_LOGIN_SESSION);
String userType=vo.getUserType();
String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath()+ "/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
<title>Head</title>
</head>
<script type="text/javascript">
    function locationAction(url){      
      	window.location.href =url;	  	  
    }
    
    //首页
    function retHome(url){
      	$("a[class='navad']").removeAttr("class");      	
      	window.parent.parent.main.location.href=url;
    }
    
    //退出
    function exit(url){
    if(confirm("<s:text name='common.confirm.exit'/>"))
       
      	window.parent.parent.location.href=url;
    }
</script>
<script type="text/javascript">
	/**function getNoReadReceiverCount(){
		$.ajax({
	  		type: "post",
	  		url: "<%=path%>/info/getNoReadReceiverCount.action",
	  		beforeSend: function(XMLHttpRequest){
	  			//ShowLoading();
	  		},
	  		success: function(data, textStatus){
	  			if(data ==null || data == ""){
	  				data = 0;
	  			}
	  			$("#noreadcount").html(data);
	  		},
	  		complete: function(XMLHttpRequest, textStatus){
	  			//HideLoading();
	  		},
	  		error: function(){
	  			//请求出错处理
	  		}
	   });
	}
	function Content()		
		{	
			$.ajax({
	      		type: "post",
	      		url: "<%=path%>/alarm/queryAlarmString.action",
	      		beforeSend: function(XMLHttpRequest){
	      			//ShowLoading();
	      		},
	      		success: function(data, textStatus){
	      			if(data!="" && data!=null){
	      				$("#alarm").html("<ul class=index_head_prompts>"+data+"</ul><div class=prompts_a><a href='<%=path%>/alarm/getAlarmMapMsg.action' target=main><s:text name='head.alarm'/></a></div>");
	      				ring();
	      			}
	      		},
	      		complete: function(XMLHttpRequest, textStatus){
	      			//HideLoading();
	      		},
	      		error: function(){
	      			//请求出错处理
	      		}
	       });
			//$("#alarm").html("<ul class='index_head_prompts'><li><span>火警</span><a href=#>A区B栋1单元506房间</a></li><li><strong>匪警</strong><a href=#>B区C栋5单元201房间</a></li><li><i>设备</i><a href=#>B区C栋5单元201房间</a></li></ul><div class=prompts_a><a href='<%=path%>/alarm/getAlarmMapMsg.action' target=main>报警</a></div>");        
		} 
		
		function device()		
		{	
			$.ajax({
	      		type: "post",
	      		url: "<%=path%>json/queryDeviceList.action?device.deviceStatus=<%=Constants.DEVICE_OFFLINE%>",
	      		beforeSend: function(XMLHttpRequest){
	      			//ShowLoading();
	      		},
	      		success: function(data, textStatus){
	      			var list="";
	      			if(data != null && data.length !=0){
		      			for(var i=0;i<data.devices.length;i++){
		      				var area="";
		      				var building="";
		      				var unit="";
		      				var room="";	
		      				var id=data.devices[i].deviceId;
		      				var title=data.devices[i].deviceId;
		      				var deviceType = data.devices[i].deviceType.deviceName;
		      				if(data.devices[i].housingDistrictRegionInfo!=null)
		      					area = data.devices[i].housingDistrictRegionInfo.name+"<s:text name='common.title.areaname'/>";
		      				if(data.devices[i].regionBuildingInfo!=null)
		      					building = data.devices[i].regionBuildingInfo.name+"<s:text name='common.title.building'/>";
		      				if(data.devices[i].buildingCellInfo!=null)
		      					unit = data.devices[i].buildingCellInfo.name+"<s:text name='common.title.unitname'/>";
		      				if(data.devices[i].cellHouseholdInfo!=null)
		      					room = data.devices[i].cellHouseholdInfo.name+"<s:text name='common.title.housename'/>";
		      				list = list + "<li><span>"+deviceType+"</span><a href='<%=path%>device/deviceDetail.action?deviceId="+id+"' target=main>"+area+building+unit+room+"</a></li>";
		      			}
		      		}
		      		if(list!=""){
		      			$("#deviceAlarm").html("<ul class=index_head_prompts>"+list+"</ul><div class=prompts_a><a href='<%=path%>device/deviceList.action?device.deviceStatus=<%=Constants.DEVICE_OFFLINE%>' target=main><s:text name='common.title.device'/></a></div>");
		      			ring();
					}
					
	      		},
	      		complete: function(XMLHttpRequest, textStatus){
	      			//HideLoading();
	      		},
	      		error: function(){
	      			//请求出错处理
	      		}
	       });
		} 
	 <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)){ %>
		$( document ).ready(function () {			
			setInterval(function(){Content()},60000);
			setInterval(function(){device()},60000);
		});
	 <%} %>
	 <% if(Constants.LOGIN_USER_TYPE_PAUSER.equals(userType)||Constants.LOGIN_USER_TYPE_OWNER.equals(userType)){ %>
		$( document ).ready(function () {			
			setInterval(function(){getNoReadReceiverCount()},20000);
		});
	 <%} %>
	 function ring(){
	 	$("#alarmRing").html("<embed id='alarmRing' src='<%=path%>voice/alarm.wav' width='0' height='0' autostart='true'></embed>");
	 }
	 **/
</script>




<body >
   
	<div id="alarmRing"></div>
	<div class="index_header">
    	<div class="index_header_article">
        	<div class="index_header_logo"></div>
            <div class="index_header_userstate">
            	<p><span>
                    <s:property value="#session.login.userName" />:</span><s:text name="head.page.welcome.info"/><i id="time"></i><a href="javascript:retHome('<s:url action="main" namespace="/login"/>');"><s:text name="head.page.back.first"/></a><a href="javascript:exit('<s:url action="logout" namespace="/login"/>');"><s:text name="head.page.exit"/></a></p>
                <s></s>
            </div>
        </div>
        <div class="index_header_nav">
            <div class="nav_top">
            	<ul class="scrol">
                 	<li class="selected">
                        <s:if test="#session.login.userType == '03'">
                            <s:text name="M001"/>
                        </s:if>
                        <s:elseif test="#session.login.userType == '04'">
                            	深圳市龙侨华实业有限公司 | 智能终端管理平台
                        </s:elseif>
                        <s:elseif test="#session.login.userType == '02'">
                        	<s:property value="#session.login.districtName" /> | 
                            <s:property value="#session.login.propertyCompanyName" />
                            <!-- <s:text name="深圳市xxxx小区物业管理有限公司"/>-->
                        </s:elseif>
                        <s:else>
                            <s:text name="M003"/>
                        </s:else>
                    </li>
                    <!--<li><s:text name="M004"/></li>
                    <li><s:text name="M005"/></li>
                    <li><s:text name="M006"/></li>
                    <li><s:text name="M007"/></li>
                    <li><s:text name="M008"/></li>
                    <li><s:text name="M009"/></li>
                    <li><s:text name="M010"/></li>
                    <li><s:text name="M011"/></li>
                    -->
                </ul>
            </div>
            <div class="nav_body">
                <div>
                    <s:iterator value="#session.permissions" var="menu">                       
                        <a href="<%=path%><s:property value='#menu.menuUrl'/>" target="main">
                            <dfn class="<s:property value='#menu.menuCode'/>"></dfn>
                            <i><s:property value='#menu.menuName'/></i>
                    	</a>                      
                    </s:iterator>
                </div>                            
                <div class="none">
                    <a href="#">
                    	<dfn class="M004001"></dfn>
                        <i><s:text name="M004001"/></i>
                    </a>
                </div>
                <div class="none">
                	<a href="#">
                    	<dfn class="M005001"></dfn>
                        <i><s:text name="M005001"/></i>
                    </a>
                </div>
                <div class="none">
                	<a href="#">
                    	<dfn class="M006001"></dfn>
                        <i><s:text name="M006001"/></i>
                    </a>
                </div>
                <div class="none">
                	<a href="#">
                    	<dfn class="M007001"></dfn>
                        <i><s:text name="M007001"/></i>
                    </a>
                </div>
                <div class="none">
                	<a href="#">
                    	<dfn class="M008001"></dfn>
                        <i><s:text name="M008001"/></i>
                    </a>
                </div>
                <div class="none">
                	<a href="#">
                    	<dfn class="M009001"></dfn>
                        <i><s:text name="M009001"/></i>
                    </a>
                </div>
                <div class="none">
                	<a href="#">
                    	<dfn class="M010001"></dfn>
                        <i><s:text name="M010001"/></i>
                    </a>
                </div>
                <div class="none">
                	<a href="#">
                    	<dfn class="M011001"></dfn>
                        <i><s:text name="M011001"/></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
		function showLocale(objD)
		{
		var str,colorhead,colorfoot;
		var yy = objD.getYear();
		if(yy<1900) yy = yy+1900;
		var MM = objD.getMonth()+1;
		if(MM<10) MM = '0' + MM;
		var dd = objD.getDate();
		if(dd<10) dd = '0' + dd;
		var hh = objD.getHours();
		if(hh<10) hh = '0' + hh;
		var mm = objD.getMinutes();
		if(mm<10) mm = '0' + mm;
		var ss = objD.getSeconds();
		if(ss<10) ss = '0' + ss;
		var ww = objD.getDay();
		if  ( ww==0 )  colorhead="";
		if  ( ww > 0 && ww < 6 )  colorhead="";
		if  ( ww==6 )  colorhead="";
		if  (ww==0)  ww='<s:text name="head.page.time.sunday"/>';
		if  (ww==1)  ww='<s:text name="head.page.time.monday"/>';
		if  (ww==2)  ww='<s:text name="head.page.time.tuesday"/>';
		if  (ww==3)  ww='<s:text name="head.page.time.wednesday"/>';
		if  (ww==4)  ww='<s:text name="head.page.time.thursday"/>';
		if  (ww==5)  ww='<s:text name="head.page.time.friday"/>';
		if  (ww==6)  ww='<s:text name="head.page.time.saturday"/>';
		colorfoot="</font>"
		str = colorhead + yy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss + "  " + ww + colorfoot;
		return(str);
		}
		var today= new Date(<%=new java.util.Date().getTime()%>);
		function tick()
		{		
		today.setSeconds(today.getSeconds()+1); 
		document.getElementById("time").innerHTML = showLocale(today);
		
		}
		tick();
		window.setInterval("tick()", 1000);
	</script>
</body>
</html>