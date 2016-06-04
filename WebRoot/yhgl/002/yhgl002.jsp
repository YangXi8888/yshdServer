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
<script type="text/javascript" src="/public/js/common.js"></script>
<link rel="stylesheet" type="text/css"
	href="/public/jquery/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css"
	href="/public/jquery/themes/icon.css"></link>
<link rel="stylesheet" type="text/css" href="/public/css/main.css"></link>
<title>泰州地税局税银互动平台</title>
</head>
<body>
	<div class="easyui-panel" style="width:98%">
		<div style="padding:10px 60px 20px 60px">
			<form id="ff" method="post">
				<table cellpadding="5">
					<tr>
						<td>新密码:</td>
						<td><input class="easyui-textbox" type="password"
							maxlength="10" id="password_new" placeholder="请输入新密码"
							data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>确认新密码:</td>
						<td><input class="easyui-textbox" type="password"
							maxlength="10" id="password_com" placeholder="请确认新密码"
							data-options="required:true"></input></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
					</tr>
				</table>
			</form>
			<div style="text-align:center;padding:5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitForm()">&nbsp;&nbsp;保&nbsp;&nbsp;存&nbsp;&nbsp;</a>
			</div>
		</div>
	</div>
		<input type="hidden" value="<%=request.getParameter("sessionId")%>"
			id="sessionId">
</body>
<script type="text/javascript" src="/yhgl/002/yhgl002.js"></script>
</html>
