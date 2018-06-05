<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML> 
<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
        <title>title</title>
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <style type="text/css">
            body{background-color: #f5f6f7;}
            .imgHolder{border: 2px dotted #bbb;color:#bbb;width: 120px;height: 140px;line-height: 140px;border-radius: 8px;cursor: pointer;text-align: center;position: relative;background-color: #fff;}
            .imgHolder:hover{border-color: #999;color: #999;}
            .imgHolder .add{font-size: 50px;}
            .imgHolder img{width: 100%;height: 100%;}
            .fileUploadBtn{position: absolute;top: 0px;left: 0px;width: 100%;height: 100%;cursor: pointer;outline: none;border:0 none;z-index: 1;opacity: 0;filter:alpha(opacity:1);}
            .loading{position: absolute;top: 0px;left: 0px;width: 100%;height: 100%;z-index: 2;display: none;
                background:url("data:image/gif") center center no-repeat;
            }
            .loading span{font-size: 12px;display: block;margin-top: 30px;}
        </style>
    </head> 
    <body>
    	<table>
    		<tr>
    			<td>
    				<div class="imgHolder">
			            <span class="add" name="fileName" id="fileName">+</span>
			            <form action="<s:url action="update"/>" id="uploadFrom1" method="post" target="uploadHolderFrame1" enctype="multipart/form-data">
			                <input type="file" name="file1" onchange="selectFileName(this.value);" class="fileUploadBtn" accept="image/*"/>
			            </form>
			            <div class="loading"><span>正在上传...</span></div>
			        </div>
    			</td>
    			<td>
    				<div class="imgHolder">
			            <span class="add">+</span>
			            <form action="<s:url action="update"/>" id="uploadFrom2" method="post" target="uploadHolderFrame2" enctype="multipart/form-data">
			                <input type="file" name="file" class="fileUploadBtn" accept="image/*"/>
			            </form>
			            <div class="loading"><span>正在上传...</span></div>
			        </div>
    			</td>
    			<td>
    				<div class="imgHolder">
			            <span class="add">+</span>
			            <form action="<s:url action="update"/>" id="uploadFrom3" method="post" target="uploadHolderFrame3" enctype="multipart/form-data">
			                <input type="file" name="file" class="fileUploadBtn" accept="image/*"/>
			            </form>
			            <div class="loading"><span>正在上传...</span></div>
			        </div>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<div class="imgHolder">
			            <span class="add">+</span>
			            <form action="<s:url action="update"/>" id="uploadFrom4" method="post" target="uploadHolderFrame4" enctype="multipart/form-data">
			                <input type="file" name="file" class="fileUploadBtn" accept="image/*"/>
			            </form>
			            <div class="loading"><span>正在上传...</span></div>
			        </div>
    			</td>
    			<td>
    				<div class="imgHolder">
			            <span class="add">+</span>
			            <form action="<s:url action="update"/>" id="uploadFrom5" method="post" target="uploadHolderFrame5" enctype="multipart/form-data">
			                <input type="file" name="file" class="fileUploadBtn" accept="image/*"/>
			            </form>
			            <div class="loading"><span>正在上传...</span></div>
			        </div>
    			</td>
    			<td>
    				<div class="imgHolder">
			            <span class="add">+</span>
			            <form action="<s:url action="update"/>" id="uploadFrom6" method="post" target="uploadHolderFrame6" enctype="multipart/form-data">
			                <input type="file" name="file" class="fileUploadBtn" accept="image/*"/>
			            </form>
			            <div class="loading"><span>正在上传...</span></div>
			        </div>
    			</td>
    		</tr>
    	</table>
    	
        <iframe id="uploadHolderFrame" name="uploadHolderFrame" style="position:absolute;top:-100px;left:-100px;width:0px;height:0px;"></iframe>
        <script type="text/javascript">
            jQuery(document).ready(function() {
                $('input[name="file"]').bind('change',function(){
                    $('#uploadFrom1').submit();
                    $('.loading').show();
                });
                $('#uploadHolderFrame1').bind('load',function(){
                    var bodyContent = $(window.frames['uploadHolderFrame1'].document.body).html();
                    $('body').append('<div>'+bodyContent+'</div>')
                    $('<img style="display:none;" src="'+bodyContent+'"/>').appendTo('.imgHolder').bind('load',function(){
                        $('.imgHolder').empty().append($(this));
                        $(this).show();
                    })
                });
            });
            
            function selectFileName(fileName){
			$('#fileName').val(fileName);
			//validateFloorPlanFileName();
}
        </script>
    </body> 
</html>