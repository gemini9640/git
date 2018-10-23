<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String basePath  = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
request.setAttribute("base",basePath);
%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Dew Shao</title>
		<link href="${base}web/css/bootstrap.css" rel='stylesheet' type='text/css' />
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="${base}web/js/jquery.min.js"></script>
		 <!-- Custom Theme files -->
		<link href="${base}web/css/style.css" rel='stylesheet' type='text/css' />
   		 <!-- Custom Theme files -->
   		 <!---- start-smoth-scrolling---->
		<script type="text/javascript" src="${base}web/js/move-top.js"></script>
		<script type="text/javascript" src="${base}web/js/easing.js"></script>
		<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){		
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
				});
			});
		</script>
		 <!---- start-smoth-scrolling---->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		<!----webfonts--->
		<link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900,100italic,300italic,400italic,700italic,900italic' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Playball' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" type="text/css" href="${base}web/css/jquery.hoverGrid.css"></link>
		<!---//webfonts--->
		<!----start-top-nav-script---->
		<style>
			.team-member h3, .team-member span {
			    color: #A7A7A7;
			    font-weight: 200;
			    font-style: normal;
			    text-transform: uppercase;
			}
			
			.head-one h2{
				color: #CCD1D1;
			    font-weight: 200;
			    text-transform: uppercase;
			    font-size: 1.8em;
			}
		</style>
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
							<a href="#">DEW SHAO</a>
						</div>
						<!----start-top-nav---->
						 <nav class="top-nav">
							<ul class="top-nav">
							<li class="active"><a href="#home" class="scroll">Home</a></li>
								<li class="page-scroll"><a href="#work" class="scroll">Portfolio</a></li>
