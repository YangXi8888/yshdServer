var userInfo;

$(document).ready(function() {
			showCurrPanel();
			initForm();
		});

function initForm() {
	hasUserInfo();
	var userInfo = sessionLoad("userInfo");
	$("#userNmae").html(userInfo.xm);
	$.messager.progress({
				title : commomWaitTitle,
				msg : '正在加载首页...',
				text : ''
			});

	$.ajax({
				url : "/GeneralAction.do?sessionId=" + userInfo.yhwybz,
				async : true,
				dataType : "json",
				data : {
					jsonData : $.toJSON({
								blhName : "MainBLH",
								handleCode : "initForm",
								yhwybz : userInfo.yhwybz
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
						$("#mainTree").tree({
									data : responseText.data.gnsList,
									animate : true,
									onClick : function(node) {
										addPanel(node);
										$(this).tree('toggle', node.target);
									}
								});
						if ("01" == userInfo.yhLxDm) {
							$("#nsr_Hs").html(responseText.data.nsr_Hs);
							$("#file_Zs").html(responseText.data.file_Zs);
							$("#yhdXxDiv").show();
						} else if ("02" == userInfo.yhLxDm) {
							$("#nsr_Hs_Swd").html(responseText.data.nsr_Hs_Swd);
							$("#file_Zs_Swd")
									.html(responseText.data.file_Zs_Swd);
							$("#swdXxDiv").show();
						}
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

function addPanel(obj) {
	var title = obj.text;
	var href = obj.href;
	if ($("#myAdd").tabs("exists", title)) {
		$("#myAdd").tabs("select", title);
		return;
	};
	var openType = obj.openType;
	if (href != "#" && href != "" && href != null && href != "null") {
		if (openType == "0") {
			$('#myAdd').tabs('add', {
				title : title,
				content : '<div style="padding:10px;height:700px"><iframe scrolling="yes" frameborder="0"'
						+ 'src='
						+ href
						+ '  style="width:100%;height:100%;" ></iframe></div>',
				closable : true
			});
		} else {
			if (openType == "1") {
				openMaxWindow(href);
			}
		}
	}
}

// 显示当前操作的panel
function showCurrPanel() {
	var currPanelNum = getCookie('panelNum');
	if (currPanelNum == undefined || typeof(currPanelNum) == "undefined"
			|| currPanelNum == null) {
		currPanelNum = 0;
	}
	$('#myAdd').tabs('select', parseInt(currPanelNum));
}

function tcXt() {
	$.messager.confirm(commomMessageTitle, '确定退出系统?', function(r) {
				if (r == true) {
					sessionClear();
					window.location.href = window.location.protocol + "//"
							+ window.location.hostname + ":"
							+ window.location.port;
				}
			});
}

function xgMm() {
	var title = "修改密码";
	if ($("#myAdd").tabs("exists", title)) {
		$("#myAdd").tabs("select", title);
		return;
	}
	$('#myAdd').tabs('add', {
		title : title,
		content : '<div style="padding:10px;height:700px"><iframe scrolling="yes" frameborder="0" style="width:100%;height:100%"'
				+ ' src="/yhgl/001/yhgl001.jsp?sessionId='
				+ $('#sessionId').val() + '"></iframe></div>',
		closable : true
	});
}