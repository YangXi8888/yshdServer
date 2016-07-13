/**
 * AJAX批量上传文件，需要修改启动参数编码
 *  -Dfile.encoding=utf-8
 */
$.fn.ajaxfileupload = function(options) {
	var settings = {
		params : {},
		formName : "",
		action : '',
		fileDom : "",
		onStart : function() {
		},
		checkData : function() {
		},
		getFormData : function() {
		},
		onComplete : function(response) {
		}
	};
	if (options) {
		$.extend(settings, options);
	}
	var $element = $("#" + settings.fileDom);
	var frame_id = 'ajaxUploader-iframe-'
			+ Math.round(new Date().getTime() / 1000)
	$('body').after(
			'<iframe width="0" height="0" style="display:none;" name="'
					+ frame_id + '" id="' + frame_id + '"/>');
	$('#' + frame_id).get(0).onload = function() {
		handleResponse(this, $element);
	};
	$element
			.wrap(
					function() {
						return '<form action="'
								+ settings.action
								+ '" method="POST" enctype="multipart/form-data" target="'
								+ frame_id + '" name="' + settings.formName
								+ '" />'
					}).before(
					function() {
						var key, html = '';
						for (key in settings.params) {
							var paramVal = settings.params[key];
							if (typeof paramVal === 'function') {
								paramVal = paramVal();
							}
							html += "<input type='hidden' name='" + key
									+ "' value='" + paramVal + "'  />";
						}
						return html;
					});
	var ret = settings.onStart.apply($element, [ settings.params ]);
	if (ret !== false) {
		var formDataArr = settings.getFormData();
		for (var i = 0; i < formDataArr.length; i++) {
			$element.parent('form').find(
					"input[name='" + formDataArr[i].name + "']")[0].value = formDataArr[i].value;
		}
		$element.parent('form').submit(function(e) {
			e.stopPropagation();
		}).submit();
	}
	var handleResponse = function(loadedFrame, element) {
		var response, responseStr = $(loadedFrame).contents().text();
		try {
			response = JSON.parse(responseStr);
		} catch (e) {
			response = responseStr;
		}
		element.siblings().remove();
		element.unwrap();
		settings.onComplete.apply(element, [ response, settings.params ]);
	};
}
