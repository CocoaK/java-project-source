/* 表单元素验证脚本（需要引入jquery.js）

	chkExists(elementId); 根据ID检测表单上某个元素是否存在
	getAttrExits(elementId,elementVal); 根据ID，获取元素上的指定属性值
	chkNull(elementId);根据ID检测该元素的value值是否为空
	chkValNull(elementVal);判断指定值是否为空
	isIntNum(elementVal);判断指定值是否为正整数
	isNum(elementVal);判断指定值是否为数字
	chkMaxLen(elementId,maxLen);根据ID判断该元素的value值长度最长不能超过所给定的 maxLen 长度
	chkMinLen(elementId,minLen);根据ID判断该元素的value值长度最短不能少于所给定的 maiLen 长度
	isEquals(elementId1,elementId2);判断所给定的两个ID元素的value值是否相等
	isEmail(elementVal);判断所给定的值是否为一个合法的email
	chkPhone(elementVal);判断所给定的值是否为一个合法的电话号码
	chkMobile(elementVal);判断所给定的值是否为一个合法的手机号码
	chkChar();限制文本框或文本域只能输入数字
	isIP(elementVal);判断所给定的值是否为一个合法的IP
	isURL(elementVal);判断所给定的值是否为一个合法的URL
*/

/*
	检测指定表单元素是否存在
	fieldId:指定元素ID
	returnVal:存在返回 true, 不存在返回 false
*/
function chkExists(fieldId){
	//如果传的是一个对象过来，直接返回 true
	if(typeof(fieldId) == "object"){
		return true;	
	}else{
		var field = $("#"+fieldId);
		if(typeof(field.val()) == "undefined"){
			alert("ID 为 "+fieldId+" 的元素没有找到！");
			return false;
		}else{
			return true;
		}
	}
}

/*
	获取指定元素的某个属性值
	fieldId:指定元素ID
	attrName:属性值
	returnVal:如存在且不为空即返回该值，否则返回空字符
*/
function getAttrExits(fieldId,attrName){
	var attrVal = null;
	if(typeof(fieldId) == "object"){
		attrVal = $(fieldId).attr(attrName);
	}else{
		attrVal = $("#"+fieldId).attr(attrName);
	}
	if(typeof(attrVal) != "undefined" && attrVal != null && attrVal.replace(/(^\s*)|(\s*$)/g, "").length > 0){
		return attrVal;
	}
	return "";
}

/*
	检测指定表单元素是否为空
	fieldId：指定元素的ID
	returnVal:为空返回 false,不为空返回 true
*/
function chkNull(fieldId){
	if(chkExists(fieldId)){
		var field = fieldId;
		if(typeof(fieldId) != "object"){
			field = $("#"+fieldId);		
		}
		//检测该元素的值是否为空
		if(field.val()!=null && field.val().replace(/(^\s*)|(\s*$)/g, "").length >0){
			return true;			
		}else{
			return false;
		}
	}else{
		return false;		
	}
}

/*
	检测所传的值是否为空
	fieldVal:需检测的值
	returnVal:为空返回 false,不为空返回 true
*/
function chkValNull(fieldVal){
	 if(fieldVal == null || fieldVal.replace(/(^\s*)|(\s*$)/g, "").length == 0){
		 return false;
	 }else{
		return true;
	 }
}

/*
	检测所传的值是否为正整数
	fieldVal:需要校验的值
*/
function isIntNum(fieldVal){
	var reg = /^\+?[1-9][0-9]*$/;//正整数   
	return reg.test(fieldVal);	
}

/*
	检测所传的值是否为数字
	fieldVal:需要校验的值
*/
function isNum(fieldVal){
	var reg = /^[0-9]+.?[0-9]*$/;
	return reg.test(fieldVal);	
}

