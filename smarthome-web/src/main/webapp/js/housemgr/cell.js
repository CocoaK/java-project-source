$(function() {
	var newCellCodeNotRepeatAPI = "../housemgr/json/newCellCodeNotRepeat";

	$("#generateCount").change(function() {
		var count = $("#generateCount").val();
		if (/^\d+$/.test(count) && count != 0) {
			newCellList(count);
		}
	});

	function newCellList(count) {
		var inputTr = $("#newCellTable tr").eq(1);

		$("#newCellTable tr").each(function(index, element) {
			// 在清空元素的时候, 保留tbody中的第一个tr, 用于给用户输入待生成单元的数量
			if (index != 0) {
				$(element).remove();
			}
		});
		
		var ele = inputTr.find("input[name ^= 'cellCodes']");
		ele.attr("value", "01");
		ele.next("label[class = 'error']").remove();
		ele.nextAll("label.exist").hide();
		
		var ele2 = inputTr.find("input[name ^= 'cellNames']");
		ele2.attr("value", "01");
		ele2.next("label[class = 'error']").remove();
		ele2.nextAll("label.exist").hide();
		
		for (var i = 0; i < count; i++) {
			var inputTrClone = inputTr.clone();
			inputTrClone.find("td").first().text(i + 1);
			inputTrClone.appendTo($("#newCellTable"));
		}
	}

	$("form").submit(function(event) {
		markUniqueEle("cellCodes");		
		markUniqueEle("cellNames");
		
		var allowSubmit = true;
		if(!$($("form")[0]).valid())
			allowSubmit = false;
		
		markSameEle("cellCodes");
		markSameEle("cellNames");
		
		if (!allowSubmit){
			event.stopImmediatePropagation();
			return allowSubmit;
		}
		
		// 先判断用户输入的编号是否重复
		var cellCodes = [];
		var cellCodeInputs = $("input[name=cellCodes]", this);
		cellCodeInputs.each(function(i, elem) {
		    var cellCode = $(elem).val();
		    if ($.inArray(cellCode, cellCodes) == -1) {
		    	cellCodes.push(cellCode);
		    }else{
		    	$(elem).nextAll("label.exist").show();
		    	allowSubmit = false;
		    }
		});
		
		// 在客户端判断用户输入的名称是否重复
		var cellNames = [];
		var cellNameInputs = $("input[name=cellNames]", this);
		cellNameInputs.each(function(i, elem) {
		    var cellName = $(elem).val();
		    if ($.inArray(cellName, cellNames) == -1) {
		    	cellNames.push(cellName);
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
		var buildingId = $("input[name=buildingId]").val();
		cellCodeInputs.each(function(i, elem) {
			var $elem = $(elem);
			var notRepeat = newCellCodeNotRepeat($elem.val(), buildingId);
			if (!notRepeat) { // 如果编号重复, 则提示用户, 不让表单提交
				allowSubmit = false;
				$elem.nextAll("label.exist").show();
				$elem.focus();
			} else {
				$elem.nextAll("label.exist").hide();
			}
		});
		
		var cellId = $("#cellId").val();
		if(cellId == undefined)
			cellId = "";
		cellNameInputs.each(function(i, elem) {
			var cellName = $(elem).val();		
		    if(existCellName(buildingId, cellId, cellName)){
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

	function newCellCodeNotRepeat(cellCode, buildingId) {
		var notRepeat = false;
		$.ajax({
			type: "POST",
			async: false,
			url: newCellCodeNotRepeatAPI,
			data: {
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
