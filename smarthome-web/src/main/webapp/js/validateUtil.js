function confirmAction(targetUrl,targetId,confirmMsg){
	var targetFunc = "javascript:hideOrDisplay('" + targetUrl + "','" + targetId + "');";
	$('#ConfirmButtonId').attr("href",targetFunc);
	$('#showMsg').text(confirmMsg);
	$('#cancelButonId').show();
	$('#tipMsgDiv').show();
}
function hideOrDisplay(targetUrl,targetId){
	$($("form")[0]).attr("action",targetUrl);
	$('#tipMsgDiv').append("<input type='hidden' name='requestId' value='" + targetId + "'/>");
	$("form")[0].submit();
}
function updateStatus(targetUrl,targetId,statusName,statusValue){
	$($("form")[0]).attr("action",targetUrl);
	$('#tipMsgDiv').append("<input type='hidden' name='requestId' value='" + targetId + "'/>");
	$('#tipMsgDiv').append("<input type='hidden' name='"+statusName+"' value='" + statusValue + "'/>");
	$("form")[0].submit();
}
function publish(targetUrl,targetId){
	$($("form")[0]).attr("action",targetUrl);
	$('#tipMsgDiv').append("<input type='hidden' name='requestId' value='" + targetId + "'/>");
	$("form")[0].submit();
}
function optionSelectInt(backValue, targetId,maxOptionsSum) {
	try {
		var targetSelected=document.getElementById(targetId);
		  if(backValue!=''){
			  for(var i=1;i<maxOptionsSum;i++){
				  try {
					  if(targetSelected.options[i].value==backValue) targetSelected.options[i].selected=true;
				} catch (e) {
				}
			  }
		  }
	} catch (e) {}
   }