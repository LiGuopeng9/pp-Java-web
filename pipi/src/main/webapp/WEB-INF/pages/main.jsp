
<%@ page language="java" import="java.util.*,java.io.*" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>皮皮</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<script src="lib/jquery-3.4.1.min.js"></script>
<script src="bootstrap/js/bootstrap.bundle.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- 样式表 -->
<link rel="stylesheet" href="css/public.css" />
<link rel="stylesheet" href="css/nav.css" />
<link rel="stylesheet" href="css/home.css" />
<link rel="stylesheet" href="css/home_content.css" />
<link rel="stylesheet" href="css/explore.css" />
<link rel="stylesheet" href="css/explore_content.css" />
<link rel="stylesheet" href="css/mes.css" />
<link rel="stylesheet" href="css/mes_content.css" />
<link rel="stylesheet" href="css/my.css" />
<link rel="stylesheet" href="css/publish.css" />
<style>
</style>
</head>
<body>
	<nav class="nav_bottom">
	<div class="nav nav-tabs " role="tablist">
		<a class="nav-item nav-link active nav_bottom_box nav_bottom_box1" id="nav_bottom_home" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">
			<div class="nav_bottom_item">
				<img src="images/icons/icons_svg_home.svg" />
				<p>主页</p>
			</div>
		</a> <a class="nav-item nav-link  nav_bottom_box nav_bottom_box1" id="nav_bottom_explore" data-toggle="tab" href="#nav-explore" role="tab" aria-controls="nav-explore" aria-selected="false">
			<div class="nav_bottom_item">
				<img src="images/icons/icons_svg_eye.svg" />
				<p>发现</p>
			</div>
		</a> <a class="nav-item nav-link nav_bottom_box nav_bottom_box2" id="" data-toggle="tab" href="#nav-add" role="tab" aria-controls="nav-add" aria-selected="false">
			<div class="nav_bottom_item">
				<img src="images/icons/add.png" id="nav_bottom_add_img" />

			</div>
		</a> <a class="nav-item nav-link nav_bottom_box nav_bottom_box3" id="" data-toggle="tab" href="#nav-mes" role="tab" aria-controls="nav-mes" aria-selected="false">
			<div class="nav_bottom_item">
				<img src="images/icons/icons_svg_chatboxes.svg" />
				<p>消息</p>
			</div>
		</a> <a class="nav-item nav-link nav_bottom_box nav_bottom_box3" id="" data-toggle="tab" href="#nav-my" role="tab" aria-controls="nav-my" aria-selected="flase">
			<div class="nav_bottom_item">
				<img src="images/icons/icons_svg_contact.svg" />
				<p>我的</p>
			</div>
		</a>
	</div>
	</nav>


	<div class="tab-content">
		<div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab"><%@include file="home.jsp"%></div>
		<div class="tab-pane fade" id="nav-explore" role="tabpanel" aria-labelledby="nav-explore-tab"><%@include file="explore.jsp"%></div>
		<div class="tab-pane fade" id="nav-add" role="tabpanel" aria-labelledby="nav-add-tab"><%@include file="publish.jsp"%></div>
		<div class="tab-pane fade" id="nav-mes" role="tabpanel" aria-labelledby="nav-mes-tab"><%@include file="message.jsp"%></div>
		<div class="tab-pane fade" id="nav-my" role="tabpanel" aria-labelledby="nav-my-tab"><%@include file="my.jsp"%></div>

	</div>




</body>
</html>
