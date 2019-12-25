<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード再設定完了画面</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<div id="top">
			<p>パスワード再設定完了</p>
		</div>
		<div>
			<p>パスワードの再設定が完了致しました。</p>
			<p>
				<a href='<s:url action="GoLoginAction"/>'>ログインページへ</a>
			</p>
		</div>
	</div>
</body>
</html>