/*
	输入文本最大长度限制
	maxLen:最大长度，默认值为200
*/
function chkMaxLen(fieldId,maxLen)
{
	//检测所传过来的最大长度是否为正整数，如果不为则使用默认值200
	maxLen = !!maxLen ? (isIntNum(maxLen) ? maxLen : 200 ) : 200;
	//检测该元素是否存在
	if(chkExists(fieldId)){
		var field = fieldId;
		if(typeof(fieldId) != "object"){
			field = $("#"+fieldId);		
		}
		//检测该元素的值长度不能超过最大长度
		if(field.val().length > maxLen){
			field.focus();
			return false;
		}else{
			return true;	
		}
	}else{
		return false;
	}
}

/*
	输入文本最小长度限制
	minLen:最小长度，默认值为6
*/
function chkMinLen(fieldId,minLen){
	//检测所传过来的最小长度是否为正整数，如果不为则使用默认值6
	minLen = !!minLen ? (isIntNum(minLen) ? minLen : 6 ) : 6;
	//检测该元素是否存在
	if(chkExists(fieldId)){
		var field = fieldId;
		if(typeof(fieldId) != "object"){
			field = $("#"+fieldId);		
		}
		//检测该元素的值长度不能少于最小长度
		if(!chkValNull(field.val()) || field.val().length < minLen){
			field.focus();
			return false;
		}else{
			return true;	
		}
	}else{
		return false;
	}
}

/*
	验证两个文本框的值是否一致
	fieldId1:第一个值ID
	fieldId2:第二个值ID
	returnVal:相等返回true，否则返回false
*/
function isEquals(fieldId1,filedId2){
	var field1 = fieldId1;
	var field2 = filedId2;
	if(typeof(fieldId1) != "object"){
		field1 = $("#"+fieldId1);		
	}
	if(typeof(fieldId2) != "object"){
		field2 = $("#"+filedId2);		
	}
	if(field1.val() == field2.val()){
		return true;	
	}else{
		return false;	
	}
}

/*
	验证输入的邮箱是否正确
	fieldVal:检测的邮箱
*/
function isEmail(fieldVal){
	var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
	if (fieldVal.search(reg) != -1)
	{
		return true;
	}else{
		return false;
	}
}

/*
	验证输入的电话是否正确
	fieldVal: 电话号码
	returnVal: 正确返回 true ，错误返回 false
*/
function chkPhone(fieldVal) 
{ 
	if(chkValNull(fieldVal)){
		var reg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/; 
		if(reg.test(fieldVal)){
			return true;
		}else{
			return false;
		}
	}else{
		return false;	
	}
} 

/*
	验证手机号码是否正确
	fieldVal: 手机号码
	returnVal: 正确返回 true ，错误返回 false
*/
function chkMobile(fieldVal){
	var reg13 = /^13\d{9}$/; //13系列
	var reg15 = /^15[0-35-9]\d{8}$/; //150-159(154 除外)
	var reg18 = /^18[05-9]\d{8}$/; //180、185、186、187、188、189
	
	if(chkValNull(fieldVal)){
		if(reg13.test(fieldVal) || reg15.test(fieldVal) || reg18.test(fieldVal)){
			return true;
		}else{
			return false;	
		}
	}else{
		return false;	
	}
}

/*
	文本框屏蔽输入字符
	使用方法：文本框中加上 onkeypress="return checkChar()" 
*/
function chkChar() 
{ 
	var keycode = event.keyCode; 
	if(!(keycode>=48&&keycode<=57)) 
	{ 
		return false; 
	} 
} 

/*
	判断IP地址是否正确
*/
function isIP(fieldVal){
    var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
    if (fieldVal.match(reg) == null) {
        return false;
    }else {
        return true;
    }
}

/*
	判断URL地址是否正确
*/
function isURL(fieldVal){
    if (fieldVal.match(/(http[s]?|ftp):\/\/[^\/\.]+?\..+\w$/i) == null) {
        return false
    }else {
        return true;
    }
}


