<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<title><s:text name="datapush.management"/></title>
<link href="<%=basePath%>css/layout.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath%>js/jquery-validation/css/screen.css" />
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<!-- 
<script type="text/javascript" src="<%=basePath%>/js/jqueryLoader.js"></script> -->
<script type="text/javascript" src="<%=basePath%>js/jquery_all.js"></script>
<script src="<%=basePath%>js/jquery-validation/lib/jquery.js" type="text/javascript"></script>
<script src="<%=basePath%>js/jquery-validation/jquery.validate.js" type="text/javascript"></script>
</head>
<script type="text/javascript">
 function locationAction(url)
    {      
      window.location.href =url;	  
	  
    }
 function repush(id)
    {      
      //var url="pushAction_repush.action?pushFinishVO.id="+id;
      locationAction(url);	  
	  
    }
   function doSubmit()
    {
       document.getElementById("pageNum").value=1;
       document.forms[0].submit();
    }
</script>
<body>
	<div class="main_section">
		<div class="all_aside">
			<div class="all_menu">
				<!--  <a href="hdfs/hdfsFileAction_list.action" ><s:text name="hdfs.task.cloud.store"/></a>--> <a
					href="hdfs/hdfsTaskAction_list.action" class="all_menu_a"><s:text name="hdfs.task.cloud.task"/></a>
			</div>
		</div>
		<div class="main_outside">
		<s:form action="hdfs/hdfsTaskAction_list.action"  method="post"  theme="simple">
			<div class="all_inside">
				<div class="all_tab_top">
					<label><s:text name="hdfs.task.uploader.kind"/></label>
                    <div class="select_div">
                        <select name="hdfsTaskVO.uploaderKind">
                          <option value="all" <s:if test="hdfsTaskVO.uploaderKind==\"all\"">selected</s:if>><s:text name="hdfs.task.all"/></option>
                          <option value="app" <s:if test="hdfsTaskVO.uploaderKind==\"app\"">selected</s:if>><s:text name="hdfs.task.uploader.kind.app"/></option>
                          <option value="web" <s:if test="hdfsTaskVO.uploaderKind==\"web\"">selected</s:if>><s:text name="hdfs.task.uploader.kind.web"/></option>
                        </select>
                    </div>
                    <label><s:text name="hdfs.task.file.kind"/></label>
                    <div class="select_div">
                        <select name="hdfsTaskVO.kind">
                          <option value="all" <s:if test="hdfsTaskVO.kind==\"all\"">selected</s:if> ><s:text name="hdfs.task.all"/></option>
                          <option value="Image" <s:if test="hdfsTaskVO.kind==\"Image\"">selected</s:if>><s:text name="hdfs.task.file.kind.image"/></option>
                          <option value="Audio" <s:if test="hdfsTaskVO.kind==\"Audio\"">selected</s:if>><s:text name="hdfs.task.file.kind.audio"/></option>
                          <option value="Vedio" <s:if test="hdfsTaskVO.kind==\"Vedio\"">selected</s:if>><s:text name="hdfs.task.file.kind.vedio"/></option>
                          <option value="Software" <s:if test="hdfsTaskVO.kind==\"Software\"">selected</s:if>><s:text name="hdfs.task.file.kind.software"/></option>
                        </select>
                    </div>
                    <label><s:text name="hdfs.task.file.name"/></label>                   
                    <s:textfield name="hdfsTaskVO.fileName" cssClass="all_tab_top_input" maxlength="50"></s:textfield>
                    <input type="button" value='<s:text name="hdfs.task.search"/>' class="search_but" onclick="doSubmit();"/>
                </div>
                <table cellspacing="0" class="all_tab_body">
                    <thead class="all_tab_thead">
                        <tr>
                            <th><s:text name="hdfs.task.file.name"/></th>
                            <th><s:text name="hdfs.task.file.kind"/></th>
                            <th><s:text name="hdfs.task.file.size"/></th>
                            <th><s:text name="hdfs.task.file.format"/></th>
                            <!--  
                            <th><s:text name="hdfs.task.server.path"/></th>
                            <th><s:text name="hdfs.task.hadoop.path"/></th>-->
                            <th><s:text name="hdfs.task.finish.addtime"/></th>
                            <th><s:text name="hdfs.task.uploader.kind"/></th>
                           
                            <th><s:text name="hdfs.task.action"/></th>
                        </tr>
                    </thead>
                    <tbody>
                       <s:iterator value="%{page.results}" var="hf">
                        <tr>
                            <td class="text_left"><s:property value="#hf.fileName"/></td>
                            <td><s:if test="#hf.kind==\"Image\""><s:text name="hdfs.task.file.kind.image"/></s:if>
                             <s:elseif test="#hf.kind==\"Audio\""><s:text name="hdfs.task.file.kind.audio"/></s:elseif>
                             <s:elseif test="#hf.kind==\"Vedio\""><s:text name="hdfs.task.file.kind.vedio"/></s:elseif>
                             <s:elseif test="#hf.kind==\"Software\""><s:text name="hdfs.task.file.kind.software"/></s:elseif>
                            </td>
                            <td class="text_left"><s:property value="#hf.fileSize"/>B</td>
                            <td class="text_left"><s:property value="#hf.fileFormat"/></td>
                            <!-- 
                            <td class="text_left"><div class="all_tb_content" title=""><s:property value="#hf.uploadPath"/></div></td>
                            <td class="text_left"><div class="all_tb_content" title=""><s:property value="#hf.hdfsPath"/></div></td> -->
                            <td >
                            <s:date name="#hf.taskAddTime" format="yyyy-MM-dd HH:mm:ss" />
                            </td>  
                            <td class="text_left"><s:if test="#hf.uploaderKind==\"web\""><s:text name="hdfs.task.uploader.kind.web"/></s:if>
                             <s:elseif test="#hf.uploaderKind==\"app\""><s:text name="hdfs.task.uploader.kind.app"/></s:elseif>
                             </td>
                            
                            <td >
                            <s:if test="#hf.uploaderKind==\"web\""><a class="all_hover_but" href='<s:property value="webFileDownloadUrl"/><s:property value="#hf.fileName"/>.<s:property value="#hf.fileFormat"/>'><s:text name="hdfs.task.file.download"/></a></s:if>
                             <s:elseif test="#hf.uploaderKind==\"app\""><a class="all_hover_but"  href='<s:property value="appFileDownloadUrl"/><s:property value="#hf.fileName"/>.<s:property value="#hf.fileFormat"/>'><s:text name="hdfs.task.file.download"/></a></s:elseif>
                             <a class="all_hover_but" href='hdfs/hdfsTaskAction_detail.action?hdfsTaskVO.id=<s:property value="#hf.id"/>'><s:text name="hdfs.task.detail"/></a>
                            </td> 
                        </tr>
                        
                        </s:iterator>
                    </tbody>
				</table>
				<s:include value="/views/common/paging.jsp"/>
			</div>
			</s:form>
		</div>
	</div>
	<!-- 
	<script>
		QueryLoader.selectorPreload = "body";
		QueryLoader.init();
	</script> -->
</body>
</html>
