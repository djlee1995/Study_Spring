<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String id = null;

if (session.getAttribute("id") != null) {
	id = (String) session.getAttribute("id");
} else {
	out.println("<script>");
	out.println("location.href='loginForm.me'");
	out.println("</script>");
}
%>
<html>
<head>
<title>회원관리 시스템 메인 페이지</title>
</head>
<body>
	<h3><%=id%>로 로그인 하였습니다.
	</h3>
	<%
		if (id.equals("admin")) {
	%>
	<a href="memberlist.me">관리자모드 접속(회원목록 보기)</a>
	<%
		}
	%>
</body>
</html>