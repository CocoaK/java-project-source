<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>jquery的时间插件的列子</title>
<script type="text/javascript" src="jquery/jquery-1.7.2.min.js"></script>	
<script type="text/javascript" language="javascript" src="js/My97DatePicker/WdatePicker.js"></script> 
  <script type="text/javascript">
	$(document).ready(function(){
		alert('111111111');
	})
  </script>
  </head>
  
  <body>
<div id="div1"></div>
<br /><br />
<script>
WdatePicker({eCont:'div1',lang:'zh-cn',onpicked:function(dp){alert('你选择的日期是:'+dp.cal.getDateStr())}})
</script>
<input  id="aa" type="text" value="" class="Wdate" onclick="WdatePicker({lang:'zh-cn'})"/>
  </body>
</html>
