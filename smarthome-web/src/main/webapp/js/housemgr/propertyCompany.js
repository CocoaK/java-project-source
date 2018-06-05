$(function() {
	var loadPropertyCompanyAPI = "../housemgr/json/queryPropertyCompanyList",
		propertyCompanyList = $("#propertyCompanyList"),
		propertyCompanyIdInput = $("input:hidden[name='propertyCompanyInfo.id']"),
		propertyCompanyNameInput = $("input:text[name='propertyCompanyInfo.name']"),
		propertyCompanyProfileInput = $("textarea[name='propertyCompanyInfo.profile']"),
		propertyCompanyContactInput = $("input:text[name='propertyCompanyInfo.contact']"),
		propertyCompanyChargeInput = $("input:text[name='propertyCompanyInfo.charge']"),
		companies = [];

	propertyCompanyList.change(function() {
		resetPropertyCompanyInput();

		var selectedPropertyCompany = $.grep(companies, function(pc) {
			return pc.id == propertyCompanyList.val();
		});

		if (selectedPropertyCompany.length > 0) {
			propertyCompanyNameInput.val(propertyCompanyList.find("option:selected").text());
			propertyCompanyIdInput.val(selectedPropertyCompany[0].id);
			propertyCompanyProfileInput.val(selectedPropertyCompany[0].profile);
			propertyCompanyContactInput.val(selectedPropertyCompany[0].contact);
			propertyCompanyChargeInput.val(selectedPropertyCompany[0].charge);
		}
	});

	function loadPropertyCompanyList() {
		$.post(loadPropertyCompanyAPI, function(data) {
			companies = data.results;
			propertyCompanyList.empty();
			$("<option value=''></option>").appendTo(propertyCompanyList);

			$(companies).each(function(index, element) {
				createPropertyCompanyEl(element).appendTo(propertyCompanyList);
			});

			// 获取所有物业公司列表后, 默认选中归属的物业公司
			propertyCompanyList.val(propertyCompanyIdInput.val());
		});
	}

	function createPropertyCompanyEl(propertyCompany) {
		return $("<option value='" + propertyCompany.id + "'>" + propertyCompany.name + "</option>");
	}

	function resetPropertyCompanyInput() {
		propertyCompanyNameInput.val("");
		propertyCompanyIdInput.val("");
		propertyCompanyProfileInput.val("");
		propertyCompanyContactInput.val("");
		propertyCompanyChargeInput.val("");
	}

	loadPropertyCompanyList();
});