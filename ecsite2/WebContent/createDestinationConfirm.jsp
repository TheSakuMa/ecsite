<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CreateDestinationConfirm画面</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div id="main">
		<div id="top">
			<p>宛先情報登録確認</p>
		</div>
		<div>
			<p class="confirm-para">以下の内容でよろしいですか？</p>
			<table>
				<tr>
					<td>宛先姓</td><td><s:property value="#session.familyName"/></td>
				</tr>
				<tr>
					<td>宛先名</td><td><s:property value="#session.firstName"/></td>
				</tr>
				<tr>
					<td>宛先住所</td><td><s:property value="#session.address"/></td>
				</tr>
				<tr>
					<td>宛先電話番号</td><td><s:property value="#session.phoneNumber"/></td>
				</tr>
			</table>
			<s:form action="CreateDestinationCompleteAction">
				<s:submit value="登録"/>
			</s:form>
			<input type="button" onClick="history.go(-1)" value="戻る">
		</div>
	</div>
</body>
</html>