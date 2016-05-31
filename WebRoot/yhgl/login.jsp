<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
<style>
* {
	padding: 0;
	margin: 0;
	font-family: "微软雅黑";
	font-size: 16px;
}

.all {
	min-width: 1280px;
}

.up {
	width: 100%;
	min-width: 1280px;
	height: 720px;
	background-image: url(/public/images/bk.png);
	background-color: #3a81ff;
}

.top {
	width: 100%;
	height: 120px;
}

.mid {
	margin: 0 auto;
	position: relative;
	width: 1265px;
	height: 600px;
	padding-left: 15px;
}

.login {
	position: absolute;
	top: 180px;
	right: -17%;
	width: 603px;
	height: 500px;
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
</style>
<title>泰州地税局税银互动平台</title>
</head>
<body>
	<div class="all">
		<div class="up">
			<div class="mid">
				<img src="/public/images/logo.png"
					style="margin-top:40px; margin-left:0px; width:620px;" /> <img
					src="/public/images/aaa.png" style=" width:800px; margin-top:42px;" />
				<div class="login">
					<p class="tit">用户登录</p>
					<input type="text" id="sjHm" placeholder="请输入手机号码"><br />
					<input type="password" id="passWord" placeholder="请输入密码"><br />
					<input type="text" id="yzm" size="6" style="width: 100px"
						placeholder="请输入验证码"> &nbsp;&nbsp;&nbsp;&nbsp;<img alt=""
						src="/yhgl/yzm.jsp" style="height:30px"><br />
					<div class="Log" onclick="dlXt()">登陆</div>
				</div>
			</div>
		</div>
		<div class="bot">
			<p
				style="width:100%; text-align:center;color:#999; height:160px; line-height:160px;">Copyright
				© 泰州地税 版权所有</p>
		</div>
	</div>
	<input type="hidden" id="sessionId">
</body>
<script type="text/javascript" src="/yhgl/login.js"></script>
</html>

