var JsonReqData = {
	blhName : "Sqhd005BLH",
	passWord : "1",
	sjHm : "13770324905",
	xm : "张三",
	handleCode : "queryWxlist",

	data : {

		swglm : "320100002350110",
		YWSX_DM:"05030310",
		lsh:"22C2BEF2E2AFA07AE053C0A8660DA07A"
	}
};

$(document).ready(function() {


	// 示例二，详细模式JSON
	$.ajax({
		url : "/ZBAction.do",
		// 默认采用异步模式访问,async=false时timeout参数将无效
		async : true,
		dataType : "json",
		data : {
			jsonData : $.toJSON(JsonReqData)
		},
		// 查询类用get，提交类用post
		type : 'get',
		timeout : 500000,
		error : function(jqXHR, textStatus, errorThrown) {
			alert(1111);
		},
		success : function(responseText, textStatus, XMLHttpRequest) {
			alert(JSON.stringify(responseText.data));
			console.log(JSON.stringify(responseText.data));
		}
	});
});
