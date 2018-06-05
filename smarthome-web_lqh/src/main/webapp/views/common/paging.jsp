<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<script language="javascript" type="text/javascript">	
	var pn = /^[1-9]\d*$/;
	function topage(pageNum) {	
		var maxPageNum = "<s:property value='page.totalPages' />";
		if(!pn.test(pageNum) || parseInt(pageNum, 10) > parseInt(maxPageNum, 10)){
			$('#topageNum').val("");
			return;
		}	
		
		var form = document.forms[0];	
		$('#pageNum').val(pageNum);
		form.submit();
	}
	
	function queryPagingList(){
		initPageNum();
		$("form")[0].submit();
	}
	
	function initPageNum(){
		$('#pageNum').val(1);
	}
	function enterEvent(){
			topage($('#topageNum').val());
	}
	$(document).ready(function()
			{
			   $(document).keyup(function(e){
			   var curKey = e.keyCode;
			   if(curKey == 13)
			   {
				   enterEvent();
			   }
			   });
			});
</script>

<div class="page">
    <s:hidden id="pageNum" name="page.pageNum"/>
    <div>
        <p>
            <i><s:text name="common.paging.totalPages" /><s:property value="page.totalPages" /><s:text name="common.paging.page.unit" />/<s:text name="common.paging.totalCount" /><s:property value="page.totalCount" /><s:text name="common.paging.record.unit" /></i>
            <i><s:text name="common.paging.pageSize" />
                <strong>
                    <select name="page.pageSize" id="page.pageSize" onchange="topage(1)">
                    	<option value="10">10</option>
                    	<option value="20">20</option>
                    	<option value="50">50</option>
                    	<option value="100">100</option>
                    </select>                    
                </strong><s:text name="common.paging.record.unit" />
            </i>
            <i><s:text name="common.paging.currentPage" /><s:property value="page.pageNum" /><s:text name="common.paging.page.unit" /></i>
            
            <s:if test="page.pageNum > 1">
                <a href="javascript:topage(1)"><s:text name="common.paging.firstPage" /></a>
                <a href="javascript:topage('<s:property value="page.pageNum - 1"/>')"><s:text name="common.paging.previousPage" /></a>               
            </s:if>
            <s:else>
                <span><s:text name="common.paging.firstPage" /></span>
                <span><s:text name="common.paging.previousPage" /></span>
            </s:else>

            <s:if test="page.pageNum < page.totalPages">
                <a href="javascript:topage('<s:property value="page.pageNum + 1"/>')"><s:text name="common.paging.nextPage" /></a>
                <a href="javascript:topage('<s:property value="page.totalPages"/>')"><s:text name="common.paging.lastPage" /></a>                
            </s:if>
            <s:else>
                <span><s:text name="common.paging.nextPage" /></span>
                <span><s:text name="common.paging.lastPage" /></span>
            </s:else>

            <i><s:text name="common.paging.jump" /></i><input type="text" id="topageNum" name="topageNum" class="page_tex" size="2"/>
            <i><s:text name="common.paging.page.unit" /></i><input type="button" value="<s:text name='common.element.action.confirm' />" onclick="topage($('#topageNum').val())" class="page_but" />
        </p>
    </div>
</div>
<script type="text/javascript">
function optionSelectInt(backValue, targetId,maxOptionsSum) {
	  var targetSelected=document.getElementById(targetId);
	  if(backValue!=''){
		  for(var i=1;i<maxOptionsSum;i++){
			try {
				  if(targetSelected.options[i].value==backValue) targetSelected.options[i].selected=true;
			} catch (e) {
			}
		  }
	  }
}
optionSelectInt('<s:property value='page.pageSize'/>',"page.pageSize", 4);
</script>

