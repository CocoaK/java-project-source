$(function() {
	// 获取webapp的context, 例如得到/smarthomeweb
	// 1. 先获取pathname, 得到类似/smarthomeweb/login/index.action
	// 2. 查找出pathname中第二个"/"的位置, 即context名称结束的位置
	// 3. 截取pathname获得context
	var context = location.pathname.substring(0, location.pathname.indexOf("/", 1));
	var loadRegionListAPI = context + "/housemgr/json/getRegions";
	var regionId = $("#regionId").attr("regionId");
	$("#districtId").change(function() {
		loadRegionList($(this).val());
	});

	function loadRegionList(districtId) {
		$.post(loadRegionListAPI + "?districtId=" + districtId, function(data) {
			var regionSelect = $("#regionId");
			regionSelect.empty();
			$("<option value=''>" + selectAllTitle + "</option>").appendTo(regionSelect);

			$(data.regionVos).each(function(index, element) {
				createRegionEl(element).appendTo(regionSelect);
			});

			// 通知其它select数据已经更新
			regionSelect.change();
		});
	}

	function createRegionEl(region) {
		var html = "<option value='" + region.id + "'";
		if (region.id == regionId) {
			html += " selected='selected'>";
		} else {
			html += ">";
		}
		html += region.name + "</option>";

		return $(html);
	}
});