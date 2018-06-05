<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.housemgr.vo.HousingDistrictInfoVo"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body, html{width: 100%;height: 100%; margin:0;}
		#l-map{height:300px;width:400px;float:right;}
		#r-result{width:250px;height:300px;float:left;margin-top:0;}
		#btn{float:right;height:32px;}
	</style>
	
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=95842e924d7e9b438a818b3a3e94bd9c"></script>
	<title>我的周边</title>
	<%	HousingDistrictInfoVo housingDistrictInfo = (HousingDistrictInfoVo)request.getAttribute("housingDistrictInfo");
		String position = null;
		String longitude = null;	//经度
		String dimension = null;	//维度
		if(housingDistrictInfo!=null){
			position = housingDistrictInfo.getPosition();
		}
		if(position!=null){
			String[] str =  position.split(",");
			longitude = str[0];
			dimension = str[1];
		}
	%>
</head>
<body>
	<table width="680">
		<tr><td>
		
		<div id="btn">
					<input type="button" value="购物" onclick="toTab(this);"/>
					<input type="button" value="酒店" onclick="toTab(this);"/>
					<input type="button" value="油站" onclick="toTab(this);"/>
					<input type="button" value="电影" onclick="toTab(this);"/>
					<input type="button" value="银行" onclick="toTab(this);"/>
					<input type="button" value="美食" onclick="toTab(this);"/>
					<input type="button" value="洗衣" onclick="toTab(this);"/>
					<input type="button" value="休闲" onclick="toTab(this);"/>
		</div>
		<div id="r-result"></div>
		<div id="l-map"></div>
		
		</td></tr>
		
	</table>
		
	
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("l-map");            // 创建Map实例
	var point = toPos(<%=longitude%>,<%=dimension%>);	//地图上创建坐标点
	map.centerAndZoom(point, 14);	//地图级别放大到14
	//map.setZoom(15); 
	var local = new BMap.LocalSearch(map, {
		renderOptions: {map: map, autoViewport: false,selectFirstResult: false,panel: "r-result"},
		pageCapacity:3
	});
	var marker = new BMap.Marker(point);  // 创建标注
			map.addOverlay(marker);              // 将标注添加到地图中
	local.searchNearby("购物",point,1500);	//检索周边1500m内

	function toTab(key){
		local.searchNearby(key.value,point,1500);
	};
	
	function toPos(longitude,dimension){
		return new BMap.Point(longitude,dimension);
	};
</script>
