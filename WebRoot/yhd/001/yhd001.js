function downLoadTest() {
	hasUserInfo();// 判断是否有会话信息
	userInfo = sessionLoad("userInfo");// 获取用户会话信息
	$.messager.progress({
				title : commomWaitTitle,
				msg : '正在下载文件.....',
				text : ''
			});
	$.fileDownload("/GeneralAction.do?sessionId=" + userInfo.yhwybz, {
				httpMethod : 'POST',
				data : {
					jsonData : $.toJSON({
								blhName : "Yhd001BLH",
								handleCode : "initForm", // 方法名
								yhwybz : userInfo.yhwybz, // 必须
								downLoadFile : "1"
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