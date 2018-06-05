<%@page import="com.biencloud.smarthome.web.info.service.IInfoSendService"%>
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
String promt=request.getParameter("promt");
%>
<script type="text/javascript">
function loadHouse(){
	var cellId=tree.getSelectedItemId();
	if(cellId.indexOf("<%=IInfoSendService.TREE_CELL_FLAG%>")!=-1){
		loadHouse(cellId);
	}
}
function onCheckEvent(itemId,status){
	onCheckEventFunction(itemId,status);
}
</script>
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
								   <% if(!"detail".equals(promt)){%>  <s:text name='infosend.noseltip' /><%} %>
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
								        var cellNo = new Array();
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
				                        if("<%=treeMap.get("CurrentNodeNo")%>".indexOf("<%=IInfoSendService.TREE_CELL_FLAG%>")!=-1) cellNo.push("<%=treeMap.get("CurrentNodeNo")%>");
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
										tree.setOnClickHandler(loadHouse);
								        //tree.setOnCheckHandler(onCheckEvent);
								        tree.enableThreeStateCheckboxes(1); 
								        for ( var int = 0; int < selectedNo.length; int++) {
								        	tree.setCheck(selectedNo[int],1);
								        	tree.openItem(selectedNo[int]);
										}
								       for ( var int = 0; int < cellNo.length; int++) {
								    	   	 tree.setItemImage2(cellNo[int],"folderOpen.gif","folderOpen.gif","folderClosed.gif");
								    	   	 <% if("detail".equals(promt)){%>
								        	 var count=tree.getSubItems(cellNo[int]);
								        	 if(count=='') tree.deleteItem(cellNo[int]);
								        	 <%}%>
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
								&& comStr.indexOf(comarevalue) !=-1) {//把选中的房间加到参数中
							out += thisvalue+",";
						}else if(thisvalue.indexOf('<%=IInfoSendService.TREE_CELL_FLAG%>')!=-1){//把选中的单元，条件是选中的单元下面的房间还没有加载出来
							var hasChildren=tree.hasChildren(thisvalue);
							if(hasChildren==0) out += thisvalue+",";
						}
				  }
			}
			return out;
		}
		
		function search(){
       	 var name=document.getElementById("groupName").value;
       	 if(name==''){i
       		 alert("<s:text name='nfosend.name.alert'/>");
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
		
		
		function linkFunction(id,userId,roomName,cellId){
			var text=tree.getItemText(id);
			if(text==0) {
				var par='<%=IInfoSendService.TREE_CELL_FLAG%>'+cellId;
				tree.openItem(par);
				loadHouse(par,id);
			}else loadSuccessForSearch(id);
			if(userId) $("#list_click").hide();
        	else $("#list_click2").hide();
        }
		
		function loadSuccessForSearch(id){
			tree.closeAllItems();
        	tree.openItem(id);
        	//tree._xopenAll(id);
        	tree.setItemColor(id, "#008792", "#008792");
        	tree.setCheck(id,1);
		}
		
		function loadHouse(cellId,itemId){
			tree.insertNewItem(cellId,"tmpId","<s:text name='infosend.loading'/>");
			//加载之前先获取当前单元下面的所有已选中的节点，在刷新后重新选上
			var subItems=tree.getSubItems(cellId);
			try {
				var subSeledArray = new Array();
				if(subItems!=0){
					var subItemArray=subItems.split(",");
					for ( var int2 = 0; int2 < subItemArray.length; int2++) {
						if(tree.isItemChecked(subItemArray[int2])==1) subSeledArray.push(subItemArray[int2]);
					}
				}
				$.ajax({
		      		type: "post",
		      		url: "<%=path%>/info/queryHouseByCellId.action",
		      		data:"cellId="+cellId,
		      		success: function(data, textStatus){
		      			var houseStr=data.split("#");
		      			tree.deleteChildItems(cellId);
		      			tree.deleteItem("tmpId");
		      			for ( var int2 = 0; int2 < houseStr.length; int2++) {
		      				if(houseStr[int2]!=''){
		      					var house=houseStr[int2];
		    					tree.insertNewItem(cellId,house.split(",")[0],house.split(",")[1]);
		      				}
						}
		      			if (typeof itemId != 'undefined') loadSuccessForSearch(itemId);//用户在搜索框处搜索
		      			//选中刷新前已选中的
		      			for ( var int2 = 0; int2 < subSeledArray.length; int2++) {
		      				tree.setCheck(subSeledArray[int2],1);
						}
		      		},
		       });
			} catch (e) {}
		}
		
		function onCheckEventFunction(itemId,status){
			var hasChildren=tree.hasChildren(itemId);
			if(hasChildren==0&&status==0){
				
			}
		}
	</script>