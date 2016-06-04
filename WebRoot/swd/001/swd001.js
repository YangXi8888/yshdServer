var userInfo;
$(document).ready(function() {
			hasUserInfo();
			userInfo = sessionLoad("userInfo");
			initPage();
			queryData();
		});

function initPage() {
	$("#dataTable").datagrid({
		title : "上传记录表",
		rownumbers : true,
		fitColumns : true,
		singleSelect : true,
		pagination : true,
		data : [],
		columns : [[{
					field : 'SCJL_ID',
					align : "center",
					title : "上传记录ID",
					hidden : true,
					width : 0
				}, {
					field : 'XM',
					align : "center",
					title : "上传人姓名",
					width : '10%'
				}, {
					field : 'QYYH_MC',
					align : "center",
					title : "银行名称",
					width : '20%'
				}, {
					field : 'WJML',
					align : "center",
					title : "文件目录",
					width : '10%'
				}, {
					field : 'WJM',
					align : "center",
					title : "文件名",
					width : '10%'
				}, {
					field : 'WJDX',
					align : "center",
					title : "文件大小",
					width : '10%'
				}, {
					field : 'SCRQ',
					align : "center",
					title : "上传日期",
					width : '10%'
				}, {
					field : 'xxx',
					align : "center",
					title : "操作",
					width : '30%',
					formatter : function(value, row, index) {
						var btnPanel = "";
						btnPanel = "<a href='#' class='editcls'  style='text-decoration: none' onclick=downLoadFile('"
								+ row.SCJL_ID
								+ "')  href='#'>下载</a>";
						btnPanel += "&nbsp&nbsp;&nbsp&nbsp;&nbsp&nbsp;&nbsp&nbsp;<a class='editcls' onclick=deleteFile('"
								+ row.SCJL_ID
								+ "') href='#'>删除</a>";
						return btnPanel;
					}
				}]]
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

function deleteFile(scjlId) {
	alert(scjlId);
}

function downLoadFile(scjlId) {
	alert(scjlId);
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
								blhName : "Swd001BLH",
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
