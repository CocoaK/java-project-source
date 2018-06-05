function existRegionName(districtId, regionId, regionName) {
	var existRN = false;
	$.ajax({
		type : "POST",
		async : false,
		url : "../housemgr/json/existRegionName",
		data : {
			districtId : districtId,
			regionId : regionId,
			regionNames : regionName
		},
		dataType : "json",
		success : function(data) {
			existRN = data.existRegionName;
		}
	});
	return existRN;
}

function existBuildingName(regionId, buildingId, buildingName) {
	var existBN = false;
	$.ajax({
		type : "POST",
		async : false,
		url : "../housemgr/json/existBuildingName",
		data : {
			regionId : regionId,
			buildingId : buildingId,
			buildingNames : buildingName
		},
		dataType : "json",
		success : function(data) {
			existBN = data.existBuildingName;
		}
	});
	return existBN;
}

function existCellName(buildingId, cellId, cellName) {
	var existCN = false;
	$.ajax({
		type : "POST",
		async : false,
		url : "../housemgr/json/existCellName",
		data : {
			buildingId : buildingId,
			cellId : cellId,
			cellNames : cellName
		},
		dataType : "json",
		success : function(data) {
			existCN = data.existCellName;
		}
	});
	return existCN;
}

function hideExistMsg(ele){
	var existMsgs = $(ele).nextAll("label.exist");
	existMsgs.each(function(i, elem) {
	    if(i == 0){
	    	$(elem).hide();
	    	return false;
	    }
	});
}

function markUniqueEle(startName){
	var eles = $("input[name ^= '" + startName + "']");
	eles.each(function(i, elem) {
		var eleid = startName + "" + i;
		$(elem).attr('id', eleid);
		$(elem).attr('name', eleid);
	});
}

function markSameEle(startName){
	var eles = $("input[name ^= '" + startName + "']");
	eles.each(function(i, elem) {
		$(elem).attr('name', startName);
	});
}

function confirmRemove(targetUrl,entityIdName,entityId,confirmMsg){
	var targetFunc = "javascript:doRemove('" + targetUrl + "','" + entityIdName + "','" + entityId + "');";
	$('#removeUrl').attr("href",targetFunc);
	$('#removeMsg').text(confirmMsg);
	$('#removeFrame').show();
}

function doRemove(targetUrl,entityIdName,entityId){
	var formObj = $("form")[0];
	$(formObj).attr("action",targetUrl);
	$(formObj).append("<input type='hidden' name='" + entityIdName + "' value='" + entityId + "'/>");
	formObj.submit();
}