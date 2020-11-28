<%@page import="com.spring.springsungjuk.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%ArrayList<SpringVO> sungjuk_list = (ArrayList<SpringVO>)request.getAttribute("sungjuk_list"); %>
<form name="loginform.me" action="loginform.me">
<center>
		<table border="1" width=300>
			<tr align="center">
				<td colspan="2">성적 목록</td>
				<td colspan="1"><a href="insert.me">성적 입력</a></td>
			</tr>
			<%
			for(int i =0; i<sungjuk_list.size(); i++){
				SpringVO vo =(SpringVO)sungjuk_list.get(i);
				%>	
				<tr align="center">
				<td><a href="info.me?hakbun=<%=vo.getHakbun() %>">
						<%=vo.getHakbun()%>
				</a></td>
			
				<td><a href="delete.me?hakbun=<%=vo.getHakbun()%>">삭제</a></td>
				<td><a href="update.me?hakbun=<%=vo.getHakbun()%>">수정</a></td>
			</tr>
			<%} %>
			<tr>
					<td align="center" colspan="3"><a
						href="javascript:indexform.submit()"> 로그아웃
				</tr>
		</table>
	</center>
</form>
</body>
</html>