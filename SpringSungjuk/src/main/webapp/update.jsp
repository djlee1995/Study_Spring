<%@page import="com.spring.springsungjuk.sungjuk.SungjukVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% SungjukVO vo = (SungjukVO)request.getAttribute("vo"); %>
<form action="Supdateprocess.me" name="updateform" method="post">
		<center>
			<table border="1">
				<tr>
					<td colspan="2" align="center"><b><font size="5">성적입력
								페이지</font></b></td>
				</tr>
				<tr>
					<td>학번:</td>
					<td><%=vo.getHakbun()%>
						<input type="hidden" name="hakbun" value=<%=vo.getHakbun() %>> </td>
				</tr>
				<tr>
					<td>이름:</td>
					<td><input type="text" name="name" value=<%=vo.getName() %> ></td>
				</tr>
				<tr>
					<td>국어:</td>
					<td><input type="text" name="kor" maxlength="2" size="5" value=<%=vo.getKor() %> ></td>
				</tr>
				<tr>
					<td>수학:</td>
					<td><input type="text" name="math" maxlength="2" size="5" value=<%=vo.getMath() %>></td>
				</tr>
				<tr>
					<td>영어:</td>
					<td><input type="text" name="eng" maxlength="2" size="5" value=<%=vo.getEng() %>></td>
				</tr>
				<tr>
					<td align="center" colspan="2">
					<a href="javascript:updateform.submit()">성적수정</a>&nbsp;&nbsp; 
					<a href="index.me">홈 으로</a></td>
				</tr>
			</table>
		</center>
	</form>
</body>
</html>