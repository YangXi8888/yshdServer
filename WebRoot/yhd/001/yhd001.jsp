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
<script type="text/javascript"
	src="/public/jquery/plugins/download/jquery.fileDownload.js"></script>
<script type="text/javascript"
	src="/public/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/public/js/aes.js"></script>
<script type="text/javascript" src="/public/js/common.js"></script>
<link rel="stylesheet" type="text/css"
	href="/public/jquery/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css"
	href="/public/jquery/themes/icon.css"></link>
<link rel="stylesheet" type="text/css" href="/public/css/main.css"></link>
<title>泰州地税局税银互动平台</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:100px">
		<div class="easyui-panel"
			style="width:100%;text-align:right;background-color: #E7F0FF;height:30px">
			<a href="#" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-search'"
				style="text-decoration: none;" onclick="queryData();">查询</a> <a
				href="#" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-download'"
				style="text-decoration: none;" onclick="downLoadAllFile();">下载全部</a>
			<a href="#" class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-close'"
				style="text-decoration: none;" onclick="window.close()">关闭</a>
		</div>
		<br />
		<div style="text-align:center;width:90%">
			<!--Mydate97控件 -->
			发送日期：<input class="Wdate" type="text" id="rqq"
				onClick="WdatePicker({maxDate:'#F{$dp.$D(\'rqz\')}'})"
				readonly="readonly"> 至<input class="Wdate" type="text"
				id="rqz" onClick="WdatePicker({minDate:'#F{$dp.$D(\'rqq\')}'})">
			&nbsp;&nbsp;&nbsp;&nbsp; 
			企业名称:<input class="easyui-textbox" style="height:32px;width:20%"  id="qyMc"> <br />
		</div>
	</div>
	<div data-options="region:'center',title:'查询内容'" style="width:98%">
		<div class="easyui-panel"
			style="width:98%;text-align:center;background-color: #E7F0FF;height:90%"
			id="dataTable"></div>
	</div>
	<input type="hidden" value="<%=request.getParameter("sessionId")%>"
		id="sessionId">
</body>
<script type="text/javascript" src="/yhd/001/yhd001.js"></script>
</html>