<%@ page language="java" pageEncoding="UTF-8"
    contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<table cellspacing="0" class="all_tab_body all_tab_bodys">
    <tbody>
        <tr>
            <td width="120"><dfn></dfn><s:text name="ad.name" /></td>
            <td>
                <input type="text" maxlength="20" name="ad.adName" class="{required:true,maxlength:20}" value="<s:property value='ad.adName'/>" />
                <input type="hidden" id="adId" name="ad.adId" value="<s:property value='ad.adId'/>" />
            </td>
        </tr>
        <tr>
            <td><s:text name="ad.type" /></td>
            <td class="sam_web">
                <div class="select_div">
                    <select name="ad.adType.typeCode">
                        <s:iterator value="%{adTypes}" var="at">
                            <option value="<s:property value='#at.typeCode'/>" <s:if test="ad.adType.typeCode == #at.typeCode">selected</s:if>>
                                <s:property value="#at.typeName" />
                            </option>
                        </s:iterator>
                    </select>
                </div>
            </td>
        </tr>
        <tr>
            <td><dfn></dfn><s:text name="ad.runningtime" /></td>
            <td class="sam_web">
                <input type="text" id="beginTime" name="ad.adBeginTime" value="<s:date name='ad.adBeginTime' format='yyyy-MM-dd HH:mm' />" dateFormat="yyyy-MM-dd hh:mm" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" onchange="validateDateRange()" readonly="true" class="time_input" />
                <label id="beginTimeErrorMsg" class="error" style="display:none;"></label>
                <label>---</label>
                <input type="text" id="endTime" name="ad.adEndTime" value="<s:date name='ad.adEndTime' format='yyyy-MM-dd HH:mm' />" dateFormat="yyyy-MM-dd hh:mm" onclick="SelectDate(this,'yyyy-MM-dd hh:mm',0,-150)" onchange="validateDateRange()" readonly="true" class="time_input" />
                <label id="endTimeErrorMsg" class="error" style="display:none;"></label>
                <label id="notGreaterCurrentTimeMsg" class="error" style="display:none;"><s:text name="error.currenttime.notgreater"/></label>
                <label id="notGreaterBeginTimeMsg" class="error" style="display:none;"><s:text name="error.begintime.notgreater"/></label>
            </td>
        </tr>
        <tr>
            <td><s:if test="ad == null || ad.adId == null || ad.adId == ''"><dfn></dfn></s:if><s:text name="ad.upload" /></td>
            <td class="up_img">
                <div>
                    <input type="text" name="adPicName" id="adPicName" class="viewfile" size="50" readonly />                  
                    <label for="upload" class="all_hover_but file_label"><s:text name="common.element.action.select" /></label>
                    <input type="file" name="adPicFile" onchange="selectPicName(this.value);hideTip('errorFileSizeMsg');" class="file" />
                    <label id="picErrorMsg" class="error" style="display:none;"></label>
                    <s:if test="errorFlag == true">
                        <label id="errorFileSizeMsg" class="error">
                            <s:if test="adPicErrorMsg != null && adPicErrorMsg != ''">
                                <s:text name="error.file.too.large"/>
                            </s:if>
                            <s:fielderror><s:param>adPicFile</s:param></s:fielderror>                        
                        </label>                       
                    </s:if>                   
                    <input type="hidden" name="ad.adPicUrl" value="<s:property value='ad.adPicUrl'/>" />
                </div>
                <p>
                	<i><s:text name='common.title.file.msg'><s:param><s:property value='#request.picExt'/></s:param><s:param>10M</s:param></s:text></i>
                </p>
            </td>
        </tr>
        <tr>
            <td><dfn></dfn><s:text name="ad.link.address" /></td>
            <td>
                <input type="text" maxlength="100" name="ad.adLinkUrl" class="{required:true,maxlength:100}" size="50" value="<s:property value='ad.adLinkUrl'/>" />
            </td>
        </tr>
        <tr>
            <td>
                <s:text name="ad.location" />
                <input type="hidden" name="ad.adLocation.adSys.sysCode" value="<s:property value='ad.adLocation.adSys.sysCode'/>" />
            </td>
            <td class="sam_web">
                <div class="select_div">
                    <select id="locationCode" name="ad.adLocation.locationCode">
                        <s:iterator value="%{adLocations}" var="al">
                            <option value="<s:property value='#al.locationCode'/>" <s:if test="ad.adLocation.locationCode == #al.locationCode">selected</s:if>>
                                <s:property value="#al.locationName" />
                            </option>
                        </s:iterator>
                    </select>
                </div>
            </td>
        </tr>
        <tr>
            <td><s:text name="ad.desc" /></td>
            <td>
                <textarea id="adDesc" name="ad.adDesc" class="{maxlength:100}" ><s:property value="ad.adDesc" /></textarea>
            </td>
        </tr>
    </tbody>
</table>
<script type="text/javascript" src="<s:url value='/js/jquery.textbox.js'/>"></script>
<script type="text/javascript">            
    $(function() {
        $("#adDesc").textbox({
            maxLength: 100
        }); 
    }); 
</script>