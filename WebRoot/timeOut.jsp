<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<title></title>
</head>
<body>
	<%
		request.getSession().removeAttribute(
				request.getParameter("sessionId"));
	%>
	<script>
		window.location.href = "/LoginAction.do";
	</script>
</body>
</html>