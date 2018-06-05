function saveAppAd(){
	var validateFlag = 0;
	if(!$($("form")[0]).valid())
		validateFlag++;
	
	if(!validateDateRange())
		validateFlag++;
	
	if(!validatePicName())
		validateFlag++;
	
	if(!validateDetailPicName())
		validateFlag++;
	
	if(!validateDeviceTypes())
		validateFlag++;
	
	if(!validateGroups())
		validateFlag++;
	
	if(validateFlag == 0){
		$("#groupIds").val(showsel());
		$("form")[0].submit();
	}	
}

function saveWebAd(){
	var validateFlag = 0;
	if(!$($("form")[0]).valid())
		validateFlag++;
	
	if(!validateDateRange())
		validateFlag++;
	
	if(!validatePicName())
		validateFlag++;
		
	if(validateFlag == 0){
		$("form")[0].submit();
	}
}

function validateGroups(){
	if(showsel() == ""){
		$("#unselectedGroupMsg").show();
		return false;
	}
	
	$("#unselectedGroupMsg").hide();
	return true;
}

function validateDeviceTypes(){
	var checkedDeviceTypes = $("input[name='deviceTypes.deviceType']:checked");
	var isChecked = checkedDeviceTypes.size() > 0;
	if(isChecked)
		$("#unselectedDeviceTypeMsg").hide();
	else
		$("#unselectedDeviceTypeMsg").show();
	return isChecked;
}

function validateDateRange(){
	var validateFlag = 0;

	var notGreaterCurrentTimeMsgObj = $("#notGreaterCurrentTimeMsg");
	var notGreaterBeginTimeMsgObj = $("#notGreaterBeginTimeMsg");
	var beginTimeStr = $("#beginTime").val();
	var beginTimeErrorMsgObj = $("#beginTimeErrorMsg");	
	if(beginTimeStr == ""){
		beginTimeErrorMsgObj.html(errorRequired);
		beginTimeErrorMsgObj.show();
		validateFlag++;
	}else{
		beginTimeErrorMsgObj.hide();
	}
	
	var endTimeStr = $("#endTime").val();	
	var endTimeErrorMsgObj = $("#endTimeErrorMsg");
	if(endTimeStr == ""){
		endTimeErrorMsgObj.html(errorRequired);
		endTimeErrorMsgObj.show();
		validateFlag++;
	}else{
		endTimeErrorMsgObj.hide();
	}
	
	if(validateFlag > 0){
		notGreaterCurrentTimeMsgObj.hide();
		notGreaterBeginTimeMsgObj.hide();
		return false;
	}
			
	var fmt = $('#endTime').attr("dateFormat");
	if(toDate(fmt, beginTimeStr) > new Date()){
		if(toDate(fmt, endTimeStr) > toDate(fmt, beginTimeStr)){
			notGreaterCurrentTimeMsgObj.hide();
			notGreaterBeginTimeMsgObj.hide();
			return true;
		}else{
			notGreaterCurrentTimeMsgObj.hide();
			notGreaterBeginTimeMsgObj.show();
			return false; 
		}
	}
	
	notGreaterCurrentTimeMsgObj.show();
	notGreaterBeginTimeMsgObj.hide();
	return false; 
}

function selectPicName(picName){
	$('#errorFileSizeMsg').hide();
	$('#adPicName').val(picName);
	validatePicName();
}

function validatePicName(){
	return validatePN($('#adPicName').val(), $('#picErrorMsg'));
}

function selectDetailPicName(detailPicName){
	$('#errorDetailFileSizeMsg').hide();
	$('#adDetailPicName').val(detailPicName);
	validateDetailPicName();
}

function validateDetailPicName(){
	return validatePN($('#adDetailPicName').val(), $('#detailPicErrorMsg'));
}

function validatePN(pn, errorMsgObj){
	var adId = $('#adId').val();
	if(adId != '' && pn == '')
		return true;
	
	if(adId == '' && pn == ''){
		errorMsgObj.html(errorRequired);
		errorMsgObj.show();
		return false;
	}	
	
	var extensionIndex = pn.lastIndexOf(".") + 1;
	if(extensionIndex < 1 
			|| !isValidExt(pn.substring(extensionIndex).toUpperCase())){
		errorMsgObj.html(errorFormatMsg);
		errorMsgObj.show();
		return false;
	}
	
	var nameStartIdx = pn.lastIndexOf("\\") + 1;
	if(nameStartIdx > 0 && pn.substring(nameStartIdx).length > fileNameMaxLength){
		errorMsgObj.html(fileNameTooLongMsg);
		errorMsgObj.show();
		return false;
	}
	
	errorMsgObj.hide();	
	return true;
}

function isValidExt(extName){	
	var exts = picExt.split(",");
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
	
	var formObj = $("form")[0];
	$(formObj).attr("action",targetUrl);
	$(formObj).append("<input type='hidden' name='adId' value='" + entityId + "'/>");
	formObj.submit();
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
	if('true' == $('#timingFlag').val()){
		$('#timingFlag').val('false');
	}else{
		$('#timingFlag').val('true');
	}
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
	$(formObj).append("<input type='hidden' name='adId' value='" + entityId + "'/>");
	formObj.submit();
}
