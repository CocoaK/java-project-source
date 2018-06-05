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
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>

</head>

<body>
	<form action="<s:url action="weatherUpdate"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="queryList"><s:text name="rss.weather.list"/></a>
                        <a href="#" class="all_menu_a"><s:text name="rss.weather.update"/></a>
                    </div>
                </div>
                <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120">
                                	<input type="hidden" id="weatherVO.Id" name="weatherVO.Id" value="<s:property value="weatherVO.Id"/>"/>
                                	<s:text name="rss.server.name"/>
                                </td>
                                <td><s:property value="weatherVO.provinceName"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="rss.server.url"/></td>
                                <td><s:property value="weatherVO.cityName"/></td>
                            </tr>
                            <tr>
                            	<td><s:text name="common.element.action.status"/></td>
                            	<td class="all_tbs_radio">
                                    <input type="radio" name="weatherVO.status" value="0" <s:if test="weatherVO.status == 0">checked</s:if>/><label><s:text name="rss.status.disable"/></label>
                                    <input type="radio" name="weatherVO.status" value="1" <s:if test="weatherVO.status == 1">checked</s:if>/><label><s:text name="rss.status.enable"/></label>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="submit" value="<s:text name='common.element.action.update'/>"/></div>
                    <s:if test="successFlag == true">                                       
                        <div class="layer" id="confirmFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                    <li>
                                        <s:text name="common.update.success"/>
                                    </li>
                                    <li>
                                        <a href="#" onclick="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.confirm"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </s:if>         
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
