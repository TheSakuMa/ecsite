<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報削除確認画面</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<div id="top">
			<p>ユーザー情報削除確認</p>
		</div>
		<div>
			<p>ユーザー情報を削除してもよろしいですか？</p>
			<s:form action="UserDeleteCompleteAction">
				<s:hidden name="id" value="%{id}" />
				<s:submit value="ＯＫ" />
			</s:form>
			<s:form action="UserDetailsAction">
				<s:hidden name="id" value="%{id}" />
				<s:submit value="キャンセル" />
			</s:form>
		</div>
	</div>
</body>
</html>