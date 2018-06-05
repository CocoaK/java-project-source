function saveGateCard(existCardNoUrl) {	
	var validateFlag = 0;
	if(!$($("form")[0]).valid())
		validateFlag++;
	
	if(!validateDevices())
		validateFlag++;
	
	if(!validateDateRanges())
		validateFlag++;
	
	if(validateFlag == 0){
		if(!existCardNo(existCardNoUrl)){
			preSave();
			$("form")[0].submit();
		}
	}
}

function updateGateCard(existCardNoUrl) {	
	var validateFlag = 0;
	if(!$($("form")[0]).valid())
		validateFlag++;
	
	if(!validateDevices())
		validateFlag++;
	
	if(!validateDateRanges())
		validateFlag++;
	
	if(validateFlag == 0){
		if(!existCardNo(existCardNoUrl)){
			preSave();
			$("form")[0].submit();
		}
	}
}

function preSave(){
	var checkedPermissionsId = $("input[name='gatePermissions.gatePermissionsId']");
	$.each(checkedPermissionsId, function(index, item) {
		$(item).attr('name', "gatePermissions[" + index + "].gatePermissionsId");
	});

	var checkedDeviceIds = $("input[name='gatePermissions.device.deviceId']");
	$.each(checkedDeviceIds, function(index, item) {
		$(item).attr('name', "gatePermissions[" + index + "].device.deviceId");
	});

	var checkedBeginTimes = $("input[name='gatePermissions.beginTime']");
	$.each(checkedBeginTimes, function(index, item) {
		$(item).attr('name', "gatePermissions[" + index + "].beginTime");
	});

	var checkedEndTimes = $("input[name='gatePermissions.endTime']");
	$.each(checkedEndTimes, function(index, item) {
		$(item).attr('name', "gatePermissions[" + index + "].endTime");
	});
}

function existCardNo(targetUrl){
	var gateCardId = $('#gateCardId').val();
	if(gateCardId == undefined)
		gateCardId = "";
	var existCN = true;
	$.ajax({
		type:"POST",
		async:false,
		url:targetUrl,
		data:"cardNo=" + $('#cardNo').attr('value') + "&gateCardId=" + gateCardId,
		dataType:"json",
		success:function(data){
			if(data.existCardNo == true){
				$('#existCardNoMsg').show();
			}else{
				$('#existCardNoMsg').hide();
				existCN = false;
			}
		}
	});
	return existCN;
}

function validateDevices(){
	var checkedDeviceIds = $("input[name='selectedDeviceId']:checked");
	var isChecked = checkedDeviceIds.size() > 0;
	if(isChecked)
		$("#unselectedDeviceMsg").hide();
	else
		$("#unselectedDeviceMsg").show();
	return isChecked;
}

function validateDateRanges(){
	var validateFlag = 0;
	var beginTimes = $("input[name='gatePermissions.beginTime']");
	var endTimes = $("input[name='gatePermissions.endTime']");
	$.each(beginTimes, function(index, item) {
		if(!validateDR(item, endTimes[index]))
			validateFlag++;
	});
	
	if(validateFlag == 0)
		return true;
	
	return false; 
}

function validateDR(beginTimeObj, endTimeObj){
	var fmt = $(endTimeObj).attr("dateFormat");
	var timeId = $(endTimeObj).attr("timeId");
	var beginTimeStr = $(beginTimeObj).val();
	var endTimeStr = $(endTimeObj).val();
	var msgObj = $("#notGreaterBeginTimeMsg" + timeId);
	var validateFlag = 0;
	if(beginTimeStr != "" || endTimeStr != ""){
		if(beginTimeStr != ""){
			if(toDate(fmt, beginTimeStr) <= new Date()){
				msgObj.html(beginTimeMsg);
				validateFlag++;
			}
		}
		
		if(endTimeStr != ""){
			if(toDate(fmt, endTimeStr) <= new Date()){
				msgObj.html(endTimeMsg);
				validateFlag++;
			}
		}
		
		if(validateFlag == 0 && beginTimeStr != "" && endTimeStr != ""){
			if(toDate(fmt, endTimeStr) <= toDate(fmt, beginTimeStr)){
				msgObj.html(dateRangeMsg);
				validateFlag++;
			}
		}
	}
	
	if(validateFlag > 0){
		msgObj.show();
		return false;
	}
	
	msgObj.html("");
	msgObj.hide();
	return true;
}

function queryDevices() {
	var params = {
		"device.housingDistrictRegionInfo.name" : $('#areaName').attr('value'),
		"device.regionBuildingInfo.name" : $('#buildingName').attr('value'),
		"device.buildingCellInfo.name" : $('#unitName').attr('value')
	};
	$.post(targetUrl, params, queryDevicesCallback, 'json');
}

