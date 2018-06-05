<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Jquery分页的列子</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link >
<script type="text/javascript" src="jquery/jquery-1.7.2.min.js"></script>	

<script type="text/javascript">
	$(document).ready(function(){
		//$("p").replaceWith("<b>Paragraph. </b>");
		//alert(11);
	});
	
	function getData(yeshu){
		//alert(yeshu);
		//$("#totalPage").html();
		//alert($("#totalPage").html());
		//alert($("#currentPage").html());
		$.ajax({
			type:"post",  
			url:"<%=path%>/inteligentHome/getJsonPageList.action",  
			data:{yeshu:yeshu},  
			dataType:"json",  
			success:function(data){
				//alert(data);
				//alert(data.list);
				//alert(data.page.totalPage);
				if(!(data.page.isHasPre)){
					$("#lastPage").attr("disabled","disabled");
					//$("#lastPage").removeAttr("onclick");
				}else{
					$("#lastPage").removeAttr("disabled");
					//$("#lastPage").attr("onclick",getData(yeshu));
				}
				if(!(data.page.isHasNext)){
					$("#nextPage").attr("disabled","disabled");
					//$("#nextPage").removeAttr("onclick");
				}else{
					//alert(22);
					$("#nextPage").removeAttr("disabled");
					//$("#nextPage").attr("onclick","getData()");
				}
				
				$("#totalPage").html(data.page.totalPage);
				$("#currentPage").html(data.page.currentPage);
				//alert(data.page.list);
				var textContent = "";
				$.each(data.page.list,function(i,entity){
					//alert(entity.name + " " + entity.age + " " + entity.sex);
					//$("#darklist").append("<tr><td>" + entity.name + "</td><td>" + entity.age + "</td><td>" + entity.sex + "</td></tr>");
					textContent += "<tr><td>" + entity.name + "</td><td>" + entity.age + "</td><td>" + entity.sex + "</td></tr>";
				});
				$("#darklist").html(textContent);
			}
		});	
	}
	
	
</script>
</head>

<body>
	<a href="<%=path%>/inteligentHome/login.action">跳转</a>
	<button id="aa" name="aa" type="button" value="tijiao" title="asdf" ></button>

	<p>大家好</p>
	<table align="center" width="780" border="1" cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<td>姓名</td>
				<td>年龄</td>
				<td>性别</td>
			</tr>		
		</thead>
		
		<tbody id="darklist">
			<tr>
				<td>1</td>
				<td>2</td>
				<td>3</td>
			</tr>
			<tr>
				<td>11</td>
				<td>22</td>
				<td>33</td>
			</tr>				
		</tbody>
		<div>
			<table align="center" width="780" border="1" cellpadding="0" cellspacing="0">
				<tr>
					<div id="page">
						<td align="right">
							共<b id="totalPage"> 30 </b>页&nbsp;&nbsp;&nbsp;
							当前第<b id="currentPage">1</b>页&nbsp;&nbsp;&nbsp;
							<input id="lastPage" value="上一页" type="button" onclick="getData(parseInt($('#currentPage').html())-1)">&nbsp;&nbsp;
							<input id="nextPage" value="下一页" type="button" onclick="getData(parseInt($('#currentPage').html())+1)">&nbsp;&nbsp;
							GO<input type="text">页<input value="GO" type="button" onclick="getData()">
						</td>

					</div>
				</tr>
			</table>
		</div>
	</table>
</body>
</html>
