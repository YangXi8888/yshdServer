var sys_timeout = 600000;
var commomMessageTitle = "系统提示消息";
var commomWaitTitle = "请等待";
var sessionStorageData = {};
var sessionId;
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

function enterPress(se, obj) {
	var e = se || window.event;
	if (e.keyCode == 13) {
		obj.focus();
	}
}

function isTimeout(responseText) {
	if ("ZB0036" == responseText.code) {
		alert(responseText.msg);
		if (self != top) {
			parent.window.location.href = "/yhgl/login.jsp";
		} else {
			window.location.href = "/yhgl/login.jsp";
		}
	} else {
		return false;
	}
}

/**
 * 使用前需要将全局变量sessionId设置值 保存本地session
 * 
 * @param {}
 *            key
 * @param {}
 *            val
 * @return {Boolean}
 */
function sessionSave(key, val) {
	try {
		sessionStorageData[$("#sessionId").val() + "_" + key] = {
			'val' : val
		};
		sessionStorage[$("#sessionId").val() + "_" + key] = JSON
				.stringify(sessionStorageData[$("#sessionId").val() + "_" + key]);
		return true;
	} catch (e) {
		alert("用户信息缓存失败:" + e.message);
		return false;
	}
}

/**
 * 使用前需要将全局变量sessionId设置值 加载本地session
 * 
 * @param {}
 *            key
 * @return {}
 */
function sessionLoad(key) {
	try {
		sessionStorageData[$("#sessionId").val() + "_" + key] = JSON
				.parse(sessionStorage[$("#sessionId").val() + "_" + key]);
		return sessionStorageData[$("#sessionId").val() + "_" + key].val;
	} catch (e) {
		return false;
	}
}

/**
 * 清除用户所有本地session
 * 
 */
function sessionClear() {
	for (var i = 0; i < sessionStorage.length; i++) {
		if (sessionStorage.key(i).indexOf($("#sessionId").val()) != -1) {
			sessionClearByKey(sessionStorage.key(i));
		}
	}
	for (var i = 0; i < sessionStorage.length; i++) {
		if (sessionStorage.key(i).indexOf($("#sessionId").val()) != -1) {
			sessionClearByKey(sessionStorage.key(i));
		}
	}
}

/**
 * 根据KEY清除本地session
 * 
 * @param {}
 *            key
 */
function sessionClearByKey(key) {
	sessionStorage.removeItem(key);
}

/**
 * session中没有用户信息就退出到登录
 * 
 * @return {}
 */
function hasUserInfo() {
	if (!sessionLoad("userInfo")) {
		if (self != top) {
			parent.window.location.href = window.location.protocol + "//"
					+ window.location.hostname + ":" + window.location.port;
		} else {
			window.location.href = window.location.protocol + "//"
					+ window.location.hostname + ":" + window.location.port;
		}
	} else {
		return sessionLoad("userInfo");
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

function openMaxWindow(url) {
	var winheight = screen.availHeight - 75;
	var winwidth = screen.availWidth - 10;
	var param = "height="
			+ winheight
			+ ",width="
			+ winwidth
			+ ",top=0,left=0,toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no,titlebar=no";
	window.open(url, "", param);
}

function openCenterWindow(url, winh, winw, scrollbars) {
	var winheight = winh;
	var winwidth = winw;
	var wintop = (screen.availHeight - winh) / 2;
	var winleft = (screen.availWidth - winw) / 2;
	if (null == scrollbars) {
		scrollbars = "yes";
	}
	var param = "height=" + winheight + ",width=" + winwidth + ",top=" + wintop
			+ ",left=" + winleft + ",toolbar=no,menubar=no,scrollbars="
			+ scrollbars + ",resizable=no,location=no,status=no,titlebar=no";
	window.open(url, "", param);
}

function setCookie(name, value) {
	var expdate = new Date();
	var argv = setCookie.arguments;
	var argc = setCookie.arguments.length;
	var expires = (argc > 2) ? argv[2] : null; // Expires – 过期时间。
	var path = (argc > 3) ? argv[3] : null; // Path – 路径。指定与cookie关联的WEB页.
	var domain = (argc > 4) ? argv[4] : null; // Domain – 域。指定关联的WEB服务器或域。
	var secure = (argc > 5) ? argv[5] : false; // Secure – 安全。
	if (expires != null)
		expdate.setTime(expdate.getTime() + (expires * 1000));
	document.cookie = name + "=" + escape(value)
			+ ((expires == null) ? "" : ("; expires=" + expdate.toGMTString()))
			+ ((path == null) ? "" : ("; path=" + path))
			+ ((domain == null) ? "" : ("; domain=" + domain))
			+ ((secure == true) ? "; secure" : "");
}

function getCookie(name) {
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while (i < clen) {
		var j = i + alen;
		if (document.cookie.substring(i, j) == arg)
			return getCookieVal(j);
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0)
			break;
	}
	return null;
}

function getCookieVal(offset) {
	var endstr = document.cookie.indexOf(";", offset);
	if (endstr == -1)
		endstr = document.cookie.length;
	return unescape(document.cookie.substring(offset, endstr));
}

/**
 * 获取指定年月的最后一天
 * 
 * @param {}
 *            year
 * @param {}
 *            month
 * @return {}
 */
function getLastDay(year, month) {
	var new_year = year;
	var new_month = month++;
	if (month > 12) {
		new_month -= 12;
		new_year++;
	}
	var new_date = new Date(new_year, new_month, 1);
	return (new Date(new_date.getTime() - 1000 * 60 * 60 * 24)).getDate();
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

function checkFileType(fileName, fileType) {
	if (fileType.indexOf(fileName.substr(fileName.lastIndexOf(".") + 1)
			.toUpperCase()) == -1) {
		return false;
	}
	return true;
}
