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
<script src="/public/jquery/plugins/upload/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/public/js/common.js"></script>
<link rel="stylesheet" type="text/css"
	href="/public/jquery/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css"
	href="/public/jquery/themes/icon.css"></link>
<link rel="stylesheet" type="text/css" href="/public/css/main.css"></link>
<title></title>
</head>
<body>
	<input type="file" name="uploadFiles[0].file">
	<br>
	<input type="file" name="uploadFiles[1].file">
	<br>
	<input type="file" name="uploadFiles[2].file">
	<br>
	<input type="file" name="uploadFiles[3].file">
	<br>
	<input type="file" name="uploadFiles[4].file">
	<br>
	<p>
		<input type="button" value="上传" id="upBtn">
	</p>
	<input type="hidden" value="<%=request.getParameter("sessionId")%>"
		id="sessionId">

</body>
<script src="/yhd/002/yhd002.js"></script>
</html>