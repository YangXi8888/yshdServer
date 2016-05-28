var sys_timeout = 600000;

function checkResponse(responseText) {
	try {
		if ("0" == responseText.code) {
			return true;
		}
	} catch (e) {
		return false;
	}
	return false;
}

/**
 * 加密
 * 
 * @param str
 * @param my
 * @returns
 */
function formatStr(str, my) {
	var key = CryptoJS.enc.Utf8.parse(my.toString().substr(0, 16));
	var iv = CryptoJS.enc.Utf8.parse(my.toString().substr(0, 16));
	var srcs = CryptoJS.enc.Utf8.parse(str);
	var encrypted = CryptoJS.AES.encrypt(srcs, key, {
				iv : iv,
				mode : CryptoJS.mode.CBC
			});
	return encrypted.toString();
}

/**
 * 返回data解密方法
 */
function Decrypt(srcs, my) {
	var key = CryptoJS.enc.Utf8.parse(my.toString().substr(0, 16));
	var iv = CryptoJS.enc.Utf8.parse(my.toString().substr(0, 16));
	var decrypt = CryptoJS.AES.decrypt(srcs, key, {
				iv : iv,
				mode : CryptoJS.mode.CBC
			});
	return decodeURI(CryptoJS.enc.Utf8.stringify(decrypt).toString());
}
/**
 * 替换解密后明文乱码方法
 */
function change(responseText) {
	var s1 = responseText.replace(/\%3A/g, ":");
	s1 = s1.replace(/\%2C/g, ",");
	s1 = s1.replace(/\%2F/g, "/");
	s1 = s1.replace(/\%3D/g, "=");
	s1 = s1.replace(/\%3B/g, ";");
	s1 = s1.replace(/\+/g, " ");
	s1 = s1.replace(/\%24/g, "$");
	s1 = s1.replace(/\%26/g, "&");
	return s1;
}

function isTimeout(responseText) {
	if ("ZB0036" == responseText.code) {
		alert(responseText.msg);
		window.location.href = "/LoginAction.do";
	} else {
		return false;
	}
}

// 验证码倒计时计时器
// var wait = 180;
function timeout(btnId, wait) {
	if (wait == 0) {
		document.getElementById(btnId).disabled = "";
		document.getElementById(btnId).innerText = "获取验证码";
		wait = 180;
	} else {
		document.getElementById(btnId).disabled = "disabled";
		document.getElementById(btnId).innerText = "重新发送（" + wait + "）";
		wait--;
		setTimeout(function() {
					timeout(btnId, wait);
				}, 1000);
	}
}
function cztimeout(btnId) {
	document.getElementById(btnId).disabled = "";
	document.getElementById(btnId).innerText = "获取验证码";
}

// sessionStorage
var sessionStorageData = {};
function sessionSave(key, val) {
	try {
		sessionStorageData[key] = {
			'val' : val
		};
		sessionStorage[key] = JSON.stringify(sessionStorageData[key]);
		return true;
	} catch (e) {
		alert("用户信息缓存失败:" + e.message);
		return false;
	}
}

function sessionLoad(key) {
	try {
		sessionStorageData[key] = JSON.parse(sessionStorage[key]);
		return sessionStorageData[key].val;
	} catch (e) {
		return false;
	}
}

function sessionClearAll() {
	sessionStorage.clear();
}

function sessionClearByKey(key) {
	sessionStorage.removeItem(key);
}

/**
 * 微信alert
 * 
 * @param {}
 *            alertId
 * @param {}
 *            content
 * @param {}
 *            fn
 */
function showWxAlert(alertId, content, fn ,title) {
	$('#' + alertId + "_content").html(content);
	$('#' + alertId).show().on('click', '.ok', function() {
				$('#' + alertId).off('click').hide();
				if (fn != undefined) {
					fn();
				}
			});
	if(title){
		$('#' + alertId + "_title").html(title);
	}
}
/**
 * 微信confirm
 * 
 * @param {}
 *            confirmId
 * @param {}
 *            title
 * @param {}
 *            content
 * @param {}
 *            fn
 */
function showWxConfirm(confirmId, title, content, fn) {
	$('#' + confirmId + "_title").text(title);
	$('#' + confirmId + "_content").html(content);
	$('#' + confirmId).show();
	$('#' + confirmId).on('click', '.ok', function() {
				$('#' + confirmId).off('click').hide();
				if (fn != undefined) {
					fn();
				}
			});
	$('#' + confirmId).on('click', '.cancel', function() {
				$('#' + confirmId).off('click').hide();
			});
}
/**
 * 微信loading加载
 * 
 * @param {}
 *            loadingId
 * @param {}
 *            content
 */
function showWxLoading(loadingId, content) {
	$('#' + loadingId + "_content").text(content);
	$('#' + loadingId).show();
	setTimeout(function() {
				$('#' + loadingId).hide();
			}, sys_timeout);
}

function hideWxLoading(loadingId) {
	$('#' + loadingId).hide();
}

// 缓存证件类型
var saveZjlxList = function() {
	var zjLxList = [];
	zjLxList.push({
				"ZJLXDM" : "01",
				"ZJLXMC" : "护照"
			}, {
				"ZJLXDM" : "02",
				"ZJLXMC" : "通行证"
			}, {
				"ZJLXDM" : "03",
				"ZJLXMC" : "回乡证"
			}, {
				"ZJLXDM" : "04",
				"ZJLXMC" : "台胞证"
			}, {
				"ZJLXDM" : "05",
				"ZJLXMC" : "旅行证"
			}, {
				"ZJLXDM" : "06",
				"ZJLXMC" : "身份证"
			}, {
				"ZJLXDM" : "07",
				"ZJLXMC" : "军官证"
			}, {
				"ZJLXDM" : "90",
				"ZJLXMC" : "其他"
			});
	sessionSave("zjLxList", zjLxList);
}();

// 获取首页统计图标等信息
function queryNsqkData() {
	var userInfo = sessionLoad('userInfo');
	var dfd = $.Deferred();
	var reqData = {
		zjlxDm : userInfo.zjLx,
		zjHm : userInfo.zjHm
	};
	var jsonReqData = {
		jsonData : $.toJSON({
					blhName : 'Cxfw002BLH',
					handleCode : "queryNsqk",
					yhwybz : sessionId,
					data : reqData,
					bizDataMw : formatStr(reqData, sessionId)
				})
	};
	$.ajax({
				url : "/Gt3GeneralAction.do?sessionId=" + sessionId,
				async : true,
				dataType : "text",
				data : jsonReqData,
				type : 'post',
				timeout : sys_timeout,
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					dfd.resolve(textStatus);
				},
				success : function(responseText, textStatus, XMLHttpRequest) {
					dfd.resolve($.evalJSON(change(Decrypt(responseText,
							sessionId))));
				}
			});
	return dfd.promise();
}

// session中没有用户信息就退出到登录
function hasUserInfo() {
	if (!sessionLoad("userInfo")) {
		window.location.href = window.location.protocol + "//"
				+ window.location.hostname + ":" + window.location.port;
	}
}

function NullToEmpty(str) {
	if (str == null) {
		return "";
	} else {
		return str;
	}
}

function NullToZero(str) {
	if (str == null || str == "") {
		return "0";
	} else {
		return str;
	}
}
