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
    <link rel="stylesheet" href="css/register.css" />
    <!-- 样式表 -->
    <style>

    </style>
  </head>
  <body>
    <img id="register_logo" alt="register" src="images/login/login_top.png">

    <form class="register_form" action="changemy" method="post" enctype="multipart/form-data">

      <div class="form-group row">
        <label class="col-3   offset-2 register_lable">用户名</label>
        <div class="col-7 ">
          <p><%=request.getSession().getAttribute("userId")%></p>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-3   offset-2 register_lable">昵称</label>
        <div class="col-7 ">
          <input type="text" class="  form-control register_input" id="username" name="username" placeholder="<%=request.getSession().getAttribute("userName")%>">
        </div>
      </div>
      <div class="form-group row">
        <label class="col-3   offset-2 register_lable">密码</label>
        <div class="col-7 ">
          <input type="password" class="  form-control register_input" id="userpwd" name="userpwd">
        </div>
      </div>
      <div class="form-group row">
        <label class="col-3   offset-2 register_lable">上传头像</label>
        <a class="input_a col-7">点击上传
          <input type="file" class="inputfile " id="userimg" name="userimg">
        </a>
      </div>
      <div class=" btn-toolbar">
        <div class=" offset-8">
          <button type="submit" class="btn btn-primary register_btn" onclick="submit()" style="width: 250px">确认修改
          </button>
        </div>
      </div>
    </form>
    <script type="text/javascript">
      function submit() {
        $("#register_form").submit();

      }
    </script>


  </body>
</html>
