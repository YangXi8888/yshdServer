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


