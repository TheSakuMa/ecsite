<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<header>
		<div class="home-header-title">ECSITE2</div>
	</header>
	<div id="main">
		<div id="top">
			<p>商品登録</p>
		</div>
		<div>
		<s:if test="errorMessage != ''">
			<s:property value="errorMessage" escape="false" />
		</s:if>
			<s:form action="ItemCreateConfirmAction" method="post">
				<div>
					<label for="itemName">商品名:</label>
					<input type="text" name="itemName" id="itemName" />
				</div>
				<div>
					<label for="itemDistributor">発売元:</label>
					<input type="text" name="itemDistributor" id="itemDistributor">
				</div>
				<div>
					<label for="category">カテゴリー:</label>
					<s:select name="categoryName" list="#session.mCategoryList" listValue="categoryName" listKey="categoryName" id="categoryName" />
				</div>
				<div>
					<label for="price">値段:</label>
					<input type="text" name="price" id="price" /><span> 円</span>
				</div>
				<div>
					<label for="stock">在庫数:</label>
					<input type="text" name="stock" id="stock" /><span> 個</span>
				</div>
				<div>
					<s:submit value="登録" />
				</div>
			</s:form>
			<div>
				<span>前画面に戻る場合は<a href='<s:url action="AdminAction"/>'>こちら</a></span>
			</div>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>