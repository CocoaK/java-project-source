$(function() {
	var loadPropertyCompanyAPI = "../housemgr/json/queryPropertyCompanyList";

	$("#propertyCompanyActor").click(function() {
		loadPropertyCompanyList();
	});
	$("#searchPropertyCompanyActor").click(function() {
		loadPropertyCompanyList();
	});
	$("#selectPropertyCompanyAct").click(function() {
		var checkedRadio = $("#propertyCompanyList input:radio[name=propertyCompanyId]:checked");
		var propertyCompanyId = checkedRadio.val();
		var propertyCompanyName = checkedRadio.parent().text();
		setPropertyCompany(propertyCompanyId, propertyCompanyName);

		$("#list_click").slideUp();
	});

	function loadPropertyCompanyList() {
		$.post(loadPropertyCompanyAPI + "?propertyCompanyName=" + $("#propertyCompanyName").val(), function(data) {
			$("#propertyCompanyList").empty();

			$(data.results).each(function(index, element) {
				createPropertyCompanyEl(element).appendTo("#propertyCompanyList");
			});
		});
	}

	function createPropertyCompanyEl(propertyCompany) {
		return $("<li><label><input type='radio' name='propertyCompanyId' value='"
				+ propertyCompany.id + "' />" + propertyCompany.name + "</label></li>");
	}

	function setPropertyCompany(id, name) {
		$("#housingDistrictInfoPropertyCompanyInfoId").val(id);
		$("#housingDistrictInfoPropertyCompanyInfoName").text(name);
	}
	
	$("form").submit(function(event) {
		var validateFlag = 0;
		if(!$($("form")[0]).valid())
			validateFlag++;
		
		if(!validateFloorPlanFileName())
			validateFlag++;
		
		if(validateFlag > 0){
			event.stopImmediatePropagation();
			return false;
		}
		
		return true;
	});
});

function selectFloorPlanFileName(floorPlanFileName){
	$('#floorPlanFileName').val(floorPlanFileName);
	validateFloorPlanFileName();
}

function validateFloorPlanFileName(){
	return validateFileName($('#floorPlanFileName').val(), $('#picErrorMsg'), validFileExts, ignoreEmpty);
}