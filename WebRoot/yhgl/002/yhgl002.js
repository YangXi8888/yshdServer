var userInfo;
$(document).ready(function() {
			hasUserInfo();
			userInfo = sessionLoad("userInfo");
		});

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
									blhName : "Yhgl002BLH",
									handleCode : "submitForm",
									yhwybz : userInfo.yhwybz,
									data : {
										N_LOGPASS : $("#password_new").val()
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
							$.messager.alert(commomMessageTitle,
									responseText.msg, 'info');
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
}

function baseCheck() {
	if ($.trim($("#password_new").val()) == "") {
		$.messager.alert(commomMessageTitle, '新密码不能为空', 'warning', function() {
					$("#password_new").focus();
				});
		return false;
	}
	if ($.trim($("#password_new").val().length) > 10
			|| $.trim($("#password_new").val().length) < 6) {
		$.messager.alert(commomMessageTitle, '请设置6至10位的新密码', 'warning',
				function() {
					$("#password_new").focus();
				});
		return false;
	}
	if ($.trim($("#password_com").val()) == "") {
		$.messager.alert(commomMessageTitle, '确认新密码不能为空', 'warning',
				function() {
					$("#password_com").focus();
				});
		return false;
	}
	if ($("#password_com").val() != $("#password_new").val()) {
		$.messager.alert(commomMessageTitle, '新密码与确认新密码不一致', 'warning',
				function() {
					$("#password_com").focus();
				});
		return false;
	}
	return true;
}
