<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<form action="login.me" name="loginform" method="post">
<center>
<form>
	<table border="1">
			<tr> 
			<td colspan="2" align="center">
				<b><font size="5">로그인 페이지</font></b>
			</td>
		</tr>
		<tr><td>아이디:</td><td><input type="text" name="hakbun"/></td></tr>
		<tr><td>비밀번호:</td><td><input type="password" name="password"/></td></tr>
		<tr>
			<td align="center" colspan="2">
			<a href="javascript:loginform.submit()">로그인</a>&nbsp;&nbsp;
			<a href="./joinform.me">회원가입</a>
			</td>
		</tr>	
	</table>
</form>
</center>
</form>
</body>
</html>