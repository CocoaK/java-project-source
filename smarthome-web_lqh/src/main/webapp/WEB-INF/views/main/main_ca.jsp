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
				data:{'tabIdTop':'tab0','tabIdBottom':'tab3'},
				//data : params,
				// 回调函数，接受服务器端返回给客户端的值，即result值          
				success : function(data, textStatus){
					//显示上部第一个tab
	         		//if(data.tabTops.length > 0){
	         			getTab("tab0");								//显示第一个tab
	         			showTab("li_tab0");
	         			showTab("div_tab0");						//将隐藏的div显示出来
		         		$("#li_tab0").attr("class","selecteds"); 	//添加class,显示选中
		         	//}
		         	//显示下部第一个tab
		         	//if(data.tabBottoms.length > 0){
		         		getTab("tab3");								//显示第一个tab
		         		showTab("li_tab3");
		         		showTab("div_tab3");						//将隐藏的div显示出来
		         		$("#li_tab3").attr("class","selecteds");	//添加class,显示选中
		         	//}
	         		//for(var i=0;i<data.tabTops.length-1;i++){
	         		//	showTab("li_"+data.tabTops[i]);							//显示tab
	         		//	showTab(data.tabTops[i]);								//显示tab值页面
					//}
					//for(var i=0;i<data.tabBottoms.length;i++){
	         		//	showTab("li_"+data.tabBottoms[i]); 						//显示tab
	         		//	showTab(data.tabBottoms[i]); 							//显示tab值页面
	         		//}
         		},
         		error: function(){
         		}
			});
         leftPage();
      }

		
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
					if(param=="tab0" && data!=null){
						for(var i=0;i<data.length;i++){
							var id= data[i].id;
							var title = data[i].title;
							var time = data[i].createTime;
	         				var list = "<li><a href='<%=path%>info/findInfoSend.action?requestId="+id+"&ShowDetail'>"+transTitle(title)+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;
	         			}
	         		}
	         		if(param=="tab1" && data!=null){
	         			for(var i=0;i<data.length;i++){
	         				var title = data[i].title;
	         				var time = data[i].complaintDate;
	         				var id = data[i].id;
	         				var list = "<li><a href='<%=path%>customercomplaint/viewComplaintDetail.action?complaintId="+id+"'>"+transTitle(title)+"</a><span>"+transTime(time)+"</span></li>";	
	         				temp = temp+list;
	         			}
	         		}
	         		if(param=="tab2" && data!=null){
	         			for(var i=0;i<data.length;i++){
	         				var id = data[i].softwareId;
	         				var title = data[i].softwareName+"&nbsp;&nbsp;["+data[i].versionName+"]";
	         				var time = data[i].applyedTime;
	         				var list = "<li><a href='<%=path%>softwareUpgrade/viewDetail.action?softwareId="+id+"'>"+transTitle(title)+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;
	         			}		
	         		}
	         		if(param=="tab3" && data!=null){
	         			for(var i=0;i<data.length;i++){
	         				var id =data[i].adId;
	         				var title = data[i].adName;
	         				var time = data[i].applyedTime;
	         				var list = "<li><a href='<%=path%>ad/viewDetail.action?adId="+id+"'>"+transTitle(title)+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;	
	         			}
	         		}
	         		if(param=="tab4" && data!=null){
	         		for(var i=0;i<data.length;i++){
	         				var id = data[i].id;
	         				var title = data[i].pushName+"&nbsp;&nbsp;"+data[i].pushClientId;
	         				var time = data[i].addTime;
	         				var list = "<li><a href='<%=path%>push/pushAction_viewDetail.action?pushVO.id="+id+"'>"+title+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;	
	         			}
	         		}
	         		if(param=="tab5" && data!=null){
	         			for(var i=0;i<data.length;i++){
	         				var fileName = data[i].fileName;
	         				var title = fileName + "&nbsp;&nbsp;["+data[i].fileFormat+"]";
	         				var time = data[i].addTime;
	         				var list = "<li><a href='<%=path%>hdfs/hdfsFileAction_list.action?localHdfsVO.uploaderKind=all&localHdfsVO.kind=all&localHdfsVO.fileName="+fileName+"'>"+title+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;
	         			}
	         		}
	         		if(param=="tab6" && data!=null){
	         			for(var i=0;i<data.length;i++){	
	         				var title = data[i].title;
	         				var time = data[i].sendTime;
	         				var list = "<li><a href='#'>"+transTitle(title)+"</a><span>"+transTime(time)+"</span></li>";
	         				temp = temp+list;
	         			}
	         		}
	         		if(param=="tab7" && data!=null){
	         			for(var i=0;i<data.length;i++){
	         				var title = data[i].title;
	         				var time = data[i].sendTime;
	         				var list = "<li><a href='#'>"+transTitle(title)+"</a><span>"+transTime(time)+"</span></li>";
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
					if(managerName == null){
						managerName = "";
					}
					if(managerTel == null){
						managerTel = "";
					}
         			$("#liverCount").text(data.liverCount);
         			$("#districtCount").text(data.districtCount);
         			$("#onlineUserCount").text(data.onlineUserCount);
         			$("#onlineDeviceCount").text(data.onlineDeviceCount);
         			$("#todayInfoCount").text(data.todayInfoCount);
         			$("#complaintCount").text(data.complaintCount);
         			$("#managerName").text(managerName);
         			$("#managerTel").text(managerTel);
         			//$("#weather").text(data.weatherReportData.dayWeatherDesc+" "+data.weatherReportData.dayTemp+" "+data.weatherReportData.nightTemp);
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
        	<p class="main_box_top"><strong><s:text name="sys.statistics"/></strong></p>
            <ul class="main_box_body">
                <!-- <li><strong class="main_ico_02"></strong><span><s:text name="user.online.count"/>:<i id="onlineUserCount"></i></span></li> -->
                <li><strong class="main_ico_02"></strong><span>注册用户数:<i id="todayInfoCount">2318</i></span></li>
                <li><strong class="main_ico_03"></strong><span><s:text name="device.online.count"/>:<i id="onlineDeviceCount">1207</i></span></li>
                <li><strong class="main_ico_04"></strong><span><s:text name="sys.today.infocount"/>:<i id="todayInfoCount"></i>1</span></li>
            </ul>
            <p class="main_box_top mbt1"><strong><s:text name="sys.manager"/></strong></p>
            <div class="main_box_show">
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
                        <li class="selecteds" onclick="getTab('tab0')" id="li_tab0"><s:text name="recent.info"/></li>

                    </ul>
           		</div>
                <div class="main_tab_body">
                    <div id="div_tab0">
                        <ul class="main_tb_hack" id="tab0"></ul>
                        <p><a href="<%=path %>info/queryInfoSendList.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>

                </div>
                <div class="main_tab_top">
                    <ul>
                    	<li onclick="getTab('tab3')" id="li_tab3"><s:text name="recent.ad"/></li>
                        <li onclick="getTab('tab5')" id="li_tab5"><s:text name="recent.file.transfer"/></li>
                        <li onclick="getTab('tab6')" id="li_tab6"><s:text name="recent.product"/></li>
                        <li onclick="getTab('tab7')" id="li_tab7"><s:text name="recent.game"/></li>
                    </ul>
            	</div>
                <div class="main_tab_body">
                    <div id="div_tab3">
                        <ul id="tab3"></ul>
                        <p><a href="<%=path %>push/pushAction_list.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                    <div class="none" id="div_tab5">
                       	<ul id="tab5"></ul>
                        <p><a href="<%=path %>hdfs/hdfsFileAction_list.action"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                    <div class="none" id="div_tab6">
                        <ul id="tab6"></ul>
                        <p><a href="#"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                    <div class="none" id="div_tab7">
                        <ul id="tab7"></ul>
                        <p><a href="#"><s:text name="main.page.more"/>&raquo;</a></p>
                    </div>
                </div>
            </div>
        </div>
        <s:include value="include_main_common.jsp"></s:include>
    </div>
</body>
</html>