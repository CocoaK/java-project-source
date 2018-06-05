<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
    <%
String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Main</title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
</head>
<script type="text/javascript">
	function noData(){//"暂无数据"国际化
		return "<s:text name='common.page.nodata'/>";
	}
	
	//进入首页时最先显示的2个tab页面
	function getMainTab(){
		hiddenAllTab();
        $.ajax({
				url : "<%=path%>json/login/getMainTab.action",
				// 数据发送方式            
				type : "post",
				// 接受数据格式            
				dataType : 'json',
				cache:false,
				// 要传递的数据       
				//data:{'tabIdTop':'tab8','tabIdBottom':'tab4'},
				//data : params,
				// 回调函数，接受服务器端返回给客户端的值，即result值          
				success : function(data, textStatus){
	         		//显示上部第一个tab
	         		if(data.tabTops.length != 0){
	         			getTab(data.tabTops[0]);								//显示第一个tab
	         			showTab("div_"+data.tabTops[0]);						//将隐藏的div显示出来
		         		$("#li_"+data.tabTops[0]).attr("class","selecteds"); 	//添加class,显示选中
		         	}
		         	//显示下部第一个tab
		         	if(data.tabBottoms.length != 0){
		         		getTab(data.tabBottoms[0]);								//显示第一个tab
		         		showTab("div_"+data.tabBottoms[0]);						//将隐藏的div显示出来
		         		$("#li_"+data.tabBottoms[0]).attr("class","selecteds");	//添加class,显示选中
		         	}
/*	         		for(var i=0;i<data.tabTops.length;i++){
	         			showTab("li_"+data.tabTops[i]);							//显示tab
	         			showTab(data.tabTops[i]);								//显示tab值页面
					}
					
					for(var i=0;i<data.tabBottoms.length;i++){
	         			showTab("li_"+data.tabBottoms[i]); 						//显示tab
	         			showTab(data.tabBottoms[i]); 							//显示tab值页面
	         		}
*/	         		
					for(var i=0;i<1;i++){
	         			showTab("li_"+data.tabTops[i]);							//显示tab
	         			showTab(data.tabTops[i]);								//显示tab值页面
					}
					
					for(var i=0;i<1;i++){
	         			showTab("li_"+data.tabBottoms[i]); 						//显示tab
	         			showTab(data.tabBottoms[i]); 							//显示tab值页面
	         		}
         		},
         		error: function(){
         		}
			});
      	leftPage();
         
      }
		//window.setInterval("refresh()", 10000);
		
	function getTab(param){
        $.ajax({
				url : "<%=path%>json/login/getItemTabValue.action?tabId="+param,
				// 数据发送方式            
				type : "post",
				// 接受数据格式            
				dataType : 'json',
				cache:false,
				// 要传递的数据       
				//data:{'loginName':'loginName'},
				//data : params,
				// 回调函数，接受服务器端返回给客户端的值，即result值          
				success : function(data, textStatus){
         			var temp="";
					if(param=="tab0"){
						for(var i=0;i<data.length;i++){
							var id= data[i].id; 
							var title = data[i].title;
							var time = "";
							if(data[i].sendTime != null){
								time = data[i].createTime;
							}
	         				var list = "<li><a href='<%=path%>info/findInfoSend.action?requestId="+id+"&ShowDetail'>"+transTitle(title)+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;
	         			}
	         		}
	         		if(param=="tab1"){
	         			for(var i=0;i<data.length;i++){
	         				var title = data[i].title;
	         				var time = data[i].complaintDate;
	         				var id = data[i].id;
	         				var list = "<li><a href='<%=path%>customercomplaint/viewOwnerComplaintDetail.action?complaintId="+id+"'>"+transTitle(title)+"</a><span>"+transTime(time)+"</span></li>";	
	         				temp = temp+list;
	         			}
	         		}
	         		if(param=="tab2"){
	         			for(var i=0;i<data.length;i++){
	         				var id = data[i].alarmId;
	         				var title = data[i].alarmType.alarmName;
	         				var time = data[i].createdTime;
	         				var list = "<li><a href='<%=path%>alarm/getAlarmMapMsg.action?requestId="+id+"'>"+title+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;
	         			}		
	         		}
	         		if(param=="tab3"){
	         			for(var i=0;i<data.length;i++){
	         				var id = data[i].id;
	         				var title = data[i].caller+"&nbsp;&nbsp;"+data[i].device.deviceName;
	         				var time = data[i].callTime;
	         				var list = "<li><a href='<%=path%>device/messageList.action?callRecordVO.id="+id+"'>"+title+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;	
	         			}
	         		}
	         		if(param=="tab4"){
	         		for(var i=0;i<data.length;i++){
	         				var id = data[i].gateCardId;
	         				var title = data[i].cardNo+"&nbsp;&nbsp;"+data[i].ownerName;
	         				var time = data[i].createdTime;
	         				var list = "<li><a href='<%=path%>gateCard/viewDetail.action?gateCardId="+id+"'>"+title+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;	
	         			}
	         		}
	         		if(param=="tab5"){
	         			for(var i=0;i<data.length;i++){
	         				var id = data[i].userId;
	         				var title = data[i].userName;
	         				var address = data[i].areaName+"<s:text name='common.title.areaname'/>"+data[i].building+"<s:text name='common.title.building'/>"+data[i].unitName+"<s:text name='common.title.unitname'/>"+data[i].houseName+"<s:text name='common.title.housename'/>";
	         				var time = data[i].createdTime;
	         				var list = "<li><a href='<%=path%>ownerUser/viewDetail?currUserId="+id+"'>"+transTitle(title)+"&nbsp;&nbsp;&nbsp;&nbsp;"+address+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;
	         			}
	         		}
	         		if(param=="tab6"){
	         			for(var i=0;i<data.length;i++){	
	         				var id = data[i].deviceId;
	         				var title = data[i].deviceName;
	         				var time = data[i].createdTime;
	         				var deviceCode = data[i].deviceCode;
	         				var list = "<li><a href='<%=path%>device/deviceDetail.action?deviceId="+id+"'>"+deviceCode+"&nbsp;&nbsp;&nbsp;&nbsp;"+title+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;
	         			}
	         		}
	         		if(param=="tab7"){
	         			for(var i=0;i<data.length;i++){
	         				var id = data[i].id;
	         				var title = data[i].title;
	         				var time = data[i].requestTime;
	         				var list = "<li><a href='<%=path%>requestRepair/findRequestRepair.action?requestId="+id+"&ShowDetail'>"+transTitle(title)+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;	
	         			}
	         		}
	         		if(param=="tab8"){
	         			for(var i=0;i<data.length;i++){
	         				var id = data[i].visitId;
	         				var title = data[i].visitorName+"&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].idCard;
	         				var time = data[i].visitTime;
	         				var list = "<li><a href='<%=path%>idCardVisit/viewDetail.action?visitId="+id+"'>"+transTitle(title)+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;	
	         			}
	         		}
	         		if(temp==""){
	         			temp=noData();
	         		}
	        		$("#"+param).html(temp);
         		},
         		error: function(){
         		}
		});
	}
		
	function leftPage(){
        $.ajax({
				url : "<%=path%>json/login/getLeftPageValue.action",
				// 数据发送方式            
				type : "post",
				// 接受数据格式            
				dataType : 'json',
				cache:false,
				// 要传递的数据       
				//data:{'loginName':'loginName'},
				//data : params,
				// 回调函数，接受服务器端返回给客户端的值，即result值          
				success : function(data, textStatus){
         			var managerName = data.saUserVO.userName;
					var managerTel = data.saUserVO.officePhone;
					var dayWeather = data.weatherReportData.dayWeatherDesc;
					var dayTemp = data.weatherReportData.dayTemp;
					var nightTemp = data.weatherReportData.nightTemp;
					if(managerName == null){
						managerName = "";
					}
					if(managerTel == null){
						managerTel = "";
					}
					if(dayWeather == null || dayWeather=="null"){
						dayWeather = "";
					}
					if(dayTemp == null || dayTemp=="null"){
						dayTemp = "";
					}
					if(nightTemp == null || nightTemp=="null"){
						nightTemp = "";
					}
         			$("#ownerCount").text(data.liverCount);
         			$("#deviceCount").text(data.deviceCount);
         			$("#complaintCount").text(data.todayCcomplaintCount);
         			$("#managerName").text(managerName);
         			$("#managerTel").text(managerTel);
         			//$("#weather").text(dayWeather+" "+dayTemp+" "+nightTemp);
         		},
         		error: function(){
         		}
			});
      		
         
      }
		window.setInterval("leftPage()", 60000);

	// 主页面隐藏全部tab 
	function hiddenAllTab(){
		for(var i=0;i<9;i++){
		var obj = document.getElementById("li_tab"+i);
		var obj2 = document.getElementById("div_tab"+i);
		if(obj != null)
	    	obj.style.display="none";
	    if(obj2 != null)
	    	obj2.style.display="none";
		}
	}
	
	//主页面显示tab 
	function showTab(tabName){
		document.getElementById(tabName).style.display="block";
	}
	</script>
