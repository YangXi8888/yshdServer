var userInfo;
$(document).ready(function() {
			hasUserInfo();
			userInfo = sessionLoad("userInfo");
			initPage();
		});
function initPage() {
	$('.easyui-window').window({
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
	$("#qyyh_row").hide();
	$('#add_pan').window('close');
	$("#dataTable").datagrid({
		rownumbers : true,
		fitColumns : true,
		singleSelect : true,
		pagination : true,
		data : [],
		columns : [[{
					field : 'UUID',
					align : "center",
					title : "记录ID",
					hidden : true,
					width : 0
				}, {
					field : 'XM',
					align : "center",
					title : "姓名",
					width : '15%'
				}, {
					field : 'SJHM',
					align : "center",
					title : "账号",
					width : '15%'
				}, {
					field : 'YHLX_MC',
					align : "center",
					title : "用户类型",
					width : '15%'
				}, {
					field : 'QYYH_MC',
					align : "center",
					title : "签约银行（银行用户）",
					width : '15%'
				}, {
					field : 'LR_SJ',
					align : "center",
					title : "注册时间",
					width : '15%'
				}, {
					field : 'xxx',
					align : "center",
					title : "操作",
					width : '15%',
					formatter : function(value, row, index) {
						var btnPanel = "";
						btnPanel = "<a href='#' class='editcls'  style='text-decoration: none' onclick=zxUser('"
								+ row.UUID + "')  href='#'>注销</a>";

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
	$.ajax({
				url : "/GeneralAction.do?sessionId=" + userInfo.yhwybz,
				async : true,
				dataType : "json",
				data : {
					jsonData : $.toJSON({
								blhName : "Yhgl003BLH",
								handleCode : "initPage",
								yhwybz : userInfo.yhwybz
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
						var arr = responseText.data.qyYhList;
						for (var i = 0; i < arr.length; i++) {
							$("#qyyhDm").append("<option value=" + arr[i].id
									+ ">" + arr[i].text + "</option>");

						}
					} else {
						if (!isTimeout(responseText)) {
							$.messager.alert(commomMessageTitle,
									responseText.msg, 'error');
						}
					}
				}
			});

}
function submitForm() {
	if (baseCheck()) {
		$.messager.progress({
					title : commomWaitTitle,
					msg : '正在保存数据...',
					text : ''
				});
		$.ajax({
			url : "/GeneralAction.do?sessionId=" + userInfo.yhwybz,
			async : true,
			dataType : "json",
			data : {
				jsonData : $.toJSON({
					blhName : "Yhgl003BLH",
					handleCode : "submitForm",
					yhwybz : userInfo.yhwybz,
					data : {
						sjHm : $("#sjHm").val(),
						pwd : $("#pwd").val(),
						xm : $("#xm").val(),
						// zjlxDm : $("#zjlxDm").val(),
						// zjHm : $("#zjHm").val(),
						yhlxDm : $('#yhlx input[name="yhlxDm"]:checked ').val(),
						qyyhDm : $("#qyyhDm").val()
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
					$('#add_pan').window('close');
					clearSaved();
				} else {
					// 判断是否超时
					if (!isTimeout(responseText)) {
						$.messager.alert(commomMessageTitle, responseText.msg,
								'error');
					}
				}
			}
		});
	}
}

function baseCheck() {

	if ($.trim($("#sjHm").val()) == "") {
		$.messager.alert(commomMessageTitle, '账号不能为空', 'warning', function() {
					$("#sjHm").focus();
				});
		return false;
	}
	if ($.trim($("#pwd").val().length) > 10
			|| $.trim($("#pwd").val().length) < 6) {
		$.messager.alert(commomMessageTitle, '请设置6至10位的新密码', 'warning',
				function() {
					$("#pwd").focus();
				});
		return false;
	}
	if ($.trim($("#pwd2").val()) == "") {
		$.messager.alert(commomMessageTitle, '确认密码不能为空', 'warning', function() {
					$("#pwd2").focus();
				});
		return false;
	}
	if ($("#pwd").val() != $("#pwd2").val()) {
		$.messager.alert(commomMessageTitle, '密码与确认新密码不一致', 'warning',
				function() {
					$("#pwd2").focus();
				});
		return false;
	}
	if ($.trim($("#xm").val()) == "") {
		$.messager.alert(commomMessageTitle, '姓名不能为空', 'warning', function() {
					$("#xm").focus();
				});
		return false;
	}
	// if ($.trim($("#zjHm").val()) == "") {
	// $.messager.alert(commomMessageTitle, '证件号码不能为空', 'warning', function() {
	// $("#zjHm").focus();
	// });
	// return false;
	// }
	if ($('#yhlx input[name="yhlxDm"]:checked ').val() == undefined) {
		$.messager.alert(commomMessageTitle, '用户类型不能为空', 'warning');
		return false;
	} else if ("01" == $('#yhlx input[name="yhlxDm"]:checked ').val()
			&& "-1" == $("#qyyhDm").val()) {
		$.messager.alert(commomMessageTitle, '签约银行不能为空', 'warning');
		return false;
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
								blhName : "Yhgl003BLH",
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

function zxUser(uuid) {
	$.messager.confirm(commomWaitTitle, "确认注销该用户", function(r) {
		if (r) {
			$.messager.progress({
						title : commomWaitTitle,
						msg : '正在注销用户...',
						text : ''
					});
			$.ajax({
						url : "/GeneralAction.do?sessionId=" + userInfo.yhwybz,
						async : true,
						dataType : "json",
						data : {
							jsonData : $.toJSON({
										blhName : "Yhgl003BLH",
										handleCode : "zxUser",
										yhwybz : userInfo.yhwybz,
										data : {
											uuid : uuid,
											rqq : $("#rqq").val(),
											rqz : $("#rqz").val()
										}
									})
						},
						type : 'post',
						timeout : sys_timeout,
						error : function(XMLHttpRequest, textStatus,
								errorThrown) {
							$.messager.progress('close');
							$.messager.alert(commomMessageTitle, textStatus,
									'error');
						},
						success : function(responseText, textStatus,
								XMLHttpRequest) {
							$.messager.progress('close');
							if (checkResponse(responseText)) {
								$.messager.alert(commomMessageTitle,
										responseText.msg, 'info');
								var dataList = responseText.data.dataList;
								$("#dataTable").datagrid("loadData",
										dataList.slice(0, 20));
								// 设置分页的相关属性
								var pager = $("#dataTable")
										.datagrid("getPager");
								pager.pagination({
											total : dataList.length,
											onSelectPage : function(pageNo,
													pageSize) {
												var start = (pageNo - 1)
														* pageSize;
												var end = start + pageSize;
												$("#dataTable").datagrid(
														"loadData",
														dataList.slice(start,
																end));
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
	});

}

function showQyyh(obj) {
	if (obj.checked == true) {
		$("#qyyh_row").show();
	}
}
function hideQyyh(obj) {
	if (obj.checked == true) {
		$("#qyyh_row").hide();
	}
}

function clearSaved() {
	$("#ff").form("clear");
}