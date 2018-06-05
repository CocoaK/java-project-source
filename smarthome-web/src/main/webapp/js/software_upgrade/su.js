function saveSoftware(){
	var validateFlag = 0;
	if(!$($("form")[0]).valid())
		validateFlag++;
	
	if(!validateSoftware())
		validateFlag++;
	
	if(!validateDistricts())
		validateFlag++;
	
	if(!validateDeviceTypes())
		validateFlag++;
	
	if(validateFlag == 0){
		$('#save').attr("disabled", "disabled");
		$("#groupIds").val(showsel());
		$("form")[0].submit();
	}
}

function selectSoftware(softwareName){
	$('#softwareFileName').val(softwareName);
	validateSoftware();
}

function validateSoftware(){
	var softwareName = $('#softwareFileName').val();
	var errorMsgObj = $('#fileErrorMsg');
	
	if(softwareName == ''){
		errorMsgObj.html(errorRequired);
		errorMsgObj.show();
		return false;
	}	
	
	var extensionIndex = softwareName.lastIndexOf(".") + 1;
	if(extensionIndex < 1 
			|| !isValidExt(softwareName.substring(extensionIndex).toUpperCase())){
		errorMsgObj.html(errorFormatMsg);
		errorMsgObj.show();
		return false;
	}
	
	var nameStartIdx = softwareName.lastIndexOf("\\") + 1;
	if(nameStartIdx > 0 && softwareName.substring(nameStartIdx).length > fileNameMaxLength){
		errorMsgObj.html(fileNameTooLongMsg);
		errorMsgObj.show();
		return false;
	}
	
	errorMsgObj.hide();	
	return true;
}

function validateDeviceTypes(){
	var softwareId = $('#softwareId').val();
	if(softwareId != undefined && softwareId != "")
		return true;
	
	var checkedDeviceTypes = $("input[name='deviceTypes.deviceType']:checked");
	var isChecked = checkedDeviceTypes.size() > 0;
	if(isChecked)
		$("#unselectedDeviceTypeMsg").hide();
	else
		$("#unselectedDeviceTypeMsg").show();
	return isChecked;
}

function isValidExt(extName){	
	var exts = fileExt.split(",");
	for(var val in exts){
		if(exts[val].toUpperCase() == extName)
			return true;
	}
	
	return false;
}

function confirmPublish(targetUrl, entityId, timingFlag){
	setTimingFlag(timingFlag);
	$('#errorMsg').hide();
	$("#notGreaterCurrTimeMsg").hide();
	$('#timingMsg').hide();
	$('#immediateMsg').show();
	
	var targetFunc = "javascript:doPublish('" + targetUrl + "','" + entityId + "');";
	$('#publishUrl').attr("href",targetFunc);
	
	$('#list_click').show();
}

function doPublish(targetUrl, entityId){
	if('true' == $('#timingFlag').val()){
		if(!validatePublishTime())
			return;
	}
	
	doAction(targetUrl,entityId);
}

function validatePublishTime(){
	var publishTimeStr = $("#publishedTime").val();
	if(publishTimeStr == ""){
		$("#notGreaterCurrTimeMsg").hide();
		$('#errorMsg').show();
		return false;
	}
	
	var fmt = $('#publishedTime').attr("dateFormat");
	if(toDate(fmt, publishTimeStr) > new Date()){
		$('#errorMsg').hide();
		$("#notGreaterCurrTimeMsg").hide();
		return true;
	}
	$('#errorMsg').hide();
	$("#notGreaterCurrTimeMsg").show();
	return false; 
}

function setTimingFlag(timingFlag){
	$('#timingFlag').val(timingFlag);
}

function changeTimingFlag(){
	$('#errorMsg').hide();
	$("#notGreaterCurrTimeMsg").hide();
	$('#publishedTime').val("");
	if('true' == $('#timingFlag').val())
		$('#timingFlag').val('false');
	else
		$('#timingFlag').val('true');   			
}

function confirmApprove(targetUrl,entityId){
	var targetFunc = "javascript:doAction('" + targetUrl + "','" + entityId + "');";
	$('#approveUrl').attr("href",targetFunc);
	$('#approveFrame').show();
}

function validateDistricts(){
	if(showsel() == ""){
		$("#unselectedGroupMsg").show();
		return false;
	}
	
	$("#unselectedGroupMsg").hide();
	return true;
}

function confirmNotify(targetUrl, entityId){
	$('#notifyErrorMsg').hide();
	$("#notifyNotGreaterCurrTimeMsg").hide();
	$('#notifyUpgradeFrame').show();
	
	var targetFunc = "javascript:doNotify('" + targetUrl + "','" + entityId + "');";
	$('#notifyUrl').attr("href",targetFunc);
	
	$('#notifyUpgradeFrame').show();
}

function doNotify(targetUrl, entityId){
	if(!validateUpgradedTime())
		return;
	
	doAction(targetUrl,entityId);
}

function validateUpgradedTime(){
	var upgradedTimeStr = $("#upgradedTime").val();
	var errorMsgObj = $('#notifyErrorMsg');
	var notGreaterCurrTimeMsgObj = $("#notifyNotGreaterCurrTimeMsg");
	if(upgradedTimeStr == ""){
		notGreaterCurrTimeMsgObj.hide();
		errorMsgObj.show();
		return false;
	}
	
	var fmt = $('#upgradedTime').attr("dateFormat");
	if(toDate(fmt, upgradedTimeStr) > new Date()){
		errorMsgObj.hide();
		notGreaterCurrTimeMsgObj.hide();
		return true;
	}
	
	errorMsgObj.hide();
	notGreaterCurrTimeMsgObj.show();
	return false; 
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
	$(formObj).append("<input type='hidden' name='softwareId' value='" + entityId + "'/>");
	formObj.submit();
}