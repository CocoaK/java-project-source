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
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <script type="text/javascript" src="../js/jquery_clorbox.js"></script>
        <style type="text/css">
            #cboxNext,#cboxPrevious,#cboxCurrent,#cboxSlideshow{ display:none !important;}
        </style>
    </head>

    <body>
        <form action="<s:url action='queryList'/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="<s:url action='queryList' namespace='/gateCard'/>"><s:text name="gatecard.management.list"/></a>
                        <a href="<s:url action='addInput' namespace='/gateCard'/>"><s:text name="gatecard.management.add"/></a>
                        <a href="<s:url action="queryList"/>" class="all_menu_a"><s:text name="gatecard.management.gatecardvisit.list"/></a>
                        <a href="<s:url action='queryList' namespace='/idCardVisit'/>"><s:text name="gatecard.management.idcardvisit.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                                                                                                        
                            <label><s:text name="gatecard.cardno"/></label>
                            <input type="text" maxlength="30" name="gateCardVisit.cardNo" value="<s:property value='gateCardVisit.cardNo'/>" class="all_tab_top_input"/>
                            <label><s:text name="gatecard.name"/></label>
                            <input type="text" maxlength="25" name="gateCardVisit.ownerName" value="<s:property value='gateCardVisit.ownerName'/>" class="all_tab_top_input"/>
                            <label><s:text name="gatecard.timerange"/></label>
                            <input type='text' name='gateCardVisit.beginTime' value='<s:date name="gateCardVisit.beginTime" format="yyyy-MM-dd" />' onclick='SelectDate(this,"yyyy-MM-dd",0,-150)' readonly='true' class="date_input time_input"/> 
                            <label><s:text name="gatecard.timerange.to"/></label>
                            <input type='text' name='gateCardVisit.endTime' value='<s:date name="gateCardVisit.endTime" format="yyyy-MM-dd" />' onclick='SelectDate(this,"yyyy-MM-dd",0,-150)' readonly='true' class="date_input time_input"/>                    
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th><s:text name="gatecard.cardno"/></th>
                                    <th width="15%"><s:text name="gatecard.name"/></th>
                                    <th><s:text name="gatecard.gate"/></th>
                                    <th width="15%"><s:text name="gatecard.visittime"/></th>
                                    <th width="220"><s:text name="gatecard.capture.pic"/></th>                                  
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="gcv">
                                    <tr>
                                        <td>
                                            <a href="<s:url action="viewDetail"><s:param name="visitId" value="#gcv.visitId"/></s:url>">
                                                <s:property value="#gcv.cardNo"/>
                                            </a>
                                        </td>
                                        <td><s:property value="#gcv.ownerName"/></td>
                                        <td><s:property value="#gcv.deviceAlias"/></td>
                                        <td><s:date name="#gcv.visitTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                                        <td>                                                                                                           
                                            <s:if test="#gcv.picPath1 != null && #gcv.picPath1 != ''">
                                                <a href="<s:property value='#gcv.picPath1'/>" rel="colorbox1" target="_blank" class="all_hover_but"><s:text name="common.element.action.show"/></a>                                                
                                            </s:if>
                                            <s:else>
                                                <a href="#" class="all_hover_but opacity"><s:text name="common.element.action.show"/></a>                                              
                                            </s:else>                                                                                      
                                        </td>
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <s:include value="/views/common/paging.jsp"/>                                                         
                    </div>
                </div>
            </div>
        </form>
        <script>
    		/*<![CDATA[*/
			$(document).ready(function(){
				$("a[rel^='colorbox']").colorbox({title:"",slideshow:true,slideshowAuto:false})
			;})
			/*]]>*/  		 		   		   				
    	</script>
    </body>
</html>
