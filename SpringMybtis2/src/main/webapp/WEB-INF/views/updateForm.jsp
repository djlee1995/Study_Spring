<%@page import="com.spring.mybatis.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
MemberVO member = (MemberVO)request.getAttribute("member");
%>
<form action="update.do" name="updateForm" method="post">
		<center>
			<table border="1" width=300>
				<tr align="center">
					<td colspan="2">회원 정보</td>
				</tr>
				<tr align="center">
					<td>ID:</td>
					<td><%=member.getId() %></td>
					<input type="hidden" name="id" value=<%=member.getId() %>>
				</tr>
				<tr align="center">
					<td>NAME:</td>
					<td><input type="text" name="name"
						value=<%=member.getName() %> ></td>
				</tr>
				<tr align="center">
					<td>E-MAIL:</td>
					<td><input type="text" name="email"
						value=<%=member.getEmail() %> ></td>
				</tr>
				<tr align="center">
					<td>PHONE:</td>
					<td><input type="text" name="phone" value=<%=member.getPhone() %> ></td>
				</tr>
				<tr>
					<td colspan="3" align="center">
					<input type="button" value="수정" onclick="updateForm.submit()">
					<input type="button" value="다시작성" onclick="updateForm.reset()"> </td>
				</tr>


			</table>
		</center>
	</form>
</body>
</html>