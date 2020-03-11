<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<form method="post" onsubmit="return false;" class="form">
					<input type="text" value="itdragon" name="username" placeholder="Account"/>
					<input type="password" value="123456789" name="password" placeholder="Password"/>
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
			var redirectUrl = "${redirect}"; // 浏览器中回显的URL
			function doLogin() {
				$.post("/user/login", $(".form").serialize(),function(data){
					if (data.status == 200) {
						if (redirectUrl == "") {
							location.href = "http://localhost:8082";
						} else {
							location.href = redirectUrl;
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