<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="device.message.list"/></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery_clorbox.js'/>" ></script>
	<script type="text/javascript">
		function checkHtml5(id,videoNetPath){
			//酷播播放器URL
			var htmlUrl = "<%=path%>/CuPlayer/htmlPlay.html?callRecordVO.id="+id+"&callRecordVO.videoNetPath="+videoNetPath;
	        //是否支持播放html5
	        var hasVideo = (document.createElement('video').canPlayType);
			if(!hasVideo){
				//window.open(htmlUrl,"newwindow", 
				//	"height=500, width=680, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");
				window.open("htmlPlay.action?callRecordVO.id="+id+"&callRecordVO.videoNetPath="+videoNetPath,"newwindow", 
					"height=500, width=680, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");
			}else{
				window.open("html5Play.action?callRecordVO.id="+id+"&callRecordVO.videoNetPath="+videoNetPath,"newwindow", 
					"height=500, width=680, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no");
			}
		}
    </script>
    <style type="text/css">
            #cboxNext,#cboxPrevious,#cboxCurrent,#cboxSlideshow{ display:none !important;}
        </style>
</head>

<body>
	<form action="<s:url action="messageList"/>" method="post">
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                    <a href="<s:url action="deviceList"/>"><s:text name="device.management.list"/></a>
                    <s:if test="userType==02">
                    	<a href="<s:url action="ipList"/>"><s:text name="device.ip.list"/></a>
                    </s:if>
                    <a href="#" class="all_menu_a"><s:text name="device.message.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                            <label class="all_border_right"><s:text name="device.devicecode"/></label>
                            <input type="text" maxlength="20" name="callRecordVO.device.deviceCode" value="${callRecordVO.device.deviceCode}" class="all_tab_top_input"/>
                            <input type="hidden" name="callRecordVO.device.deviceId" value="${callRecordVO.device.deviceId}" />
                            <label><s:text name="device.devicename"/></label>
                            <input type="text" maxlength="40" name="callRecordVO.device.deviceName" value="${callRecordVO.device.deviceName}" class="all_tab_top_input"/>
	                        <label><s:text name="device.message.type"/></label>
	                        <div class="select_div">
		                        <select name="callRecordVO.callType">
	                                <option value="">
	                                	<s:text name="common.title.all"/>
	                                </option>
	                               	<option value="0" <s:if test="callRecordVO.callType != '' && callRecordVO.callType == 0">selected</s:if>>
	                                	<s:text name="device.message.callout" />
	                                </option>
	                                <option value="1" <s:if test="callRecordVO.callType != '' && callRecordVO.callType == 1">selected</s:if>>
	                               		<s:text name="device.message.callin" />
	                                </option>
	                                <option value="2" <s:if test="callRecordVO.callType != '' && callRecordVO.callType == 2">selected</s:if>>
	                               		<s:text name="device.message.callin.pickup" />
	                                </option>
	                                <option value="3" <s:if test="callRecordVO.callType != '' && callRecordVO.callType == 3">selected</s:if>>
	                               		<s:text name="device.message.leftmsg" />
	                                </option>                               
	                            </select>
                            </div>
							<div class="click_div">
							<strong class="screening_show search_but"><s:text name="common.element.action.moreFilter"/></strong>
							<ul class="screening_body" style="display: none;">
								<li>
									<label><s:text name="device.message.caller"/></label>
									<input type="text" maxlength="40" name="callRecordVO.caller" value="${callRecordVO.caller}" class="all_tab_top_input"/>
								</li>
								<li>
									<label class="all_border_right"><s:text name="device.begintime"/></label>
									<input type='text' name='callRecordVO.beginTime' value='<s:date name="callRecordVO.beginTime" format="yyyy-MM-dd" />' onclick='SelectDate(this,"yyyy-MM-dd",0,-150)' readonly='readonly' class="date_input time_input"/>
	                            </li>
	                            <li>
	                            	<label class="all_border_right"><s:text name="device.endtime"/></label>
	                            	<input type='text' name='callRecordVO.endTime' value='<s:date name="callRecordVO.endTime" format="yyyy-MM-dd" />' onclick='SelectDate(this,"yyyy-MM-dd",0,-150)' readonly='readonly' class="date_input time_input"/>     
                            	</li>
                            </ul>
                            </div>
                            <input type="button" value="<s:text name='common.element.action.search' />" onclick="queryPagingList()" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="device.devicecode"/></th>
                                    <th><s:text name="device.devicename"/></th>
                                    <th><s:text name="device.message.type"/></th>
                                    <th><s:text name="device.message.caller"/></th>
                                    <th><s:text name="device.message.createdtime"/></th>
                                    <th><s:text name="device.message.talktime"/></th>
                                    <th><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="result">
                                    <tr>
                                        <td><s:property value="#result.device.deviceCode"/></td>
                                        <td><s:property value="#result.device.deviceName"/></td>
                                        <td>
                                        	<s:if test="#result.callType==0">
                                        		<s:text name="device.message.callout"/>
                                        	</s:if>
                                        	<s:if test="#result.callType==1">
                                        		<s:text name="device.message.callin"/>
                                        	</s:if>
                                        	<s:if test="#result.callType==2">
                                        		<s:text name="device.message.callin.pickup"/>
                                        	</s:if>
                                        	<s:if test="#result.callType==3">
                                        		<s:text name="device.message.leftmsg"/>
                                        	</s:if>
                                        </td>
                                        <td><s:property value="#result.caller"/></td>
                                        <td><s:date name="#result.callTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                                        <td><s:property value="#result.talkTime"/></td>
                                        <td>
											<s:if test="#result.pictureNetPath != '' && #result.pictureNetPath != null">
	     										<a href="<s:property value="appDownloadUrl"/><s:property value='#result.pictureNetPath'/>" rel="colorbox1" target="_blank" class="all_hover_but"><s:text name="device.message.pic.preview"/></a>
	               							</s:if>
		               						<s:else>
		                            			<i class="all_hover_but opacity"><s:text name="device.message.pic.preview"/></i>
		                          			</s:else>
	                                  		<s:if test="#result.videoNetPath != '' && #result.videoNetPath != null">
	                         					<a href="#" onclick="checkHtml5('<s:property value="#result.id"/>','<s:property value="appDownloadUrl"/><s:property value="#result.videoNetPath"/>')" class="all_hover_but"><s:text name="device.message.play"/></a>
	                                      		<a href="<s:property value="appDownloadUrl"/><s:property value='#result.videoNetPath'/>" class="all_hover_but"><s:text name="device.message.video.download"/></a>
	                                    	</s:if>
	                              			<s:else>
	                                 			<a href="#" class="all_hover_but opacity"><s:text name="device.message.play"/></a>
	                                 			<i class="all_hover_but opacity"><s:text name="device.message.video.download"/></i>
	                                  		</s:else>
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
		/*<![CDATA[*/
        		$(document).ready(function(){
					$("a[rel^='colorbox']").colorbox({title:"",slideshow:true,slideshowAuto:false,previous:"<s:text name='common.title.browse.previous'/>",next:"<s:text name='common.title.browse.next'/>",close:"<s:text name='common.element.action.close'/>",slideshowStart:"<s:text name='common.element.action.broadcast'/>",slideshowStop:"<s:text name='common.element.action.pause'/>",current:"<s:text name='common.title.browse.info'/>"})
				;});
    		/*]]>*/
	</script>
</body>
</html>
