<%@ page language="java" pageEncoding="UTF-8"
    contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<thead>
    <tr>
        <th colspan="4" class="font_left">
        <s:text name="pauser.management.info" />
        </th>
    </tr>
</thead>
<tbody>
    <tr>
        <td width="120"><dfn></dfn><s:text name="user.name" /></td>
        <td>
            <input type="hidden" name="user.userId" value="<s:property value='user.userId'/>"/>
            <input type="text" size="30" maxlength="25" name="user.userName" class="{required:true,maxlength:25}" value="<s:property value='user.userName'/>" />
        </td>
        <td class="mainput">
            <s:if test="districtName == null || districtName == ''"><dfn></dfn></s:if>
            <s:text name="common.title.districtname" />
        </td>
        <td class="mainput room_hack">
            <s:if test="districtName != null && districtName != ''">
                <s:property value='districtName'/>
            </s:if>
            <s:else>
                <input type="hidden" id="selectedDistrictId" name="user.districtId" value="<s:property value='user.districtId'/>" />
                <input type="text" id="selectedDistrictName" size="30" name="user.districtName" value="<s:property value='user.districtName'/>" disabled="disabled" />
                <a href="javascript:void(0);queryDistricts('<s:url action="getDistrictsByName" namespace="/housemgr/json"/>');" class="notetitle all_hover_but"><s:text name="common.element.action.select" /></a>
                <label id="unselectedDistrictMsg" class="error" style="display:none;"><s:text name="error.required" /></label>
            </s:else>           
        </td>
    </tr>
    <tr>
    	<td width="120"><s:text name="common.title.gender" /></td>
        <td>
            <select name="user.gender">
                <option value="0"
                    <s:if test="user.gender != '' && user.gender == 0">selected</s:if>>
                    <s:text name="common.title.gender.male" />
                </option>
                <option value="1"
                    <s:if test="user.gender != '' && user.gender == 1">selected</s:if>>
                    <s:text name="common.title.gender.female" />
                </option>
            </select>
        </td>
        <td><s:text name="common.title.degree" /></td>
        <td>
            <input type="text" maxlength="25" name="user.degree" class="{maxlength:25}" value="<s:property value='user.degree'/>" />
        </td>
    </tr>
    <tr>
        <td><s:text name="user.department" /></td>
        <td>
            <input type="text" size="30" maxlength="25" name="user.department" class="{maxlength:25}" value="<s:property value='user.department'/>" />
        </td>
        <td><s:text name="user.post" /></td>
        <td>
            <input type="text" size="30" maxlength="25" name="user.post" class="{maxlength:25}" value="<s:property value='user.post'/>" />
        </td>
    </tr>
    <tr>
        <td><dfn></dfn><s:text name="common.title.idcard" /></td>
        <td>
            <input type="text" size="30" maxlength="30" name="user.idCard" class="{required:true,idcard:true}" value="<s:property value='user.idCard'/>" />
        </td>
        <td><dfn></dfn><s:text name="common.title.birthdate" /></td>
        <td>
            <input type="text" name="user.birthDate" class="date_input" validateType="birthDate" required="true" dateFormat="yyyy-MM-dd" errorEleId="errorBirthDateMsg" value="<s:date name='user.birthDate' format='yyyy-MM-dd' />" onclick="SelectDate(this,'yyyy-MM-dd',0,-150)" onchange="checkUserBirthDate(this);" readonly="true" />
            <label id="errorBirthDateMsg" class="error" style="display:none;"></label>
        </td>
    </tr>
    <tr>
        <td><s:text name="common.title.email" /></td>
        <td>
            <input type="text" size="30" maxlength="50" name="user.email" class="{email:true,maxlength:50}" value="<s:property value='user.email'/>" />
        </td>
        <td><s:text name="common.title.address" /></td>
        <td>
            <input type="text" size="50" maxlength="50" name="user.addr" class="{maxlength:50}" value="<s:property value='user.addr'/>" />
        </td>
    </tr>
    <tr>
        <td><s:text name="common.title.officephone" /></td>
        <td>
            <input type="text" maxlength="20" name="user.officePhone" class="{phone:true}" value="<s:property value='user.officePhone'/>" />
        </td>
        <td><s:text name="common.title.mobilephone" /></td>
        <td>
            <input type="text" maxlength="20" name="user.mobilePhone" class="{mobilephone:true}" value="<s:property value='user.mobilePhone'/>" />
        </td>
    </tr>
    <tr>
        <td><s:text name="user.intro" /></td>
        <td colspan="3">
            <textarea id="intro" name="user.intro" class="{maxlength:100}"><s:property value='user.intro' /></textarea>
        </td>
    </tr>
</tbody>
<thead>
    <tr>
        <th colspan="4" class="font_left">
            <s:text name="user.login.info" />(<s:text name="user.login.init.pass"/><s:property value='%{#request.loginInitPass}'/>)
        </th>
    </tr>
</thead>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery.textbox.js'/>"></script>
<script type="text/javascript">            
	$(function() {
    	$("#intro").textbox({
        	maxLength: 100
        }); 
    }); 
</script>