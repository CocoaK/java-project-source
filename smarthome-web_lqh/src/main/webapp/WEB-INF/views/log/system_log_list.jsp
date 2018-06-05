<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="systemlog.management"/></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>

</head>

<body>
	<form action="<s:url action="systemLogList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="systemLogList.action" class="all_menu_a"><s:text name="systemlog.management.list"/></a>
                        <a href="operationLogList.action" class="all_menu_b"><s:text name="operationlog.management.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                            <label class="all_border_right"><s:text name="systemlog.logid"/></label>
                            <input type="text" name="logId" value="${param.logId}" class="search_tex"/>
                            <label class="all_border_right"><s:text name="systemlog.optuser"/></label>
                            <input type="text" name="operateUser" value="${param.operateUser}" class="search_tex"/>
                            <label class="all_border_right"><s:text name="systemlog.optcode"/></label>
                            <input type="text" name="oprateCode" value="${param.operateCode}" class="search_tex"/>                   
                            <input type="submit" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="systemlog.logid"/></th>
                                    <th><s:text name="systemlog.optuser"/></th>
                                    <th><s:text name="systemlog.menucode"/></th>
                                    <th><s:text name="systemlog.optcode"/></th>
                                    <th><s:text name="systemlog.opttime"/></th>
                                    <th><s:text name="systemlog.errlocation"/></th>
                                    <th><s:text name="systemlog.errmsg"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="systemlog">
                                    <tr>
                                        <td>
                                            <a href="<s:url action="systemLogDetail"><s:param name="logId" value="#systemlog.logId"/></s:url>">
                                                <s:property value="#systemlog.logId"/>
                                            </a>
                                        </td>
                                        <td><s:property value="#systemlog.operateUser"/></td>
                                        <td><s:property value="#systemlog.menuCode"/></td>
                                        <td><s:property value="#systemlog.operationCode"/></td>  
                                        <td><s:property value="#systemlog.operateTime"/></td>  
                                        <td><s:property value="#systemlog.errorLocation"/></td>  
                                        <td><s:property value="#systemlog.errorMsg"/></td>                                
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
