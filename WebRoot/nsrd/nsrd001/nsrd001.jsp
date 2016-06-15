<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>泰州地税局税银互动平台</title>
<style type="text/css">
body {
	font-family: verdana, helvetica, arial, sans-serif;
	padding: 20px;
	font-size: 12px;
	margin: 0;
}
</style>
<link rel="stylesheet" type="text/css"
	href="/public/jquery/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css"
	href="/public/jquery/themes/icon.css"></link>
<link rel="stylesheet" type="text/css" href="/public/css/main.css"></link>
<script type="text/javascript" src="/public/jquery/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="/public/jquery/jquery.json.min.js"></script>
<script type="text/javascript" src="/public/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/public/js/common.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height:85px;padding:0px">
		<div class="top_tit">
			<img src="/public/images/tit_png.png" style="float: left;" />
		</div>
	</div>
	<div data-options="region:'center',title:'数据推送'">
		<table align="center" height="100%">
			<tr>
				<td valign="middle">
					<div class="easyui-panel" title=" " style="width:600px">
						<div style="padding:10px 60px 20px 60px">
							<table cellpadding="5">
								<tr>
									<td>税务管理码:</td>
									<td><input type="text" id="swglm" readonly="readonly"
										style="border-width:0px;" /></td>
								</tr>
								<tr>
									<td>纳税人名称:</td>
									<td><input type="text" id="nsrMc" readonly="readonly"
										style="border-width:0px;" /></td>
								</tr>
								<tr>
									<td>推 送 银 行:</td>
									<td><select id="qyyhDm">
											<option value="-1">==&nbsp;请选择&nbsp;==</option>
									</select></td>
								</tr>
							</table>
							<div style="text-align:center;padding:5px">
								<a href="javascript:submitData();" class="easyui-linkbutton"
									onclick="">&nbsp;&nbsp;提&nbsp;交&nbsp;&nbsp;</a>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'south',border:false"
		style="height:50px;padding:10px;">版权所有©泰州地方税务局</div>
	<input type="hidden" id="nsrSbm" />
</body>
<script type="text/javascript" src="/nsrd/nsrd001/nsrd001.js"></script>
</html>