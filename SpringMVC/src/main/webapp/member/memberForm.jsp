<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입창</title>
</head>
<body>
	<form action="${contextPath }/member/memberInfo.do" name="frmMember" method="post">
		<h2 align="center">회원가입창</h2>
		<table align="center">
			<tr align="center">
				<td width="200" align="right">아이디 : </td>
				<td width="400"><input type="text" name="id"></td>
			</tr>
			<tr align="center">
				<td width="200" align="right">비밀번호 : </td>
				<td width="400"><input type="password" name="pwd"></td>
			</tr>
			<tr align="center">
				<td width="200" align="right">이름 : </td>
				<td width="400"><input type="text" name="name"></td>
			</tr>
			<tr align="center">
				<td width="200" align="right">이메일 : </td>
				<td width="400"><input type="text" name="email"></td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<input type="submit" value="가입하기">
					<input type="reset" value="다시입력">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>