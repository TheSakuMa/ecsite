<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー一覧削除確認</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<header>
		<div class="home-header-title">ECSITE2</div>
	</header>
	<div id="main">
		<div id="top">
			<p>ユーザー一覧削除確認</p>
		</div>
		<div>
			<h3>管理者ユーザー以外を削除します。よろしいですか？</h3>
			<s:form action="UserListDeleteCompleteAction">
				<s:submit value="ＯＫ" />
			</s:form>
			<s:form action="UserListAction">
				<s:submit value="キャンセル" />
			</s:form>
		</div>
	</div>
</body>
</html>