<%@ page language="java" pageEncoding="UTF-8"
    contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<div id="list_click" style="display:none;" class="layers layerw">
    <div>
        <p><strong><s:text name="user.select.house.info"/></strong><span class="closediv">X</span></p>
        <p class="layerwp">
            <label><s:text name="common.title.areaname"/></label><input type="text" maxlength="10" id="areaName" name="areaName" value="<s:property value='areaName'/>"/>
            <label><s:text name="common.title.building"/></label><input type="text" maxlength="10" id="buildingName" name="buildingName" value="<s:property value='buildingName'/>"/>
            <label><s:text name="common.title.unitname"/></label><input type="text" maxlength="10" id="unitName" name="unitName" value="<s:property value='unitName'/>"/>
            <label><s:text name="common.title.housename"/></label><input type="text" maxlength="25" id="houseName" name="houseName" value="<s:property value='houseName'/>"/>
            <input type="button" onclick="queryHouses('<s:url action="getHouses" namespace="/housemgr/json"/>',1)" value="<s:text name='common.element.action.search' />" class="all_hover_but layerspbt" />
        </p>
        <ul class="layers_search_list layers_padding">
            <li>
                <table cellspacing="0" class="all_tab_body">
                    <thead>
                        <tr>
                        	<th width="28"><s:text name="common.element.action.select"/></th>
                            <th><s:text name="common.title.areaname"/></th>
                            <th><s:text name="common.title.building"/></th>
                            <th><s:text name="common.title.unitname"/></th>
                            <th><s:text name="common.title.housename"/></th>
                        </tr>
                    </thead>
                    <tbody id="houseResult">
                    </tbody>
                    <tfoot class="new_page">
                        <tr>
                            <td colspan="5" id="pageResult">
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </li>
        </ul>
        <a href="javascript:appendHouse('<s:url action="existHouseId" namespace="/json/ownerUser"/>');" class="all_hover_but">
            <s:text name='common.element.action.confirm' />
        </a>
    </div>
</div>

<script type="text/javascript">
	var actionUrl = "<s:url action='getHouses' namespace='/housemgr/json'/>";
	var firstPageText = "<s:text name='common.paging.firstPage'/>";
	var previousPageText = "<s:text name='common.paging.previousPage'/>";
	var nextPageText = "<s:text name='common.paging.nextPage'/>";
	var lastPageText = "<s:text name='common.paging.lastPage'/>";
</script>