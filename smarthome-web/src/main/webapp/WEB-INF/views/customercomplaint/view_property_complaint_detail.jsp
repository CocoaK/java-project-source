<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="customercomplaint.property.list" /></title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>

</head>

<body>
  <div class="main_section">
        <div class="all_aside">
            <div class="all_menu">
                <a href="<s:url action="queryPropertyComplaintList"/>" class="all_menu_b"><s:text name="customercomplaint.property.list" /></a>
                <a href="<s:url action="addPropertyComplaintInput"/>"><s:text name="customercomplaint.property.new" /></a>
                <a href="<s:url action="queryOwnerComplaintList"/>" class="all_menu_b"><s:text name="customercomplaint.owner.list" /></a>
                <a href="#" class="all_menu_a"><s:text name="customercomplaint.detail" /></a>
            </div>
        </div>
        <div class="main_outside">
            <div class="all_inside">
                <table cellspacing="0" class="all_tab_body all_tab_bodys">
                    <thead>
                        <tr>
                            <th colspan="4" class="font_left"><s:text name="customercomplaint.complaintInfo" /></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td width="120"><s:text name="customercomplaint.title" /></td>
                            <td><s:property value="complaint.title"/></td>
                        </tr>
                        <tr>
                            <td valign="top"><s:text name="customercomplaint.content" /></td>
                            <td style="word-wrap:break-word;overflow:hidden"><s:property value="complaint.content"/></td>
                        </tr>
                        <tr>
                            <td><s:text name="customercomplaint.complaintLoginName" /></td>
                            <td><s:property value="complaint.complaintLogin.userName"/></td>
                        </tr>
                        <tr>
                            <td><s:text name="customercomplaint.complaintDate" /></td>
                            <td><s:date name="complaint.complaintDate" format="yyyy-MM-dd HH:mm:ss" /></td>
                        </tr>
                        <tr>
                            <td><s:text name="customercomplaint.location" /></td>
                            <td><s:property value="complaint.location"/></td>
                        </tr>
                    </tbody>
		            <s:if test="complaint.processingStatus == 1">
                    <thead>
                        <tr>
                            <th colspan="4" class="font_left"><s:text name="customercomplaint.processingInfo" /></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><s:text name="customercomplaint.processingLoginName" /></td>
                            <td><s:property value="complaint.processingLogin.userName"/></td>
                        </tr>
                        <tr>
                            <td><s:text name="customercomplaint.processingDate" /></td>
                            <td><s:date name="complaint.processingDate" format="yyyy-MM-dd HH:mm:ss" /></td>
                        </tr>
                        <tr>
                            <td><s:text name="customercomplaint.suggestion" /></td>
                            <td><s:property value="complaint.suggestion"/></td>
                        </tr>
                    </tbody>
		            </s:if>
                </table>
            </div>
        </div>
    </div>
    <script>
        QueryLoader.selectorPreload = "body";
        QueryLoader.init();
    </script>
</body>
</html>