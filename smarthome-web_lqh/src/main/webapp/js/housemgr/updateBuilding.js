$(function() {
	var updateBuildingCodeNotRepeatAPI = "../housemgr/json/updateBuildingCodeNotRepeat";
	
	$("form").submit(function(event) {
		if(!$($("form")[0]).valid()){
			event.stopImmediatePropagation();
			return false;
		}
			
		
		var allowSubmit = true;
		
		var buildingCodeInput = $("input[name='building.code']");
		var buildingNameInput = $("input[name='building.name']");
		var buildingCode = buildingCodeInput.val();
		var buildingName = buildingNameInput.val();
		
//		if ($.trim(buildingCode) === "")
//			allowSubmit = false;
//
//		if ($.trim(buildingName) === "")
//			allowSubmit = false;
//
//		if(!allowSubmit)
//			return allowSubmit;
		
		var buildingId = $("input[name='building.id']").val();
		var regionId = $("input[name='building.THmHousingDistrictRegionInfo.id']").val();
		// 判断数据库中是否已经有重复的编号
		var notRepeat = updateBuildingCodeNotRepeat(buildingId, buildingCode, regionId);
		if (!notRepeat) { // 如果编号重复, 则提示用户, 不让表单提交
			allowSubmit = false;
			buildingCodeInput.nextAll("label.exist").show();
			buildingCodeInput.focus();
		} else {
			buildingCodeInput.nextAll("label.exist").hide();
		}
		
		if(existBuildingName(regionId, buildingId, buildingName)){
			buildingNameInput.nextAll("label.exist").show();
			buildingNameInput.focus();
	    	allowSubmit = false;
	    }else{
	    	buildingNameInput.nextAll("label.exist").hide();
	    }
		
		if (!allowSubmit) {
			event.stopImmediatePropagation();
		}
		return allowSubmit;
	});

	function updateBuildingCodeNotRepeat(buildingId, buildingCode, regionId) {
		var notRepeat = false;
		$.ajax({
			type: "POST",
			async: false,
			url: updateBuildingCodeNotRepeatAPI,
			data: {
				buildingId: buildingId,
				buildingCodes: buildingCode,
				regionId: regionId
			},
			dataType: "json",
			success:function(data) {
				notRepeat = data.notRepeat;
			}
		});
		return notRepeat;
	}
});
