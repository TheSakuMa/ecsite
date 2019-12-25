<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー一覧</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<header>
		<div class="home-header-title">ECSITE2</div>
	</header>
	<div id="main">
		<div id="top">
			<p>ユーザー一覧</p>
		</div>
		<div>
			<s:if test="message != null">
				<h3>削除に失敗しました。</h3>
			</s:if>
			<s:if test="userInfoList == null">
				<h3>ユーザー情報がありません。</h3>
			</s:if>
			<s:else>
				<table border="1">
					<tr>
						<th>ID</th>
						<th>ログインID</th>
						<th>ログインPASS</th>
						<th>ユーザー名</th>
						<th>登録日</th>
						<th>詳細</th>
					</tr>
					<s:iterator value="userInfoList">
						<tr>
							<td><s:property value="id" /></td>
							<td><s:property value="loginId" /></td>
							<td><s:property value="loginPassword" /></td>
							<td><s:property value="userName" /></td>
							<td><s:property value="insert_date" /></td>
							<td><s:form action="UserDetailsAction">
									<s:hidden name="id" value="%{id}" />
									<s:submit value="詳細" />
								</s:form></td>
						</tr>
					</s:iterator>
				</table>
				<div id="text-right">
					<s:form action="UserListDeleteConfirmAction">
						<s:submit value="削除" />
					</s:form>
				</div>
			</s:else>
		</div>
		<div>
			<p>
				管理者TOPに戻る場合は<a href='<s:url action="AdminAction"/>'>こちら</a>
			</p>
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>