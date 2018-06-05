<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="housemgr.house.list"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_clorbox.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.parseQuerystring.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/districtList.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/regionList.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/buildingList.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/housemgr.js'/>"></script>
        <style type="text/css">
            #cboxNext,#cboxPrevious,#cboxCurrent,#cboxSlideshow{ display:none !important;}
        </style>
        <link rel="stylesheet" type="text/css" href="<s:url value='/css/boundSip.css'/>">
        <script type="text/javascript">
        	//同步门口机数据
        	function sync(deviceNo){
        		$.ajax({
        			url:service_url+'smarthomesocket/rest/sock/send',
        			type:'post',
        			dataType:'json',
        			cache:false,
        			data:{"deviceNo": deviceNo,
        				"data":'refresh',
        				"jsonType":'refresh'
        			},
        			success:function(json){
        				if(json.code==1){
        					console.log("同步成功！");
        					/* alert("门口机已同步"); 
        					$(".div_green").fadeIn(1000);
							setInterval(function(){
								$(".div_green").fadeOut(2000);
							},3000);*/
        				}else{
        					console.log("同步未成功！");
        					/* alert("门口机未同步"); 
        					$(".div_red").fadeIn(1000);
							setInterval(function(){
								$(".div_red").fadeOut(2000);
							},3000);*/
        				}
        			},
        			error:function(){
        				console.log("网络异常,同步门口机失败");
        			}
        		});
        	}
        	
        	//绑定手机账号
        	function boundSip(){
        		var options=$("#select option:selected");
        		$.ajax({
       				url:service_url+'smarthomeservice/rest/user/roomNo/bound',
       				type : "post",          
					dataType : 'json',
					cache : false,
					data : {'roomNo' : $("#roomNo").html(),
						'houseId':$("#houseId").val(),
						'sipid': $("#sipid").val(),
						'deviceNo': options.val(),
						'status': $("#status").text(),
						'districtId' : $("#district").html(),
						'unitId' : $("#unitId").html()
					},
					success:function(json){
						if(json.code==1){
							alert("绑定账号成功");
							//sync();
			        		$("#select option").each(function () {
			        	 		var txt = $(this).text(); //获取单个text
			        		    var val = $(this).val(); //获取单个value
			        		    sync(val);
			        		});
						}else if(json.code==7){
							alert("绑定账号已存在");
						}else{
							alert("绑定账号失败");
						}
					},
					error:function(){
						alert("网络异常,绑定账号失败");
					}
       			});
        	}
        	
        	
        	//解绑
        	function unbundling(obj) {
				var msg = "您确定要解绑吗？";
				if (confirm(msg)==true){
					$.ajax({
						url:service_url+'smarthomeservice/rest/user/roomNo/delete',
						type:'post',
						dataType:'json',
						cache:false,
						data:{
							"id":$(obj).parent().parent().find('td:eq(0)').text()
						},
						success:function(json){
							if(json.code==1){
								$(".unboundSuccess").fadeIn(1000);
								setInterval(function(){
									$(".unboundSuccess").fadeOut(2000);
								},3000);
								$(obj).parent().parent().html("");
								$("#select option").each(function () {
				        	 		var txt = $(this).text(); //获取单个text
				        		    var val = $(this).val(); //获取单个value
				        		    sync(val);
				        		});
							}else{
								$(".unboundFailed").fadeIn(1000);
								setInterval(function(){
									$(".unboundFailed").fadeOut(2000);
								},3000);
							}
						},
						error:function(){
							alert("网络异常,无法解绑");
						}
	        		});
				}else{
					return false; 
				} 
			}
			function boundSipView(districtId,unitId,houseId,roomNo){
//				console.log("districtId:"+districtId+", unitId:"+unitId+", roomNo:"+roomNo);
    			var housingDistrictRegionInfo = $("#housingDistrictRegionInfo").html();
    			var regionBuildingInfo = $("#regionBuildingInfo").html();
    			var buildingCellInfo = $("#buildingCellInfo").html();
    			$("#roomNo").html(roomNo);
    			$("#houseId").val(houseId);
    			$("#district").html(districtId);
    			$("#unitId").html(unitId);
    			$("#checkNull").css("display","none");
    			$.ajax({
    				url:service_url+'smarthomeweb/json/queryGateCardDevices',
    				type : "post",
					dataType : 'json',
					//async:false,
					cache : false,
					data : {'device.housingDistrictRegionInfo.name':housingDistrictRegionInfo,
						'device.regionBuildingInfo.name':regionBuildingInfo,
						'device.buildingCellInfo.name':buildingCellInfo
					},
					success:function(json){
						if(json!=null){
							/* alert("success");
							alert(json.devices.length); */
							if(json.devices.length==0){
								$("#checkDevice").css("display","inline");
									$("#sure").attr("disabled","disabled");
							}else{
								$("#checkDevice").css("display","none");
								$("#sure").attr("disabled",false);
							}
							var str="";
							for(var i=0;i<json.devices.length;i++){
								var option="<option value="+json.devices[i].deviceCode+">"+json.devices[i].deviceName+"</option>";
								str+=option;
							}
							$("#select").html(str);
						}else{
								alert("未获取到门口机信息");
							}
					},
					error:function(){
						alert("获取门口机信息失败");
					}
    			});
    			if($("#sipid").val()!=null){
    				$("#sipid").val("");
    				$("#checkSip").css("display","none");
    			}
    			/* $("#layout").css("display","block");
    			$("#fade").css("display","block"); */
    			$("#layout").slideDown("slow");
    			$("#fade").slideDown("slow");
			}
			
			function submit(){
				console.log("submit");
				$("#checkNull").css("display","none");
				if($("#sipid").val()==null || $("#sipid").val()==""){
					$("#checkSip").css("display","none");
					$("#checkNull").css("display","inline");
				}else{
					var options=$("#select option:selected");
					$.ajax({
        				url:service_url+'smarthomeservice/rest/user/roomNo/queryByHouseIdAndSipId',
        				type : "post",          
						dataType : 'json',
						cache : false,
						data : {'sipid' : $("#sipid").val(),
							'houseId' : $("#houseId").val()
						},
						success:function(json){
							if(json.length>0){
								$("#checkSip").css("display","inline");
								/* $("#sure").attr("disabled","disabled"); */ 
							}else{
								$("#checkSip").css("display","none");
								if($("#sipid").val()==null || $("#sipid").val()==""){
			        				$("#checkNull").css("display","inline");
			        			}else{
			        				var options=$("#select option:selected");
				        			$.ajax({
					        			url:service_url+'smarthomeservice/rest/user/roomNo/queryByHouseId',
					        			type:'post',
					        			dataType:'json',
					        			cache:false,
					        			data:{"houseId":$("#houseId").val()
					        				//"sipid":options.val()
					        			},
					        			success:function(json){
					        				if(json!=null){
													if(json.length>=3){
														alert("已超过绑定数量");
													}else{
				        								$("#layout").slideUp("slow");
			        									$("#fade").slideUp("slow");
														boundSip();
													}
												}else{
													alert("查询绑定账号失败");						
												}
					        			},
					        			error:function(){
					        				alert("网络异常,查询绑定账号失败");
					        			}
					        		});
			        			}
							}
						},
						error:function(){
							alert("检查SIP账号异常");
						}
        			});
				}
			}
        	$(function(){   				
        		//获取门口机ID
        		$(".boundSip").click(function(){
        			var housingDistrictRegionInfo=$(this).parent().parent().find('td:eq(3)').text();
        	    	var regionBuildingInfo=$(this).parent().parent().find('td:eq(2)').text();
        	    	var buildingCellInfo=$(this).parent().parent().find('td:eq(1)').text();
        	    	$("#housingDistrictRegionInfo").html(housingDistrictRegionInfo);
        	    	$("#regionBuildingInfo").html(regionBuildingInfo);
        	    	$("#buildingCellInfo").html(buildingCellInfo);
        		});
        		
        		$("#sipid").keyup(function(){
        			$("#checkSip").css("display","none");
        		});
        		/*
        		//提交
        		$("#sure").click(function(){
        			
        			
        		});
        		*/
        		//检查SIP账号是否存在
				/* $("#sipid").keyup(function(){
					$("#checkNull").css("display","none");
					if($("#sipid").val()==null || $("#sipid").val()==""){
						$("#checkSip").css("display","none");
						$("#checkNull").css("display","inline");
					}else{
						var options=$("#select option:selected");
						$.ajax({
	        				url:service_url+'smarthomeservice/rest/user/roomNo/queryByHouseIdAndSipId',
	        				type : "post",          
							dataType : 'json',
							cache : false,
							data : {'sipid' : $(this).val(),
								'houseId' : $("#houseId").val()
							},
							success:function(json){
								if(json.length>0){
									$("#checkSip").css("display","inline");
									$("#sure").attr("disabled","disabled");
								}else{
									$("#checkSip").css("display","none");
									$("#sure").attr("disabled",false);
								}
							},
							error:function(){
								alert("检查SIP账号异常");
							}
	        			});
					}
				}); */
				
				//查看绑定账号
				$(".showBoundSip").click(function(){
					$("#syncID").html("");
					//$("body").css("overflow","hidden");
					$.ajax({
        				url:service_url+'smarthomeservice/rest/user/roomNo/showBoundSipByHouseId',
        				type : "post",          
						dataType : 'json',
						cache : false,
						data : {
							'houseId' : $(this).attr("name")
						},
						success:function(json){
							if(json!=null){
								var str="";
								for(var i=0;i<json.length;i++){
									var tbody="<tr><td>"+json[i].id+"</td><td>"+
									json[i].roomNo+"</td><td>"+json[i].sipid+"</td><td>"+
									json[i].deviceNo+"</td><td>"+json[i].status+"</td><td><a href='javascript:void(0)' onclick='unbundling(this)' class='unbound all_hover_but'>"+'解绑'+"</a></td></tr>";
									str+=tbody;
								}
								$("#tbody").html(str);
							
								/* $("#fade").css("display","block");
								$("#table").css("display","block"); */
								$("#fade").slideDown("slow");
								$("#table").slideDown("slow");
								$("#scroll").css("overflow-y","scroll");
							}else{
								alert("查询绑定账号失败");								
							}
						},
						error:function(){
							alert("网络异常,查询绑定账号失败");
						}
        			});
				});
				
				$("#sipid").mouseout(function(){
					$("#checkNull").css("display","none");
				});
				
				$("#select").change(function(){
					$("#checkSip").css("display","none");
				});
				
				$("#icon").click(function(){
					/* $("#fade").css("display","none");
					$("#table").css("display","none"); */
					$("#fade").slideUp("slow");
					$("#table").slideUp("slow");
					//$("body").css("overflow","scroll");
				});
				
        		$("#cancel").click(function(){
        			/* $("#layout").css("display","none");
        			$("#fade").css("display","none"); */
        			$("#layout").slideUp("slow");
        			$("#fade").slideUp("slow");
        		});
        	});
        </script>
    </head>

    <body>
        <form action="<s:url action="queryHouseholdList"/>" method="post">
            <input type="hidden" name="cellId" value="<s:property value="cellId"/>" />
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                    	<!-- <a href="#" class="all_menu_a"><s:text name="housemgr.house.list"/></a> -->
                    	<a href="#" class="all_menu_a">小区房号列表</a>
                        <!-- <a href="queryRegionList" class="all_menu_b"><s:text name="housemgr.region.list"/></a> -->
                        <a href="queryRegionList" class="all_menu_b">小区区域列表</a>
                        <a href="queryBuildingList" class="all_menu_b">小区楼栋列表</a>
                        <a href="queryCellList" class="all_menu_b">小区单元列表</a>
                        <!-- <a href="queryBuildingList" class="all_menu_b"><s:text name="housemgr.building.list"/></a>
                        <a href="queryCellList" class="all_menu_b"><s:text name="housemgr.cell.list"/></a> -->
                        <a href="queryHouseholdList?print"><s:text name="hoursemgr.common.printsitenumber"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                            <label class="all_border_right" for="housingStatus"><s:text name="housemgr.house.list.housingStatus"/></label>
                            <div class="select_div">
                                <select id="housingStatus" name="house.housingStatus">
                                    <option value=""><s:text name="common.title.all"/></option>
                                    <option value="0" <s:if test="house.housingStatus != '' && house.housingStatus == 0">selected</s:if>><s:text name="housemgr.house.list.housingStatus.notIn"/></option>
                                    <option value="1" <s:if test="house.housingStatus != '' && house.housingStatus == 1">selected</s:if>><s:text name="housemgr.house.list.housingStatus.in"/></option>
                                </select>
                            </div>
                            <label class="all_border_right" for="houseName"><s:text name="housemgr.house.list.name"/></label>
			                <input type="text" maxlength="4" id="houseName" name="house.name" value="<s:property value='house.name'/>" class="all_tab_top_input"/>                            

		                    <div class="click_div">
		                        <strong class="screening_show search_but"><s:text name="common.element.action.moreFilter" /></strong>
		                        <ul class="screening_body">
		                            <li>
			                            <label class="all_border_right" for="districtId"><s:text name="housemgr.region.list.search.district"/></label>
			                            <div class="select_div">
			                                <select id="districtId" name="districtId"></select>
			                            </div>
		                            </li>
		                            <li>
			                            <label class="all_border_right" for="regionId"><s:text name="housemgr.region.list.search.regionName"/></label>
			                            <div class="select_div">
			                                <select id="regionId" name="regionId" regionId="<s:property value='regionId'/>"></select>
			                            </div>
		                            </li>
		                            <li>
			                            <label class="all_border_right" for="buildingId"><s:text name="housemgr.building.list.search.buildingName"/></label>
			                            <div class="select_div">
			                                <select id="buildingId" name="buildingId" buildingId="<s:property value='buildingId'/>"></select>
			                            </div>
		                            </li>
		                            <li>
			                            <label class="all_border_right" for="cellName"><s:text name="housemgr.cell.list.search.cellName"/></label>
			                            <input type="text" maxlength="10" id="cellName" name="cellName" value="${param.cellName}" class="all_tab_top_input"/>
		                            </li>
		                            <li>
			                            <label class="all_border_right" for="ownerName"><s:text name="housemgr.house.list.ownerName"/></label>
                            			<input type="text" maxlength="25" id="ownerName" name="house.owner.userName" value="<s:property value='house.owner.userName'/>" class="all_tab_top_input"/>
		                            </li>
		                        </ul>
		                    </div>
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="housemgr.house.list.code"/></th>
                                    <th><s:text name="housemgr.cell.list.name"/></th>
                                    <th><s:text name="housemgr.building.list.name"/></th>
                                    <th><s:text name="housemgr.region.list.name"/></th>
                                    <!-- <th width="120"><s:text name="housemgr.common.sitenumber"/></th> -->
                                    <th>门口机区号</th>
                                    <th><s:text name="housemgr.common.sitenumber"/></th>
                                    <!-- <th width="80"><s:text name="housemgr.house.list.size"/></th> -->
                                    <th><s:text name="housemgr.house.list.area"/></th>
                                    <!--<th width="80"><s:text name="housemgr.house.list.ownerName"/></th>-->
                                    <!-- <th width="50"><s:text name="housemgr.house.list.housingStatus"/></th>-->
                                    <!-- <th ><s:text name="housemgr.house.list.checkInDate"/></th> -->
                                    <!-- <th>SIP账号绑定</th> -->
                                    <!-- <th width="100">门卡</th> -->
                                    <th><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="house">
                                    <tr>
                                        <td>
                                            <a href="<s:url action="viewHouseholdDetail"><s:param name="houseId" value="#house.id"/></s:url>">
                                                <s:property value="#house.code"/>
                                            </a>
                                        </td>
                                        <td><s:property value="#house.THmBuildingCellInfo.name"/></td>
                                        <td><s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.name"/></td>
                                        <td><s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name"/></td>
