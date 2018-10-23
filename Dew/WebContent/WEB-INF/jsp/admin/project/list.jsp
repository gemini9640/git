<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String basePath  = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
request.setAttribute("base",basePath);
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
response.setHeader("Pragma","no-cache"); //HTTP 1.0    
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<!DOCTYPE  html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>news list</title>
<script type="text/javascript" src="${base}source/js/jquery-3.3.1.min.js"></script>
<script>
	function deleteProject(projectId) {
		$.post("${base}admin/project/delete.do",{
			id : projectId 
		}, function (json) {
			alert(json.resultMsg);
			if (json.isSuccess == 0){
				$("#pro_"+projectId).remove();
			}
		});
	}
</script>
</head>
<body>

	<a href="${base}admin/project/addStart.do">添加项目</a>&nbsp&nbsp&nbsp&nbsp
	<a href="${base}logout">退出</a>
	<br/>
	<br/>
	<c:forEach items="${projectList}" var="project" varStatus="vs">
		<div id="pro_${project.id}">
			<h1>${project.project}</h1>
			<a href="${base}admin/project/detail.do?id=${project.id}">查看项目</a>
			<a href="###" onclick="deleteProject(${project.id});">删除</a>
			<br/>
			<c:if test="${not empty project.imgPath}">
				<img src="${base}admin/img/showByPath.do?folder=${project.id}&path=${project.imgPath}" width='400px' height='150px'/>
			</c:if>
			<h3>${project.title}</h3>
			<p>${project.caption}</p>
			<p>${project.content}</p>
			<hr/>
		</div>
	</c:forEach>
</body>
</html>