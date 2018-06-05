<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="rss.management"/></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/ad/ad.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
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
                        <a href="<s:url action="queryList"/>" class="all_menu_a"><s:text name="rss.server.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                            <label class="all_border_right"><s:text name="rss.server.name"/></label>
                            <input type="text" maxlength="40" name="rssServerVO.rssName" value="${rssServerVO.rssName}" class="all_tab_top_input"/> 
                            <label class="all_border_right"><s:text name="rss.server.status"/></label>
                            <div class="select_div">
		                    	<select name="rssServerVO.status">
	                            	<option value="">
	                            		<s:text name="common.title.all"/>
	                                </option>
	                                <option value="0" <s:if test="rssServerVO.status != '' && rssServerVO.status == 0">selected</s:if>>
	                                    <s:text name="rss.status.disable" />
	                                </option>
	                                <option value="1" <s:if test="rssServerVO.status != '' && rssServerVO.status == 1">selected</s:if>>
	                                	<s:text name="rss.status.enable" />
	                                </option>                               
	                            </select>
                            </div>                        
	                       	<input type="button" value="<s:text name='common.element.action.search' />" onclick="doSubmit()" class="search_but" />
                                              
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th width="200"><s:text name="rss.server.name"/></th>
                                    <th><s:text name="rss.server.url"/></th>
                                    <th width="50"><s:text name="common.element.action.status"/></th>
                                    <th width="140"><s:text name="common.action.createduser"/></th>
                                    <th width="140"><s:text name="common.action.createdtime"/></th>
                                    <th width="150"><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="rssServer">
                                    <tr>
                                        <td class="text_left">
                                        	<a href="<s:url action="weather/queryList"/>">
                                        		<s:property value="#rssServer.rssName"/>
                                        	</a>
                                       	</td>
                                        <td class="text_left" title="<s:property value="#rssServer.serverUrl"/>"><div class="all_tb_content all_tb_ct1"><s:property value="#rssServer.serverUrl"/></div></td>
                                        <td>
                                        	<s:if test="#rssServer.status==0">
                                        		<s:text name="rss.status.disable"/>
                                        	</s:if>
                                        	<s:if test="#rssServer.status==1">
                                        		<s:text name="rss.status.enable"/>
                                        	</s:if>
                                        </td>
                                        <td><s:property value="#rssServer.userLogin.userName"/></td> 
                                        <td><s:date name="#rssServer.createdTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td>
                                        	<a href="<s:url action="updateDetail"><s:param name="rssServerVO.rssId" value="#rssServer.rssId"/></s:url>" class="all_hover_but">
                                        		<s:text name="common.element.action.update"/>
                                        	</a>
                                        	<a href="javascript:confirmAction('<s:url action="rssRemove"><s:param name="rssServerVO.rssId" value="#rssServer.rssId"/></s:url>','<s:text name="common.confirm.remove"/>')" class="all_hover_but">
                                        		<s:text name="common.element.action.remove"/>
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
