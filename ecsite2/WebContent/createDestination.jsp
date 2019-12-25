<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CreateDestination画面</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div id="main">
		<div id="top">
			<p>宛先情報登録</p>
		</div>
		<s:form action="CreateDestinationConfirmAction">
			<label for="familyName">宛先姓:</label>
			<s:textfield name="familyName" id="familyName"/>
			<br>
			<label for="firstName">宛先名:</label>
			<s:textfield name="firstName" id="firstName"/>
			<br>
			<label for="address">宛先住所:</label>
			<s:textfield name="address" id="address"/>
			<br>
			<label for="phoneNumber">宛先電話番号:</label>
			<input type="tel" name="phoneNumber" minlength="11" maxLength="16">
			<br>
			<s:submit value="確認"/>
		</s:form>
		<s:form action="BuyItemAction">
			<s:submit value="戻る"/>
		</s:form>
	</div>
</body>
</html>