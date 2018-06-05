<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="页面修改"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
    </head>

    <body>
    <form action="<s:url action="update"/>" method="post">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                   	<a href="#" class="all_menu_a" >页面修改</a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td>页面名称</td>
                                <td>
                                	<input size="20" id="pageVO.name" maxlength="40" name="pageVO.name" value="<s:property value="pageVO.name"/>"/>
                                	<input type="hidden" id="id" maxlength="40" name="id" value="<s:property value="id"/>"/>
                                </td>
                            </tr>
                            <tr>
                                <td>页面描述</td>
                                <td><input size="20" id="pageVO.pageDesc" maxlength="40" name="pageVO.pageDesc" value="<s:property value="pageVO.pageDesc"/>"/></td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="submit" value="<s:text name='common.element.action.update'/>"/></div>
                    <s:if test="successFlag == 'success'">                                       
                        <div class="layer" id="confirmFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                    <li>
                                        <s:text name="common.update.success"/>
                                    </li>
                                    <li>
                                        <a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.confirm"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </s:if>
                    <s:if test="successFlag == 'fail'">                                       
                        <div class="layer" id="confirmFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                    <li>
                                        <s:text name="common.element.action.operationfailed"/>
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
                                	<li>
                                        <s:text name="common.element.action.operationfailed"/>
                                    </li>
                                    <li>
                                        	名称重复
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
    	</script>
    </body>
</html>
