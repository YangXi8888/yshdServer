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
<script type="text/javascript" src="/public/jquery/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/public/js/common.js"></script>
<link rel="stylesheet" type="text/css" href="/public/jquery/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="/public/jquery/themes/icon.css"></link>
<link rel="stylesheet" type="text/css" href="/public/css/main.css"></link>
<title>泰州地税局税银互动平台</title>
</head>
<body>

	<div class="easyui-panel" style="text-align: right; background-color: #E7F0FF;">
		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" style="text-decoration: none;" onclick="queryData();">查询</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" style="text-decoration: none;" onclick="$('#add_pan').window('open');">添加</a>
	</div>
	<div style="text-align: center;">
		<br> 上传日期：<input class="Wdate" type="text" id="rqq" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'rqz\')}'})" readonly="readonly"> 至<input class="Wdate" type="text" id="rqz" onClick="WdatePicker({minDate:'#F{$dp.$D(\'rqq\')}'})"><br> <br>
	</div>
	<div class="easyui-panel" title="用户列表" style="height:90%;" id="dataTable"></div>
	<div class="easyui-window" title="添加用户" style="width:350px;height:350px;padding:10px;" id="add_pan">
		<form id="ff" method="post">
			<table style="width:100%;height:100%;text-align:center;">
				<tr>
					<td style="padding-top:10px;">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</td>
					<td style="padding-top:10px;"><input data-options="required:true" style="width:200px;" class="easyui-textbox" type="text" maxlength="10" id="sjHm" placeholder="请输入账号" /></td>
				</tr>
				<tr>
					<td style="padding-top:10px;">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</td>
					<td style="padding-top:10px;"><input data-options="required:true" style="width:200px;" class="easyui-textbox" type="password" maxlength="10" id="pwd" placeholder="请输入密码" /></td>
				</tr>
				<tr>
					<td style="padding-top:10px;">确认密码</td>
					<td style="padding-top:10px;"><input data-options="required:true" style="width:200px;" class="easyui-textbox" type="password" maxlength="10" id="pwd2" placeholder="请再次输入密码" /></td>
				</tr>
				<tr>
					<td style="padding-top:10px;">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</td>
					<td style="padding-top:10px;"><input data-options="required:true" style="width:200px;" class="easyui-textbox" type="text" maxlength="10" id="xm" placeholder="请输入姓名" /></td>
				</tr>
				<!--
					<tr>
						<td style="padding-top:10px;">证件类型</td>
						<td style="padding-top:10px;"><select class="easyui-combobox" id="zjlxDm" disabled="disabled" style="width:200px;">
								<option value="06">身份证</option>
						</select></td>
					</tr>
					 <tr>
						<td style="padding-top:10px;">证件号码</td>
						<td style="padding-top:10px;"><input data-options="required:true" style="width:200px;" class="easyui-textbox" type="text" maxlength="10" id="zjHm" placeholder="请输入证件号码" /></td>
					</tr>
					 -->
				<tr>
					<td style="padding-top:10px;">用户类型</td>
					<td style="padding-top:10px;">
						<div id="yhlx">
							<input type="radio" name="yhlxDm" value="01" onclick="showQyyh(this)">银行系统 &nbsp;&nbsp; <input type="radio" name="yhlxDm" value="02" onclick="hideQyyh(this)"> 地税系统
						</div>
					</td>
				</tr>
				<tr id="qyyh_row">
					<td style="padding-top:10px;">签约银行</td>
					<td style="padding-top:10px;"><select id="qyyhDm" style="width:200px;">
							<option value="-1">请选择</option>
					</select></td>
				</tr>
				<tr>
					<td colspan="2" style="padding-top:20px;text-align: center;"><a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">&nbsp;&nbsp;保&nbsp;&nbsp;存&nbsp;&nbsp;</a></td>
				</tr>
			</table>
		</form>
	</div>
	<input type="hidden" value="<%=request.getParameter("sessionId")%>" id="sessionId">
</body>
<script type="text/javascript" src="/yhgl/003/yhgl003.js"></script>
</html>
