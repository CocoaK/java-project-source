<%@ page language="java" pageEncoding="UTF-8"
    contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<div id="list_click" style="display:none;" class="layers layerw">
    <div>
        <p><strong><s:text name="user.select.house.info"/></strong><span class="closediv">X</span></p>
        <p class="layerwp">
            			<label><s:text name="common.title.areaname"/></label><input type="text" id="regionName" name="user.areaName" value="<s:property value='user.areaName'/>"/>
						        	<label><s:text name="common.title.building"/></label><input type="text" id="buildingName" name="user.building" value="<s:property value='user.building'/>"/>
						            <label><s:text name="common.title.unitname"/></label><input type="text" id="cellName" name="user.unitName" value="<s:property value='user.unitName'/>"/>
						            <label><s:text name="common.title.housename"/></label><input type="text" id="houseName" name="user.houseName" value="<s:property value='user.houseName'/>"/>
						            <input type="button" onclick="searchHouse('<s:url action="getHouses" namespace="/housemgr/json/"/>')" value="<s:text name='common.element.action.search' />" class="all_hover_but" />
        </p>
        <ul id="districtResult" class="layers_search_list layers_padding">
            <li>
                <table class="all_tab_body">
                    <thead>
                        <tr>
                            <th><s:text name="common.title.areaname"/></th>
                            <th><s:text name="common.title.building"/></th>
                            <th><s:text name="common.title.unitname"/></th>
                            <th><s:text name="common.title.housename"/></th>
                            <th width="28"><s:text name="common.element.action.select"/></th>
                        </tr>
                    </thead>
                    <tbody id="dataTr">
                    </tbody>
                </table>
            </li>
        </ul>
    </div>
</div>
<script type="text/javascript">
function searchHouse(url){
  	 var houseName=$("#houseName").val();
  	 var regionName=$("#regionName").val();
  	 var buildingName=$("#buildingName").val();
  	 var cellName=$("#cellName").val();
  	 $.ajax({
    		type: "post",
    		url: url,
    		data:"houseName="+houseName+"&regionName="+regionName+"&buildingName="+buildingName+"&cellName="+cellName,
    		beforeSend: function(XMLHttpRequest){
    			//ShowLoading();
    		},
    		success: function(data, textStatus){
    			//var dataobj=eval("("+data+")");//转换为json对象 
    			//alert(dataobj.foundHouses.length);
    			var str="";
    			$.each(data.foundHouses,function(idx,item){ 
					str+="<tr><td>"+item.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name+"</td><td>"+item.THmBuildingCellInfo.THmRegionBuildingInfo.name+"</td><td>"+item.THmBuildingCellInfo.name+"</td><td>"+item.name+"</td>";
    			    str+="<td><a href='javascript:linkFunction(\""+item.id+"A"+item.owner.userId+"\")'><s:text name='common.element.action.select'/></a></td></tr>";
    			}); 
    			$("#dataTr").html("");
    			$("#dataTr").append(str);
    		},
    		complete: function(XMLHttpRequest, textStatus){
    			//HideLoading();
    		},
    		error: function(){
    			//请求出错处理
    		}
    });
   }
</script>
