<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="menu.management"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/menu/menu.js'/>"></script>
    </head>

    <body>
        <form action="<s:url action="queryList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="<s:url action='queryList'/>" class="all_menu_a"><s:text name="menu.management.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                   
                            <label><s:text name="menu.status" /></label>
                            <div class="select_div">
                                <select name="menu.status">
                                    <option value=""><s:text name="common.title.all"/></option>
                                    <option value="0" <s:if test="menu.status != '' && menu.status == 0">selected</s:if>>
                                        <s:text name="menu.status.display" />
                                    </option>
                                    <option value="1" <s:if test="menu.status != '' && menu.status == 1">selected</s:if>>
                                        <s:text name="menu.status.hide" />
                                    </option>
                                </select>
                            </div>
                            <label class="all_border_right"><s:text name="menu.name"/></label>
                            <input type="text" maxlength="20" name="menu.menuName" value="<s:property value='menu.menuName'/>" class="all_tab_top_input"/>                    
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th width="30%"><s:text name="menu.code"/></th>
                                    <th width="40%"><s:text name="menu.name"/></th>
                                    <th width="10%"><s:text name="menu.status"/></th>
                                    <th><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="menu">
                                    <tr>
                                        <td>
                                            <a href="<s:url action="viewDetail"><s:param name="menuCode" value="#menu.menuCode"/></s:url>">
                                                <s:property value="#menu.menuCode"/>
                                            </a>
                                        </td>
                                        <td><s:property value="#menu.menuName"/></td>
                                        <s:if test="#menu.status == 0">
                                            <td><s:text name="menu.status.display"/></td>
                                            <td>
                                                <a href="javascript:confirmHideOrDisplay('<s:url action="hideOrDisplay"/>','<s:property value="#menu.menuCode"/>','1','<s:text name="menu.confirm.hide"/>');" class="all_hover_but">
                                                    <s:text name="menu.status.hide"/>
                                                </a>                                            
                                        </s:if>
                                        <s:else>
                                            <td><s:text name="menu.status.hide"/></td>
                                            <td>
                                                <a href="javascript:confirmHideOrDisplay('<s:url action="hideOrDisplay"/>','<s:property value="#menu.menuCode"/>','0','<s:text name="menu.confirm.display"/>');" class="all_hover_but">
                                                    <s:text name="menu.status.display"/>
                                                </a>
                                        </s:else>
                                            <a href="<s:url action="updateInput"><s:param name="menuCode" value="#menu.menuCode"/></s:url>" class="all_hover_but">
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
