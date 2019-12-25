<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者用商品情報詳細画面</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<header>
		<div class="home-header-title">ECSITE2</div>
	</header>
	<div id="main">
		<div id="top">
			<p>管理者商品情報詳細画面</p>
		</div>
		<div>
			<s:if test="message != ''">
				<s:property value="message" />
			</s:if>
			<table border="1" id="admin-item-details">
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
				<tr>
					<td>在庫数</td>
					<td><s:property value="#session.item_stock" /><span> 個</span></td>
				</tr>
				<tr>
					<td>登録日</td>
					<td><s:property value="#session.insert_date" /></td>
				</tr>
				<s:if test="#session.item_detail != null">
					<tr>
						<td>商品説明</td>
						<td><s:property value="#session.item_detail" /></td>
					</tr>
				</s:if>
				<tr>
					<td>商品画像</td>
					<td><img
						src='<s:property value="#session.image_path"/>/<s:property value="#session.image_name"/>'></td>
				</tr>
			</table>
			<div>
				<s:form action="ItemDeleteConfirmAction">
					<s:hidden name="id" value="%{id}" />
					<s:submit value="削除" />
				</s:form>
				<s:form action="ItemListAction">
					<s:hidden name="id" value="%{id}" />
					<s:submit value="戻る" />
				</s:form>
			</div>
		</div>
	</div>
</body>
</html>