function queryDevicesCallback(data) {	
	var deviceResultObj = $('#deviceResult');
	deviceResultObj.html("");
	if(data.devices.length > 0){
		var areaName = "";
		var buildingName = "";
		var cellName = "";
		$.each(data.devices, function(index, item) {
			if(item.housingDistrictRegionInfo != undefined)
				areaName = item.housingDistrictRegionInfo.name;
			if(item.regionBuildingInfo != undefined)
				buildingName = item.regionBuildingInfo.name;
			if(item.buildingCellInfo != undefined)
				cellName = item.buildingCellInfo.name;
			deviceResultObj.append(
					"<tr><td><input name='returnDeviceId' type='checkbox' alias='" + item.deviceName + "' value='" + item.deviceId + "'/></td><td>" + item.deviceName + "</td><td>" + item.deviceType.deviceName + "</td><td>" + areaName + "</td><td>" + buildingName + "</td><td>" + cellName + "</td></tr>");
		});
	}else{
		deviceResultObj.append("<tr><td colspan='6' align='center'>" + noDataMsg + "</td></tr>");
	}
}

function appendDevices() {
	var checkedDeviceIds = $("input[name='returnDeviceId']:checked");
	$.each(checkedDeviceIds, function(index, item) {
		appendDevice(item.value, $(item).attr('alias'));
	});
	$('#deviceResult').html("");
	validateDevices();
	$('#list_click').hide();	
}

function appendDevice(deviceId, deviceAlias) {
	var selectedDeviceIds = $(":checkbox[name='selectedDeviceId']");
	if (!containValue(selectedDeviceIds, deviceId)) {
		$('#selectedDevices')
				.append(
						"<li id='"
								+ deviceId
								+ "'><label>"
								+ deviceAlias
								+ "</label><input name='selectedDeviceId' type='checkbox' value='"
								+ deviceId + "' checked disabled/></li>");
		$('#selectedGates')
				.append(
						"<li id='"
								+ deviceId
								+ "'><strong class='jur_tab_long'>"
								+ deviceAlias
								+ "</strong><input type='hidden' name='gatePermissions.device.deviceId' value='"
								+ deviceId
								+ "'/>"
								+ "<input type='text' id='beginTime" + deviceId + "' timeId='" + deviceId + "' name='gatePermissions.beginTime' dateFormat='yyyy-MM-dd hh:mm' onclick='SelectDate(this,\"yyyy-MM-dd hh:mm\",0,-150)' onchange='validateDR(this, $(\"#endTime" + deviceId + "\"))' readonly='true' />"
								+ "<strong>---</strong><input type='text' id='endTime" + deviceId + "' timeId='" + deviceId + "' name='gatePermissions.endTime' dateFormat='yyyy-MM-dd hh:mm' onclick='SelectDate(this,\"yyyy-MM-dd hh:mm\",0,-150)' onchange='validateDR($(\"#beginTime" + deviceId + "\"), this)' readonly='true' />"
								+ "<a href='javascript:removeGate(" + deviceId
								+ ")' class='all_hover_but'>" + removeButName
								+ "</a><label id='notGreaterBeginTimeMsg" + deviceId + "' class='error' style='display:none;'></label></li>");
	}
}

function clearDeviceResult(){
	$('#deviceResult').html('');
}

function removeGate(deviceId) {
	$("ul[id='selectedDevices'] li[id='" + deviceId + "']").remove();
	$("ul[id='selectedGates'] li[id='" + deviceId + "']").remove();
	validateDevices();
}

function containValue(targetElements, currValue) {
	var containFlag = false;
	$.each(targetElements, function(index, item) {
		if (item.value == currValue) {
			containFlag = true;
			return;
		}
	});

	return containFlag;
}

function confirmAction(targetUrl,entityId,confirmMsg){
	var targetFunc = "javascript:doAction('" + targetUrl + "','" + entityId + "');";
	$('#targetUrl').attr("href",targetFunc);
	$('#confirmMsg').text(confirmMsg);
	$('#confirmFrame').show();
}

function doAction(targetUrl,entityId){
	var formObj = $("form")[0];
	$(formObj).attr("action",targetUrl);
	$(formObj).append("<input type='hidden' name='gateCardId' value='" + entityId + "'/>");
	formObj.submit();
}

function confirmEnableOrDisable(targetUrl,gateCardId,targetStatus,confirmMsg){
	var targetFunc = "javascript:enableOrDisable('" + targetUrl + "','" + gateCardId + "','" + targetStatus + "');";
	$('#enableOrDisableUrl').attr("href",targetFunc);
	$('#enableOrDisableMsg').text(confirmMsg);
	$('#enableOrDisableFrame').show();
}

function enableOrDisable(targetUrl,gateCardId,targetStatus){
	$($("form")[0]).attr("action",targetUrl);
	$('#enableOrDisableFrame').append(
			"<input type='hidden' name='gateCardId' value='" + gateCardId + "'/><input type='hidden' name='status' value='" + targetStatus + "'/>");
	$("form")[0].submit();
}