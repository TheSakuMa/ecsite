<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home画面</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<header>
		<div class="home-header-title">ECSITE2</div>
	</header>
	<div id="main">
		<div id="top">
			<p>Home</p>
		</div>
		<div id="text-center">
			<s:if test="searchErrorMessage != null">
				<p class="searchErrorMessage">
					<s:property value="searchErrorMessage" />
				</p>
			</s:if>
			<s:form action="SearchItemAction" class="home-search-form">
				<s:if test="#session.mCategoryList != null && #session.mCategoryList.size() > 0">
					<s:select name="categoryId" list="#session.mCategoryList" listValue="categoryName" listKey="categoryId" id="categoryId" />
				</s:if>
				<br>
				<s:textfield name="keywords" placeholder="検索ワード" />
				<br>
				<s:hidden name="jspName" id="header_jspName" value="" />
				<s:submit value="商品検索" />
			</s:form>
			<div>
				<p>
					<a href='<s:url action="HomeAction"/>'>全商品を見る</a>
				</p>
			</div>
			<s:if test="#session.loginedFlg != 1">
				<p>
					<a href='<s:url action="GoLoginAction"/>'>ログイン</a>
				</p>
			</s:if>
			<s:else>
				<p>
					ログアウトする場合は<a href='<s:url action="LogoutAction"/>'>こちら</a>
				</p>
			</s:else>
		</div>
		<s:if test="#session.admin_flg == 1">
			<s:form action="AdminAction">
				<s:submit value="管理者" />
			</s:form>
		</s:if>
	</div>
	<!-- jsp名を Action に渡すための記述 -->
	<s:hidden name="jspName" id="jspName" value="home.jsp" />
	<!-- jsp名を Action に渡すための処理 -->
	<script type="text/javascript" src="./js/refererBranch.js"></script>
</body>
</html>