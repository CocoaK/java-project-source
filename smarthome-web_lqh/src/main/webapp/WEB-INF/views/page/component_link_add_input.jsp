<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="添加组件"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
    </head>

    <body>
    <form action="<s:url action="add"/>" method="post" enctype="multipart/form-data">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                   	<a href="<s:url action='actionAddInput'><s:param name="componentVO.pageId" value="componentVO.pageId"/></s:url>" >新增动作</a>
                    <a href="<s:url action='linkAddInput'><s:param name="componentVO.pageId" value="componentVO.pageId"/></s:url>" class="all_menu_a">新增链接</a>
                    <a href="<s:url action='textAddInput'><s:param name="componentVO.pageId" value="componentVO.pageId"/></s:url>" >新增文本</a>
                    <a href="<s:url action='appAddInput'><s:param name="componentVO.pageId" value="componentVO.pageId"/></s:url>" >新增应用</a>
                   	                   	
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="20%">组件名称</td>
                                <td>
                                	<input size="20" id="componentVO.name" maxlength="40" name="componentVO.name" />
                                	<input type="hidden" name="componentVO.pageId" value="<s:property value='componentVO.pageId'/>"/>
                                	<input type="hidden" name="componentVO.type" value="1"/>
                                </td>
                            </tr>
                            <!-- <tr>
                                <td>组件类型</td>
                                <td>
	                                <select name="componentVO.type" onchange="selectType(this.value);">
			                            <option value="1">链接</option>
			                            <option value="2">动作</option>
			                            <option value="3">文本</option>
			                            <option value="4">应用</option>                            
			                        </select>
		                        </td>
                            </tr> -->
							<tr>
								<td>组件分组</td>
                                <td>
									<select name="componentVO.groups">
			                            <option value="1">title</option>
			                            <option value="2">content</option>                            
			                        </select>
								</td>
							</tr>
                            <tr>
                                <td>内容</td>
                                <td>
                                	<div id="url_input"><input size="60" id="componentVO.url" maxlength="40" name="componentVO.url" /></div>
                                	<!-- <div id="url_select">
	                                	<select name="componentVO.url">
	                                		<s:iterator value="%{pages}" var="page">
	                                			<option value="<s:property value="#page.id"/>"><s:property value="#page.name"/></option>
	                                		</s:iterator>                            
				                        </select>
			                        </div>-->
                                </td>
                            </tr>
                            <tr>
                                <td>图片</td>
                                <td>
                                	<input type="file" name="file" id="file" onchange="selectFileName(this.value,fileName);" class="fileUploadBtn" accept="image/*"/>
                                	<input type="hidden" name="fileName" id="fileName" readonly="readonly" />
                                </td>
                            </tr>
                            <tr>
                                <td>上方组件编号</td>
                                <td><input size="20" id="componentVO.belowOfId" maxlength="40" name="componentVO.belowOfId" /></td>
                            </tr>
                            <tr>
                                <td>左方组件编号</td>
                                <td><input size="20" id="componentVO.rightOfId" maxlength="40" name="componentVO.rightOfId" /></td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="submit" value="保存"/></div>                   
                </div>
            </div>
        </div>
        
        			<s:if test="successFlag == 'success'">
        			<div class="layer" id="confirmFrame">
                    	<div>
                            <p><s:text name="common.system.info"/></p>
                            <ul>
                                <li>
                                   	 操作成功
                                </li>
                                <li>
                                    <a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.confirm"/></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    </s:if>
                    <s:if test="successFlag == 'repeat_name'">                                       
                        <div class="layer" id="confirmFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                	<li> <s:text name="common.element.action.operationfailed"/></li>
                                    <li>名称重复</li>
                                    <li>
                                        <a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.confirm"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </s:if>              
                
	</form>
        <script>
    		QueryLoader.selectorPreload = "body";
    		QueryLoader.init();
    		
    		function selectFileName(filePathName,id){
				$(id).val(filePathName);
			};

			function selectType(value){
				var html = "<select name='componentVO.url'><s:iterator value='%{pages}' var='page'>"
					+"<option value='<s:property value='#page.id'/>'><s:property value='#page.name'/></option></s:iterator></select>";
				if(value=='2'){
					$(url_input).html(html);
				}else{
					var str = "<textarea id='componentVO.url' name='componentVO.url' />";
					$(url_input).html(str);
				}
			};

    	</script>
    </body>
</html>
