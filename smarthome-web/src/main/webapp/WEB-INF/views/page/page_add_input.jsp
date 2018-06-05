<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="device.management.detail"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/user/user.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/ad/ad.js'/>"></script>
    </head>

    <body>
    <form action="<s:url action="add"/>" method="post">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                	<a href="<s:url action='queryList'/>" >页面列表</a>
                   	<a href="<s:url action='addInput'/>" class="all_menu_a" >添加页面</a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0" class="all_tab_body all_tab_bodyq">
                        <tbody>
                            <tr>
                                <td>页面名称</td>
                                <td><input size="20" id="pageVO.name" maxlength="40" name="pageVO.name" /></td>
                            </tr>
                            <tr>
                                <td>页面描述</td>
                                <td><input size="20" id="pageVO.pageDesc" maxlength="40" name="pageVO.pageDesc" /></td>
                            </tr>
                            <tr>
                                <td>所属小区</td>
                                <td onclick="validateGroups()">
                                	<input type="hidden" id="groupIds" name="groupIds" />
                                	<s:include value="/WEB-INF/views/common/group_tree.jsp"><s:param name="viewResultFlag" value="1"/></s:include>
                                	<p class="hr_error">
                    					<label id="unselectedGroupMsg" class="error" style="display:none;"><s:text name="error.required" /></label>
									</p>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <!-- <s:include value="/WEB-INF/views/device/include_select_district_msg.jsp"/>  -->
                    <div class="all_tab_butb"><input type="button" onclick="savePage();" value="保存" /></div>                
                </div>
            </div>
        </div>
        
        			<s:if test="successFlag == 'success'">
        			<div class="layer" id="confirmFrame">
                    	<div>
                            <p><s:text name="common.system.info"/></p>
                            <ul>
                                <li>
                                   	 操作成功
                                </li>
                                <li>
                                    <a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.confirm"/></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    </s:if>
                    <s:if test="successFlag == 'repeat_name'">                                       
                        <div class="layer" id="confirmFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                	<li> <s:text name="common.element.action.operationfailed"/></li>
                                    <li>名称重复</li>
                                    <li>
                                        <a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.confirm"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </s:if>              
                
	</form>
	<script>
		function savePage(){
			var validateFlag = 0;

			if(validateFlag == 0){
				$("#groupIds").val(showsel());
				$("form")[0].submit();
			}	
		}
	</script>
    </body>
</html>
