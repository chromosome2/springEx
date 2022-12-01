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
<title>로그인 칭</title>
</head>
<body>
	<form action="${contextPath }/member/login.do" method="post" name="frmLogin">
		<table align="center">
			<tr align="center">
				<td>
					아이디 : <input type="text" name="id">
				</td>
			</tr>
			<tr align="center">
				<td>
					비밀번호 : <input type="password" name="pwd">
				</td>
			</tr>
			<tr align="center">
				<td>
					<input type="submit" value="로그인">
					<input type="reset" value="다시입력">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>