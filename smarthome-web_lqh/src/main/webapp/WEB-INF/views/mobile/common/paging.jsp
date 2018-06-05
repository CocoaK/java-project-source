<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script language="javascript" type="text/javascript">
    function toPage(pageNum) {    
        $('#pageNum').val(pageNum);
        $("form")[0].submit();
    }
    
    function queryPagingList(){
        initPageNum();
        $("form")[0].submit();
    }
    
    function initPageNum(){
        $('#pageNum').val(1);
    }
</script>

<s:hidden id="pageNum" name="page.pageNum"/>

<div class="submit">    
    <s:if test="page.pageNum > 1">
        <a href="javascript:toPage('<s:property value="page.pageNum - 1"/>')" class="left_page"><s:text name="common.paging.previousPage" /></a>
    </s:if>
    <s:else>
        <a href="#" class="left_page page_none"><s:text name="common.paging.previousPage" /></a>
    </s:else>
    <strong><s:text name="common.paging.currentPage" /><s:if test="page.totalPages == 0">0</s:if><s:else><s:property value="page.pageNum" /></s:else>/<s:property value="page.totalPages" /></strong>
    <s:if test="page.pageNum < page.totalPages">
        <a href="javascript:toPage('<s:property value="page.pageNum + 1"/>')" class="right_page"><s:text name="common.paging.nextPage" /></a>
    </s:if>
    <s:else>
        <a href="#" class="right_page page_none"><s:text name="common.paging.nextPage" /></a>
    </s:else>
</div>