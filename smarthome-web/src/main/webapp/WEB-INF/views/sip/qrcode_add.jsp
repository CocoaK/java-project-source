<%@ page language="java" import="java.util.*,com.biencloud.smarthome.web.common.vo.*" pageEncoding="UTF-8"%>
<%@ page import="com.biencloud.smarthome.web.wsclient.stub.*"%> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>二维码生成</title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<s:include value="/WEB-INF/views/common/include_common.jsp"></s:include>

</head>

<body>
	<form action="<s:url action="add"/>" method="post" target="_blank" id="form1">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                    	<a href="<s:url action="queryReqList" namespace="/qrcode"/>" >二维码申请列表</a>
                        <a href="#" class="all_menu_a">二维码生成</a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                            <tbody>
                            	<tr>
	                                <td width="40%">请选择生成方式</td>
	                                <td>
	                                	<select id="select" onchange="selectType(this.value);">
	                                		<option value="1">自动SIP账号</option>
	                                		<option value="2">自定义输入</option>
	                                	</select>
	                                </td>
	                          	</tr>
	                            <tr>
	                                <td width="40%">请输入门口机SIP账号</td>
	                                <td>
	                                	<input type="text" maxlength="20" name="qrcode.doorSipid" class="{required:true,number:true}" value="<s:property value='qrcode.doorSipid'/>" />
	                                	<input type="hidden" name="sipRegister.type" value="0" />
	                                </td>
	                          	</tr>
	                          	<tr>
	                                <td>请输入完整房号</td>
	                                <td>
	                                	<input type="text" maxlength="15" name="qrcode.roomNo" class="{required:true,number:true,minlength:15}" value="<s:property value='qrcode.roomNo'/>" />
	                                	<i>格式为：100102003040501</i>
	                                </td>
	                            </tr>
	                            <!-- <tr>
	                                <td>请输入SIP账号密码</td>
	                                <td><input type="text" maxlength="20" name="qrcode.pwd" class="{required:true}" value="<s:property value='qrcode.pwd'/>" /></td>
	                            </tr> -->
	                            <tr id="tr1"></tr>
	                            <tr id="tr2"></tr>

                        	</tbody>
                        </table>
                        <div class="all_tab_butb"><input type="button" onclick="check();" value="<s:text name='common.element.action.add' />" /></div>
                    	<s:include value="/WEB-INF/views/housemgr/include_result_msg.jsp"/>
                    </div>
                </div>
            </div>
        </form>
		<script>
			QueryLoader.selectorPreload = "body";
			QueryLoader.init();
		
			function selectType(value){
				var html = "<td class='all_tab_bscor'>请输入手机SIP账号</td>"+
						   "<td><input id='sipUid' type='text' maxlength='20' name='qrcode.sipUid' class='{required:true,number:true}' value='<s:property value='qrcode.sipUid'/>' /><i id='tt'></i></td>";
				if(value=='1'){
					$("#tr1").html("");
				}else if(value=='2'){
					$("#tr1").html(html);
				}
			};
			
			function check(){
				//var reg=new RegExp("^[1-9][0-9]{3,14}$");
				var sipUid = $("#sipUid").val();
				var flag = $("#select").val();
				//自动生成SIP账号，不验证直接返回true
				if(flag == 1){
					$("#form1").submit();
				}else{
					$.ajax({
							url : "<%=path%>sip/json/isExist?qrcode.sipUid="+sipUid,
							// 数据发送方式 
							type : "post",
							// 接受数据格式            
							dataType : 'json',
							cache:false,
							// 要传递的数据       
							//data:{'loginName':'loginName'},
							//data : params,
							// 回调函数，接受服务器端返回给客户端的值，即result值          
							success : function(data, textStatus){
								if(data.code==1){
									$("#tt").text("");
									$("#form1").submit();
								}
								else if(data.code==0){
									$("#tt").html("<font color='red'>此SIP账号不存在!</font>");
								}
			         		},
			         		error: function(){
			         		}
					});
				}
		        
			}
    	</script>
</body>
</html>
