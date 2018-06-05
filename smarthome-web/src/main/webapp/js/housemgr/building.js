$(function() {
	var newBuildingCodeNotRepeatAPI = "../housemgr/json/newBuildingCodeNotRepeat";
	
	$("#generateCount").change(function() {
		var count = $("#generateCount").val();
		if (/^\d+$/.test(count) && count != 0) {
			newBuildingList(count);
		}
	});

	function newBuildingList(count) {
		var li = $("#newRegionList li").first();
		var ele = li.find("input[name ^= 'buildingCodes']");
		ele.attr("value", "001");
		ele.next("label[class = 'error']").remove();
		ele.nextAll("label.exist").hide();
		var ele2 = li.find("input[name ^= 'buildingNames']");
		ele2.attr("value", "001");
		ele2.next("label[class = 'error']").remove();
		ele2.nextAll("label.exist").hide();
		var ele3 = li.find("input[name ^= 'coordinatesText']");
		ele3.attr("value", "0,0");
		ele3.next("label.error").hide();
				
		$("#newRegionList").empty();
		for (var i = 0; i < count; i++) {
			li.clone().appendTo($("#newRegionList"));
		}
	}

	$(".notetitle").live("click", function() {
    	$("#list_click").slideDown();
    	// @see map.js
    	map.multipleLocation = $("#newRegionList li").index($(this).parent("li"));
    });
	
	$("form").submit(function(event) {
		markUniqueEle("buildingCodes");		
		markUniqueEle("buildingNames");
		
		var allowSubmit = true;
		if(!$($("form")[0]).valid())
			allowSubmit = false;
		
		markSameEle("buildingCodes");
		markSameEle("buildingNames");
		
		// 判断用户是否生成了坐标, 不是默认的0,0
		var defaultCoordinatesText = $.grep($("[name=coordinatesText]").get(), function(elem, i) {
			// 先将所有的提示生成坐标的label隐藏
			$(elem).next("label.error").hide();
			return ($(elem).val() === "" || $(elem).val() === "0,0");
		});
		if (defaultCoordinatesText.length > 0) {
			$.each(defaultCoordinatesText, function(i, elem) {
				$(elem).next("label.error").show();
			});
			//event.stopImmediatePropagation();
			allowSubmit = false;
		}
		
		if (!allowSubmit){
			event.stopImmediatePropagation();
			return allowSubmit;
		}
		
		// 在前端判断用户输入的编号是否重复
		var buildingCodes = [];
		var buildingCodeInputs = $("input[name=buildingCodes]", this);
		buildingCodeInputs.each(function(i, elem) {		    
			var buildingCode = $(elem).val();
		    if ($.inArray(buildingCode, buildingCodes) == -1) {
		    	buildingCodes.push(buildingCode);
		    }else{
		    	$(elem).nextAll("label.exist").show();
		    	allowSubmit = false;
		    }
		});
		
		// 在前端判断用户输入的名称是否重复
		var buildingNames = [];
		var buildingNameInputs = $("input[name=buildingNames]", this);
		buildingNameInputs.each(function(i, elem) {
		    var buildingName = $(elem).val();
		    if ($.inArray(buildingName, buildingNames) == -1) {
		    	buildingNames.push(buildingName);
		    	$(elem).nextAll("label.exist").hide();
		    }else{
		    	$(elem).nextAll("label.exist").show();
		    	allowSubmit = false;
		    }
		});
		
		if(!allowSubmit){
			event.stopImmediatePropagation();
			return allowSubmit;
		}

		// 到后台判断是否已经有重复的编号
		var regionId = $("input[name=regionId]").val();
		buildingCodeInputs.each(function(i, elem) {
			var $elem = $(elem);
			var notRepeat = newBuildingCodeNotRepeat($elem.val(), regionId);
			if (!notRepeat) { // 如果编号重复, 则提示用户, 不让表单提交
				allowSubmit = false;
				$elem.nextAll("label.exist").show();
				$elem.focus();
			} else {
				$elem.nextAll("label.exist").hide();
			}
		});
		
		// 到后台判断是否已经有重复的名称
		var buildingId = $("#buildingId").val();
		if(buildingId == undefined)
			buildingId = "";
		buildingNameInputs.each(function(i, elem) {
			var buildingName = $(elem).val();		
		    if(existBuildingName(regionId, buildingId, buildingName)){
		    	$(elem).nextAll("label.exist").show();
		    	$(elem).focus();
		    	allowSubmit = false;
		    }else{
		    	$(elem).nextAll("label.exist").hide();
		    }
		});
		
		if (!allowSubmit) {
			event.stopImmediatePropagation();
		}
		return allowSubmit;
	});

	function newBuildingCodeNotRepeat(buildingCode, regionId) {
		var notRepeat = false;
		$.ajax({
			type: "POST",
			async: false,
			url: newBuildingCodeNotRepeatAPI,
			data: {
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
