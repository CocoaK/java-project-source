jQuery.validator.addMethod("useraccount",
	function(value, element) {
		if(value == "")
			return true;
		var un = /^[a-zA-Z]+\w{3,19}$/;
		return un.test(value);
	});

jQuery.validator.addMethod("phone",
	function(value, element) {
		if(value == "")
			return true;
		var p = /^\d{1,20}$/;
		return p.test(value);
	});

jQuery.validator.addMethod("idcard",
	function(value, element) {
		if(value == "")
			return true;
		var p = /^[a-zA-Z0-9]{1,30}$/;
		return p.test(value);
	});

jQuery.validator.addMethod("mobilephone",
	function(value, element) {
		if(value == "")
			return true;
		var mp = /^\d{1,20}$/;
		return mp.test(value);
	});

jQuery.validator.addMethod("pass",
	function(value, element) {
		if(value == "")
			return true;
		var mp = /^\w{6,20}$/;
		return mp.test(value);
	});

jQuery.validator.addMethod("idcard",
	function(value, element) {
		if(value == "")
			return true;
		var p = /^[a-zA-Z0-9]{1,30}$/;
		return p.test(value);
	});

jQuery.validator.addMethod("birthDate",
		function(value, element) {
			if(value == "")
				return true;
			var fmt = $(element).attr("dateFormat");
			var currDate = new Date();
			currDate.setHours(0);
			currDate.setMinutes(0);
			currDate.setSeconds(0);
			currDate.setMilliseconds(0);
			if(toDate(fmt, value) > currDate)
				return false;
			return true;
		});

/*
 * 验证当前值是否是一个有效数字。
 * params包含三个参数的数组：
 * 第一个标识支持正负数（必须是整数），-1表示只支持负数或0，小于-1表示只支持负数，0表示支持正负数和0，1表示只支持正数或0，大于1表示只支持正数；
 * 第二个参数标识数字的最大总长度（必须是正整数）；
 * 第三个参数标识小数位的最大长度（必须是正整数或0）。
 */
jQuery.validator.addMethod("num",
		function(value, element, params) {	
			if(value == "" || params == undefined)
				return true;
			if(params.length != 3)
				return true;
			
			var intNum = params[1] - params[2];
			if(intNum < 0)
				return true;
			
			//验证是否数字的正则表达式
			var numExp = /^-?(0|([1-9]\d*))(\.\d+)?$/;
			if(!numExp.test(value))
				return false;
			
			var numSign = "";
			if((value.indexOf("-") == 0)){
				numSign = value.substr(0,1);
				value = value.substr(1);
			}
			
			if(((params[0] >= 1) && (numSign == "-")) 
					|| ((params[0] < -1) && (numSign != "-")))
				return false;
			
			var decValue = "0";//小数位的值
			var numArr = value.split(".");
			if(numArr.length  > 1){
				decValue = numArr[1];
				if(numArr[1].length > params[2])
					return false;
			}
							
			if(numArr[0].length > intNum)
				return false;
			
			if((params[0] != 0) && (params[0] != -1) && (params[0] != 1) 
					&& (numArr[0] == "0") && (/^0+$/.test(decValue)))
				return false;
			
			return true;
		});

jQuery.validator.addMethod("repeatValidate", 
		function(value, element, param) {
			if ( this.optional(element) )
				return "dependency-mismatch";
		
			var previous = this.previousValue(element);
			if (!this.settings.messages[element.name] )
				this.settings.messages[element.name] = {};
			previous.originalMessage = this.settings.messages[element.name].remote;
			this.settings.messages[element.name].remote = previous.message;
		
			param = typeof param == "string" && {url:param} || param;
		
			if ( this.pending[element.name] ) {
				return "pending";
			}
			if ( previous.old === value ) {
				return previous.valid;
			}
		
			previous.old = value;
			var validator = this;
			this.startRequest(element);
			var data = {};
			data[element.name] = value;
			$.ajax($.extend(true, {
				url: param,
				mode: "abort",
				port: "validate" + element.name,
				dataType: "json",
				type: "POST",
				data: data,
				success: function(response) {
					validator.settings.messages[element.name].remote = previous.originalMessage;
					var valid = response === true;
					if ( valid ) {
						var submitted = validator.formSubmitted;
						validator.prepareElement(element);
						validator.formSubmitted = submitted;
						validator.successList.push(element);
						validator.showErrors();
					} else {
						var errors = {};
						var message = response || validator.defaultMessage( element, "repeatValidate" );
						errors[element.name] = previous.message = $.isFunction(message) ? message(value) : message;
						validator.showErrors(errors);
					}
					previous.valid = valid;
					validator.stopRequest(element, valid);
				}
			}, param));
			return "pending";
		});

jQuery.validator.addMethod("num12",
		function(value, element) {
			if(value == "")
				return true;
			var mp = /^[0-9]{1,2}$/;
			return mp.test(value);
		});

jQuery.validator.addMethod("num34",
		function(value, element) {
			if(value == "")
				return true;
			var mp = /^[0-9]{3,4}$/;
			return mp.test(value);
		});

jQuery.validator.addMethod("num1_3",
		function(value, element) {
			if(value == "")
				return true;
			var mp = /^[1-3]{1}$/;
			return mp.test(value);
		});
jQuery.validator.addMethod("num1_2",
		function(value, element) {
			if(value == "")
				return true;
			var mp = /^[1-2]{1}$/;
			return mp.test(value);
		});