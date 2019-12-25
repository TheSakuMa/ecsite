<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー一覧削除完了</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<header>
		<div class="home-header-title">ECSITE2</div>
	</header>
	<div id="main">
		<div id="top">
			<p>ユーザー一覧削除完了</p>
		</div>
		<div>
			<h3>削除が完了致しました。</h3>
			<div>
				<p>
					<a href='<s:url action="AdminAction"/>'>管理者TOPへ</a>
				</p>
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>