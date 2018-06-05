$(function() {		
	$('#map').mousemove(function(e) {
			showCoor(e);
	}).toggle(function() {
				$('#switch').val('0');
			},
			function() {
				$('#switch').val('1');
			});

    $(".mapxyshow a").first().click(function() {
    	var coordinate = $('#spShow').text();
    	if (map.multipleLocation != null) { // 更新多个生成坐标中的一个
    		$("#newRegionList input[name='coordinates']").eq(map.multipleLocation).val(coordinate);
    		$("#newRegionList input[name='coordinatesText']").eq(map.multipleLocation).val(coordinate);
    		if(coordinate == "0,0")
    			$("#newRegionList input[name='coordinatesText']").eq(map.multipleLocation).next("label.error").show();
    		else
    			$("#newRegionList input[name='coordinatesText']").eq(map.multipleLocation).next("label.error").hide();
    	} else {
            $("#coordinateValue").val(coordinate);
            $("#coordinateText").val(coordinate);
    	}

        $("#list_click").slideUp();
    });
});

// 记录要更新哪个生成坐标
var map = {};

function showCoor(e) { 
	var swi = $.trim( $('#switch').val() );
	if(swi == 1) {
		var ex = e.clientX;
		var ey = e.clientY;		
		var exs = ex - 235;
		var eys = ey - 15;
		var ww = $(window).width();
		var wh = $(window).height();

		var str = exs +','+ eys +'';
		$('#spShow').css({"left":(ex-220) +"px","top":(ey) +"px"}).html(str);
		$('#coordinatesx').css({"width":ex +"px","height":ey +"px"});

		if($.browser.msie && $.browser.version == '6.0') {
			ex -= 1;
			ey -= 1;
		}
		$('#coordinatesy').css({"width":(ww-ex) +"px","height":(wh-ey) +"px","left":ex +"px","top":ey +"px"});
	}
}
