/*
 * 对Date的扩展，将指定格式fmt的dateStr转换为日期.
 * yyyy:年, MM:月, dd:日, hh:小时, mm:分, ss:秒, S:毫秒.
 * 参数:
 * fmt:yyyy-MM-dd, dateStr:2012-07-01;
 * fmt:yyyy-MM-dd hh:mm:ss.S, dateStr:2012-07-01 18:18:18.123;
 * fmt:yyyy/MM/dd, dateStr:2012/07/01;
 * fmt:yyyy/MM/dd hh:mm:ss.S, dateStr:2012/07/01 18:18:18.123等.
 */
function toDate(fmt, dateStr) {   
	var retDate = new Date();
	retDate.setFullYear(0);
	retDate.setMonth(0);
	retDate.setDate(1);
	retDate.setHours(0);
	retDate.setMinutes(0);
	retDate.setSeconds(0);
	retDate.setMilliseconds(0);
	
	if(/(yyyy)/.test(fmt))
		retDate.setFullYear(dateStr.substring(0, RegExp.$1.length));
	
	if(/(MM)/.test(fmt)){
		var beginIndex = fmt.indexOf(RegExp.$1);
		var mon = dateStr.substring(beginIndex, beginIndex + RegExp.$1.length);
		if(mon.indexOf("0") == 0)
			mon = mon.substring(1);
		retDate.setMonth(mon - 1);
	}
	
	if(/(dd)/.test(fmt)){
		var beginIndex = fmt.indexOf(RegExp.$1);
		retDate.setDate(dateStr.substring(beginIndex, beginIndex + RegExp.$1.length));
	}
	
	if(/(hh)/.test(fmt)){
		var beginIndex = fmt.indexOf(RegExp.$1);
		retDate.setHours(dateStr.substring(beginIndex, beginIndex + RegExp.$1.length));
	}
	
	if(/(mm)/.test(fmt)){
		var beginIndex = fmt.indexOf(RegExp.$1);
		retDate.setMinutes(dateStr.substring(beginIndex, beginIndex + RegExp.$1.length));
	}
	
	if(/(ss)/.test(fmt)){
		var beginIndex = fmt.indexOf(RegExp.$1);
		retDate.setSeconds(dateStr.substring(beginIndex, beginIndex + RegExp.$1.length));
	}
	
	if(/(S)/.test(fmt)){
		var beginIndex = fmt.indexOf(RegExp.$1);
		retDate.setMilliseconds(dateStr.substring(beginIndex, beginIndex + RegExp.$1.length));
	}
	
	return retDate;   
}

function hideTip(targetId){
	var hideObj = $('#'+targetId);
	if(hideObj != null || hideObj != undefined)
		hideObj.hide();
}


/**
 * 将含有"<"符号的内容中的"<"替换成"&lt;",以过滤<script>执行
 */
function transTitle(title){
	if(title != null){	
		return title.replace(/</g,"&lt;");
	}
}

/**
 * 将json返回的时间"2012-12-12T12:12:59"转换成"2012-12-12 12:12:59"
 */
function transTime(time){
	if (time != null){
		return time.replace("T"," ");
	}
}

function validateBirthDate(currEle, fmt, required, errorMsgId){
	var value = $(currEle).val();
	var errorMsgObj = $('#'+errorMsgId);
	if(!required && value == ""){
		errorMsgObj.hide();
		return true;
	}
	
	if(required && value == ""){
		errorMsgObj.text(errorRequired);
		errorMsgObj.show();
		return false;
	}
	
	var currDate = new Date();
	currDate.setHours(0);
	currDate.setMinutes(0);
	currDate.setSeconds(0);
	currDate.setMilliseconds(0);
	if(toDate(fmt, value) > currDate){
		errorMsgObj.text(errorBirthDate);
		errorMsgObj.show();
		return false;
	}
		
	errorMsgObj.hide();
	return true;
}

function validateUserAcc(value){
	if(value == "")
		return true;
	var un = /^[a-zA-Z]+\w{3,19}$/;
	return un.test(value);
}

function validatePassword(value){
	if(value == "")
		return true;
	var mp = /^\w{6,20}$/;
	return mp.test(value);
}

function generatePagingInfo(pagingInfoId, funcName, actionUrl, 
		pageNum, totalPages, firstPageText, previousPageText, nextPageText, lastPageText){
	var pageInfoObj = $('#' + pagingInfoId);
	pageInfoObj.html("");
	if(pageNum > 1){
		pageInfoObj.append("<a href='javascript:" + funcName + "(\"" + actionUrl + "\",1)'>" + firstPageText + "</a>");
		pageInfoObj.append("<a href='javascript:" + funcName + "(\"" + actionUrl + "\"," + (pageNum - 1) + ")'>" + previousPageText + "</a>");
	}else{
		pageInfoObj.append("<i>" + firstPageText + "</i><i>" + previousPageText + "</i>");
	}
	
	var startPage = 1;
	var endPage = 5;
	if(pageNum > 3){
		startPage = pageNum - 2;
		endPage = startPage + 4;
	}
	
	if(endPage > totalPages){
		endPage = totalPages;
		if(endPage > 5){
			startPage = endPage - 4;
		}else{
			startPage = 1;
		}
	}
	
	for(var i = startPage; i <= endPage; i++){
		if(pageNum == i){
			pageInfoObj.append("<i>" + i + "</i>");
		}else{
			pageInfoObj.append("<a href='javascript:" + funcName + "(\"" + actionUrl + "\"," + i + ")'>" + i + "</a>");
		}
	}
	
	if(pageNum < totalPages){
		pageInfoObj.append("<a href='javascript:" + funcName + "(\"" + actionUrl + "\"," + (pageNum + 1) + ")'>" + nextPageText + "</a>");
		pageInfoObj.append("<a href='javascript:" + funcName + "(\"" + actionUrl + "\"," + totalPages + ")'>" + lastPageText + "</a>");
	}else{
		pageInfoObj.append("<i>" + nextPageText + "</i><i>" + lastPageText + "</i>");
	}
}