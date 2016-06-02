$(document).ready(function() {
	hasUserInfo();// 判断是否有会话信息
	var userInfo = sessionLoad("userInfo");// 获取用户会话信息

	$('input[type="file"]').ajaxfileupload({
		submit_button : $("#upBtn"),// 绑定某个DOM控件一般
		action : "/GeneralAction.do?sessionId=" + userInfo.yhwybz,
		params : {
			jsonData : $.toJSON({
						blhName : "Yhd002BLH",
						handleCode : "upLoadFile",
						yhwybz : userInfo.yhwybz
					})
		},
		onComplete : function(responseText) {
			$.messager.progress('close');
			if (checkResponse(responseText)) {
				$.messager.alert(commomMessageTitle, responseText.msg, 'info');
			} else {
				// 判断是否超时
				if (!isTimeout(responseText)) {
					$.messager.alert(commomMessageTitle, responseText.msg,
							'error');
				}
			}
		},
		onStart : function() {
			$.messager.progress({
						title : commomWaitTitle,
						msg : '正在上传文件...',
						text : ''
					});
			return true;
		},
		onCancel : function() {
			$.messager.progress('close');
		}
	});

});