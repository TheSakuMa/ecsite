<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者画面</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<header>
		<div class="home-header-title">ECSITE2</div>
	</header>
	<div id="main">
		<div id="top">
			<p>管理者画面</p>
		</div>
		<div>
			<div>
				<p>商品</p>
				<s:form action="ItemCreateAction">
					<s:submit value="新規登録" />
				</s:form>
				<s:form action="ItemListAction">
					<s:submit value="一覧" />
				</s:form>
			</div>
			<div>
				<p>ユーザー</p>
				<s:form action="UserCreateAction">
					<s:hidden name="userCreateByAdminFlg" value="1"/>
					<s:submit value="新規登録" />
				</s:form>
				<s:form action="UserListAction">
					<s:submit value="一覧" />
				</s:form>
			</div>
		</div>
		<div>
			<a href='<s:url action="GoHomeAction"/>'>Homeへ</a>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>