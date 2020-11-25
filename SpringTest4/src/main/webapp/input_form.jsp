<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ page import="com.spring.springtest3.memberVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");

	memberVO memberVO = (memberVO)request.getAttribute("memberVO");
	
	String hobby[] = memberVO.getHobby();
	String hobby_str = "";
	int i;
	for(i = 0; i < hobby.length-1; i++){
		hobby_str += hobby[i];
		hobby_str += ", ";
	}
	hobby_str += hobby[i];
	
%>

	id : <%=memberVO.getId() %> <br>
	pw : <%=memberVO.getPw() %> <br>
	jumin : <%=memberVO.getJumin1() %> - <%=memberVO.getJumin2() %>  <br>
	gender : <%=memberVO.getGender() %> <br>
	번호 : <%=memberVO.getTel1() %> - <%=memberVO.getTel2() %> - <%=memberVO.getTel3() %> <br>
	email : <%=memberVO.getEmail1() %>@ <%=memberVO.getEmail2() %> <br>
	hobby : <%=hobby_str %> <br>
	intro : <%=memberVO.getIntro() %> <br>
</body>
</html>