<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="softwareupgrade.management"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/software_upgrade/su.js'/>"></script>
    </head>

    <body>
        <form action="<s:url action='queryList'/>">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="<s:url action='queryList'/>" class="all_menu_a"><s:text name="softwareupgrade.management.list"/></a>
                        <a href="<s:url action='addInput'/>"><s:text name="softwareupgrade.management.add"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                   
                            <label><s:text name="softwareupgrade.devicetype" /></label>
                            <div class="select_div">
                                <select name="suTarget.deviceType.deviceType">
                                    <option value=""><s:text name="common.title.all"/></option>
                                    <s:iterator value="#request.deviceTypes" var="dt">
                                        <option value="<s:property value='#dt.deviceType'/>" <s:if test="suTarget.deviceType.deviceType == #dt.deviceType">selected</s:if>>
                                        	<s:property value='#dt.deviceName'/>
                                        </option>
                                    </s:iterator>                               
                                </select>
                            </div>
                            <label><s:text name="softwareupgrade.status" /></label>
                            <div class="select_div">
                                <select name="softwareUpgrade.status">
                                    <option value=""><s:text name="common.title.all"/></option>
                                    <option value="0" <s:if test="softwareUpgrade.status != '' && softwareUpgrade.status == 0">selected</s:if>>
                                        <s:text name="softwareupgrade.status.applied" />
                                    </option>
                                    <option value="1" <s:if test="softwareUpgrade.status != '' && softwareUpgrade.status == 1">selected</s:if>>
                                        <s:text name="softwareupgrade.status.approved" />
                                    </option>
                                    <option value="2" <s:if test="softwareUpgrade.status != '' && softwareUpgrade.status == 2">selected</s:if>>
                                        <s:text name="softwareupgrade.status.refused" />
                                    </option>
                                    <option value="3" <s:if test="softwareUpgrade.status != '' && softwareUpgrade.status == 3">selected</s:if>>
                                        <s:text name="softwareupgrade.status.publishing" />
                                    </option>
                                    <option value="4" <s:if test="softwareUpgrade.status != '' && softwareUpgrade.status == 4">selected</s:if>>
                                        <s:text name="softwareupgrade.status.published" />
                                    </option>
                                </select>
                            </div>                           
                            <label><s:text name="softwareupgrade.name"/></label>
                            <input type="text" maxlength="20" name="softwareUpgrade.softwareName" value="<s:property value='softwareUpgrade.softwareName'/>" class="all_tab_top_input"/>                    
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th width="80"><s:text name="softwareupgrade.code"/></th>
                                    <th><s:text name="softwareupgrade.name"/></th>
                                    <th width="80"><s:text name="softwareupgrade.version"/></th>
                                    <th width="140"><s:text name="common.action.createdtime"/></th>
                                    <th width="140"><s:text name="common.action.approvedtime"/></th>
                                    <th width="140"><s:text name="common.action.publishedtime"/></th>
                                    <th width="60"><s:text name="softwareupgrade.status"/></th>
                                    <th width="320"><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="software">
                                    <tr>
                                        <td>
                                            <a href="<s:url action="viewDetail"><s:param name="softwareId" value="#software.softwareId"/></s:url>">
                                                <s:property value="#software.softwareCode"/>
                                            </a>
                                        </td>
                                        <td class="text_left" title="<s:property value='#software.softwareName'/>">                                            
                                        	<div class="all_tb_content all_tb_ct1"><s:property value="#software.softwareName"/></div>                                           
                                        </td>
                                        <td class="text_left"><s:property value="#software.versionName"/></td>
                                        <td><s:date name="#software.applyedTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><s:date name="#software.approvedTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><s:date name="#software.publishedTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                                        <s:if test="#software.status == 0">
                                            <td><s:text name="softwareupgrade.status.applied"/></td>
                                            <td>
                                                <a href="javascript:confirmApprove('<s:url action="approve"/>','<s:property value="#software.softwareId"/>')" class="all_hover_but">
                                                    <s:text name="common.element.action.approve"/>
                                                </a>
                                                <a href="#" class="all_hover_but opacity">
                                                    <s:text name="common.element.action.publish"/>
                                                </a>
                                                <!-- <a href="#" class="all_hover_but opacity">
                                                    <s:text name="softwareupgrade.notify.upgrade"/>
                                                </a> -->
                                                <a href="javascript:confirmAction('<s:url action='remove'/>','<s:property value="#software.softwareId"/>','<s:text name="common.confirm.remove"/>')" class="all_hover_but">
                                                    <s:text name="common.element.action.remove"/>
                                                </a>
                                        </s:if>
                                        <s:elseif test="#software.status == 1">
                                            <td><s:text name="softwareupgrade.status.approved"/></td>
                                            <td>
                                                <a href="#" class="all_hover_but opacity">
                                                    <s:text name="common.element.action.approve"/>
                                                </a>
                                                <a href="javascript:confirmPublish('<s:url action="publish"/>','<s:property value="#software.softwareId"/>','false')" class="notetitle all_hover_but">
                                                    <s:text name="common.element.action.publish"/>
                                                </a>
                                                <!-- <a href="#" class="all_hover_but opacity">
                                                    <s:text name="softwareupgrade.notify.upgrade"/>
                                                </a> -->
                                                <a href="javascript:confirmAction('<s:url action='remove'/>','<s:property value="#software.softwareId"/>','<s:text name="common.confirm.remove"/>')" class="all_hover_but">
                                                    <s:text name="common.element.action.remove"/>
                                                </a>
                                        </s:elseif>
                                        <s:elseif test="#software.status == 2">
                                            <td><s:text name="softwareupgrade.status.refused"/></td>
                                            <td>
                                                <a href="#" class="all_hover_but opacity">
                                                    <s:text name="common.element.action.approve"/>
                                                </a>
                                                <a href="#" class="all_hover_but opacity">
                                                    <s:text name="common.element.action.publish"/>
                                                </a>
                                                <!-- <a href="#" class="all_hover_but opacity">
                                                    <s:text name="softwareupgrade.notify.upgrade"/>
                                                </a> -->
                                                <a href="javascript:confirmAction('<s:url action='remove'/>','<s:property value="#software.softwareId"/>','<s:text name="common.confirm.remove"/>')" class="all_hover_but">
                                                    <s:text name="common.element.action.remove"/>
                                                </a>
                                        </s:elseif>
                                        <s:elseif test="#software.status == 3">
                                            <td><s:text name="softwareupgrade.status.publishing"/></td>
                                            <td>
                                                <a href="#" class="all_hover_but opacity">
                                                    <s:text name="common.element.action.approve"/>
                                                </a>
                                                <a href="#" class="all_hover_but opacity">
                                                    <s:text name="common.element.action.publish"/>
                                                </a>
                                                <!-- <a href="#" class="all_hover_but opacity">
                                                    <s:text name="softwareupgrade.notify.upgrade"/>
                                                </a> -->
                                                <a href="javascript:confirmAction('<s:url action='remove'/>','<s:property value="#software.softwareId"/>','<s:text name="common.confirm.remove"/>')" class="all_hover_but">
                                                    <s:text name="common.element.action.remove"/>
                                                </a>
                                        </s:elseif>                                        
                                        <s:else>
                                            <td>
                                                <s:if test="#software.status == 4"><s:text name="softwareupgrade.status.published"/></s:if>
                                                <s:else><s:text name="softwareupgrade.status.disabled"/></s:else>
                                            </td>
                                            <td>
                                                <a href="#" class="all_hover_but opacity">
                                                    <s:text name="common.element.action.approve"/>
                                                </a>
                                                <a href="#" class="all_hover_but opacity">
                                                    <s:text name="common.element.action.publish"/>
                                                </a>
                                                <!-- 
                                                <s:if test="#software.status == 4">
                                                    <a href="javascript:confirmNotify('<s:url action="notifyUpgrade"/>','<s:property value="#software.softwareId"/>')" class="all_hover_but">
                                                        <s:text name="softwareupgrade.notify.upgrade"/>
                                                    </a>
                                                </s:if>
                                                <s:else>
                                                    <a href="#" class="all_hover_but opacity">
                                                        <s:text name="softwareupgrade.notify.upgrade"/>
                                                    </a>
                                                </s:else> -->
                                                <a href="#" class="all_hover_but opacity">
                                                    <s:text name="common.element.action.remove"/>
                                                </a>
                                        </s:else>
                                        <!-- <s:if test="#software.status == 4">                                                                       
                                            <a href="<s:url action='upgradeInput'><s:param name='softwareId' value='#software.softwareId'/></s:url>" class="all_hover_but">
                                            	<s:text name="common.element.action.upgrade"/>
                                            </a>
                                        </s:if> -->
                                            <a href="<s:property value='%{#request.downloadContextPath}'/><s:property value='#software.savePath'/>" class="all_hover_but">
                                                <s:text name="common.element.action.download"/>
                                            </a>
                                        </td>
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <s:include value="/views/common/paging.jsp"/>
                        <s:if test="promptFlag == true">                                       
                            <div class="layer" id="promptFrame">
                                <div>
                                    <p><s:text name="common.system.info"/></p>
                                    <ul>
                                        <li>
                                            <s:text name="common.element.action.operationSuccess" />
                                            <br/><br/>
                                        </li>
                                        <li>
                                            <a href="javascript:hideTip('promptFrame');"><s:text name="common.element.action.confirm"/></a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </s:if>
                        <div class="layer" id="approveFrame" style="display:none;">
                            <div>
                                <p><s:text name="softwareupgrade.approve.title"/></p>
                                <ul>
                                    <li>
                                        <s:text name="softwareupgrade.approve"/>
                                        <select id="approveFlag" name="approveFlag">                                                
                                        	<option value="true">
                                            	<s:text name="softwareupgrade.approve.agree"/>
                                            </option>
                                            <option value="false">
                                            	<s:text name="softwareupgrade.approve.disagree"/>
                                            </option>                               
                                    	</select>
                                    </li>
                                    <br/>
                                    <li>
                                        <a id="approveUrl" href="#">
                                            <s:text name="common.element.action.confirm"/>
                                        </a>
                                        <a href="javascript:hideTip('approveFrame');"><s:text name="common.element.action.cancel"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="layer" id="list_click" style="display:none;">
                            <div>
                                <p><s:text name="softwareupgrade.publish"/></p>
                                <ul>
                                    <li>
                                        <strong id="immediateMsg"><s:text name="common.title.publish.immediate"/><input type="hidden" id="timingFlag" name="timingFlag"/></strong>
                                        <strong id="timingMsg" class="none"><label><dfn></dfn><s:text name="common.title.publish.date"/></label><input type="text" id="publishedTime" name="publishedTime" dateFormat="yyyy-MM-dd hh:mm" onblur="$('#errorMsg').hide();" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" onchange="validatePublishTime()" readonly="true" /></strong>
                                    </li>
                                    <li id="errorMsg" style="display:none;"><s:text name="error.required"/></li>
                                    <li id="notGreaterCurrTimeMsg" style="display:none;"><s:text name="error.publishtime.notgreater"/></li>
                                    <li><i class="list_click_but" onclick="changeTimingFlag()"><s:text name="common.action.publish.mode"/></i></li>
                                    <li>
                                        <a id="publishUrl" href="#">
                                            <s:text name="common.element.action.confirm"/>
                                        </a>
                                        <a href="#" class="closediv"><s:text name="common.element.action.cancel"/></a>
                                    </li>
                                </ul>                               
                            </div>
                        </div>
                        <div class="layer" id="confirmFrame" style="display:none;">
                            <div>
                                <p id="confirmMsg"></p>
                                <ul>
                                    <li>
                                        <a id="targetUrl" href="#">
                                            <s:text name="common.element.action.confirm"/>
                                        </a>
                                        <a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.cancel"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="layer" id="notifyUpgradeFrame" style="display:none;">
                            <div>
                                <p><s:text name="softwareupgrade.notify.upgrade"/></p>
                                <ul>
                                    <li>
                                        <strong><label><dfn></dfn><s:text name="softwareupgrade.notify.upgrade.timing"/></label>
                                        <input type="text" id="upgradedTime" name="upgradedTime" dateFormat="yyyy-MM-dd hh:mm" onblur="$('#notifyErrorMsg').hide();" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" onchange="validateUpgradedTime()" readonly="true" />
                                        </strong>
                                    </li>
                                    <li id="notifyErrorMsg" style="display:none;"><s:text name="error.required"/></li>
                                    <li id="notifyNotGreaterCurrTimeMsg" style="display:none;"><s:text name="error.upgradetime.notgreater"/></li>
                                    <br/>                                    
                                    <li>
                                        <a id="notifyUrl" href="#">
                                            <s:text name="common.element.action.confirm"/>
                                        </a>
                                        <a href="javascript:hideTip('notifyUpgradeFrame');"><s:text name="common.element.action.cancel"/></a>
                                    </li>
                                </ul>                               
                            </div>
                        </div>                                    
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
