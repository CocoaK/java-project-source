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
	function noData(){
		return "<s:text name='common.page.nodata'/>";
	}
	
	//主页面第一次显示的tab
	function getMainTab(){
		hiddenAllTab();	//隐藏所有主页tab
        $.ajax({
				url : "<%=path%>json/login/getMainTab.action",
				// 数据发送方式            
				type : "post",
				// 接受数据格式            
				dataType : 'json',
				cache:false,
				// 要传递的数据       
				data:{'tabIdTop':'tab0','tabIdBottom':'tab4'},
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
	         		for(var i=0;i<data.tabTops.length;i++){
	         			showTab("li_"+data.tabTops[i]);							//显示tab
	         			showTab(data.tabTops[i]);								//显示tab值页面
					}
					
					for(var i=0;i<data.tabBottoms.length;i++){
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
	//获取单个tab
	function getTab(param){
        $.ajax({
				url : "<%=path%>json/login/getItemTabValue.action?tabId="+param,
				// 数据发送方式            
				type : "post",
				// 接受数据格式            
				dataType : 'json',
				cache:false,
				// 回调函数，接受服务器端返回给客户端的值，即result值          
				success : function(data, textStatus){
         			var temp="";
					if(param=="tab0"){
						for(var i=0;i<data.length;i++){
							var id= data[i].id;
							var title = data[i].infoSend.title;
	         				var time = data[i].infoSend.sendTime;
	         				var list = "<li><a href='<%=path%>info/findInfoReceiver.action?requestId="+id+"&ShowDetail'>"+transTitle(title)+"</a><span>"+transTime(time)+"</span></li>";
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
	         				var id = data[i].id;
	         				var title = data[i].caller;
	         				var time = data[i].callTime;
	         				var list = "<li><a href='<%=path%>device/messageList.action?callRecordVO.id="+id+"'>"+title+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;
	         			}		
	         		}
	         		if(param=="tab3"){
	         			for(var i=0;i<data.length;i++){
	         				var id = data[i].id;
	         				var title = data[i].title;
	         				var time = data[i].requestTime;
	         				var list = "<li><a href='<%=path%>requestRepair/findRequestRepair.action?requestId="+id+"&ShowDetail'>"+transTitle(title)+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;	
	         			}
	         		}
	         		if(param=="tab4"){
	         		for(var i=0;i<data.length;i++){       				
	         				var id = data[i].id;
	         				var time = "";
	         				var status = "";
	         				var title = "<s:text name='chargedetail.printsn'/>:&nbsp;&nbsp;"+data[i].printSn;
	         				if(data[i]!=null && data[i].chargeStatus=="1"){
	         					time = data[i].chargeTime;
	         					status = "<s:text name='common.charge.yesfee'/>";
	         				}else if(data[i]!=null && data[i].chargeStatus=="2" && data[i].chargeData!=null){
	         					time = data[i].chargeData.createTime;
	         					status = "<s:text name='common.charge.nofee'/>";
	         				}
	         				var list = "<li><a href='<%=path%>charge/findChargeDetail.action?requestId="+id+"&ShowDetail'>"+title+"&nbsp;&nbsp;&nbsp;&nbsp;"+status+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;	
	         			}
	         		}
	         		if(param=="tab5"){
	         			for(var i=0;i<data.length;i++){
	         				var title = data[i].title;
	         				var time = data[i].sendTime;
	         				var list = "<li><a href='#'>"+transTitle(title)+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;
	         			}
	         		}
	         		if(param=="tab6"){
	         			for(var i=0;i<data.length;i++){	
	         				var id= data[i].id; 
							var title = data[i].title;
	         				var time = data[i].createTime;
	         				var status = "";
	         				if(data[i]!=null && data[i].status=="1"){
	         					status = "<s:text name='infosend.noaudit'/>";
	         				}
	         				if(data[i]!=null && data[i].status=="2"){
	         					status = "<s:text name='common.element.action.approveyes'/>";
	         				}
	         				if(data[i]!=null && data[i].status=="3"){
	         					status = "<s:text name='common.element.action.approveyes'/>";
	         				}
	         				if(data[i]!=null && data[i].status=="4"){
	         					status = "<s:text name='infosend.nosend'/>";
	         				}
	         				if(data[i]!=null && data[i].status=="5"){
	         					status = "<s:text name='infosend.yessend'/>";
	         				}
	         				var list = "<li><a href='<%=path%>info/findInfoSend.action?requestId="+id+"&ShowDetail'>"+transTitle(title)+"&nbsp;&nbsp;&nbsp;&nbsp;"+status+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;
	         			}
	         		}
	         		if(param=="tab7"){
	         			for(var i=0;i<data.length;i++){
	         				var id = data[i].logId;
	         				var title = data[i].operateUser + "&nbsp;&nbsp;"+data[i].ip;
	         				var time = data[i].operateTime;
	         				var list = "<li><a href='<%=path%>log/operationLogDetail.action?logId="+id+"'>"+title+"</a><span>"+transTime(time)+"</span></li>";
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
	
	//页面左边统计数据
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
         			var dayWeather = data.weatherReportData.dayWeatherDesc;
					var dayTemp = data.weatherReportData.dayTemp;
					var nightTemp = data.weatherReportData.nightTemp;
					if(dayWeather == null || dayWeather=="null"){
						dayWeather = "";
					}
					if(dayTemp == null || dayTemp=="null"){
						dayTemp = "";
					}
					if(nightTemp == null || nightTemp=="null"){
						nightTemp = "";
					}
         			$("#infoCount").text(data.infoCount);
         			$("#complaintCount").text(data.complaintCount);
         			$("#repairCount").text(data.repairCount);
         			$("#visitCount").text(data.visitorCount);
         			$("#alarmCount").text(data.alarmCount);
         			//$("#goodsCount").text(data.goodsCount);
         			$("#managerName").text(data.paUser.userName);
         			$("#managerTel").text(data.paUser.officePhone);
         			$("#photo").attr("src",data.paUser.photoPath);
         			$("#weather").text(dayWeather+" "+dayTemp+" "+nightTemp);
         		},
         		error: function(){
         		}
			});
      		
         
      }
		window.setInterval("leftPage()", 60000);

	// 主页面隐藏全部tab 
	function hiddenAllTab(){
		for(var i=0;i<8;i++){
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
        	<p class="main_box_top"><strong><s:text name="owner.statistics"/></strong></p>
            <ul class="main_box_body">
            	<li><strong class="main_ico_00"></strong><span><s:text name="owner.info.count"/>:<i id="infoCount"></i></span></li>
                <li><strong class="main_ico_01"></strong><span><s:text name="owner.complaint.count"/>:<i id="complaintCount"></i></span></li>
                <li><strong class="main_ico_02"></strong><span><s:text name="owner.repair.count"/>:<i id="repairCount"></i></span></li>
                <li><strong class="main_ico_03"></strong><span><s:text name="owner.visit.count"/>:<i id="visitCount"></i></span></li>
                <li><strong class="main_ico_05"></strong><span><s:text name="owner.alarm.count"/>:<i id="alarmCount"></i></span></li>
                <li><strong><s:text name="weather.report"/></strong><span id="weather"></span></li>
            </ul>
            <p class="main_box_top mbt1"><strong><s:text name="pa.manager"/></strong></p>
            <div class="main_box_show">
            	<p><a href="#"><img id="photo" src="" width="100%" height="100%"/></a></p>
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
                        <li class="selecteds" onclick="getTab('tab0')" id="li_tab0"><s:text name="info.unread"/></li>
                       	<li onclick="getTab('tab1')" id="li_tab1"><s:text name="recent.complaint"/></li>
                        <li onclick="getTab('tab2')" id="li_tab2"><s:text name="recent.visitor"/></li>
                        <li onclick="getTab('tab3')" id="li_tab3"><s:text name="recent.repair"/></li>
                    </ul>
           		</div>
                <div class="main_tab_body">
                    <div id="div_tab0">
                        <ul class="main_tb_hack" id="tab0"></ul>
                        <p><a href="<%=path%>info/queryInfoReceiverList.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                    <div class="none" id="div_tab1">
                        <ul id="tab1"></ul>
                        <p><a href="<%=path%>customercomplaint/queryOwnersComplaintList.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                    <div class="none" id="div_tab2">
                       	<ul id="tab2"></ul>
                        <p><a href="<%=path%>device/messageList.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                    <div class="none" id="div_tab3">
                        <ul id="tab3"></ul>
                        <p><a href="<%=path%>requestRepair/queryRequestRepairList.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                </div>
                <div class="main_tab_top">
                    <ul>
                        <li class="selecteds" onclick="getTab('tab4')" id="li_tab4"><s:text name="recent.charge"/></li>
                    <!--     <li onclick="getTab('tab5')"><s:text name="recent.file.transfer"/></li> -->
                        <li onclick="getTab('tab6')" id="li_tab6"><s:text name="recent.info"/></li>
                    <!--    <li onclick="getTab('tab7')"><s:text name="recent.log"/></li> -->
                    </ul>
            	</div>
                <div class="main_tab_body">
                    <div id="div_tab4">
                        <ul id="tab4"></ul>
                        <p><a href="<%=path%>charge/queryChargeDetailList.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                  <!--   <div class="none" id="div_tab5">
                       	<ul id="tab5"></ul>
                        <p><a href="#"><s:text name="main.page.more"/>&raquo;</a></p> 
                    </div>-->
                    <div class="none" id="div_tab6">
                        <ul id="tab6"></ul>
                        <p><a href="<%=path%>info/queryInfoSendList.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                  <!--   <div class="none" id="div_tab7">
                        <ul id="tab7"></ul>
                        <p><a href="<%=path%>log/operationLogList.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div> -->
                </div>
            </div>
        </div>
        <s:include value="include_main_common.jsp"></s:include>
</body>
</html>