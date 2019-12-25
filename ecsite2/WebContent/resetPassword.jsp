<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログインPASS再設定画面</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<div id="top">
			<p>ログインPASS再設定</p>
		</div>
		<s:if test="errorMessage != null">
			<s:property value="errorMessage" />
		</s:if>
		<s:form action="ResetPasswordCompleteAction" onsubmit="return check()">
			<s:if test="#session.saveUserIdFlg">
				<s:textfield name="loginUserId" value="%{#session.login_user_id}"
					label="ユーザーID" autocomplete="off" />
			</s:if>
			<s:else>
				<s:textfield name="loginUserId" placeholder="ユーザーID" label="ユーザーID"
					autocomplete="off" />
			</s:else>
			<s:password name="loginPassword" placeholder="現在のログインPASS"
				label="現在のログインPASS" />
			<s:password name="newLoginPassword" placeholder="新しいログインPASS"
				label="新しいログインPASS" />
			<s:password name="confirmNewLoginPassword"
				placeholder="新しいログインPASS（確認）" label="新しいログインPASS（確認）" />
			<s:submit value="確認" />
		</s:form>
	</div>
	<script type="text/javascript">
    function check() {
    	if(window.confirm('ログインPASSを変更してもよろしいですか？')) {
    		return true;
    	} else {
    		return false;
    	}
    }
  </script>
</body>
</html>