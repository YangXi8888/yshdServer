<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="/public/jquery/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="/public/jquery/jquery.json.min.js"></script>
<script type="text/javascript" src="/public/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="/public/jquery/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" type="text/css"
	href="/public/jquery/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css"
	href="/public/jquery/themes/icon.css"></link>
<script type="text/javascript" src="/public/js/common.js"></script>
<script type="text/javascript" src="/public/js/validator.js"></script>
<title>泰州地方税务局银税互动平台</title>
<style>
* {
	padding: 0;
	margin: 0;
	font-family: "微软雅黑";
	font-size: 16px;
}

.up {
	width: 100%;
	height: 650px;
	background-color: #3a81ff;
	background-image: url(/public/images/bka.jpg);
}

.top {
	width: 100%;
	height: 120px;
}

.mid {
	margin: 0 auto;
	position: relative;
	width: 1260px;
	height: 650px;
	overflow: hidden;
}

.login {
	position: absolute;
	top: 200px;
	right: 7%;
	height: 400px;
	padding: 30px;
	background-repeat: no-repeat;
	background-image: url(/public/images/ty.png);
}

.login h1 {
	
}

.login input {
	height: 40px;
	width: 300px;
	padding-left: 5px;
	padding-right: 5px;
	margin-bottom: 20px;
}

.Log {
	width: 310px;
	height: 50px;
	background-color: #3a81ff;
	border-radius: 4px;
	color: #fff;
	margin-top: -5px;
	line-height: 50px;
	cursor: pointer;
	text-align: center;
}

.tit {
	font-size: 20px;
	margin-top: 2px;
	margin-bottom: 20px;
	color: #3a81ff;
}

.Log:hover {
	background-color: #175edb;
}

.link {
	margin-top: 10px;
}

.bot {
	height: 70px;
	width: 100%;
}

a {
	color: #06C;
	text-decoration: none
}

.sy {
	position: absolute;
	top: 60px;
	color: #fff;
	right: 60px;
}

.sy a {
	color: #fff;
}
</style>
</head>

<body>
	<div class="up">
		<div class="mid">
			<img src="/public/images/logo.png"
				style="margin-top:20px; margin-left:30px; width:570px;" />
			<div class="sy">
				<a href="#" class="Conte">联系我们</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
					href="#" class="Conte">帮助</a>
			</div>
			<img src="/public/images/ggg.png" style="width:800px;" />
			<div class="login">
				<p class="tit">用户登录</p>
				<input type="text" id="sjHm" placeholder="请输入手机号码"
					onkeydown="enterPress(event,$('#passWord')[0])"><br /> <input
					type="password" id="passWord" placeholder="请输入密码"
					onkeydown="enterPress(event,$('#yzm')[0])"><br /> <input
					type="text" id="yzm" size="6" style="width: 100px"
					placeholder="请输入验证码" onkeydown="yzmOnKeyDown(event)">
				&nbsp;&nbsp;&nbsp;&nbsp;<img alt="" src="/yhgl/yzm.jsp"
					style="height:30px"><br />
				<div class="Log" onclick="dlXt()">登陆</div>
			</div>
		</div>
	</div>
	<div class="bot">
		<p
			style="width:100%; text-align:center;color:#999; height:70px; line-height:70px;">Copyright
			© 2016 - 2016 Taizhou. All Rights Reserved. 泰州地税 版权所有</p>
	</div>
	<input type="hidden" id="sessionId">
	<script type="text/javascript" src="/yhgl/login.js"></script>
</body>
</html>