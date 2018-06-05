<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="user.management"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/user/user.js'/>"></script>
    </head>

    <body>
        <form action="<s:url action='queryList'/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <s:if test="#session.login.userType == '03'">
                            <a href="<s:url action='queryList' namespace='/saUser'/>"><s:text name="sauser.management.list"/></a>
                            <a href="<s:url action='addInput' namespace='/saUser'/>"><s:text name="sauser.management.add"/></a>
                        </s:if>
                        <s:if test="#session.login.userType == '02'">
                            <a href="<s:url action='viewPropertyCompanyDetail' namespace='/housemgr'/>"><s:text name="housemgr.propertyCompany.detail"/></a>
                            <a href="<s:url action="updatePropertyCompanyInput" namespace='/housemgr'/>"><s:text name="housemgr.propertyCompany.update"/></a>
                        </s:if>
                        <a href="<s:url action='queryList'/>" class="all_menu_a"><s:text name="pauser.management.list"/></a>
                        <a href="<s:url action="addInput"/>"><s:text name="pauser.management.add"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                                                                                                         
                            <s:if test="#session.login.userType == '03'">        
                                <label><s:text name="common.title.districtname" /></label>
                                <div class="select_div">
                                    <select name="user.districtId">
                                        <option value=""><s:text name="common.title.all"/></option>
                                        <s:iterator value="%{#request.allDistricts}" var="dist">
                                            <option value="<s:property value='#dist.id'/>" <s:if test="user.districtId == #dist.id">selected</s:if>>
                                                <s:property value='#dist.name'/>
                                            </option>
                                        </s:iterator>                               
                                    </select>
                                </div>
                            </s:if>
                            <label><s:text name="user.name"/></label>
                            <input type="text" maxlength="25" name="user.userName" value="<s:property value='user.userName'/>" class="all_tab_top_input"/>
                            <label><s:text name="common.title.idcard"/></label>
                            <input type="text" maxlength="30" name="user.idCard" value="<s:property value='user.idCard'/>" class="all_tab_top_input"/>
                            <label><s:text name="user.department"/></label>
                            <input type="text" maxlength="25" name="user.department" value="<s:property value='user.department'/>" class="all_tab_top_input"/>                    
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>                                   
                                    <th width="15%"><s:text name="user.name"/></th>
                                    <th width="15%"><s:text name="common.title.idcard"/></th>
                                    <th width="50"><s:text name="common.title.gender"/></th>
                                    <th><s:text name="user.department"/></th>
                                    <th><s:text name="user.post"/></th>
                                    <th width="10%"><s:text name="common.title.mobilephone"/></th>
                                    <th><s:text name="common.title.districtname"/></th>                                  
                                    <th width="200"><s:text name="common.element.title.action"/></th>                                  
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="user">
                                    <tr>
                                        <td>
                                            <a href="<s:url action="viewDetail"><s:param name="currUserId" value="#user.userId"/></s:url>">
                                                <s:property value="#user.userName"/>
                                            </a>
                                        </td>
                                        <td><s:property value="#user.idCard"/></td>
                                        <td>
                                            <s:if test="#user.gender != '' && #user.gender == 0">
                                                <s:text name="common.title.gender.male" />
                                            </s:if>
                                            <s:elseif test="#user.gender != '' && #user.gender == 1">
                                                <s:text name="common.title.gender.female" />
                                            </s:elseif>
                                        </td>
                                        <td><s:property value="#user.department"/></td>
                                        <td><s:property value="#user.post"/></td>                                       
                                        <td><s:property value="#user.mobilePhone"/></td>
                                        <td><s:property value="#user.districtName"/></td>
                                        <td>
                                            <a href="<s:url action="updateInput"><s:param name="currUserId" value="#user.userId"/></s:url>" class="all_hover_but">
                                                <s:text name="common.element.action.update"/>
                                            </a>
                                            <a id="<s:property value='#user.login.loginId'/>" href="javascript:confirmEnableOrDisable('<s:url action='enableOrDisable' namespace='/json/login'/>','<s:property value="#user.login.loginId"/>','<s:property value="#user.login.status"/>');" class="all_hover_but">
                                                <s:if test="#user.login.status == 0"><s:text name="common.element.action.disable"/></s:if><s:else><s:text name="common.element.action.enable"/></s:else>
                                            </a>                                           
                                            <a href="javascript:confirmResetPass('<s:url action='resetPass' namespace='/json/login'></s:url>','<s:property value="#user.login.loginName"/>','<s:text name="user.confirm.reset"/>');" class="all_hover_but">
                                                <s:text name="common.element.action.resetpass"/>
                                            </a>
                                        </td>                                       
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <s:include value="/views/common/paging.jsp"/>
                        <s:include value="/WEB-INF/views/user/include_query_list_msg.jsp"/>                                    
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
