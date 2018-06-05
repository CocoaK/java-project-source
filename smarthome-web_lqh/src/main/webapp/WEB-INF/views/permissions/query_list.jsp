<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="permissions.management"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
    </head>

    <body>
        <form action="<s:url action='queryList'/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="<s:url action="queryList"/>" class="all_menu_a"><s:text name="permissions.management.list"/></a>
                        <a href="<s:url action='addInput'/>"><s:text name="permissions.management.add"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                   
                            <label><s:text name="permissions.status" /></label>
                            <div class="select_div">
                                <select name="status">
                                    <option value=""><s:text name="common.title.all"/></option>
                                    <option value="0" <s:if test="status != '' && status == 0">selected</s:if>>
                                        <s:text name="permissions.status.enabled" />
                                    </option>
                                    <option value="1" <s:if test="status != '' &&status == 1">selected</s:if>>
                                        <s:text name="permissions.status.disabled" />
                                    </option>
                                </select>
                            </div>
                            <label class="all_border_right"><s:text name="permissions.name"/></label>
                            <input type="text" maxlength="20" name="roleName" value="<s:property value='roleName'/>" class="all_tab_top_input"/>                    
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th width="20%"><s:text name="permissions.name"/></th>
                                    <th width="60%"><s:text name="permissions.desc"/></th>
                                    <th width="10%"><s:text name="permissions.status"/></th>
                                    <th width="10%"><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="role">
                                    <tr>
                                        <td class="text_left">
                                            <a href="<s:url action="viewDetail"><s:param name="currRoleCode" value="#role.roleCode"/></s:url>">
                                                <s:property value="#role.roleName"/>
                                            </a>
                                        </td>
                                        <td class="text_left"><s:property value="#role.roleDesc"/></td>
                                        <s:if test="#role.status == 0">
                                            <td><s:text name="permissions.status.enabled"/></td>
                                        </s:if>
                                        <s:else>
                                            <td><s:text name="permissions.status.disabled"/></td>
                                        </s:else>                                
                                        <td>
                                            <a href="<s:url action="updateInput"><s:param name="currRoleCode" value="#role.roleCode"/></s:url>" class="all_hover_but">
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
    </body>
</html>
