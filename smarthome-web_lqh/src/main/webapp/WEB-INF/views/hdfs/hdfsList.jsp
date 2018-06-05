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
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<!-- 
        <script type="text/javascript" src="<%=basePath%>/js/jqueryLoader.js"></script> -->
        <script type="text/javascript" src="<%=basePath%>js/jquery_all.js"></script>
</head>
<script type="text/javascript">
 function locationAction(url)
    {      
      window.location.href =url;	  
	  
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
				<a href="hdfs/hdfsFileAction_list.action" class="all_menu_a"><s:text name="hdfs.cloud.store"/></a> <a
					href="hdfs/hdfsTaskAction_list.action"><s:text name="hdfs.cloud.task"/></a>
			</div>
		</div>
		<div class="main_outside">
		<s:form action="hdfs/hdfsFileAction_list.action"  method="post"  theme="simple">
			<div class="all_inside">
				<div class="all_tab_top">
					<label><s:text name="hdfs.uploader.kind"/></label>
                    <div class="select_div">
                        <select name="localHdfsVO.uploaderKind">
                          <option value="all" <s:if test="localHdfsVO.uploaderKind==\"all\"">selected</s:if>><s:text name="hdfs.all"/></option>
                          <option value="app" <s:if test="localHdfsVO.uploaderKind==\"app\"">selected</s:if>><s:text name="hdfs.uploader.kind.app"/></option>
                          <option value="web" <s:if test="localHdfsVO.uploaderKind==\"web\"">selected</s:if>><s:text name="hdfs.uploader.kind.web"/></option>
                        </select>
                    </div>
                    <label><s:text name="hdfs.file.kind"/></label>
                    <div class="select_div">
                        <select name="localHdfsVO.kind">
                          <option value="all" <s:if test="localHdfsVO.kind==\"all\"">selected</s:if>><s:text name="hdfs.all"/></option>
                          <option value="Image" <s:if test="localHdfsVO.kind==\"Image\"">selected</s:if>><s:text name="hdfs.file.kind.image"/></option>
                          <option value="Audio" <s:if test="localHdfsVO.kind==\"Audio\"">selected</s:if>><s:text name="hdfs.file.kind.audio"/></option>
                          <option value="Vedio" <s:if test="localHdfsVO.kind==\"Vedio\"">selected</s:if>><s:text name="hdfs.file.kind.vedio"/></option>
                          <option value="Software" <s:if test="localHdfsVO.kind==\"Software\"">selected</s:if>><s:text name="hdfs.file.kind.software"/></option>
                        </select>
                    </div>
                    <label><s:text name="hdfs.file.name"/></label>
                    <!--  
                    <input type="text" name="localHdfsVO.fileName" class="all_tab_top_input"/> -->
                    <s:textfield name="localHdfsVO.fileName" cssClass="all_tab_top_input" maxlength="50"></s:textfield>                   
                    <input type="button" value='<s:text name="hdfs.search"/>' class="search_but" onclick="doSubmit();"/>
                </div>
                <table cellspacing="0" class="all_tab_body">
                    <thead class="all_tab_thead">
                        <tr>
                            <th><s:text name="hdfs.file.name"/></th>
                            <th><s:text name="hdfs.file.kind"/></th>
                            <th><s:text name="hdfs.file.size"/></th>
                            <th><s:text name="hdfs.file.format"/></th>
                            <!-- 
                            <th><s:text name="hdfs.server.path"/></th>
                            <th><s:text name="hdfs.hadoop.path"/></th> -->
                            <th><s:text name="hdfs.uploader.kind"/></th>
                            <th><s:text name="hdfs.finish.finishtime"/></th>
                            <th><s:text name="hdfs.action"/></th>
                        </tr>
                    </thead>
                    <tbody>
                       <s:iterator value="%{page.results}" var="hf">
                        <tr>
                            <td class="text_left"><s:property value="#hf.fileName"/></td>
                            <td class="text_left"><s:if test="#hf.kind==\"Image\""><s:text name="hdfs.file.kind.image"/></s:if>
                             <s:elseif test="#hf.kind==\"Audio\""><s:text name="hdfs.file.kind.audio"/></s:elseif>
                             <s:elseif test="#hf.kind==\"Vedio\""><s:text name="hdfs.file.kind.vedio"/></s:elseif>
                             <s:elseif test="#hf.kind==\"Software\""><s:text name="hdfs.file.kind.software"/></s:elseif>
                            </td>
                            <td class="text_left"><s:property value="#hf.fileSize"/>B</td>
                            <td class="text_left"><s:property value="#hf.fileFormat"/></td>
                            <!-- 
                            <td class="text_left"><div class="all_tb_content" title=""><s:property value="#hf.localPath"/></div></td>
                            <td class="text_left"><div class="all_tb_content" title=""><s:property value="#hf.hdfsPath"/></div></td>
                             -->
                            <td class="text_left"><s:if test="#hf.uploaderKind==\"web\""><s:text name="hdfs.uploader.kind.web"/></s:if>
                             <s:elseif test="#hf.uploaderKind==\"app\""><s:text name="hdfs.uploader.kind.app"/></s:elseif>
                             </td>
                             <td >
                            <s:date name="#hf.addTime" format="yyyy-MM-dd HH:mm:ss" />
                            </td>
                            <td >
                            <s:if test="#hf.uploaderKind==\"web\""><a class="all_hover_but" href='<s:property value="webFileDownloadUrl"/><s:property value="#hf.fileName"/>.<s:property value="#hf.fileFormat"/>'><s:text name="hdfs.file.download"/></a>
                            </s:if>
                             <s:elseif test="#hf.uploaderKind==\"app\""><a class="all_hover_but" href='<s:property value="appFileDownloadUrl"/><s:property value="#hf.fileName"/>.<s:property value="#hf.fileFormat"/>'><s:text name="hdfs.file.download"/></a></s:elseif>
                            <a class="all_hover_but" href='hdfs/hdfsFileAction_detail.action?localHdfsVO.id=<s:property value="#hf.id"/>'><s:text name="hdfs.file.detail"/></a>
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
	</script>-->
</body>
</html>
