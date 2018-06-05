<%@ page language="java" pageEncoding="UTF-8"
    contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<s:if test="unallowedFlag == true">
    <div class="layer" id="confirmFrame">
        <div>
            <p>
                <s:text name="common.system.info" />
            </p>
            <ul>
                <li>
                    <s:text name="error.role.undefined" /><br/><br/>
                </li>
            </ul>
        </div>
    </div>
</s:if>
