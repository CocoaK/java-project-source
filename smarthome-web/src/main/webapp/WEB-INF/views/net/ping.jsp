<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.io.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<base href="<%=basePath%>">



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="net.ping"/></title>
<link href="<%=basePath%>css/layout.css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>

<script type="text/javascript" src="<%=basePath%>js/jquery_all.js"></script>


</head>
<script type="text/javascript">
        var loop=1;
        var backResult="";
        var count=1;
        
		
		function ping()
       {
        
        var ip=document.getElementById("ip").value;
        var action=document.getElementById("action").value;
        var url="<%=basePath%>net/netAction_ping.action?net.ip="+ip+"&net.action="+action;
        
        if(ip!="")
        {
          if(checkIP())
          {//alert("2");
           document.getElementById("start").disabled=true;
           //document.getElementById("stop").disabled=false;
           document.getElementById("ip").disabled=true;
           document.getElementById("result").innerHTML="System is executing ping command...";
           $.ajax({
				url :url,
				// 数据发送方式            
				//type : "post",
				// 接受数据格式            
				dataType : 'json',
				cache:false,
				// 要传递的数据            
				//data : params,
				// 回调函数，接受服务器端返回给客户端的值，即result值          
				success : show
			});
          }
         }
      }
		
	  function show(result) {
	    if(result.pingResult!=null) 
	    {
	      var pr=result.pingResult;
	      backResult= pr+backResult;         
          document.getElementById("result").innerHTML=pr;
          document.getElementById("start").disabled=false;
          
        }
       
        
    }	
    function back() {
      history.back();
    }
    function stop() {
      document.getElementById("start").disabled=false;
      document.getElementById("stop").disabled=true;
      document.getElementById("ip").disabled=false;
      loop=0;
      count=1;
      backResult="";
    }
    function checkIP()
   {
     var ipArray,j;
     var ip=document.getElementById("ip").value.toLowerCase();
      if(/[A-Za-z_-]/.test(ip)){
         if(!/^([\w-]+\.)+((com)|(net)|(org)|(gov\.cn)|(info)|(cc)|(com\.cn)|(net\.cn)|(org\.cn)|(name)|(biz)|(tv)|(cn)|(la))$/.test(ip)){
            document.getElementById("ip").value="";
            document.getElementById("ip").focus();
            return false;
          }
      }
      else{
        ipArray = ip.split(".");
        j = ipArray.length
        if(j!=4)
        {
          document.getElementById("ip").value="";
          document.getElementById("ip").focus();
          return false;
        }

        for(var i=0;i<4;i++)
       {
         if(ipArray[i].length==0 || ipArray[i]>255)
           {
             document.getElementById("ip").value="";
             document.getElementById("ip").focus();
              return false;
           }
       }
    }
    return true;
}
</script>
    
	</script>
<body>
	<div class="main_section">
		<div class="all_aside">
			<div class="all_menu">
				<a href='net/netAction_toPing.action?net.ip=<s:property value="net.ip"/>' class="all_menu_a"><s:text name="net.ping"/></a> 
				<a href="<s:url action="deviceList" namespace="/device"/>" ><s:text name="net.device.list"/></a>
                        <s:if test="userType!=01">
                        	<a href="<s:url action="ipList" namespace="/device"/>"><s:text name="net.ip.list"/></a>
                        </s:if>
                        <s:if test="userType!=03">
                        	<a href="<s:url action="messageList" namespace="/device"/>"><s:text name="net.device.list"/></a>
                        </s:if>
			</div>
		</div>
		<div class="main_outside">		  
			<div class="all_inside">
				<div class="all_tab_top">										
					<label><s:text name="net.server.ip"/>[<%=request.getServerName() %>] &nbsp;&nbsp;&nbsp;&nbsp;Ping &nbsp;&nbsp;&nbsp;&nbsp;<s:text name="net.ping.ip"/></label>
					<s:if test="net.action!=null"><s:textfield cssClass="all_tab_top_input" name="net.ip" id="ip" maxlength="20" disabled="true"/></s:if>
					<s:else><s:textfield cssClass="all_tab_top_input" name="net.ip" id="ip" maxlength="20" /></s:else>
					
					<input type="button" value='<s:text name="net.ping.start"/>' class="search_but" onclick="ping();" id="start"/>
					<!--  
					<input type="button" value='<s:text name="net.ping.stop"/>' class="search_but" onclick="stop();" id="stop"/>-->
					<input type="button" value='<s:text name="net.ping.back"/>' class="search_but" onclick="back();" id="back"/>
					<s:hidden name="net.action" id="action"></s:hidden>
				</div>
				Ping<s:text name="net.ping.result"/>:
				<div class="all_tab_body" id="result">
				
				</div>	


				
			</div>
			
		</div>
	</div>
	
</body>
</html>
