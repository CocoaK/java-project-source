$(function() {
	var newHouseCodeNotRepeatAPI = "../housemgr/json/newHouseCodeNotRepeat";
	
	$("#houseCreate").click(function() {
		var startFloor = $("#startFloor").val();
		var endFloor = $("#endFloor").val();
		var houseCount = $("#houseCount").val();
		if(startFloor==0){
			$("#msg").text("起始层数不能为零！");	
			return;
		}
		if(parseInt(endFloor)<parseInt(startFloor)){
			$("#msg").text("结束层数不能小于起始层数！");	
			return;
		}
		if(houseCount==0){
			$("#msg").text("每层户数不能为零！");	
			return;
		}
			newHouseList(startFloor,endFloor,houseCount);
	});

	
	//起始房号，每层户数，层数
	function newHouseList(startFloor,endFloor,houseCount) {
		var inputTr = $("#newHouseTable tr").eq(1);
		inputTr.find("td").removeAttr("class");
		$("#newHouseTable tr").each(function(index, element) {
			// 在清空元素的时候, 保留tbody中的第一个tr, 用于给用户输入待生成单元的数量
			if (index != 0) {
				$(element).remove();
			}
		});

		var floor = "";
		var house = "";
		var s = 0;
		for (var i = parseInt(startFloor); i <= parseInt(endFloor); i++) {
			if(i<10){
				floor = "0"+i;
			}else{
				floor = i;
			}
			for(var j = 1; j <= houseCount; j++){
				if(j<10){
					house = "0"+j;
				}else{
					house = j
				}
				s++;
				var ele = inputTr.find("input[name ^= 'houseCodes']");
				ele.attr("value", floor+house);
				ele.next("label[class = 'error']").remove();
				ele.nextAll("label.exist").hide();
				var inputTrClone = inputTr.clone();
				inputTrClone.find("td").first().text(s);
				inputTrClone.appendTo($("#newHouseTable"));	
			}
					
		}				
	}
	
	$("form").submit(function(event) {
		markUniqueEle("houseCodes");
		
		var allowSubmit = true;
		if(!$($("form")[0]).valid())
			allowSubmit = false;
		
		markSameEle("houseCodes");
		
		if (!allowSubmit){
			event.stopImmediatePropagation();
			return allowSubmit;
		}
				
		// 先判断用户输入的编号是否重复
		var houseCodes = [];
		// 去掉第一个input(输入待生成房号的个数)
		//var houseCodeInputs = $("form").find("input:text:gt(0)");
		var houseCodeInputs = $("input[name=houseCodes]", this);
		houseCodeInputs.each(function(i, el) {
		    var houseCode = $(el).val();
		    if ($.inArray(houseCode, houseCodes) == -1) {
		        houseCodes.push(houseCode);
		    }else{
		    	$(el).nextAll("label.exist").show();
		    	allowSubmit = false;
		    }
		});
		
		if(!allowSubmit){
			event.stopImmediatePropagation();
			return allowSubmit;
		}

		// 再判断数据库中是否已经有重复的编号
		var cellId = $("input[name='cell.id']").val();
		houseCodeInputs.each(function(i, elem) {
			var $elem = $(elem);
			if ($.trim($elem.val()) === "") {
				return "continue";
			}

			var notRepeat = newHouseCodeNotRepeat($elem.val(), cellId);
			if (!notRepeat) { // 如果编号重复, 则提示用户, 不让表单提交
				allowSubmit = false;
				$elem.nextAll("label.exist").show();
				$elem.focus();
			} else {
				$elem.nextAll("label.exist").hide();
			}
		});
		if (!allowSubmit) {
			event.stopImmediatePropagation();
		}
		return allowSubmit;
	});

	function newHouseCodeNotRepeat(houseCode, cellId) {
		var notRepeat = false;
		$.ajax({
			type: "POST",
			async: false,
			url: newHouseCodeNotRepeatAPI,
			data: {
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
