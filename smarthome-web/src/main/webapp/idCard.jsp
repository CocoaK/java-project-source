<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--  
<link rel="stylesheet" href="pic/style3.css" type="text/css">-->
<script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
</head>
<script language="JavaScript">
     var isOnLoadOk=true;
    window.setInterval("readOne()", 3000);
     
    function saveCardInfo(){
        var name=document.all['Name'].value;
        if(name=="")
        {
          toReadCard();
          alert("刷卡失败，请重刷卡！");
          return false;
        }
        
        var gender=document.all['Sex'].value;
        if(gender=="")
        {
          toReadCard();
          alert("刷卡失败，请重刷卡！");
          return false;
        }else
        {
          if(gender=="男")
          {
           gender="0";
          }else if(gender=="女")
          {
          gender="1";
          }
        }
        var cardNo=document.all['CardNo'].value;
        if(cardNo=="")
        {
          toReadCard();
          alert("刷卡失败，请重刷卡！");
          return false;
        }
        var address=document.all['Address'].value;
        if(address=="")
        {
          toReadCard();
          alert("刷卡失败，请重刷卡！");
          return false;
        }
        //校验
        var ret=Save_IDCard();
        if(ret)
        {
         $.ajax({
                url : '<%=basePath%>flushIdCardJson/idCardVisitAction_saveCard.action',
				// 接受数据格式            
				dataType : 'json',
				type : 'POST',
				data : 'idCardVisit.visitorName=' + name+ '&idCardVisit.gender=' + gender+ '&idCardVisit.idCard=' + cardNo+ '&idCardVisit.visitorAddress=' + address,
				cache : false,
				// 回调函数，接受服务器端返回给客户端的值，即result值          
				success : result
				});
		}
	}
	
	function result(result) {
		var json = result;
		if(json==true)
		{
		  alert("刷卡成功!");
		  isOnLoadOk=false;
		  toReadCard();
		  
	    }else
	    {
	     alert("保存身份证信息失败!");
	    }

	}
	function readOne()
	{
	  if(isOnLoadOk)
	   {
	     toReadCard();
	   }
	}
	function toReadCard()
	{
	   HideActiveX();
	  //检查设备
		  DoCheckReader();
		  
		    //停止读卡
		    DoStopRead();
		  
		   //读身份证
		   ReadIDCard();
		 
		  
	}
	function HideActiveX() {
		CVR_IDCard.Visible=false; 
		ClearIDCard();
		ClearForm();
		document.all['ReadResult'].value = "等待验证";
		
		return true;
	}

	function ClearIDCard() {
		CVR_IDCard.Name = "";
		CVR_IDCard.NameL = "";
		CVR_IDCard.Sex = "";
		//CVR_IDCard.SexL="";   
		CVR_IDCard.Nation = "";
		//CVR_IDCard.NationL="";
		CVR_IDCard.Born = "";
		//CVR_IDCard.BornL="";
		CVR_IDCard.Address = "";
		CVR_IDCard.CardNo = "";
		CVR_IDCard.Police = "";
		CVR_IDCard.Activity = "";
		CVR_IDCard.NewAddr = "";

		return true;
	}

	function ClearForm() {
		document.all['Name'].value = "";
		document.all['Sex'].value = "";
		document.all['Nation'].value = "";
		document.all['Born'].value = "";
		document.all['Address'].value = "";
		document.all['CardNo'].value = "";
		document.all['Police'].value = "";
		document.all['Activity'].value = "";
		document.all['NewAddr'].value = "";
		document.all['ActivityLFrom'].value = "";
		document.all['ActivityLTo'].value = "";
		document.all['PhotoBuffer'].value = "";

		return true;
	}

	function FillForm() {
		var pName = CVR_IDCard.NameL; //var pNameL=CVR_IDCard.NameL;
		var pSex = CVR_IDCard.SexL; //var pSexL=CVR_IDCard.SexL;
		var pNation = CVR_IDCard.NationL; //var pNationL=CVR_IDCard.NationL;
		var pBorn = CVR_IDCard.BornL; //var pBornL=CVR_IDCard.BornL;
		var pAddress = CVR_IDCard.Address;
		var pCardNo = CVR_IDCard.CardNo;
		var pPolice = CVR_IDCard.Police;
		var pActivity = CVR_IDCard.Activity;
		var pNewAddr = CVR_IDCard.NewAddr;
		var pActivityLFrom = CVR_IDCard.ActivityLFrom;
		var pActivityLTo = CVR_IDCard.ActivityLTo;
		var pPhotoBuffer = CVR_IDCard.GetPhotoBuffer;

		document.all['Name'].value = pName; //document.all['Name'].value = pNameL;
		document.all['Sex'].value = pSex; // document.all['Sex'].value = pSexL; 
		document.all['Nation'].value = pNation; //document.all['Nation'].value = pNationL;
		document.all['Born'].value = pBorn; //document.all['Born'].value = pBornL; 
		document.all['Address'].value = pAddress;
		document.all['CardNo'].value = pCardNo;
		document.all['Police'].value = pPolice;
		document.all['Activity'].value = pActivity;
		document.all['NewAddr'].value = pNewAddr;
		document.all['ActivityLFrom'].value = pActivityLFrom;
		document.all['ActivityLTo'].value = pActivityLTo;
		document.all['PhotoBuffer'].value = pPhotoBuffer;

		//显示照片Servlet

		return true;
	}

	function ReadIDCard() {
		CVR_IDCard.PhotoPath = document.all['PhotoPath'].value;
		CVR_IDCard.TimeOut = 120;
		ClearIDCard();
		ClearForm();
		document.all['ReadResult'].value = "请放卡...";
		alert(document.all['ReadResult'].value);
		var strReadResult = CVR_IDCard.ReadCard;
		FillForm();
		document.all['ReadResult'].value = "返回值:" + strReadResult;
		if(strReadResult==0)
		{
		  saveCardInfo();
		}else
		{
		  ReadIDCard();
		}
		//alert(strReadResult);  

		return true;
	}

	function DoStopRead() {
		CVR_IDCard.DoStopRead;
		ClearIDCard();
		ClearForm();
		document.all['ReadResult'].value = "用户已取消读卡"
		return true;
	}

	function DoCheckReader() {
		var State = CVR_IDCard.GetState;
		document.all['ReadResult'].value = State;

		return true;
	}

	function Save_IDCard() {
		var pName = CVR_IDCard.Name;
		var pSex = CVR_IDCard.Sex;
		var pNation = CVR_IDCard.Nation;
		var pBorn = CVR_IDCard.Born;
		var pAddress = CVR_IDCard.Address;
		var pCardNo = CVR_IDCard.CardNo;
		var pPolice = CVR_IDCard.Police;
		var pActivity = CVR_IDCard.Activity;
		var pNewAddr = CVR_IDCard.NewAddr;
		var pActivityLFrom = CVR_IDCard.ActivityLFrom;
		var pActivityLTo = CVR_IDCard.ActivityLTo;
		var pPhotoBuffer = CVR_IDCard.GetPhotoBuffer;

		if (pName == "") {
			ClearForm();
			alert('读卡内容为空，无法保存数据！请先读卡，再保存。');
			return false;
		}
		document.all['Name'].value = pName;
		document.all['Sex'].value = pSex;
		document.all['Nation'].value = pNation;
		document.all['Born'].value = pBorn;
		document.all['Address'].value = pAddress;
		document.all['CardNo'].value = pCardNo;
		document.all['Police'].value = pPolice;
		document.all['Activity'].value = pActivity;
		document.all['NewAddr'].value = pNewAddr;
		document.all['ActivityLFrom'].value = pActivityLFrom;
		document.all['ActivityLTo'].value = pActivityLTo;
		document.all['PhotoBuffer'].value = pPhotoBuffer;

		//相片文件默认保存在 CVR_IDCard.PhotoPath

		ClearIDCard();
		return true;
	}
