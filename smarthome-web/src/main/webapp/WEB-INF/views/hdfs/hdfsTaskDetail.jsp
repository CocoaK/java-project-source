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
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title></title>
<link href="<%=basePath%>css/layout.css" rel="stylesheet" />
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>js/jqueryLoader.js"></script>
        <script type="text/javascript" src="<%=basePath%>js/jquery_all.js"></script>
        <script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.min.js"></script>
</head>
  
  <body>
	<div class="main_section">
    	<div class="all_aside">
			<div class="all_menu">
			     <!-- 
				<a href="hdfs/hdfsFileAction_list.action" ><s:text name="hdfs.task.cloud.store"/></a> --> <a
					href="hdfs/hdfsTaskAction_list.action" ><s:text name="hdfs.task.cloud.task"/></a>
               <a href='hdfs/hdfsTaskAction_detail.action?hdfsTaskVO.id=<s:property value="hdfsTaskVO.id"/>' class="all_menu_a"><s:text name="hdfs.task.detail"/></a>
			</div>
		</div>
        <div class="main_outside">
            <div class="all_inside">
                <table cellspacing="0" class="all_tab_body all_tab_bodys">
                    <tbody>
                        <tr>
                            <td width="120"><s:text name="hdfs.task.server.path"/>:</td>
                            <td><s:property value="hdfsTaskVO.uploadPath"/></td>
                        </tr>
                       <!-- 
                       <tr>
                            <td width="120"><s:text name="hdfs.task.hadoop.path"/>:</td>
                            <td><s:property value="hdfsTaskVO.hdfsPath"/></td>
                        </tr> -->
                    </tbody>
                </table>
                
            </div>
        </div>
    </div>
</body>
</html>
