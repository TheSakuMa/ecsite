<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録確認</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<header>
		<div class="home-header-title">ECSITE2</div>
	</header>
	<div id="main">
		<div id="top">
			<p>商品登録確認</p>
		</div>
		<div>
			<h3>以下の内容でよろしいですか?</h3>
			<s:form action="ItemCreateCompleteAction">
				<div>
					<label>商品名:</label>
					<s:property value="#session.itemName" escape="false" />
				</div>
				<div>
					<label>発売元:</label>
					<s:property value="#session.itemDistributor" escape="false" />
				</div>
				<div>
					<label>カテゴリー:</label>
					<s:property value="#session.categoryName"/>
				</div>
				<div>
					<label>値段:</label>
					<s:property value="#session.price" escape="false" /><span> 円</span>
				</div>
				<div>
					<label>在庫数:</label>
					<s:property value="#session.stock" escape="false" /><span> 個</span>
				</div>
				<div>
					<s:submit value="完了" />
				</div>
			</s:form>
		</div>
	</div>
</body>
</html>