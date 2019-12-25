<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カート</title>
<link rel="stylesheet" href="./css/style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
</script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<div id="top">
			<p>カート</p>
		</div>
		<div>
			<s:if test="#session.cartList != null">
				<s:form action="EmptyCartAction" id="each_del">
					<table border="1">
						<tr>
							<th>商品名</th>
							<th>値段</th>
							<th>個数</th>
							<th>小計</th>
							<th>操作</th>
						</tr>
						<s:iterator value="#session.cartList">
							<tr>
								<td><s:property value="itemName" /></td>
								<td><s:property value="itemPrice" /></td>
								<td><s:property value="totalCount" /></td>
								<td><s:property value="itemPrice * totalCount" /></td>
								<td><input type="checkbox" name="checkList" value="<s:property value='itemId'/>" /></td>
							</tr>
						</s:iterator>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><strong>合計</strong></td>
							<td><s:property value="#session.sum" /></td>
						</tr>
					</table>
					<s:hidden name="eachFlg" value="1" />
				</s:form>
				<s:form action="BuyItemAction">
					<label>支払い方法</label>
					<input type="radio" name="pay" value="1" checked="checked">現金
          <input type="radio" name="pay" value="2">クレジットカード
          <br>
          <s:submit value="購入" />
				</s:form>
				<!-- 個別削除ボタン -->
				<button id="del_btn">選択した商品をカートから削除する</button>
				<!-- 全体削除フォーム -->
				<s:form action="EmptyCartAction">
					<s:hidden name="eachFlg" value="0" />
					<s:submit value="カートを空にする" />
				</s:form>
			</s:if>
			<!-- 削除結果メッセージからNULLでない場合 -->
			<s:if test="deleteMessage != null">
				<s:property value="deleteMessage" />
			</s:if>
			<!-- 削除結果メッセージが NULL 且つcartListがNULLのとき -->
			<s:elseif test="#session.cartList == null">
				<p>
					<s:property value="emptyMessage" />
				</p>
			</s:elseif>
			<s:form action="HomeAction">
				<s:submit value="戻る" />
			</s:form>
		</div>
	</div>
	<div>
		<!-- jsp名を SearchItemAction に渡すための記述 -->
		<s:hidden name="jspName" id="jspName" value="cart.jsp" />
	</div>
	<!-- jsp名を SearchItemAction に渡すための処理 -->
	<script type="text/javascript" src="./js/refererBranch.js"></script>
	<!-- form 要素外の button を押下したら submit するためのプログラム -->
	<script type="text/javascript">
  	$(function() {
        $('#del_btn').click(function() {
        	$('#each_del').submit();
        });
	});
  </script>
</body>
</html>