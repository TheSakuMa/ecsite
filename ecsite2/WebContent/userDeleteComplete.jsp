<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報削除完了画面</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<div id="top">
			<p>ユーザー情報削除完了</p>
		</div>
		<div>
			<h3>削除が完了致しました。</h3>
			<div>
				<a href='<s:url action="AdminAction"/>'>管理者TOPへ</a>
			</div>
		</div>
	</div>
</body>
</html>