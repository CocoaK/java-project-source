var count = 0;
$(function() {
	// 场景JSON数据
	$.ajaxSetup({cache:false});
	var sceneData = [];
	// 房号对应的设备编码
	var deviceCode = $("input:hidden[name=deviceCode]").val();
	// 显示选中的场景名称的element
	var $useScene = $("#useScene");
	var $useSceneId = $("input:hidden[name=useSceneId]");
	// 场景select
	var $sceneList = $("#sceneList");
	// 布防/撤防radio
	//var $deploymentModel = $("input:radio[name=deploymentModel]");

//	$deploymentModel.change(function() {
//		if ($(this).val() == 0) { // 撤防隐藏 场景select, 对用户使用中的场景进行撤防
//			$sceneList.parents("tr").hide();
//		} else {
//			$sceneList.parents("tr").show();
//		}
//	});

	// 提交控制命令
    $("#submitControlConfirm").click(function() {
    	//var deploymentModel = $("input:radio[name=deploymentModel]:checked").val();
    	var deploymentModel = 1;
    	var sceneId = "";
		if (deploymentModel == 0) { // 撤防时针对用户使用中的sceneId
			sceneId = $useSceneId.val();
		} else { // 布防时从场景select中获取要布防的sceneId
			sceneId = $sceneList.val();
		}
		$.post("../monitor/json/sendProtectionAndRemovalMonitorCommand", {
			deviceCode: deviceCode,
			sceneId: sceneId,
			cache: false,
			actionWay: deploymentModel
		} , function(data) {
			if (data.successFlag) {
				$("#commandSucceed").show();
				updateUseSence();
			} else {
				$("#commandFail").show();
			}
		});
    });

    /**
     * 更新使用中的场景
     */
    function updateUseSence() {
		$useScene.text($sceneList.find("option:selected").text());
		$useSceneId.val($sceneList.val());
    }
    
    /**
     * 加载场景数据
     */
    function loadScene() {
    	//sceneKind为场景类型，0为安防，1为家电控制
        $.post("../monitor/json/getSceneByDeviceNo", {deviceCode: deviceCode,sceneKind: 0,cache: false}, function(data) {
        	if (data.sceneList.length == 0) { // 如果还没有推来场景数据, 延时后重新加载
        		//显示等待，24秒后如果没有数据则停止加载
        		$("#waitData").show();
        		if(count<8){
        			setTimeout(loadScene, 3000);
        			++count;
        		}else{
        			$("#noSceneDeviceData").show();
        			$("#waitData").hide();
        		}
        	} else {
        		$("#waitData").hide();
        		sceneData = data.sceneList;
        		$("#sceneControlConfirm").removeAttr("disabled");
        		initScene();
        	}
//        	
//        	if (data.sceneList.length == 0) { // 如果还没有推来场景数据, 延时后重新加载
//        		setTimeout(loadScene, 3000);
//        	} else {
//        		sceneData = data.sceneList;
//        		initScene();
//        	}
        });
    }

    function initScene() {
		$.each(sceneData, function(index, element) {
			$("<option value=" + element.sceneId + ">" + element.sceneName + "</option>").appendTo($sceneList);
		});

		// 筛选出使用中的场景
		var useScene = $.grep(sceneData, function(element) {
			if(element.isUse == 1)
				return element;
		});
		if (useScene.length > 0) {
			$useScene.text(useScene[0].sceneName);
			$sceneList.val(useScene[0].sceneId);
			$useSceneId.val(useScene[0].sceneId);
		}
    }

    loadScene();
});