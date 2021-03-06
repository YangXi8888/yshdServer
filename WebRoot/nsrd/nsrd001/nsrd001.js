$(function() {
			var swglm = getQueryString("swglm");
			initPage(swglm);
		});

function submitData() {
	if ("-1" == $("#qyyhDm").val()) {
		$.messager.alert(commomMessageTitle, "请选择推送银行", 'error');
		return;
	}
	$.messager.progress({
				title : "",
				msg : '数据处理中...',
				text : ''
			});
	$.ajax({
		url : "/Nsrd001Action.do",
		async : true,
		dataType : "json",
		data : {
			jsonData : $.toJSON({
						blhName : "Nsrd001BLH",
						handleCode : "sendData",
						data : {
							swglm : $("#swglm").val(),
							nsrSbm : $("#nsrSbm").val(),
							nsrMc : $("#nsrMc").val(),
							qyyhDm : $("#qyyhDm").val()
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
				$.messager.alert(commomMessageTitle, textStatus, 'error');
			}
		},
		success : function(responseText, textStatus, XMLHttpRequest) {
			$.messager.progress('close');
			if (checkResponse(responseText)) {
				$.messager.alert(commomMessageTitle,
						responseText.msg + "，关闭页面", 'info', function() {
							window.opener = null;
							window.open('', '_self');
							window.close();
						});
			} else {
				$.messager.alert(commomMessageTitle, responseText.msg, 'error');
			}
		}
	});
}

function initPage(swglm) {
	$.messager.progress({
				title : "",
				msg : '页面加载中...',
				text : ''
			});
	$.ajax({
		url : "/Nsrd001Action.do",
		async : true,
		dataType : "json",
		data : {
			jsonData : $.toJSON({
						blhName : "Nsrd001BLH",
						handleCode : "initPage",
						data : {
							swglm : swglm
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
				$.messager.alert(commomMessageTitle, textStatus, 'error');
			}
		},
		success : function(responseText, textStatus, XMLHttpRequest) {
			$.messager.progress('close');
			if (checkResponse(responseText)) {
				$("#swglm").val(swglm);
				$("#nsrMc").val(responseText.data.nsrMc);
				$("#nsrSbm").val(responseText.data.nsrSbm);
				var yhData = responseText.data.yhList;
				for (var i = 0; i < yhData.length; i++) {
					$("#qyyhDm").append("<option value='" + yhData[i].qyyhDm
							+ "'>" + yhData[i].qyyhMc + "</option>");

				}

			} else {
				$.messager.alert(commomMessageTitle, responseText.msg, 'error');
			}
		}
	});
}

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}