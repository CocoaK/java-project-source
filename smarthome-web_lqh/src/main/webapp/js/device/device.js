function saveDeviceIp(){
	if($($("form")[1]).valid()){
		$("form")[1].submit();
		$("#list_click").hide();
		
	}
}

function confirmAction(targetUrl,confirmMsg){
	$('#targetUrl').attr("href",targetUrl);
	$('#confirmMsg').text(confirmMsg);
	$('#confirmFrame').show();
}

function postAdd(deviceId){
		var reg=new RegExp("^[1-9][0-9]{3,14}$");
		var sip = $("#sip"+deviceId).val();
		if(sip==null || sip==""){
			return;
		}else if(reg.test(sip)!=true){
			$("#sip"+deviceId).text("只能为4-15位数字");
			return;
		}else{
			$("#sip"+deviceId).text("");
		}
       $.ajax({
				url : "../json/add?deviceSipVO.deviceId="+deviceId+"&deviceSipVO.sipid="+sip,
				// 数据发送方式 
				type : "post",
				// 接受数据格式            
				dataType : 'json',
				cache:false,
				// 要传递的数据       
				//data:{'loginName':'loginName'},
				//data : params,
				// 回调函数，接受服务器端返回给客户端的值，即result值          
				success : function(data, textStatus){
        			$("#a"+deviceId).hide();
        			$("#sip"+deviceId).hide();
        			$("#label"+deviceId).text(data.sipid);
        		},
        		error: function(){
        		}
		});
}