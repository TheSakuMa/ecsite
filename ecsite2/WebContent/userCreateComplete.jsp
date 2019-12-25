<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserCreateComplete画面</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<div id="top">
			<p>UserCreateComplete</p>
		</div>
		<div>
			<h3>ユーザの登録が完了致しました。</h3>
			<div>
				<s:if test="userCreateByAdminFlg == 1">
					<a href='<s:url action="AdminAction"/>'>管理者画面TOPへ</a>
				</s:if>
				<s:else>
					<a href='<s:url action="GoLoginAction"/>'>ログインへ</a>
				</s:else>
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>