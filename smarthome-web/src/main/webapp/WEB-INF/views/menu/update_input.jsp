<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="menu.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/menu/menu.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.textbox.js'/>"></script>
        <s:include value="/WEB-INF/views/common/include_common.jsp"></s:include>
    </head>
    
    <body>
        <form action="<s:url action="update"/>" method="post">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action="queryList"/>"><s:text name="menu.management.list"/></a>
                    <a href="<s:url action='updateInput'><s:param name='menuCode' value='menu.menuCode'/></s:url>" class="all_menu_a"><s:text name="menu.management.update"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="menu.code"/></td>
                                <td><input type="hidden" name="menu.menuCode" value="<s:property value='menu.menuCode'/>"/><s:property value="menu.menuCode"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="menu.parent.code"/></td>
                                <td><input type="hidden" name="menu.parentCode" value="<s:property value='menu.parentCode'/>"/><s:property value="menu.parentCode"/></td>
                            </tr>
                            <tr>
                                <td><s:text name="menu.name"/></td>
                                <td><s:property value="menu.menuName"/></td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="menu.url"/></td>
                                <td><input type="text" size="100" maxlength="150" name="menu.menuUrl" class="{required:true,maxlength:150}" value="<s:property value="menu.menuUrl"/>" /></td>
                            </tr>
                            <tr>
                                <td><s:text name="menu.status"/></td>
                                <td class="all_tbs_radio">
                                    <input type="radio" name="menu.status" value="0" <s:if test="menu.status != '' && menu.status == 0">checked</s:if>/><label><s:text name="menu.status.display"/></label>
                                    <input type="radio" name="menu.status" value="1" <s:if test="menu.status != '' && menu.status == 1">checked</s:if>/><label><s:text name="menu.status.hide"/></label>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top"><s:text name="menu.desc"/></td>
                                <td><textarea id="menuDesc" name="menu.menuDesc" class="{maxlength:100}"><s:property value="menu.menuDesc"/></textarea></td>
                            </tr>                            
                        </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="button" onclick="saveMenu()" value="<s:text name='common.element.action.update' />" /></div>
                     <s:if test="promptFlag == true">                                       
                        <div class="layer" id="confirmFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                    <li>
                                        <s:text name="common.element.action.operationSuccess"/><br/><br/>
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
            $(function() {
                $("#menuDesc").textbox({
                    maxLength: 100
                }); 
            });
        </script>
    </body>
</html>