<!-- 								<li class="page-scroll"><a href="#work" class="scroll">About</a></li> -->
								<li class="page-scroll"><a href="#team" class="scroll">About</a></li>
								<li class="contatct-active" class="page-scroll"><a href="#team" class="scroll">Contact</a></li>
							</ul>
							<a href="#" id="pull"><img src="${base}web/images/nav-icon.png" title="menu" /></a>
						</nav>
						<div class="clearfix"> </div>
					</div>
				</div>
			</div>
			<!----- //End-header---->
			<!----banner---->
			<div class="banner text-center">
				<div class="container">
				<!--	
					<div class="banner-info">
						<h1><span>let us make</span> your lives easier</h1>
						<p>We specialize in crafting awesome ideas for web, mobile and beyond.</p>
						<label class="page-scroll"><a class="big-btn scroll" href="#work1"><span> </span></a></label>
					</div>
				-->
				</div>
			</div>
			<!--//banner---->
			<!---work--->
			<div id="work" class="work">
				<div id="work1" class="container">
					<div class="head-one text-center">
						<h2>My recent project</h2>
						
					</div>
					<!---works--->
					
					<div class="works">
							<div id="whatever">
							    <div id="end_project" class="clearfix"> </div>               
							</div>
					</div>
					 <div class="clearfix"> </div>   
					</div>
				</div>
					<!---//works--->
					<!---team--->
					<div id="team" class="team">
						<div class="container">
							<div class="head-one text-center team-head">
								<h2>Dew Shao  Illustration Designer</h2>
								<p>我叫邵露，一个野生成长的插画设计师。曾经做过动画 拍过微电影 做过衣服 玩过摄影 当伪艺术家 美术老师。当你经历了很多之后那个心心念一直想做的事情应该就是这辈子要做的事情了。作为伪艺术家 部分作品也收录于中国最大的当代艺术网站Art。虽然插画设计师与原画师和画家有所同有所不同，为了能更自如的驾驭画笔，画出更多的可能，也还算欣慰，对于大多数项目的需求，都可匹配适合它气质的画风。目前只是作为插画设计师生涯的开始，还有更多需要探索和学习的。愿自己不忘初心，在创作的路上严格要求自己，作品如人品，落地可生花。</p>
							</div>	
							<!----team-members---->
							<div class="team-members">
								<div class="col-md-3">
									<div class="team-member text-center">
										<img class="t-pic" src="${base}web/images/t1.jpg" title="name" />
										<h3>Dew Shao 邵露</h3>
										<span>落地生花</span>
										<ul class="t-social unstyled-list list-inline">
											<li><a class="twitter" href="#"><span> </span></a></li>
											<li><a class="dribbble" href="#"><span> </span></a></li>
											<li><a class="in" href="#"><span> </span></a></li>
											<div class="clearfix"> </div>
										</ul>
									</div>
								</div>
								<div class="col-md-3">
									<div class="team-member text-center">
										<img class="t-pic" src="${base}web/images/t2.jpg" title="name" />
										<h3>Dew Shao 邵露</h3>
										<span>落地生花</span>
										<ul class="t-social unstyled-list list-inline">
											<li><a class="twitter" href="#"><span> </span></a></li>
											<li><a class="dribbble" href="#"><span> </span></a></li>
											<li><a class="in" href="#"><span> </span></a></li>
											<div class="clearfix"> </div>
										</ul>
									</div>
								</div>
								<div class="col-md-3">
									<div class="team-member text-center">
										<img class="t-pic" src="${base}web/images/t3.jpg" title="name" />
										<h3>Dew Shao 邵露</h3>
										<span>落地生花</span>
										<ul class="t-social unstyled-list list-inline">
											<li><a class="twitter" href="#"><span> </span></a></li>
											<li><a class="dribbble" href="#"><span> </span></a></li>
											<li><a class="in" href="#"><span> </span></a></li>
											<div class="clearfix"> </div>
										</ul>
									</div>
								</div>
								<div class="col-md-3">
									<div class="team-member text-center">
										<img class="t-pic" src="${base}web/images/t4.jpg" title="name" />
										<h3>Dew Shao 邵露</h3>
										<span>落地生花</span>
										<ul class="t-social unstyled-list list-inline">
											<li><a class="twitter" href="#"><span> </span></a></li>
											<li><a class="dribbble" href="#"><span> </span></a></li>
											<li><a class="in" href="#"><span> </span></a></li>
											<div class="clearfix"> </div>
										</ul>
									</div>
								</div>
								<div class="clearfix"> </div>
							</div>
							<!----//team-members---->
						</div>
					</div>
					<!---//team--->
					<!---Contact--->
					<!---//Contact--->
					<!---footer--->
					<div class="footer">
						<div class="container">
							<div class="footer-left">
								<p>在微博和instagram不定时分享一些创作故事，欢迎大家关注一起交流探讨。</p>
							</div>
							<div class="footer-right">
								<p>微博：邵露DEW-SHAO&nbspinstagram:dew-shao</p>
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
	<script>
		function listAllProjects() {
			$.post("${base}project/listAll.do", function(data) {
				var html = "";
				for(var key in data) {
					var project = data[key];
					html = "<a href='${base}project/detail.do?id="+project.id+"'>"+
							"<div class='col-md-4 work-grid'>"+
							    "<div class='item'>"+
							        "<img  src='${base}img/show.do?folder="+project.id+"&path="+project.imgPath+"' title='name' />"+
							        "<div class='caption' style='display: none;'>"+
							            "<h2>"+project.project+"</h2>"+
							            "<p>"+project.caption+"</p>"+
							        "</div>"+
							    "</div>  "+
						    "</div>"+
						    "</a>"; 
				    $("#end_project").before(html);
				}
			});
		}
		listAllProjects();
	</script>
	<!---images-hover-effects--->
	<script type="text/javascript">
	    $(document).ready(function() {
	    	  $.getScript("${base}web/js/jquery.hoverGrid.js", function(event) {
	    		  $('#whatever').hoverGrid();
	    	  });
	    });
	</script>
	<!---images-hover-effects--->
</body>
</html>