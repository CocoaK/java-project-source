function saveUser(existLoginNameUrl) {
	if(validateSaUser() == 0){
		if(!existLoginName(existLoginNameUrl))
			$("form")[0].submit();
	}
}

function updateUser(){
	if(validateSaUser() == 0){
		$("form")[0].submit();
	}
}

function validateSaUser(){
	var validateFlag = 0;
	
	if(!$($("form")[0]).valid())
		validateFlag++;
	
	if(!validateUserBirthDate())
		validateFlag++;
	
	return validateFlag;
}

function savePass() {	
	var validateFlag = 0;
	if(!$($("form")[0]).valid())
		validateFlag++;
	
	if(!isEqualPass())
		validateFlag++;
	
	if(validateFlag == 0)
		$("form")[0].submit();
}

function isEqualPass(){
	var confirmNewPass = $("#confirmNewPass").val();
	if((confirmNewPass == "") || (confirmNewPass == $("#newPass").val())){
		notEqualPassMsg(false);
		return true;
	}
	notEqualPassMsg(true);
	return false;
}

function notEqualPassMsg(showFlag){
	var msgObj = $('#notEqualMsg');
	if(showFlag)
		msgObj.show();
	else
		msgObj.hide();		
}

function saveOwnerUser(existLoginNameUrl, existHouseIdUrl) {
	var validateFlag = 0;
	if(!$($("form")[0]).valid())
		validateFlag++;
	
	if(!selectedHouse(existHouseIdUrl))
		validateFlag++;
	
	if(!validateUserBirthDate())
		validateFlag++;
	
	if(validateFlag == 0){
		if(existLoginNameUrl != "" && existLoginName(existLoginNameUrl))
			validateFlag++;
		if(validateFlag == 0)
			$("form")[0].submit();
	}
}

function savePaUser(existLoginNameUrl) {
	var validateFlag = 0;
	if(!$($("form")[0]).valid())
		validateFlag++;

	if(!selectedDistrict())
		validateFlag++;
	
	if(!validateUserBirthDate())
		validateFlag++;
	
	if(validateFlag == 0){
		if(existLoginNameUrl != "" && existLoginName(existLoginNameUrl))
			validateFlag++;
		if(validateFlag == 0)
			$("form")[0].submit();
	}
}

function existLoginName(targetUrl){	
	var existLN = true;
	$.ajax({
		type:"POST",
		async:false,
		url:targetUrl,
		data:"currLoginName=" + $('#loginName').attr('value'),
		dataType:"json",
		success:function(data){
			if(data.existLoginName == true){
				$('#existLoginNameMsg').show();
			}else{
				$('#existLoginNameMsg').hide();
				existLN = false;
			}
		}
	});
	return existLN;
}

function selectedHouse(targetUrl){
	var houseId = $("#selectedHouseId").val();
	if(houseId == ""){
		$('#existHouseMsg').hide();
		$('#unselectedHouseMsg').show();		
		return false;
	}
	return (!existHouseId(targetUrl));
}

function existHouseId(targetUrl){
	var currUserId = $('#currUserId').attr('value');
	if(currUserId == undefined)
		currUserId = "";
	
	var existHI = true;
	$.ajax({
		type:"POST",
		async:false,
		url:targetUrl,
		data:"currUserId=" + currUserId + "&user.houseId=" + $('#selectedHouseId').attr('value'),
		dataType:"json",
		success:function(data){
			if(data.existHouseId == true){
				$('#existHouseMsg').show();
				$('#unselectedHouseMsg').hide();
			}else{
				$('#existHouseMsg').hide();
				$('#unselectedHouseMsg').hide();
				existHI = false;
			}
		}
	});
	return existHI;
}

function selectedDistrict(){
	var houseId = $("#selectedDistrictId").val();
	if(houseId == ""){
		$('#unselectedDistrictMsg').show();		
		return false;
	}
	$('#unselectedDistrictMsg').hide();
	return true;
}

function queryDistricts(targetUrl) {
	var params = {
		"housingDistrictName" : $('#districtName').attr('value')
	};
	$.post(targetUrl, params, queryDistrictsCallback, 'json');
}

function queryDistrictsCallback(data) {
	$('#districtResult').html("");
	if(data.districtVos.length > 0){
		$.each(data.districtVos, function(index, item) {
			$('#districtResult').append(
					"<li><label>" + item.name
							+ "</label><input name='returnDistrictId' type='radio' alias='"
							+ item.name + "' value='"
							+ item.id + "'/></li>");
		});
	}else{
		$('#districtResult').append("<li style='text-align: center'><label>" + noDataMsg + "</label></li>");
	}	
}

function appendDistrict() {
	var checkedDistricts = $("input[name='returnDistrictId']:checked");
	$.each(checkedDistricts, function(index, item) {
		$('#selectedDistrictId').attr('value', item.value);
		$('#selectedDistrictName').attr('value', $(item).attr('alias'));
	});
	selectedDistrict();
	$('#list_click').hide();
}

