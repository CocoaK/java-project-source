$(function() {
	// 获取webapp的context, 例如得到/smarthomeweb
	// 1. 先获取pathname, 得到类似/smarthomeweb/login/index.action
	// 2. 查找出pathname中第二个"/"的位置, 即context名称结束的位置
	// 3. 截取pathname获得context
	var context = location.pathname.substring(0, location.pathname.indexOf("/", 1));
	var loadDistrictListAPI = context + "/housemgr/json/getDistricts";
	var districtId = $.parseQuerystring().districtId;
	function loadDistrictList() {
		$.post(loadDistrictListAPI, function(data) {
			var districtSelect = $("#districtId");
			districtSelect.empty();
			// $("<option value=''>-</option>").appendTo(districtSelect);

			$(data.districtVos).each(function(index, element) {
				createDistrictEl(element).appendTo(districtSelect);
			});

			// 通知其它select数据已经更新
			districtSelect.change();
		});
	}

	function createDistrictEl(district) {
		var html = "<option value='" + district.id + "'";
		if (district.id == districtId) {
			html += " selected='selected'>";
		} else {
			html += ">";
		}
		html += district.name + "</option>";

		return $(html);
	}

	loadDistrictList();
});