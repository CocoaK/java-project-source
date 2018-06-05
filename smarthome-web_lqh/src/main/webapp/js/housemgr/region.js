$(function() {
	var newRegionCodeNotRepeatAPI = "../housemgr/json/newRegionCodeNotRepeat";
	
	$("#generateCount").change(function() {
		var count = $("#generateCount").val();
		if (/^\d+$/.test(count) && count != 0) {
			newRegionList(count);
		}
	});

	function newRegionList(count) {
		var li = $("#newRegionList li").first();
		var ele = li.find("input[name ^= 'regionCodes']");
		ele.attr("value", "01");
		ele.next("label[class = 'error']").remove();
		ele.nextAll("label.exist").hide();
		var ele2 = li.find("input[name ^= 'regionNames']");
		ele2.attr("value", "A");
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
		markUniqueEle("regionCodes");		
		markUniqueEle("regionNames");
		
		var allowSubmit = true;
		if(!$($("form")[0]).valid()){
			allowSubmit = false;
		}
		
		markSameEle("regionCodes");
		markSameEle("regionNames");
		
		// 判断用户是否生成了坐标, 不是默认的0,0
//		var defaultCoordinatesText = $.grep($("[name=coordinatesText]").get(), function(elem, i) {
//			// 先将所有的提示生成坐标的label隐藏
//			$(elem).next("label.error").hide();
//			return ($(elem).val() === "" || $(elem).val() === "0,0");
//		});
//		if (defaultCoordinatesText.length > 0) {
//			$.each(defaultCoordinatesText, function(i, elem) {
//				$(elem).next("label.error").show();
//			});
//			//event.stopImmediatePropagation();
//			allowSubmit = false;
//		}
		
		if (!allowSubmit){
			event.stopImmediatePropagation();
			return allowSubmit;
		}
		
		// 先判断用户输入的编号是否重复
		var regionCodes = [];		
		var regionCodeInputs = $("input[name=regionCodes]", this);
		regionCodeInputs.each(function(i, elem) {
		    var regionCode = $(elem).val();
		    if ($.inArray(regionCode, regionCodes) == -1) {
		    	regionCodes.push(regionCode);
		    }else{
		    	$(elem).nextAll("label.exist").show();
		    	allowSubmit = false;
		    }
		});
		
		// 在客户端判断用户输入的名称是否重复
		var regionNames = [];
		var regionNameInputs = $("input[name=regionNames]", this);
		regionNameInputs.each(function(i, elem) {
		    var regionName = $(elem).val();
		    if ($.inArray(regionName, regionNames) == -1) {
		    	regionNames.push(regionName);
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

		// 再判断数据库中是否已经有重复的编号
		var districtId = $("#districtId").val();
		regionCodeInputs.each(function(i, elem) {
			var $elem = $(elem);
			var notRepeat = newRegionCodeNotRepeat($elem.val(), districtId);
			if (!notRepeat) { // 如果编号重复, 则提示用户, 不让表单提交
				allowSubmit = false;
				$elem.nextAll("label.exist").show();
				$elem.focus();
			} else {
				$elem.nextAll("label.exist").hide();
			}
		});
		
		var regionId = $("#regionId").val();
		if(regionId == undefined)
			regionId = "";
		regionNameInputs.each(function(i, elem) {
			var regionName = $(elem).val();		
		    if(existRegionName(districtId, regionId, regionName)){
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

	function newRegionCodeNotRepeat(regionCode, districtId) {
		var notRepeat = false;
		$.ajax({
			type: "POST",
			async: false,
			url: newRegionCodeNotRepeatAPI,
			data: {
				regionCodes: regionCode,
				districtId: districtId
			},
			dataType: "json",
			success:function(data) {
				notRepeat = data.newRegionCodeNotRepeat;
			}
		});
		return notRepeat;
	}
});
