<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報詳細画面</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<div id="top">
			<p>ユーザー情報詳細画面</p>
		</div>
		<div>
			<table border="1">
				<tr>
					<td>ID</td>
					<td><s:property value="session.uid" /></td>
				</tr>
				<tr>
					<td>ログインID</td>
					<td><s:property value="session.login_id" /></td>
				</tr>
				<tr>
					<td>ログインPASS</td>
					<td><s:property value="session.login_pass" /></td>
				</tr>
				<tr>
					<td>ユーザー名</td>
					<td><s:property value="session.user_name" /></td>
				</tr>
				<tr>
					<td>登録日</td>
					<td><s:property value="session.insert_date" /></td>
				</tr>
			</table>
		</div>
		<div>
			<s:form action="UserDeleteConfirmAction">
				<s:hidden name="id" value="%{id}" />
				<s:submit value="削除" />
			</s:form>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>