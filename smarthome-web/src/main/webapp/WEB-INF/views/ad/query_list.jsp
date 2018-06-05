<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="ad.management"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/ad/ad.js'/>"></script>
    </head>

    <body>
        <form action="<s:url action='queryList'/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="<s:url action='queryList'/>" class="all_menu_a"><s:text name="ad.management.list"/></a>
                        <a href="<s:url action='addInput'/>"><s:text name="ad.management.add"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                   
                            <label><s:text name="ad.name"/></label>
                            <input type="text" maxlength="20" name="ad.adName" value="<s:property value='ad.adName'/>" class="all_tab_top_input"/>                            
                            <label><s:text name="ad.type" /></label>
                            <div class="select_div">
                                <select name="ad.adType.typeCode">
                                    <option value=""><s:text name="common.title.all"/></option>
                                    <s:iterator value="%{adTypes}" var="at">
                                        <option value="<s:property value='#at.typeCode'/>" <s:if test="ad.adType.typeCode == #at.typeCode">selected</s:if>>
                                            <s:property value="#at.typeName"/>
                                        </option>
                                    </s:iterator>                                                                   
                                </select>
                            </div>
                            <label><s:text name="ad.status" /></label>
                            <div class="select_div">
                                <select name="ad.status">
                                    <option value=""><s:text name="common.title.all"/></option>
                                    <option value="0" <s:if test="ad.status != '' && ad.status == 0">selected</s:if>>
                                        <s:text name="ad.status.applied" />
                                    </option>
                                    <option value="1" <s:if test="ad.status != '' && ad.status == 1">selected</s:if>>
                                        <s:text name="ad.status.publishing" />
                                    </option>
                                    <option value="2" <s:if test="ad.status != '' && ad.status == 2">selected</s:if>>
                                        <s:text name="ad.status.published" />
                                    </option>
                                    <option value="3" <s:if test="ad.status != '' && ad.status == 3">selected</s:if>>
                                        <s:text name="ad.status.running" />
                                    </option>
                                    <option value="4" <s:if test="ad.status != '' && ad.status == 4">selected</s:if>>
                                        <s:text name="ad.status.stopped" />
                                    </option>                               
                                </select>
                            </div>                                             
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th width="20%"><s:text name="ad.name"/></th>
                                    <th width="10%"><s:text name="ad.type"/></th>
                                    <th width="10%"><s:text name="ad.sys"/></th>
                                    <th width="6%"><s:text name="ad.status"/></th>                                   
                                    <th width="10%"><s:text name="ad.appliedtime"/></th>
                                    <th width="10%"><s:text name="ad.begintime"/></th>
                                    <th width="10%"><s:text name="ad.endtime"/></th>
                                    <th><s:text name="common.element.title.action"/></th>                                  
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="ad">
                                    <tr>
                                        <td class="text_left" title="<s:property value='#ad.adName'/>">
                                            <a href="<s:url action='viewDetail'><s:param name='adId' value='#ad.adId'/></s:url>">
                                                <div class="all_tb_content all_tb_ct1"><s:property value="#ad.adName"/></div>
                                            </a>
                                        </td>
                                        <td><s:property value="#ad.adType.typeName"/></td>
                                        <td><s:property value="#ad.adLocation.adSys.sysName"/></td>
                                        <td>
                                            <s:if test="#ad.status == 0">
                                                <s:text name="ad.status.applied"/>
                                            </s:if>
                                            <s:elseif test="#ad.status == 1">
                                                <s:text name="ad.status.publishing"/>
                                            </s:elseif>
                                            <s:elseif test="#ad.status == 2">
                                                <s:text name="ad.status.published"/>
                                            </s:elseif>
                                            <s:elseif test="#ad.status == 3">
                                                <s:text name="ad.status.running"/>
                                            </s:elseif>
                                            <s:else>
                                                <s:text name="ad.status.stopped"/>
                                            </s:else>
                                        </td>
                                        <td><s:date name="#ad.applyedTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td><s:date name="#ad.adBeginTime" format="yyyy-MM-dd HH:mm" /></td>
                                        <td><s:date name="#ad.adEndTime" format="yyyy-MM-dd HH:mm" /></td>
                                        <td>
                                            <s:if test="#ad.status != '' && #ad.status == 0">
                                                <a href="javascript:confirmPublish('<s:url action="publish"/>','<s:property value="#ad.adId"/>','false');" class="notetitle all_hover_but">
                                                    <s:text name="common.element.action.publish"/>
                                                </a>
                                                <a href="<s:url action='updateInput'><s:param name='adId' value='#ad.adId'/></s:url>" class="all_hover_but">
                                                    <s:text name="common.element.action.update"/>
                                                </a>
                                            </s:if>
                                            <s:else>
                                                <a href="#" class="all_hover_but opacity">
                                                    <s:text name="common.element.action.publish"/>
                                                </a>
                                                <a href="#" class="all_hover_but opacity">
                                                    <s:text name="common.element.action.update"/>
                                                </a>
                                            </s:else>
                                            
                                            <s:if test="#ad.status != '' && #ad.status == 3">
                                                <a href="javascript:confirmAction('<s:url action="stop"/>','<s:property value="#ad.adId"/>','<s:text name="ad.confirm.stop"/>');" class="all_hover_but">
                                                    <s:text name="common.element.action.stop"/>
                                                </a>
                                            </s:if>
                                            <s:elseif test="#ad.status != '' && #ad.status == 4">
                                                <a href="javascript:confirmAction('<s:url action="resume"/>','<s:property value="#ad.adId"/>','<s:text name="ad.confirm.resume"/>');" class="all_hover_but">
                                                    <s:text name="common.element.action.resume"/>
                                                </a>
                                            </s:elseif>
                                            <s:else>
                                                <a href="#" class="all_hover_but opacity">
                                                    <s:text name="common.element.action.stop"/>
                                                </a>
                                            </s:else>
                                            
                                            <a href="javascript:confirmAction('<s:url action='remove'/>','<s:property value="#ad.adId"/>','<s:text name="common.confirm.remove"/>')" class="all_hover_but">
                                                <s:text name="common.element.action.remove"/>
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
                        
                        <div class="layer" id="list_click" style="display:none;">
                            <div>
                                <p><s:text name="ad.publish"/></p>
                                <ul>
                                    <li>
                                        <strong id="immediateMsg"><s:text name="common.title.publish.immediate"/><input type="hidden" id="timingFlag" name="timingFlag"/></strong>
                                        <strong id="timingMsg" class="none"><label><s:text name="common.title.publish.date"/></label><input type="text" id="publishedTime" name="publishedTime" dateFormat="yyyy-MM-dd hh:mm" onblur="$('#errorMsg').hide();" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" onchange="validatePublishTime()" readonly="true"/></strong>                                       
                                    </li>
                                    <li id="errorMsg" style="display:none;"><s:text name="error.required"/></li>
                                    <li id="notGreaterCurrTimeMsg" style="display:none;"><s:text name="error.publishtime.notgreater"/></li>
                                    <li><i class="list_click_but" onclick="changeTimingFlag()"><s:text name="common.action.publish.mode"/></i></li>
                                    <li>
                                        <a id="publishUrl" href="#" >
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
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
