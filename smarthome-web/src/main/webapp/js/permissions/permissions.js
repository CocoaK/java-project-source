$(function() {
	$("form").submit(function(event) {
		var validateFlag = 0;
		if(!$($("form")[0]).valid())
			validateFlag++;
		
		if(!validateMenus())
			validateFlag++;
		
		if(validateFlag == 0){
			if(!existRoleName(existRoleNameUrl))
				return true;
		}
		event.stopImmediatePropagation();
		return false;
	});
});

function selectAllMenus(selectAllAction, selectNoneAction){
	var selectAllMenus = $('#selectAllMenus');
	var selectFlag = selectAllMenus.attr('selectFlag');
	if(selectFlag == 'true'){
		selectAllMenus.attr('selectFlag', 'false');
		selectAllMenus.text(selectNoneAction);
	}else{
		selectAllMenus.attr('selectFlag', 'true');
		selectAllMenus.text(selectAllAction);
	}
	var allMenus = $("input[name='menuCodes']");
	$.each(allMenus, function(index, item) {
		if(selectFlag == 'true'){
			$(item).attr('checked', 'checked');
		}else{
			$(item).removeAttr('checked');
		}		
	});	
}

function validateMenus(){
	var checkedMenus = $("input[name='menuCodes']:checked");
	var isChecked = checkedMenus.size() > 0;
	if(isChecked)
		$("#unselectedMenuCodeMsg").hide();
	else
		$("#unselectedMenuCodeMsg").show();
	return isChecked;
} 

function existRoleName(targetUrl){	
	var currRoleCode = $('#roleCode').attr('value');
	if(currRoleCode == undefined)
		currRoleCode = "";
	
	var existRN = true;
	$.ajax({
		type:"POST",
		async:false,
		url:targetUrl,
		data:"currRoleCode=" + currRoleCode + "&roleName=" + $('#roleName').attr('value'),
		dataType:"json",
		success:function(data){
			if(data.existRoleName == true){
				$('#existRoleNameMsg').show();
			}else{
				$('#existRoleNameMsg').hide();
				existRN = false;
			}
		}
	});
	return existRN;
}