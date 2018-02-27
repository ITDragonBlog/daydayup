<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh">
	<head>
		<meta name="viewport" content="initial-scale=1.0, width=device-width, user-scalable=no" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
		<meta http-equiv="X-UA-Compatible" content="IE=8" />
		<title>欢迎登录</title>
		<link type="image/x-icon" href="images/favicon.ico" rel="shortcut icon">
		<link rel="stylesheet" href="static/css/main.css" />
	</head>
	<body>
		<div class="wrapper">
			<div class="container">
				<h1>Welcome</h1>
				<form method="post" class="form">
					<input type="text" value="itdragon" name="username" placeholder="Account"/>
					<input type="password" value="12345678" name="password" placeholder="Password"/>
					<button type="button" id="login-button">Login</button>
				</form>
			</div>
			<ul class="bg-bubbles">
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ul>
		</div>
		<script type="text/javascript" src="static/js/jquery-1.10.1.min.js" ></script>
		<script type="text/javascript">
			$(function () {
				sessionStorage.removeItem("nav-index"); // 页面或窗口关闭后才失效，用户登出不失效 
			});
			function doLogin() {
				$.post("/employees/login", $(".form").serialize(),function(data){
					if (data.status == 200) {
						if (data.data == "") {
							location.href = "http://localhost:8081/dashboard";
						} else {
							location.href = data.data;
						}
					} else {
						alert("登录失败，原因是：" + data.msg);
					}
				});
			}
			$(function(){
				$("#login-button").click(function(){
					doLogin();
				});
			});
		</script>
	</body>
</html>