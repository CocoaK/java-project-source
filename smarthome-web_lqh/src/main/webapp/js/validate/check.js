//通过ID判断对象是否为null
function isNullById(id)
{
	if(getElementById(id)==null)
		return false;
	return true;
}
// 通过ID获得对象
function getElementById(id)
{
	return document.getElementById(id);
}
// 通过ID获得值
function getValueById(id)
{
	if(isNullById(id))
	{
		return trim(getElementById(id).value);
	}
	return "";
}
// 判断是否为数字字母下滑线
function notchinese(str)
{
   var reg=/[^A-Za-z0-9_]/g
   if (reg.test(str)){
	  
      return false;
   }else{
     return true;
     }
}
// 判断两个字符串是否相等
function issame(str1,str2) 
{ 
  if (str1==str2) 
  {return(true);} 
  else 
  {   return(false);} 
} 
// 检查一段字符串是否全由数字组成
function checkNum(str){
	return str.match(/\D/)==null;
}
// 检查是否为空
function checkEmpty(id,msg)
{ 
  if (isNullById(id)&&trim(getElementById(id).value).length == 0) 
  {   alert(msg);    
	  getElementById(id).value="";
      return false;
   }
   return true;
}
//检查是否为空
function checkEmpty(id,msg,errid)
{ 
  if (isNullById(id)&&trim(getElementById(id).value).length == 0) 
  {       
	  getElementById(id).value="";
	  document.getElementById(errid).innerHTML=msg;
      return false;
   }
   return true;
}
// 检查是否为空
function isEmpty(id)
{ 
  if (isNullById(id)&&trim(getElementById(id).value).length == 0) 
  {     
      return true;
   }
   return false;
}
// 校验登录名：只能输入1-20个以字母开头、可带数字、“_”的字串
function isUserName(s)
{
  var patrn=/^[a-zA-Z]{1}([a-zA-Z0-9_]){1,19}$/;
  if (!patrn.exec(s))
	  return false
  return true
}
// 校验登录名：只能输入1-20个以字母开头、可带数字、“_”的字串
function isUserAccount(s)
{
  var patrn=/^[a-zA-Z]{1}([a-zA-Z0-9_]){3,19}$/;
  
  if (!patrn.exec(s))
	  return false
  return true
}
// 校验密码：只能输入4-15个字母、数字
function isPasswd(s)
{
  var patrn=/^[a-zA-Z0-9]{4,15}$/;
  if (!patrn.exec(s)) 
	  return false
   return true
}
// 是否字母、数字
function isWordORNum(s)
{
  var patrn=/^[a-zA-Z0-9]$/;
  if (!patrn.exec(s)) 
	  return false
   return true
}
// 校验数字
function isNum(s)
{
  var patrn=/^[0-9]$/;
  if (!patrn.exec(s)) 
	  return false
   return true
}
// 判断电话号码/手机号码
function phoneCheck(s) 
{
  var str=s;
  var reg=/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/;
  return reg.test(str);
}
function isMobile(phone) { 
	var tmp = /^1[3-9]\d{9}$/;   // 支持11位手机号码验证	
    var flag=tmp.exec(phone);   
    if(!flag){   
       alert("手机号输入不合法");          
    }   
    return flag;
} 

function isTel(s)   
{   
// "兼容格式: 国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"
// return
// (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?/.test(this.Trim()));
return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?/.test(s.Trim()));   
} 

// Email格式判断
function ismail(email)
{
  return(new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/).test(email));
}
// 判断只能输入中文、数字、字母
function isChinese(str)
{
  var pattern = /^[0-9a-zA-Z\u4e00-\u9fa5]{1,10}$/i;
  if (pattern.test(str))
  {
   return true;
  }
  else
  {  
   return false;
  }
}
// 判断是否合法的16位终端序列号码
function isSeq(s)
{
  var patrn=/^[a-zA-Z0-9]{15}$/;
  if (patrn.exec(s)) 
    return true;
	  return false;
  
} 
// 计算两个日期的间隔天数
function Computation(sDate1, sDate2){   // sDate1和sDate2是2008-12-13格式
  var aDate, oDate1, oDate2, iDays     
  aDate = sDate1.split("-")     
  oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0])   // 转换为12-13-2008格式
  aDate = sDate2.split("-")     
  oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0])     
  iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 /24)   // 把相差的毫秒数转换为天数
   alert(iDays);   
      return iDays     
}   

function  trim(s)
{
return s.replace(/(^\s*)|(\s*$)/g, "");
}

function  leftTrim(s)
{
return s.replace(/(^\s*)/g, "");
}

function rightTrim(s)
{
return s.replace(/(\s*$)/g, "");
}
function block(oEvent)
{
	if(window.event)
	{
	    oEvent=window.event;
	    if(oEvent.button==2)
	    {
	    //alert("鼠标右键不可用");
	    }
	}
}
function ifenter()
{  
	  if(event.keyCode==13)
	  { 
	    return   false; 
	  }else if(event.srcElement.type=="submit")
	  {  
	   return true;
	  } 
} 
//判断输入是否是一个整数
function isint(str)
{
    var result=str.match(/^\d{1,3}$/);
    if(result==null) return false;
    return true;
}
/**
 * 判断字符内容是否超长
 * @param id
 * @param len
 * @param des
 * @return
 */
function overLength(id,len,des)
{
	var s=getElementById(id).value;
	if(s.length>len)
	{
		 alert(des);
		 return false;
	}
	return true;
}
//判断字符串是否为数字   
function checkRate(input)   
{   
     var re = /^[0-9]+.?[0-9]*$/;          
     if (!re.test(input.rate.value))   
    {   
        alert("请输入数字");   
        input.rate.focus();   
        return false;   
     }   
} 
//判断正整数   
function checkRate(id,des)   
{   
     var re = /^[1-9]+[0-9]*]*$/;   
     if (!re.test(getElementById(id).value))   
    {   
        alert(des);         
        return false;   
     } 
     else
     {
    	  return true;
     }
}  
function onlyNum()
{
if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105)))
//考虑小键盘上的数字键
event.returnvalue=false;
}

