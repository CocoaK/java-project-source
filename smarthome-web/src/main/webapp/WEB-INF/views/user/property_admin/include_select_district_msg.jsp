<%@ page language="java" pageEncoding="UTF-8"
    contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<div id="list_click" style="display:none;" class="layers">
    <div>
        <p>
            <label><s:text name="common.title.districtname" />
            </label>
            <input type="text" maxlength="25" id="districtName" name="districtName" value="<s:property value='districtName'/>" />
            <input type="button" onclick="queryDistricts('<s:url action="getDistrictsByName" namespace="/housemgr/json"/>')" value="<s:text name='common.element.action.search' />" class="all_hover_but layerspbt" />
        </p>
        <ul id="districtResult" class="layers_search_list">
        </ul>
        <a href="javascript:appendDistrict();" class="all_hover_but">
            <s:text name='common.element.action.confirm' />
        </a>
        <a href="#" class="closediv all_hover_but">
            <s:text name='common.element.action.cancel' />
        </a>
    </div>
</div>
