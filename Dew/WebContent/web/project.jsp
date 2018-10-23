<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String basePath  = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
request.setAttribute("base",basePath);
%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Dew Shao, ${project.project}</title>
		<link href="${base}web/css/bootstrap.css" rel='stylesheet' type='text/css' />
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="${base}web/js/jquery.min.js"></script>
		 <!-- Custom Theme files -->
		<link href="${base}web/css/style.css" rel='stylesheet' type='text/css' />
   		 <!-- Custom Theme files -->
   		 <!---- start-smoth-scrolling---->
		<script type="text/javascript" src="${base}web/js/move-top.js"></script>
		<script type="text/javascript" src="${base}web/js/easing.js"></script>
		 <!---- start-smoth-scrolling---->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		<!----webfonts--->
		<link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900,100italic,300italic,400italic,700italic,900italic' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Playball' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" type="text/css" href="${base}web/css/jquery.hoverGrid.css"></link>
		<!---//webfonts--->
		<!----start-top-nav-script---->
		<script>
			$(function() {
				var pull 		= $('#pull');
					menu 		= $('nav ul');
					menuHeight	= menu.height();
				$(pull).on('click', function(e) {
					e.preventDefault();
					menu.slideToggle();
				});
				$(window).resize(function(){
	        		var w = $(window).width();
	        		if(w > 320 && menu.is(':hidden')) {
	        			menu.removeAttr('style');
	        		}
	    		});
			});
		</script>
		<!----//End-top-nav-script---->
	</head>
	<body>
		<!----- start-header---->
			<div id="home" class="header">
					<div class="top-header">
						<div class="container">
						<div class="logo">
							<a href="${base}">DEW SHAO</a>
						</div>
						<!----start-top-nav---->
						 <nav class="top-nav">
							<ul class="top-nav">
							<li class="active"><a href="${base}" class="scroll">Home</a></li>
								<li class="page-scroll"><a href="#work" class="scroll">Portfolio</a></li>
								<li class="page-scroll"><a href="#work" class="scroll">About</a></li>
								<li class="page-scroll"><a href="#team" class="scroll">Blog</a></li>
								<li class="contatct-active" class="page-scroll"><a href="#contact" class="scroll">Contact</a></li>
							</ul>
							<a href="#" id="pull"><img src="${base}web/images/nav-icon.png" title="menu" /></a>
						</nav>
						<div class="clearfix"> </div>
					</div>
				</div>
			</div>
			<!----- //End-header---->
			<!---work--->
			<div class="head-one text-center">
				<h2>${project.project}</h2>
				<p style="width:70%;text-align:left;">${project.content}</p>
				<c:forEach items="${imgs}" var="img" varStatus="vs">
					<br/>
					<img src="${base}img/show.do?folder=${project.id}&path=${img.newName}"
						 width="70%" height="70%"/>
					<br/>
				</c:forEach>
			</div>
			<!---works--->
			<!---footer--->
			<div class="footer">
				<div class="container">
					<div class="footer-left">
						<p><span>Adress:</span> ipsum dolor sit amet, consectetur adipiscing - elit.</p>
					</div>
					<div class="footer-right">
						<p>Copyright &copy; 2014.Company name All rights reserved.More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
						<script type="text/javascript">
							$(document).ready(function() {
								/*
								var defaults = {
						  			containerID: 'toTop', // fading element id
									containerHoverID: 'toTopHover', // fading element hover id
									scrollSpeed: 1200,
									easingType: 'linear' 
						 		};
								*/
								
								$().UItoTop({ easingType: 'easeOutQuart' });
								
							});
						</script>
							<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
					</div>
					<div class="clearfix"> </div>
				</div>
			</div>
			<!---//footer--->
<script type="text/javascript">
	$(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
</script>
</body>
</html>