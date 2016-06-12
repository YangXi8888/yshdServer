var userInfo;
$(document).ready(function() {
			hasUserInfo();
			userInfo = sessionLoad("userInfo");
			initPage();
			queryData();
		});

function initPage() {
	$("#dataTable").datagrid({
		title : "纳税人发送记录",
		rownumbers : true,
		fitColumns : true,
		singleSelect : true,
		pagination : true,
		data : [],
		columns : [[{
					field : 'ck',
					checkbox : true
				}, {
					field : 'ZB_UUID',
					align : "center",
					title : "UUID",
					hidden : true,
					width : 0
				}, {
					field : 'NSR_MC',
					align : "center",
					title : "发送人姓名",
					width : '45%'
				}, {
					field : 'FSRQ',
					align : "center",
					title : "发送日期",
					width : '25%'
				}, {
					field : 'xxx',
					align : "center",
					title : "操作",
					width : '30%',
					formatter : function(value, row, index) {
						var btnPanel = "";
						btnPanel = "<a href='#' class='editcls'  style='text-decoration: none' onclick=downLoadFile('"
								+ row.ZB_UUID + "')  href='#'>下载</a>";
						return btnPanel;
					}
				}]],
		singleSelect : false,
		selectOnCheck : true,
		checkOnSelect : true
	});
	$("#dataTable").datagrid("getPager").pagination({
				showPageList : false,
				showRefresh : false,
				pageSize : 30,
				displayMsg : ''
			});
	var sysDate = sessionLoad("sysDate");
	$("#rqq").val(sysDate.substr(0, 8) + "01");
	$("#rqz").val(sysDate.substr(0, 8)
			+ getLastDay(sysDate.split("-")[0], sysDate.split("-")[1]));
}

function queryData() {
	$.messager.progress({
				title : commomWaitTitle,
				msg : '正在查询数据...',
				text : ''
			});
	$.ajax({
				url : "/GeneralAction.do?sessionId=" + userInfo.yhwybz,
				async : true,
				dataType : "json",
				data : {
					jsonData : $.toJSON({
								blhName : "Yhd001BLH",
								handleCode : "queryData",
								yhwybz : userInfo.yhwybz,
								data : {
									rqq : $("#rqq").val(),
									rqz : $("#rqz").val()
								}
							})
				},
				type : 'post',
				timeout : sys_timeout,
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messager.progress('close');
					$.messager.alert(commomMessageTitle, textStatus, 'error');
				},
				success : function(responseText, textStatus, XMLHttpRequest) {
					$.messager.progress('close');
					if (checkResponse(responseText)) {
						var dataList = responseText.data.dataList;
						$("#dataTable").datagrid("loadData",
								dataList.slice(0, 30));
						// 设置分页的相关属性
						var pager = $("#dataTable").datagrid("getPager");
						pager.pagination({
									total : dataList.length,
									onSelectPage : function(pageNo, pageSize) {
										var start = (pageNo - 1) * pageSize;
										var end = start + pageSize;
										$("#dataTable").datagrid("loadData",
												dataList.slice(start, end));
										pager.pagination('refresh', {
													total : dataList.length,
													pageNumber : pageNo
												});
									}
								});
					} else {
						// 判断是否超时
						if (!isTimeout(responseText)) {
							$.messager.alert(commomMessageTitle,
									responseText.msg, 'error');
						}
					}
				}
			});
}

function downLoadFile(zbUuid) {
	$.messager.progress({
				title : commomWaitTitle,
				msg : '正在下载文件.....',
				text : ''
			});
	$.fileDownload("/GeneralAction.do?sessionId=" + userInfo.yhwybz, {
				httpMethod : 'POST',
				data : {
					jsonData : $.toJSON({
								blhName : "Yhd001BLH",
								handleCode : "downLoadFile",
								yhwybz : userInfo.yhwybz,
								downLoadFile : "1",
								data : {
									zbUuid : zbUuid
								}
							})
				},
				successCallback : function(url) {
					$.messager.progress('close');
				},
				failCallback : function(responseHtml, url) {
					$.messager.progress('close');
				}
			});
}

function downLoadAllFile() {
	var selRows = $('#dataTable').datagrid('getChecked');
	if (selRows.length <= 0) {
		$.messager.alert(commomMessageTitle, "请至少选择一行数据进行打包下载", 'error');
		return;
	}
	var zbUuids = "";
	for (var i = 0; i < selRows.length; i++) {
		zbUuids += selRows[i].ZB_UUID + ",";
	}
	zbUuids = zbUuids.substring(0, zbUuids.lastIndexOf(","));
	$.messager.progress({
				title : commomWaitTitle,
				msg : '正在下载文件.....',
				text : ''
			});
	$.fileDownload("/GeneralAction.do?sessionId=" + userInfo.yhwybz, {
				httpMethod : 'POST',
				data : {
					jsonData : $.toJSON({
								blhName : "Yhd001BLH",
								handleCode : "downLoadAllFile",
								yhwybz : userInfo.yhwybz,
								downLoadFile : "1",
								data : {
									zbUuids : zbUuids
								}
							})
				},
				successCallback : function(url) {
					$.messager.progress('close');
				},
				failCallback : function(responseHtml, url) {
					$.messager.progress('close');
				}
			});

}