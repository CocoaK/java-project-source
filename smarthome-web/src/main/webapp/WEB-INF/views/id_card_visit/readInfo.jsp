<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath()+ "/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><s:text name="gatecard.management" /></title>
        <link href="<s:url value='/css/layout.css'/>" rel="stylesheet" />
        <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jqueryLoader.js'/>"></script>
        <script type="text/javascript" src="<s:url value='/js/jquery_all.js'/>"></script>
    </head>
<script language="JavaScript">
     
     function doSubmit()
     {
       if(saveCardInfo())
       {
         document.forms[0].submit();
       }
       
     }
     function doRefrush()
     {
       //停止读卡
		DoStopRead();
       toReadCard();
       
     }
     function doCancel()
     {
        //停止读卡
		DoStopRead();
		window.location.href ='<s:url action="queryList" namespace='/idCardVisit'/>';  
       
     }
     function doForward(url)
     {
        //停止读卡
		DoStopRead();
		window.location.href =url;  
       
     }
   // window.setInterval("readOne()", 3000);
    // toReadCard();
    function saveCardInfo(){
        
        var name=document.all['idCardVisit.visitorName'].value;
        if(name=="")
        {
          alert('<s:text name="gatecard.idcard.refrush"/>');
          //toReadCard();
          
          return false;
        }
        
        var gender=document.all['Sex'].value;
        if(gender=="")
        {
          //toReadCard();
          alert('<s:text name="gatecard.idcard.refrush"/>');
          return false;
        }
        var cardNo=document.all['idCardVisit.idCard'].value;
        if(cardNo=="")
        {
          //toReadCard();
          alert('<s:text name="gatecard.idcard.refrush"/>');
          return false;
        }
        var address=document.all['idCardVisit.visitorAddress'].value;
        if(address=="")
        {
          alert('<s:text name="gatecard.idcard.refrush"/>');
          //toReadCard();
          
          return false;
        }
        var reason=document.all['idCardVisit.reason'].value;
        if(reason=="")
        {
          alert('<s:text name="gatecard.idcard.inputreason"/>');
          return false;
        }
        if(reason.length>50)
        {
          alert('<s:text name="gatecard.reason"/><s:text name="gatecard.reason.length"/>');
          return false;
        }
        //校验
        var ret=Save_IDCard();
        //alert(ret);
        return ret;
        //if(ret)
        //{
        // $.ajax({
          //      url : '<%=basePath%>flushIdCardJson/idCardVisitAction_saveCard.action',
				// 接受数据格式            
			//	dataType : 'json',
			//	type : 'POST',
			//	data : 'idCardVisit.visitorName=' + name+ '&idCardVisit.gender=' + gender+ '&idCardVisit.idCard=' + cardNo+ '&idCardVisit.visitorAddress=' + address+'&idCardVisit.reason='+reason,
			//	cache : false,
				// 回调函数，接受服务器端返回给客户端的值，即result值          
			//	success : result
			//	});
		//}
	}
	function dateToStr(datetime)
	{ 
     var year = datetime.getFullYear();
     var month = datetime.getMonth()+1;//js从0开始取 
     var date = datetime.getDate(); 
     var hour = datetime.getHours(); 
     var minutes = datetime.getMinutes(); 
     var second = datetime.getSeconds();

     if(month<10){
         month = "0" + month;
     }
     if(date<10){
        date = "0" + date;
     }
     if(hour <10){
       hour = "0" + hour;
     }
     if(minutes <10){
        minutes = "0" + minutes;
     }
     if(second <10){
       second = "0" + second ;
     }

     var time = year+"-"+month+"-"+date+" "+hour+":"+minutes+":"+second; //2009-06-12 17:18:05

    return time;
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
	
	function toReadCard()
	{
	  // HideActiveX();
	  //检查设备
		  if(DoCheckReader())
		  {
		    //停止读卡
		    DoStopRead();
		  
		   //读身份证
		   ReadIDCard();
		  }else
		  {
		    alert('<s:text name="gatecard.check.card"/>');
		  }
		  
		    
		 
		  
	}
	function HideActiveX() {
		CVR_IDCard.Visible=false; 
		ClearIDCard();
		ClearForm();
		document.all['ReadResult'].value = "等待验证";
		toReadCard();
		//return true;
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
	    document.all['idCardVisit.gender'].value = "";
		document.all['idCardVisit.visitorAddress'].value = "";
		document.all['idCardVisit.idCard'].value = "";
		document.all['idCardVisit.visitorName'].value = "";
		document.all['Sex'].value = "";
		//document.all['Name'].value = "";
		//document.all['Sex'].value = "";
		document.all['Nation'].value = "";
		document.all['Born'].value = "";
		//document.all['Address'].value = "";
		//document.all['CardNo'].value = "";
		document.all['Police'].value = "";
		document.all['Activity'].value = "";
		document.all['NewAddr'].value = "";
		document.all['ActivityLFrom'].value = "";
		document.all['ActivityLTo'].value = "";
		document.all['PhotoBuffer'].value = "";
		document.all['idCardVisit.visitTime'].value="";

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
		
        var today= new Date(<%=new java.util.Date().getTime()%>);
        today=dateToStr(today);
        document.all['idCardVisit.gender'].value = pSex;
		document.all['idCardVisit.visitorAddress'].value = pAddress;
		document.all['idCardVisit.idCard'].value = pCardNo;
		document.all['idCardVisit.visitorName'].value = pName;
		document.all['idCardVisit.visitTime'].value=today;
		document.all['Sex'].value = pSex;
		//document.all['Name'].value = pName; //document.all['Name'].value = pNameL;
		//document.all['Sex'].value = pSex; // document.all['Sex'].value = pSexL; 
		document.all['Nation'].value = pNation; //document.all['Nation'].value = pNationL;
		document.all['Born'].value = pBorn; //document.all['Born'].value = pBornL; 
		//document.all['Address'].value = pAddress;
		//document.all['CardNo'].value = pCardNo;
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
		CVR_IDCard.TimeOut = 30;
		ClearIDCard();
		ClearForm();
		document.all['ReadResult'].value = "请放卡...";
		alert('<s:text name="gatecard.idcard.putcard"/>');
		var strReadResult = CVR_IDCard.ReadCard;
		FillForm();
		document.all['ReadResult'].value = "返回值:" + strReadResult;
		if(strReadResult==0)
		{
		  //saveCardInfo();
		}else
		{
		  //ReadIDCard();
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
		if(State=="-1")
		{
		  return false;
		}else
		{
		  return true;
		}
		//alert(State);

		//return true;
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
		
		var gender=document.all['Sex'].value;
		if(gender=="男")
		{
		   document.all['idCardVisit.gender'].value =0;
		}else
		{
		  document.all['idCardVisit.gender'].value =1;
		}
        var today= new Date(<%=new java.util.Date().getTime()%>);
        today=dateToStr(today);
		
		document.all['idCardVisit.visitorAddress'].value = pAddress;
		document.all['idCardVisit.idCard'].value = pCardNo;
		document.all['idCardVisit.visitorName'].value = pName;
		document.all['idCardVisit.visitTime'].value = today;
		//document.all['Sex'].value = pSex;
		document.all['Nation'].value = pNation;
		document.all['Born'].value = pBorn;
		
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
	var oldValue=new Array();
  function checkMaxLen(obj,maxlength,num){
   if(obj.value.length>maxlength){
    obj.value=oldValue[num];
   }
   else{
    oldValue[num]=obj.value;
   }
  }


	
</script>
    <body onload="HideActiveX()">
       <OBJECT classid="clsid:10946843-7507-44FE-ACE8-2B3483D179B7" codebase="CVR100.cab#version=3,0,3,3" id="CVR_IDCard"	name="CVR_IDCard" width=0 height=0  hspace=0 vspace=0> </OBJECT>
	   <form action="<s:url action='saveCard' namespace='/idCardVisit'/>" method="post" >		    
       <input type="hidden" name="Name" value=""/>
   
   <input type="hidden" name="Nation" value=""/>
   <input type="hidden" name="Born" value=""/>
   <!-- 
   <input type="hidden" name="Address" value=""/>
   <input type="hidden" name="CardNo" value=""/>
    -->
   <input type="hidden" name="Police" value=""/>
   <input type="hidden" name="Activity" value=""/>
   <input type="hidden" name="ActivityLFrom" value=""/>
   <input type="hidden" name="ActivityLTo" value=""/>
   <input type="hidden" name="NewAddr" value=""/>
   <input type="hidden" name="PhotoPath" value="C:\"/>
   <input type="hidden" name="PhotoBuffer" value=""/>
   <input type="hidden" name="ReadResult" value="" />
   <input type="hidden" name="idCardVisit.gender" value="" />
        <div class="main_section">
            <div class="all_aside">
                <div class="all_menu">
                    <a href="javascript:doForward(<s:url action="queryList" namespace='/gateCard'/>);"><s:text name="gatecard.management.list"/></a>
                    <a href="javascript:doForward('<s:url action='addInput' namespace='/gateCard'/>');"><s:text name="gatecard.management.add"/></a>
                    <a href="javascript:doForward('<s:url action="queryList" namespace='/gateCardVisit'/>');"><s:text name="gatecard.management.gatecardvisit.list"/></a>
                    <a href="javascript:doForward('<s:url action='queryList'/>');"><s:text name="gatecard.management.idcardvisit.list"/></a>                    
                    <a href="javascript:doForward('<s:url action="toReadCard"/>');" class="all_menu_a"><s:text name="gatecard.read.card"/></a>
                </div>
            </div>
            
            <div class="main_outside">
                <div class="all_inside">
                    <table cellspacing="0"
                        class="all_tab_body all_tab_bodys">
                        <tbody>
                            <tr>
                                <td width="120"><s:text name="gatecard.name"/>:</td>
                                <td><input type="text" name="idCardVisit.visitorName"  value=""  readonly=true/></td>
                            </tr>
                            <tr>
                                <td><s:text name="gatecard.idcard"/>:</td>
                                <td><input type="text" name="idCardVisit.idCard"  value="" readonly=true /></td>
                            </tr>                           
                            <tr>
                                <td><s:text name="common.title.gender"/>:</td>
                                <td>
                                    <input type="text" name="Sex"  value="" readonly=true />                                   
                                </td>
                            </tr>
                            <tr>
                                <td><s:text name="common.title.address"/>:</td>
                                <td><input type="text" name="idCardVisit.visitorAddress"  value="" readonly=true /> </td>
                            </tr>                           
                            <tr>
                                <td><s:text name="gatecard.visittime"/>:</td>
                                <td><input type="text" name="idCardVisit.visitTime"  value="" readonly=true /> </td>
                            </tr>
                            <tr>
                                <td><s:text name="gatecard.reason"/>:</td>
                                <td><textarea name="idCardVisit.reason" cols="40" rows="2" size="50" maxlength="50" onpropertychange="checkMaxLen(this,50,0)" onblur="checkMaxLen(this,50,0)"></textarea><s:text name="gatecard.reason.length"/></td>
                            </tr>                                                      
                        </tbody>
                    </table> 
                    <div class="all_tab_butb"><a href="javascript:doSubmit();"><s:text name="gatecard.button.save"/></a><a href="javascript:doRefrush();"><s:text name="gatecard.button.refrush"/></a><a href="javascript:doCancel();"><s:text name="gatecard.button.cancel"/></a></div>
                    
                                  
                </div>
            </div>
        </div>
        </form>
    </body>
</html>
