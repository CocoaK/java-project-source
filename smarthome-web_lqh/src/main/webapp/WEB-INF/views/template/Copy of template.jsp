<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head> 
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>title</title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
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
            .loading{position: absolute;top: 0px;left: 0px;width: 100%;height: 100%;z-index: 2;display: none;}
            .loading span{font-size: 12px;display: block;margin-top: 30px;}
        </style>
    </head> 
    <body>
    <div class="main_section">
    <div class="main_outside">
    	<div class="all_inside">
    	<table>
    		<tr>
    			<td>
    				<div class="imgHolder">
			            <span class="add" id="add"><img id="img1" src="#" />+</span>
			            <form action="<s:url action="upload"/>" id="uploadFrom1" method="post" target="uploadHolderFrame1" enctype="multipart/form-data">
			                <input type="text" name="fileName" id="fileName" readonly /> 
			                <input type="file" name="file1" onchange="selectFileName(this.value);" class="fileUploadBtn" accept="image/*"/>
			            </form>
			            <div class="loading" id="loading1"><span>正在上传...</span></div>
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
    	<table cellspacing="0" class="all_tab_body">
   		<thead class="all_tab_thead">
        	<tr>
                <th width="20%">名称</th>
                <th width="10%">类型</th>
                <th>链接地址</th>
            </tr>
       	</thead>
    	<tbody>
    		<tr>
    			<td>组件1：</td>
    			<td>
    			<div class="select_div">
		            <select name="type">
			        <option value="content">内容</option>
                    <option value="button">按钮</option>     
			        </select>
	            </div>
    			</td>
    			<td><input type="text" size="150" name="linkUrl" /></td>
    		</tr>
    		<tr>
    			<td>组件2：</td>
    			<td>
    			<div class="select_div">
		            <select name="type">
			        <option value="content">内容</option>
                    <option value="button">按钮</option>     
			        </select>
	            </div>
    			</td>
    			<td><input type="text" size="150" name="linkUrl" /></td>
    		</tr>
    		<tr>
    			<td>组件3：</td>
    			<td>
    			<div class="select_div">
		            <select name="type">
			        <option value="content">内容</option>
                    <option value="button">按钮</option>     
			        </select>
	            </div>
    			</td>
    			<td><input type="text" size="150" name="linkUrl" /></td>
    		</tr>
    		<tr>
    			<td>组件4：</td>
    			<td>
    			<div class="select_div">
		            <select name="type">
			        <option value="content">内容</option>
                    <option value="button">按钮</option>     
			        </select>
	            </div>
    			</td>
    			<td><input type="text" size="150" name="linkUrl" /></td>
    		</tr>
    		<tr>
    			<td>组件5：</td>
    			<td>
    			<div class="select_div">
		            <select name="type">
			        <option value="content">内容</option>
                    <option value="button">按钮</option>     
			        </select>
	            </div>
    			</td>
    			<td><input type="text" size="150" name="linkUrl" /></td>
    		</tr>
    		<tr>
    			<td>组件6：</td>
    			<td>
    			<div class="select_div">
		            <select name="type">
			        <option value="content">内容</option>
                    <option value="button">按钮</option>     
			        </select>
	            </div>
    			</td>
    			<td><input type="text" size="150" name="linkUrl" /></td>
    		</tr>
    		</tbody>
    	</table>
    	</div>
    	</div>
    	</div>
        <iframe id="uploadHolderFrame1" name="uploadHolderFrame1" style="position:absolute;top:-100px;left:-100px;width:0px;height:0px;"></iframe>
        <script type="text/javascript">
        	var webDownloadUrl = "<s:property value='webDownloadUrl'/>";
            var newFileName = "<s:property value='newFileName'/>";
            var path = webDownloadUrl+newFileName;
            jQuery(document).ready(function() {
                $('input[name="file1"]').bind('change',function(){
                    $('#uploadFrom1').submit();
                    $('#loading1').show();
                    alert("path:"+path);    
                });
                
                //var img = '<img src="'+path+'"/>';
               // alert("path:"+path);
                //$('#img1').attr('src',path);
                $("#img1").attr("src",path);
               // $('#uploadHolderFrame1').bind('load',function(){
                    //var bodyContent = $(window.frames['uploadHolderFrame1'].document.body).html();
                   // var frameContent = $('#file1')value;
                   // alert(frameContent);
                    //$('body').append('<div>'+bodyContent+'</div>')
                    //$('<img style="display:none;" src="'+bodyContent+'"/>').appendTo('.imgHolder').bind('load',function(){
                    //    $('.imgHolder').empty().append($(this));
                    //    $(this).show();
                   // })
               // });
            });
            
            function selectFileName(filePathName){
			$('#fileName').val(filePathName);
			//alert("fileName:"+filePathName);
			//validateFloorPlanFileName();
			};
			
		//	function validateFloorPlanFileName(){
		//		return validateFileName($('#fileName').val(), $('#picErrorMsg'), validFileExts, ignoreEmpty);
		//	}
		
		function uploadFile(){
		var file1 = $('#file1').val();
		var fileName = $('#fileName').val();
        $.ajax({
				url : "<%=path%>json/template/upload.action",
				// 数据发送方式            
				type : "post",
				// 接受数据格式            
				dataType : 'json',
				cache:false,
				// 要传递的数据       
				data:{'file':file1,'fileName':fileName},
				//data : params,
				// 回调函数，接受服务器端返回给客户端的值，即result值          
				success : function(data, textStatus){
					alert("data:"+data);
         		},
         		error: function(){
         		}
			});
      }
        </script>
    </body> 
</html>