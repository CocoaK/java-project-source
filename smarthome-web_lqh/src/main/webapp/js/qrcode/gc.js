
function validateDevices(){
	var device = $("#selectedDevices").val();
	var doorSipid = $("#doorSipid").val();
	if(doorSipid!=null && doorSipid!="")
		$("#noSip").hide();
	else{
		$("#noSip").show();
		$("#btn").attr("disabled",true);
	}
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
					"<tr><td><input name='returnDeviceId' type='radio' alias='" + item.deviceName + "' value='" + item.sipid + "'/></td><td>" + item.deviceName + "</td><td>" + item.deviceType.deviceName + "</td><td>" + areaName + "</td><td>" + buildingName + "</td><td>" + cellName + "</td></tr>");
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
	//validateDevices();
	$('#list_click').hide();	
}

function appendDevice(doorSipid, deviceAlias) {
	var selectedDeviceIds = $(":radio[name='selectedDeviceId']");
	if (!containValue(selectedDeviceIds, doorSipid)) {
//		$('#selectedDevices').text(deviceAlias+"   ["+doorSipid+"]");
		$('#selectedDevices').text(deviceAlias);
		$('#doorSipid').val(doorSipid);
	}
}

function clearDeviceResult(){
	$('#deviceResult').html('');
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

function toVaild() {
	var device = $('#selectedDevices').text();
	var flag = true;
	if(device==null || device==""){
		flag = false;
	}
	return flag;
}