<%@page import="com.spring.springsungjuk.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% SpringVO vo = (SpringVO)request.getAttribute("vo"); %>
<form action="index.me" name="sungjuk_info" method="post">
<center>
<table border="1">
	<tr> 
		<td colspan="2" align="center">
			<b><font size="5">성적  페이지</font></b>
		</td>
	</tr>
	<tr><td>학번:</td><td><%=vo.getHakbun() %></td></tr>
	<tr><td>이름:</td><td><%=vo.getName() %></td></tr>
	<tr><td>국어:</td><td><%=vo.getKor() %></td></tr>
	<tr><td>수학:</td><td><%=vo.getMath() %></td></tr>
	<tr><td>영어:</td><td><%=vo.getEng() %></td></tr>
	<tr><td>총점:</td><td><%=vo.getTot() %></td></tr>
	<tr><td>평균:</td><td><%=vo.getAvg() %></td></tr>
	<tr><td>등급:</td><td><%=vo.getGrade() %></td></tr>
	<tr>
		<td align="center" colspan="2">
		<a href="javascript:sungjuk_info.submit()">홈으로
	</tr>	
</table>
</center>
</form>
</body>
</html>