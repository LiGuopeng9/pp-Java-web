
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

<link rel="stylesheet" href="css/home_content.css" />

<style>
.my2_top {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 150px;
  font-size: 3rem;
  text-align: center;
  border-bottom: 10px solid #e6e6e6;
  margin-bottom: 50px;
  background: #F7F7F7;
}

.my2_top>img {

  position: absolute;
  width: 100px;
  left: 25px;
  top: 25px;
}

.my2_top>p {
  font-size: 3rem;
  position: relative;
  top: 30px;
}
.my2_mid{
margin-top: 200px;
background: #E6E6E6;
}
</style>
</head>
<body>
	<div class="my2_top">
		<p>帖子详情</p>
		<img src="images/icons/icons_svg_back.svg" onclick="backToMain()" />
	</div>
	<div class="my2_mid">
	<%@include file="home_content.jsp"%>
	</div>
	
	

</body>
<script type="text/javascript">
		//点赞收藏 the_a表示被点击的标签
		function backToMain()
		{
			 window.location.href = 'toMain';
		}

	</script>
</html>
