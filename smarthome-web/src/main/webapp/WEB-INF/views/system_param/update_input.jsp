<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="sysparam.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/system_param/sp.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.textbox.js'/>"></script>
        <s:include value="/WEB-INF/views/common/include_common.jsp"></s:include>
    </head>

    <body>
        <form action="<s:url action="update"/>" method="post">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action="queryList"/>"><s:text name="sysparam.management.list"/></a>
                    <a href="<s:url action='updateInput'><s:param name='paramCode' value='systemParam.paramCode'/></s:url>" class="all_menu_a"><s:text name="sysparam.management.update"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="sysparam.code"/></td>
                                <td><input type="hidden" name="systemParam.paramCode" value="<s:property value='systemParam.paramCode'/>"/><s:property value="systemParam.paramCode"/></td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="sysparam.name"/></td>
                                <td><input type="text" size="50" maxlength="50" name="systemParam.paramName" class="{required:true,maxlength:50}" value="<s:property value="systemParam.paramName"/>" /></td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="sysparam.value"/></td>
                                <td><input type="text" size="100" maxlength="250" name="systemParam.paramValue" class="{required:true,maxlength:250}" value="<s:property value="systemParam.paramValue"/>" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="sysparam.desc"/></td>
                                <td><textarea id="paramDesc" name="systemParam.paramDesc" class="{maxlength:100}"><s:property value="systemParam.paramDesc"/></textarea></td>
                            </tr>                         
                        </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="button" onclick="saveSp();" value="<s:text name='common.element.action.confirm.update' />" /></div>
                    <s:if test="successFlag == true">                                       
                        <div class="layer" id="confirmFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                    <li>
                                        <s:text name="common.update.success"/><br/><br/>
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
        		$("#paramDesc").textbox({
            		maxLength: 100
        		}); 
    		});
        </script>
    </body>
</html>
