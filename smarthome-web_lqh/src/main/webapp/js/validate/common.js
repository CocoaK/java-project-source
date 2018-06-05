
//修改
function edit(checkbox,form,url,n)
    {
	     var c=countchecked(checkbox,n);
        
         if(c==0)
         {
           alert("请选择记录！");
         }
         else if(c>1)
         {
        	 alert("不能选择多条记录，只能选择一条记录！");
         }
         else if(c==1)
         {
           var id=check(checkbox,n);
           
           if(id==null)
           {
        	   alert("请选择记录！");
           }
           else
           {
           document.getElementById(form).action = url+"="+id;
	       document.getElementById(form).submit();
           }
	     }
    }
//选择
function check(obj,n)       
{       
   var id;
  
   for(i=0;i<n;i++)       
    {       
      if(document.getElementById(obj+i).checked)
      {    	  
        id=document.getElementById(obj+i).value;
        break;
       
      }     
    }
    return id;       
} 
function countchecked(obj,n)       
{       
	
   var c=0;
   for(i=0;i<n;i++)       
    {       
      if(document.getElementById(obj+i).checked)
      {
    	  c++; 
       
      }     
    }
   
    return c;       
}  

//新增
function add(form,url)
{
  
  document.getElementById(form).action = url;
  document.getElementById(form).submit();
}

//// 处理‘全选’复选框的选择情况
function handleSelect(checkboxId,n) {
    
    if(document.getElementById('selectAll').checked === true) {
        selectAll(checkboxId,n);
    } else {
        unSelectAll(checkboxId,n);
    }
}

// 全选所有的列表复选框
function selectAll(checkboxId,n) {    
    for(var i = 0; i < n; ++i) {
        var o = document.getElementById(checkboxId+i);
        if(!o.disabled)
        {
         o.checked = true; 
        }

    }
}

// 全不选所有的复选框
function unSelectAll(checkboxId,n) {    
    for(var i = 0; i < n; ++i) {
        var o = document.getElementById(checkboxId+i);
        o.checked = false;      
    }
}

function del(formId,action,checkboxId,n)
{
   document.getElementById(formId).action = action;
  if(checkBoxSubmit(checkboxId,n))
  {
	  if(confirm("确定删除吗？"))
	  {
        document.getElementById(formId).submit();
	  }
  }else
  {
    alert("请选择记录!");
  }
}
function checkBoxSubmit(checkboxId,n)
{

for(var i = 0; i < n; ++i) {
    var o = document.getElementById(checkboxId+i);
    if(o.checked)
    {
      return true;
    }

}
return false;
}
function action(form,url)
{
  document.getElementById(form).action = url;
  document.getElementById(form).submit();
}
//返回
function back(url)
{
  window.location.href=url;
} 
//页面关闭事件
function onwindowsClose()
{

	var warning="确认退出?";

	if (event.clientX>document.body.clientWidth && event.clientY<0 ||event.altKey)
	{

	    return warning;
	}

}
function win_location_href(url)
{
	window.location.href=url;
}
function switchTab(tabpage,tabid){
    
	var oItem = document.getElementById(tabpage); 
	
	var LIs=oItem.getElementsByTagName("LI");
	
	for(var i=0;i<LIs.length;i++)
	{
		
		var x = LIs[i];	
		x.className = "";
		var y = x.getElementsByTagName('a');
		//y[0].style.color="#333333";
	}	
	document.getElementById(tabid).className = "Selected";
	
	var j=document.getElementById(tabid);
	j.style.color = "#fff";
	var dvs=document.getElementById("cnt").getElementsByTagName("div");

	for (var i=0;i<dvs.length;i++)
	{
	  if(dvs[i].className =="HackBox")
	  {
		  if (dvs[i].id==('d'+tabid))
		  dvs[i].style.display='block';
		  else
		  dvs[i].style.display='none';
	  }
	}
}

