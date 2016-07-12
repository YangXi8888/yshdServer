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
					width : '15%'
				}, {
					field : 'QYYH_MC',
					align : "center",
					title : "银行名称",
					width : '20%'
				}, {
					field : 'WJM',
					align : "center",
					title : "文件名",
					width : '15%'
				}, {
					field : 'WJDX',
					align : "center",
					title : "文件大小",
					width : '10%'
				}, {
					field : 'SCRQ',
					align : "center",
					title : "上传日期",
					width : '15%'
				}, {
					field : 'xxx',
					align : "center",
					title : "操作",
					width : '25%',
					formatter : function(value, row, index) {
						var btnPanel = "";
						btnPanel = "<a href='#' class='editcls'  style='text-decoration: none' onclick=downLoadFile('"
								+ row.SCJL_ID + "')  href='#'>下载</a>";
						btnPanel += "&nbsp&nbsp;&nbsp&nbsp;&nbsp&nbsp;&nbsp&nbsp;<a class='editcls' onclick=deleteFile('"
								+ row.SCJL_ID + "') href='#'>删除</a>";
						return btnPanel;
					}
				}]]
	});

	$("#dataTable").datagrid("getPager").pagination({
				showPageList : false,
				showRefresh : false,
				pageSize : 15,
				displayMsg : ''
			});
	var sysDate = sessionLoad("sysDate");
	$("#rqq").val(sysDate.substr(0, 8) + "01");
	$("#rqz").val(sysDate.substr(0, 8)
			+ getLastDay(sysDate.split("-")[0], sysDate.split("-")[1]));

	$.ajax({
				url : "/GeneralAction.do?sessionId=" + userInfo.yhwybz,
				async : true,
				dataType : "json",
				data : {
					jsonData : $.toJSON({
								blhName : "CommBLH",
								handleCode : "queryQyYh",
								yhwybz : userInfo.yhwybz
							})
				},
				type : 'post',
				timeout : sys_timeout,
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					if (XMLHttpRequest.responseText.indexOf("script") != -1) {
						document.write(XMLHttpRequest.responseText);
					} else {
						$.messager.alert(commomMessageTitle, textStatus,
								'error');
					}
				},
				success : function(responseText, textStatus, XMLHttpRequest) {
					if (checkResponse(responseText)) {
						for (var i = 0; i < responseText.data.list.length; i++) {
							$("#qyYhDm").append("<option value='"
									+ responseText.data.list[i].QYYH_DM + "'>"
									+ responseText.data.list[i].QYYH_MC
									+ "</option>");
						}
					} else {
						// 判断是否超时
						if (!isTimeout(responseText)) {
							$.messager.alert(commomMessageTitle,
									responseText.msg, 'error', function() {
										queryData();
									});
						}
					}
				}
			});
}

function deleteFile(scjlId) {
	$.messager.confirm(commomMessageTitle, '确定删除该文件?', function(r) {
		if (r == true) {
			$.messager.progress({
						title : commomWaitTitle,
						msg : '正在删除文件.....',
						text : ''
					});

			$.ajax({
						url : "/GeneralAction.do?sessionId=" + userInfo.yhwybz,
						async : true,
						dataType : "json",
						data : {
							jsonData : $.toJSON({
										blhName : "Swd001BLH",
										handleCode : "deleteFile",
										yhwybz : userInfo.yhwybz,
										data : {
											scjlId : scjlId
										}
									})
						},
						type : 'post',
						timeout : sys_timeout,
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
							$.messager.progress('close');
							if (XMLHttpRequest.responseText.indexOf("script") != -1) {
								document.write(XMLHttpRequest.responseText);
							} else {
								$.messager.alert(commomMessageTitle,
										textStatus, 'error');
							}
						},
						success : function(responseText, textStatus,
								XMLHttpRequest) {
							$.messager.progress('close');
							if (checkResponse(responseText)) {
								$.messager.alert(commomMessageTitle,
										responseText.msg, 'info', function() {
											queryData();
										});
							} else {
								// 判断是否超时
								if (!isTimeout(responseText)) {
									$.messager.alert(commomMessageTitle,
											responseText.msg, 'error',
											function() {
												queryData();
											});
								}
							}
						}
					});
		}
	});

}

function downLoadFile(scjlId) {
	$.messager.progress({
				title : commomWaitTitle,
				msg : '正在下载文件.....',
				text : ''
			});
	$.fileDownload("/GeneralAction.do?sessionId=" + userInfo.yhwybz, {
				httpMethod : 'POST',
				data : {
					jsonData : $.toJSON({
								blhName : "Swd001BLH",
								handleCode : "downLoadFile",
								yhwybz : userInfo.yhwybz,
								downLoadFile : "1",
								data : {
									scjlId : scjlId
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
	$.messager.progress({
				title : commomWaitTitle,
				msg : '正在下载文件.....',
				text : ''
			});
	$.fileDownload("/GeneralAction.do?sessionId=" + userInfo.yhwybz, {
				httpMethod : 'POST',
				data : {
					jsonData : $.toJSON({
								blhName : "Swd001BLH",
								handleCode : "downLoadAllFile",
								yhwybz : userInfo.yhwybz,
								downLoadFile : "1",
								data : {
									rqq : $("#rqq").val(),
									rqz : $("#rqz").val()
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
									rqz : $("#rqz").val(),
									qyYhDm : $("#qyYhDm").val()
								}
							})
				},
				type : 'post',
				timeout : sys_timeout,
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messager.progress('close');
					if (XMLHttpRequest.responseText.indexOf("script") != -1) {
						document.write(XMLHttpRequest.responseText);
					} else {
						$.messager.alert(commomMessageTitle, textStatus,
								'error');
					}
				},
				success : function(responseText, textStatus, XMLHttpRequest) {
					$.messager.progress('close');
					if (checkResponse(responseText)) {
						var dataList = responseText.data.dataList;
						$("#dataTable").datagrid("loadData",
								dataList.slice(0, 15));
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