<body onload="getMainTab()">
	<div class="main_section">
    	<div class="main_aside">
        	<p class="main_box_top"><strong><s:text name="area.statistics"/></strong></p>
            <ul class="main_box_body">
            	<li><strong class="main_ico_00"></strong><span><s:text name="owner.count"/>:<i id="ownerCount"></i></span></li>
                <li><strong class="main_ico_01"></strong><span><s:text name="area.device.count"/>:<i id="deviceCount"></i></span></li>
              <!--  <li><strong class="main_ico_02"></strong><span><s:text name="area.charge.finish"/>:<i id="chargeFinish"></i></span></li>
                <li><strong class="main_ico_03"></strong><span><s:text name="area.charge.total"/>:<i id="chargeTotal"></i></span></li>
               --> 
                <li><strong class="main_ico_05"></strong><span><s:text name="today.complaint.count"/>:<i id="complaintCount"></i></span></li>
                <!-- <li><strong class="main_ico_06"></strong><span><s:text name="sys.goods.count"/>:<i id="goodsCount"></i></span></li> -->
              <!--  <li><strong><s:text name="weather.report"/></strong><span id="weather"></span></li> -->
            </ul>
            <p class="main_box_top mbt1"><strong><s:text name="sys.manager"/></strong></p>
            <div class="main_box_show">
            	<!-- <p><a href="#"><img id="photo" src="#" width="100%" height="100%"/></a></p> -->
            	<p><a href="#"><img src="<s:url value='/images/user.png'/>" /></a></p>
                <ul>
                	<li><s:text name="user.name"/>:<span id="managerName"></span></li>
                    <li><s:text name="user.tel"/>:<span id="managerTel"></span></li>
                </ul>
                <div id="duty"></div>
            </div>
        </div>
        <div class="main_outside">
        	<div class="main_inside">
            	<div class="main_tab_top">
                    <ul>
                        <li onclick="getTab('tab0')" id="li_tab0"><s:text name="info.verify"/></li>
                       	<li onclick="getTab('tab1')" id="li_tab1"><s:text name="recent.pending.complaint"/></li>
                        <li onclick="getTab('tab2')" id="li_tab2"><s:text name="recent.alarm"/></li>
                        <li onclick="getTab('tab3')" id="li_tab3"><s:text name="recent.visitor"/></li>
                        <li onclick="getTab('tab8')" id="li_tab8"><s:text name="idcard.record"/></li>
                    </ul>
           		</div>
                <div class="main_tab_body">
                    <div class="none" id="div_tab0" >
                        <ul id="tab0"></ul>
                        <p><a href="<%=path%>info/queryInfoSendList.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                    <div class="none" id="div_tab1" >
                        <ul id="tab1"></ul>
                        <p><a href="<%=path%>customercomplaint/queryOwnerComplaintList.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                    <div class="none" id="div_tab2" >
                       	<ul id="tab2"></ul>
                        <p><a href="<%=path%>alarm/queryAlarmList.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                    <div class="none" id="div_tab3" >
                        <ul id="tab3"></ul>
                        <p><a href="<%=path%>device/messageList.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                    <div class="none" id="div_tab8" >
                        <ul class="main_tb_hack" id="tab8"></ul>
                        <p><a href="<%=path%>idCardVisit/queryList.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                </div>
                <div class="main_tab_top">
                    <ul>
                        <li class="selecteds" onclick="getTab('tab4')" id="li_tab4"><s:text name="today.card.send"/></li>
                        <li onclick="getTab('tab5')" id="li_tab5"><s:text name="today.check.in"/></li>
                        <li onclick="getTab('tab6')" id="li_tab6"><s:text name="device.online"/></li>
                        <li onclick="getTab('tab7')" id="li_tab7"><s:text name="today.repair"/></li>
                    </ul>
            	</div>
                <div class="main_tab_body">
                    <div id="div_tab4" >
                        <ul id="tab4"></ul>
                        <p><a href="<%=path%>gateCard/queryList.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                    <div class="none" id="div_tab5" >
                       	<ul id="tab5"></ul>
                        <p><a href="<%=path%>ownerUser/queryList.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                    <div class="none" id="div_tab6" >
                        <ul id="tab6"></ul>
                        <p><a href="<%=path%>device/deviceList.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                    <div class="none" id="div_tab7" >
                        <ul id="tab7"></ul>
                        <p><a href="<%=path%>requestRepair/queryRequestRepairList.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                </div>
            </div>
        </div>
        <s:include value="include_main_common.jsp"></s:include>
    </div>
</body>
</html>