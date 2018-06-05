<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="housemgr.district" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
    </head>

    <body>
        <form action="<s:url action="viewCellSize"/>">
        <input type="hidden" name="cellId" value="<s:property value='cellId'/>"/>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action="queryRegionList"/>"><s:text name="housemgr.region.list"/></a>
                    <a href="queryBuildingList" class="all_menu_b"><s:text name="housemgr.building.list"/></a>
                    <a href="queryCellList" class="all_menu_b"><s:text name="housemgr.cell.list"/></a>
                    <a href="#" class="all_menu_a"><s:text name="housemgr.size.view"/></a>
                    <a href="queryHouseholdList" class="all_menu_b"><s:text name="housemgr.house.list"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <s:if test="sizeList.size() == 0">
                        <div class="layer" id="promptFrame">
                            <div>
                                <p><s:text name="common.system.info"/></p>
                                <ul>
                                    <li><s:text name="housemgr.size.view.noSizePrompt"/></li>
                                    <li>
                                        <a href="<s:url action="generateSizeInput"><s:param name="cellId" value="cellId"/></s:url>">
                                            <s:text name="housemgr.size.generate"/>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </s:if>
                    <div class="unit_pice">
                        <s:iterator value="%{sizeList}" var="size">
	                        <li>
	                            <img src="${fileServerUrl}<s:property value="#size.plan" />" />
	                            <p>
	                                <strong><s:property value='#size.room'/><s:text name="housemgr.size.generate.room"/><s:property value='#size.hall'/><s:text name="housemgr.size.generate.hall"/></strong>
                                    <a href="javascript:remove('<s:url action='removeCellSize'><s:param name="sizeId" value="#size.id"/><s:param name="cellId" value="cellId"/></s:url>','<s:text name="common.confirm.remove"/>')">
                                        <s:text name="common.element.action.remove"/>
                                    </a>
	                            </p>
	                        </li>
                        </s:iterator>
                    </div>
	                <div class="layer" id="removeFrame" style="display:none;">
	                    <div>
	                        <p id="removeMsg"></p>
	                        <ul>
	                            <li>
	                                <a id="removeUrl" href="#">
	                                    <s:text name="common.element.action.confirm"/>
	                                </a>
	                                <a href="javascript:hideTip('removeFrame');"><s:text name="common.element.action.cancel"/></a>
	                            </li>
	                        </ul>
	                    </div>
	                </div>
                    <s:if test="promptFlag == true">                                       
                        <div class="layer" id="confirmFrame">
                            <div>
                                <p><s:if test="successFlag == true"><s:text name="common.system.info"/></s:if><s:else><s:text name="common.element.action.operationfailed"/></s:else></p>
                                <ul>
                                    <li>
                                        <s:if test="successFlag == true"><s:text name="common.element.action.operationSuccess" /></s:if><s:else><s:text name="housemgr.size.exist.related.data"/></s:else><br /><br />
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
            function remove(removeUrl,removeMsg) {
                $('#removeUrl').attr("href",removeUrl);
                $('#removeMsg').text(removeMsg);
                $('#removeFrame').show();
            }
        </script>
    </body>
</html>
