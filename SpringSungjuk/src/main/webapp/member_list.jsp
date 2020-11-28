
<%@page import="com.spring.springsungjuk.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sun.security.timestamp.TSRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%
	if ((session.getAttribute("hakbun") == null)
			|| (!((String) session.getAttribute("hakbun")).equals("admin"))) {
		out.println("<script>");
		out.println("location.href='loginform.me'");
		out.println("</script>");
	}

	ArrayList<SpringVO> member_list = (ArrayList<SpringVO>) request.getAttribute("member_list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="loginform.me" name="listform">
	<center>
		<table border="1" whakbunth=300>
			<tr align="center">
				<td colspan="3">회원 목록</td>
			</tr>
			<%
				for (int i = 0; i < member_list.size(); i++) {
					SpringVO vo = (SpringVO) member_list.get(i);
			%>
			<tr align="center">
				<td><a href="memberinfo.me?hakbun=<%=vo.getHakbun()%>"> <%=vo.getHakbun()%>
				</a></td>
				<td><a href="memberupdate.me?hakbun=<%=vo.getHakbun()%>">수정</a></td>
				<td><a href="memberdelete.me?hakbun=<%=vo.getHakbun()%>">삭제</a></td>

			</tr>

			<%
				}
			%>
			<tr>
				<td align="center" colspan="3"><a
					href="javascript:listform.submit()"> 로그아웃 
			</tr>
		</table>
	</center>
	</form>
</body>
</html>