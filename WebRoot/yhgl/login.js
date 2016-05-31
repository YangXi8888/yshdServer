function dlXt() {
	if (loginCheck()) {
		$.ajax({
					url : "/LoginAction.do",
					async : true,
					dataType : "json",
					data : {
						jsonData : $.toJSON({
									blhName : "LoginBLH",
									handleCode : "login",
									data : {
										sjHm : $("#sjHm").val(),
										passWord : $("#passWord").val(),
										yzm : $("#yzm").val()
									}
								})
					},
					type : 'post',
					timeout : sys_timeout,
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.messager.alert(commomMessageTitle, textStatus,
								'error');
					},
					success : function(responseText, textStatus, XMLHttpRequest) {
						if (checkResponse(responseText)) {
							$("#sessionId")
									.val(responseText.data.loginVO.yhwybz);
							sessionSave("sysDate", responseText.data.sysDate);
							sessionSave("userInfo", responseText.data.loginVO);
							window.location.href = "./main/main.jsp?sessionId="
									+ responseText.data.loginVO.yhwybz;
						} else {
							$.messager.alert(commomMessageTitle,
									responseText.msg, 'error');
						}
					}
				});
	}
}

function loginCheck() {
	if ($.trim($("#sjHm").val()) == "") {
		$.messager.alert(commomMessageTitle, '手机不能为空', 'warning');
		return false;
	}
	if (!f_check_mobile(document.getElementById("sjHm"))) {
		$.messager.alert(commomMessageTitle, '手机号格式有误', 'warning');
		return false;
	}
	if ($.trim($("#passWord").val()) == "") {
		$.messager.alert(commomMessageTitle, '密码不能为空', 'warning');
		return false;
	}
	if ($.trim($("#yzm").val()) == "") {
		$.messager.alert(commomMessageTitle, '验证码不能为空', 'warning');
		return false;
	}
	return true;
}