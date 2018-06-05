<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="deviceregedit.log.management"/></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
                 <script type="text/javascript">
	function preview(oper) { 
		if (oper < 10){ 
			bdhtml=window.document.body.innerHTML;
			sprnstr="<!--startprint"+oper+"-->"; 
			eprnstr="<!--endprint"+oper+"-->";
			prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18);
			prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
			window.document.body.innerHTML=prnhtml; 
			window.print(); 
			window.document.body.innerHTML=bdhtml; 
			} else{ 
		window.print(); 
		} 
	} 
</script>
</head>

<body>
	<form action="<s:url action="diviceRegeditLogList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="<s:url action="operationLogList" namespace='/log'/>"><s:text name="operationlog.management.list"/></a>
                        <a href="<s:url action="clientLogList" namespace='/log'/>"><s:text name="client.log.list"/></a>
                        <a href="<s:url action="fileUploadLogList" namespace='/log' />"><s:text name="fileupload.log.management.list"/></a>
                        <a href="<s:url action="diviceRegeditLogList" namespace='/log' />" class="all_menu_a"><s:text name="deviceregedit.log.management.list"/></a>
                        <a href="<s:url action="appDataLogList" namespace='/log' />"><s:text name="appdata.log.management.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                            <label class="all_border_right"><s:text name="deviceregedit.log.name"/></label>
                            <input type="text" name="diviceRegeditLog.name" maxlength="50" value="${diviceRegeditLog.name}" class="all_tab_top_input"/>
                            <label class="all_border_right"><s:text name="deviceregedit.log.deviceno"/></label>
                            <input type="text" name="diviceRegeditLog.deviceNo" maxlength="30" value="${diviceRegeditLog.deviceNo}" class="all_tab_top_input"/>
                            <div class="click_div"><strong class="screening_show search_but"><s:text name="common.element.action.moreFilter"/></strong>
		                    <ul class="screening_body">
			                    <li>
			                    <label><s:text name="client.log.add.time"/></label>
		                            <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="diviceRegeditLog.addStartTime" id="diviceRegeditLog.addStartTime" value="<s:date name="diviceRegeditLog.addStartTime" format="yyyy-MM-dd HH:mm" />"/>
		                            <span><s:text name="common.element.title.to"/></span>
		                            <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="diviceRegeditLog.addEndTime" id="diviceRegeditLog.addEndTime" value="<s:date name="diviceRegeditLog.addEndTime" format="yyyy-MM-dd HH:mm" />"/>   
			                  </li>
			                    <li>
			                        <label class="all_border_right"><s:text name="deviceregedit.log.districtname"/></label>
                            		<input type="text" size="53" name="diviceRegeditLog.districtName" maxlength="20" value="${diviceRegeditLog.districtName}" class="all_tab_top_input"/>
			                    </li>
			                    <li>
			                        <label class="all_border_right"><s:text name="deviceregedit.log.regionname"/></label>
                            		<input type="text" size="53" name="diviceRegeditLog.regionName" maxlength="20" value="${diviceRegeditLog.regionName}" class="all_tab_top_input"/>
			                    </li>
			                    <li>
			                        <label class="all_border_right"><s:text name="deviceregedit.log.buildingname"/></label>
                            		<input type="text" size="53" name="diviceRegeditLog.buildingName" maxlength="20" value="${diviceRegeditLog.buildingName}" class="all_tab_top_input"/>
			                    </li>
			                    <li>
			                        <label class="all_border_right"><s:text name="deviceregedit.log.cellname"/></label>
                            		<input type="text" size="53" name="diviceRegeditLog.cellName" maxlength="20" value="${diviceRegeditLog.cellName}" class="all_tab_top_input"/>
			                    </li>
			                    <li>
			                        <label class="all_border_right"><s:text name="deviceregedit.log.hourseno"/></label>
                            		<input type="text" size="53" name="diviceRegeditLog.hourseNo" maxlength="20" value="${diviceRegeditLog.hourseNo}" class="all_tab_top_input"/>
			                    </li>
		                    </ul>
		                    </div>
                            <input type="submit" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                         <!--startprint1-->
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="deviceregedit.log.name"/></th>
                                    <th><s:text name="deviceregedit.log.deviceno"/></th>
                                    <th><s:text name="deviceregedit.log.divecetype"/></th>
                                    <th><s:text name="deviceregedit.log.hourseno"/></th>
                                    <th><s:text name="deviceregedit.log.siteno"/></th>
                                    <th><s:text name="operationlog.ip"/></th>
                                    <th><s:text name="deviceregedit.log.location"/></th>
                                    <th><s:text name="deviceregedit.log.addtime"/></th>
                                    <th><s:text name="deviceregedit.log.action"/></th>
                                     <th><s:text name="client.log.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="diviceRegeditLog">
                                    <tr>
                                        <td><s:property value="#diviceRegeditLog.name"/></td>
                                        <td><s:property value="#diviceRegeditLog.deviceNo"/></td>
                                        <td><s:property value="#diviceRegeditLog.diveceType"/></td>  
                                        <td>
                                        <s:property value="#diviceRegeditLog.districtName"/>&nbsp;
                                        <s:property value="#diviceRegeditLog.regionName"/>&nbsp;
                                        <s:property value="#diviceRegeditLog.buildingName"/>&nbsp;
                                        <s:property value="#diviceRegeditLog.cellName"/>&nbsp;
                                        <s:property value="#diviceRegeditLog.hourseNo"/>
                                        </td>  
                                         <td><s:property value="#diviceRegeditLog.siteNo"/></td> 
                                        <td><s:property value="#diviceRegeditLog.userIp"/></td> 
                                        <td><s:property value="#diviceRegeditLog.location"/></td> 
                                        <td><s:date name="#diviceRegeditLog.addTime" format="yyyy-MM-dd HH:mm" /></td> 
                                        <td><s:property value="#diviceRegeditLog.eventAciton"/></td>
                                        <td>
                                        	<a  href="<s:url action="diviceRegeditLogDetail"/>?requestId=<s:property value='#diviceRegeditLog.id'/>" class="all_hover_but"><s:text name="client.log.data.view"/></a>
                                        </td>                                  
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <!--endprint1-->
                        <s:include value="/views/common/paging.jsp"/>
                        <s:if test="%{page.totalCount>0}">
		               	<!-- <div class="all_tab_butb"><input type="button" value="<s:text name="common.element.action.confirm.print"/>" onclick="preview(1)" /></div> -->
		               	</s:if>                                      
                    </div>
                </div>
            </div>
        </form>
</body>
</html>
