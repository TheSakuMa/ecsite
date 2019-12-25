<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧削除確認</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<div id="top">
			<p>商品一覧削除確認</p>
		</div>
		<div>
			<h3>すべての商品を削除します。よろしいですか？</h3>
			<s:form action="ItemListDeleteCompleteAction">
				<s:submit value="ＯＫ" />
			</s:form>
			<s:form action="ItemListAction">
				<s:submit value="キャンセル" />
			</s:form>
		</div>
	</div>
</body>
</html>