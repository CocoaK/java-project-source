<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title><s:text name='head.title.login'/></title>
        <link href="<s:url value='/mobile/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript">
        	var requiredErrMsg = "<s:text name='error.mobile.required'/>";
        	var formatErrMsg = "<s:text name='error.format'/>";
        	
        	function login(){
            	var validateFlag = 0;
            	
            	if(!validateAcc())
            		validateFlag++;
            		
            	if(!validatePwd())
            		validateFlag++;
            		
            	if(validateFlag == 0){
            		$("form")[0].submit();
                	$("#submitLogin").attr("disabled","disabled");
                }
            }
            
            function validateAcc(){
            	var accErrObj = $("#userAccountError");
            	var accVal = $.trim($("#userAccount").val());
            	var accErrMsg = "";
            	if(accVal == ""){
            		accErrMsg = requiredErrMsg;
            	}else{
            		if(!validateUserAcc(accVal))
            			accErrMsg = formatErrMsg;
            	}
            	
            	accErrObj.html(accErrMsg);          		
            	return (accErrMsg == "")?true:false;
            }
                      
            function validatePwd(){
            	var pwdErrObj = $("#userPasswordError");
            	var pwdVal = $.trim($("#userPassword").val());
            	var pwdErrMsg = "";
            	if(pwdVal == ""){
            		pwdErrMsg = requiredErrMsg;
            	}else{
            		if(!validatePassword(pwdVal))
            			pwdErrMsg = formatErrMsg;
            	}
            	
            	pwdErrObj.html(pwdErrMsg);          		
            	return (pwdErrMsg == "")?true:false;
            }
        </script>
    </head>
    
    <body class="login">
        <form action="<s:url action='go' namespace='/mobile/login'/>" method="post">
            <img src="<s:url value='/mobile/images/login.png'/>" class="login_bg" />
            <div class="login_logo"><img src="<s:url value='/mobile/images/login_logo.png'/>" /></div>
            <div class="login_content">
                <ul>
                    <li><strong><s:text name="login.page.useraccount" /></strong><span id="userAccountError"></span></li>
                    <li><input type="text" id="userAccount" name="login.loginName" value="<s:property value='login.loginName'/>" maxlength="20" onchange="validateAcc();" /></li>
                    <li><strong><s:text name="login.page.psw" /></strong><span id="userPasswordError"></span></li>
                    <li><input type="password" id="userPassword" name="login.password" minlength="4" maxlength="20" onchange="validatePwd();" /></li>   
                </ul>
                <p><input id="submitLogin" type="button" onclick="login();" value="<s:text name='login.page.button.login'/>" /><p>
            </div> 
            
            <s:if test="promptFlag == true">
                <div id="errorMsgFrame" class="layer">
                    <div><s:actionerror/><a href="javascript:hideTip('errorMsgFrame');"><s:text name="common.element.action.close" /></a></div>
                </div>
            </s:if>          
        </form>
    </body>
</html>