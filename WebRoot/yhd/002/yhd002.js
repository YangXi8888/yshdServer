var userInfo;
$(document).ready(function() {
			hasUserInfo();
			userInfo = sessionLoad("userInfo");
			initPage();

		});

function initPage() {
	$("#dataTable").datagrid({
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
					width : '20%'
				}, {
					field : 'WJM',
					align : "center",
					title : "文件名",
					width : '25%'
				}, {
					field : 'WJDX',
					align : "center",
					title : "文件大小",
					width : '15%'
				}, {
					field : 'SCRQ',
					align : "center",
					title : "上传日期",
					width : '15%'
				}, {
					field : 'xxx',
					align : "center",
					title : "操作",
					width : '15%',
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
				pageSize : 20,
				displayMsg : ''
			});
	var sysDate = sessionLoad("sysDate");
	$("#rqq").val(sysDate.substr(0, 8) + "01");
	$("#rqz").val(sysDate.substr(0, 8)
			+ getLastDay(sysDate.split("-")[0], sysDate.split("-")[1]));

}

function upLoadFile() {
	if (baseCheck()) {
		$.fn.ajaxfileupload({
					action : "/GeneralAction.do?sessionId=" + userInfo.yhwybz,
					formName : "GeneralForm",
					fileDom : "fileTable",
					params : {
						jsonData : ""
					},
					onComplete : function(responseText) {
						$.messager.progress('close');
						if (checkResponse(responseText)) {
							$.messager.alert(commomMessageTitle,
									responseText.msg, 'info');
							for (var i = 0; i < $('input[type="file"]').length; i++) {
								$('input[type="file"]')[i].value = "";
							}
						} else {
							// 判断是否超时
							if (!isTimeout(responseText)) {
								$.messager.alert(commomMessageTitle,
										responseText.msg, 'error');
							}
						}
					},
					checkData : function() {
						return true;
					},
					getFormData : function() {
						return [{
									name : "jsonData",
									value : $.toJSON({
												blhName : "Yhd002BLH",
												handleCode : "upLoadFile",
												yhwybz : userInfo.yhwybz
											})
								}];
					},
					onStart : function(obj) {
						$.messager.progress({
									title : commomWaitTitle,
									msg : '正在上传文件...',
									text : ''
								});
						return true;
					}
				});
	}

}

function baseCheck() {

	var flag = false;
	for (var i = 0; i < $('input[type="file"]').length; i++) {
		if ($('input[type="file"]')[i].value != "") {
			flag = true;
			break;
		}
	}
	if (flag == false) {
		$.messager.alert(commomMessageTitle, '请选择文件', 'warning');
		return false;
	}

	var fileType = "PNG, JPG, JPEG, PDF, DOC, TXT, RAR, ZIP, DOCX, XLS, XLSX, PPT, PPTX";
	for (var i = 0; i < $('input[type="file"]').length; i++) {
		if ($('input[type="file"]')[i].value != "") {
			flag = checkFileType($('input[type="file"]')[i].value, fileType);
			if (flag == false) {
				$.messager.alert(commomMessageTitle, '文件格式不正确，允许上传的格式有：'
								+ fileType, 'warning');
				return flag;
			}
		}
	}
	return true;
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
								blhName : "Yhd002BLH",
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
								dataList.slice(0, 20));
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
								blhName : "Yhd002BLH",
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
function deleteFile(scjlId) {
	$.messager.confirm(commomMessageTitle, '确定删除该文件?', function(r) {
				if (r == true) {
					$.messager.progress({
								title : commomWaitTitle,
								msg : '正在删除文件.....',
								text : ''
							});

					$.ajax({
								url : "/GeneralAction.do?sessionId="
										+ userInfo.yhwybz,
								async : true,
								dataType : "json",
								data : {
									jsonData : $.toJSON({
												blhName : "Yhd002BLH",
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
									$.messager.alert(commomMessageTitle,
											textStatus, 'error');
								},
								success : function(responseText, textStatus,
										XMLHttpRequest) {
									$.messager.progress('close');
									if (checkResponse(responseText)) {
										$.messager.alert(commomMessageTitle,
												responseText.msg, 'info',
												function() {
													queryData();
												});
									} else {
										// 判断是否超时
										if (!isTimeout(responseText)) {
											$.messager.alert(
													commomMessageTitle,
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
