<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="customercomplaint.owner.list" /></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
</head>

<body>
    <form action="<s:url action="queryOwnersComplaintList"/>" method="post">
	<div class="main_section">
    	<div class="all_aside">
        	<div class="all_menu">
            	<a href="#" class="all_menu_a"><s:text name="customercomplaint.owner.list" /></a>
            	<a href="<s:url action="addOwnerComplaintInput"/>"><s:text name="customercomplaint.owner.new" /></a>
            </div>
        </div>
        <div class="main_outside">
            <div class="all_inside">
                <div class="all_tab_top">
                	<label><s:text name="customercomplaint.processingStatus" /></label>
                    <div class="select_div">
                        <select name="complaint.processingStatus">
                          <option value=""><s:text name="common.title.all"/></option>
                          <option value="0" <s:if test="complaint.processingStatus != '' && complaint.processingStatus == 0">selected="selected"</s:if>><s:text name="customercomplaint.processingStatus.pending" /></option>
                          <option value="1" <s:if test="complaint.processingStatus != '' && complaint.processingStatus == 1">selected="selected"</s:if>><s:text name="customercomplaint.processingStatus.processed" /></option>
                          <option value="2" <s:if test="complaint.processingStatus != '' && complaint.processingStatus == 2">selected="selected"</s:if>><s:text name="customercomplaint.processingStatus.uncommitted" /></option>
                        </select>
                    </div>
                    <label><s:text name="customercomplaint.title" /></label>
                    <input name="complaint.title" value="${param['complaint.title']}" maxlength="20" type="text" class="all_tab_top_input" />
                    <div class="click_div">
	                    <strong class="screening_show search_but"><s:text name="common.element.action.moreFilter" /></strong>
	                    <ul class="screening_body">
	                    	<li>
	                        	<label><s:text name="customercomplaint.complaintDate" /></label>
	                        	<input name="complaint.complaintDate" value="${param['complaint.complaintDate']}" type="text" onclick="SelectDate(this,'yyyy-MM-dd',0,-150)" readonly="readonly" class="date_input" />
	                            <span>-</span>
	                            <input name="endComplaintDate" value="${param['endComplaintDate']}" type="text" onclick="SelectDate(this,'yyyy-MM-dd',0,-150)" readonly="readonly" class="date_input" />
	                        </li>
	                        <li>
	                        	<label><s:text name="customercomplaint.processingDate" /></label>
	                        	<input name="complaint.processingDate" value="${param['complaint.processingDate']}" type="text" onclick="SelectDate(this,'yyyy-MM-dd',0,-150)" readonly="readonly" class="date_input" />
	                            <span>-</span>
	                            <input name="endProcessingDate" value="${param['endProcessingDate']}" type="text" onclick="SelectDate(this,'yyyy-MM-dd',0,-150)" readonly="readonly" class="date_input" />
	                        </li>
	                    </ul>
                    </div>
                    <input type="submit" onclick="queryPagingList();" value="<s:text name="common.element.action.search" />" class="search_but" />
                </div>
                <table cellspacing="0" class="all_tab_body">
                    <thead class="all_tab_thead">
                        <tr>
                            <th><s:text name="customercomplaint.title" /></th>
                            <th width="60"><s:text name="customercomplaint.processingStatus" /></th>
                            <th width="120"><s:text name="customercomplaint.complaintDate" /></th>
                            <th width="100"><s:text name="customercomplaint.complaintLoginName" /></th>
                            <th width="300"><s:text name="customercomplaint.location" /></th> 
                            <th width="120"><s:text name="customercomplaint.processingDate" /></th>
                            <th width="100"><s:text name="customercomplaint.processingLoginName" /></th>
                            <th width="150"><s:text name="common.element.title.action" /></th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="%{page.results}" var="complaint">
                            <tr>
                                <td  class="text_left">
                                    <a href="<s:url action="viewOwnersComplaintDetail"><s:param name="complaintId" value="#complaint.id"/></s:url>">
                                        <s:property value="#complaint.title"/>
                                    </a>
                                </td>
                                <td><s:property value="#complaint.processingStatusText"/></td>
                                <td><s:date name="#complaint.complaintDate" format="yyyy-MM-dd HH:mm:ss" /></td>
                                <td><s:property value="#complaint.complaintLogin.userName"/></td>
                                <td><s:property value="#complaint.location"/></td> 
                                <td><s:date name="#complaint.processingDate" format="yyyy-MM-dd HH:mm:ss" /></td>
                                <td><s:property value="#complaint.processingLogin.userName"/></td>
                                <td>
                                    <s:if test="#complaint.processingStatus == 2">
	                                    <a class="all_hover_but" href="<s:url action="updateOwnerComplaintInput"><s:param name="complaintId" value="#complaint.id"/></s:url>">
	                                        <s:text name="common.element.action.update" />
	                                    </a>
                                        <a href="javascript:remove('<s:url action='removeOwnerComplaint'></s:url>','<s:text name="common.confirm.remove"/>','<s:property value="#complaint.id"/>')" class="all_hover_but">
                                            <s:text name="common.element.action.remove"/>
                                        </a>
                                    </s:if>
                                    <s:else>
                                        <a class="all_hover_but opacity" href="#">
                                            <s:text name="common.element.action.update" />
                                        </a>
                                        <a class="all_hover_but opacity" href="#">
                                            <s:text name="common.element.action.remove" />
                                        </a>
                                    </s:else>
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <s:include value="/views/common/paging.jsp"/>
                <s:if test="successFlag == true">                                       
                    <div class="layer" id="promptFrame">
                        <div>
                            <p><s:text name="common.system.info"/></p>
                            <ul>
                                <li>
                                    <s:text name="common.element.action.operationSuccess" />
                                </li>
                                <li>
                                    <a href="javascript:$('form')[0].submit();"><s:text name="common.element.action.confirm"/></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </s:if>
                <div class="layer" id="removeFrame" style="display:none;">
                    <div>
                        <p id="removeMsg"></p>
                        <ul>
                            <li>
                                <a id="removeUrl" href="#"><s:text name="common.element.action.confirm"/></a>
                                <a href="javascript:hideTip('removeFrame');"><s:text name="common.element.action.cancel"/></a>
                            </li>
                        </ul>
                   </div>
                </div>
            </div>
        </div>
    </div>
    </form>
    <script>

    function remove(removeUrl,removeMsg,targetId) {
    	var targetFunc = "javascript:hideOrDisplay('" + removeUrl + "','" + targetId + "');";
    	$('#removeUrl').attr("href",targetFunc);
        //$('#removeUrl').attr("href",removeUrl);
        $('#removeMsg').text(removeMsg);
        $('#removeFrame').show();
    }
    function hideOrDisplay(targetUrl,targetId){
    	$($("form")[0]).attr("action",targetUrl);
    	$('#removeFrame').append("<input type='hidden' name='complaintId' value='" + targetId + "'/>");
    	$("form")[0].submit();
    }
    </script>
</body>
</html>
