function queryHouses(targetUrl, pageNum) {
	var params = {
		"page.pageNum" : pageNum,
		"regionName" : $('#areaName').attr('value'),
		"buildingName" : $('#buildingName').attr('value'),
		"cellName" : $('#unitName').attr('value'),
		"houseName" : $('#houseName').attr('value')
	};
	$.post(targetUrl, params, queryHousesCallback, 'json');
}

function queryHousesCallback(data) {
	$('#houseResult').html("");
	var pageNum = data.pageNum;
	var totalPages = data.totalPages;
	if(data.foundHouses.length > 0){
		var str="";
		$.each(data.foundHouses, function(index, item) {
			var ownerId="";var ownerName="";
			if(item.owner!=null){
				ownerId=item.owner.userId;
				ownerName=item.owner.userName;
			}
			str+="<tr><td>"+item.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name+"</td><td>"+item.THmBuildingCellInfo.THmRegionBuildingInfo.name+"</td><td>"+item.THmBuildingCellInfo.name+"</td><td>"+item.name+"</td>";
			str+="<td><a href='javascript:linkFunction(\""+item.id+"A"+ownerId+"\",\""+item.id+"\",\""+item.name+"\",\""+item.THmBuildingCellInfo.id+"\",\""+item.THmBuildingCellInfo.name+"\",\""+item.THmBuildingCellInfo.THmRegionBuildingInfo.id+"\",\""+item.THmBuildingCellInfo.THmRegionBuildingInfo.name+"\",\""+item.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name+"\",\""+ownerName+"\")'>"+selectText+"</a></td></tr>";
		});
		$("#houseResult").append(str);
	}else{
		$('#houseResult').append("<tr><td colspan='5' align='center'>" + noDataMsg + "</td></tr>");
	}
	
	generatePagingInfo("pageResult", "queryHouses", actionUrl, pageNum, 
			totalPages, firstPageText, previousPageText, nextPageText, lastPageText);
}