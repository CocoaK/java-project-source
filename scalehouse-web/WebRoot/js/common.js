var service_url="http://192.156.1.230:8080/";
var web_url="http://192.156.1.230:8080/";
function loadCombobox(areaId,productId){
	$.ajax({
		url:service_url+'scalehouse/person/getCountry',
		type:'post',
		dataType:'json',
		data:{
		},
		cache:false,
		success:function(json){
			if(json.code==1){
				var str="";
				for(var i=0;i<json.data.length;i++){
					str=str+"<option value="+json.data[i].countryCode+">"+json.data[i].countryName+"</option>";
				}
				$("#"+areaId).html("<option value='' selected>全部地区</option>"+str);
			}else{
				alert("网络不好，请稍后重试");
			}
		},
		error:function(){
			alert("网络不好，请稍后重试");
		}
	});
	
   $.ajax({
		url:service_url+'scalehouse/device/getProduct',
		type:'post',
		dataType:'json',
		data:{
		},
		cache:false,
		success:function(json){
			if(json.code==1){
				var str="";
				for(var i=0;i<json.data.length;i++){
					str=str+"<option value="+json.data[i].deviceType+">"+json.data[i].deviceName+"</option>";
				}
				$("#"+productId).html("<option value='' selected>全部产品</option>"+str);
			}else{
				alert("网络不好，请稍后重试");
			}
		},
		error:function(){
			alert("网络不好，请稍后重试");
		}
	});
};

function pSelectAreaOrProduct(area,type,pageNumber,pageSize){
	$.ajax({
		url:service_url+'scalehouse/person/getAll',
		type:'post',
		dataType:'json',
		data:{
			'countryCode':area,
			'type':type,
			'pageNumber':pageNumber,
			'pageSize':pageSize
		},
		cache:false,
		success:function(json){
			$('#datagridPer').datagrid('loadData', json);
			$('#datagridPer').datagrid('hideColumn', 'id');
		},
		error:function(){
			//alert("error");
			$.messager.alert('信息提示','error!','info');
		}
	});
}

function dSelectAreaOrProduct(area,type,pageNumber,pageSize){
	$.ajax({
		url:service_url+'scalehouse/device/getAll',
		type:'post',
		dataType:'json',
		data:{
			'countryCode':area,
			'type':type,
			'pageNumber':pageNumber,
			'pageSize':pageSize
		},
		cache:false,
		success:function(json){
			$('#datagridDevice').datagrid('loadData', json);
			$('#datagridDevice').datagrid('hideColumn', 'id');
		},
		error:function(){
			//alert("error");
			$.messager.alert('信息提示','error!','info');
		}
	});
}

function fSelectAreaOrProduct(area,type,status,pageNumber,pageSize){
	$.ajax({
		url:service_url+'scalehouse/feedback/getList',
		type:'post',
		dataType:'json',
		data:{
			'countryCode':area,
			'type':type,
			'status':status,
			'pageNumber':pageNumber,
			'pageSize':pageSize
		},
		cache:false,
		success:function(json){
			$('#datagridFeedback').datagrid('loadData', json);
			$('#datagridFeedback').datagrid('hideColumn', 'id');
			//$('#datagridFeedback').datagrid('hideColumn', 'createTime');
		},
		error:function(){
			//alert("error");
			$.messager.alert('信息提示','error!','info');
		}
	});
}

function calcDate(date){
	var myDate = new Date(); //获取今天日期
	myDate.setDate(myDate.getDate() - date + 1);
	var dateArray = []; 
	var dateTemp; 
	var flag = 1; 
	for (var i = 0; i < date; i++) {
	    dateTemp =myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
	    dateArray.push(dateTemp);
	    myDate.setDate(myDate.getDate() + flag);
	}
	//console.log(dateArray);
	return dateArray;
}
function getChartsData(ex_url,num,id,countryCode,type){
	$.ajax({
		url:service_url+'scalehouse/'+ex_url,
		type:'post',
		dataType:'json',
		data:{
			"intervals":num,
			"countryCode":countryCode,
			"type":type
		},
		cache:false,
		success:function(json){
			if(json.code==1){
				
				var title = {
				   text: '数据分析'   
				};
				var subtitle = {
				   text: ''
				};
				var xAxis = {
				   categories: calcDate(num)
				};
				var yAxis = {
				   title: {
				      text: '单位(人)'
				   },
				   plotLines: [{
				      value: 0,
				      width: 1,
				      color: '#808080'
				   }]
				};   
				
				var tooltip = {
				   valueSuffix: '人'
				};
				
				var legend = {
				   layout: 'vertical',
				   align: 'right',
				   verticalAlign: 'middle',
				   borderWidth: 1
				};
				
				var series =  [
				   {
				      name: '人数',
				      data: json.data
				   }, 
				];
				
				var json = {};
				
				json.title = title;
				json.subtitle = subtitle;
				json.xAxis = xAxis;
				json.yAxis = yAxis;
				json.tooltip = tooltip;
				json.legend = legend;
				json.series = series;
				
				$('#'+id).highcharts(json);
			}else{
				alert("error");
			}
		},
		error:function(){
			alert("error");
		}
	});
}

function commonAjax(ex_url,id,area,type){
	$.ajax({
		url:service_url+'scalehouse/'+ex_url,
		type:'post',
		dataType:'json',
		data:{
			'countryCode':area,
			'type':type
		},
		cache:false,
		success:function(json){
			if(json.code==1){
				$("#"+id).html(json.total);
			}else{
				alert("网络卡了~,稍后再试!");
			}
		},
		error:function(){
			alert("网络卡了~,稍后再试!");
		}
	});
}

/**
* Name 分页过滤器
*/
function pagerFilter(data){       
	if (typeof data.length == 'number' && typeof data.splice == 'function'){// is array                
		data = {                   
			total: data.length,                   
			rows: data               
		};         
	}
	var dg = $(this);    
	var opts = dg.datagrid('options');
	var pager = dg.datagrid('getPager');          
	pager.pagination({                
		onSelectPage:function(pageNum, pageSize){                
			opts.pageNumber = pageNum;       
			opts.pageSize = pageSize;                
			pager.pagination('refresh',{pageNumber:pageNum,pageSize:pageSize});                  
			dg.datagrid('loadData',data);
		}          
	});
	if (!data.originalRows){               
		data.originalRows = (data.rows);
	}
	var start = (opts.pageNumber-1)*parseInt(opts.pageSize);     
	var end = start + parseInt(opts.pageSize);        
	data.rows = (data.originalRows.slice(start, end));         
	return data;      
}

//时间格式化
Date.prototype.Format = function(fmt) {
	var o = {
	"M+" : this.getMonth()+1, //月份
	"d+" : this.getDate(), //日
	"H+" : this.getHours(), //小时
	"m+" : this.getMinutes(), //分
	"s+" : this.getSeconds(), //秒
	"q+" : Math.floor((this.getMonth()+3)/3), //季度
	"S" : this.getMilliseconds() //毫秒
	};
	if(/(y+)/.test(fmt))
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	for(var k in o)
		if(new RegExp("("+ k +")").test(fmt))
	fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	return fmt;
};

