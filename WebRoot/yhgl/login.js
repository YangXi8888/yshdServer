function dlXt() {
	if (baseCheck()) {
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
						if (XMLHttpRequest.responseText.indexOf("script") != -1) {
							document.write(XMLHttpRequest.responseText);
						} else {
							$.messager.alert(commomMessageTitle, textStatus,
									'error');
						}
					},
					success : function(responseText, textStatus, XMLHttpRequest) {
						if (checkResponse(responseText)) {
							$("#sessionId")
									.val(responseText.data.loginVO.yhwybz);
							sessionSave("sysDate", responseText.data.sysDate);
							sessionSave("userInfo", responseText.data.loginVO);
							window.location.href = "../main/main.jsp?sessionId="
									+ responseText.data.loginVO.yhwybz;
						} else {
							$.messager.alert(commomMessageTitle,
									responseText.msg, 'error');
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
	if ($.trim($("#passWord").val()) == "") {
		$.messager.alert(commomMessageTitle, '密码不能为空', 'warning', function() {
					$("#passWord").focus();
				});
		return false;
	}
	if ($.trim($("#yzm").val()) == "") {
		$.messager.alert(commomMessageTitle, '验证码不能为空', 'warning', function() {
					$("#yzm").focus();
				});
		return false;
	}
	return true;
}

function yzmOnKeyDown(se) {
	var e = se || window.event;
	if (e.keyCode == 13) {
		dlXt();
	}
}