<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>画像ファイルアップロードテスト</title>
</head>
<body>
	<s:property value="fileUpload" />
	<br>
	<s:property value="fileUploadFileName" />
	<br>
	<s:property value="fileUploadContentType" />
</body>
</html>