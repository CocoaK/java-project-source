$(function(){
	$("#selectall").click(function() { 
		$("input[name='shareuser']").each(function() { $(this).attr("checked", true); });
	 }); 
	$("#fanall").click(function() { 
		$("input[name='shareuser']").each(function() { 
			if($(this).attr("checked"))
				{$(this).attr("checked", false); }
			else{$(this).attr("checked", true); }}); 
	}); 
	
	$(".main_box_body li:last,.jur_tab_body li:last,.pass_list ul li:last,.harea_tab_body ul li:last").addClass("all_border_none");
	 $(".all_tab_bodys tbody tr td:even,.all_tab_bodyq tbody tr td:even").addClass("all_tab_bscor");
	 //$(".all_tab_body tbody tr:even").addClass("all_list_bg");
	 $(".all_tab_body tbody tr").hover(function(){$(this).addClass("all_list_bg");},function(){$(this).removeClass("all_list_bg");});
	$("[disabled='disabled']").addClass("disabg");
  	$(".all_tab_body dfn,.layer dfn").html("*");
	$('.opacity').each(function(){
  		$(this).attr('href' , '#');
		});
	
	 $(".screening_show").toggle(function(){
     $(".screening_body").show('fast');
	   },function(){
		 $(".screening_body").hide('slow');
	   });
	
	
	var index = 0;  
	$(".nav_top ul li").click(function(){
		index = $(".nav_top ul li").index(this);
		$(this).addClass("selected").siblings().removeClass("selected");
		$(".nav_body div").eq(index).show().siblings().hide();
	});
	
	var index = 1;  
	$(".main_tab_top ul li").click(function(){
		index = $(".main_tab_top ul li").index(this);
		$(this).addClass("selecteds").siblings().removeClass("selecteds");
		$(".main_tab_body div").eq(index).show().siblings().hide();
	});
	
	var index = 1;  
	$(".house_aside ul li").click(function(){
		index = $(".house_aside ul li").index(this);
		$(this).addClass("selected").siblings().removeClass("selected");
		$(".house_tab_body div").eq(index).show().siblings().hide();
	});
	
	$(".notetitle").click(function(){
		// var _this=$(this);
		 $("#list_click").slideDown();
		// $("#notecontent").html('<img src="images/loading.gif" />数据载入中...'));
	})
        $(".closediv").hover(function(){
},function(){
}).click(function(){
	       $("#list_click").slideUp();
         });
		 
		$(".list_click_but").click(function(){$("#list_click li strong").toggle(); });
		
		$(".notetitle2").click(function(){
			$("#list_click2").slideDown();})
        $(".closediv2").hover(function(){},function(){}).click(function(){
	    	$("#list_click2").slideUp();
         });
		
	// Tab
		
	$(".nav_body div a").click(function(){
		index = $(".nav_body div a").index(this);
		$(this).addClass("navad").siblings().removeClass("navad");
	});
	
	// Tab sub Click
	
	$('.nav_body dfn').each(function(){$(this).css("opacity",".6")})
		.hover(function(){$(this).animate({
		opacity:"1"
		})
		},function(){
		$(this).animate({
		opacity:".6"
		})					
	});
	
	// Nav ico opacity
	 
});
	// All End
	
	function scroll_new(){
    	var firstNode = $('.index_head_prompt li');firstNode.eq(0).fadeOut('slow',function(){
     		$(this).clone().appendTo($(this).parent()).show('slow');$(this).remove();
  		});
	};
	setInterval('scroll_new()',3000);
	
	// Head Prompt
	
	
	(function(){
		var all = $.event.props,
			len = all.length,
			res = [];
		while (len--) {
		  var el = all[len];
		  if (el != 'layerX' && el != 'layerY') res.push(el);
		}
		$.event.props = res;
	}());
	//event.layerX and event.layerY are broken and deprecated in WebKit. They will
	
	function isMobile(){
		var ua=navigator.userAgent.toLowerCase();
		if (ua.indexOf('iphone') != -1)return "iphone";
		if (ua.indexOf('ipod') != -1)return "ipod";
		if (ua.indexOf('ipad') != -1) return "ipad";
		if (ua.indexOf('android') != -1) return "android";
		return false;
	}
	// Pice Show List
	
	(function($){
	$.fn.slide=function(options){
		$.fn.slide.deflunt={
		effect : "fade", 
		autoPlay:false, 
		delayTime : 500,
		interTime : 2500,
		defaultIndex : 0,
		titCell:".hd li",
		mainCell:".bd",
		trigger: "mouseover",
		scroll:1,
		vis:1,
		titOnClassName:"on",
		autoPage:false,
		prevCell:".prev",
		nextCell:".next"
		};

		return this.each(function() {
			var opts = $.extend({},$.fn.slide.deflunt,options);
			var index=opts.defaultIndex;
			var prevBtn = $(opts.prevCell, $(this));
			var nextBtn = $(opts.nextCell, $(this));
			var navObj = $(opts.titCell, $(this));
			var navObjSize = navObj.size();
			var conBox = $(opts.mainCell , $(this));
			var conBoxSize=conBox.children().size();
			var slideH=0;
			var slideW=0;
			var selfW=0;
			var selfH=0;
			var autoPlay = opts.autoPlay;
			var inter=null;
			var oldIndex = index;

			if(conBoxSize<opts.vis) return; 

			if( navObjSize==0 )navObjSize=conBoxSize;
			if( opts.autoPage ){
				var tempS = conBoxSize-opts.vis;
				navObjSize=1+parseInt(tempS%opts.scroll!=0?(tempS/opts.scroll+1):(tempS/opts.scroll)); 
				navObj.html(""); 
				for( var i=0; i<navObjSize; i++ ){ navObj.append("<li>"+(i+1)+"</li>") }
				var navObj = $("li", navObj);
			}

			conBox.children().each(function(){
				if( $(this).width()>selfW ){ selfW=$(this).width(); slideW=$(this).outerWidth(true);  }
				if( $(this).height()>selfH ){ selfH=$(this).height(); slideH=$(this).outerHeight(true);  }
			});

			switch(opts.effect)
			{
				case "top": conBox.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; height:'+opts.vis*slideH+'px"></div>').css( { "position":"relative","padding":"0","margin":"0"}).children().css( {"height":selfH} ); break;
				case "left": conBox.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; width:'+opts.vis*slideW+'px"></div>').css( { "width":conBoxSize*slideW,"position":"relative","overflow":"hidden","padding":"0","margin":"0"}).children().css( {"float":"left","width":selfW} ); break;
				case "leftLoop":
				case "leftMarquee":
					conBox.children().clone().appendTo(conBox).clone().prependTo(conBox); 
					conBox.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; width:'+opts.vis*slideW+'px"></div>').css( { "width":conBoxSize*slideW*3,"position":"relative","overflow":"hidden","padding":"0","margin":"0","left":-conBoxSize*slideW}).children().css( {"float":"left","width":selfW}  ); break;
				case "topLoop":
				case "topMarquee":
					conBox.children().clone().appendTo(conBox).clone().prependTo(conBox); 
					conBox.wrap('<div class="tempWrap" style="overflow:hidden; position:relative; height:'+opts.vis*slideH+'px"></div>').css( { "height":conBoxSize*slideH*3,"position":"relative","padding":"0","margin":"0","top":-conBoxSize*slideH}).children().css( {"height":selfH} ); break;
			}

			
			var doPlay=function(){
				switch(opts.effect)
				{
					case "fade": case "top": case "left": if ( index >= navObjSize) { index = 0; } else if( index < 0) { index = navObjSize-1; } break;
					case "leftMarquee":case "topMarquee": if ( index>= 2) { index=1; } else if( index<0) { index = 0; } break;
					case "leftLoop": case "topLoop":
						var tempNum = index - oldIndex; 
						if( navObjSize>2 && tempNum==-(navObjSize-1) ) tempNum=1;
						if( navObjSize>2 && tempNum==(navObjSize-1) ) tempNum=-1;
						var scrollNum = Math.abs( tempNum*opts.scroll );
						if ( index >= navObjSize) { index = 0; } else if( index < 0) { index = navObjSize-1; }
					break;
				}

				switch (opts.effect)
				{
					case "fade":conBox.children().stop(true,true).eq(index).fadeIn(opts.delayTime).siblings().hide();break;
					case "top":conBox.stop(true,true).animate({"top":-index*opts.scroll*slideH},opts.delayTime);break;
					case "left":conBox.stop(true,true).animate({"left":-index*opts.scroll*slideW},opts.delayTime);break;
					case "leftLoop":
						if(tempNum<0 ){
								conBox.stop(true,true).animate({"left":-(conBoxSize-scrollNum )*slideW},opts.delayTime,function(){
								for(var i=0;i<scrollNum;i++){ conBox.children().last().prependTo(conBox); }
								conBox.css("left",-conBoxSize*slideW);
							});
						}
						else{
							conBox.stop(true,true).animate({"left":-( conBoxSize + scrollNum)*slideW},opts.delayTime,function(){
								for(var i=0;i<scrollNum;i++){ conBox.children().first().appendTo(conBox); }
								conBox.css("left",-conBoxSize*slideW);
							});
						}break;

					case "topLoop":
						if(tempNum<0 ){
								conBox.stop(true,true).animate({"top":-(conBoxSize-scrollNum )*slideH},opts.delayTime,function(){
								for(var i=0;i<scrollNum;i++){ conBox.children().last().prependTo(conBox); }
								conBox.css("top",-conBoxSize*slideH);
							});
						}
						else{
							conBox.stop(true,true).animate({"top":-( conBoxSize + scrollNum)*slideH},opts.delayTime,function(){
								for(var i=0;i<scrollNum;i++){ conBox.children().first().appendTo(conBox); }
								conBox.css("top",-conBoxSize*slideH);
							});
						}break;

					case "leftMarquee":
						var tempLeft = conBox.css("left").replace("px",""); 

						if(index==0 ){
								conBox.animate({"left":++tempLeft},0,function(){
									if( conBox.css("left").replace("px","")>= 0){ for(var i=0;i<conBoxSize;i++){ conBox.children().last().prependTo(conBox); }conBox.css("left",-conBoxSize*slideW);}
								});
						}
						else{
								conBox.animate({"left":--tempLeft},0,function(){
									if(  conBox.css("left").replace("px","")<= -conBoxSize*slideW*2){ for(var i=0;i<conBoxSize;i++){ conBox.children().first().appendTo(conBox); }conBox.css("left",-conBoxSize*slideW);}
								});
						}break;

						case "topMarquee":
						var tempTop = conBox.css("top").replace("px",""); 
							if(index==0 ){
									conBox.animate({"top":++tempTop},0,function(){
										if( conBox.css("top").replace("px","") >= 0){ for(var i=0;i<conBoxSize;i++){ conBox.children().last().prependTo(conBox); }conBox.css("top",-conBoxSize*slideH);}
									});
							}
							else{
									conBox.animate({"top":--tempTop},0,function(){
										if( conBox.css("top").replace("px","")<= -conBoxSize*slideH*2){ for(var i=0;i<conBoxSize;i++){ conBox.children().first().appendTo(conBox); }conBox.css("top",-conBoxSize*slideH);}
									});
							}break;


				}
					navObj.removeClass(opts.titOnClassName).eq(index).addClass(opts.titOnClassName);
					oldIndex=index;
			};
			
			doPlay();

			
			if (autoPlay) {
					if( opts.effect=="leftMarquee" || opts.effect=="topMarquee"  ){
						index++; inter = setInterval(doPlay, opts.interTime);
						conBox.hover(function(){if(autoPlay){clearInterval(inter); }},function(){if(autoPlay){clearInterval(inter);inter = setInterval(doPlay, opts.interTime);}});
					}else{
						 inter=setInterval(function(){index++; doPlay() }, opts.interTime); 
						$(this).hover(function(){if(autoPlay){clearInterval(inter); }},function(){if(autoPlay){clearInterval(inter); inter=setInterval(function(){index++; doPlay() }, opts.interTime); }});
					}
			}

			
			var mst;
			if(opts.trigger=="mouseover"){
				navObj.hover(function(){ clearTimeout(mst); index=navObj.index(this); mst = window.setTimeout(doPlay,200); }, function(){ if(!mst)clearTimeout(mst); });
			}else{ navObj.click(function(){index=navObj.index(this);  doPlay(); })  }
			nextBtn.click(function(){ index++; doPlay(); });
			prevBtn.click(function(){  index--; doPlay(); });

    	});

	};

})(jQuery);