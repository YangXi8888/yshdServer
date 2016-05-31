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
<link rel="stylesheet" type="text/css"
	href="/public/jquery/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css"
	href="/public/jquery/themes/icon.css"></link>
<script type="text/javascript" src="/public/js/common.js"></script>
<script type="text/javascript" src="/public/js/validator.js"></script>
<title>泰州地税局税银互动平台</title>
</head>
<body>
	<div>
		<ul>
			<li>手机号码：<input type="text" id="sjHm" placeholder="请输入手机号码">
			</li>
			<li>密码：<input type="password" id="passWord" placeholder="请输入密码">
			</li>
			<li>验证码：<input type="text" id="yzm" size="6" placeholder="请输入验证码">
				<img alt="" src="/yhgl/yzm.jsp">
			</li>
			<li><input type="button" id="dlBtn" value="登录" onclick="dlXt()">
			</li>
		</ul>
	</div>
	<input type="hidden" id="sessionId">
</body>
<script type="text/javascript" src="/yhgl/login.js"></script>
</html>
