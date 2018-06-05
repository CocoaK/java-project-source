<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login</title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />

<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/validate/check.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<!--[if IE 6]> 
    <script type="text/javascript"> alert("您所使用的浏览器版本过低，请使用IE8/IE9/Firefox/Chrome来浏览") </script> 
<![endif]--> 
<!--[if IE 7]> 
    <script type="text/javascript"> alert("您所使用的浏览器版本过低，请使用IE8/IE9/Firefox/Chrome来浏览") </script> 
<![endif]-->
<s:include value="/WEB-INF/views/common/include_common.jsp"></s:include>
</head>
<script type="text/javascript">
    var validCodeOpenFlag = true;
    getValidCodeFlag();
    getVersionCode();
    
    function locationAction(url){      
      window.location.href =url;          
    }
    
    function getValidCodeFlag(){
        $.ajax({
                url : '<s:url action="getValidCodeFlag" namespace="/json/login"/>',
                // 接受数据格式            
                dataType : 'json',
                cache:false,
                // 回调函数，接受服务器端返回给客户端的值，即result值          
                success : setValidCodeFlag
            });       
    }
     
    function getValidateCode(){
        $.ajax({
                url : '<s:url action="getValidationCode" namespace="/json/login"/>',
                // 接受数据格式            
                dataType : 'json',
                cache:false,
                // 回调函数，接受服务器端返回给客户端的值，即result值          
                success : show
            });       
    }
    
    //获得版本号
    function getVersionCode(){
        $.ajax({
                url : '<s:url action="getVersionCode" namespace="/json/login"/>',
                // 接受数据格式            
                dataType : 'json',
                cache:false,
                // 回调函数，接受服务器端返回给客户端的值，即result值          
                success : showVersionCode
            });       
    }
    
    function setValidCodeFlag(result){
    	validCodeOpenFlag = result.validCodeOpenFlag;
    }
    
    function changeValidCode(){
    	var validCodeObj = $("#validCodeId");
    	var lastUrl = validCodeObj.attr("src");
    	$("#validCodeId").attr("src",changeValidCodeUrl(lastUrl));
    }
    
    function changeValidCodeUrl(url){
    	var randomCode = (new Date()).valueOf();
    	if(url.indexOf("?") > -1){
    		if(url.indexOf("randomCode=") > -1)
    			url = url.substring(0, (url.indexOf("randomCode=") + 11)) + randomCode;
    		else
    			url = url + "&randomCode=" + randomCode;	
    	}else{
    		url = url + "?randomCode=" + randomCode;
    	}
    	
    	return url;
    }
    
    function show(result) {
        validCodeOpenFlag = result.validCodeOpenFlag;     
        document.getElementById("imageCode").innerHTML=result.verificationCode;;
    }
    
    //显示版本号
     function showVersionCode(result) {
        document.getElementById("versionCode").innerHTML=result.version;
    }
        
    function langChange(v){
        locationAction("<s:url action='changeLocale' namespace='/shop/login'/>?selectedLanguage="+v);
    }
    
    function checkValidationCode(){   	
    	if(!validCodeOpenFlag)
    		return true;
    	var isSuccess = false;
    	var errorMsgObj = $('#errorValidateCodeMsg');
    	var validationCode = $('#userRandomCode').val();
    	if(validationCode == ""){  		
    		errorMsgObj.html('<s:text name="error.required"/>');
    		errorMsgObj.show();
    		return isSuccess;
    	}
    	
    	var p = /^\w{5}$/;
    	isSuccess = p.test(validationCode);
    	if(!isSuccess){
    		errorMsgObj.html('<s:text name="login.page.validate.verification.code"/>');
    		errorMsgObj.show();
    	}else{
    		errorMsgObj.hide();
    	}
		return isSuccess;
    }
    
    function login(){
        var validateFlag = 0;
		if(!$($("form")[0]).valid())
			validateFlag++;
		
		if(!checkValidationCode())
			validateFlag++;
			
		if(validateFlag == 0){
            $("form")[0].submit();
            $("#submitbut").attr("disabled","disabled");
        }
    }
</script>
<body>
    <form action="<s:url action='go' namespace='/shop/login'/>" method="post">
        <div class="login_header">
            <div class="">
                <s:set name="SESSION_LOCALE" value="#session['WW_TRANS_I18N_LOCALE']"></s:set>
                <select id="language" onchange="langChange(this.value);" name="selectedLanguage" value='<s:property value="selectedLanguage"/>'>
                    <option value="zh"
                        <s:if test="selectedLanguage=='zh'">selected</s:if>>
                        <s:text name="login.page.languange.chinese" />
                    </option>
                    <option value="en"
                        <s:if test="selectedLanguage=='en'">selected</s:if>>
                        <s:text name="login.page.languange.english" />
                    </option>
                </select>
            </div>
            <div class=""></div>
            <div class=""></div>
        </div>

        <div class="login_article_shop">
            <div>
                <ul>
                    <li>
	                    <div>
	                        <label><s:text name="login.page.useraccount" />:</label>
	                        <input type="text" id="userAccount" name="login.loginName" size="20" value="<s:property value='login.loginName'/>" maxlength="20" class="{required:true,useraccount:true}"/>
	                    </div>
                    </li>
                    <li>
                        <div>
	                        <label><s:text name="login.page.psw" />:</label>
	                        <input type="password" id="userPassword" name="login.password" size="20" minlength="4" maxlength="20" class="{required:true,pass:true}"/>                   
                    	</div>
                    </li>
                    <li>
                    	<div>
	                        <label><s:text name="login.page.verification code" />:</label>
	                        <input type="text" id="userRandomCode" name="login.validCode" maxlength="5" size="5" onkeyup="checkValidationCode()"/>                                     
	                        <a href="javascript:changeValidCode();"><img id="validCodeId" src="<s:url action='getValidCode' namespace='/login'/>" ></a>
	                   	</div>
                    </li>
                    <li class="login_button">
	                    <div>
	                        <strong id="errorValidateCodeMsg" class="error" style="display:none;"></strong>
	                        <input type="submit" id="submitbut" onclick="login()" value='<s:text name="login.page.button.login"/>' />
	                    </div>
                    </li>
                </ul>
                <div>
                </div>
            </div>
        </div>
        <s:if test="promptFlag == true">
            <div id="errorMsgFrame" class="layer layerl">
                <div>
                    <p><s:text name="common.system.info"/></p>
                    <ul>
                        <li><s:actionerror/></li>
                        <li><a href="javascript:hideTip('errorMsgFrame');"><s:text name="common.element.action.confirm"/></a></li>
                    </ul>
                </div>
            </div>
        </s:if>
        <div class="login_footer">
            <s:text name="login.page.foot.info" />&nbsp;&nbsp;&nbsp;&nbsp;<s:text name="login.page.version"/>:<i id="versionCode"></i>
        </div>
    </form>
</body>
</html>