<%@ page language="java" pageEncoding="UTF-8"
    contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:if test="promptFlag == true">
    <div class="layer" id="promptFrame">
        <div>
            <p>
                <s:text name="common.system.info" />
            </p>
            <ul>
                <li><s:text name="common.element.action.operationSuccess" /><br /><br /></li>
                <li><a href="javascript:hideTip('promptFrame');"><s:text name="common.element.action.confirm" /></a></li>
            </ul>
        </div>
    </div>
</s:if>
<s:include value="include_common.jsp"/>