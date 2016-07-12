var userInfo;
var editIndex = undefined;
$(document).ready(function() {
			hasUserInfo();
			userInfo = sessionLoad("userInfo");
			initPage();
			queryData();
		});

function initPage() {
	$("#dataTable").datagrid({
				toolbar : '#tb',
				iconCls : 'icon-edit',
				singleSelect : true,
				onClickCell : onClickCell,
				onEndEdit : onEndEdit,
				onAfterEdit : function(rowIndex, rowData, changes) {
					editIndex = undefined;
					saveData(rowData);
				},
				pagination : true,
				fit : false,
				fitColumn : false,
				striped : true,
				border : false,
				columns : [[{
							field : 'QYYH_DM',
							title : '银行编码',
							width : 100,
							sortable : true
						}, {
							field : 'QYYH_MC',
							title : '银行名称',
							width : 100,
							sortable : true,
							editor : {
								type : 'textbox',
								options : {
									required : true
								}
							}
						}, {
							field : 'XY_BJ',
							title : '选用标记',
							width : 100,
							formatter : function(value, row) {
								if ("1" == value) {
									return "选用";
								} else {
									return "不选用";
								}
							},
							editor : {
								type : 'combobox',
								options : {
									required : true,
									valueField : 'value',
									textField : 'label',
									data : [{
												label : '选用',
												value : '1'
											}, {
												label : '不选用',
												value : '0'
											}]
								}
							}
						}]]
			});

	$("#dataTable").datagrid("getPager").pagination({
				showPageList : false,
				showRefresh : true,
				displayMsg : '',
				pageSize : 15,
				pageList : [15, 30, 45, 60],
				onRefresh : function(pageNumber, pageSize) {
					queryData();
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
								blhName : "Swd003BLH",
								handleCode : "queryData",
								yhwybz : userInfo.yhwybz
							})
				},
				type : 'post',
				timeout : sys_timeout,
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messager.progress('close');
					// 增加是否脚本判断
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

function saveData(rowData) {
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
								blhName : "Swd003BLH",
								handleCode : "saveData",
								yhwybz : userInfo.yhwybz,
								data : {
									QYYH_DM : rowData.QYYH_DM,
									QYYH_MC : rowData.QYYH_MC,
									XY_BJ : rowData.XY_BJ
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

function endEditing() {
	if (editIndex == undefined) {
		return true;
	}
	if ($('#dataTable').datagrid('validateRow', editIndex)) {
		$('#dataTable').datagrid('endEdit', editIndex);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}

function onClickCell(index, field) {
	if (editIndex != index) {
		if (endEditing()) {
			$('#dataTable').datagrid('selectRow', index).datagrid('beginEdit',
					index);
			var ed = $('#dataTable').datagrid('getEditor', {
						index : index,
						field : field
					});
			if (ed) {
				($(ed.target).data('textbox')
						? $(ed.target).textbox('textbox')
						: $(ed.target)).focus();
			}
			editIndex = index;
		} else {
			setTimeout(function() {
						$('#dataTable').datagrid('selectRow', editIndex);
					}, 0);
		}
	}
}
function onEndEdit(index, row) {
	var ed = $(this).datagrid('getEditor', {
				index : index,
				field : 'XY_BJ'
			});
	row.label = $(ed.target).combobox('getText');
}
function append() {
	if (endEditing()) {
		$('#dataTable').datagrid('appendRow', {
					QYYH_DM : '',
					QYYH_MC : '',
					XY_BJ : '1'
				});
		editIndex = $('#dataTable').datagrid('getRows').length - 1;
		$('#dataTable').datagrid('selectRow', editIndex).datagrid('beginEdit',
				editIndex);
	}
}

function accept() {
	if (endEditing()) {
		$('#dataTable').datagrid('acceptChanges');
	}
}
function reject() {
	$('#dataTable').datagrid('rejectChanges');
	editIndex = undefined;
}