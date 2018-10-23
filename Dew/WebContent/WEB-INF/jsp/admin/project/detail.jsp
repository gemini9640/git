<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String basePath  = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
request.setAttribute("base",basePath);
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
response.setHeader("Pragma","no-cache"); //HTTP 1.0    
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server

request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DEW</title>
<script type="text/javascript" src="${base}source/js/jquery-3.3.1.min.js"></script>
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
	     success : function(data) {
	    	 	if(data.isSuccess == 0) {
	    	     	var imgNames = $.parseJSON(data.imgNames);
	    	     	for(var key in imgNames) {
	    		     	$("#div_files").html("");
	    	     		$("#allImgs").append("<div><img id='imgId"+key+"' src='${base}admin/img/showByPath.do?folder=${project.id}&path="+imgNames[key]+"' width='400px' height='150px'/>"+
	    	     				"<a href='###' onclick='putFrontImg(${project.id}, \""+imgNames[key]+"\");'>设为封面</a></div>"+
	    	     				"<br/><br/><br/>");
	    	     	}
	    	 	}
	    	 	$("#uploadForm")[0].reset();
    	     	alert(data.resultmsg);
	     },
	     error : function(data) {
	    	 	console.log(data);
	        alert(data);
	     }
	});
}
	
	function previewFile(){ 
		var files = $("input[type='file']")[0].files;
		for(var key in files) {
			var num = Number(key);
			var file = files[key];
			showPreview(file, key);
		}
	}
	
	function showPreview(file, key) {
		var reg = /^[0-9]+$/;
		  if (reg.test(key)) {
			$("#div_files").append("<img width='400px' height='200px' id='preview"+key+"'>");
			var img = $("#preview"+key);
			var reader  = new FileReader();
			reader.onloadend = function () {
				img.attr("src",reader.result);
			}
			if (file) {
			    reader.readAsDataURL(file);
			} else {
				img.attr("src","");
			}
		}	
	}
	
	function putFrontImg(projectId, imgName) {
 		$.post("${base}admin/project/putFrontImg.do", {
 			projectId : projectId,
 			imgName : imgName
 		}, function(data) {
 			if(data.isSuccess == 0) {
 				var project = $.parseJSON(data.project);
 				$("#cover").attr("src","${base}admin/img/showByPath.do?folder="+projectId+"&path="+project.imgPath);
 			}
 			alert(data.errormsg);
 		});
 	}
	
	function deleteImg(imgId) {
		if(confirm("确定删除吗？")) {
			$.post("${base}admin/img/delete.do", {
				imgId : imgId
	 		}, function(data) {
	 			if(data.isSuccess == 0) {
	 				$("#img_"+imgId).remove();
	 			}
	 			alert(data.resultmsg);
	 		});
		}
	}
</script>
</head>
<body>
<c:if test="${not empty resultMsg}">
	<script>
		alert("${resultMsg}");
	</script>
</c:if>
<a href="${base}admin/project/listAll.do">查看所有项目</a>&nbsp&nbsp&nbsp&nbsp<a href="${base}logout">退出</a>
<form action="${base}admin/project/edit.do" method="post">
	<input type="hidden" name="id" value="${project.id}"/>
	 项目:&nbsp&nbsp<input name="project" type="text" value="${project.project}"/><br/>
	 标题:&nbsp&nbsp<input name="title" type="text" value="${project.title}"/><br/>
	 概览:&nbsp&nbsp<input name="caption" type="text" value="${project.caption}"/><br/>
	 内容:&nbsp&nbsp<textarea name="content">${project.content}</textarea><br/>
	 <input type="submit" value="更新"/>
</form>

 <h2>封面</h2>
 <div><img  id="cover" src="${base}admin/img/showByPath.do?folder=${project.id}&path=${project.imgPath}" width='400px' height='150px'/></div>
 <form id="uploadForm" method="post" style="border:2px blue solid" enctype="multipart/form-data">
 	 <input type="hidden" name="projectId" value="${project.id}"> 
     <h2>上传作品(只能上传单张10M以下的 PNG、JPG、GIF 格式的图片):</h2>
     <br/>
     <div id="div_files"></div>
     <input type="file" name="file" onchange="previewFile();" multiple><br/>
     <br/>
     <input onclick="upload();" type="button" value="上传">
     <br/>
 </form>
 
 <h1>作品展示</h1>
 <div id="allImgs">
 <c:forEach items="${imgs}" var="img">
	<c:if test="${not empty img.newName}">
		<div id="img_${img.id}">
			<img src="${base}admin/img/showByPath.do?folder=${project.id}&path=${img.newName}" width='400px' height='150px'/>
			<a href="###" onclick="putFrontImg(${project.id}, '${img.newName}');">设为封面</a>
			<a href="###" onclick="deleteImg(${img.id});">删除</a>
			<br/><br/><br/>
		</div>
	</c:if>
</c:forEach>
 </div>
</body>
</html>