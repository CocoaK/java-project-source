$(function() {
	var updateRegionCodeNotRepeatAPI = "../housemgr/json/updateRegionCodeNotRepeat";
	
	$("form").submit(function(event) {
		var allowSubmit = true;
		var regionId = $("input[name='region.id']").val();
		var districtId = $("input[name='region.housingDistrictInfo.id']").val();
		var regionCodeInput = $("input[name='region.code']");
		var regionNameInput = $("input[name='region.name']");
		var regionCode = regionCodeInput.val();
		var regionName = regionNameInput.val();
		if ($.trim(regionCode) === "") {
			allowSubmit = false;
		}
		if ($.trim(regionName) === "") {
			allowSubmit = false;
		}

		if(!allowSubmit)
			return allowSubmit;
		
		// 判断数据库中是否已经有重复的编号
		var notRepeat = updateRegionCodeNotRepeat(regionId, regionCode, districtId);
		if (!notRepeat) { // 如果编号重复, 则提示用户, 不让表单提交
			allowSubmit = false;
			regionCodeInput.nextAll("label.exist").show();
			regionCodeInput.focus();
		} else {
			regionCodeInput.nextAll("label.exist").hide();
		}
		
		if(existRegionName(districtId, regionId, regionName)){			
			regionNameInput.nextAll("label.exist").show();
			regionNameInput.focus();
	    	allowSubmit = false;
	    }else{
	    	regionNameInput.nextAll("label.exist").hide();
	    }
		
		if (!allowSubmit) {
			event.stopImmediatePropagation();			
		}
		return allowSubmit;
	});
	
	function updateRegionCodeNotRepeat(regionId, regionCode, districtId) {
		var notRepeat = false;
		$.ajax({
			type: "POST",
			async: false,
			url: updateRegionCodeNotRepeatAPI,
			data: {
				regionId: regionId,
				regionCodes: regionCode,
				districtId: districtId
			},
			dataType: "json",
			success:function(data) {
				notRepeat = data.updateRegionCodeNotRepeat;
			}
		});
		return notRepeat;
	}
});
