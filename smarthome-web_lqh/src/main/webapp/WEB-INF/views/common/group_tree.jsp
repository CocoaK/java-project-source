<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="com.biencloud.smarthome.web.common.util.Constants"%>
<%@ page language="java" pageEncoding="UTF-8"
    contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
        <link href="<s:url value='/css/dhtmlxtree.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/dhtmlxcommon.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/dhtmlxtree.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/ext/dhtmlxtree_json.js'/>"></script>
<% 
String path=request.getContextPath();
List<Map<String,String>> treeList=request.getAttribute("treeList")==null?null:(List<Map<String,String>>)request.getAttribute("treeList");
String comStr=request.getAttribute("comStr")==null?null:(String)request.getAttribute("comStr");
String isCheckBox=request.getAttribute("isCheckBox")==null?null:(String)request.getAttribute("isCheckBox");
request.setAttribute("LOGINUSERTYPEOWNER",Constants.LOGIN_USER_TYPE_OWNER);
request.setAttribute("LOGINUSERTYPEPAUSER",Constants.LOGIN_USER_TYPE_PAUSER);
%>
 <input type="hidden" size="40" name="comStr" id="comStr" value="<%=comStr%>"/>
<% if(treeList!=null&&treeList.size()!=0){ %>
                                <div class="dtree dtrees">
                                <!-- searchStart -->
                                <div class="" id="systemGroupSearch" style="padding-bottom: 10px;"> 
	                                <s:if test="#request.LOGINUSERTYPEPAUSER == #session.login.userType">
	                                    <input type="button" onclick="searchHouseTip()" value="<s:text name='user.select.house.info' />"  class="search_but layerspbt" class="all_hover_buts" />                                       
		                            </s:if>
		                            <s:else>
		                                <label><s:text name="sysgroup.name"/></label>
                                        <input type="text" name="groupName" id="groupName" value="${group.groupName}" maxlength="10"/>                    
                                        <input type="button" value="<s:text name='common.element.action.search' />" class="search_but layerspbt" class="notetitle2 search_but" onclick="search();"/>
	                                </s:else>
                                </div>
                                <!-- searchEnd -->
                                 <!-- treeStart -->
                                 <div><input type="button" value="<s:text name='common.element.title.openall'  />" class="search_but layerspbt" onclick="openAll()"/>
								    <input type="button" value="<s:text name='common.element.title.closeall' />" class="search_but layerspbt" onclick="closeAll()"/></div>
									<div id="treeBox" style="width:200;height:200"></div>
									
									 <script>
									    var tree = new dhtmlXTreeObject("treeBox","100%","100%",0);
								        tree.setImagePath("<%=path%>/images/imgsTree/csh_vista/");
								        <% if(isCheckBox==null){%>
								        tree.enableCheckBoxes(1,1);
								        <% }else{%>
								        tree.enableCheckBoxes(0,0);
								        <% }%>
								        tree.enableFixedMode(1);
								        var treeArray = new Array();
								        var selectedNo = new Array();
								        <%
										if(treeList!=null){
				                        for (Object treeMapResult:treeList){
				                        	Map<String,String> treeMap=(Map<String,String>)treeMapResult;
				                        %>
				                        var subArray = new Array();
				                        subArray.push("<%=treeMap.get("CurrentNodeNo")%>");
				                        subArray.push("<%=treeMap.get("ParentNodeNo")%>");
				                        subArray.push("<%=treeMap.get("Nodename")%>");
				                        if(<%=treeMap.get("checked")%>==1) selectedNo.push("<%=treeMap.get("CurrentNodeNo")%>");
				                        treeArray.push(subArray);
				                        <%}}%>
											     /*var treeArray = new Array(
												    ["1","0","node 1"],
												    ["2","1","node 1.1"],
												    ["3","2","node 1.1.1"],
												    ["5","3","node 1.1.1"],
												    ["15","3","node 1.1.1"],
												    ["4","0","node 2"],
												    ["6","4","node 2"],
												    ["7","6","node 2"]
												    );*/
								        tree.loadJSArray(treeArray);
								        tree.enableThreeStateCheckboxes(1); 
								        for ( var int = 0; int < selectedNo.length; int++) {
								        	tree.setCheck(selectedNo[int],1);
								        	tree.openItem(selectedNo[int]);
										}
								        function openAll(){
								        	tree.openAllItems();
								        }
								        function closeAll(){
								        	tree.closeAllItems();
								        }
								    </script>
								    </div>
									<!-- treeEnd -->
                                    
                                    <div id="list_click2" style="display:none;" <% 
                                        String viewResultFlag = request.getParameter("viewResultFlag");
                                        if("1".equals(viewResultFlag)){ 
                                    %>
                                    class="layers layer2"
                                    <% }else{ %>
                                    class="layers"
                                    <% }%>>
                                            <div>
                                                <p><strong><s:text name='sysgroup.searchresults'/></strong><span style="float: right;"><a href="#" class="closediv2">X</a></span></p>
                                                <ul class="layers_search_list" id="searchResult">
                                                </ul>
                                            </div>
                                </div>
									<%}else{ %>
									  <s:text name='common.title.nodata'/>
									<%} %>
<script type="text/javascript">
		function showsel() {
			if (typeof tree == 'undefined') return "";
			var checkedId = tree.getAllChecked();
			var es=checkedId.split(",");
			var out = "";
			var comStr = $("#comStr").val();
			for ( var i = 0; i < es.length; i++) {
				  if(es[i]!=''&&es[i]!=null){
					  var thisvalue=es[i];
						var comarevalue='-'+thisvalue+'-';
						if (comStr != '' && comStr != null
								&& comStr.indexOf(comarevalue) !=-1) {
							out += thisvalue+",";
						}
				  }
			}
			return out;
		}
		
		function search(){
       	 var name=document.getElementById("groupName").value;
       	 if(name==''){
       		 alert("必须输入组织名称才能搜索");
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
         			if(data=="") data="<s:text name='common.title.nodata'/>";
         			$("#searchResult").append(data);
         			$("#list_click2").show();
         		},
         		complete: function(XMLHttpRequest, textStatus){
         			//HideLoading();
         		},
         		error: function(){
         			//请求出错处理
         		}
         });
        }
		
		
		function linkFunction(id,userId){
			tree.closeAllItems();
        	tree.openItem(id);
        	//tree._xopenAll(id);
        	tree.setItemColor(id, "#008792", "#008792");
        	tree.setCheck(id,1);
        	if(userId) $("#list_click").hide();
        	else $("#list_click2").hide();
        }
		
		
	</script>