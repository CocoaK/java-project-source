function saveMenu() {	
	if($($("form")[0]).valid()){
		$("form")[0].submit();
	}
}

function confirmHideOrDisplay(targetUrl,menuCode,targetStatus,confirmMsg){
	var targetFunc = "javascript:hideOrDisplay('" + targetUrl + "','" + menuCode + "','" + targetStatus + "');";
	$('#targetUrl').attr("href",targetFunc);
	$('#confirmMsg').text(confirmMsg);
	$('#confirmFrame').show();
}

function hideOrDisplay(targetUrl,menuCode,targetStatus){
	$($("form")[0]).attr("action",targetUrl);
	$('#confirmFrame').append(
			"<input type='hidden' name='menuCode' value='" + menuCode + "'/><input type='hidden' name='status' value='" + targetStatus + "'/>");
	$("form")[0].submit();
}