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
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height:85px;padding:0px">
		<div class="top_tit">
			<img src="/public/images/tit_png.png" style="float: left;" />
			<div class="nowUser">
				<div class="bz_2">
					<br />
					<br /> <a href='#' style='cursor: pointer' onclick="xgMm()">修改密码</a>
					&nbsp;&nbsp;&nbsp;&nbsp;<a style="cursor: pointer" onclick="tcXt()"
						href='#'>退&nbsp;出</a>
				</div>
			</div>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'功能树'"
		style="width:200px;padding:10px;">
		<div id="div_gns" style="overflow-y: auto;">
			<ul class="easyui-tree" id="mainTree"></ul>
		</div>
	</div>
	<div data-options="region:'center',iconCls:'icon-ok'">
		<div id="myAdd" class="easyui-tabs"
			data-options="fit:true,tools:'#tab-tools'">
			<div title="信息提示" data-options="closable:false"
				style="overflow: scroll;">
				<!--首页内容-->




				<!--首页内容-->
			</div>
		</div>
	</div>
	<div data-options="region:'south',border:false"
		style="height:50px;padding:10px;">版权所有©泰州地方税务局</div>
	<input type="hidden" value="<%=request.getParameter("sessionId")%>"
		id="sessionId">
</body>
<script type="text/javascript" src="/main/main.js"></script>
</html>
