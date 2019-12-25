<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BuyItemConfirm画面</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<div id="top">
			<p>BuyItemConfirm</p>
		</div>
		<div>
			<s:form>
				<h3>内容</h3>
				<table border="1">
					<tr>
						<th>商品名</th>
						<th>値段</th>
						<th>個数</th>
						<th>小計</th>
					</tr>
					<s:iterator value="#session.cartList">
						<tr>
							<td><s:property value="itemName" /></td>
							<td><s:property value="itemPrice" /><span>円</span></td>
							<td><s:property value="totalCount" /><span>個</span></td>
							<td><s:property value="itemPrice * totalCount" /><span>円</span></td>
						</tr>
					</s:iterator>
				</table>
				<table>
					<tr>
						<td><strong>合計</strong></td>
						<td><s:property value="#session.sum" /></td>
					</tr>
				</table>
				<p>
					支払い方法：
					<s:property value="#session.pay" />
				</p>
				<h3>宛先選択</h3>
				<s:if test="destinationList != null && destinationList.size() > 0">
				<table>
					<tr>
						<th>姓</th><th>名</th><th>住所</th><th>電話番号</th>
					</tr>
					<s:iterator value="destinationList">
						<tr>
							<td><input type="radio" name="destinationId" value="%{destinationId}"></td>
							<td><s:property value="familyName"/></td>
							<td><s:property value="firstName"/></td>
							<td><s:property value="address"/></td>
							<td><s:property value="phoneNumber"/></td>
						</tr>
					</s:iterator>
				</table>
				</s:if>
				<s:else>
				<p>宛先情報はまだ登録されていません。</p>
				</s:else>
				<!-- jQueryを使って、同一フォーム上で押下するボタンに依って設定される action 属性の値を変える -->
				<input type="button" value="戻る" onclick="submitAction('CartAction')">
				<input type="button" value="完了" onclick="submitAction('BuyItemConfirmAction')">
			</s:form>
			<s:form action="CreateDestinationAction">
				<s:submit value="新規宛先登録"/>
			</s:form>
		</div>
		<div>
			<p>
				前画面に戻る場合は<a href='<s:url action="GoHomeAction"/>'>こちら</a>
			</p>
			<p>
				マイページは<a href='<s:url action="MyPageAction"/>'>こちら</a>
			</p>
		</div>
	</div>
	<div id="footer"></div>

	<script type="text/javascript">
    function submitAction(url) {
    	$('form').attr('action', url);
    	$('form').submit();
    }
  </script>
	<script type="text/javascript" src="./js/refererBranch.js"></script>
</body>
</html>