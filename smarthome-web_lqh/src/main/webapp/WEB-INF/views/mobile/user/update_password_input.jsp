<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title><s:text name='M003501'/></title>
        <link href="<s:url value='/mobile/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
        <script type="text/javascript">
            var requiredErrMsg = "<s:text name='error.mobile.required'/>";
            var formatErrMsg = "<s:text name='error.format'/>";
            var notEqualErrMsg = "<s:text name='error.not.equal'/>";
            
            function updatePass(){
                var validateFlag = 0;                 
                if(!validatePwd("currPass", "currPassErr"))
                    validateFlag++;
                
                if(!validatePwd("newPass", "newPassErr"))
                    validateFlag++;
                    
                if(!validatePwd("confirmNewPass", "confirmNewPassErr"))
                    validateFlag++;
                
                if(!isEqualPass())
                	validateFlag++;
                    
                if(validateFlag == 0){
                    $("form")[0].submit();
                    $("#submitUpdatePass").attr("disabled","disabled");
                }
            }           
                      
            function validatePwd(pwdId, pwdErrID){
                var pwdErrObj = $("#" + pwdErrID);
                var pwdVal = $.trim($("#" + pwdId).val());
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
            
            function isEqualPass(){
            	var pwdErrMsg = "";
            	var pwdErrObj = $("#confirmNewPassErr");
            	var confirmNewPass = $("#confirmNewPass").val();
            	var newPass = $("#newPass").val();
            	if(newPass != "" && confirmNewPass != "" && confirmNewPass != newPass)
            		pwdErrMsg = notEqualErrMsg;
            	
            	if(pwdErrObj.text() == "")
            		pwdErrObj.html(pwdErrMsg);                  
                return (pwdErrMsg == "")?true:false;
            }
        </script>
    </head>
    
    <body>
        <s:include value="/WEB-INF/views/mobile/head.jsp"/>
        
        <form action="<s:url action='updatePass' namespace='/mobile/login'/>" method="post">
            <div class="list_top"><s:text name="user.pass.update"/></div>
            
            <table cellspacing="0" class="list_tab list_tabright">
                <tbody>
                    <tr>
                        <td class="tab_none"></td>                        
                        <td>
                            <p>
                                <s:text name="user.update.current.pass"/><dfn>*</dfn><span id="currPassErr"></span>
                            </p>
                            <p>
                                <input id="currPass" type="password" maxlength="20" name="currPass" onchange="validatePwd('currPass', 'currPassErr');"/>
                            </p>                        
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <p>
                                <s:text name="user.update.new.pass"/><dfn>*</dfn><span id="newPassErr"></span>
                            </p>
                            <p>
                                <input id="newPass" type="password" maxlength="20" name="newPass" onchange="validatePwd('newPass', 'newPassErr');" />
                            </p>                        
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <p>
                                <s:text name="user.update.confirm.new.pass"/><dfn>*</dfn><span id="confirmNewPassErr"></span>
                            </p>
                            <p>
                                <input id="confirmNewPass" type="password" maxlength="20" name="confirmNewPass" onchange="validatePwd('confirmNewPass', 'confirmNewPassErr');isEqualPass();" />
                            </p>                        
                        </td>                          
                    </tr>
                </tbody>
            </table>
            
            <div class="submit">
                <input id="submitUpdatePass" type="button" onclick="updatePass();" value="<s:text name='common.element.action.confirm'/>" />
            </div>
            
            <s:if test="promptFlag == true">
                <div id="errorMsgFrame" class="layer">
                    <div><s:actionmessage /><a href="javascript:hideTip('errorMsgFrame');"><s:text name="common.element.action.close" /></a></div>
                </div>
            </s:if>
        </form>
    </body>
</html>