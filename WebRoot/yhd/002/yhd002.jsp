<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="/public/jquery/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="/public/jquery/themes/icon.css"></link>
<link rel="stylesheet" type="text/css" href="/public/css/main.css"></link>

<script type="text/javascript" src="/public/jquery/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="/public/jquery/jquery.json.min.js"></script>
<script type="text/javascript" src="/public/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/public/jquery/locale/easyui-lang-zh_CN.js"></script>
<script src="/public/jquery/plugins/upload/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/public/jquery/plugins/download/jquery.fileDownload.js"></script>
<script type="text/javascript" src="/public/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/public/js/common.js"></script>
<title>泰州地税局税银互动平台</title>
</head>
<body class="easyui-layout">
	<div style="margin:0 auto; width:90%; height:90%; ">

		<div class="easyui-panel" style="text-align: right; background-color: #E7F0FF;">
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'" style="text-decoration: none;" onclick="queryData();">查询</a> <a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-zip'" style="text-decoration: none;" id="upBtn">上传</a> <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-close'" style="text-decoration: none;" onclick="window.close()">关闭</a>
		</div>
		<div style="text-align: center; height:10%"><br>
			上传日期：<input class="Wdate" type="text" id="rqq" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'rqz\')}'})" readonly="readonly"> 至<input class="Wdate" type="text" id="rqz" onClick="WdatePicker({minDate:'#F{$dp.$D(\'rqq\')}'})">
		</div>
		<div class="easyui-panel" title="上传记录" style="height:45%" id="dataTable"></div>
		<div class="easyui-panel" title="文件上传" style="height:50%">
			<table align="center" style="width:300px;height:260px;border:1pxsolid #95B8E7;">
				<tr>
					<td style="text-align: center;"><input type="file" name="uploadFiles[0].file"></td>
				</tr>
				<tr>
					<td style="text-align: center;"><input type="file" name="uploadFiles[1].file"></td>
				</tr>
				<tr>
					<td style="text-align: center;"><input type="file" name="uploadFiles[2].file"></td>
				</tr>
				<tr>
					<td style="text-align: center;"><input type="file" name="uploadFiles[3].file"></td>
				</tr>
				<tr>
					<td style="text-align: center;"><input type="file" name="uploadFiles[4].file"></td>
				</tr>
				<tr>
					<td style="text-align: center;"><input type="file" name="uploadFiles[5].file"></td>
				</tr>
			</table>
		</div>

		<input type="hidden" value="<%=request.getParameter("sessionId")%>" id="sessionId">
	</div>
</body>
<script type="text/javascript" src="/yhd/002/yhd002.js"></script>
</html>
