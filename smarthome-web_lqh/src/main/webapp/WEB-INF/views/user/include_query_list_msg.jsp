<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="layer" id="enableOrDisableConfirmFrame" style="display:none;">
    <div>
        <p id="enableOrDisableMsg"></p>
        <ul>
            <li>
                <input type="hidden" id="enableOrDisableUrl" name="enableOrDisableUrl" />
                <input type="hidden" id="loginId" name="loginId" />
                <input type="hidden" id="status" name="status" />
                <a id="enableOrDisable" href="javascript:enableOrDisable();">
                    <s:text name="common.element.action.confirm" />
                </a>
                <a href="javascript:hideTip('enableOrDisableConfirmFrame');">
                    <s:text name="common.element.action.cancel" />
                </a>
            </li>
        </ul>
    </div>
</div>

<div class="layer" id="enableOrDisableFrame" style="display:none;">
	<div>
    	<p><s:text name="common.system.info"/></p>
        <ul>
        	<li>
            	<s:text name="common.element.action.operationSuccess"/>
                <br/><br/>
            </li>
            <li>
            	<a href="javascript:hideTip('enableOrDisableFrame');"><s:text name="common.element.action.confirm"/></a>
            </li>
    	</ul>
	</div>
</div>

<div class="layer" id="resetPassConfirmFrame" style="display:none;">
    <div>
        <p id="resetPassConfirmMsg"></p>
        <ul>
            <li>
                <input type="hidden" id="resetPassUrl" name="resetPassUrl" />
                <input type="hidden" id="currLoginName" name="currLoginName" />
                <a id="resetPass" href="javascript:resetPass();">
                    <s:text name="common.element.action.confirm" />
                </a>
                <a href="javascript:hideTip('resetPassConfirmFrame');">
                    <s:text name="common.element.action.cancel" />
                </a>
            </li>
        </ul>
    </div>
</div>

<div class="layer" id="resetPassFrame" style="display:none;">
    <div>
    	<p><s:text name="common.system.info"/></p>
        <ul>
        	<li>
                <s:text name="common.resetpass.success" /><br/><br/>
            </li>
            <li>
            	<a href="javascript:hideTip('resetPassFrame');"><s:text name="common.element.action.confirm"/></a>
            </li>
        </ul>
    </div>
</div>

<script>
	var enableAction = "<s:text name='common.element.action.enable'/>";
	var disableAction = "<s:text name='common.element.action.disable'/>";
            
	var confirmEnableMsg = "<s:text name='user.confirm.enable'/>";
	var confirmDisableMsg = "<s:text name='user.confirm.disable'/>";                                
</script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>