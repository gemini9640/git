<%@ page language="java" pageEncoding="UTF-8"%>
<%
String basePath  = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
request.setAttribute("base",basePath);
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
response.setHeader("Pragma","no-cache"); //HTTP 1.0    
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server

request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content") != null ? request.getParameter("content") : "";
%>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DEW</title>
<script type="text/javascript" src="${base}source/js/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="${base}source/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${base}source/js/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="${base}source/js/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${base}source/js/kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8" src="${base}source/js/kindeditor/plugins/code/prettify.js"></script>
<script>
function upload() {
	var formData = new FormData($("#uploadForm")[0]);  
	$.ajax({
	     url : "${base}admin/img/upload.do",
	     type : "POST",
	     data : formData,
	     async: false,  
         cache: false,  
         contentType: false,  
         processData: false,  
	     success : function(json) {
	     	var imgId = json.imgId;
	     	$("#imgId").remove();
	     	$("#uploadForm").append("<img id='imgId' src='${base}admin/img/show.do?imgId="+imgId+"' width='400px' height='150px'/>");
	     	$("#img_id").val(imgId);
	     },
	     error : function(data) {
	          alert(data);
	     }
	});
}
</script>
</head>
<body>
<a href="${base}admin/project/listAll.do">查看所有项目</a>&nbsp&nbsp&nbsp&nbsp<a href="${base}logout">退出</a>

 <h1>添加一个项目</h1>
<!--  <h2>只能上传单张10M以下的 PNG、JPG、GIF 格式的图片</h2> -->
<!--  <form id="uploadForm" method="post" enctype="multipart/form-data"> -->
<!--      上传图片:<input type="file" name="file"> -->
<!--      <input onclick="upload();" type="button" value="上传"> -->
<!--      <br/> -->
<!--  </form> -->
 <form name="newsForm" action="${base}admin/project/add.do" method="post">
 	 <input type="hidden" id="img_id" name="img_id"/>
     项目:<input type="text" name="project">
     <br/>
     标题:<input type="text" name="title">
     <br/>
     caption:<input type="text" name="caption">
     <br/>
     内容: 
     <br/>
     <textarea name="content" cols="100" rows="8" style="width:700px;height:200px;"></textarea>
     <br/>
     <input type="submit" value="添加">
 </form>
</body>
</html>