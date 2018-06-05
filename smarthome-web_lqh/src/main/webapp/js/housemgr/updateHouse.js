$(function() {
	var updateHouseCodeNotRepeatAPI = "../housemgr/json/updateHouseCodeNotRepeat";
	
	$("form").submit(function(event) {
		var allowSubmit = true;
		var houseId = $("input[name=houseId]").val();
		var cellId = $("input[name=cellId]").val();
		var houseCodeInput = $("input[name='house.code']");

		var houseCode = houseCodeInput.val();
		if ($.trim(houseCode) === "") {
			return false;
		}

		// 判断数据库中是否已经有重复的编号
		var notRepeat = updateHouseCodeNotRepeat(houseId, houseCode, cellId);
		if (!notRepeat) { // 如果编号重复, 则提示用户, 不让表单提交
			allowSubmit = false;
			houseCodeInput.nextAll("label.exist").show();
			houseCodeInput.focus();
		} else {
			houseCodeInput.nextAll("label.exist").hide();
		}
		
//		if(!validateChargeTypes())
//			allowSubmit = false;
		
		if (!allowSubmit) {
			event.stopImmediatePropagation();
		}
		return allowSubmit;
	});

	function updateHouseCodeNotRepeat(houseId, houseCode, cellId) {
		var notRepeat = false;
		$.ajax({
			type: "POST",
			async: false,
			url: updateHouseCodeNotRepeatAPI,
			data: {
				houseId: houseId,
				houseCodes: houseCode,
				cellId: cellId
			},
			dataType: "json",
			success:function(data) {
				notRepeat = data.notRepeat;
			}
		});
		return notRepeat;
	}
});

function validateChargeTypes(){
	var checkedChargeTypes = $("input[name=selectedChargeTypeIds]:checked");
	var isChecked = checkedChargeTypes.size() > 0;
	if(isChecked)
		$("#unselectedMsg").hide();
	else
		$("#unselectedMsg").show();
	return isChecked;
}