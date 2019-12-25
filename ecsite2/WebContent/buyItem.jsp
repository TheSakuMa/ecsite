<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BuyItem画面</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<s:if test="#session.loginedFlg == 1">
			<s:form action="MyPageAction" id="top-menu" class="mypage-btn-wrapper">
			  <s:submit value="マイページ"/>
			</s:form>
		</s:if>
		<div id="top">
			<p>商品一覧</p>
		</div>
		<div>
			<s:if test="emptyListFlg == true">
				<p>
					「<s:property value="keywords" />」に一致する商品は見つかりませんでした。
				</p>
			</s:if>
			<s:if test="message != ''">
				<!-- 在庫数が購入しようとする個数を下回る場合にメッセージを表示 -->
				<s:property value="message" escape="false" />
			</s:if>
			<s:iterator value="#session.itemInfoList">
				<div id="item-wrapper">
					<div id="item-img">
						<img
							src='<s:property value="image_path"/>/<s:property value="image_name"/>'
							onclick="location.href='<s:url action="ItemDetailsAction"><s:param name="id" value="%{id}"/></s:url>'">
					</div>
					<s:form action="AddtoCartAction">
						<table>
							<tr>
								<td><span>ID</span></td>
								<td><s:property value="id" /></td>
							</tr>
							<tr>
								<td><span>商品名</span></td>
								<td><s:property value="itemName" /></td>
							</tr>
							<tr>
								<td><span>値段</span></td>
								<td><s:property value="price" /><span>円</span></td>
							</tr>
							<tr>
								<td><span>購入個数</span></td>
								<td><select name="count">
										<option value="1" selected="selected">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
								</select></td>
							</tr>
							<tr>
								<td><s:hidden name="id" value="%{id}" /> <s:submit value="カートに入れる" /></td>
							</tr>
						</table>
					</s:form>
				</div>
			</s:iterator>
		</div>
		<div>
			<p>
				Home画面に戻る場合は<a href='<s:url action="GoHomeAction"/>'>こちら</a>
			</p>
			<p>
				マイページは<a href='<s:url action="MyPageAction"/>'>こちら</a>
			</p>
		</div>
		<div>
			<s:form action="CartAction">
				<s:submit value="カートを見る" />
			</s:form>
		</div>
	</div>
	<div id="footer">
		<!-- jsp名を Action に渡すための記述 -->
		<s:hidden name="jspName" id="jspName" value="buyItem.jsp" />
	</div>
	<!-- jsp名を Action に渡すための処理 -->
	<script type="text/javascript" src="./js/refererBranch.js"></script>
</body>
</html>