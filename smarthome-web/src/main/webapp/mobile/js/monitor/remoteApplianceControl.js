var checkedDevice;
var count = 0;
var sceneStatus = 1;

function init() {
	// 场景JSON数据
	var sceneData;
	// 房号对应的设备编码
	var deviceCode = $("input:hidden[name=deviceCode]").val();

	// 点击家电列表中的anchor时, 切换家电的开/关状态
	 $("#applianceTb a").live("click", function(event) {
	    	toggleDeviceStatus(this);
	    	event.preventDefault();
	    });

    // 提交控制命令
    $("#submitControlConfirm").click(function() {
    	var deviceIds = [];
    	var statusList = [];
    	if(checkedDevice!=null){
	    	$.each(checkedDevice, function(i, device) {
	    		deviceIds.push(device.deviceId);
	    		statusList.push(device.status);
	    	});
    	}

    	if (deviceIds.length > 0) {
    		$.post("../../monitor/json/sendSceneMonitorCommand", {
    			sceneDeviceIds: deviceIds.join(","),
    			sceneDeviceStatus: statusList.join(","),
    			deviceCode: deviceCode,
    			cache: false,
    			sceneId: 0
    		} , function(data) {
    			if (data.successFlag) {
    				$("#commandSucceed").show();
    			} else {
    				$("#commandFail").show();
    			}
    		});
    	}
    });
    //loadScene(); 
    loadSceneDevice();
}


/**
 * 刷新页面显示的设备数据
 */
function refreshView(deviceList) {
	checkedDevice = deviceList;
	var $applianceTb = $("#applianceTb");
		$applianceTb.html("");
	$.each(deviceList, function(index, device) {
		// 在页面显示场景模式下的家电状态
		$("<tr><td>" +
				device.deviceName+"</td>"+"<td><a href='#'>" + getDeviceStatusText(device.status) + "</a></td>"
				+ "</tr>").appendTo($applianceTb);
	});
	if(deviceList!=null && deviceList.length>0){
		$("#submitControlConfirm").removeAttr("disabled");
	}
}

function toggleDeviceStatus(anchor) {
	//国际化字符串 设备状态(开)
	var statusOnText = $("#deviceStatusOn").val();
	// 国际化字符串 设备状态(关)
	var statusOffText = $("#deviceStatusOff").val();
	var $anchor = $(anchor);
	var anchorIndex = $(anchor).parents("tbody").find("a").index($anchor);

	if ($anchor.text() == statusOnText) {
		checkedDevice[anchorIndex].status = 0;
		checkedDevice[anchorIndex].statusText = statusOffText;
		$anchor.text(statusOffText);
	} else {
		checkedDevice[anchorIndex].status = 1;
		checkedDevice[anchorIndex].statusText = statusOnText;
		$anchor.text(statusOnText);
	}
}

/**
 * 获取设备状态的国际化字符
 */
function getDeviceStatusText(status) {
	//国际化字符串 设备状态(开)
	var statusOnText = $("#deviceStatusOn").val();
	// 国际化字符串 设备状态(关)
	var statusOffText = $("#deviceStatusOff").val();
	var statusText = "";
	if (status == 0) {
		statusText = statusOffText;
	} else if (status == 1) {
		statusText = statusOnText;
	}
	return statusText;
}

/**
 * 加载场景数据
 */
function loadScene() {
	var deviceCode = $("input:hidden[name=deviceCode]").val();
    $.post("../../monitor/json/getSceneByDeviceNo", {deviceCode: deviceCode,sceneKind: 1,cache: false}, function(data) {
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
    		//$("#sceneControlConfirm").removeAttr("disabled");
    		initScene();
    		
    	}
    });
}

function initScene() {
	var $sceneList = $("#sceneList");
	$.each(sceneData, function(index, element) {
		$("<option value=" + element.sceneId + ">" + element.sceneName + "</option>").appendTo($sceneList);
	});

	// 筛选出使用中的场景
	var useScene = $.grep(sceneData, function(element) {
		return element.isUse == 1;
	});
	if (useScene.length > 0) {
		$("#useScene").text(useScene[0].sceneName);
		$("#sceneList").val(useScene[0].sceneId);
	}
}

/**
 * 加载场景设备数据
 */
function loadSceneDevice() {
	var deviceCode = $("input:hidden[name=deviceCode]").val();
    $.post("../../monitor/json/querySceneDeviceByDeviceNo", {deviceCode: deviceCode,sceneId: 0,cache: false}, function(data) {
    	if (data.sceneDeviceList.length == 0) { // 如果还没有推来场景数据, 延时后重新加载
    		//显示等待，24秒后如果没有数据则停止加载
    		$("#waitData").show();
    		if(count<8){
    			setTimeout(loadSceneDevice, 3000);
    			++count;
    		}else{
    			$("#noSceneDeviceData").show();
    			$("#waitData").hide();
    		}
    	} else {
    		$("#waitData").hide();
    		refreshView(data.sceneDeviceList);
    	}
    });
}

//提交控制命令
function doSceneControl() {
	var deviceCode = $("input:hidden[name=deviceCode]").val();
	if ($("#sceneList").val()!=null) {
		$.post("../../monitor/json/sendSceneCommand", {
			deviceCode: deviceCode,
			sceneId: $("#sceneList").val(),
			cache: false,
			actionWay: sceneStatus
		} , function(data) {
			if (data.successFlag) {
				$("#commandSucceed").show();
				updateUseSence();
			} else {
				$("#commandFail").show();
			}
		});
	}
};

/**
 * 更新使用中的场景
 */
function updateUseSence() {
	var $useScene = $("#useScene");
	var $useSceneId = $("input:hidden[name=useSceneId]");
	var $sceneList = $("#sceneList");
	$useScene.text($sceneList.find("option:selected").text());
	$useSceneId.val($sceneList.val());
}