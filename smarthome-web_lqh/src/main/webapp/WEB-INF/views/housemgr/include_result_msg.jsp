<%@ page language="java" pageEncoding="UTF-8"
    contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<s:if test="successFlag == true">                                       
    <div class="layer" id="confirmFrame">
        <div>
            <p><s:text name="common.system.info"/></p>
            <ul>
            	<li>
                	<s:text name="common.element.action.operationSuccess" /><br /><br />
                </li>
                <li>
                	<a href="javascript:hideTip('confirmFrame');"><s:text name="common.element.action.confirm"/></a>
                </li>
            </ul>
	    </div>
    </div>
</s:if>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<script type="text/javascript">
    var validFileExts = "<s:property value='#request.picExt'/>";
</script>