<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>页面组件管理</title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/menu/menu.js'/>"></script>
        <script type="text/javascript">
			function doSubmit(){
	   			document.getElementById("pageNum").value=1;
	   			document.forms[0].submit();
	   		}
</script>
    </head>

    <body>
        <form action="<s:url action="queryList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="<s:url action='queryList'><s:param name="componentVO.pageId" value="componentVO.pageId"/></s:url>" class="all_menu_a">组件列表</a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">
                        	<label class="all_border_right">类型</label>
                        	<div class="select_div">
	                            <select name="componentVO.type">
		                        	<option value="">
		                        		<s:text name="common.title.all"/>
		                            </option>
		                            <option value="1">链接</option>
		                            <option value="2">动作</option>
		                            <option value="3">文本</option>
		                            <option value="4">应用</option>                            
		                        </select>
	                        </div> 
	                        <label class="all_border_right">分组</label>
                        	<div class="select_div">
	                            <select name="componentVO.groups">
		                        	<option value="">
		                        		<s:text name="common.title.all"/>
		                            </option>
		                            <option value="1">title</option>
		                            <option value="2">content</option>                            
		                        </select>
	                        </div>     
                            <input type="hidden" name="componentVO.pageId" value="<s:property value='componentVO.pageId'/>" class="all_tab_top_input"/>                    
                            <input type="submit" onclick="doSubmit();" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                	<th>组件编号</th>
                                    <th>组件名称</th>
                                    <th>组件类型</th>
                                    <th>组件分组</th>
                                    <th>所属页面</th>
                                    <th>内容</th>
                                    <th>图片地址</th>
                                    <th>上方组件编号</th>
                                    <th>左方组件编号</th>
                                    <th><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="component">
                                    <tr>
                                    	<td><s:property value="#component.id"/></td>
                                        <td><s:property value="#component.name"/></td>
                                        <td>
                                        	<s:if test="#component.type == 1">链接</s:if>
                                        	<s:if test="#component.type == 2">动作</s:if>
                                        	<s:if test="#component.type == 3">文本</s:if>
                                        	<s:if test="#component.type == 4">应用</s:if>
                                        </td>
                                        <td>
                                        	<s:if test="#component.groups == 1">title</s:if>
                                        	<s:if test="#component.groups == 2">content</s:if>
                                        </td>
                                        <td><s:property value="#component.pageId"/></td>
                                        <td><s:property value="#component.url"/></td>
                                        <td><s:property value="#component.image"/></td>
                                        <td><s:property value="#component.belowOfId"/></td>
                                        <td><s:property value="#component.rightOfId"/></td>
                                        <td>
                                        	<a href="<s:url action="delete"><s:param name="id" value="#component.id"/></s:url>" class="all_hover_but">
												删除
                                            </a>
                                            <a href="<s:url action="updateInput"><s:param name="id" value="#component.id"/></s:url>" class="all_hover_but">
                                                <s:text name="common.element.action.update"/>
                                            </a>
                                        </td>
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <s:include value="/views/common/paging.jsp"/>
                        <div class="layer" id="confirmFrame" style="display:none;">
                            <div>
                                <p id="confirmMsg"></p>
                                <ul>
                                    <li>
                                        <a id="targetUrl" href="#">
                                        	<s:text name="common.element.action.confirm"/>
                                        </a>
                                        <a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.cancel"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <s:if test="promptFlag == true">                                       
                            <div class="layer" id="resultFrame">
                                <div>
                                    <p><s:text name="common.system.info"/></p>
                                    <ul>
                                        <li>
                                            <s:text name="common.element.action.operationSuccess"/><br/><br/>
                                        </li>
                                        
                                    </ul>
                                </div>
                            </div>
                        </s:if>               
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
