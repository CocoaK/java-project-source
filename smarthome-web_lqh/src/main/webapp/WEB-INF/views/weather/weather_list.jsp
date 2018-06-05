<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="rss.management"/></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>
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
                        <a href="#" class="all_menu_a"><s:text name="rss.weather.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                        	<label class="all_border_right"><s:text name="rss.province"/></label>
                            <input type="text" maxlength="50" name="weatherVO.provinceName" value="${weatherVO.provinceName}" class="all_tab_top_input"/>
                            <label class="all_border_right"><s:text name="rss.city"/></label>
                            <input type="text" maxlength="50" name="weatherVO.cityName" value="${weatherVO.cityName}" class="all_tab_top_input"/>                         
	                       	<input type="button" value="<s:text name='common.element.action.search' />" onclick="doSubmit()" class="search_but" />
                                              
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="rss.province"/></th>
                                    <th><s:text name="rss.city"/></th>
                                    <th><s:text name="rss.server.url"/></th>
                                    <th><s:text name="rss.weather.date"/></th>
                                    <th><s:text name="rss.weather.info"/></th>
                                    <th><s:text name="common.element.action.status"/></th>
                                    <th><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="weather">
                                    <tr>
                                        <td>
                                        	<s:property value="#weather.provinceName"/>

                                       	</td>
                                        <td><s:property value="#weather.cityName"/></td>
                                        <td><s:property value="#weather.serviceUrl"/></td> 
                                        <td><s:date name="#weather.reportDate" format="yyyy-MM-dd"/></td>
                                        <td><s:property value="#weather.content"/></td>
                                        <td>
                                        	<s:if test="#weather.status==0">
                                        		<s:text name="rss.status.disable"/>
                                        	</s:if>
                                        	<s:if test="#weather.status==1">
                                        		<s:text name="rss.status.enable"/>
                                        	</s:if>
                                        </td>
                                        <td>
                                        	<a href="<s:url action="updateDetail"><s:param name="weatherVO.id" value="#weather.id"/></s:url>" class="all_hover_but">
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
