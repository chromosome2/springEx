<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
</head>
<body>
	<h2 align="center">회원정보</h2>
	<table border="1" align="center">
		<tr align="center" bgcolor="lightgreen">
			<th>아이디</th><th>비밃번호</th><th>이름</th><th>이메일</th>
		</tr>
		<tr align="center">
			<td>${id }</td>
			<td>${pwd }</td>
			<td>${name }</td>
			<td>${email }</td>
		</tr>
	</table>
</body>
</html>