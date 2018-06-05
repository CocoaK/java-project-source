<%@ page language="java" pageEncoding="UTF-8"
    contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value='/js/housemgr/queryHouse.js'/>"></script>
<div id="list_click" style="display:none;" class="layers layerw">
    <div>
        <p><strong><s:text name="user.select.house.info"/></strong><span class="closediv">X</span></p>
        <p class="layerwp">
            			<label><s:text name="common.title.areaname"/></label><input type="text" id="regionName" name="regionName" value="<s:property value='regionName'/>" maxlength="20"/>
						        	<label><s:text name="common.title.building"/></label><input type="text" id="buildingName" name="buildingName" value="<s:property value='buildingName'/>" maxlength="20"/>
						            <label><s:text name="common.title.unitname"/></label><input type="text" id="cellName" name="cellName" value="<s:property value='cellName'/>" maxlength="20"/>
						            <label><s:text name="common.title.housename"/></label><input type="text" id="houseName" name="houseName" value="<s:property value='houseName'/>" maxlength="20"/>
						            <input type="button" onclick="queryHouses('<s:url action="getHouses" namespace="/housemgr/json"/>',1)" value="<s:text name='common.element.action.search' />" class="all_hover_but layerspbt" />
        </p>
        <ul class="layers_search_list layers_padding">
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
    </div>
</div>
<script type="text/javascript">
	var actionUrl = "<s:url action='getHouses' namespace='/housemgr/json'/>";
	var firstPageText = "<s:text name='common.paging.firstPage'/>";
	var previousPageText = "<s:text name='common.paging.previousPage'/>";
	var nextPageText = "<s:text name='common.paging.nextPage'/>";
	var lastPageText = "<s:text name='common.paging.lastPage'/>";
	var selectText="<s:text name="common.element.action.select"/>";
</script>
<script type="text/javascript">
$(function() {
	queryHouses('<s:url action="getHouses" namespace="/housemgr/json"/>',1)
});
</script>
