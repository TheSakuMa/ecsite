<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<header>
		<div class="home-header-title">ECSITE2</div>
	</header>
	<div id="main">
		<div id="top">
			<p>商品一覧</p>
		</div>
		<div>
			<s:if test="itemInfoList == null">
				<h3>商品情報はありません。</h3>
			</s:if>
			<s:else>
				<table border="1">
					<tr>
						<th>ID</th>
						<th>商品名</th>
						<th>値段</th>
						<th>在庫数</th>
						<th>登録日</th>
						<th>詳細</th>
					</tr>
					<s:iterator value="itemInfoList">
						<tr>
							<td><s:property value="id" /></td>
							<td><s:property value="itemName" /></td>
							<td><s:property value="price" /><span> 円</span></td>
							<td><s:property value="stock" /><span> 個</span></td>
							<td><s:property value="insert_date" /></td>
							<td>
								<s:form action="AdminItemDetailsAction">
									<s:hidden name="id" value="%{id}" />
									<s:submit value="詳細" />
								</s:form>
							</td>
						</tr>
					</s:iterator>
				</table>
				<div id="text-right">
					<s:form action="ItemListDeleteConfirmAction">
						<s:submit value="全件削除" />
					</s:form>
				</div>
			</s:else>
			<s:if test="message != null">
				<h3>
					<s:property value="message" />
				</h3>
			</s:if>
			<p>
				管理者TOPに戻る場合は<a href='<s:url action="AdminAction"/>'>こちら</a>
			</p>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>