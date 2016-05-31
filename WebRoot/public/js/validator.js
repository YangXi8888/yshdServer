/*******************************************************************************
 * @author杨希
 * @version 1.0
 * @see 很费解
 ******************************************************************************/
var valiObjName = "";

function validator(useValiObjName) {
	valiObjName = useValiObjName;
	this.errorMessage = new Array();
	this.model = "1";
	this.errorItem = new Array();
	this.singer = false;
	/**
	 * mode 默认1 1 只要出现错误就停止校验并弹出错误提示 2 只要出现错误就停止校验并显示错误红字 3 出现错误继续校验,校验所有后弹出错误提示
	 * 4 出现错误继续校验,校验所有显示错误红字
	 */
	this.validate = function(soureTag, model) {
		var obj, count;
		if (soureTag.tagName == "FORM") {
			obj = soureTag.elements;
			count = obj.elements.length;
		} else {
			obj = soureTag.all;
			count = obj.length;
		}
		if (null != model) {
			this.model = model;
		}
		this.errorMessage.length = 0;
		this.errorItem.length = 0;
		var errSpan = $$('span.errSpan');
		for (var i = 0; i < errSpan.length; i++) {
			Element.remove(errSpan[i]);
		}
		for (var i = 0; i < count; i++) {
			with (obj[i]) {
				var dataType = getAttribute("dataType");
				if (typeof(dataType) == "object"
						|| typeof(dataType) == "undefined") {
					continue;
				}
				switch (dataType) {
					case "require" :
						this.requireF(obj[i], i);
						break;
					case "chinese" :
						this.chineseF(obj[i], i);
						break;
					case "english" :
						this.englishF(obj[i], i);
						break;
					case "phone" :
						this.checkPhoneF(obj[i], i);
						break;
					case "mobile" :
						this.checkMobileF(obj[i], i);
						break;
					case "mobOrph" :
						this.checkMobOrph(obj[i], i);
						break;
					case "ip" :
						this.checkIPF(obj[i], i);
						break;
					case "port" :
						this.checkPortF(obj[i], i);
						break;
					case "date" :
						this.checkDateF(obj[i], i);
						break;
					case "numid" :
						this.checkNumIdF(obj[i], i);
						break;
					case "yzbm" :
						this.checkYzbmF(obj[i], i);
						break;
					case "number" :
						this.checkNumberF(obj[i], i);
						break;
					case "time" :
						this.checkTimeF(obj[i], i);
						break;
					case "weburl" :
						this.checkWebUrlF(obj[i], i);
						break;
					case "email" :
						this.checkEmailF(obj[i], i);
						break;
					case "double" :
						this.checkDoubleF(obj[i], i);
						break;
					case "integer" :
						this.checkIntegerF(obj[i], i);
						break;
					case "compStrLen" :
						this.checkStrLenF(obj[i], i);
						break;
					case "compIntLen" :
						this.checkIntLenF(obj[i], i);
						break;
					case "compNumLen" :
						this.checkNumLenF(obj[i], i);
						break;
					case "file" :
						this.checkFileF(obj[i], i);
						break;
					case "select" :
						this.checkRequire(obj[i], i);
						break;
					case "radio" :
						this.checkRaidoF(obj[i], i);
						break;
					case "checkbox" :
						this.checkCheckBoxF(obj[i], i);
						break;
					case "compDouLen" :
						this.checkDouLenF(obj[i], i);
						break;
					case "compNumObj" :
						this.checkNumObjF(obj[i], i);
						break;
					case "compNyObj" :
						this.checkNyObjF(obj[i], i);
						break;
					case "compDouObj" :
						this.checkDouObjF(obj[i], i);
						break;
					case "compIntObj" :
						this.checkIntObjF(obj[i], i);
						break;
					case "datetime" :
						this.checkDateTimeF(obj[i], i);
						break;
					case "sfz" :
						this.checkSfzF(obj[i], i);
						break;
					default :
						break;
				}
			}
			if (this.errorMessage.length != 0) {
				if (this.model == "1" || this.model == "2") {
					break;
				}
			}
		}
		if (this.errorMessage.length > 0) {
			this.showError();
			return false;
		}
		return true;
	}
	this.setFocus = function() {
		var useValiObjName = eval(valiObjName);
		if (useValiObjName.errorItem[useValiObjName.errorItem.length - 1].tagName != "SELECT"
				&& useValiObjName.errorItem[useValiObjName.errorItem.length - 1].type != "radio") {
			useValiObjName.errorItem[useValiObjName.errorItem.length - 1]
					.select();
		}
		if (useValiObjName.errorItem[useValiObjName.errorItem.length - 1].type != "radio") {
			if (validatorIsVisible(useValiObjName.errorItem[useValiObjName.errorItem.length
					- 1])
					&& validatorCheckPrVis(useValiObjName.errorItem[useValiObjName.errorItem.length
							- 1])) {
				useValiObjName.errorItem[useValiObjName.errorItem.length - 1]
						.focus();
			}
		}
	}
	this.showError = function() {
		if (this.model == "1") {
			Ext.MessageBox.show({
						title : '信息提示框',
						msg : this.errorItem[this.errorItem.length - 1].bt
								+ " : "
								+ this.errorMessage[this.errorItem.length - 1],
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.WARNING,
						fn : this.setFocus
					});
		} else if (this.model == "2") {
			if (this.errorItem[this.errorItem.length - 1].type == "radio"
					|| this.errorItem[this.errorItem.length - 1].type == "checkbox") {
				Element.insert(this.errorItem[this.errorItem.length - 1], {
							before : "<span class='errSpan' style='color:red'>"
									+ this.errorMessage[this.errorMessage.length
											- 1] + "</span>"
						});
			} else {
				Element.insert(this.errorItem[this.errorItem.length - 1], {
							after : "<span class='errSpan' style='color:red'>"
									+ this.errorMessage[this.errorMessage.length
											- 1] + "</span>"
						});
			}
		} else if (this.model == "3") {
			var msg = "<p>以下原因导致无法保存数据：</p>"
			for (var i = 0; i < this.errorMessage.length; i++) {
				if (this.errorItem[i] != null) {
					msg = msg + "<p><font color='red'>" + this.errorItem[i].bt
							+ ":" + this.errorMessage[i] + "</font></p>";
				}

			}
			var errIn = 0;
			for (var i = 0; i < this.errorItem.length; i++) {
				if (this.errorItem[i] != null) {
					errIn = i;
					break;
				}
			}
			if (this.errorItem[errIn].tagName != "SELECT") {
				this.errorItem[errIn].select();
			}
			if (validatorIsVisible(this.errorItem[errIn])
					&& validatorCheckPrVis(this.errorItem[errIn])) {
				this.errorItem[errIn].focus();
			}
			Ext.MessageBox.show({
						title : '信息提示框',
						msg : msg,
						buttons : Ext.MessageBox.OK
					});
		} else if (this.model == "4") {
			for (var j = 0; j < this.errorMessage.length; j++) {
				if (this.errorItem[j] != null) {
					if (this.errorItem[j].type == "radio"
							|| this.errorItem[j].type == "checkbox") {
						Element.insert(this.errorItem[j], {
									before : "<span class='errSpan' style='color:red'>"
											+ this.errorMessage[j] + "</span>"
								});
					} else {
						Element.insert(this.errorItem[j], {
									after : "<span class='errSpan' style='color:red'>"
											+ this.errorMessage[j] + "</span>"
								});
					}
				}
			}
		}
	}

	/***************************************************************************
	 * 调用必填校验
	 */
	this.requireF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		return true;
	}

	/***************************************************************************
	 * 中文校验
	 */
	this.chineseF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		/***********************************************************************
		 * 三种情况分析 1 是必填字段已经填有值 2 非必填字段有值， 3 非必填字段无值 只要条件符合值不空就校验
		 **********************************************************************/
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_chinese(obj)) {
				if (this.singer) {
					f_alert(obj, "请输入中文");
					return false;
				} else {
					this.addError(obj, index, "请输入中文");
					return false;
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 英文校验
	 */
	this.englishF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_english(obj)) {
				if (this.singer) {
					f_alert(obj, "请输入英文");
					return false;
				} else {
					this.addError(obj, index, "请输入英文");
					return false;
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 校验是否必填
	 **************************************************************************/
	this.checkRequire = function(obj, index) {
		if (obj.require != undefined) {
			if (validatorTrim(obj.require) == "true") {
				if (validatorTrim(obj.value).empty()) {
					if (this.singer) {
						f_alert(obj, "不能为空");
						return false;
					} else {
						this.addError(obj, index, "不能为空");
						return false;
					}
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 移动电话号码校验
	 */
	this.checkMobileF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_mobile(obj)) {
				if (this.singer) {
					f_alert(obj, "格式不正确");
					return false;
				} else {
					this.addError(obj, index, "格式不正确");
					return false;
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 固定电话校验
	 */
	this.checkPhoneF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_phone(obj)) {
				if (this.singer) {
					f_alert(obj, "格式不正确");
					return false;
				} else {
					this.addError(obj, index, "格式不正确");
					return false;
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 联系电话校验
	 **************************************************************************/
	this.checkMobOrph = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_mobileorphone(obj)) {
				if (this.singer) {
					f_alert(obj, "格式不正确");
					return false;
				} else {
					this.addError(obj, index, "格式不正确");
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * IP校验
	 */
	this.checkIPF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_IP(obj)) {
				if (this.singer) {
					f_alert(obj, "格式不正确");
					return false;
				} else {
					this.addError(obj, index, "格式不正确");
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * 端口校验
	 */
	this.checkPortF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_port(obj)) {
				if (this.singer) {
					f_alert(obj, "格式不正确");
					return false;
				} else {
					this.addError(obj, index, "格式不正确");
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * 日期校验
	 */
	this.checkDateF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_date(obj)) {
				if (this.singer) {
					f_alert(obj, "格式不正确");
					return false;
				} else {
					this.addError(obj, index, "格式不正确");
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * 数字编码校验
	 */
	this.checkNumIdF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_number(obj)) {
				if (this.singer) {
					f_alert(obj, "应该为数字");
					return false;
				} else {
					this.addError(obj, index, "应该为数字");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 数字校验，如果长度超出一位则第一不能为0
	 */
	this.checkNumberF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_numberze(obj)) {
				if (this.singer) {
					f_alert(obj, "应该为0或正整数");
					return false;
				} else {
					this.addError(obj, index, "应该为0或正整数");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 校验时间
	 */
	this.checkTimeF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_time(obj)) {
				if (this.singer) {
					f_alert(obj, "格式不正确");
					return false;
				} else {
					this.addError(obj, index, "格式不正确");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 校验网站地址
	 */
	this.checkWebUrlF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_weburl(obj)) {
				if (this.singer) {
					f_alert(obj, "格式不正确");
					return false;
				} else {
					this.addError(obj, index, "格式不正确");
					return false;
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 校验EMAIL
	 **************************************************************************/
	this.checkEmailF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_email(obj)) {
				if (this.singer) {
					f_alert(obj, "格式不正确");
					return false;
				} else {
					this.addError(obj, index, "格式不正确");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 校验浮点数
	 */
	this.checkDoubleF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_rolefloat(obj)) {
				if (this.singer) {
					f_alert(obj, "格式不正确");
					return false;
				} else {
					this.addError(obj, index, "格式不正确");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 校验整数
	 */
	this.checkIntegerF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_integer(obj)) {
				if (this.singer) {
					f_alert(obj, "格式不正确");
					return false;
				} else {
					this.addError(obj, index, "格式不正确");
					return false;
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 字符长度比较
	 **************************************************************************/
	this.checkStrLenF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_strlen(obj)) {
				if (this.singer) {
					f_alert(obj, "字符长度无效");
					return false;
				} else {
					this.addError(obj, index, "字符长度无效");
					return false;
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 校验整数大小
	 **************************************************************************/
	this.checkIntLenF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_intlen(obj)) {
				if (this.singer) {
					f_alert(obj, "数据不正确");
					return false;
				} else {
					this.addError(obj, index, "数据不正确");
					return false;
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 校验浮点数大小
	 **************************************************************************/
	this.checkDouLenF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_doulen(obj)) {
				if (this.singer) {
					f_alert(obj, "数据不正确");
					return false;
				} else {
					this.addError(obj, index, "数据不正确");
					return false;
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 校验自然数大小
	 **************************************************************************/
	this.checkNumLenF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_numlen(obj)) {
				if (this.singer) {
					f_alert(obj, "数据不正确");
					return false;
				} else {
					this.addError(obj, index, "数据不正确");
					return false;
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 文件校验
	 **************************************************************************/
	this.checkFileF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_filetype(obj)) {
				if (this.singer) {
					f_alert(obj, "文件类型不被允许");
					return false;
				} else {
					this.addError(obj, index, "文件类型不被允许");
					return false;
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 校验单选框是否选择
	 */
	this.checkRaidoF = function(obj, index) {
		if (obj.require == "true") {
			if (!f_check_radio(obj)) {
				if (this.singer) {
					f_alert(obj, "请选择其中一个");
					return false;
				} else {
					this.addError(obj, index, "请选择其中一个");
					return false;
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 校验复选框是否选择
	 **************************************************************************/
	this.checkCheckBoxF = function(obj, index) {
		if (obj.require == "true") {
			if (!f_check_checkbox(obj)) {
				if (this.singer) {
					f_alert(obj, "请选择指定数目");
					return false;
				} else {
					this.addError(obj, index, "请选择指定数目");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 自然数对象大小比较
	 */
	this.checkNumObjF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_numobj(obj)) {
				if (this.singer) {
					f_alert(obj, "数据不正确");
					return false;
				} else {
					this.addError(obj, index, "数据不正确");
					return false;
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 年月对象比较大小
	 **************************************************************************/
	this.checkNyObjF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_nyobj(obj)) {
				if (this.singer) {
					f_alert(obj, "数据不正确");
					return false;
				} else {
					this.addError(obj, index, "数据不正确");
					return false;
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 浮点数对象比较
	 **************************************************************************/
	this.checkDouObjF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_douobj(obj)) {
				if (this.singer) {
					f_alert(obj, "数据不正确");
					return false;
				} else {
					this.addError(obj, index, "数据不正确");
					return false;
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 整数对象比较
	 **************************************************************************/
	this.checkIntObjF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_intobj(obj)) {
				if (this.singer) {
					f_alert(obj, "数据不正确");
					return false;
				} else {
					this.addError(obj, index, "数据不正确");
					return false;
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 校验日期时间
	 **************************************************************************/
	this.checkDateTimeF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_datetime(obj)) {
				if (this.singer) {
					f_alert(obj, "数据不正确");
					return false;
				} else {
					this.addError(obj, index, "数据不正确");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 校验身份证
	 */

	this.checkSfzF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_sfz(obj)) {
				if (this.singer) {
					f_alert(obj, "数据不正确");
					return false;
				} else {
					this.addError(obj, index, "数据不正确");
					return false;
				}
			}
		}
		return true;
	}

	/***************************************************************************
	 * 邮政编码
	 */
	this.checkYzbmF = function(obj, index) {
		if (!this.checkRequire(obj, index)) {
			return false;
		}
		if (validatorTrim(obj.value).empty() == false) {
			if (!f_check_number(obj)) {
				if (this.singer) {
					f_alert(obj, "应该为数字");
					return false;
				} else {
					this.addError(obj, index, "应该为数字");
					return false;
				}
			}
			if (validatorTrim(obj.value).length != 6) {
				if (this.singer) {
					f_alert(obj, "长度应该为六位");
					return false;
				} else {
					this.addError(obj, index, "长度应该为六位");
					return false;
				}
			}
		}
		return true;
	}

	this.addError = function(obj, index, str) {
		this.errorItem[index] = obj;
		this.errorMessage[index] = str;
	}
}

/** ************调用函数************************ */

/*
 * 校验中文
 */
function f_check_chinese(obj) {
	if (/^[\u0391-\uFFE5]+$/.test(validatorTrim(obj.value))) {
		return true;
	}
	return false;
}

/*
 * 校验英文
 */
function f_check_english(obj) {
	if (/^[A-Za-z]+$/.test(validatorTrim(obj.value))) {
		return true;
	}
	return false;
}

/*
 * 判断是否为数字，是则返回true,否则返回false
 */
function f_check_number(obj) {
	if (typeof(obj) == "object") {
		if (/^\d+$/.test(validatorTrim(obj.value))) {
			return true;
		}
	} else {
		if (/^\d+$/.test(validatorTrim(obj))) {
			return true;
		}
	}
	return false;
}

/*
 * 判断是否为数字，不能以0开头
 */
function f_check_numberze(obj) {
	if (typeof(obj) == "object") {
		if (validatorTrim(obj.value).length > 1) {
			if (validatorTrim(obj.value).substr(0, 1) == '0') {
				return false;
			}
		}
		if (/^\d+$/.test(validatorTrim(obj.value))) {
			return true;
		}
	} else {
		if (validatorTrim(obj).length > 1) {
			if (validatorTrim(obj).substr(0, 1) == '0') {
				return false;
			}
		}
		if (/^\d+$/.test(validatorTrim(obj))) {
			return true;
		}
	}
	return false;
}

/*******************************************************************************
 * 要求：一、移动电话号码为11或12位，如果为12位,那么第一位为0 二、11位移动电话号码的第一位和第二位为"13"或"15"或"18"
 * 三、12位移动电话号码的第二位和第三位为"13"或"15"或"18" 用途：检查输入手机号码是否正确 返回： 如果通过验证返回true,否则返回false
 */
function f_check_mobile(obj) {
	var re13 = new RegExp(/(^[1][3][0-9]{9}$)|(^0[1][3][0-9]{9}$)/);
	var re14 = new RegExp(/(^[1][4][0-9]{9}$)|(^0[1][4][0-9]{9}$)/);
	var re15 = new RegExp(/(^[1][5][0-9]{9}$)|(^0[1][5][0-9]{9}$)/);
	var re17 = new RegExp(/(^[1][7][0-9]{9}$)|(^0[1][7][0-9]{9}$)/);
	var re18 = new RegExp(/(^[1][8][0-9]{9}$)|(^0[1][8][0-9]{9}$)/);
	if (re13.test(validatorTrim(obj.value))) {
		return true;
	} else if (re14.test(validatorTrim(obj.value))) {
		return true;
	} else if (re15.test(validatorTrim(obj.value))) {
		return true;
	} else if (re17.test(validatorTrim(obj.value))) {
		return true;
	} else if (re18.test(validatorTrim(obj.value))) {
		return true;
	}
	return false;
}

/*******************************************************************************
 * 要求：一、电话号码由数字、"("、")"和"-"构成 二、电话号码为3到8位 三、如果电话号码中包含有区号，那么区号为三位或四位
 * 四、区号用"("、")"或"-"和其他部分隔开 用途：检查输入的电话号码格式是否正确 返回： 如果通过验证返回true,否则返回false
 */
function f_check_phone(obj) {
	var regu = /(^([0][1-9]{2,3}[-])?\d{3,8}(-\d{1,6})?$)|(^\([0][1-9]{2,3}\)\d{3,8}(\(\d{1,6}\))?$)|(^\d{3,8}$)/;
	var re = new RegExp(regu);
	if (re.test(validatorTrim(obj.value)) && parseInt(obj.value.length) >= 8) {
		return true;
	}
	return false;
}

/*******************************************************************************
 * 要求：一、电话号码由数字、"("、")"和"-"构成 二、电话号码为12到13位 三、电话号码中必须包含有区号，区号为三位或四位
 * 四、区号用"("、")"或"-"和其他部分隔开 用途：检查输入的电话号码格式是否正确 返回： 如果通过验证返回true,否则返回false
 */
function f_check_phone_mustqh(obj) {
	var regu = /(^([0][1-9]{2,3}[-])?\d{3,8}(-\d{1,6})?$)|(^\([0][1-9]{2,3}\)\d{3,8}(\(\d{1,6}\))?$)|(^\d{3,8}$)/;
	var re = new RegExp(regu);
	if (re.test(validatorTrim(obj.value))&& parseInt(obj.value.length) > 11) {
		return true;
	}
	return false;
}

/*******************************************************************************
 * 校验联系电话
 ******************************************************************************/
function f_check_mobileorphone(obj) {
	if (!f_check_mobile(obj) && !f_check_phone(obj)) {
		return false;
	}
	return true;
}

/*
 * 用途：校验ip地址的格式 输入：strIP：ip地址 返回：如果通过验证返回true,否则返回false；
 */
function f_check_IP(obj) {
	var re = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/; // 匹配IP地址的正则表达式
	if (re.test(validatorTrim(obj.value))) {
		if (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256
				&& RegExp.$4 < 256) {
			return true;
		}
	}
	return false;
}

/*
 * 用途：检查输入对象的值是否符合端口号格式 输入：str 输入的字符串 返回：如果通过验证返回true,否则返回false
 */
function f_check_port(obj) {
	if (!f_check_number(obj)) {
		return false;
	}
	if (obj.value < 65536) {
		return true;
	}
	return false;
}

/*
 * 功能：判断是否为日期(格式:yyyy年MM月dd日,yyyy-MM-dd,yyyy/MM/dd,yyyyMMdd)
 * 使用：f_check_date(obj) 返回：bool
 */
function f_check_date(obj) {
	var date = validatorTrim(obj.value);
	var format = obj.format || "all"; // 日期格式
	var year, month, day, datePat, matchArray;
	if (format == "yyyy-MM-dd" || format == "yyyy/MM/dd") {
		datePat = /^(\d{4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
	} else if (format == "yyyy年MM月dd日") {
		datePat = /^(\d{4})年(\d{1,2})月(\d{1,2})日$/;
	} else if (format == "yyyyMMdd") {
		datePat = /^(\d{4})(\d{2})(\d{2})$/;
	}
	if (format == "all") {
		matchArray = date.match(/^(\d{4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
		if (matchArray != null) {
			format = "yyyy-MM-dd"
		} else {
			matchArray = date.match(/^(\d{4})年(\d{1,2})月(\d{1,2})日$/);
			if (matchArray != null) {
				format = "yyyy年MM月dd日"
			} else {
				matchArray = date.match(/^(\d{4})(\d{2})(\d{2})$/);
				format = "yyyyMMdd";
			}
		}
		if (matchArray == null) {
			// 日期长度不对,或日期中有非数字符号
			return false;
		}
	} else {
		matchArray = date.match(datePat);
		if (matchArray == null) {
			// 日期长度不对,或日期中有非数字符号
			return false;
		}
	}
	if (/^(y{4})(-|\/)(M{1,2})\2(d{1,2})$/.test(format)) {
		year = matchArray[1];
		month = matchArray[3];
		day = matchArray[4];
	} else {
		year = matchArray[1];
		month = matchArray[2];
		day = matchArray[3];
	}
	if (month < 1 || month > 12) {
		// 月份应该为1到12的整数
		return false;
	}
	if (day < 1 || day > 31) {
		// 每个月的天数应该为1到31的整数
		return false;
	}
	if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
		// 该月不存在31号
		return false;
	}
	if (month == 2) {
		var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
		if (day > 29) {
			// 2月最多有29天
			return false;
		}
		if ((day == 29) && (!isleap)) {
			// 闰年2月才有29天
			return false;
		}
	}
	return true;
}

/*
 * 功能：校验的格式为yyyy年MM月dd日HH时mm分ss秒,yyyy-MM-dd HH:mm:ss,yyyy/MM/dd
 * HH:mm:ss,yyyyMMddHHmmss 使用：f_check_datetime(obj) 返回：bool
 */
function f_check_datetime(obj) {
	var datetime = validatorTrim(obj.value);
	var format = obj.format || "all";
	var datePat, matchArray, year, month, day, hour, minute, second;
	if (format == "yyyy-MM-dd HH:mm:ss" || format == "yyyy/MM/dd HH:mm:ss") {
		datePat = /^(\d{4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
	} else if (format == "yyyy年MM月dd日HH时mm分ss秒") {
		datePat = /^(\d{4})年(\d{1,2})月(\d{1,2})日(\d{1,2})时(\d{1,2})分(\d{1,2})秒$/;
	} else if (format == "yyyyMMddHHmmss") {
		datePat = /^(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})$/;
	}
	if (format == "all") {
		matchArray = datetime
				.match(/^(\d{4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/);
		if (matchArray != null) {
			format = "yyyy-MM-dd HH:mm:ss"
		} else {
			matchArray = datetime
					.match(/^(\d{4})年(\d{1,2})月(\d{1,2})日(\d{1,2})时(\d{1,2})分(\d{1,2})秒$/);
			if (matchArray != null) {
				format = "yyyy年MM月dd日HH时mm分ss秒"
			} else {
				matchArray = datetime
						.match(/^(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2})$/);
				format = "yyyyMMddHHmmss";
			}
		}
		if (matchArray == null) {
			// 日期长度不对,或日期中有非数字符号
			return false;
		}
	} else {
		matchArray = datetime.match(datePat);
		if (matchArray == null) {
			return false;
		}
	}

	if (/^(y{4})(-|\/)(M{1,2})\2(d{1,2}) (HH:mm:ss)$/.test(format)) {
		year = matchArray[1];
		month = matchArray[3];
		day = matchArray[4];
		hour = matchArray[5];
		minute = matchArray[6];
		second = matchArray[7];
	} else {
		year = matchArray[1];
		month = matchArray[2];
		day = matchArray[3];
		hour = matchArray[4];
		minute = matchArray[5];
		second = matchArray[6];
	}
	if (month < 1 || month > 12) {
		// 月份应该为1到12的整数
		return false;
	}
	if (day < 1 || day > 31) {
		// 每个月的天数应该为1到31的整数
		return false;
	}
	if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31) {
		// 该月不存在31号
		return false;
	}
	if (month == 2) {
		var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
		if (day > 29) {
			// 2月最多有29天
			return false;
		}
		if ((day == 29) && (!isleap)) {
			// 闰年2月才有29天
			return false;
		}
	}
	if (hour < 0 || hour > 23) {
		// 小时应该是0到23的整数
		return false;
	}
	if (minute < 0 || minute > 59) {
		// 分应该是0到59的整数
		return false;
	}
	if (second < 0 || second > 59) {
		// 秒应该是0到59的整数
		return false;
	}
	return true;
}

/**
 * 时间 提供两种格式 hh:mm hh:mm:ss
 */
function f_check_time(obj) {
	var format = obj.format || "all"; // 时间格式
	if (format == "hh:mm") {
		if (/^((\d|1\d|2[0-3]):([0-5]\d))?$/.test(validatorTrim(obj.value))) {
			return true;
		}
	} else if (format == "hh:mm:ss") {
		if (/^((\d|1\d|2[0-3]):([0-5]\d):([0-5]\d))?$/
				.test(validatorTrim(obj.value))) {
			return true;
		}
	} else {
		if (/^((\d|1\d|2[0-3]):([0-5]\d))?$/.test(validatorTrim(obj.value))) {
			return true;
		}
		if (/^((\d|1\d|2[0-3]):([0-5]\d):([0-5]\d))?$/
				.test(validatorTrim(obj.value))) {
			return true;
		}
	}
	return false;
}

/**
 * 网站地址校验
 */
function f_check_weburl(obj) {
	if (/^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/
			.test(validatorTrim(obj.value))) {
		return true;
	}
	return false;
}

/*
 * 用途：检查输入对象的值是否符合E-Mail格式
 */
function f_check_email(obj) {
	var myReg = /^([-_A-Za-z0-9\.]+)@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
	if (myReg.test(validatorTrim(obj.value))) {
		return true;
	}
	return false;
}

/*
 * 判断是否为实数，是则返回true,否则返回false
 */
function f_check_float(obj) {
	if (typeof(obj) == "object") {
		if (/^(\+|-)?\d+($|\.\d+$)/.test(validatorTrim(obj.value))
				&& f_check_floatIn(validatorTrim(obj.value))) {
			return true;
		}
	} else {
		if (/^(\+|-)?\d+($|\.\d+$)/.test(validatorTrim(obj))
				&& f_check_floatIn(validatorTrim(obj))) {
			return true;
		}
	}
	return false;
}

function f_check_floatIn(str) {
	if (str.indexOf(".") < 0) {
		if (str.length > 0) {
			if (str.substr(0, 1) == "0") {
				return false;
			}
		}
	}
	return true;
}

/*
 * 判断是否为整数，是则返回true,否则返回false
 */
function f_check_integer(obj) {
	if (typeof(obj) == "object") {
		if (/^(\+|-)?\d+$/.test(validatorTrim(obj.value))) {
			return true;
		}
	} else {
		if (/^(\+|-)?\d+$/.test(validatorTrim(obj))) {
			return true;
		}
	}
	return false;
}

/**
 * 校验浮点数,如果小数位不为空则必须符合规定的位数,默认2位
 */
function f_check_rolefloat(obj) {
	if (!f_check_float(obj)) {
		return false;
	} else {
		if (obj.value.indexOf(".") > 0) {
			var len = obj.format || 2;
			var str = validatorTrim(obj.value);
			if (str.substr(str.indexOf(".") + 1, str.length).length > len) {
				return false;
			}
		}
	}
	return true;
}

/*******************************************************************************
 * 校验字符长度
 ******************************************************************************/
function f_check_strlen(obj) {
	if (obj.to == null || obj.operator == null) {
		return false;
	}
	var to = obj.to;
	if (!f_check_integer(to.split("-")[0])) {
		return false;
	}
	var operator = obj.operator;
	if (to.split("-").length == 1) {
		switch (operator) {
			case "notEqual" :
				return (obj.value.length != to);
			case "greaterThan" :
				return (obj.value.length > to);
			case "greaterThanEqual" :
				return (obj.value.length >= to);
			case "lessThan" :
				return (obj.value.length < to);
			case "lessThanEqual" :
				return (obj.value.length <= to);
			case "equal" :
				return (obj.value.length == to);
			default :
				return false;
		}
	} else {
		var f = parseInt(to.split("-")[0]);
		var e = parseInt(to.split("-")[1]);
		switch (operator) {
			case "include" :
				if (obj.value.length >= f && obj.value.length <= e) {
					return true;
				}
				break;
			case "includeF" :
				if (obj.value.length >= f && obj.value.length < e) {
					return true;
				}
				break;
			case "includeE" :
				if (obj.value.length > f && obj.value.length <= e) {
					return true;
				}
				break;
			case "barring" :
				if (obj.value.length > f && obj.value.length < e) {
					return true;
				}
				break;
			default :
				return false;
		}
	}
	return false;
}

/*******************************************************************************
 * 校验整数大小
 ******************************************************************************/
function f_check_intlen(obj) {
	if (obj.to == null || obj.operator == null) {
		return false;
	}
	var to = obj.to;
	if (!f_check_integer(to.split("-")[0]) || !f_check_integer(obj)) {
		return false;
	}
	var operator = obj.operator;
	var intValue = parseInt(validatorTrim(obj.value));
	if (to.split("-").length == 1) {
		switch (operator) {
			case "notEqual" :
				return (intValue != to);
			case "greaterThan" :
				return (intValue > to);
			case "greaterThanEqual" :
				return (intValue >= to);
			case "lessThan" :
				return (intValue < to);
			case "lessThanEqual" :
				return (intValue <= to);
			case "equal" :
				return (intValue == to);
			default :
				return false;
		}
	} else {
		var f = parseInt(to.split("-")[0]);
		var e = parseInt(to.split("-")[1]);
		switch (operator) {
			case "include" :
				if (intValue >= f && intValue <= e) {
					return true;
				}
				break;
			case "includeF" :
				if (intValue >= f && intValue < e) {
					return true;
				}
				break;
			case "includeE" :
				if (intValue > f && intValue <= e) {
					return true;
				}
				break;
			case "barring" :
				if (intValue > f && intValue < e) {
					return true;
				}
				break;
			default :
				return false;
		}
	}
	return false;
}

/*******************************************************************************
 * 整数对象比较
 ******************************************************************************/
function f_check_intobj(obj) {
	if (obj.to == null || obj.operator == null) {
		return false;
	}
	var to = obj.to;
	var operator = obj.operator;
	if (!f_check_integer(obj)) {
		return false;
	}
	// 如果要比较对象为NULL或者值为空则不再比较
	if ($(to) == null || validatorTrim($F(to)).empty()) {
		return true;
	}
	// 如果要比较对象不符合整数规则不再比较
	if (!f_check_integer($(to))) {
		return true;
	}
	var intValue = parseInt(validatorTrim(obj.value));
	var toObjValue = parseFloat(validatorTrim($F(to)));
	switch (operator) {
		case "notEqual" :
			return (intValue != toObjValue);
		case "greaterThan" :
			return (intValue > toObjValue);
		case "greaterThanEqual" :
			return (intValue >= toObjValue);
		case "lessThan" :
			return (intValue < toObjValue);
		case "lessThanEqual" :
			return (intValue <= toObjValue);
		case "equal" :
			return (intValue == toObjValue);
		default :
			return false;
	}
	return false;
}

/*******************************************************************************
 * 校验文件类型
 ******************************************************************************/
function f_check_filetype(obj) {
	var arr = obj.value.split("\\");
	if (arr[arr.length - 1].split(".").length > 2) {
		return false;
	}
	var arrType = arr[arr.length - 1].split(".")[1].toLowerCase();
	if (obj.filetype == null) {
		return false;
	}
	var temp = obj.filetype.split(",");
	var flag = false;
	for (var i = 0; i < temp.length; i++) {
		if (arrType == temp[i]) {
			flag = true;
			break;
		}
	}
	if (flag == false) {
		return false;
	}
	return true;
}

/*******************************************************************************
 * 单选框校验
 ******************************************************************************/
function f_check_radio(obj) {
	var radioArr = $ES(obj.name);
	var flag = false;
	for (var i = 0; i < radioArr.length; i++) {
		if (radioArr[i].checked == true) {
			flag = true;
		}
	}
	return flag;
}

/*******************************************************************************
 * 复选框校验
 ******************************************************************************/
function f_check_checkbox(obj) {
	if (obj.to == null || obj.operator == null) {
		return false;
	}
	var to = obj.to;
	var operator = obj.operator;
	var checkBoxArr = $ES(obj.name);
	var flag = 0;
	for (var i = 0; i < checkBoxArr.length; i++) {
		if (checkBoxArr[i].checked == true) {
			flag++;
		}
	}
	if (to.split("-").length == 1) {
		switch (operator) {
			case "notEqual" :
				return (flag != to);
			case "greaterThan" :
				return (flag > to);
			case "greaterThanEqual" :
				return (flag >= to);
			case "lessThan" :
				return (flag < to);
			case "lessThanEqual" :
				return (flag <= to);
			case "equal" :
				return (flag == to);
			default :
				return false;
		}
	} else {
		var f = parseInt(to.split("-")[0]);
		var e = parseInt(to.split("-")[1]);
		switch (operator) {
			case "include" :
				if (flag >= f && flag <= e) {
					return true;
				}
				break;
			case "includeF" :
				if (flag >= f && flag < e) {
					return true;
				}
				break;
			case "includeE" :
				if (flag > f && flag <= e) {
					return true;
				}
				break;
			case "barring" :
				if (flag > f && flag < e) {
					return true;
				}
				break;
			default :
				return false;
		}
	}
	return false;
}

/*******************************************************************************
 * 校验浮点数大小
 ******************************************************************************/
function f_check_doulen(obj) {
	if (obj.to == null || obj.operator == null) {
		return false;
	}
	var to = obj.to;
	var operator = obj.operator;

	if (!f_check_float(to.split("-")[0]) || !f_check_rolefloat(obj)) {
		return false;
	}

	var floatValue = parseFloat(validatorTrim(obj.value));
	if (to.split("-").length == 1) {
		switch (operator) {
			case "notEqual" :
				return (floatValue != to);
			case "greaterThan" :
				return (floatValue > to);
			case "greaterThanEqual" :
				return (floatValue >= to);
			case "lessThan" :
				return (floatValue < to);
			case "lessThanEqual" :
				return (floatValue <= to);
			case "equal" :
				return (floatValue == to);
			default :
				return false;
		}
	} else {
		var f = parseFloat(to.split("-")[0]);
		var e = parseFloat(to.split("-")[1]);
		switch (operator) {
			case "include" :
				if (floatValue >= f && floatValue <= e) {
					return true;
				}
				break;
			case "includeF" :
				if (floatValue >= f && floatValue < e) {
					return true;
				}
				break;
			case "includeE" :
				if (floatValue > f && floatValue <= e) {
					return true;
				}
				break;
			case "barring" :
				if (floatValue > f && floatValue < e) {
					return true;
				}
				break;
			default :
				return false;
		}
	}
	return false;
}

/*******************************************************************************
 * 浮点数对象比较
 ******************************************************************************/
function f_check_douobj(obj) {
	if (obj.to == null || obj.operator == null) {
		return false;
	}
	var to = obj.to;
	var operator = obj.operator;
	if (!f_check_rolefloat(obj)) {
		return false;
	}
	// 如果要比较对象为NULL或者值为空则不再比较
	if ($(to) == null || validatorTrim($F(to)).empty()) {
		return true;
	}
	// 如果要比较对象不符合自然数则不再比较
	if (!f_check_rolefloat($(to))) {
		return true;
	}
	var douValue = parseFloat(validatorTrim(obj.value));
	var toObjValue = parseFloat(validatorTrim($F(to)));
	switch (operator) {
		case "notEqual" :
			return (douValue != toObjValue);
		case "greaterThan" :
			return (douValue > toObjValue);
		case "greaterThanEqual" :
			return (douValue >= toObjValue);
		case "lessThan" :
			return (douValue < toObjValue);
		case "lessThanEqual" :
			return (douValue <= toObjValue);
		case "equal" :
			return (douValue == toObjValue);
		default :
			return false;
	}
	return false;
}

/*******************************************************************************
 * 校验自然数大小
 ******************************************************************************/
function f_check_numlen(obj) {
	if (obj.to == null || obj.operator == null) {
		return false;
	}
	var to = obj.to;
	var operator = obj.operator;
	if (!f_check_numberze(to.split("-")[0]) || !f_check_numberze(obj)) {
		return false;
	}
	var numValue = parseInt(validatorTrim(obj.value));
	if (to.split("-").length == 1) {
		switch (operator) {
			case "notEqual" :
				return (numValue != to);
			case "greaterThan" :
				return (numValue > to);
			case "greaterThanEqual" :
				return (numValue >= to);
			case "lessThan" :
				return (numValue < to);
			case "lessThanEqual" :
				return (numValue <= to);
			case "equal" :
				return (numValue == to);
			default :
				return false;
		}
	} else {
		var f = parseInt(to.split("-")[0]);
		var e = parseInt(to.split("-")[1]);
		switch (operator) {
			case "include" :
				if (numValue >= f && numValue <= e) {
					return true;
				}
				break;
			case "includeF" :
				if (numValue >= f && numValue < e) {
					return true;
				}
				break;
			case "includeE" :
				if (numValue > f && numValue <= e) {
					return true;
				}
				break;
			case "barring" :
				if (numValue > f && numValue < e) {
					return true;
				}
				break;
			default :
				return false;
		}
	}
	return false;
}

/*******************************************************************************
 * 自然数对象比较
 ******************************************************************************/
function f_check_numobj(obj) {
	if (obj.to == null || obj.operator == null) {
		return false;
	}
	var to = obj.to;
	var operator = obj.operator;
	if (!f_check_numberze(obj)) {
		return false;
	}
	// 如果要比较对象为NULL或者值为空则不再比较
	if ($(to) == null || validatorTrim($F(to)).empty()) {
		return true;
	}
	// 如果要比较对象不符合自然数则不再比较
	if (!f_check_numberze($(to))) {
		return true;
	}
	var numValue = parseInt(validatorTrim(obj.value));
	var toObjValue = parseInt(validatorTrim($F(to)));
	switch (operator) {
		case "notEqual" :
			return (numValue != toObjValue);
		case "greaterThan" :
			return (numValue > toObjValue);
		case "greaterThanEqual" :
			return (numValue >= toObjValue);
		case "lessThan" :
			return (numValue < toObjValue);
		case "lessThanEqual" :
			return (numValue <= toObjValue);
		case "equal" :
			return (numValue == toObjValue);
		default :
			return false;
	}
	return false;
}

/*******************************************************************************
 * 年月对象比较
 ******************************************************************************/
function f_check_nyobj(obj) {
	if (obj.to == null || obj.operator == null || obj.format == null) {
		return false;
	}
	var to = obj.to;
	// 如果要比较对象为NULL或者值为空则不再比较
	if ($(to) == null || validatorTrim($F(to)).empty()) {
		return true;
	}
	var operator = obj.operator;
	var format = obj.format || "all";
	var datePat, matchArray, ny, toNy, year, month;
	if (format == "yyyy-MM" || format == "yyyy/MM") {
		datePat = /^(\d{4})(-|\/)(\d{1,2})$/;
	} else if (format == "yyyy年MM月") {
		datePat = /^(\d{4})年(\d{1,2})月$/;
	} else if (format == "yyyyMM") {
		datePat = /^(\d{4})(\d{2})$/;
	}

	if (format == "all") {
		matchArray = validatorTrim(obj.value).match(/^(\d{4})(-|\/)(\d{1,2})$/);
		if (matchArray != null) {
			format = "yyyy-MM"
		} else {
			matchArray = validatorTrim(obj.value).match(/^(\d{4})年(\d{1,2})月$/);
			if (matchArray != null) {
				format = "yyyy年MM月"
			} else {
				matchArray = validatorTrim(obj.value).match(/^(\d{4})(\d{2})$/);
				format = "yyyyMM";
			}
		}
		if (matchArray == null) {
			// 日期长度不对,或日期中有非数字符号
			return false;
		}
	} else {
		matchArray = validatorTrim(obj.value).match(datePat);
		if (matchArray == null) {
			// 日期长度不对,或日期中有非数字符号
			return false;
		}
	}

	if (/^(y{4})(-|\/)(M{1,2})$/.test(format)) {
		year = matchArray[1];
		month = matchArray[3];
	} else {
		year = matchArray[1];
		month = matchArray[2];
	}
	ny = parseInt(year + month);
	matchArray = validatorTrim($F(to)).match(datePat);
	if (matchArray == null) {
		// 比较对象的日期长度不对,或日期中有非数字符号
		return false;
	}
	if (/^(y{4})(-|\/)(M{1,2})$/.test(format)) {
		year = matchArray[1];
		month = matchArray[3];
	} else {
		year = matchArray[1];
		month = matchArray[2];
	}
	toNy = parseInt(year + month);
	switch (operator) {
		case "notEqual" :
			return (ny != toNy);
		case "greaterThan" :
			return (ny > toNy);
		case "greaterThanEqual" :
			return (ny >= toNy);
		case "lessThan" :
			return (ny < toNy);
		case "lessThanEqual" :
			return (ny <= toNy);
		case "equal" :
			return (ny == toNy);
		default :
			return false;
	}
	return false;
}

/*******************************************************************************
 * 判断对象是否存在
 ******************************************************************************/
function validatorIsexist(s) {
	try {
		eval(s);
	} catch (e) {
		return false;
	}
	return true;
}

/**
 * 根据TITLE弹出相应信息并设置光标选择
 */
function f_alert(obj, alertInfo) {
	var caption = obj.bt;
	if (caption == null) {
		caption = "";
	}
	alert(caption + "：" + alertInfo + "！");
	if (obj.tagName != "SELECT" && obj.type != "radio") {
		obj.select();
	}
	if (obj.type != "radio") {
		if (validatorIsVisible(obj) && validatorCheckPrVis(obj)) {
			obj.focus();
		}
	}
}

/**
 * 判断当前对象是否可见
 */
function validatorIsVisible(obj) {
	var visAtt, disAtt;
	try {
		disAtt = obj.style.display;
		visAtt = obj.style.visibility;
	} catch (e) {
	}
	if (disAtt == "none" || visAtt == "hidden")
		return false;
	return true;
}

/**
 * 判断当前对象及其父对象是否可见
 */
function validatorCheckPrVis(obj) {
	var pr = obj.parentNode;
	do {
		if (pr == undefined || pr == "undefined")
			return true;
		else {
			if (!validatorIsVisible(pr))
				return false;
		}
	} while (pr = pr.parentNode);
	return true;
}

/*******************************************************************************
 * 去掉字符串两端的空格,字符串若全为空格则返回一个空串
 */
function validatorTrim(mystring) {
	try {
		var temp_string = mystring;
		while (temp_string.substring(0, 1) == " ")
			temp_string = temp_string.substring(1);
		while (temp_string
				.substring(temp_string.length - 1, temp_string.length) == " ")
			temp_string = temp_string.substring(0, temp_string.length - 1);
		return temp_string;
	} catch (e) {
		alert(e.message);
		return "";
	}
}
