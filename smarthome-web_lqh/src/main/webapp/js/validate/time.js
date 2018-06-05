 /**
  *  计算两个日期的间隔天数   
  * @param sDate1
  * @param sDate2
  * @return
  */
function ComputationDay(sDate1, sDate2)
{   //sDate1和sDate2是2008-12-13格式     
  
  var aDate, oDate1, oDate2, iDays     
  aDate = sDate1.split("-")     
  oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0])   //转换为12-13-2008格式     
  aDate = sDate2.split("-")     
  oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0])     
  iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 /24)   //把相差的毫秒数转换为天数     
   
      return iDays     
}   
/**
 * js日期格式化
 * @param format
 * @return
 */
Date.prototype.format = function(format)
{
    var o =
    {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format))
    format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
    if(new RegExp("("+ k +")").test(format))
    format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}