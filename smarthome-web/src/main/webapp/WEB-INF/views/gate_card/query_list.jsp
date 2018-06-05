<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="gatecard.management"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/gatecard/gc.js'/>"></script>
    </head>

    <body>
        <form action="<s:url action='queryList'/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="<s:url action="queryList"/>" class="all_menu_a"><s:text name="gatecard.management.list"/></a>
                        <!--<a href="<s:url action='addInput'/>"><s:text name="gatecard.management.add"/></a>
                        
                        <a href="<s:url action="queryList" namespace='/gateCardVisit'/>"><s:text name="gatecard.management.gatecardvisit.list"/></a>
                        <a href="<s:url action="queryList" namespace='/idCardVisit'/>"><s:text name="gatecard.management.idcardvisit.list"/></a>
                    	 -->
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                   
                            <label><s:text name="gatecard.status" /></label>
                            <div class="select_div">
                                <select name="gateCard.status">
                                    <option value=""><s:text name="common.title.all"/></option>
                                    <option value="0" <s:if test="gateCard.status != '' && gateCard.status == 0">selected</s:if>>
                                        <s:text name="gatecard.status.enable" />
                                    </option>
                                    <option value="1" <s:if test="gateCard.status != '' && gateCard.status == 1">selected</s:if>>
                                        <s:text name="gatecard.status.disable" />
                                    </option>                               
                                </select>
                            </div>                                                                               
                            <label><s:text name="gatecard.cardno"/></label>
                            <input type="text" maxlength="30" name="gateCard.cardNo" value="<s:property value='gateCard.cardNo'/>" class="all_tab_top_input"/>
                            <label><s:text name="gatecard.name"/></label>
                            <input type="text" maxlength="25" name="gateCard.ownerName" value="<s:property value='gateCard.ownerName'/>" class="all_tab_top_input"/>
                            <label><s:text name="gatecard.idcard"/></label>
                            <input type="text" maxlength="30" name="gateCard.ownerIdCard" value="<s:property value='gateCard.ownerIdCard'/>" class="all_tab_top_input"/>                    
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="gatecard.cardno"/></th>
                                    <th width="15%"><s:text name="gatecard.name"/></th>
                                    <th><s:text name="gatecard.idcard"/></th>
                                    <th>房号</th>
                                    <th width="10%"><s:text name="gatecard.status"/></th>
                                    <th width="200"><s:text name="common.element.title.action"/></th>                                  
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="gateCard">
                                    <tr>
                                        <td>
                                            <a href="<s:url action="viewDetail"><s:param name="gateCardId" value="#gateCard.gateCardId"/></s:url>">
                                                <s:property value="#gateCard.cardNo"/>
                                            </a>
                                        </td>
                                        <td><s:property value="#gateCard.ownerName"/></td>
                                        <td><s:property value="#gateCard.ownerIdCard"/></td>
                                        <td><s:property value="#gateCard.roomName"/></td>
                                        <s:if test="#gateCard.status == 0">
                                            <td><s:text name="gatecard.status.enable"/></td>
                                            <td>
                                                <a href="javascript:confirmEnableOrDisable('<s:url action="enableOrDisable"/>','<s:property value="#gateCard.gateCardId"/>','1','<s:text name="gatecard.confirm.disable"/>');" class="all_hover_but">
                                                    <s:text name="common.element.action.disable"/>
                                                </a>
                                        </s:if>
                                        <s:else>
                                            <td><s:text name="gatecard.status.disable"/></td>
                                            <td>
                                                <a href="javascript:confirmEnableOrDisable('<s:url action="enableOrDisable"/>','<s:property value="#gateCard.gateCardId"/>','0','<s:text name="gatecard.confirm.enable"/>');" class="all_hover_but">
                                                    <s:text name="common.element.action.enable"/>
                                                </a>
                                        </s:else>                                                                                                           
                                            <a href="<s:url action="updateInput"><s:param name="gateCardId" value="#gateCard.gateCardId"/></s:url>" class="all_hover_but">
                                                <s:text name="common.element.action.update"/>
                                            </a>
                                            <a href="javascript:confirmAction('<s:url action="remove"/>','<s:property value="#gateCard.gateCardId"/>','<s:text name="common.confirm.remove"/>')" class="all_hover_but">
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
                        <div class="layer" id="enableOrDisableFrame" style="display:none;">
                            <div>
                                <p id="enableOrDisableMsg"></p>
                                <ul>
                                    <li>
                                        <a id="enableOrDisableUrl" href="#">
                                            <s:text name="common.element.action.confirm"/>
                                        </a>
                                        <a href="javascript:hideTip('enableOrDisableFrame');"><s:text name="common.element.action.cancel"/></a>
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
