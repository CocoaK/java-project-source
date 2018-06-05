<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>

<!DOCTYPE HTML> 
<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"> 
        <title>title</title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <style type="text/css">
            body{background-color: #f5f6f7;}
            .imgHolder{border: 2px dotted #bbb;color:#bbb;width: 220px;height: 200px;line-height: 200px;border-radius: 8px;cursor: pointer;text-align: center;position: relative;background-color: #fff;}
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
    <div class="main_outside" align="center">
    	<div class="all_inside">
    	<table>
    		<tr>
    			<td>
    				<div class="imgHolder">
			            <span class="add" id="add0">+</span>
			            <form action="<s:url action="upload"/>" id="uploadFrom0" method="post" target="uploadHolderFrame0" enctype="multipart/form-data">
			            	<input type="hidden" name="fileName" id="fileName0" readonly />
			            	<input type="hidden" name="index" value="0" readonly />
			                <input type="file" name="file" id="file0" onchange="selectFileName(this.value,fileName0);" class="fileUploadBtn" accept="image/*"/>
			            </form>
			            <div class="loading" id="loading0"><span>正在上传...</span></div>
			        </div>
    			</td>
    			<td>
    				<div class="imgHolder">
			            <span class="add" id="add1">+</span>
			            <form action="<s:url action="upload"/>" id="uploadFrom1" method="post" target="uploadHolderFrame1" enctype="multipart/form-data">
			                <input type="hidden" name="fileName" id="fileName1" readonly />
			                <input type="hidden" name="index" value="1" readonly />
			                <input type="file" name="file" id="file1" onchange="selectFileName(this.value,fileName1);" class="fileUploadBtn" accept="image/*"/>
			            </form>
			            <div class="loading" id="loading1"><span>正在上传...</span></div>
			        </div>
    			</td>
    			<td>
    				<div class="imgHolder">
			            <span class="add" id="add2">+</span>
			            <form action="<s:url action="upload"/>" id="uploadFrom2" method="post" target="uploadHolderFrame2" enctype="multipart/form-data">
			                <input type="hidden" name="fileName" id="fileName2" readonly />
			                <input type="hidden" name="index" value="2" readonly />
			                <input type="file" name="file" id="file2" onchange="selectFileName(this.value,fileName2);" class="fileUploadBtn" accept="image/*"/>
			            </form>
			            <div class="loading" id="loading2"><span>正在上传...</span></div>
			        </div>
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<div class="imgHolder">
			            <span class="add" id="add3">+</span>
			            <form action="<s:url action="upload"/>" id="uploadFrom3" method="post" target="uploadHolderFrame3" enctype="multipart/form-data">
			                <input type="hidden" name="fileName" id="fileName3" readonly />
			                <input type="hidden" name="index" value="3" readonly />
			                <input type="file" name="file" id="file3" onchange="selectFileName(this.value,fileName3);" class="fileUploadBtn" accept="image/*"/>
			            </form>
			            <div class="loading" id="loading3"><span>正在上传...</span></div>
			        </div>
    			</td>
    			<td>
    				<div class="imgHolder">
			            <span class="add" id="add4">+</span>
			            <form action="<s:url action="upload"/>" id="uploadFrom4" method="post" target="uploadHolderFrame4" enctype="multipart/form-data">
			                <input type="hidden" name="fileName" id="fileName4" readonly />
			                <input type="hidden" name="index" value="4" readonly />
			                <input type="file" name="file" id="file4" onchange="selectFileName(this.value,fileName4);" class="fileUploadBtn" accept="image/*"/>
			            </form>
			            <div class="loading" id="loading4"><span>正在上传...</span></div>
			        </div>
    			</td>
    			<td>
    				<div class="imgHolder">
			            <span class="add" id="add5">+</span>
			            <form action="<s:url action="upload"/>" id="uploadFrom5" method="post" target="uploadHolderFrame5" enctype="multipart/form-data">
			                <input type="hidden" name="fileName" id="fileName5" readonly />
			                <input type="hidden" name="index" value="5" size="5" readonly />
			                <input type="file" name="file" id="file5" onchange="selectFileName(this.value,fileName5);" class="fileUploadBtn" accept="image/*"/>
			            </form>
			            <div class="loading" id="loading5"><span>正在上传...</span></div>
			        </div>
    			</td>
    		</tr>
    	</table>
    	
        <iframe id="uploadHolderFrame0" name="uploadHolderFrame0" onload="getSource(0)" style="position:absolute;top:-100px;left:-100px;width:0px;height:0px;"></iframe>
        <iframe id="uploadHolderFrame1" name="uploadHolderFrame1" onload="getSource(1)" style="position:absolute;top:-100px;left:-100px;width:0px;height:0px;"></iframe>
        <iframe id="uploadHolderFrame2" name="uploadHolderFrame2" onload="getSource(2)" style="position:absolute;top:-100px;left:-100px;width:0px;height:0px;"></iframe>
        <iframe id="uploadHolderFrame3" name="uploadHolderFrame3" onload="getSource(3)" style="position:absolute;top:-100px;left:-100px;width:0px;height:0px;"></iframe>
        <iframe id="uploadHolderFrame4" name="uploadHolderFrame4" onload="getSource(4)" style="position:absolute;top:-100px;left:-100px;width:0px;height:0px;"></iframe>
        <iframe id="uploadHolderFrame5" name="uploadHolderFrame5" onload="getSource(5)" style="position:absolute;top:-100px;left:-100px;width:0px;height:0px;"></iframe>
        <table cellspacing="0" class="all_tab_body all_tab_bodys">
   		<thead class="all_tab_thead">
        	<tr>
                <th width="10%">名称</th>
                <th width="10%">类型</th>
                <th>链接地址</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
       	</thead>
    	<tbody>
    		<tr>
    			<td><label>组件1：</label></td>
    			<td>
    			<div class="select_div">
		            <select name="type" id="type0">
			        <option value="content">内容</option>
                    <option value="button">按钮</option>     
			        </select>
	            </div>
	            </td>	
    			<td><input type="text" size="95%" name="linkUrl" id="linkUrl0" /></td>
    			<td><span id="flag0"></span></td>
	            <td>
	            	<a href="javascript:updateModule(0);" type="button" class="all_hover_but"><s:text name="common.element.action.confirm"/></a>
	           	 </td>
    		</tr>
    		<tr>
    			<td><label>组件2：</label></td>
    			<td>
    			<div class="select_div">
		            <select name="type" id="type1">
			        <option value="content">内容</option>
                    <option value="button">按钮</option>     
			        </select>
	            </div>
	            </td>	
    			<td><input type="text" size="95%" name="linkUrl" id="linkUrl1" /></td>
    			<td><span id="flag1"></span></td>
	            <td>
	            	<a href="javascript:updateModule(1);" type="button" class="all_hover_but"><s:text name="common.element.action.confirm"/></a>
	           	 </td>
    		</tr>
    		<tr>
    			<td><label>组件3：</label></td>
    			<td>
    			<div class="select_div">
		            <select name="type" id="type2">
			        <option value="content">内容</option>
                    <option value="button">按钮</option>     
			        </select>
	            </div>
	            </td>	
    			<td><input type="text" size="95%" name="linkUrl" id="linkUrl2" /></td>
    			<td><span id="flag2"></span></td>
	            <td>
	            	<a href="javascript:updateModule(2);" type="button" class="all_hover_but"><s:text name="common.element.action.confirm"/></a>
	           	 </td>
    		</tr>
    		<tr>
    			<td><label>组件4：</label></td>
    			<td>
    			<div class="select_div">
		            <select name="type" id="type3">
			        <option value="content">内容</option>
                    <option value="button">按钮</option>     
			        </select>
	            </div>
	            </td>	
    			<td><input type="text" size="95%" name="linkUrl" id="linkUrl3" /></td>
    			<td><span id="flag3"></span></td>
	            <td>
	            	<a href="javascript:updateModule(3);" type="button" class="all_hover_but"><s:text name="common.element.action.confirm"/></a>
	           	 </td>
    		</tr>
    		<tr>
    			<td><label>组件5：</label></td>
    			<td>
    			<div class="select_div">
		            <select name="type" id="type4">
			        <option value="content">内容</option>
                    <option value="button">按钮</option>     
			        </select>
	            </div>
	            </td>	
    			<td><input type="text" size="95%" name="linkUrl" id="linkUrl4" /></td>
    			<td><span id="flag4"></span></td>
	            <td>
	            	<a href="javascript:updateModule(4);" type="button" class="all_hover_but"><s:text name="common.element.action.confirm"/></a>
	           	 </td>
    		</tr>
    		<tr>
    			<td><label>组件6：</label></td>
    			<td>
    			<div class="select_div">
		            <select name="type" id="type5">
			        <option value="content">内容</option>
                    <option value="button">按钮</option>     
			        </select>
	            </div>
	            </td>	
    			<td><input type="text" size="95%" name="linkUrl" id="linkUrl5" /></td>
    			<td><span id="flag5"></span></td>
	            <td>
	            	<a href="javascript:updateModule(5);" type="button" class="closediv all_hover_but"><s:text name="common.element.action.confirm"/></a>
	           	 </td>
    		</tr>

    		</tbody>
    	</table>
    	<div class="all_tab_butb"><form action="<s:url action="addTemplate"/>" method="post"><input type="submit" value="<s:text name="common.element.action.confirm"/>"/></form></div>
        </div>
        </div>
        </div>
        
        <script type="text/javascript">
            jQuery(document).ready(function() {
                $('input[id="file0"]').bind('change',function(){
                    $('#uploadFrom0').submit();
                    $('#loading0').show();    
                });
                $('input[id="file1"]').bind('change',function(){
                    $('#uploadFrom1').submit();
                    $('#loading1').show();    
                });
                $('input[id="file2"]').bind('change',function(){
                    $('#uploadFrom2').submit();
                    $('#loading2').show();    
                });
                $('input[id="file3"]').bind('change',function(){
                    $('#uploadFrom3').submit();
                    $('#loading3').show();    
                });
                $('input[id="file4"]').bind('change',function(){
                    $('#uploadFrom4').submit();
                    $('#loading4').show();    
                });
                $('input[id="file5"]').bind('change',function(){
                    $('#uploadFrom5').submit();
                    $('#loading5').show();    
                });
            });
            
            	
            function selectFileName(filePathName,id){
            	//alert("filePathName:"+filePathName);
				$(id).val(filePathName);
			};

			function getSource(index){
	        $.ajax({
					url : "<%=path%>json/template/getSource.action",
					// 数据发送方式            
					type : "post",
					// 接受数据格式            
					dataType : 'json',
					cache:false,
					// 要传递的数据       
					data:{'index':index},
					//data : params,
					// 回调函数，接受服务器端返回给客户端的值，即result值          
					success : function(data, textStatus){
						var imageUrl = null;
						if(data.module != null){
							imageUrl = data.module.imageUrl;
						}
						if(data.module != null && imageUrl != null){
							$("#add"+index).html("<img id='img"+index+"' src='"+imageUrl+"' />");
						}
						$("#loading"+index).hide(); 
	         		},
	         		error: function(){
	         		}
				});
	      	}
	      	
	      	function updateModule(index){
	      	var linkUrl = $("#linkUrl0").val();
	      	var type = $("#type0").val();
	        $.ajax({
					url : "<%=path%>json/template/updateModule.action",
					// 数据发送方式            
					type : "post",
					// 接受数据格式            
					dataType : 'json',
					cache:false,
					// 要传递的数据       
					data:{'index':index,'type':type,'linkUrl':linkUrl},
					//data : params,
					// 回调函数，接受服务器端返回给客户端的值，即result值          
					success : function(data, textStatus){
						if(data.message == "success"){
							$("#flag"+index).html("<font color='green'>success</font>");
						}else{
							$("#flag"+index).html("<font color='red'>fail</font>");
						}
						
	         		},
	         		error: function(){
	         		}
				});
	      	}
	      	
	      	function addTemplate(){
	        	document.addTemplate.submit();
	      	}
      
        </script>
    </body> 
</html>