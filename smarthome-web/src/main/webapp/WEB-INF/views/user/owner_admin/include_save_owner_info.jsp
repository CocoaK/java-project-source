<%@ page language="java" pageEncoding="UTF-8"
    contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<thead>
    <tr>
        <th colspan="4" class="font_left"><s:text name="owneruser.management.info" /></th>
    </tr>
</thead>
<tbody>
	<tr>
        <td><dfn></dfn><s:text name="user.house.info" /></td>
        <td colspan="3" class="room_hack">
            <input type="hidden" id="selectedHouseId" name="user.houseId" value="<s:property value='user.houseId'/>" />
            <label id="houseInfo"><s:if test="user.houseId != ''">
            	<s:property value='house.THmBuildingCellInfo.THmRegionBuildingInfo.THmHousingDistrictRegionInfo.name'/>
            	-<s:property value='house.THmBuildingCellInfo.THmRegionBuildingInfo.name'/>
            	-<s:property value='house.THmBuildingCellInfo.name'/>
            	-<s:property value='house.name'/></s:if></label>
            <!-- <a href="javascript:void(0);queryHouses('<s:url action="getHouses" namespace="/housemgr/json"/>',1);" class="notetitle all_hover_but"><s:text name="common.element.action.select" /></a> -->
            <label id="unselectedHouseMsg" class="error" style="display:none;"><s:text name="error.required" /></label>
            <label id="existHouseMsg" class="error" style="display:none;"><s:text name="error.house.exist" /></label>
            <label>(<s:text name="user.house.info.format" />)</label>
            
            <input type="hidden" maxlength="20" name="user.login.loginName" id="loginName" class="{required:true,useraccount:true}" value="yezhu<s:property value='house.name'/>" onkeyup="hideTip('existLoginNameMsg');"/>
           	<input type="hidden" maxlength="25" name="user.login.loginAlias" class="{required:true,maxlength:25}" value="yezhu<s:property value='house.name'/>" />
         	<input type="hidden" name="user.login.status" value="1"/>
        </td>
    </tr>
    <tr>
        <td width="120"><dfn></dfn><s:text name="user.name" /></td>
        <td>
            <input type="hidden" id="currUserId" name="user.userId" value="<s:property value='user.userId'/>"/>
            <input type="text" size="30" maxlength="25" name="user.userName" class="{required:true,maxlength:25}" value="<s:property value='user.userName'/>" />
        </td>
        <td width="120"><s:text name="common.title.gender" /></td>
        <td>
            <select name="user.gender">
                <option value="0" <s:if test="user.gender != '' && user.gender == 0">selected</s:if>>
                    <s:text name="common.title.gender.male" />
                </option>
                <option value="1" <s:if test="user.gender != '' && user.gender == 1">selected</s:if>>
                    <s:text name="common.title.gender.female" />
                </option>
            </select>
        </td>
    </tr>
    <tr>
        <td><dfn></dfn><s:text name="common.title.idcard" /></td>
        <td><input type="text" size="30" maxlength="30" name="user.idCard" class="{required:true,idcard:true}" value="<s:property value='user.idCard'/>" /></td>
        <td><dfn></dfn><s:text name="common.title.birthdate" /></td>
        <td>
            <input type="text" id="birthDate" name="user.birthDate" class="date_input" validateType="birthDate" required="true" dateFormat="yyyy-MM-dd" errorEleId="errorBirthDateMsg" value="<s:date name='user.birthDate' format='yyyy-MM-dd' />" onclick="SelectDate(this,'yyyy-MM-dd',0,-150);" onchange="checkUserBirthDate(this);" readonly="true" />
            <label id="errorBirthDateMsg" class="error" style="display:none;"></label>
        </td>
    </tr>
    <tr>
        <td><s:text name="common.title.homephone" /></td>
        <td><input type="text" maxlength="20" name="user.homePhone" class="{phone:true}" value="<s:property value='user.homePhone'/>" /></td>
        <td><s:text name="common.title.mobilephone" /></td>
        <td><input type="text" maxlength="20" name="user.mobilePhone" class="{mobilephone:true}" value="<s:property value='user.mobilePhone'/>" /></td>
    </tr>
    <tr>
        <td><s:text name="common.title.email" /></td>
        <td><input type="text" size="30" maxlength="50" name="user.email" class="{email:true,maxlength:50}" value="<s:property value='user.email'/>" /></td>
        <td><s:text name="common.title.address" /></td>
        <td><input type="text" size="50" maxlength="50" name="user.addr" class="{maxlength:50}" value="<s:property value='user.addr'/>" /></td>
    </tr>
    
</tbody>
<!--<thead>
	 <tr>
    	<th colspan="4" class="font_left"><s:text name="user.login.info"/>(<s:text name="user.login.init.pass"/><s:property value='%{#request.loginInitPass}'/>)</th>
    </tr> 
</thead>-->

<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>