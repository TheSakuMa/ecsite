<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品情報削除完了画面</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<header>
		<div class="home-header-title">ECSITE2</div>
	</header>
	<div id="main">
		<div id="top">
			<p>商品情報削除完了画面</p>
		</div>
		<div>
			<h2>削除が完了致しました。</h2>
			<div>
				<a href='<s:url action="AdminAction"/>'>管理者TOPへ</a>
			</div>
		</div>
	</div>
</body>
</html>