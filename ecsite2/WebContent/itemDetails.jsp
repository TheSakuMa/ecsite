<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品情報詳細画面</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<div id="top">
			<p>商品情報詳細</p>
		</div>
		<div>
			<s:if test="message != ''">
				<s:property value="message" />
			</s:if>
			<div>
				<img
					src='<s:property value="#session.image_path"/>/<s:property value="#session.image_name"/>'
					id="item-details-img">
			</div>
			<table border="1" id="item-details-table">
				<tr>
					<td>商品ID</td>
					<td><s:property value="#session.id" /></td>
				</tr>
				<tr>
					<td>商品名</td>
					<td><s:property value="#session.item_name" /></td>
				</tr>
				<tr>
					<td>発売元</td>
					<td><s:property value="#session.item_distributor" /></td>
				</tr>
				<tr>
					<td>値段</td>
					<td><s:property value="#session.item_price" /><span> 円</span></td>
				</tr>
				<tr>
					<td>カテゴリー</td>
					<td><s:property value="#session.categoryName" /></td>
				</tr>
				<s:if test="#session.item_detail != null">
					<tr>
						<td>商品説明</td>
						<td><s:property value="#session.item_detail" /></td>
					</tr>
				</s:if>
				<tr>
					<td>登録日</td>
					<td><s:property value="#session.insert_date" /></td>
				</tr>
			</table>
			<div id="details-to-cart">
				<s:form action="AddtoCartAction">
					<div>
						<span>購入個数</span> <select name="count">
							<option value="1" selected="selected">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
					</div>
					<br>
					<s:hidden name="id" value="%{#session.id}" />
					<s:submit value="カートに入れる" />
				</s:form>
				<s:form action="HomeAction">
					<s:hidden name="id" value="%{#session.id}" />
					<s:submit value="戻る" />
				</s:form>
			</div>
		</div>
	</div>
</body>
</html>