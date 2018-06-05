$(function() {
	// 户型数变化时更新户型输入列表
	$("#generateCount").change(function() {
		var count = $("#generateCount").val();
		if (/^\d+$/.test(count) && count != 0) {
			newSizeList(count);
		}
	});

	// 户型房/厅变化时更新房间数
	$("#newSizeList").delegate("select", "click", function() {
		var $td = $(this).closest("td");

		var roomCount = 0;
		$("select", $td).each(function(i, el) {
			roomCount += parseInt($(el).val());
		});

		var pfnObj = $td.find("[name=planFileName]");
		pfnObj.val("");
		pfnObj.nextAll("a.ala_show").remove();
		$td.find("[name=planFileErrorMsg]").hide();
		$td.find("[name=generateRoomCount]").val(roomCount).change();
	});

	// 房间数变化时更新房间名输入项
	$("#newSizeList").delegate("[name=generateRoomCount]", "change", function() {
		newRoomInfoList($(this));
	});

	/**
	 * 生成房间信息输入列表
	 */
	function newRoomInfoList($generateRoomCout) {
		var generateRoomCount = $generateRoomCout.val();
		//var roomInfoDiv = $generateRoomCout.nextAll("div.cb:first");
		
		if (/^\d+$/.test(generateRoomCount) && generateRoomCount > 0) {
			// 清除原来的房间信息
			//$generateRoomCout.nextAll("div.cb").remove();
			$generateRoomCout.parent().parent().children("ul:last").children("li").children("a").remove();
			var roomInfo = $generateRoomCout.parent().parent().children("ul:last").children("li:first");
			roomInfo.find("[name=roomPlanFileName]").val("");
			roomInfo.find("[name=roomPlanFileErrorMsg]").hide();
			$generateRoomCout.parent().parent().children("ul:last").children("li").remove();
			
			for (var i = 0; i < generateRoomCount; i++) {
				// 通过clone的方式来新增房间信息输入项
				var clonedRoom = roomInfo.clone();
				// 房间名label
				var roomNameTextLabel = clonedRoom.find("label:first");
				// 取出房间名, 例如房间名为: 房间 1, 以空格分开房间名和索引号
				var roomNameText = roomNameTextLabel.html().split(" ")[0];
				roomNameTextLabel.html(roomNameText + " " + (i + 1));
				// 房间名输入框的值默认显示为房间名的label
				clonedRoom.find("[name=roomName]").val(roomNameTextLabel.html());
				clonedRoom.appendTo($generateRoomCout.parent().parent().children("ul:last"));
				// 生成随机不重复的id来让jquery.validate对name相同的input都做验证
				//clonedRoom.find("[name=roomName]").attr("id", "roomName" + $.now() + "_" + i);
				//clonedRoom.find("[name=roomPlan]").attr("id", "roomPlan" + $.now() + "_" + i);

				//clonedRoom.appendTo($generateRoomCout.closest("td"));
			}
		}
	}
	
	/**
	 * 生成户型信息输入列表
	 */
	function newSizeList(count) {
		// 用于放置户型选择的tbody
		var newSizeList = $("#newSizeList");
		// 一个户型选择代表一个tr
		var tr = $("#newSizeList tr").first();
		tr.find("[name=planFileName]").val("");
		tr.find("[name=planFileErrorMsg]").hide();
		newSizeList.empty();
		
		for (var i = 0; i < count; i++) {
			// 通过clone的方式来新增户型选择区域
			var trClone = tr.clone();
			// 户型名的TD
			var sizeTextTd = trClone.find("td").first();
			// 取出户型名, 例如户型名为: 户型 1, 以空格分开户型名和索引号
			var sizeText = sizeTextTd.html().split(" ")[0];
			// 新生成的户型名重新分配索引号
			sizeTextTd.html(sizeText + " " + (i + 1));
			// 重置房间数
			newRoomInfoList(trClone.find("[name=generateRoomCount]").val("2"));

			// 生成随机不重复的id来让jquery.validate对name相同的input都做验证
			trClone.find("[name=plan]").attr("id", "plan" + $.now() + "_" + i);

			trClone.appendTo(newSizeList);
		}
	}

	$("form").submit(function(event) {
		markUniqueEle("generateRoomCount");		
		markUniqueEle("roomName");
		
//		var counts = $("input[name=generateRoomCount]", this);		
//		var currCounts = [];
//		counts.each(function(i, elem) {
//			var countId = "generateRoomCount" + i;
//			$(elem).attr('name', countId);
//			$(elem).attr('id', countId);
//			currCounts.push(countId);
//		});
//
//		var rns = $("input[name=roomName]", this);
//		var currRoomNames = [];
//		rns.each(function(i, elem) {
//			var rnid = "roomName" + i;
//			$(elem).attr('name', rnid);
//			$(elem).attr('id', rnid);
//			currRoomNames.push(rnid);
//		});

		var allowSubmit = true;
		if(!$($("form")[0]).valid())
			allowSubmit = false;
		
		markSameEle("generateRoomCount");
		markSameEle("roomName");
		
//		$.each(currCounts, function(index, item) {
//			$("#" + item).attr('name', "generateRoomCount");
//		});
//		
//		$.each(currRoomNames, function(index, item) {
//			$("#" + item).attr('name', "roomName");
//		});

		var pfns = $("input[name=planFileName]", this);
		var pfems = $("label[name=planFileErrorMsg]", this);
		pfns.each(function(i, elem) {
			if(!validateFileName($(elem).val(), $(pfems[i]), validFileExts, ignorePlanEmpty))
				allowSubmit = false;
		});
						
		var rpfns = $("input[name=roomPlanFileName]", this);		
		var rpfems = $("label[name=roomPlanFileErrorMsg]", this);
		rpfns.each(function(i, elem) {
			if(!validateFileName($(elem).val(), $(rpfems[i]), validFileExts, ignorePlanEmpty))
				allowSubmit = false;
		});
		
		if (!allowSubmit){
			event.stopImmediatePropagation();
			return allowSubmit;
		}
		
		$("#newSizeList tr").each(function(i, cellTr) {
			if (allowSubmit) {
				var roomNameInputs = $(cellTr).find("[name=roomName]");

				// 取出每个户型中房间的名称
				// 遍历每个房间名看是否有重复的, 有重复则不提交表单
				var roomNames = [];
				roomNameInputs.each(function(i1, roomNameInput) {
				    var roomName = $(roomNameInput).val();
				    if ($.inArray(roomName, roomNames) == -1) {
				    	roomNames.push(roomName);
				    }
				});
				if (roomNames.length != roomNameInputs.length) {
					$("#promptRepeat").slideDown();
					roomNameInputs.focus();
					allowSubmit = false;
				}
			}
		});

		return allowSubmit;
	});
});

function selectPlanFileName(fileObj, fileName, errorMsgName, ignoreEmpty){
	var currFileObj = $(fileObj).parent().children("input[name=" + fileName +"]");
	currFileObj.val($(fileObj).val());
	validateFileName(currFileObj.val(), $(fileObj).parent().children("label[name=" + errorMsgName +"]"), validFileExts, ignoreEmpty);
}