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
    </head>
    <script language="JavaScript">
     function locationAction(url){      
      window.location.href =url;          
    } 
    function toReadCard()
    {
      var url="<s:url action='toReadCard'/>";
      locationAction(url);
    }
    </script>
    <body>
        <form action="<s:url action='queryList'/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="<s:url action='queryList' namespace='/gateCard'/>"><s:text name="gatecard.management.list"/></a>
                        <a href="<s:url action='addInput' namespace='/gateCard'/>"><s:text name="gatecard.management.add"/></a>
                        <a href="<s:url action='queryList' namespace='/gateCardVisit'/>"><s:text name="gatecard.management.gatecardvisit.list"/></a>
                        <a href="<s:url action="queryList"/>" class="all_menu_a"><s:text name="gatecard.management.idcardvisit.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">                                                                                                        
                            <label><s:text name="gatecard.name"/></label>
                            <input type="text" maxlength="25" size="20" name="idCardVisit.visitorName" value="<s:property value='idCardVisit.visitorName'/>" class="all_tab_top_input"/>
                            <label><s:text name="gatecard.idcard"/></label>
                            <input type="text"  maxlength="25" size="25" name="idCardVisit.idCard" value="<s:property value='idCardVisit.idCard'/>" class="all_tab_top_input"/>
                            <label><s:text name="common.title.gender"/></label>
                            <div class="select_div">
                                <select name="idCardVisit.gender">
                                    <option value=""><s:text name="common.title.all"/></option>
                                    <option value="0" <s:if test="idCardVisit.gender != '' && idCardVisit.gender == 0">selected</s:if>><s:text name="common.title.gender.male" /></option>
                                    <option value="1" <s:if test="idCardVisit.gender != '' && idCardVisit.gender == 1">selected</s:if>><s:text name="common.title.gender.female" /></option>
                                </select>
                            </div>
                            <label><s:text name="gatecard.timerange"/></label>
                            <input type='text' name='idCardVisit.beginTime' value='<s:date name="idCardVisit.beginTime" format="yyyy-MM-dd" />' onclick='SelectDate(this,"yyyy-MM-dd",0,-150)' readonly='true' class="date_input time_input"/> 
                            <label><s:text name="gatecard.timerange.to"/></label>
                            <input type='text' name='idCardVisit.endTime' value='<s:date name="idCardVisit.endTime" format="yyyy-MM-dd" />' onclick='SelectDate(this,"yyyy-MM-dd",0,-150)' readonly='true' class="date_input time_input"/>                    
                            <input type="submit" onclick="queryPagingList();" value="<s:text name='common.element.action.search' />" class="search_but" />                    
                            <input type="button" onclick="toReadCard();" value="<s:text name='gatecard.read.card' />" class="search_but" />
                        </div>
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th width="20%"><s:text name="gatecard.name"/></th>
                                    <th><s:text name="gatecard.idcard"/></th>                                   
                                    <th width="50"><s:text name="common.title.gender"/></th>
                                    <th width="20%"><s:text name="gatecard.idcard.address"/></th>
                                    <th><s:text name="gatecard.visittime"/></th>                                  
                                </tr>
                            </thead>
                            <tbody>
                                <s:iterator value="%{page.results}" var="icv">
                                    <tr>
                                        <td>
                                            <a href="<s:url action="viewDetail"><s:param name="visitId" value="#icv.visitId"/></s:url>">
                                                <s:property value="#icv.visitorName"/>
                                            </a>
                                        </td>
                                        <td>
                                            <s:property value="#icv.idCard"/>
                                        </td>                                       
                                        <td>
                                            <s:if test="#icv.gender ==\"0\"">
                                                <s:text name="common.title.gender.male" />
                                            </s:if>
                                            <s:elseif test="#icv.gender ==\"1\"">
                                                <s:text name="common.title.gender.female" />
                                            </s:elseif>
                                        </td>
                                        <td><s:property value="#icv.visitorAddress"/></td>
                                        <td><s:date name="#icv.visitTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                        <s:include value="/views/common/paging.jsp"/>                                                         
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
