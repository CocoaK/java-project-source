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
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery.textbox.js'/>"></script>
<jsp:include  page="../common/include_common.jsp"/>
</head>

<body>
  <form action="<s:url action='addPropertyComplaint' />" method="post">
  <input type="hidden" id="submitComplaint" name="submitComplaint" value="true" />
  <div class="main_section">
        <div class="all_aside">
            <div class="all_menu">
                <a href="<s:url action="queryPropertyComplaintList"/>" class="all_menu_b"><s:text name="customercomplaint.property.list" /></a>
                <a href="#" class="all_menu_a"><s:text name="customercomplaint.property.new" /></a>
                <a href="<s:url action="queryOwnerComplaintList"/>" class="all_menu_b"><s:text name="customercomplaint.owner.list" /></a>
            </div>
        </div>
        <div class="main_outside">
            <div class="all_inside">
                <table cellspacing="0" class="all_tab_body all_tab_bodys">
                    <tbody>
                        <tr>
                            <td width="120"><dfn></dfn><s:text name="customercomplaint.title" /></td>
                            <td><input type="text" name="complaint.title" maxlength="20" class="{required:true, maxlength:20}" /></td>
                        </tr>
                        <tr>
                            <td valign="top"><dfn></dfn><s:text name="customercomplaint.content" /></td>
                            <td><textarea name="complaint.content" id="remark" class="{required:true, maxlength:500}"></textarea></td>
                        </tr>
                    </tbody>
                </table>
                <div class="all_tab_butb">
                    <input type="submit" value="<s:text name="common.element.action.submit" />" />
                    <input type="button" value="<s:text name="common.element.action.draft" />"
                        onclick="$('#submitComplaint').val('false'); $('form').submit();"/>
                </div>
                <s:if test="successFlag == true">                                       
                    <div class="layer" id="confirmFrame">
                        <div>
                            <p><s:text name="common.system.info"/></p>
                            <ul>
                                <li>
                                    <s:text name="common.add.success"/>
                                </li>
                                <li>
                                    <a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.confirm"/></a>
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
        $(function() {
    		$("#remark").textbox({
        		maxLength: 500
    		}); 
		});
    </script>
</body>
</html>
