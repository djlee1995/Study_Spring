<%@page import="com.spring.springsungjuk.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.spring.springsungjuk.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%
if ((session.getAttribute("hakbun") == null) || (!((String) session.getAttribute("hakbun")).equals("admin"))) {
	out.println("<script>");
	out.println("location.href='loginform.jsp'");
	out.println("</script>");
}
SpringVO vo = (SpringVO)request.getAttribute("vo");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="updateprocess.me" name="updateform" method="post">
		<center>
			<table border="1" whakbunth=300>
				<tr align="center">
					<td colspan="2">회원 정보</td>
				</tr>
				<tr align="center">
					<td>학번:</td>
					<td><%=vo.getHakbun() %></td>
					<input type="hidden" name="hakbun" value=<%=vo.getHakbun() %>>
				</tr>
				<tr align="center">
					<td>비밀번호:</td>
					<td><input type="password" name="password"
						value=<%=vo.getPassword() %> ></td>
				</tr>
				<tr align="center">
					<td>이름:</td>
					<td><input type="text" name="name"
						value=<%=vo.getName() %> ></td>
				</tr>
				<tr align="center">
					<td>나이:</td>
					<td><input type="text" name="age" value=<%=vo.getAge() %> ></td>
				</tr>
				<tr align="center">
					<td>성별:</td>
					<td>
						<%
							if (vo.getGender().equals("남") ) {
						%> <input type="radio" name="gender" value="남" checked />남자 <input
						type="radio" name="gender" value="여" >여자 <%
 							}
							else
							{
 						 %> <input type="radio" name="gender" value="남" />남자 <input
						type="radio" name="gender" value="여" checked >여자 <% }%>
					</td>
				</tr>
				<tr align="center">
					<td>이메일:</td>
					<td><input type="text" name="email"
						value=<%=vo.getEmail()%> ></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><a
						href="javascript:updateform.submit()">수정</a>&nbsp;&nbsp; <a
						href="member_list.jsp">리스트로 돌아가기</a></td>
				</tr>


			</table>
		</center>
	</form>
</body>
</html>