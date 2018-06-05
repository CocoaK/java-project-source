<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="fileupload.log.management"/></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>

</head>

<body>
	<form action="<s:url action="fileUploadLogList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="<s:url action="operationLogList" namespace='/log'/>"><s:text name="operationlog.management.list"/></a>
                        <a href="<s:url action="clientLogList" namespace='/log'/>"><s:text name="client.log.list"/></a>
                        <a href="<s:url action="fileUploadLogList" namespace='/log' />" class="all_menu_a"><s:text name="fileupload.log.management.list"/></a>
                        <a href="<s:url action="diviceRegeditLogList" namespace='/log' />"><s:text name="deviceregedit.log.management.list"/></a>
                        <a href="<s:url action="appDataLogList" namespace='/log' />" ><s:text name="appdata.log.management.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                            <label class="all_border_right"><s:text name="fileupload.log.source"/></label>
                            <input type="text" name="fileUploadLog.source" maxlength="10" value="${fileUploadLog.source}" class="all_tab_top_input"/>
                            <label class="all_border_right"><s:text name="fileupload.log.name"/></label>
                            <input type="text" name="fileUploadLog.name" value="${fileUploadLog.name}" maxlength="50" class="all_tab_top_input"/>
                            <label class="all_border_right"><s:text name="fileupload.log.format"/></label>
                            <input type="text" name="fileUploadLog.format" value="${fileUploadLog.format}" maxlength="20" class="all_tab_top_input"/>  
                            <label><s:text name="client.log.add.time"/></label>
		                            <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="fileUploadLog.addStartTime" id="fileUploadLog.addStartTime" value="<s:date name="fileUploadLog.addStartTime" format="yyyy-MM-dd HH:mm" />"/>
		                            <span><s:text name="common.element.title.to"/></span>
		                            <input type="text" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" readonly="readonly" class="date_input time_input" name="fileUploadLog.addEndTime" id="fileUploadLog.addEndTime" value="<s:date name="fileUploadLog.addEndTime" format="yyyy-MM-dd HH:mm" />"/>                 
                            <input type="submit" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="fileupload.log.source"/></th>
                                    <th><s:text name="fileupload.log.name"/></th>
                                    <th><s:text name="fileupload.log.format"/></th>
                                    <th><s:text name="fileupload.log.size"/></th>
                                    <th><s:text name="operationlog.ip"/></th>
                                    <th><s:text name="client.log.add.time"/></th>
                                     <th><s:text name="client.log.result"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="fileUploadLog">
                                    <tr>
                                        <td><s:property value="#fileUploadLog.source"/></td>
                                        <td class="text_left"><s:property value="#fileUploadLog.name"/></td>
                                        <td><s:property value="#fileUploadLog.format"/></td>  
                                        <td class="text_left"><s:property value="#fileUploadLog.size"/></td>  
                                        <td><s:property value="#fileUploadLog.userIp"/></td>  
                                        <td><s:date name="#fileUploadLog.addTime" format="yyyy-MM-dd HH:mm:ss" /></td> 
                                        <td class="text_left">
                                           <s:if test="#fileUploadLog.exception==\"file_name_repeat\"">
							                  <s:text name="upload.file.name.repeat"/>
							               </s:if>
							               <s:elseif test="#fileUploadLog.exception==\"file_upload_sucess\"">
							               <s:text name="upload.file.sucess"/>
							               </s:elseif>
							               <s:elseif test="#fileUploadLog.exception==\"file_upload_failure\"">
							               <s:text name="upload.file.fail"/>
							               </s:elseif>
							               <s:elseif test="#fileUploadLog.exception==\"file_format_err\"">
							               <s:text name="upload.file.format.err"/>
							               </s:elseif>
							               <s:elseif test="#fileUploadLog.exception==\"file_download_fail\"">
							               <s:text name="download.file.fail"/>
							               </s:elseif>
							               <s:elseif test="#fileUploadLog.exception==\"file_downlaod_success\"">
							               <s:text name="download.file.sucess"/>
							               </s:elseif>
							               <s:elseif test="#fileUploadLog.exception==\"file_download_exception\"">
							               <s:text name="download.file.exception"/>
							               </s:elseif>
							               <s:else><s:property value="#fileUploadLog.exception"/></s:else>
                                            <!-- 
                                        	<a  href="<s:url action="fileUploadLogDetail"/>?requestId=<s:property value='#fileUploadLog.id'/>" class="all_hover_but"><s:text name="client.log.data.view"/></a>
                                            -->
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
