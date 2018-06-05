$(function() {
	var housingPurpose = $.parseQuerystring().housingPurpose;
	var housingStatus = $.parseQuerystring().housingStatus;
	var keyStatus = $.parseQuerystring().keyStatus;

	$("#housingPurpose").val(housingPurpose);
	$("#housingStatus").val(housingStatus);
	$("#keyStatus").val(keyStatus);
});