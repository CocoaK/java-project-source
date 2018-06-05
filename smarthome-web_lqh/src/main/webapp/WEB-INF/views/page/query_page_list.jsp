<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>电商页面管理</title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/menu/menu.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/user/user.js'/>"></script>
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
                        <a href="<s:url action='queryList'/>" class="all_menu_a">页面列表</a>
                        <a href="<s:url action='addInput'/>" >添加页面</a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">   
                        	<label>所属小区</label>
				            <input type="hidden" id="selectedDistrictId" name="pageVO.districtId" value="<s:property value='device.housingDistrictInfo.id'/>" />
				            <input type="text" id="selectedDistrictName" size="20" name="districtName" value="<s:property value="device.housingDistrictInfo.name" />" readonly="readonly" class="all_tab_top_input"/>
				            <a href="javascript:void(0);queryDistricts('<s:url action="getDistrictsByName" namespace="/housemgr/json"/>');" class="notetitle all_hover_but but_left"><s:text name="common.element.action.select" /></a>                
                            
                            <label class="all_border_right">页面名称</label>
                            <input type="text" maxlength="20" name="pageVO.name" value="<s:property value='pageVO.name'/>" class="all_tab_top_input"/>                    
                            <input type="submit" onclick="doSubmit();" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th width="50">选择</th>
                                    <th width="20%"><s:text name="页面名称"/></th>
                                    <th width="40%"><s:text name="页面描述"/></th>
                                    <th><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="pages">
                                    <tr>
                                    	<td>
                                    		<input type="checkbox" name="updateId" checked="checked" onclick="return false;" value="<s:property value="#pages.id"/>"/></td>
                                        <td><s:property value="#pages.name"/></td>
                                        <td><s:property value="#pages.pageDesc"/></td>
                                        <td>
                                        	<a href="<s:url action="delete"><s:param name="id" value="#pages.id"/></s:url>" class="all_hover_but">
												删除
                                            </a>
                                            <a href="<s:url action="updateInput"><s:param name="id" value="#pages.id"/></s:url>" class="all_hover_but">
                                                <s:text name="common.element.action.update"/>
                                            </a>
                                            <a href="<s:url namespace="/component" action="queryList"><s:param name="componentVO.pageId" value="#pages.id"/></s:url>" class="all_hover_but">
                                            	查看组件
                                            </a>
                                            <a href="<s:url namespace="/component" action="actionAddInput"><s:param name="componentVO.pageId" value="#pages.id"/></s:url>" class="all_hover_but">
                                            	新增组件
                                            </a>
                                        </td>
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <s:if test="#pages!=null"><div class="all_tab_butb"><input type="submit" value="保存"/></div></s:if>
                        <s:include value="/views/common/paging.jsp"/>
                        <s:include value="/WEB-INF/views/device/include_select_district_msg.jsp"/>  
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
                                        <li>
                                            <a href="javascript:hideTip('resultFrame');"><s:text name="common.element.action.confirm"/></a>
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
