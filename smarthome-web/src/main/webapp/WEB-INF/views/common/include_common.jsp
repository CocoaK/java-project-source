<%@ page language="java" pageEncoding="UTF-8"
    contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="<s:url value='/js/jquery-validation/jquery.validate.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-validation/validate.extend.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-validation/localization/messages_cn.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-validation/localization/messages_extend_cn.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-validation/lib/jquery.metadata.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-validation/additional-methods.js'/>"></script>

<s:if test="#session['language'] == 'en'">
    <script type="text/javascript" src="<s:url value='/js/jquery-validation/localization/messages_en.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/js/jquery-validation/localization/messages_extend_en.js'/>"></script>
</s:if>
<script type="text/javascript">
	$(document).ready(function() {
		var forms = $("form");
		$.each(forms, function(index, item) {
			$(item).validate();
		});			
	});
	
	var noDataMsg = "<s:text name='common.title.nodata'/>";
	var fileNameMaxLength = 30;  
    var fileNameTooLongMsg = "<s:text name='error.upload.filename.too.long'><s:param>" + fileNameMaxLength + "</s:param></s:text>";
    var errorRequired = "<s:text name='error.required'/>";
    var errorFormatMsg = "<s:text name='error.uploadfile.format'/>";
    var errorBirthDate = "<s:text name='error.birthdate'/>";
    
    function validateFileName(fileName, errorMsgObj, validFileExts, ignoreEmpty){
    	if(ignoreEmpty && fileName == '')
    		return true;
    	
    	if(!ignoreEmpty && fileName == ''){
    		errorMsgObj.html(errorRequired);
    		errorMsgObj.show();
    		return false;
    	}	
    	
    	var extensionIndex = fileName.lastIndexOf(".") + 1;
    	if(extensionIndex < 1 
    			|| !isValidFileExt(validFileExts, fileName.substring(extensionIndex))){
    		errorMsgObj.html(errorFormatMsg);
    		errorMsgObj.show();
    		return false;
    	}
    	
    	var nameStartIdx = fileName.lastIndexOf("\\") + 1;
    	if(nameStartIdx > 0 && fileName.substring(nameStartIdx).length > fileNameMaxLength){
    		errorMsgObj.html(fileNameTooLongMsg);
    		errorMsgObj.show();
    		return false;
    	}
    	
    	errorMsgObj.hide();	
    	return true;
    }
    
    function isValidFileExt(validFileExts, extName){	
    	extName = extName.toUpperCase();
    	var exts = validFileExts.split(",");
    	for(var val in exts){
    		if(exts[val].toUpperCase() == extName)
    			return true;
    	}
    	
    	return false;
    }
</script>