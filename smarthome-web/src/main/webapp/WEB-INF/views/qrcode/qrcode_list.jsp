<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>二维码列表</title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>

</head>

<body>
	<form action="<s:url action="queryList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="#" class="all_menu_a">二维码列表</a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th>二维码编号</th>
                                    <th>完整房号</th>
                                    <th>二维码用户名</th>
                                    <th>目标用户名</th>
                                    <th>类型</th>
                                    <th><s:text name="common.element.action.status"/></th>
                                    <th><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="qrcodeVOs" var="qrcodeVO">
                                    <tr>
                                        <td><s:property value="#qrcodeVO.id"/></td>
                                        <td><s:property value="#qrcodeVO.roomName"/></td>
                                        <td><s:property value="#qrcodeVO.sipUid"/></td>
                                        <td><s:property value="#qrcodeVO.doorSipid"/></td> 
                                        <td><s:property value="#qrcodeVO.type"/></td>
                                        <td>
                                        	<s:if test="#qrcodeVO.status==0">
                                        		无效
                                        	</s:if>
                                        	<s:if test="#qrcodeVO.status==1">
                                        		启用
                                        	</s:if>
                                        </td>
                                        <td>
                                        	<a href="<s:url action="printQrcode">
                                        			<s:param name="qrcodeVO.houseId" value="#qrcodeVO.houseId"/>
                                        			<s:param name="qrcodeVO.sipUid" value="#qrcodeVO.sipUid"/>
                                        			<s:param name="qrcodeVO.doorSipid" value="#qrcodeVO.doorSipid"/>
                                        			<s:param name="qrcodeVO.roomNo" value="#qrcodeVO.roomNo"/>
                                        			<s:param name="qrcodeVO.pwd" value="#qrcodeVO.pwd"/>
                                        		</s:url>" target="_blank" class="all_hover_but">
                                        		打印
                                        	</a>
                                        	<a href="<s:url action="deleteSip">
                                        			<s:param name="qrcodeVO.id" value="#qrcodeVO.id"/>
                                        			<s:param name="qrcodeVO.houseId" value="#qrcodeVO.houseId"/>
                                        			<s:param name="qrcodeVO.sipUid" value="#qrcodeVO.sipUid"/>
                                        		</s:url>" class="all_hover_but">
                                        		删除
                                        	</a>
                                        </td>                  
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <div class="all_tab_butb"><a href="<s:url action="queryHouseholdList" namespace='/housemgr'/>">返回房号列表</a></div>
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
