<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login画面</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<div id="top">
			<p>Login</p>
		</div>
		<div>
			<s:if test="errorMessage != null">
				<h3>
					<s:property value="errorMessage" />
				</h3>
			</s:if>
			<s:else>
				<h3>商品の購入・マイページの閲覧にはログインが必要です。</h3>
			</s:else>
			<s:form action="LoginAction" class="login-form">
				<s:if test="#session.saveUserIdFlg == true">
					<s:textfield name="loginUserId" placeholder="ユーザーID" value="%{#session.login_user_id}" autocomplete="off" />
				</s:if>
				<s:else>
					<s:textfield name="loginUserId" placeholder="ユーザーID" autocomplete="off" />
				</s:else>
				<br>
				<s:password name="loginPassword" placeholder="パスワード" autocomplete="off" />
				<br>
				<s:checkbox name="saveUserIdFlg" checked="checked" id="saveUserIdFlg" />
				<s:label for="saveUserIdFlg" value="ユーザーID保存" />
				<s:submit value="ログイン" />
			</s:form>
			<br>
			<s:form action="UserCreateAction">
				<s:submit value="新規ユーザー登録"/>
			</s:form>
			<div id="text-link">
				<p>
					Homeへ戻る場合は<a href='<s:url action="GoHomeAction"/>'>こちら</a>
				</p>
				<p>
					パスワードの再設定は<a href='<s:url action="ResetPasswordAction"/>'>こちら</a>
				</p>
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>