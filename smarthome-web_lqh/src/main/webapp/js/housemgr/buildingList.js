$(function() {
	// 获取webapp的context, 例如得到/smarthomeweb
	// 1. 先获取pathname, 得到类似/smarthomeweb/login/index.action
	// 2. 查找出pathname中第二个"/"的位置, 即context名称结束的位置
	// 3. 截取pathname获得context
	var context = location.pathname.substring(0, location.pathname.indexOf("/", 1));
	var loadBuildingListAPI = context + "/housemgr/json/getBuildings";
	var buildingId = $("#buildingId").attr("buildingId");
	$("#regionId").change(function() {
		loadBuildingList($(this).val());
	});

	function loadBuildingList(regionId) {
		$.post(loadBuildingListAPI + "?regionId=" + regionId, function(data) {
			var buildingSelect = $("#buildingId");
			buildingSelect.empty();
			$("<option value=''>" + selectAllTitle + "</option>").appendTo(buildingSelect);

			$(data.buildingVos).each(function(index, element) {
				createBuildingEl(element).appendTo(buildingSelect);
			});

			// 通知其它select数据已经更新
			buildingSelect.change();
		});
	}

	function createBuildingEl(building) {
		var html = "<option value='" + building.id + "'";
		if (building.id == buildingId) {
			html += " selected='selected'>";
		} else {
			html += ">";
		}
		html += building.name + "</option>";

		return $(html);
	}
});