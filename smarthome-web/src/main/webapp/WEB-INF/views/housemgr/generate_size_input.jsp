<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="housemgr.region"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/housemgr.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/size.js'/>"></script>
        <jsp:include  page="../common/include_common.jsp"/>
    </head>

    <body>
        <form action="<s:url action="generateSize"/>" method="post" enctype="multipart/form-data">
        <input type="hidden" name="cell.id" value="<s:property value='cell.id'/>"/>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="<s:url action="queryRegionList"/>"><s:text name="housemgr.region.list"/></a>
                    <a href="queryBuildingList" class="all_menu_b"><s:text name="housemgr.building.list"/></a>
                    <a href="queryCellList" class="all_menu_b"><s:text name="housemgr.cell.list"/></a>
                    <a href="#" class="all_menu_a"><s:text name="housemgr.size.generate"/></a>
                    <a href="queryHouseholdList" class="all_menu_b"><s:text name="housemgr.house.list"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0" class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="housemgr.cell.list.name"/></td>
                                <td><input type="hidden" name="cell.name" value="<s:property value='cell.name'/>"/><s:property value='cell.name'/></td>
                                <td width="120"><dfn></dfn><s:text name="housemgr.size.generate.quantity"/></td>
                                <td><input id="generateCount" name="generateCount" type="text" size="10" value="<s:property value='generateCount'/>" maxlength="1" class="{required:true, digits: true, min: 1}" /></td>
                            </tr>
                        </tbody>
                    </table>
                    <table cellspacing="0" class="all_tab_body all_tab_bodys error_th">
	                    <thead>
	                        <tr>
                                <th colspan="2" style="text-align: left">                                    
                                    <i><s:text name="housemgr.size.generate.type"/>(<s:text name='common.title.file.msg'><s:param><s:property value='#request.picExt'/></s:param><s:param>10M</s:param></s:text>)</i>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <s:if test="exceedFileSizeFlag == true || errorFlag == true">
                                        <label id="errorFileSizeMsg" class="error">
                                            <s:if test="exceedFileSizeFlag == true">
                                                <s:text name="error.file.too.large"/>
                                            </s:if>
                                            <s:else>
                                                <s:actionerror />
                                            </s:else>
                                        </label>
                                    </s:if>
                                </th>
                            </tr>
	                    </thead>
	                    <tbody id="newSizeList" class="apar_layout">
	                        <tr>
	                            <td width="120"><dfn></dfn><s:text name="housemgr.house.list.size"/> 1</td>
	                            <td>                                    
	                                <ul>
                                        <li>
                                            <div class="select_div">                                        
                                                <select name="roomCount">
                                                    <s:iterator value="%{#request.roomCounts}" var="rc">
                                                        <option value="<s:property value='#rc'/>"><s:property value='#rc'/></option>
                                                    </s:iterator>            	                                   
                                                </select>
                                            </div>
    	                                    <label><s:text name="housemgr.size.generate.room"/></label>
                                            <div class="select_div">
                                                <select name="hallCount">
                                                   <s:iterator value="%{#request.roomCounts}" var="rc">
                                                        <option value="<s:property value='#rc'/>"><s:property value='#rc'/></option>
                                                    </s:iterator>
                                                </select>
                                            </div>                                  
        	                                <label><s:text name="housemgr.size.generate.hall"/></label>
        	                                <label><s:text name="housemgr.size.generate.plan"/></label>
                                            <input type="text" name="planFileName" class="viewfile" size="50" readonly />                  
                                            <label for="upload" class="all_hover_but file_label"><s:text name="common.element.action.select" /></label>
                                            <input type="file" name="plan" onchange="selectPlanFileName(this, 'planFileName', 'planFileErrorMsg', false);hideTip('errorFileSizeMsg');" class="file" />
                                            <label name="planFileErrorMsg" class="error errors" style="display:none;"></label>
                                        </li>
                                    </ul>
                                    <p>
                                        <label class="ala_label"><s:text name="housemgr.size.generate.roomCount" /></label>
                                        <input name="generateRoomCount" type="text" maxlength="2" value="2" class="{required:true, digits: true, min: 1}" />
                                    </p>
                                    <p>
                                        <ul>
                                            <li>
                                                <label class="ala_label"><s:text name="housemgr.size.generate.roomName" /> 1</label><input name="roomName" value="<s:text name="housemgr.size.generate.roomName" /> 1" type="text" maxlength="10" class="{required:true}" />
                                                <label><s:text name="housemgr.size.generate.roomPlan" /></label>
                                                <input type="text" name="roomPlanFileName" class="viewfile" size="50" readonly />                  
                                                <label for="upload" class="all_hover_but file_label"><s:text name="common.element.action.select" /></label>
                                                <input type="file" name="roomPlan" onchange="selectPlanFileName(this, 'roomPlanFileName', 'roomPlanFileErrorMsg', false);hideTip('errorFileSizeMsg');" class="file" />
                                                <label name="roomPlanFileErrorMsg" class="error errors" style="display:none;"></label>
                                            </li>
                                            <li>
                                                <label class="ala_label"><s:text name="housemgr.size.generate.roomName" /> 2</label><input name="roomName" type="text" value="<s:text name="housemgr.size.generate.roomName" /> 2" maxlength="10" class="{required:true}" />
                                                <label><s:text name="housemgr.size.generate.roomPlan" /></label>
                                                <input type="text" name="roomPlanFileName" class="viewfile" size="50" readonly />                  
                                                <label for="upload" class="all_hover_but file_label"><s:text name="common.element.action.select" /></label>
                                                <input type="file" name="roomPlan" onchange="selectPlanFileName(this, 'roomPlanFileName', 'roomPlanFileErrorMsg', false);hideTip('errorFileSizeMsg');" class="file" />
                                                <label name="roomPlanFileErrorMsg" class="error errors" style="display:none;"></label>
                                            </li>
                                        </ul>
                                    </p>
	                            </td>
	                        </tr>
	                        <tr>
	                            <td><dfn></dfn><s:text name="housemgr.house.list.size"/> 2</td>
	                            <td>
                                    <ul>
                                        <li>
                                            <div class="select_div">
            	                                <select name="roomCount">
            	                                   <s:iterator value="%{#request.roomCounts}" var="rc">
                                                        <option value="<s:property value='#rc'/>"><s:property value='#rc'/></option>
                                                    </s:iterator>
                                                </select>
                                            </div>        
    	                                    <label><s:text name="housemgr.size.generate.room"/></label>
                                            <div class="select_div">
                                                <select name="hallCount">
                                                   <s:iterator value="%{#request.roomCounts}" var="rc">
                                                        <option value="<s:property value='#rc'/>"><s:property value='#rc'/></option>
                                                    </s:iterator>
                                                </select>
                                            </div>
    	                                    <label><s:text name="housemgr.size.generate.hall"/></label>
    	                                    <label><s:text name="housemgr.size.generate.plan"/></label>
                                            <input type="text" name="planFileName" class="viewfile" size="50" readonly />                  
                                            <label for="upload" class="all_hover_but file_label"><s:text name="common.element.action.select" /></label>
                                            <input type="file" name="plan" onchange="selectPlanFileName(this, 'planFileName', 'planFileErrorMsg', false);hideTip('errorFileSizeMsg');" class="file" />
                                            <label name="planFileErrorMsg" class="error errors" style="display:none;"></label>
                                        </li>
                                    </ul>
                                    <p>
                                        <label class="ala_label"><s:text name="housemgr.size.generate.roomCount" /></label>
                                        <input name="generateRoomCount" type="text" value="2" maxlength="2" class="{required:true, digits: true, min: 1}" />
                                    </p>
                                    <p>
                                        <ul>
                                            <li>
                                                <label class="ala_label"><s:text name="housemgr.size.generate.roomName" /> 1</label><input name="roomName" value="<s:text name="housemgr.size.generate.roomName" /> 1" type="text" maxlength="10" class="{required:true}" />
                                                <label><s:text name="housemgr.size.generate.roomPlan" /></label>
                                                <input type="text" name="roomPlanFileName" class="viewfile" size="50" readonly />                  
                                                <label for="upload" class="all_hover_but file_label"><s:text name="common.element.action.select" /></label>
                                                <input type="file" name="roomPlan" onchange="selectPlanFileName(this, 'roomPlanFileName', 'roomPlanFileErrorMsg', false);hideTip('errorFileSizeMsg');" class="file" />
                                                <label name="roomPlanFileErrorMsg" class="error errors" style="display:none;"></label>
                                            </li>
                                            <li>
                                                <label class="ala_label"><s:text name="housemgr.size.generate.roomName" /> 2</label><input name="roomName" type="text" value="<s:text name="housemgr.size.generate.roomName" /> 2" maxlength="10" class="{required:true}" />
                                                <label><s:text name="housemgr.size.generate.roomPlan" /></label>
                                                <input type="text" name="roomPlanFileName" class="viewfile" size="50" readonly />                  
                                                <label for="upload" class="all_hover_but file_label"><s:text name="common.element.action.select" /></label>
                                                <input type="file" name="roomPlan" onchange="selectPlanFileName(this, 'roomPlanFileName', 'roomPlanFileErrorMsg', false);hideTip('errorFileSizeMsg');" class="file" />
                                                <label name="roomPlanFileErrorMsg" class="error errors" style="display:none;"></label>
                                            </li>
                                        </ul>
                                    </p>                                  
	                            </td>
	                        </tr>
	                    </tbody>
                    </table>
                    <div class="all_tab_butb"><input type="submit" value="<s:text name='common.element.action.add' />" /></div>
                    <div class="layer none" id="promptRepeat">
                        <div>
                            <p><s:text name="common.system.info"/></p>
                            <ul>
                                <li>
                                    <s:text name="housemgr.size.generate.roomNameRepeat"/><br /><br />
                                </li>
                                <li>
                                    <a href="javascript:hideTip('promptRepeat');"><s:text name="common.element.action.confirm"/></a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <s:include value="/WEB-INF/views/housemgr/include_result_msg.jsp"/>                   
                </div>
            </div>
        </div>
        </form>
        <script>
            QueryLoader.selectorPreload = "body";
            QueryLoader.init();
            
            var ignorePlanEmpty = false;
            var validFileExts = "<s:property value='#request.picExt'/>";
        </script>
    </body>
</html>
