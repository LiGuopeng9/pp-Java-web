<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="css/login.css" />
<style>

</style>
</head>
<body>
	<img id="login_logo" alt="login" src="images/login/login_top.png">

	<form class="login_form" action="login" method="post">
		<div class="form-group row">
			<label class="col-2   offset-2 login_lable">用户名</label>
			<div class="col-7 ">
				<input type="text" class="  form-control login_input" id="userid"  name="userid"
					placeholder="请输入用户名">
			</div>
		</div>


		<div class="form-group row">
			<label class="col-2   offset-2 login_lable">密码</label>
			<div class="col-7 ">
				<input type="password" class="  form-control login_input" id="userpwd" name="userpwd">
			</div>
		</div>
		<div class=" btn-toolbar">
			
			<div class="offset-9 ">
				<button type="submit" class="btn btn-primary login_btn" onclick="submit();">登录
				</button>
			</div>
		</div>
	</form>
	<div class="offset-6 ">
				<button  class="btn btn-primary login_btn register_btn " onclick="register()">注册</button>
			</div>
	<script type="text/javascript">
function register() {
	location.href="toregister";
	
}
	</script>


</body>
</html>
