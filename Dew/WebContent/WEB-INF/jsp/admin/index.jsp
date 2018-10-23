<%@ page language="java" pageEncoding="UTF-8"%>
<%
String basePath  = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
request.setAttribute("base",basePath);
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1    
response.setHeader("Pragma","no-cache"); //HTTP 1.0    
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server

%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dew</title>
<script type="text/javascript" src="${base}source/js/jquery-3.3.1.min.js"></script>
<script>
</script>
</head>
<body>
<h4 style="color:red">${errInfo}</h4>
 <form action="${base}login.do" method="post">
     用户名:<input type="text" name="username">
     <br/>
     密码:<input type="password" name="password">
     <br/>
     <input type="submit" value="登录">
 </form>
</body>
</html>