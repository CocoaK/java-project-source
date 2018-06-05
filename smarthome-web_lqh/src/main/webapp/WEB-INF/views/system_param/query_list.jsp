<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="sysparam.management"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
    </head>

    <body>
        <form action="<s:url action="queryList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="<s:url action='queryList'/>" class="all_menu_a"><s:text name="sysparam.management.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                            <label class="all_border_right"><s:text name="sysparam.name"/></label>
                            <input type="text" maxlength="50" name="paramName" value="<s:property value='paramName'/>" class="all_tab_top_input"/>                    
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th width="20%"><s:text name="sysparam.code"/></th>
                                    <th width="20%"><s:text name="sysparam.name"/></th>
                                    <th width="50%"><s:text name="sysparam.value"/></th>
                                    <th><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="sysparam">
                                    <tr>
                                        <td class="text_left" title="<s:property value='#sysparam.paramCode'/>">
                                            <a href="<s:url action="viewDetail"><s:param name="paramCode" value="#sysparam.paramCode"/></s:url>">
                                                <div class="all_tb_content all_tb_ct1"><s:property value="#sysparam.paramCode"/></div>
                                            </a>
                                        </td>
                                        <td class="text_left" title="<s:property value='#sysparam.paramName'/>"><div class="all_tb_content all_tb_ct1"><s:property value="#sysparam.paramName"/></div></td>
                                        <td class="text_left" title="<s:property value='#sysparam.paramValue'/>"><div class="all_tb_content all_tb_ct1"><s:property value="#sysparam.paramValue"/></div></td>                                
                                        <td>
                                            <a href="<s:url action="updateInput"><s:param name="paramCode" value="#sysparam.paramCode"/></s:url>" class="all_hover_but">
                                                <s:text name="common.element.action.update"/>
                                            </a>
                                        </td>
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <s:include value="/views/common/paging.jsp"/>                                      
                    </div>
                </div>
            </div>
        </form>
        <script>
            QueryLoader.selectorPreload = "body";
            QueryLoader.init();                
        </script>
    </body>
</html>
