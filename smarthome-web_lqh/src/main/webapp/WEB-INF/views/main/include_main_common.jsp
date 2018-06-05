<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:if test="ads != null && ads.size() > 0">
    <div class="main_ad">
        <div id="main_ads" class="main_ads">
        	<div class="bd">
	            <ul>
	                <s:iterator value="%{ads}" var="ad">
	                    <li>
	                        <a href="<s:property value="#ad.adLinkUrl"/>" target="_blank"><img src="<s:property value="#ad.adPicUrl"/>" width="100%" height="100%"/></a>
	                    </li>
	                </s:iterator>
	            </ul>
	        </div>
        </div>
        <script type="text/javascript">jQuery(".main_ads").slide( { mainCell:".bd ul",effect:"left",autoPlay:true} );</script>
    </div>
</s:if>