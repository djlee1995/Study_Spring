<%@page import="java.util.List"%>
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
List<MemberVO> memberList = (List<MemberVO>)request.getAttribute("memberList");
System.out.println(memberList.get(2));
%>
<center>
<form name="listForm" action="insert.do">
	<table border="2" width =600>
		<tr align="center">
			<td>ID</td>
			<td>NAME</td>
			<td>E-MAIL</td>
			<td>PHONE</td>
			<td colspan="2">BUTTON</td>
		</tr>
		<tr align="center">
			<td> <input type="text" name="id"> </td>
			<td> <input type="text" name="name"> </td>
			<td> <input type="text" name="email"> </td>
			<td> <input type="text" name="phone"> </td>
			<td colspan="2"> <input type="button" value="추가" onclick="listForm.submit()"> </td>
			
		</tr>
		<%
			for(int i =0; i<memberList.size(); i++){
				MemberVO vo =(MemberVO)memberList.get(i);
			%>
			<tr align="center">
				<td> <%=vo.getId()%></td>
				<td> <%=vo.getName()%></td>
				<td> <%=vo.getEmail()%></td>
				<td> <%=vo.getPhone()%></td>
				<td><input type="button" value="수정" onclick="location.href='updateForm.do?id=<%=vo.getId()%>'"></td>
				<td><input type="button" value="삭제" onclick="location.href='delete.do?id=<%=vo.getId()%>'"></td>
				
				
			</tr>
			<%} %>
	</table>
</form>
</center>

</body>
</html>