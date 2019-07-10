<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="css/publishSuccess.css" />
<style>
</style>
</head>
<body>
	<div class="publishSuccess_top">
		<p>发帖子</p>
	</div>
	<div class="publishSuccess_mid">
		<p>发帖成功啦！</p>
		<p>将于3秒后返回首页...</p>
		<button class="btn btn-primary publishSuccess_mid_btn" onclick="backToMain();">返回主页</button>
		<button class="btn btn-primary publishSuccess_mid_btn" onclick="checkEs();">查看帖子</button>
	</div>
	<script type="text/javascript">
	function backToMain() {
    window.location.href = 'toMain';
	}
  function checkEs(){
    window.location.href = "esDetial?esId=${requestScope.esId}";
  }
/*   setTimeout(backToMain,3000); */
</script>

</body>
</html>