</script>

  <body onload="HideActiveX()" >
   <input type="hidden" name="Name" value=""/>
   <input type="hidden" name="Sex" value=""/>
   <input type="hidden" name="Nation" value=""/>
   <input type="hidden" name="Born" value=""/>
   <input type="hidden" name="Address" value=""/>
   <input type="hidden" name="CardNo" value=""/>
   <input type="hidden" name="Police" value=""/>
   <input type="hidden" name="Activity" value=""/>
   <input type="hidden" name="ActivityLFrom" value=""/>
   <input type="hidden" name="ActivityLTo" value=""/>
   <input type="hidden" name="NewAddr" value=""/>
   <input type="hidden" name="PhotoPath" value="C:\"/>
   <input type="hidden" name="PhotoBuffer" value=""/>
   <input type="hidden" name="ReadResult" value="" />
   <OBJECT classid="clsid:10946843-7507-44FE-ACE8-2B3483D179B7"
				codebase="CVR100.cab#version=3,0,3,3" id="CVR_IDCard"
				name="CVR_IDCard" width=119 height=136 align=center hspace=0
				vspace=0> </OBJECT>
	<!-- 
	<center>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td width="100%" colspan="3">
					<p align="center">
						<b><font color="#5555FF" size="6"> </font> </b>
				</td>
			</tr>
			<tr>
				<td width="33%"></td>
				<td width="33%">
					<p align="center">
						<font color="#FF0000"></font>
				</td>
				<td width="34%"></td>
			</tr>
			<tr>
				<td width="33%" bgcolor="#FF9900"></td>
				<td width="33%" bgcolor="#FF9900">
					<p align="center">
						<font color="#FF0000"></font>
				</td>
				<td width="34%" bgcolor="#FF9900"></td>
			</tr>
		</table>
		<p>
			<OBJECT classid="clsid:10946843-7507-44FE-ACE8-2B3483D179B7"
				codebase="CVR100.cab#version=3,0,3,3" id="CVR_IDCard"
				name="CVR_IDCard" width=119 height=136 align=center hspace=0
				vspace=0> </OBJECT>
	</center>
	<form name="MyForm" method=post>
		<table border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>

				<td width="21%"></td>
				<td width="51%" colspan="3">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
					 
						<tr>
							<td width="33%">
								<p align="right">
									<input type="button" name="ReadCard" value="读身份证"
										onclick="return ReadIDCard()">
							</td>
							<td width="33%">
								<p align="center">
									<input type="button" name="StopRead" value="停止读卡"
										onclick="return DoStopRead()">
							</td>
							<td width="34%">
								<p align="left">
									<input type="button" name="StopRead1" value="检测设备"
										onclick="return DoCheckReader()">
							</td>
						</tr>
						
					</table>
				</td>

				<td width="28%"></td>
			</tr>
			<tr>
				<td width="21%"></td>
				<td width="51%" colspan="3">
					<HR>
				</td>
				<td width="28%"></td>
			</tr>
			<tr>
				<td width="21%"></td>
				<td width="11%"></td>
				<td width="18%"></td>
				<td width="23%" valign="top"></td>

				<td width="28%"></td>
			</tr>
			<tr>
				<td width="21%"></td>

				<td width="11%">
					<p align="right">姓名：</p>
				</td>
				<center>
					<td width="18%"><input type="text" name="Name" size="20"
						class=Input3 >
					</td>
				</center>

				<td width="23%" valign="top"></td>

				<center>
					<center>
						<center>
							<td width="28%"></td>
						</center>
					</center>
				</center>
			</tr>
			<tr>
				<td width="21%"></td>

				<td width="11%">
					<p align="right">性别：</p>
				</td>
				<center>
					<td width="18%"><input type="text" name="Sex" size="20"
						class=Input3></td>
				</center>

				<td width="23%" valign="top"></td>

			</tr>
			<tr>
				<td width="21%"></td>

				<td width="11%">
					<p align="right">民族：</p>
				</td>
				<center>
					<td width="18%"><input type="text" name="Nation" size="20"
						class=Input3></td>
				</center>

				<td width="23%" valign="top"></td>

			</tr>
			<tr>
				<td width="21%"></td>

				<td width="11%">
					<p align="right">出生：</p>
				</td>
				<center>
					<td width="18%"><input type="text" name="Born" size="20"
						class=Input3></td>
				</center>

				<td width="23%" valign="top"></td>

			</tr>
			<tr>
				<td width="21%"></td>

				<td width="11%">
					<p align="right">地址：</p>
				</td>
				<center>
					<td width="41%" colspan="2"><input type="text" name="Address"
						size="49" class=Input2></td>
					<td width="28%"></td>
				</center>
			</tr>
			<tr>
				<td width="21%"></td>

				<td width="11%">
					<p align="right">
						<font color="#FF0000"><b>身份证号：</b> </font>
					</p>
				</td>
				<center>
					<td width="41%" colspan="2"><input type="text" name="CardNo"
						size="49" class=Input2 style="color: #FF0000"></td>
					<td width="28%"></td>
				</center>
			</tr>
			<tr>
				<td width="21%"></td>

				<td width="11%">
					<p align="right">签发机关：</p>
				</td>
				<center>
					<td width="41%" colspan="2"><input type="text" name="Police"
						size="49" class=Input2></td>
					<td width="28%"></td>
				</center>
			</tr>
			<tr>
				<td width="21%"></td>

				<td width="11%">
					<p align="right">有效期限：</p>
				</td>
				<center>
					<td width="41%" colspan="2"><input type="text" name="Activity"
						size="49" class=Input2></td>
					<td width="28%"></td>
				</center>
			</tr>
			<tr>
				<td width="21%"></td>

				<td width="11%">
					<p align="right">期限起始：</p>
				</td>
				<center>
					<td width="41%" colspan="2"><input type="text"
						name="ActivityLFrom" size="49" class=Input2>
					</td>
					<td width="28%"></td>
				</center>
			</tr>
			<tr>
				<td width="21%"></td>


				<td width="11%">
					<p align="right">期限失效：
				</td>
				<center>
					<center>
						<center>
							<td width="41%" colspan="2"><input type="text"
								name="ActivityLTo" size="49" class=Input2>
							</td>
							<td width="28%"></td>
						</center>
					</center>
				</center>
			</tr>
			<tr>
				<td width="21%"></td>

				<td width="11%">
					<p align="right">最新地址：</p>
				</td>
				<center>
					<td width="41%" colspan="2"><input type="text" name="NewAddr"
						size="49" class=Input2>
					</td>
					<td width="28%"></td>
				</center>
			</tr>

			<tr>
				<td width="21%"></td>


				<td width="11%">
					<p align="right">照片路径：</p>
				</td>
				<td width="41%" colspan="2"><input type="text" name="PhotoPath"
					value="C:\" size="49" class=Input2>
				</td>
				<center>
					<center>
						<center>
							<td width="28%"></td>
						</center>
					</center>
				</center>
			</tr>
			<tr>
				<td width="21%"></td>


				<td width="11%" valign="top">
					<p align="right">照片编码：</p>
					<p align="center"></p>
				</td>
				<center>
					<center>
						<center>
							<td width="41%" colspan="2"><textarea rows="4"
									name="PhotoBuffer" cols="47" class=MultiEditBox></textarea>
							</td>
							<td width="28%"></td>
						</center>
					</center>
				</center>
			</tr>

			<tr>
				<td width="21%"></td>
				<td width="11%"></td>
				<td width="41%" colspan="2"></td>
				<td width="28%"></td>
			</tr>
			<tr>
				<td width="21%"></td>

				<td width="11%">
					<p align="right">
						<font color="#0000FF">操作提示:</font>
					</p>
				</td>


				<td width="41%" colspan="2"><input type="text"
					name="ReadResult" value="等待验证" size="32"><input type=button
					name="submit" value="保存读卡结果" onclick="saveCardInfo();">
				</td>
				<td width="28%"></td>
			</tr>

			
			<tr>
				<td width="21%"></td>

				<td width="11%"></td>


				<td width="41%" colspan="2"></td>
				<center>


					<td width="28%"></td>
				</center>
			</tr>

		</table>

	</form>

    -->


</body>
</html>
