<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="softwareupgrade.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery.textbox.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/software_upgrade/su.js'/>"></script>
        <s:include value="/WEB-INF/views/common/include_common.jsp"></s:include>
        <script type="text/javascript">            
            $(function() {
                $("#softwareDesc").textbox({
                    maxLength: 100
                }); 
            }); 
		</script>
    </head>

    <body>
        <form action="<s:url action='save'/>" method="post" enctype="multipart/form-data">
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action='queryList'/>"><s:text name="softwareupgrade.management.list"/></a>
                    <a href="<s:url action='addInput'/>" class="all_menu_a"><s:text name="softwareupgrade.management.add"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0" class="all_tab_body all_tab_bodyq">
                        <tbody>
                            <tr>
                                <td width="120"><dfn></dfn><s:text name="softwareupgrade.name"/></td>
                                <td>
                                    <input type="text" size="40" maxlength="20" name="softwareUpgrade.softwareName" class="{required:true,maxlength:20}" value="<s:property value='softwareUpgrade.softwareName'/>" />
                                </td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="softwareupgrade.version"/></td>
                                <td>
                                    <input type="text" size="40" maxlength="30" name="softwareUpgrade.versionName" class="{required:true,maxlength:30}" value="<s:property value='softwareUpgrade.versionName'/>" />
                                </td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="softwareupgrade.upload"/></td>
                                <td class="up_img">
                                    <div>
                                        <input type="text" name="softwareFileName" id="softwareFileName" class="viewfile" size="50" readonly/>                                    
                                        <label for="upload" class="all_hover_but file_label"><s:text name="common.element.action.select"/></label>
                                        <input type="file" name="softwareFile" onchange="selectSoftware(this.value);hideTip('errorFileSizeMsg');" class="file" />
                                        <label id="fileErrorMsg" class="error" style="display:none;"></label>                                    
                                        <s:if test="errorFlag == true">
                                            <label id="errorFileSizeMsg" class="error">
                                                <s:if test="softwareFileErrorMsg != null && softwareFileErrorMsg != ''">
                                                    <s:text name="error.file.too.large"/>
                                                </s:if>
                                                <s:fielderror><s:param>softwareFile</s:param></s:fielderror>
                                            </label>
                                        </s:if>
                                    </div>
                                    <p>
                                        <i><s:text name='common.title.file.msg'><s:param><s:property value='#request.fileExt'/></s:param><s:param>100M</s:param></s:text></i>
                                    </p>                                   
                                </td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="common.title.select.district" /><input type="hidden"
                                    id="groupIds" name="groupIds" />
                                </td>
                                <td onclick="validateDistricts()"><s:include
                                        value="/WEB-INF/views/common/group_tree.jsp" />
                                    <p class="hr_error">
                                        <label id="unselectedGroupMsg" class="error"
                                            style="display:none;"><s:text
                                                name="error.required" />
                                        </label>
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td><dfn></dfn><s:text name="softwareupgrade.devicetype"/></td>
                                <td valign="top">
                                    <ul class="all_tbs_checkbox">
                                        <s:iterator value="%{deviceTypes}" var="dt" status="st">
                                        	<!-- 目前只显示门口机和围墙机 -->
                                        	<s:if test="#dt.deviceType=='02' or #dt.deviceType=='06'">
	                                            <li>
	                                                <input type="checkbox" name="deviceTypes.deviceType" onchange="validateDeviceTypes()" value="<s:property value='#dt.deviceType'/>" />
	                                                <label><s:text name='#dt.deviceName' /></label>
	                                            </li>
                                            </s:if>
                                        </s:iterator>
                                    </ul>
                                    <p class="hr_error">
                                        <label id="unselectedDeviceTypeMsg" class="error" style="display:none;"><s:text name="error.required" /></label>
                                    </p>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top"><s:text name="softwareupgrade.desc"/></td>
                                <td><textarea id="softwareDesc" name="softwareUpgrade.softwareDesc" class="{maxlength:100}"><s:property value="softwareUpgrade.softwareDesc"/></textarea></td>
                            </tr>                                                                                                              
                        </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="button" id="save" onclick="saveSoftware();" value="<s:text name='common.element.action.add' />" /></div>
                    <s:include value="include_save_common.jsp"></s:include>                   
                </div>
            </div>
        </div>
        </form>
    </body>
</html>

