var userInfo;
$(document).ready(function() {
			hasUserInfo();
			userInfo = sessionLoad("userInfo");
			initPage();
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
					field : 'xm',
					align : "center",
					title : "上传人姓名",
					width : 100
				}, {
					field : 'yhMc',
					align : "center",
					title : "银行名称",
					width : 100
				}, {
					field : 'wjm',
					align : "center",
					title : "文件名",
					width : 100
				}, {
					field : 'wjdx',
					align : "center",
					title : "文件大小",
					width : 100
				}, {
					field : 'scRQ',
					align : "center",
					title : "上传日期",
					width : 100
				}, {
					field : 'cz',
					align : "center",
					title : "操作",
					width : 100,
					formatter : function(value, row, index) {
						var btn = null;
						if ("" != row.swglm) {
							btn = "<a class='editcls' onclick='testFun("
									+ row.swglm
									+ ")' href='javascript:void(0);'>查看</a>";
							btn += "&nbsp&nbsp;<a class='editcls' onclick='testFun("
									+ row.swglm
									+ ")' href='javascript:void(0);'>编辑</a>"
						}
						return btn;
					}
				}]]
	});
	$("#dataTable").datagrid("getPager").pagination({
				showPageList : false,
				showRefresh : false,
				pageSize : 50,
				displayMsg : ''
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
								blhName : "Yhgl001BLH",
								handleCode : "submitForm",
								yhwybz : userInfo.yhwybz,
								data : {
									Y_LOGPASS : $("#password").val(),
									N_LOGPASS : $("#password_new").val()
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
						$.messager.alert(commomMessageTitle, responseText.msg,
								'info');
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