function queryHouses(targetUrl, pageNum) {
	var params = {
		"page.pageNum" : pageNum,
		"regionName" : $('#areaName').attr('value'),
		"buildingName" : $('#buildingName').attr('value'),
		"cellName" : $('#unitName').attr('value'),
		"houseName" : $('#houseName').attr('value')
	};
	$.post(targetUrl, params, queryHousesCallback, 'json');
}

function queryHousesCallback(data) {
	$('#houseResult').html("");
	var pageNum = data.pageNum;
	var totalPages = data.totalPages;
	if(data.foundHouses.length > 0){
		$.each(data.foundHouses, function(index, item) {
			$('#houseResult').append(
					"<tr><td><input name='returnHouseId' type='radio' alias='"
							+ item.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name + '-' + item.THmBuildingCellInfo.THmRegionBuildingInfo.name + '-' + item.THmBuildingCellInfo.name + '-' + item.name + "' value='"
							+ item.id + "'/></td><td>" + item.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name + "</td><td>" + item.THmBuildingCellInfo.THmRegionBuildingInfo.name + "</td><td>" + item.THmBuildingCellInfo.name + "</td><td>" + item.name + "</td></tr>");
		});
	}else{
		$('#houseResult').append("<tr><td colspan='5' align='center'>" + noDataMsg + "</td></tr>");
	}
	
	generatePagingInfo("pageResult", "queryHouses", actionUrl, pageNum, 
			totalPages, firstPageText, previousPageText, nextPageText, lastPageText);
}

function appendHouse(existHouseIdUrl) {
	var checkedHouse = $("input[name='returnHouseId']:checked");
	$.each(checkedHouse, function(index, item) {
		$('#selectedHouseId').attr('value', item.value);
		$('#houseInfo').html($(item).attr('alias'));
	});
	selectedHouse(existHouseIdUrl);
	$('#list_click').hide();
}

function confirmEnableOrDisable(enableOrDisableUrl,loginId,status){
	$('#enableOrDisableUrl').attr("value",enableOrDisableUrl);
	$('#loginId').attr("value",loginId);
	var newStatus = (status == "0") ? "1" : "0";
	$('#status').attr("value",newStatus);
	var enableOrDisableMsg = (status == "0") ? confirmDisableMsg : confirmEnableMsg;
	$('#enableOrDisableMsg').text(enableOrDisableMsg);
	$('#enableOrDisableConfirmFrame').show();
}

function enableOrDisable(){
	$('#enableOrDisableConfirmFrame').hide();
	var params = {
			"loginId" : $('#loginId').val(),
			"status" : $('#status').val()
	};
	$.post($('#enableOrDisableUrl').val(), params, enableOrDisableCallback, 'json');
}

function enableOrDisableCallback(data){
	if(data.promptFlag == true){
		var confirmFunc = "javascript:confirmEnableOrDisable('" + $('#enableOrDisableUrl').val() + "','" + $('#loginId').val() + "','" + $('#status').val() + "');";
		var targetA = $("#" + $('#loginId').val());
		targetA.attr("href",confirmFunc);
		var targetAction = ($('#status').val() == "0") ? disableAction : enableAction;
		targetA.html(targetAction);
		$('#enableOrDisableFrame').show();
	}
}

function confirmResetPass(resetPassUrl,loginName,resetPassConfirmMsg){
	$('#resetPassUrl').attr("value",resetPassUrl);
	$('#currLoginName').attr("value",loginName);
	$('#resetPassConfirmMsg').text(resetPassConfirmMsg);   			
	$('#resetPassConfirmFrame').show();
}

function resetPass() {
	$('#resetPassConfirmFrame').hide();
	var params = {
		"currLoginName" : $('#currLoginName').attr('value')
	};
	$.post($('#resetPassUrl').attr('value'), params, resetPassCallback, 'json');
}

function resetPassCallback(data){
	if(data.promptFlag == true)
		$('#resetPassFrame').show();
}

//与appendDistrict()函数类似,只是加了一些逻辑，弹出层如果不做选择，则在弹出层给出提示
function appendChoseDistrict() {
	var checkedDistricts = $("input[name='returnDistrictId']:checked");
	var listDistricts = $("input[name='returnDistrictId']");
	$.each(listDistricts, function(index, item) {
		$(listDistricts[index]).attr('onclick', 'choseDistrict()');
	});
	$.each(checkedDistricts, function(index, item) {
		$('#selectedDistrictId').attr('value', item.value);
		$('#selectedDistrictName').attr('value', $(item).attr('alias'));
	});
	if(selectedDistrict() && checkedDistricts[0]!=null){
		$('#list_click').hide();
	}else{
		$('#unselectedDistrictMsg').show();
	}
}

//隐藏未选择信息
function choseDistrict() {
	var checkedDistricts = $("input[name='returnDistrictId']:checked");
	if(checkedDistricts[0]!=null){$('#unselectedDistrictMsg').hide();}
}

function validateUserBirthDate(){
	var currEle = $("input[validateType='birthDate']");
	return checkUserBirthDate(currEle);
}

function checkUserBirthDate(currEle){
	var fmt = $(currEle).attr("dateFormat");
	var required = $(currEle).attr("required");
	var errorMsgId = $(currEle).attr("errorEleId");
	return validateBirthDate(currEle, fmt, required, errorMsgId);
}