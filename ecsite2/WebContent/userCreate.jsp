<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserCreate画面</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<div id="top">
			<p>UserCreate</p>
		</div>
		<div>
		<s:if test="errorMessage != ''">
			<s:property value="errorMessage" escape="false" />
		</s:if>
			<s:form action="UserCreateConfirmAction">
				<label for="loginUserId">ユーザーID: </label>
				<s:textfield name="loginUserId" id="loginUserId" />
				<br>
				<label for="loginPassword">パスワード:  </label>
				<s:password name="loginPassword" id="loginPassword" />
				<br>
				<label for="userName">ユーザー名: </label>
				<s:textfield name="userName" id="userName" />
				<br>
				<s:submit value="登録" />
			</s:form>
			<div>
			<s:if test="#session.userCreateByAdminFlg == 1">
				<span>前画面に戻る場合は</span> <a href='<s:url action="AdminAction"/>'>こちら</a>
			</s:if>
			<s:else>
				<span>前画面に戻る場合は</span> <a href='<s:url action="HomeAction"/>'>こちら</a>
			</s:else>
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>