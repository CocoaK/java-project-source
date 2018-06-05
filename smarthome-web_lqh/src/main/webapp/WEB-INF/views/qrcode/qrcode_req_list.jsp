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
<title>二维码管理</title>
<link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>" ></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<s:include value="/WEB-INF/views/common/include_common.jsp"></s:include>
<script type="text/javascript">
	function postUpdate(id){
		var reg=new RegExp("^[1-9][0-9]{3,14}$");
		var pwd = $("#pwd"+id).val();
		var doorSipid = $("#doorSip"+id).val();
		if(doorSipid==null || doorSipid==""){
			$("#info"+id).text("*不能为空");
			return;
		}else if(reg.test(doorSipid)!=true){
			$("#info"+id).text("只能为4-15位数字");
			return;
		}else{
			$("#info"+id).text("");
		}
        $.ajax({
				url : "<%=path%>qrcode/json/update?qrcodeVO.id="+id+"&qrcodeVO.doorSipid="+doorSipid+"&sipRegister.type=0",
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
         			$("#a"+id).hide();
         			$("#doorSip"+id).attr("readonly","readonly");
         			$("#sipUid"+id).text(data.sipUid);
         			$("#id"+id).text("已添加");
         		},
         		error: function(){
         		}
		});
	}
</script>
</head>

<body>
	<form action="<s:url action="queryList"/>" method="post">    
            <div class="main_section">
                <div class="all_aside">
                    <div class="all_menu">
                        <a href="#" class="all_menu_a">二维码申请列表</a>
                        <a href="<s:url action="addInput" namespace="/sip"/>">二维码生成</a>
                    </div>
                </div>
                <div class="main_outside">
                    <div class="all_inside">
                        <table cellspacing="0" class="all_tab_body">
                            <thead class="all_tab_thead">
                                <tr>
                                    <th>二维码编号</th>
                                    <th>房号</th>
                                    <th width="200">二维码SIP账号</th>
                                    <th width="300">门口机SIP账号</th>
                                    <th>类型</th>
                                    <th><s:text name="common.element.action.status"/></th>
                                    <th>申请时间</th>
                                    <th><s:text name="common.element.title.action"/></th>
                                </tr>
                            </thead>
                            <tbody class="all_tab_bodym">
                                <s:iterator value="qrcodeVOs" var="qrcodeVO">
                                    <tr>
                                        <td><s:property value="#qrcodeVO.id"/></td>
                                        <td><s:property value="#qrcodeVO.roomNo"/></td>
                                        <td><i id="sipUid<s:property value="#qrcodeVO.id"/>"></i></td>
                                        <td><input type="text" id="doorSip<s:property value="#qrcodeVO.id"/>" name="doorSipid"/ maxlength="20" class="{required:true}">
                                        	<i id="info<s:property value="#qrcodeVO.id"/>" style="color:red"></i></td>
                                        <td><s:property value="#qrcodeVO.type"/></td>
                                        <td>
                                        	<s:if test="#qrcodeVO.status==2">
                                        		申请中
                                        	</s:if>
                                        </td>
                                        <td><s:date name="#qrcodeVO.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
	                                    <td>
	                                        <i id="id<s:property value="#qrcodeVO.id"/>"></i>
	                                        <a href="#" id="a<s:property value="#qrcodeVO.id"/>" onclick="postUpdate(<s:property value='#qrcodeVO.id'/>)" class="all_hover_but">添加</a>
	                                    </td>
                                    </tr>
                                </s:iterator>                       
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </form>
</body>
</html>
