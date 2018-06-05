var checkedDevice;
var count = 0;
var sceneStatus = 1;
// 场景JSON数据
var sceneData;

/**
 * 获取树中选中的设备
 * @see http://blog.csdn.net/java_cxrs/article/details/5973398
 */
function getCheckedDevice(dxtree) {
    // 筛选出选中的设备, 判断依据是设备节点会有自定义数据, 位置节点则没有
	if(dxtree.getAllChecked()!="0.1")
    checkedDevice = $.map(dxtree.getAllChecked().split(","), function(itemId) {
    	
    	var sceneId = dxtree.getUserData(itemId, "sceneId");
    	var deviceId = dxtree.getUserData(itemId, "deviceId");
    	var deviceName = dxtree.getUserData(itemId, "deviceName");
    	var status = dxtree.getUserData(itemId, "status");
    	var statusText = dxtree.getUserData(itemId, "statusText");
    	//var positionName = dxtree.getUserData(itemId, "positionName");

    	if (deviceId != null) {
    		return {
    			sceneId: sceneId,
    			deviceId: deviceId,
    			deviceName: deviceName,
    			status: status,
    			statusText: statusText,
    			//positionName: positionName
    		};
    	}
    });
    $("#applianceTb").empty();
    if (checkedDevice!=null && checkedDevice.length > 0) {
    	$("#submitControlConfirm").removeAttr("disabled");
        showDevice(checkedDevice);
    }
    
}

function showDevice(checkedDevice) {
	var $container = $("#applianceTb");
	$.each(checkedDevice, function(i, device) {
		$("<tr><td>" + device.deviceName + "</td>"
				+ "<td><a href='#'>" + device.statusText + "</a></td>"
				+ "</tr>").appendTo($container);
	});
}

function init() {
	// 房号对应的设备编码
	var deviceCode = $("input:hidden[name=deviceCode]").val();
	// 国际化字符串 设备状态(开)
	var statusOnText = $("#deviceStatusOn").val();
	// 国际化字符串 设备状态(关)
	var statusOffText = $("#deviceStatusOff").val();

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
    		$.post("../monitor/json/sendSceneMonitorCommand", {
    			sceneDeviceIds: deviceIds.join(","),
    			sceneDeviceStatus: statusList.join(","),
    			deviceCode: deviceCode,
    			cache: false,
    			sceneId: 0
    		} , function(data) {
    			if (data.successFlag) {
    				
    				$("#commandSucceed").show();
    				updateTreeData();
    			} else {
    				$("#commandFail").show();
    			}
    		});
    	}
    });

    function updateTreeData() {
		$.each(checkedDevice, function(i, device) {
			var deviceItemId = device.sceneId + "-" + device.deviceId;
			tree.setUserData(deviceItemId, "status", device.status);
			tree.setUserData(deviceItemId, "statusText", getDeviceStatusText(device.status));
		});
    }
    //loadScene();
    loadSceneDevice();
}


/**
 * 刷新设备树中的数据
 */
function refreshTree(deviceList) {
	tree.deleteChildItems(0.1);

	$.each(deviceList, function(index, device) {
		// 设备位置, 例如: 客厅, 厨房, 卧室, 设备在树形结构中按照位置分类
		//var positionName = device.positionName;
		// 设备名称
		var deviceName = device.deviceName;
		// 设备ID(组合场景ID和设备ID)作为树节点的itemId(child)
		var deviceId = device.sceneId + "-" + device.deviceId;
		tree.insertNewItem(0.1, deviceId, deviceName);
		tree.setUserData(deviceId, "sceneId", device.sceneId);
		tree.setUserData(deviceId, "deviceId", device.deviceId);
		tree.setUserData(deviceId, "deviceName", device.deviceName);
		tree.setUserData(deviceId, "status", device.status);
		tree.setUserData(deviceId, "statusText", getDeviceStatusText(device.status));
	});

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
    $.post("../monitor/json/getSceneByDeviceNo", {deviceCode: deviceCode,sceneKind: 1,cache: false}, function(data) {
    	
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
    });
}

function initScene() {
	var $sceneList = $("#sceneList");
	//$("<option value='0'></option>").appendTo($sceneList);
	$.each(sceneData, function(index, element) {
		$("<option value=" + element.sceneId + ">" + element.sceneName + "</option>").appendTo($sceneList);
	});

	// 筛选出使用中的场景
	var useScene = $.grep(sceneData, function(element) {
		return element.isUse == 1;
	});
	if (useScene.length > 0) {
		$("#useScene").text(useScene[0].sceneName);
		//sceneId = useScene[0].sceneId;//当前使用场景的场景编号
		$("#sceneList").val(useScene[0].sceneId);
	}
}

/**
 * 加载场景设备数据
 */
function loadSceneDevice() {
	var deviceCode = $("input:hidden[name=deviceCode]").val();
    $.post("../monitor/json/querySceneDeviceByDeviceNo", {deviceCode: deviceCode,sceneId: 0,cache: false}, function(data) {
    	// data.sceneDeviceList[i].deviceId, data.sceneDeviceList[i].deviceName
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
    		refreshTree(data.sceneDeviceList);
    	}
    });
}

//提交控制命令
function doSceneControl() {
	var deviceCode = $("input:hidden[name=deviceCode]").val();
	if ($("#sceneList").val()!=null) {
		$.post("../monitor/json/sendSceneCommand", {
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