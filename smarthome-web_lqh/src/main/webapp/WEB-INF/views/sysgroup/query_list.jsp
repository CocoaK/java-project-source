<%@page import="com.biencloud.smarthome.web.systemgroup.vo.SystemGroupVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String path=request.getContextPath();List<Map<String,String>> treeList=request.getAttribute("treeList")==null?null:(List<Map<String,String>>)request.getAttribute("treeList"); %>
<% Boolean editResult=request.getAttribute("editResult")==null?null:(Boolean)request.getAttribute("editResult"); %>
<% Boolean groupNameExist=request.getAttribute("groupNameExist")==null?null:(Boolean)request.getAttribute("groupNameExist"); %>
<% Boolean delResult=request.getAttribute("delResult")==null?null:(Boolean)request.getAttribute("delResult"); %>
<% String groupNoForList=request.getAttribute("groupNoForList")==null?null:(String)request.getAttribute("groupNoForList"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="sysgroup.management"/></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <link href="<s:url value='/css/dhtmlxtree.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
        <!-- <script type="text/javascript" src="<s:url value='/js/dtree.js'/>"></script> -->
        <script type="text/javascript" src="<s:url value='/js/tm.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/dhtmlxcommon.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/dhtmlxtree.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/ext/dhtmlxtree_json.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <style>
       	 .dtree {
			font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
			font-size: 11px;
			color: #666;
			white-space: nowrap;
			padding: 30px 0 0 10px;
			}
			.dtree img {
				border: 0px;
				vertical-align: middle;
			}
			.dtree a {
				color: #333;
				text-decoration: none;
			}
			.dtree a.node, .dtree a.nodeSel {
				white-space: nowrap;
				padding: 1px 2px 1px 2px;
			}
			.dtree a.node:hover, .dtree a.nodeSel:hover {
				color: #333;
				text-decoration: underline;
			}
			.dtree a.nodeSel {
				background-color: #c0d2ec;
			}
			.dtree .clip {
				overflow: hidden;
			}
        </style>
        <script>
        function detailMsg(){
       	 var selectedItemId=tree.getSelectedItemId()+"text";
       	 var target=$("#"+selectedItemId).val();
       	 var params=target.split(",");
       	 detailToMsg(params[0],params[1],params[2],params[3],params[4],params[5]);
        }
        </script>
    </head>

    <body>
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="#" class="all_menu_a"><s:text name="sysgroup.management.list"/></a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <div class="all_tab_top">   
                            <label class="all_border_right"><s:text name="sysgroup.name"/></label>
                            <input type="text" name="groupName" id="groupName" value="${group.groupName}" class="all_tab_top_input" maxlength="10"/>                    
                            <input type="button" value="<s:text name='common.element.action.search' />" class="search_but" onclick="search();"/>   
                        </div>
                <p class="dtree_top"><strong><s:text name='sysgroup.treedetail'/></strong><a href="javascript: openAll();"><s:text name='sysgroup.openAll'/></a><span>|</span><a href="javascript: closeAll();"><s:text name='sysgroup.closeAll'/></a></p>
                <div class="dtree">
                   <%-- <script type="text/javascript">
							 d = new dTree('d','<%=path%>/images/img/');
							<%
							if(treeList!=null){
	                        for (Object treeMapResult:treeList){
	                        	Map<String,String> treeMap=(Map<String,String>)treeMapResult;
	                        	String NodeUrl=treeMap.get("NodeUrl");
	                        %>
	                        d.add(<%=treeMap.get("NodeNo")%>,'<%=treeMap.get("Nodename")%>',"<%=NodeUrl%>");
	                        <%}}%>
							document.write(d); 
					
					</script>--%>
					<div id="treeBox" style="width:200;height:200"></div>
					<span id="hiddenElement"></span>
					 <script>
									    var tree = new dhtmlXTreeObject("treeBox","100%","100%",0);
								        tree.setImagePath("<%=path%>/images/imgsTree/csh_vista/");
								        tree.enableCheckBoxes(0,0);
								        tree.enableFixedMode(1);
								        var treeArray = new Array();
								        <%
										if(treeList!=null){
				                        for (Object treeMapResult:treeList){
				                        	Map<String,String> treeMap=(Map<String,String>)treeMapResult;
				                        %>
				                        var subArray = new Array();
				                        subArray.push("<%=treeMap.get("CurrentNodeNo")%>");
				                        subArray.push("<%=treeMap.get("ParentNodeNo")%>");
				                        subArray.push("<%=treeMap.get("Nodename")%>");
				                        treeArray.push(subArray);
				                        tree.setUserData('<%=treeMap.get("CurrentNodeNo")%>', "CurrentNodeNo", "<%=treeMap.get("Nodename")%>");
				                        $("#hiddenElement").append("<input type='hidden' name='<%=treeMap.get("CurrentNodeNo")%>text' id='<%=treeMap.get("CurrentNodeNo")%>text' value='<%=treeMap.get("onClickParams")%>'/>");
				                        <%}}%>
								        tree.loadJSArray(treeArray);
								        tree.setOnClickHandler(detailMsg);
								        function openAll(){
								        	tree.openAllItems();
								        }
								        function closeAll(){
								        	tree.closeAllItems();
								        }
								    </script>
                </div>
                <div class="dtree_right">
                	<h4 id="init"><s:text name='sysgroup.optTip'/></h4>
                	<h4 id="inited" style="display: none;"><s:text name='sysgroup.detailedInfo'/></h4>
                    <strong id="title"><s:text name='sysgroup.thisposition'/></strong>
                    <div id="contentDesc"><s:text name='sysgroup.msg'/></div>
                    <div id="optButtonDiv" style="display: none;">
	                    <p>
	                    	<a href="javascript:showInput(0);" class="all_hover_but" id="addLink"><s:text name='common.element.action.new'/></a>
	                    	<a href="javascript:showInput(1);" class="all_hover_but"><s:text name='common.element.action.update'/></a>
	                        <a href="javascript:delData();" class="all_hover_but" id="delLink"><s:text name='common.element.action.remove'/></a>
	                    </p>
                    </div>
                    <div id="opttip" style='text-indent:0px;display: none;'>
	                </div>
	                <div id="optParentButtonDiv" style="display: none;">
	                    <p>
	                    	<a href="javascript:showInput(0,0);" class="notetitle all_hover_buts"><s:text name='sysgroup.addCountries'/></a>
	                    </p>
                    </div>
                </div>
                <div id="list_click" style="display:none;" class="layers">
                	<div>
                    	<p><strong><s:text name='sysgroup.searchresults'/></strong><span style="float: right;"><a href="#" class="closediv">X</a></span></p>
                        <ul class="layers_search_list" id="searchResult">
                        </ul>
                    </div>
            	</div>
                    </div>
                </div>
            </div>
            <div id="list_click2" style="display:none;" class="layer">
            	
                	<div>
                	<form action="<s:url action="editSystemGroupData"/>" id="inputForm" name="inputForm" method="post">
                    	<p id="add"><s:text name='sysgroup.neworganization'/></p>
                    	<p id="update" style="display: none;"><s:text name='sysgroup.updateorganization'/></p>
                        <ul>
							<li id="parentNameText" style="text-align: left;padding-left: 40px">
                            	<strong><s:text name='sysgroup.subordinateOrganizations'/>：<span id="groupNameShow"></span>
                            	</strong>
                            	<strong><span id="groupNameShow"></span>
                            	</strong>
                            </li>
                            <li  style="text-align: left;padding-left: 40px"><strong id="nameId"><s:text name='sysgroup.name'/>：</strong>
                            <input type="hidden" name="systemGroup.groupNo" id="systemGroup.groupNo"/>
                            <input type="hidden" name="systemGroup.groupParentNo" id="systemGroup.groupParentNo"/>
                            <input type="text" name="systemGroup.groupName" id="systemGroup.groupName"  onkeypress="if(event.keyCode==13||event.which==13){return false;}" maxlength="10"/></li>
                            <li style="padding-top: 10px;"><a href="javascript:iuputSumbit();"><s:text name="common.element.action.confirm"/></a>
                            <a href="javascript:hideTip('list_click2');" class="closediv"><s:text name="common.element.action.cancel"/></a></li>
                        </ul>
                         </form>
                    </div>
                    
            	</div>
            
            <div class="layer" id="tipMsgDiv" style="display:none;">
                	<div>
                    	<p><s:text name="common.system.info"/></p>
                    	
                        <ul>
                            <li id="showMsg"></li>
                            <li style="padding-top: 10px"><a href="javascript:hideTip('tipMsgDiv');" id="ConfirmButtonId"><s:text name="common.element.action.confirm"/></a>
                            <span id="cancelButonId" style="display: none"><a href="javascript:hideTip('tipMsgDiv');" ><s:text name="common.element.action.cancel"/></a></span>
                            </li>
                        </ul>
                    </div>
                </div>
                <input name="gropuNoselected" id="gropuNoselected" value="" type="hidden"/>
                <input name="gropuParentNoselected" id="gropuParentNoselected" value="" type="hidden"/>
                <input name="gropuNameselected" id="gropuNameselected" value="" type="hidden"/>
                <input name="hashChildselected" id="hashChildselected" value="" type="hidden"/>
        <script type="text/javascript">
             function showInput(value,national){
            	 $('#list_click2').show();
            	 $('#list_click').hide();
            	 $("#groupNameShow").html("");
            	 $("#groupNameShow").append($("#gropuNameselected").val());
            	 if(value==0){
            		 $("#add").show();
                	 $("#update").hide();
            		 $("#parentNameText").show(); 
            		 document.getElementById("systemGroup.groupName").value="";
            		 document.getElementById("systemGroup.groupNo").value="";
            		 document.getElementById("systemGroup.groupParentNo").value=$("#gropuNoselected").val();
            		 if(national==0) {
            			 $('#parentNameText').hide();
            			 $("#add").text("<s:text name='sysgroup.newcountry'/>");
            			 $("#nameId").text("<s:text name='sysgroup.countryname'/>");
            			 document.getElementById("systemGroup.groupParentNo").value="";
            		 }
            	 }else{
            		 $("#update").show();
                	 $("#add").hide();
                	 $("#parentNameText").hide(); 
                	 document.getElementById("systemGroup.groupName").value=$("#gropuNameselected").val();
                	 document.getElementById("systemGroup.groupNo").value=$("#gropuNoselected").val();
            	 }
             }
            function detailToMsg(id,parentId,name,desc,hashChild,deep){
            	 $("#addButton").show();
            	 $("#list_click").hide();
            	 $("#list_click2").hide();
            	 $("#addLink").attr('href','javascript:showInput(0);'); 
        		 $("#addLink").attr('class','all_hover_but'); 
        		 $("#delLink").attr('href','javascript:delData();'); 
           		 $("#delLink").attr('class','all_hover_but'); 
            	 if(deep==<%=SystemGroupVO.DEEP_COMMUNITY%>){
            		 $("#addLink").attr('href','#'); 
            		 $("#addLink").attr('class','all_hover_but opacity'); 
            		 validateIsDel(id);
            	 }
            	 $("#init").hide();
            	 $("#inited").show();
            	 $("#contentDesc").html("");
            	 if(desc=='null') desc="<s:text name='sysgroup.description'/>";
            	 $("#contentDesc").append(desc);
            	 $("#title").html("");
            	 $("#title").append(name);
            	 $("#opttip").html("");
            	 $("#opttip").show();
            	 $("#opttip").append("<stong ><s:text name='sysgroup.optHis'/>：</stong><br/><s:text name='common.element.action.new'/>：<s:text name='common.element.action.new'/>'"+name+"'<s:text name='sysgroup.ofsubgroup'/>"+"<br/><s:text name='common.element.action.update'/>：<s:text name='common.element.action.update'/>'"+name+"'<s:text name='sysgroup.ofname'/>"+"<br/><s:text name='common.element.action.remove'/>：<s:text name='common.element.action.remove'/>'"+name+"'");
            	 $("#gropuNoselected").val(id);
            	 $("#gropuParentNoselected").val(parentId);
            	 $("#gropuNameselected").val(name);
            	 $("#hashChildselected").val(hashChild);
            	 $("#optButtonDiv").show();
            	 
             }
             function validateIsDel(groupId){
            	 $.ajax({
               		type: "post",
               		url: "<%=path%>/sysgroup/validateIsGenratorRegion!.action",
               		data:"groupId="+groupId,
               		success: function(data, textStatus){
               		 if(data=='false'){
               			$("#delLink").attr('href','#'); 
                  		 $("#delLink").attr('class','all_hover_but opacity'); 
               		 }
               		}
               });
             }
             function search(){
            	 var name=document.getElementById("groupName").value;
            	 if(name==''){
            		 $("#showMsg").html("");
            		 $("#showMsg").append("<s:text name='sysgroup.name.inputValidate'/>");
            		 $("#tipMsgDiv").show();
            		 return false;
            	 }
            	 var groupName=$("#groupName").val();
            	 $.ajax({
              		type: "post",
              		url: "<%=path%>/sysgroup/querySystemGroupList.action",
              		data:"groupName="+groupName,
              		beforeSend: function(XMLHttpRequest){
              			//ShowLoading();
              		},
              		success: function(data, textStatus){
              			$("#searchResult").html("");
              			/*$("item",data).each(function(i, domEle){
              				$(".ajax.ajaxResult").append("<li>"+$(domEle).children("title").text()+"</li>");
              			});*/
              			$("#searchResult").append(data);
              			$("#list_click").show();
              		},
              		complete: function(XMLHttpRequest, textStatus){
              			//HideLoading();
              		},
              		error: function(){
              			//请求出错处理
              		}
              });
             }
             function linkFunction(value){
            	 $("#list_click").hide();
            	 closeAll();
            	 tree.openItem(value);
             	 tree.setItemColor(value, "#008792", "#008792");
             }
             
             function delData(){
            		 $("#showMsg").html("");
            		 $("#cancelButonId").show();
            		 $('#ConfirmButtonId').attr("href","javascript:goDelData();");
            		 $("#showMsg").append("<s:text name="common.confirm.remove"/>");
            		 $("#tipMsgDiv").show();
             }
             
             function goDelData(){
            	 var hashChild=$("#hashChildselected").val();
            	 if(<%=SystemGroupVO.HashChild_YES%>==hashChild){
            		 $("#showMsg").html("");
            		 $("#showMsg").append("<s:text name='sysgroup.noDel'/>");
            		 $('#ConfirmButtonId').attr("href","javascript:hideTip('tipMsgDiv');");
            		 $("#cancelButonId").hide();
            		 $("#tipMsgDiv").show();
            	 } else{
            		 var groupNo=document.getElementById("gropuNoselected").value;
            	     var gropuParentNoselected=document.getElementById("gropuParentNoselected").value;
                	 document.location.href="<%=path%>/sysgroup/DelSystemGroupData.action?optId="+groupNo+"&gropuParentNoselected="+gropuParentNoselected;
            	 }
             }
             
             function iuputSumbit(){
            	 var name=document.getElementById("systemGroup.groupName").value;
            	 var checkuname=/<|>|'|;|&|#|"'|'|~|!|%/;
            	 if(name==''){
            		 $("#showMsg").html("");
            		 $("#showMsg").append("<s:text name='sysgroup.name.inputValidate'/>");
            		 $("#tipMsgDiv").show();
            	 }else if(checkuname.test(name))
	         		{
            		 $("#showMsg").html("");
            		 $("#showMsg").append("不能包含特殊字符！");
            		 $("#tipMsgDiv").show();
	          		 }
            	 else if(name.length>10)
	         		{
	         		 $("#showMsg").html("");
	         		 $("#showMsg").append("长度不能超过10个字符！");
	         		 $("#tipMsgDiv").show();
	          		 }
            	 else{
            		 inputForm.submit();
            	 }
             }
             if('<%=groupNoForList%>'!=''){
            	 if('<%=groupNoForList%>'!=null){
 		        	linkFunction(<%=groupNoForList%>);
 		        }
              $("#showMsg").html("");
             }
             <%
             if(groupNameExist!=null){
           %>
           $("#showMsg").append("<s:text name='sysgroup.name.exist'/>！");
           $("#ConfirmButtonId").attr("href","<%=path%>/sysgroup/querySystemGroupList.action?groupNo=<%=request.getAttribute("hanlerGroupNo")%>");
           $("#tipMsgDiv").show();
           <%}%>
             <%
               if(editResult!=null){
            	   if(editResult) {
             %>
             $("#showMsg").append("<s:text name='common.element.action.operationSuccess'/>！");
             <%}else{%>
             $("#showMsg").append("<s:text name='common.element.action.operationfailed'/>！");
             <%}%>
             $("#ConfirmButtonId").attr("href","<%=path%>/sysgroup/querySystemGroupList.action?groupNo=<%=request.getAttribute("hanlerGroupNo")%>");
             $("#tipMsgDiv").show();
             <%}%>
             <%
             if(delResult!=null){
          	   if(delResult) {
           %>
           $("#showMsg").append("<s:text name='common.element.action.operationSuccess'/>！");
           <%}else{%>
           $("#showMsg").append("<s:text name='sysgroup.useredNoDel'/>！");
           <%}%>
           $("#ConfirmButtonId").attr("href","<%=path%>/sysgroup/querySystemGroupList.action?groupNo=<%=request.getAttribute("hanlerGroupNo")%>");
           $("#tipMsgDiv").show();
           <%}%>
        </script>
        
    </body>
</html>
