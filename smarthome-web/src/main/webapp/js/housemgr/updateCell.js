$(function() {
	var updateCellCodeNotRepeatAPI = "../housemgr/json/updateCellCodeNotRepeat";
	
	$("form").submit(function(event) {
		var allowSubmit = true;
		var cellId = $("input[name='cell.id']").val();
		var buildingId = $("input[name='cell.THmRegionBuildingInfo.id']").val();
		var cellCodeInput = $("input[name='cell.code']");
		var cellNameInput = $("input[name='cell.name']");
		var cellCode = cellCodeInput.val();
		var cellName = cellNameInput.val();
		
		if ($.trim(cellCode) === "")
			allowSubmit = false;

		if ($.trim(cellName) === "")
			allowSubmit = false;
		
		if(!allowSubmit)
			return allowSubmit;
		
		// 判断数据库中是否已经有重复的编号
		var notRepeat = updateCellCodeNotRepeat(cellId, cellCode, buildingId);
		if (!notRepeat) { // 如果编号重复, 则提示用户, 不让表单提交
			allowSubmit = false;
			cellCodeInput.nextAll("label.exist").show();
			cellCodeInput.focus();
		} else {
			cellCodeInput.nextAll("label.exist").hide();
		}
		
		if(existCellName(buildingId, cellId, cellName)){
			cellNameInput.nextAll("label.exist").show();
			cellNameInput.focus();
	    	allowSubmit = false;
	    }else{
	    	cellNameInput.nextAll("label.exist").hide();
	    }
		
		if (!allowSubmit) {
			event.stopImmediatePropagation();
		}
		return allowSubmit;
	});

	function updateCellCodeNotRepeat(cellId, cellCode, buildingId) {
		var notRepeat = false;
		$.ajax({
			type: "POST",
			async: false,
			url: updateCellCodeNotRepeatAPI,
			data: {
				cellId: cellId,
				cellCodes: cellCode,
				buildingId: buildingId
			},
			dataType: "json",
			success:function(data) {
				notRepeat = data.notRepeat;
			}
		});
		return notRepeat;
	}
});
