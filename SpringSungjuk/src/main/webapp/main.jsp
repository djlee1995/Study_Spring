<%@page import="com.spring.springsungjuk.SpringVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String hakbun = null;
	String pass = null;
if (session.getAttribute("hakbun") != null) {
	hakbun = (String) session.getAttribute("hakbun");
	pass = (String) session.getAttribute("password");
} else {
	out.println("<script>");
	out.println("location.href='loginForm.me'");
	out.println("</script>");
}
SpringVO vo = (SpringVO)request.getAttribute("vo");
%>
<html>
<head>
<title>회원관리 시스템 메인 페이지</title>
</head>
<body>
<form action="loginform.me">
<center>
<h3><%=hakbun%>로 로그인 하였습니다.</h3>
	<%
		if (hakbun.equals("admin")) {
	%>
	<a href="memberlist.me">회원관리</a>
	<a href="index.me">성적관리</a>

	<%
		}else if(vo.getHakbun()==null){
	%>	
	 <h3>성적 처리중...</h3>
	<% 	
		}else{
	%>

		
			<table border="1">
				<tr>
					<td colspan="2" align="center"><b><font size="5">성적
								페이지</font></b></td>
				</tr>
				<tr>
					<td>학번:</td>
					<td><%=vo.getHakbun()%></td>
				</tr>
				<tr>
					<td>이름:</td>
					<td><%=vo.getName()%></td>
				</tr>
				<tr>
					<td>국어:</td>
					<td><%=vo.getKor()%></td>
				</tr>
				<tr>
					<td>수학:</td>
					<td><%=vo.getMath()%></td>
				</tr>
				<tr>
					<td>영어:</td>
					<td><%=vo.getEng()%></td>
				</tr>
				<tr>
					<td>총점:</td>
					<td><%=vo.getTot()%></td>
				</tr>
				<tr>
					<td>평균:</td>
					<td><%=vo.getAvg()%></td>
				</tr>
				<tr>
					<td>등급:</td>
					<td><%=vo.getGrade()%></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><a
						href="javascript:sungjuk_info.submit()"> 로그아웃 
				</tr>
			</table>
			<%} %>
		</center>
	</form>

</body>
</html>