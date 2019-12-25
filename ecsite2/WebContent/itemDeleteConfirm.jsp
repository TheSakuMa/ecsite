<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品情報削除確認画面</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<header>
		<div class="home-header-title">ECSITE2</div>
	</header>
	<div id="main">
		<div id="top">
			<p>商品情報削除確認</p>
		</div>
		<div>
			<h2>削除してもよろしいですか？</h2>
			<s:form action="ItemDeleteCompleteAction">
				<s:hidden name="id" value="%{id}" />
				<s:submit value="ＯＫ" />
			</s:form>
			<s:form action="ItemDetailsAction">
				<s:hidden name="id" value="%{id}" />
				<s:submit value="キャンセル" />
			</s:form>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>