<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="housemgr.size.customSize"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>        
        <script type="text/javascript" src="<s:url value='/js/housemgr/size.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/housemgr/housemgr.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_clorbox.js'/>"></script>
        <style type="text/css">
            #cboxNext,#cboxPrevious,#cboxCurrent,#cboxSlideshow{ display:none !important;}
        </style>
        <jsp:include  page="../common/include_common.jsp"/>
    </head>

    <body>
        <form action="<s:url action="customSize"/>" method="post" enctype="multipart/form-data">
        <input type="hidden" name="size.id" value="<s:property value='size.id'/>"/>
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="#" class="all_menu_a"><s:text name="housemgr.size.customSize"/></a>
                </div>
            </div>
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0" class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="housemgr.house.list.code"/></td>
                                <td><input type="hidden" name="house.code" value="<s:property value='house.code'/>"/><s:property value='house.code'/></td>
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
                                <td width="120"><dfn></dfn><s:text name="housemgr.house.list.size"/></td>
                                <td>                                    
                                    <%-- <ul>
                                        <li>
                                            <div class="select_div">                                        
                                                <select name="size.room">
                                                    <s:iterator value="%{#request.roomCounts}" var="rc">
                                                        <option value="<s:property value='#rc'/>" <s:if test="#rc == size.room">selected</s:if>><s:property value='#rc'/></option>
                                                    </s:iterator>                                                  
                                                </select>
                                            </div>
                                            <label><s:text name="housemgr.size.generate.room"/></label>
                                            <div class="select_div">
                                                <select name="size.hall">
                                                   <s:iterator value="%{#request.roomCounts}" var="rc">
                                                        <option value="<s:property value='#rc'/>" <s:if test="#rc == size.hall">selected</s:if>><s:property value='#rc'/></option>
                                                    </s:iterator>
                                                </select>
                                            </div>                                  
                                            <label><s:text name="housemgr.size.generate.hall"/></label>
                                            <label><s:text name="housemgr.size.generate.plan"/></label>
                                            <input type="text" name="planFileName" class="viewfile" size="50" readonly />                  
                                            <label for="upload" class="all_hover_but file_label"><s:text name="common.element.action.select" /></label>
                                            <input type="file" name="plan" onchange="selectPlanFileName(this, 'planFileName', 'planFileErrorMsg', false);hideTip('errorFileSizeMsg');" class="file" />
                                            <a href="${fileServerUrl}<s:property value='size.plan' />" rel="colorbox1" target="_blank" class="all_hover_but ala_show"><s:text name="common.element.action.show"/></a>
                                            <label name="planFileErrorMsg" class="error errors" style="display:none;"></label>                                           
                                        </li>
                                    </ul> --%>
                                    <p>
                                        <label class="ala_label"><s:text name="housemgr.size.generate.roomCount" /></label>
                                        <input name="generateRoomCount" type="text" maxlength="2" value="<s:property value='generateRoomCount[0]'/>" class="{required:true, digits: true, min: 1}" />
                                    </p>
                                    <p>
                                        <ul>
                                            <s:iterator value="%{size.rooms}" var="room" status="st">
                                                 <li>
                                                    <label class="ala_label"><s:text name="housemgr.size.generate.roomName" /> <s:property value='#st.index + 1'/></label><input name="roomName" value="<s:property value='#room.name'/>" type="text" maxlength="10" class="{required:true}" />
                                                    <label><s:text name="housemgr.size.generate.roomPlan" /></label>
                                                    <input type="text" name="roomPlanFileName" class="viewfile" size="50" readonly />                  
                                                    <label for="upload" class="all_hover_but file_label"><s:text name="common.element.action.select" /></label>
                                                    <input type="file" name="roomPlan" onchange="selectPlanFileName(this, 'roomPlanFileName', 'roomPlanFileErrorMsg', false);hideTip('errorFileSizeMsg');" class="file" />
                                                    <a href="${fileServerUrl}<s:property value='#room.plan' />" rel="colorbox1" target="_blank" class="all_hover_but ala_show"><s:text name="common.element.action.show"/></a>
                                                    <label name="roomPlanFileErrorMsg" class="error errors" style="display:none;"></label>                                                   
                                                </li>       
                                            </s:iterator>
                                        </ul>
                                    </p>
                                </td>
                            </tr>                            
                        </tbody>	               
                    </table>
                    <div class="all_tab_butb"><input type="submit" value="<s:text name='common.element.action.confirm.update' />" /></div>
                    <div class="layer none" id="promptRepeat">
                        <div>
                            <p><s:text name="common.system.info"/></p>
                            <ul>
                                <li>
                                    <s:text name="housemgr.size.generate.roomNameRepeat"/>
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
            /*<![CDATA[*/
        		$(document).ready(function(){
					$("a[rel^='colorbox']").colorbox({title:"",slideshow:true,slideshowAuto:false,previous:"<s:text name='common.title.browse.previous'/>",next:"<s:text name='common.title.browse.next'/>",close:"<s:text name='common.element.action.close'/>",slideshowStart:"<s:text name='common.element.action.broadcast'/>",slideshowStop:"<s:text name='common.element.action.pause'/>",current:"<s:text name='common.title.browse.info'/>"})
				;})
    		/*]]>*/
    		
            QueryLoader.selectorPreload = "body";
            QueryLoader.init();
            
            var ignorePlanEmpty = false;
            var validFileExts = "<s:property value='#request.picExt'/>";
        </script>
    </body>
</html>