<!--                                         <td><s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.code"/>&nbsp;<s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.code"/>&nbsp;<s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.code"/>&nbsp;<s:property value="#house.THmBuildingCellInfo.code"/>&nbsp;<s:property value="#house.code"/></td>
 -->
 										<td><s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.code"/></td>
                                        <td><s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.code"/>&nbsp;<s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.code"/>&nbsp;<s:property value="#house.THmBuildingCellInfo.code"/>&nbsp;<s:property value="#house.code"/></td>
                                         <%-- <td>
                                            <s:if test="#house.THmCellSizeInfo">
                                                <a href="${fileServerUrl}/<s:property value="#house.THmCellSizeInfo.plan" />" rel="colorbox1" target="_blank">
                                                    <s:property value="#house.THmCellSizeInfo.room"/><s:text name="housemgr.size.generate.room"/><s:property value="#house.THmCellSizeInfo.hall"/><s:text name="housemgr.size.generate.hall"/>
                                                </a>
                                            </s:if>
                                        </td> --%>
                                        <td><s:property value="#house.area"/></td>
                                        <!--<td><s:if test="#house.owner.userName!=''"><s:property value="#house.owner.userName"/></s:if>
                                        	<s:else>
                                        		<a href="<s:url action="addInput" namespace="/ownerUser"><s:param name="user.houseId" value="#house.id"/></s:url>" class="all_hover_but">添加业主</a>
                                        	</s:else>
                                        </td>-->
                                       <!--  <td>
                                            <s:if test="#house.housingStatus == null || #house.housingStatus == '' || #house.housingStatus == 0"><s:text name="housemgr.house.list.housingStatus.notIn"/></s:if>
                                            <s:if test="#house.housingStatus == 1"><s:text name="housemgr.house.list.housingStatus.in"/></s:if>
                                        </td>
                                        <td><s:date name="#house.checkInDate" format="yyyy-MM-dd"/></td> -->
                                        <!-- <td>
                                        	<s:if test="#device.sipid==''">
	                                        	<td width="180">
	                                        		<font id="label<s:property value='#device.deviceId'/>"></font>
	                                        		<input id="sip<s:property value='#device.deviceId'/>" type="text" size="15"><s:property value="#device.sipid"/></input>
	                                        		<a href="#" id="a<s:property value='#device.deviceId'/>" class="all_hover_but" onClick="postAdd(<s:property value='#device.deviceId'/>)">绑定</a>
	                                        	</td>
	                                        </s:if>
                                        </td> -->
                                       <!--  <td>
                                        	<a href="<s:url action="addInput" namespace="/gateCard">
        										<s:param name="gateCard.houseId" value="#house.id"/></s:url>" class="all_hover_but">
												添加门卡
                                            </a>
                                        </td> -->
                                        <td>
                                        	<%-- <a href="<s:url action="boundHouse" namespace="/sip">
        										<s:param name="qrcodeVO.houseId" value="#house.id"/>
        									</s:url>" class="all_hover_but">
												绑定SIP
                                            </a> --%>
                                            <!-- <button class="showBoundSip" style="width:83px;height:25px;color:#446D8C;background-color:#F0F0F0;border:#CACACA solid 1px">查看绑定账号</button> -->
                                            <a href="javascript:void(0)" class="showBoundSip all_hover_but" name="<s:property value="#house.id"/>">查看绑定</a>
                                            <a href="javascript:boundSipView(<s:property value="#house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.housingDistrictInfo.id"/>,
                                            								 <s:property value="#house.THmBuildingCellInfo.id"/>,
                                            								 <s:property value="#house.id"/>,
                                            								 '<s:property value="#house.code"/>')" class="boundSip all_hover_but" >绑定手机</a>
                                        	
                                            <a href="<s:url action="updateHouseholdInput"><s:param name="houseId" value="#house.id"/></s:url>" class="all_hover_but">
                                                <s:text name="common.element.action.update"/>
                                            </a>
                                            <a href="javascript:confirmRemove('<s:url action="removeHousehold"/>','houseId','<s:property value="#house.id"/>','<s:text name="common.confirm.remove"/>')" class="all_hover_but">
                                                <s:text name="common.element.action.remove"/>
                                            </a>
                                        </td>
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <s:include value="/views/common/paging.jsp"/>
                        <s:if test="successFlag == true">                                       
                            <div class="layer" id="promptFrame">
                                <div>
                                    <p><s:text name="common.system.info"/></p>
                                    <ul>
                                        <li>
                                            <s:text name="common.element.action.operationSuccess" /><br/><br/>
                                        </li>
                                        <li>
                                            <a href="javascript:hideTip('promptFrame');"><s:text name="common.element.action.confirm"/></a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </s:if>
                        <s:elseif test="fromRemove == true && successFlag == false">
                            <div class="layer" id="promptFrame">
                                <div>
                                    <p><s:text name="common.element.action.operationfailed"/></p>
                                    <ul>
                                        <li>
                                            <s:text name="housemgr.house.exist.related.data" /><br/><br/>
                                        </li>
                                        <li>
                                            <a href="javascript:hideTip('promptFrame');"><s:text name="common.element.action.confirm"/></a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </s:elseif>
                        <div class="layer" id="removeFrame" style="display:none;">
                            <div>
                                <p id="removeMsg"></p>
                                <ul>
                                    <li>
                                        <a id="removeUrl" href="#">
                                            <s:text name="common.element.action.confirm"/>
                                        </a>
                                        <a href="javascript:hideTip('removeFrame');"><s:text name="common.element.action.cancel"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <script>
			var selectAllTitle = "<s:text name='common.title.all'/>";

            $(document).ready(function(){
                $("a[rel^='colorbox']").colorbox({title:"",slideshow:true,slideshowAuto:false,previous:"上一张",next:"下一张",close:"关闭",slideshowStart:"播放",slideshowStop:"暂停",current:"第 {current} 张 (共{total}张)"});
            });
        </script>
        
     <div id="layout">
		<div id="top" style="line-height:50px;padding-top:0px;text-align:center">
			<label style="padding-left:0px;font-size:14px"><b>绑定手机账号</b></label>
		</div>
		<div>
			<label>房&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号&nbsp;:</label>
			<span id="roomNo" style="margin-left:3px"></span>
			<input type="hidden" id="houseId"/>
		</div>
		<div>
			<label style="letter-spacing:0.283px;">手机账号&nbsp;:</label>
			<input type="text" id="sipid" style="margin-left:2px" maxlength="20"/>
			<span id="checkNull" style="color:red;display:none">手机账号不能为空</span>
			<span id="checkSip" style="color:red;display:none">手机账号已存在</span>
		</div>
		<div>
			<label style="letter-spacing:1.02px">门&nbsp;口&nbsp;机&nbsp;:</label>
			<!-- <span id="deviceId">1</span> -->
			<span id="housingDistrictRegionInfo" style="display:none"></span>
			<span id="regionBuildingInfo" style="display:none"></span>
			<span id="buildingCellInfo" style="display:none"></span>
			<span id="district" style="display:none"></span>
			<span id="unitId" style="display:none"></span>
			<select id="select" style="height:24px;margin-left:1px">
			
			</select>
			<span id="checkDevice" style="color:red;display:none">未获取到门口机信息</span>
		</div>
		<div>
			<label>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态&nbsp;:</label>
			<span id="status" style="margin-left:3px">1</span>
		</div>
		<div id="button">
			<button id="sure" onclick="submit()">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button id="cancel">取消</button>
		</div>
	</div>
    <div id="fade" class="black_overlay"></div>
    <div class="div_green"><b>门口机已同步</b></div>
	<div class="div_red"><b>门口机未同步</b></div>
    <div id="table" style="display:none">
    	<div id="top" style="height:36px;line-height:36px;padding-top:0px;text-align:center">
			<label style="padding-left:0px;font-size:14px"><b>查看绑定的账号</b></label>
		</div>
    	<span id="icon"></span>
    	<div id="scroll" style="width:100%;height:80%">
    		<table id="tableHeight" border="1" cellspacing="0" cellpadding="6" align="center" style="text-align:center">
	    		<thead>
	    			<tr>
	    				<th width="50px" height="30px">编号</th>
	    				<th width="60px">房号</th>
	    				<th width="60px">手机账号</th>
	    				<th width="60px">设备编号</th>
	    				<th width="60px">状态</th>
	    				<th width="60px">操作</th>
	    			</tr>
	    		</thead>
	    		<tbody id="tbody">
	    		
	    		</tbody>
	    	</table>
	    	<span style="color:green;display:none;position:absolute;bottom:5px;left:215px" class="unboundSuccess">解绑成功</span>
	    	<span style="color:red;display:none;position:absolute;bottom:5px;left:215px" class="unboundFailed">解绑失败</span>
    	</div>
    </div>
    </body>
</html>
