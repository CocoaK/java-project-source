<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="operationlog.management"/></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
<script type="text/javascript">
	function doSubmit(){
   		document.getElementById("pageNum").value=1;
   		document.forms[0].submit();
   	}
</script>
</head>

<body>
	<form action="<s:url action="operationLogList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="<s:url action="operationLogList" namespace='/log'/>" class="all_menu_a"><s:text name="operationlog.management.list"/></a>
                         <a href="<s:url action="clientLogList" namespace='/log'/>" ><s:text name="client.log.list"/></a>
                         <a href="<s:url action="fileUploadLogList" namespace='/log' />" ><s:text name="fileupload.log.management.list"/></a>
                         <a href="<s:url action="diviceRegeditLogList" namespace='/log' />"><s:text name="deviceregedit.log.management.list"/></a>
                         <a href="<s:url action="appDataLogList" namespace='/log' />"><s:text name="appdata.log.management.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                            
                            <!-- <label class="all_border_right"><s:text name="operationlog.logid"/></label>
                            <input type="text" maxlength="20" name="operationLog.logId" value="${operationLog.logId}" class="all_tab_top_input"/>  -->
                           	<label class="all_border_right"><s:text name="operationlog.optuser"/></label>
                            <input type="text" maxlength="40" name="operationLog.operateUser" value="${operationLog.operateUser}" class="all_tab_top_input"/>
                            <label><s:text name="operationlog.optresult"/></label>
	                        <div class="select_div">
		                    	<select name="operationLog.operateResult">
	                                <option value="0">
	                                	<s:text name="common.title.all"/>
	                                </option>
	                                <option value="1" <s:if test="operationLog.operateResult != '' && operationLog.operateResult == 1">selected</s:if>>
	                                   	<s:text name="operation.success" />
	                                </option>
	                                <option value="2" <s:if test="operationLog.operateResult != '' && operationLog.operateResult == 2">selected</s:if>>
	                                  	<s:text name="operation.fail" />
	                               	</option>
	                               	<option value="3" <s:if test="operationLog.operateResult != '' && operationLog.operateResult == 3">selected</s:if>>
	                                   	<s:text name="operation.error" />
	                              	</option>                          
	                            </select>
                            </div>
                            <div class="click_div">
                            <strong class="screening_show search_but"><s:text name="common.element.action.moreFilter"/></strong>
							<ul class="screening_body" style="display: none;">
								<li>
									<label class="all_border_right"><s:text name="common.page.begintime"/></label>
									<input type='text' name='operationLog.beginTime' value='<s:date name="operationLog.beginTime" format="yyyy-MM-dd" />' onclick='SelectDate(this,"yyyy-MM-dd",0,-150)' readonly='readonly' class="date_input time_input"/>
	                            </li>
	                            <li>
	                            	<label class="all_border_right"><s:text name="common.page.endtime"/></label>
	                            	<input type='text' name='operationLog.endTime' value='<s:date name="operationLog.endTime" format="yyyy-MM-dd" />' onclick='SelectDate(this,"yyyy-MM-dd",0,-150)' readonly='readonly' class="date_input time_input"/>     
                            	</li>
                            </ul>
                            </div>             
                            <input type="button" value="<s:text name='common.element.action.search' />" onclick="doSubmit()" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="operationlog.optuser"/></th>
                                    <th><s:text name="operationlog.menuname"/></th>
                                    <th><s:text name="operationlog.ip"/></th>
                                    <th><s:text name="operationlog.operationname"/></th>
                                    <th><s:text name="operationlog.opttime"/></th>
                                    <th><s:text name="operationlog.optresult"/></th>
                                    <th><s:text name="client.log.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="operationlog">
                                    <tr>
                                        <td><s:property value="#operationlog.operateUser"/></td>
                                        <td><s:property value="#operationlog.menuCode"/></td>
                                        <td><s:property value="#operationlog.ip"/></td>
                                        <td><s:property value="#operationlog.operationCode"/></td>  
                                        <td><s:date name="#operationlog.operateTime" format="yyyy-MM-dd HH:mm:ss"/></td>  
                                        <td>
                                        	<s:if test="#operationlog.operateResult==1"><s:text name="operation.success"/></s:if>
                                        	<s:if test="#operationlog.operateResult==2"><s:text name="operation.fail"/></s:if>
                                        	<s:if test="#operationlog.operateResult==3"><s:text name="operation.error"/></s:if>	
                                        </td>
                                        <td><a href="<s:url action="operationLogDetail"><s:param name="logId" value="#operationlog.logId"/></s:url>" class="all_hover_but"><s:text name="client.log.data.view"/></a></td>                        
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
